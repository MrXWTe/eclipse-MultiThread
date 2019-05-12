package cn.xuweiteng.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * һ�������̵߳ĵ����ַ�ʽ��ʵ��Callable�ӿڡ�
 * �����ʵ��Runnable�ӿڵķ�ʽ�����������з���ֵ�������ܹ��׳��쳣
 * ִ��Callable��ʽ����ҪFutureTaskʵ�����֧�֣����ڽ���������
 * FutureTask��Future�ӿڵ�ʵ����
 * FutureTaskҲ�����ڱ���
 * @author MrXu
 *
 */
public class TestCallable {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		
		FutureTask<Integer> res = new FutureTask<>(td);
		
		new Thread(res).start();
		
		// �����߳������Ľ��
		try {
			System.out.println(res.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}

class ThreadDemo implements Callable<Integer>{
	
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		
		for (int i = 0; i <= 100; i++) {
			sum+=i;
		}
		return sum;
	}
}
