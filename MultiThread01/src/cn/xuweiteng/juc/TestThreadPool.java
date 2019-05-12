package cn.xuweiteng.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �̳߳�
 * JUC���̳߳ص���ϵ�ṹ��
 * java.util.concurrent.Executor:�����̵߳�ʹ�ú͵��ȵĸ��ӿ�
 * 		|--ExecutorService	�ӽӿڣ��̳߳ص���Ҫ�ӿڣ�**important��
 * 			|--ThreadPoolExecutor	ʵ����
 * 			|--ScheduledExecutorService �ӽӿڣ������̵߳ĵ���
 * 				|--ScheduledThreadPoolExecutor ���̳� ThreadPoolExecutor�� ʵ�� ScheduledExecutorService
 * 
 * JUC������ : Executors 
 * ExecutorService newFixedThreadPool() : �����̶���С���̳߳�
 * ExecutorService newCachedThreadPool() : �����̳߳أ��̳߳ص��������̶������Ը��������Զ��ĸ���������
 * ExecutorService newSingleThreadExecutor() : ���������̳߳ء��̳߳���ֻ��һ���߳�
 * 
 * ScheduledExecutorService newScheduledThreadPool() : �����̶���С���̣߳������ӳٻ�ʱ��ִ������
 * 
 * @author MrXu
 *
 */
public class TestThreadPool {
	
	public static void main(String[] args) {
		// 1�������̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		// 2��Ϊ�̳߳��е��̷߳�������
		for (int i = 0; i < 10; i++) {
			pool.submit(new ThreadPoolDemo());
		}
		
		// 3���ر��̳߳�
		pool.shutdown();
	}

}

class ThreadPoolDemo implements Runnable{

	private int i = 0;
	
	@Override
	public void run() {
		while(i <= 100){
			System.out.println(Thread.currentThread().getName() + " : " + i++);
		}
	}
	
}
