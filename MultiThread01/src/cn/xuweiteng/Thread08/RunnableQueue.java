package cn.xuweiteng.Thread08;
/**
 * 任务队列，用于存放提交到线程池的任务
 * 用阻塞队列BlockedQueue实现
 * @author MrXu
 *
 */
public interface RunnableQueue {

	// 当有新任务来时，需要offer进队列
	void offer(Runnable runnable);
	
	// 工作过程中利用take获取任务
	Runnable take();
	
	// 获取任务的数量
	int size();
}
