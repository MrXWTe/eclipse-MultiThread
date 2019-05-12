package cn.xuweiteng.juc;

/**
 * 生产者和消费者模式
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
				System.out.println("货物已满，消费者请消费");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("生产者正在生产，商品数量为：" + ++good);
		this.notifyAll();
		
	}
	
	public synchronized void consume() {
		while(good == 0) {
			try {
				System.out.println("目前货物缺少，通知生产者生产");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("消费者正在消费，商品数量为：" + --good);
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