package cn.xuweiteng.Thread08;

/**
 * 线程池接口
 * @author MrXu
 *
 */
public interface ThreadPool {

	// 提交任务到线程池
	void execute(Runnable runnable);
	
	// 关闭线程池
	void shutdown();
	
	// 获取线程池的初始化大小
	int getInitSize();
	
	// 获取线程池最大数量
	int getMaxSize();
	
	// 获取线程池的核心线程数量
	int getCoreSize();
	
	// 获取线程池中用于缓存队列的大小
	int getQueueSize();
	
	// 获取线程池中活跃线程的数量
	int getActiveSize();
	
	// 查看线程池是否被关闭
	boolean isShutDown();
}
