package cn.xuweiteng.Thread02;

/**
 * 测试join()方法
 * 暂停当前的线程，知道加入的线程运行完毕，当前线程才能继续运行。
 * 比如：
 * 		在main方法中有 thread1.join();
 * 		表示thread1线程加入进来（加入main方法中）
 * 		main方法停止运行，加入的线程（thread1）运行结束后，才能运行main
 * @author MrXu
 *
 */
public class TestJoin {

	
	public static void main(String[] args) {
		
		Runnable r = () -> {
			for(int i = 0; i<200; i++) {
				System.out.println(Thread.currentThread().getName() + i);
			}
		};
		
		Thread t = new Thread(r, "thread1");
		t.start();
		for(int i = 0; i<20; i++) {
			if(i == 10) {
				 try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
			}
			System.out.println(Thread.currentThread().getName() + i);
		}
		
		
	}
}
