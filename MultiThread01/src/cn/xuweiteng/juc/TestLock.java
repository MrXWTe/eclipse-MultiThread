package cn.xuweiteng.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * һ�����ڽ�����߳�����ķ�ʽ��
 * 	1����synchroized:ͬ�������
 * 	2����				ͬ������
 * 	3����ͬ������Lock(jdk1.5�Ժ�)������һ����ʾ������Ҫͨ��lock()����������unlock()��ʽ����
 * @author MrXu
 *
 */
public class TestLock {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		
		new Thread(ticket, "1�Ŵ���").start();
		new Thread(ticket, "2�Ŵ���").start();
		new Thread(ticket, "3�Ŵ���").start();
	}
}

class Ticket implements Runnable{
	private int tick = 100;
	private Lock lock = new ReentrantLock();
	
	@Override
	public void run() {
		lock.lock(); // ����
		try {
			while(tick > 0) {
				System.out.println(Thread.currentThread().getName() + "�����Ʊ����ƱΪ" + --tick);
			}
		} finally {
			lock.unlock(); // �ͷ���
		}
		
	}
}
