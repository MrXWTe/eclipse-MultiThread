package cn.xuweiteng.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * 测试辅助类：CyclicBarrier
 * 
 * @author MrXu
 *
 */
public class TestCyclicBarrier {

	private static CyclicBarrier cyclicBarrier;

	static class ThreadCyclicBarrier extends Thread {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "达到...");
			try {
				cyclicBarrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "执行完成...");
		}
	}

	public static void main(String[] args) {
		cyclicBarrier = new CyclicBarrier(5, new Runnable() {

			@Override
			public void run() {
				System.out.println("执行CyclicBarrier中的任务.....");
			}
		});
		for (int i = 1; i <= 5; i++) {
			new ThreadCyclicBarrier().start();
		}
	}

}
