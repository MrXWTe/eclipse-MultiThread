package cn.xuweiteng.Thread08;

/**
 * �����̵߳Ĺ���
 * @author MrXu
 *
 */
@FunctionalInterface
public interface ThreadFactory {
	Thread cerateThread(Runnable runnable);
}
