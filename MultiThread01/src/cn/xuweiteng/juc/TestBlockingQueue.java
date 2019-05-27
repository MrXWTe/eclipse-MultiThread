package cn.xuweiteng.juc;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 利用阻塞队列实现生产者消费者模型
 * @author MrXu
 *
 */
public class TestBlockingQueue {
	
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> queue= new ArrayBlockingQueue<>(10);
		
		Runnable produceor = ()->{
			while(true) {
				try {
					Thread.sleep(500);
					queue.put(1);
					System.out.println("生产者成功生产一个单位粮食，目前粮食总量为：" + (queue.size()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable consumer = ()->{
			while(true) {
				try {
					Thread.sleep(500);
					queue.take();
					System.out.println("消费者成功消费一个单位粮食，目前粮食总量为：" + queue.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(produceor).start();
		new Thread(consumer).start();
	}
}


