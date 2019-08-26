package cn.xuweiteng.Thread05;

import java.util.concurrent.TimeUnit;

public class ProducerAndConsumer {
	
	private static final String LOCK = "lock";
	private static final int MAX = 10;
	private static int count = 0;
	
	public static void main(String[] args) {
		ProducerAndConsumer pc = new ProducerAndConsumer();
		new Thread(pc.new Producer(), "生产者").start();
		new Thread(pc.new Consumer(), "消费者1").start();
		new Thread(pc.new Consumer(), "消费者2").start();
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
							+ ": 正在生产，当前资源个数为   " + count);
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
							+ ": 正在消费，当前资源个数为   " + count);
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

