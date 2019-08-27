package cn.xuweiteng.Thread08;

import java.util.LinkedList;

/**
 * 任务队列，线程池中的多个任务会并发向该队列中取出任务进行执行
 * @author MrXu
 *
 */
public class LinkedRunnableQueue implements RunnableQueue{
	
	// 队列中最大任务数量，在构造时传入
	private final int limit;
	
	// 若队列中任务已满，执行该拒绝策略
	private final DenyPolicy denyPolicy;
	
	// 存放任务的队列
	private final LinkedList<Runnable> runnableList = new LinkedList<>();
	
	// 线程池
	private final ThreadPool threadPool;
	
	public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}

	
	/**
	 *  向任务队列中添加任务
	 *  因为会有多个线程向其中添加信息，因此需要进行同步
	 */
	@Override
	public void offer(Runnable runnable) {
		synchronized (runnableList) {
			if(runnableList.size() >= limit) {
				// 表示队列中的任务满了，需要执行拒绝策略
				denyPolicy.reject(runnable, threadPool);
			}else {
				// 将任务添加至队列尾部
				runnableList.addLast(runnable);
				runnableList.notifyAll();
			}
		}
		
	}
	
	
	// 向任务队列中取出任务
	@Override
	public Runnable take(){
		synchronized (runnableList) {
			while(runnableList.isEmpty()) {
				try {
					// 如果任务队列中没有可执行的任务，则当前线程将会挂起，进入runnableList关联的monitor waitset
					// 中等待唤醒
					runnableList.wait();
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// 将任务队列头返回
			return runnableList.removeFirst();
		}
	}
	
	
	// 返回当前任务队列中的任务数
	@Override
	public int size() {
		synchronized (runnableList) {
			return runnableList.size();
		}
	}
	
	
}
