package cn.xuweiteng.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch/闭锁：在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才算继续执行
 * @author MrXu
 *
 */
public class TestCountDownLatch {
	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(5);
		LatchDemo ld = new LatchDemo(latch);
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 5; i++) {
			new Thread(ld).start();
		}
		latch.await();
		long endTime = System.currentTimeMillis();
		System.out.println("time:" + (endTime - startTime));
	}
}

class LatchDemo implements Runnable{
	private CountDownLatch latch;
	
	public LatchDemo(CountDownLatch latch){
		this.latch = latch;
	}
	
	@Override
	public void run() {
		synchronized (this) {
			try {
				for (int i = 0; i < 50000; i++) {
					if(i % 2 == 0) System.out.println(i);
				}
			} finally {
				latch.countDown();
			}
		}
	}
}
