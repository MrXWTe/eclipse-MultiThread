package cn.xuweiteng.juc;

/**
 * �����ߺ�������ģʽ
 * @author MrXu
 *
 */
public class TestProductorAndConsumer {

	public static void main(String[] args) {
		Goods good = new Goods();
		Productor product = new Productor(good);
		Consumer consumer = new Consumer(good);
		 
		new Thread(product).start();
		new Thread(consumer).start();
		
		new Thread(product).start();
		new Thread(consumer).start();
	}
}

class Goods{
	private int good  = 0;
	
	public synchronized void produce() {
		while(good == 20) {
			try {
				System.out.println("����������������������");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("������������������Ʒ����Ϊ��" + ++good);
		this.notifyAll();
		
	}
	
	public synchronized void consume() {
		while(good == 0) {
			try {
				System.out.println("Ŀǰ����ȱ�٣�֪ͨ����������");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("�������������ѣ���Ʒ����Ϊ��" + --good);
		this.notifyAll();
	}
}

class Productor implements Runnable{
	Goods good;
	public Productor(Goods good) {
		this.good = good;
	}
	@Override
	public void run() {
		while(true) {
			good.produce();
		}
	}
}

class Consumer implements Runnable{
	Goods good;
	public Consumer(Goods good) {
		this.good = good;
	}
	@Override
	public void run() {
		while(true) {
			good.consume();
		}
	}
}