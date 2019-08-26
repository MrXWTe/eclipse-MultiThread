package cn.xuweiteng.Thread05;

import java.util.concurrent.TimeUnit;

public class ProducerAndConsumer {
	
	private static final String LOCK = "lock";
	private static final int MAX = 10;
	private static int count = 0;
	
	public static void main(String[] args) {
		ProducerAndConsumer pc = new ProducerAndConsumer();
		new Thread(pc.new Producer(), "������").start();
		new Thread(pc.new Consumer(), "������1").start();
		new Thread(pc.new Consumer(), "������2").start();
	}
	
	class Producer implements Runnable{
		@Override
		public void run() {
			while(true) {
				synchronized (LOCK) {
					while(count > MAX) {
						try {
							LOCK.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					count++;
					System.out.println(Thread.currentThread().getName() 
							+ ": ������������ǰ��Դ����Ϊ   " + count);
					LOCK.notifyAll();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	class Consumer implements Runnable{
		@Override
		public void run() {
			while(true) {
				synchronized (LOCK) {
					while(count <= 0) {
						try {
							LOCK.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					count--;
					System.out.println(Thread.currentThread().getName() 
							+ ": �������ѣ���ǰ��Դ����Ϊ   " + count);
					LOCK.notifyAll();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

