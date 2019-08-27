package cn.xuweiteng.Thread08;

import java.util.concurrent.TimeUnit;

/**
 * 测试线程池
 * @author MrXu
 *
 */
public class ThreadPoolTest {
	
	public static void main(String[] args) throws InterruptedException {
		// 初始化线程池，初始线程数为2， 最大线程数为6， 核心线程数为4，任务最大为1000
		final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
		
		for(int i = 0; i<20; i++) {
			threadPool.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(10);
					System.out.println(Thread.currentThread().getName() + " is running and done.");
				}catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
		for(;;) {
			System.out.println("getActiveCount: " + threadPool.getActiveSize());
			System.out.println("getQueueSize: " + threadPool.getQueueSize());
			System.out.println("getCoreSize: " + threadPool.getCoreSize());
			System.out.println("getMaxSize: " + threadPool.getMaxSize());
			System.out.println("=================================");
			TimeUnit.SECONDS.sleep(5);
		}
	}

}
