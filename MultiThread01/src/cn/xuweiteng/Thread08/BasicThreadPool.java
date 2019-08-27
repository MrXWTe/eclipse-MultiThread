package cn.xuweiteng.Thread08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicThreadPool extends Thread implements ThreadPool{
	
	// 初始化线程数量
	private final int initSize;
	
	// 线程池最大数量
	private final int maxSize;
	
	// 线程池核心数量
	private final int coreSize;
	
	// 当前活跃的线程数量
	private int activeCount;
	
	// 创建线程所需的工厂
	private final ThreadFactory threadFactory;
	
	// 任务队列
	private final RunnableQueue runnableQueue;
	
	// 线程池是否已经被关闭
	private boolean isShutDown = false;
	
	// 工作线程队列
	private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();
	
	// 默认拒绝策略
	private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
	
	// 默认线程工厂
	private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();
	
	// 保持活跃时间
	private final long keepAliveTime;
	
	// 时间单位
	private final TimeUnit timeUnit;
	
	
	
	public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
		this(initSize, maxSize, coreSize, 
				DEFAULT_THREAD_FACTORY, queueSize, 
				DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
	}

	/**
	 * 构造函数
	 * @param initSize 初始化线程数量
	 * @param maxSize 最大线程数量
	 * @param coreSize 核心线程数量
	 * @param threadFactory
	 * @param queueSize 队列长度，也是队列中任务最大数量
	 * @param denyPolicy
	 * @param keepAliveTime
	 * @param timeUnit
	 */
	public BasicThreadPool(int initSize, int maxSize, int coreSize, 
						   ThreadFactory threadFactory, int queueSize,
						   DenyPolicy denyPolicy, 
						   long keepAliveTime, TimeUnit timeUnit) {
		super();
		this.initSize = initSize;
		this.maxSize = maxSize;
		this.coreSize = coreSize;
		this.threadFactory = threadFactory;
		this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
		this.keepAliveTime = keepAliveTime;
		this.timeUnit = timeUnit;
		this.init();
	}
	
	// 初始化，首先创建一个initSize个线程
	private void init() {
		start();
		
		for(int i = 0; i<this.initSize; i++) {
			newThread();
		}
	}
	
	// 创建任务线程
	private void newThread() {
		InternalTask internalTask = new InternalTask(this.runnableQueue);
		Thread thread = this.threadFactory.cerateThread(internalTask);
		ThreadTask threadTask = new ThreadTask(thread, internalTask);
		threadQueue.offer(threadTask);
		this.activeCount++;
		thread.start();
	}
	
	@Override
	public void run() {
		// run方法继承自Thread，主要用于维护线程数量，比如扩容，回收等工作
		while(!isShutDown && !isInterrupted()) {
			try {
				timeUnit.sleep(keepAliveTime);
			}catch(InterruptedException e){
				isShutDown = true;
				break;
			}
			
			synchronized (this) {
				if(isShutDown)
					break;
				
				// 当前队列中由任务尚未处理，并且activeCount<coreSize，则继续扩容
				if(runnableQueue.size() > 0 && activeCount < coreSize) {
					for(int i = initSize; i<coreSize; i++) {
						newThread();
					}
					// continue的目的是为了不想让线程的扩容直接达到
					continue;
				}
				
				// 当前队列中由任务尚未处理，并且activeCount<maxSize，则继续扩容
				if(runnableQueue.size() > 0 && activeCount < maxSize) {
					for(int i = coreSize; i<maxSize; i++) {
						newThread();
					}
				}
				
				// 当前队列中没有任务，并且直接将线程回收至coreSize即可
				if(runnableQueue.size() == 0 && activeCount > coreSize) {
					for(int i = coreSize; i<activeCount; i++) {
						removeThread();
					}
				}
			}
		}
	}
	
	// 从线程中移除某个线程
	private void removeThread() {
		ThreadTask threadTask = threadQueue.remove();
		threadTask.internalTask.stop();
		this.activeCount--;
	}
	
	
	private static class ThreadTask{
		Thread thread;
		InternalTask internalTask;
		public ThreadTask(Thread thread, InternalTask internalTask) {
			this.thread = thread;
			this.internalTask = internalTask;
		}
	}
	
	
	private static class DefaultThreadFactory implements ThreadFactory{
		private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
		
		private static final ThreadGroup group = 
				new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());
		
		private static final AtomicInteger COUNTER = new AtomicInteger(0);

		@Override
		public Thread cerateThread(Runnable runnable) {
			return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndDecrement());
		}
	}
	
	
	/*******************************override method**************************************/
	
	// 提交任务
	@Override
	public void execute(Runnable runnable) {
		if(this.isShutDown) {
			throw new IllegalStateException("线程池已经被销毁");
		}
		
		// 将任务插入队列中
		this.runnableQueue.offer(runnable);
	}

	@Override
	public void shutdown() {
		synchronized (this) {
			if(isShutDown) return;
			isShutDown = true;
			threadQueue.forEach(threadTask -> {
				threadTask.internalTask.stop();
				threadTask.thread.interrupt();
			});
			this.interrupt();
		}
	}

	@Override
	public int getInitSize() {
		if(this.isShutDown) {
			throw new IllegalStateException("线程池已经被销毁");
		}
		return this.initSize;
	}

	@Override
	public int getMaxSize() {
		if(this.isShutDown) {
			throw new IllegalStateException("线程池已经被销毁");
		}
		return this.maxSize;
	}

	@Override
	public int getCoreSize() {
		if(this.isShutDown) {
			throw new IllegalStateException("线程池已经被销毁");
		}
		return this.coreSize;
	}

	@Override
	public int getQueueSize() {
		if(this.isShutDown) {
			throw new IllegalStateException("线程池已经被销毁");
		}
		return runnableQueue.size();
	}

	@Override
	public int getActiveSize() {
		synchronized (this) {
			return this.activeCount;
		}
	}

	@Override
	public boolean isShutDown() {
		return this.isShutDown;
	}

	
}
