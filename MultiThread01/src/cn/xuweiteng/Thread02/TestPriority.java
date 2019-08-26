package cn.xuweiteng.Thread02;

/**
 * 测试线程优先级
 * 设计多线程应用程序的时候，不能依赖设置线程优先级来决定线程调度的先后，这是没有保障的
 * @author MrXu
 *
 */
public class TestPriority {

	public static void main(String[] args) {
		Runnable r = ()->{
			for(int i = 0; i<200; i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		};
		
		Thread t1 = new Thread(r, "thread1");
		Thread t2 = new Thread(r, "thread2");
		
		t1.setPriority(Thread.MAX_PRIORITY);	// 代表线程优先级为 1
		t2.setPriority(Thread.MIN_PRIORITY);	// 代表线程优先级为 10
		
		t2.start();
		t1.start();
	}
	
}
