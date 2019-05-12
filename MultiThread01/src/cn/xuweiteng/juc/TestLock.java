package cn.xuweiteng.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一、用于解决多线程问题的方式：
 * 	1）、synchroized:同步代码块
 * 	2）、				同步方法
 * 	3）、同步锁：Lock(jdk1.5以后)；这是一个显示锁，需要通过lock()方法上锁，unlock()方式解锁
 * @author MrXu
 *
 */
public class TestLock {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		
		new Thread(ticket, "1号窗口").start();
		new Thread(ticket, "2号窗口").start();
		new Thread(ticket, "3号窗口").start();
	}
}

class Ticket implements Runnable{
	private int tick = 100;
	private Lock lock = new ReentrantLock();
	
	@Override
	public void run() {
		lock.lock(); // 上锁
		try {
			while(tick > 0) {
				System.out.println(Thread.currentThread().getName() + "完成售票，余票为" + --tick);
			}
		} finally {
			lock.unlock(); // 释放锁
		}
		
	}
}
