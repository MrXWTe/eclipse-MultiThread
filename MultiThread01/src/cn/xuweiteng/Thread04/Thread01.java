package cn.xuweiteng.Thread04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 修改银行叫号，保证同步
 * 1、使用synchronized进行同步
 * 2、使用重入锁进行同步
 * @author MrXu
 *
 */
public class Thread01 extends Thread{
	//每个柜台最多承受50笔业务
		private static final int MAX = 50;
		
		private int index = 1;
			
//		private static Object o = new Object();
//		@Override
//		public void run() {
//			synchronized (o) {
//				while(index < MAX) {
//					System.out.println("柜台：" + Thread.currentThread().getName() + "当前的号码是：" + (index++));
//				}
//			}
//		}
		
		
		private static Lock lock = new ReentrantLock();
		@Override
		public void run() {
			lock.lock();
			try {
				while(index < MAX) {
					System.out.println("柜台：" + Thread.currentThread().getName() + "当前的号码是：" + (index++));
				}
			}finally {
				lock.unlock();
			}
		}

		public static void main(String[] args) {
			final Thread01 thread = new Thread01(); 
			Thread t1 = new Thread(thread, "一号柜台");
			Thread t2 = new Thread(thread, "二号柜台");
			Thread t3 = new Thread(thread, "三号柜台");
			Thread t4 = new Thread(thread, "四号柜台");
			
			t1.start();
			t2.start();
			t3.start();
			t4.start();
		}
}
