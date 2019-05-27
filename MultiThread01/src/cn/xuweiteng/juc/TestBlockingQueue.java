package cn.xuweiteng.juc;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ������������ʵ��������������ģ��
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
					System.out.println("�����߳ɹ�����һ����λ��ʳ��Ŀǰ��ʳ����Ϊ��" + (queue.size()));
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
					System.out.println("�����߳ɹ�����һ����λ��ʳ��Ŀǰ��ʳ����Ϊ��" + queue.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(produceor).start();
		new Thread(consumer).start();
	}
}


