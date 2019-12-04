package datamaker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import IdleTimer.UESimulator;

public class dataMaker {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		datamake4();
		long end = System.currentTimeMillis();
	    System.out.println("実行時間" + (end - start) + "ミリ秒");

	}
	private static void datamake4() {
		FileWriter fw;
		BufferedWriter bw;
		PrintWriter pw = null;
		
		String fileName = "scenario5.csv";
		//mmeの設定
		int timeStep = 0;
		int idleTimer = 600;
		//ueの設定
		int id = 1;
		int onTime = 1;
		int offTime = -1;
		int state = 3;
		int myIdleTimer = 0;
		int lastTransmitTime = 0;
		
		int loop1 = 86400;
		int loop1_set = 3;
		int interval1 = 86400;
		
		int loop2 = 7200;
		int loop2_set = 36;
		int interval2 = 7200;
		
		int loop3 = 3600;
		int loop3_set = 27;
		int interval3 = 3600;
		
		int loop4 = 1800;
		int loop4_set = 18;
		int interval4 = 1800;
		

		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.printf("timeStep,idleTimer\n");
			pw.printf("%s,%s\n",String.valueOf(timeStep),String.valueOf(idleTimer));
			pw.printf("id,interval,onTime,offTime,state,myIdleTimer,lastTransmitTime\n");
			for(int i = 1; i <= loop1; i++) {
				for (int t = 1; t <= loop1_set; t++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval1),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
					id++;
				}
				onTime++;
			}
			onTime = 1;
			for(int i = 1; i <= loop2; i++) {
				for (int t = 1; t <= loop2_set; t++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval2),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
					id++;
				}
				onTime++;
			}
			onTime = 1;
			for(int i = 1; i <= loop3; i++) {
				for (int t = 1; t <= loop3_set; t++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval3),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
					id++;
				}
				onTime++;
			}
			onTime = 1;
			for(int i = 1; i <= loop4; i++) {
				for (int t = 1; t <= loop4_set; t++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval4),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
					id++;
				}
				onTime++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("fileの書き込みに失敗しました");
			System.exit(1);
		} finally {
			pw.close();
		}
	}
	
	private static void datamake3() {
		FileWriter fw;
		BufferedWriter bw;
		PrintWriter pw = null;
		
		String fileName = "scenario4.csv";
		//mmeの設定
		int timeStep = 0;
		int idleTimer = 3600;
		//ueの設定
		int id = 1;
		int interval = 86400;
		int onTime = 1;
		int offTime = -1;
		int state = 3;
		int myIdleTimer = 0;
		int lastTransmitTime = 0;
		int loop1 = 86400;
		int loop2 = 21600;
		int loop2_set = 4;

		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.printf("timeStep,idleTimer\n");
			pw.printf("%s,%s\n",String.valueOf(timeStep),String.valueOf(idleTimer));
			pw.printf("id,interval,onTime,offTime,state,myIdleTimer,lastTransmitTime\n");
			for(int i = 1; i <= loop1; i++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
				id++;
				onTime++;
			}
			onTime += 21600;
			for(int i = 1; i <= loop2; i++) {
				for (int t = 1; t <= loop2_set; t++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
					id++;
				}
			onTime++;
		}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("fileの書き込みに失敗しました");
			System.exit(1);
		} finally {
			pw.close();
		}
	}
	
	private static void datamake2() {
		FileWriter fw;
		BufferedWriter bw;
		PrintWriter pw = null;
		
		String fileName = "scenario3.csv";
		//mmeの設定
		int timeStep = 0;
		int idleTimer = 600;
		//ueの設定
		int id = 1;
		int interval = 86400;
		int onTime = 1;
		int offTime = -1;
		int state = 3;
		int myIdleTimer = 0;
		int lastTransmitTime = 0;
		int loop1 = 86400;
		int loop1_set = 12;

		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.printf("timeStep,idleTimer\n");
			pw.printf("%s,%s\n",String.valueOf(timeStep),String.valueOf(idleTimer));
			pw.printf("id,interval,onTime,offTime,state,myIdleTimer,lastTransmitTime\n");
			for(int i = 1; i <= loop1; i++) {
				for (int t = 1; t <= loop1_set; t++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
					id++;
				}
				onTime++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("fileの書き込みに失敗しました");
			System.exit(1);
		} finally {
			pw.close();
		}
	}
	private static void datamake() {
		FileWriter fw;
		BufferedWriter bw;
		PrintWriter pw = null;
		
		String fileName = "testData3.csv";
		//mmeの設定
		int timeStep = 0;
		int idleTimer = 600;
		//ueの設定
		int id = 1;
		int interval = 86400;
		int onTime = 1;
		int offTime = -1;
		int state = 3;
		int myIdleTimer = 0;
		int lastTransmitTime = 0;
		int loop1 = 36800;
		int loop1_set = 11;
		int loop2 = 49600;
		int loop2_set = 12;

		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.printf("timeStep,idleTimer\n");
			pw.printf("%s,%s\n",String.valueOf(timeStep),String.valueOf(idleTimer));
			pw.printf("id,interval,onTime,offTime,state,myIdleTimer,lastTransmitTime\n");
			for(int i = 1; i <= loop1; i++) {
				for (int t = 1; t <= loop1_set; t++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
					id++;
				}
				onTime++;
			}
			for(int i = 1; i <= loop2; i++) {
				for (int t = 1; t <= loop2_set; t++) {
					pw.printf("%s,%s,%s,%s,%s,%s,%s\n",String.valueOf(id),String.valueOf(interval),
							String.valueOf(onTime),String.valueOf(offTime),String.valueOf(state),
							String.valueOf(myIdleTimer),String.valueOf(lastTransmitTime));
					id++;
				}
				onTime++;
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
