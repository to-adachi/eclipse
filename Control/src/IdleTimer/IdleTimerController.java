package IdleTimer;

public class IdleTimerController {
	private float diff = 0;
	private float preDiff = 0;
	private float integrationValue = 0;
	private float D = 0;
	private float preD = 0;
	private final float pGain = 0.318f;
//	private final float iGain =  0.0000463f;
//	private final float dGain = 0f;
	private final float Ti = 3725f;
	private final float Td = 931.25f;
//	private final float eta = 0.125f;
	
	public float control(float cpuLoad, float memoryLoad, int cpuMax, int memoryMax, int ueNumber) {
		if(ueNumber <=0) return 0;	//ueが0台の時はIdleタイマを制御しない
		
		if(cpuLoad > cpuMax) {		//負荷がmax値を超えている場合は現実にはありえないので、補正
			cpuLoad = cpuMax;
		}
		float cpuCapa = cpuMax / cpuLoad;
		
//		if(averageCPU > cpuMax) {		//負荷がmax値を超えている場合は現実にはありえないので、補正
//			averageCPU = cpuMax;
//		}
//		float cpuCapa = cpuMax / averageCPU;
		
		if(memoryLoad > memoryMax) {
			memoryLoad = memoryMax;
		}
		float memoryCapa = memoryMax / memoryLoad;
		diff = memoryCapa - cpuCapa ;
		integrationValue += diff;
		
		//Ideal PID
			float u = pGain * (diff +  integrationValue / Ti + Td * (diff - preDiff));
			//float u = pGain * diff + iGain * integrationValue + dGain * (diff - preDiff);
			preDiff = diff;
		
		//practice PID
	//		D = (Td * (diff - preDiff) + eta * Td * preD) / (1 + eta * Td); 
	//		float u = pGain * (diff +  integrationValue / Ti + D);
	//		preD = D;
	//		preDiff = diff;
		
		
//		System.out.printf("%f,%f,%f,%f,%f\n", cpuCapa,memoryCapa,diff,preDiff,u);
		return u;
	}

}
