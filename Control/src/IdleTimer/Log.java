package IdleTimer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Log {
	private ArrayList<String> outData = new ArrayList();
	
	Log(){
		outData.add("timeStep,idleTimer,ueON,ueConnect,ueInactive,ueIdle,ueOFF,cpuLoad,memoryLoad");
	}
	
	protected void set(int v1, float v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
		String text = Integer.toString(v1) + "," + String.valueOf(v2) + "," + Integer.toString(v3) + "," + Integer.toString(v4) + "," + Integer.toString(v5) + "," + Integer.toString(v6) + "," + Integer.toString(v7) + "," + Integer.toString(v8) + "," + Integer.toString(v9);
		outData.add(text);
		return;
	}
	
	protected void output(String logFile) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(logFile);
			for(String o : outData) {
				pw.println(o);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("fileの書き込みに失敗しました");
			System.exit(1);
		}finally {
			pw.close();
		}
		
	}

}
