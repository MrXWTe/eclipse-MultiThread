package cn.xuweiteng.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、创建线程的第三种方式：实现Callable接口。
 * 相较于实现Runnable接口的方式，方法可以有返回值，并且能够抛出异常
 * 执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
 * FutureTask是Future接口的实现类
 * FutureTask也可用于闭锁
 * @author MrXu
 *
 */
public class TestCallable {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		
		FutureTask<Integer> res = new FutureTask<>(td);
		
		new Thread(res).start();
		
		// 接受线程运算后的结果
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
