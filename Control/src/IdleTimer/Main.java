package IdleTimer;

public class Main {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		//args[0] : file名、args[1] : シミュレーション終了時刻
		MMESimulator ms = new MMESimulator(args[0], args[1], args[2]);
		ms.simulate();
		if(args.length == 4) {
			ms.snapshot(args[3]);
		}


	    long end = System.currentTimeMillis();
	    System.out.println("実行時間" + (end - start) + "ミリ秒");
		

	}

}
