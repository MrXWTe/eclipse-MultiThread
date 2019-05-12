package cn.xuweiteng.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �����ߺ�������ģʽ
 * ʹ��Lock��ʵ��
 * @author MrXu
 *
 */
public class TestProductorAndConsumerWithLock {

	public static void main(String[] args) {
		Goods1 good = new Goods1();
		Productor1 product = new Productor1(good);
		Consumer1 consumer = new Consumer1(good);
		 
		new Thread(product).start();
		new Thread(consumer).start();
		
		new Thread(product).start();
		new Thread(consumer).start();
	}
}

class Goods1{
	private int good  = 0;
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void produce() {
		lock.lock();
		try {
			while(good == 20) {
				try {
					System.out.println("����������������������");
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("������������������Ʒ����Ϊ��" + ++good);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
		
		
	}
	
	public void consume() {
		
		lock.lock();
		try {
			while(good == 0) {
				try {
					System.out.println("Ŀǰ����ȱ�٣�֪ͨ����������");
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("�������������ѣ���Ʒ����Ϊ��" + --good);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
		
	}
}

class Productor1 implements Runnable{
	Goods1 good;
	public Productor1(Goods1 good) {
		this.good = good;
	}
	@Override
	public void run() {
		while(true) {
			good.produce();
		}
	}
}

class Consumer1 implements Runnable{
	Goods1 good;
	public Consumer1(Goods1 good) {
		this.good = good;
	}
	@Override
	public void run() {
		while(true) {
			good.consume();
		}
	}
}