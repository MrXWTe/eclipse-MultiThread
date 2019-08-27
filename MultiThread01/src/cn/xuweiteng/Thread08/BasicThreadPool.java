package cn.xuweiteng.Thread08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicThreadPool extends Thread implements ThreadPool{
	
	// ��ʼ���߳�����
	private final int initSize;
	
	// �̳߳��������
	private final int maxSize;
	
	// �̳߳غ�������
	private final int coreSize;
	
	// ��ǰ��Ծ���߳�����
	private int activeCount;
	
	// �����߳�����Ĺ���
	private final ThreadFactory threadFactory;
	
	// �������
	private final RunnableQueue runnableQueue;
	
	// �̳߳��Ƿ��Ѿ����ر�
	private boolean isShutDown = false;
	
	// �����̶߳���
	private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();
	
	// Ĭ�Ͼܾ�����
	private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
	
	// Ĭ���̹߳���
	private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();
	
	// ���ֻ�Ծʱ��
	private final long keepAliveTime;
	
	// ʱ�䵥λ
	private final TimeUnit timeUnit;
	
	
	
	public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
		this(initSize, maxSize, coreSize, 
				DEFAULT_THREAD_FACTORY, queueSize, 
				DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
	}

	/**
	 * ���캯��
	 * @param initSize ��ʼ���߳�����
	 * @param maxSize ����߳�����
	 * @param coreSize �����߳�����
	 * @param threadFactory
	 * @param queueSize ���г��ȣ�Ҳ�Ƕ����������������
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
	
	// ��ʼ�������ȴ���һ��initSize���߳�
	private void init() {
		start();
		
		for(int i = 0; i<this.initSize; i++) {
			newThread();
		}
	}
	
	// ���������߳�
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
		// run�����̳���Thread����Ҫ����ά���߳��������������ݣ����յȹ���
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
				
				// ��ǰ��������������δ��������activeCount<coreSize�����������
				if(runnableQueue.size() > 0 && activeCount < coreSize) {
					for(int i = initSize; i<coreSize; i++) {
						newThread();
					}
					// continue��Ŀ����Ϊ�˲������̵߳�����ֱ�Ӵﵽ
					continue;
				}
				
				// ��ǰ��������������δ��������activeCount<maxSize�����������
				if(runnableQueue.size() > 0 && activeCount < maxSize) {
					for(int i = coreSize; i<maxSize; i++) {
						newThread();
					}
				}
				
				// ��ǰ������û�����񣬲���ֱ�ӽ��̻߳�����coreSize����
				if(runnableQueue.size() == 0 && activeCount > coreSize) {
					for(int i = coreSize; i<activeCount; i++) {
						removeThread();
					}
				}
			}
		}
	}
	
	// ���߳����Ƴ�ĳ���߳�
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
	
	// �ύ����
	@Override
	public void execute(Runnable runnable) {
		if(this.isShutDown) {
			throw new IllegalStateException("�̳߳��Ѿ�������");
		}
		
		// ��������������
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
			throw new IllegalStateException("�̳߳��Ѿ�������");
		}
		return this.initSize;
	}

	@Override
	public int getMaxSize() {
		if(this.isShutDown) {
			throw new IllegalStateException("�̳߳��Ѿ�������");
		}
		return this.maxSize;
	}

	@Override
	public int getCoreSize() {
		if(this.isShutDown) {
			throw new IllegalStateException("�̳߳��Ѿ�������");
		}
		return this.coreSize;
	}

	@Override
	public int getQueueSize() {
		if(this.isShutDown) {
			throw new IllegalStateException("�̳߳��Ѿ�������");
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
