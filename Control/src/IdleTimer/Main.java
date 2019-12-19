package IdleTimer;

public class Main {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		//args[0] : Input Snapshot、args[1] : シミュレーション終了時刻  args[2] : 制御スタート時刻 args[3] : 出力logファイル args[4] : 出力snapshotファイル
		MMESimulator ms = new MMESimulator(args[0], args[1], args[2]);
		ms.simulate();
			
		ms.log(args[3]);
		ms.snapshot(args[4]);


	    long end = System.currentTimeMillis();
	    System.out.println("実行時間" + (end - start) + "ミリ秒");
		

	}

}
