package IdleTimer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MMESimulator {
	//MMEの設定
	private final int CPU_MAX = 1200;
	private final int MEMORY_MAX = 1000 * 1048576; //(byte)
	private final float M_C = 2234.75f; //17878bit => 2234.75byte
	private final float M_CI = 2234.75f;
	private final int M_I = 51;	//408 => 51byte
	private final int S_C_C = 0;
	private final int S_C_CI = 0;
	private final int S_C_I = 5;
	private final int S_CI_C = 0;
	private final int S_CI_CI = 0;
	private final int S_CI_I = 5;
	private final int S_I_C = 5;
	private final int S_I_I = 0;
	private final IdleTimerController itc;
	private final Log log;
	private ArrayList<UESimulator> ueList = new ArrayList();
	
	//CPU負荷の移動平均観測
	private float averageCPU = 0;
	private final float eta = 1f;
	
	//MMEの情報（変動値）
	private int timeStep;
	private float idleTimer;
	private int endTimeStep;
	private int controlStartTimeStep;
	
	MMESimulator(String fileName, String a, String b){
		FileInputStream fis;
		InputStreamReader isr;
		BufferedReader br = null;
		try {
			
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String line;
			String[] arr;
			//MMEの情報をファイルから読み込み、設定
			br.readLine();//1行目を読み飛ばす
			String[] info = br.readLine().split(",");
			timeStep = Integer.parseInt(info[0]);
			idleTimer = Float.parseFloat(info[1]);
			//UEの情報をファイルから読み込み、UE毎にインスタンスを生成
			br.readLine();//3行目を読み飛ばす
			while ((line = br.readLine()) != null) {
				arr = line.split(",");
				ueList.add(new UESimulator(arr));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("fileが存在しません");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("fileの読み込みに失敗しました");
			System.exit(1);
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("fileをcloseできませんでした");
				System.exit(1);
			}
		}
		endTimeStep = Integer.parseInt(a);
		controlStartTimeStep = Integer.parseInt(b);
		itc = new IdleTimerController();
		log = new Log();
	}
	
	protected void simulate() {
		while(timeStep <= endTimeStep) {
			Integer[] load; //[0]:ueON, [1]:ueConnected, [2]:ueInactive, [3]:ueIdle, [4]:ueOFF, [5]:cpuLoad, [6]:memoryLoad
			//負荷の導出とIdleTimerの更新を同時に行う
			load = loadCalculation();
			//Logの書き出し
			log.set(timeStep, idleTimer, load[0], load[1], load[2], load[3], load[4], load[5], load[6]);
			
			averageCPU = eta * load[5] + (1 - eta) * averageCPU;
//			System.out.printf("##############%f\n", averageCPU);
			//Idleタイマの更新
			if(timeStep >= controlStartTimeStep) {
				idleTimer += itc.control(averageCPU, load[6], CPU_MAX, MEMORY_MAX, load[0]);
				if(idleTimer < 10) {
					idleTimer = 10;
				}
			}
			timeStep++;
		}
	}
	
	private Integer[] loadCalculation() {
		String[] result = {null,null};
		Integer[] load = {0,0,0,0,0,0,0,0};
		for(UESimulator s : ueList) {
			result = s.simulate(timeStep ,idleTimer);
			switch(result[0]) {
			case "Connected":
				switch(result[1]) {
				case "Connected":
					load[0]++;
					load[1]++;
					load[5] += S_C_C;
					break;
				case "Inactive":
					load[0]++;
					load[2]++;
					load[5] += S_C_CI;
					break;
				case "Idle":
					load[0]++;
					load[3]++;
					load[5] += S_C_I;
					break;
				case "Disconnected":
					//TODO
					load[4]++;
					break;
				}
				break;
			case "Inactive":
				switch(result[1]) {
				case "Connected":
					load[0]++;
					load[1]++;
					load[5] += S_CI_C;
					break;
				case "Inactive":
					load[0]++;
					load[2]++;
					load[5] += S_CI_CI;
					break;
				case "Idle":
					load[0]++;
					load[3]++;
					load[5] += S_CI_I;
					break;
				case "Disconnected":
					//TODO
					load[4]++;
					break;
				}
				break;
			case "Idle":
				switch(result[1]) {
				case "Connected":
					load[0]++;
					load[1]++;
					load[5] += S_I_C;
					break;
				case "Inactive":
					System.err.println("状態遷移が異常です1");
					System.exit(1);
					break;
				case "Idle":
					load[0]++;
					load[3]++;
					load[5] += S_I_I;
					break;
				case "Disconnected":
					//TODO
					load[4]++;
					break;
				}
				break;
			case "Disconnected":
				switch(result[1]) {
				case "Connected":
					load[0]++;
					load[1]++;
					//TODO
					load[5] += S_I_C; //??
					break;
				case "Disconnected":
					load[4]++;
					break;
				default:
					System.err.println("状態遷移が異常です2");
					System.err.println(result[1]);
					System.exit(1);
				}
				break;
			default:
				System.err.println("状態遷移が異常です3");
				System.exit(1);
			}	
		}
		//8*1024*1024
		load[6] = (int) (load[1]*M_C + load[2]*M_CI + load[3]*M_I);
		return load;
	}
	
	protected void log(String logFile) {
		log.output(logFile);
	}
	protected void snapshot(String outFile) {
		FileWriter fw;
		BufferedWriter bw;
		PrintWriter pw = null;
		ArrayList<ArrayList<String>> snapshots = new ArrayList();
		
		for(UESimulator s : ueList) {
			snapshots.add(s.snapshot());
		}
		try {
			fw = new FileWriter(outFile);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.printf("timeStep,idleTimer\n");
			pw.printf("%s,%s\n",String.valueOf(timeStep),String.valueOf(idleTimer));
			pw.printf("id,interval,onTime,offTime,state,myIdleTimer,lastTransmitTime\n");
			for(ArrayList<String> s : snapshots) {
				pw.println(String.join(",", s));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("fileの書き込みに失敗しました");
			System.exit(1);
		} finally {
			pw.close();
		}
		
		
	}
                                                                      
}
