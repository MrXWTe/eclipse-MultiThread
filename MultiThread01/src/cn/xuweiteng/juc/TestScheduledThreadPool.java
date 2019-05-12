package cn.xuweiteng.juc;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
public class TestScheduledThreadPool {
	public static void main(String[] args) throws Exception {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

		for (int i = 0; i < 5; i++) {
			Future<Integer> result = pool.schedule(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					int num = new Random().nextInt(100);// ���������
					System.out.println(Thread.currentThread().getName() + " : " + num);
					return num;
				}

			}, 2, TimeUnit.SECONDS);

			System.out.println(result.get());
		}

		pool.shutdown();
	}
}
