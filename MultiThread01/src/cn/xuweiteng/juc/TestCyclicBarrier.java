package cn.xuweiteng.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * ���Ը����ࣺCyclicBarrier
 * 
 * @author MrXu
 *
 */
public class TestCyclicBarrier {

	private static CyclicBarrier cyclicBarrier;

	static class ThreadCyclicBarrier extends Thread {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "�ﵽ...");
			try {
				cyclicBarrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "ִ�����...");
		}
	}

	public static void main(String[] args) {
		cyclicBarrier = new CyclicBarrier(5, new Runnable() {

			@Override
			public void run() {
				System.out.println("ִ��CyclicBarrier�е�����.....");
			}
		});
		for (int i = 1; i <= 5; i++) {
			new ThreadCyclicBarrier().start();
		}
	}

}
