package cn.xuweiteng.Thread08;

/**
 * 用于线程池内部，该类会用到RunnableQueue，不断从queue中取出某个runnable，并运行runnable的run方法
 * @author MrXu
 *
 */
public class InternalTask implements Runnable{
	
	private final RunnableQueue runnableQueue;
	private volatile boolean running = true;
	
	public InternalTask(RunnableQueue runnableQueue) {
		this.runnableQueue = runnableQueue;
	}
	
	@Override
	public void run() {
		// 如果当前线程为running并且没有被中断，则不断从runnableQueue中取出runnable，然后执行run方阿飞
		while(running && !Thread.currentThread().isInterrupted()) {
			try {
				Runnable task = runnableQueue.take();
				task.run();
			}catch (Exception e) {
				running = false;
				break;
			}
		}
	}
	
	// 停止当前任务，主要会在线程池的shutdown方法中使用
	public void stop() {
		this.running = false;
	}
}
