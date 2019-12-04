package IdleTimer;

import java.util.ArrayList;

public class UESimulator {
	//ue共通の設定
	private final ArrayList<String> stateList = new ArrayList<>(){
		{
			add("Connected");
			add("Inactive");
			add("Idle");
			add("Disconnected");
		}
	};
	private final int inactiveTimer = 10;
	
	//ue毎の設定
	private final int id;			//ueのID
	private final int interval;		//通信周期
	private final int onTime;		//起動する時刻
	private final int offTime;		//停止する時刻
	private String state;			//状態
	private float myIdleTimer;		//ueに設定されているIdleタイマ
	private int lastTransmitTime;	//最後にデータ送信をした時刻
//	private final int option1;
//	private final int option2;
	
	UESimulator(String[] ueInfo){
		id = Integer.parseInt(ueInfo[0]);
		interval = Integer.parseInt(ueInfo[1]);
		onTime = Integer.parseInt(ueInfo[2]);
		offTime = Integer.parseInt(ueInfo[3]);
		state = stateList.get(Integer.parseInt(ueInfo[4]));
		myIdleTimer = Float.parseFloat(ueInfo[5]);
		lastTransmitTime = Integer.parseInt(ueInfo[6]);
//		option1 = Integer.parseInt(ueInfo[7]);
//		option2 = Integer.parseInt(ueInfo[8]);
		if(onTime >= offTime & offTime != -1) {
			System.err.print("onTimeとoffTimeが不正です。id = ");
			System.err.println(id);
			System.exit(1);
		}
	}
	
	protected String[] simulate(int timeStep, float idleTimer) {
		String[] stateTran = {null,null}; //[0]:source, [1]:destination
		stateTran[0] = state;
		if(timeStep == offTime) { // any state -> Disconnected
			state = stateList.get(3);
			stateTran[1] = state;
			return stateTran;
		}
		Integer t = timeStep - lastTransmitTime; //最後のデータ送信からの経過時間
		if(state == stateList.get(0)) {
			if(t >= interval) { //Connect -> Connect (そのまま)
				lastTransmitTime = timeStep;
				myIdleTimer = idleTimer;
			}else if(t >= myIdleTimer) { //Connect -> Idle
				state = stateList.get(2);
			}else if(t >= inactiveTimer) { //Connect -> Inactive
				state = stateList.get(1);
			}
		}else if(state == stateList.get(1)) {
			if(t >= interval) { //Inactive -> Connect
				state = stateList.get(0);
				lastTransmitTime = timeStep;
				myIdleTimer = idleTimer;
			}else if(t >= myIdleTimer) { //Inactive -> Idle
				state = stateList.get(2);
			}
		}else if(state == stateList.get(2)) {
			if(t >= interval) { //Idle -> Connect
				state = stateList.get(0);
				lastTransmitTime = timeStep;
				myIdleTimer = idleTimer;
			}
		}else if(state == stateList.get(3)) {
			if(timeStep == onTime) { //Disconnected -> Connected
				state = stateList.get(0);
				lastTransmitTime = timeStep;
				myIdleTimer = idleTimer;
			}
		}
		stateTran[1] = state;
		return stateTran;
	}
	
	protected ArrayList<String> snapshot() {
		ArrayList<String> snap = new ArrayList<String>();
		snap.add(String.valueOf(id));
		snap.add(String.valueOf(interval));
		snap.add(String.valueOf(onTime));
		snap.add(String.valueOf(offTime));
		snap.add(String.valueOf(stateList.indexOf(state)));
		snap.add(String.valueOf(myIdleTimer));
		snap.add(String.valueOf(lastTransmitTime));
		return snap;
	}

}
