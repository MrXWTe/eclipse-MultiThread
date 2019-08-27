package cn.xuweiteng.Thread08;

/**
 * 创建线程的工厂
 * @author MrXu
 *
 */
@FunctionalInterface
public interface ThreadFactory {
	Thread cerateThread(Runnable runnable);
}
