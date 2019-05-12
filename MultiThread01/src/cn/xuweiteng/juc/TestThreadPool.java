package cn.xuweiteng.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * JUC中线程池的体系结构：
 * java.util.concurrent.Executor:负责线程的使用和调度的根接口
 * 		|--ExecutorService	子接口：线程池的主要接口（**important）
 * 			|--ThreadPoolExecutor	实现类
 * 			|--ScheduledExecutorService 子接口：负责线程的调度
 * 				|--ScheduledThreadPoolExecutor ：继承 ThreadPoolExecutor， 实现 ScheduledExecutorService
 * 
 * JUC工具类 : Executors 
 * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
 * 
 * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
 * 
 * @author MrXu
 *
 */
public class TestThreadPool {
	
	public static void main(String[] args) {
		// 1、创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		// 2、为线程池中的线程分配任务
		for (int i = 0; i < 10; i++) {
			pool.submit(new ThreadPoolDemo());
		}
		
		// 3、关闭线程池
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
