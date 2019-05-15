package cn.xuweiteng.Thread02;

/**
 * 测试yeild()
 * 该方法是启发式方法，通知CPU本线程愿意放弃当前CPU资源。让给其他线程，但如果CPU资源不紧张会忽略这个请求。
 * 因此该方法一般不使用
 * @author MrXu
 *
 */
public class TestYield {

	public static void main(String[] args) {
		Runnable r = () ->{
			Thread.yield();
			System.out.println(Thread.currentThread().getName());
		};
		new Thread(r, "thread1").start();
		try {
			Thread.sleep(20);
		} catch (Exception e) {
		}
		System.out.println(Thread.currentThread().getName());
		
	}
}
