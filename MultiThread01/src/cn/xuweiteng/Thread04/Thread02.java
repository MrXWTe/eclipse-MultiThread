package cn.xuweiteng.Thread04;

import java.util.concurrent.TimeUnit;
/**
 * 利用Jconsole检测线程状态
 * @author MrXu
 *
 */
public class Thread02 {

	private final static Object THREAD = new Object();
	
	public void accessResource() {
		synchronized (THREAD) {
			try {
				TimeUnit.MINUTES.sleep(10);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final Thread02 thread = new Thread02();
		for(int i=0; i<5; i++) {
			new Thread(thread::accessResource).start();
		}
	}
}
