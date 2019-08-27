package cn.xuweiteng.Thread08;

import java.util.concurrent.TimeUnit;

/**
 * �����̳߳�
 * @author MrXu
 *
 */
public class ThreadPoolTest {
	
	public static void main(String[] args) throws InterruptedException {
		// ��ʼ���̳߳أ���ʼ�߳���Ϊ2�� ����߳���Ϊ6�� �����߳���Ϊ4���������Ϊ1000
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
