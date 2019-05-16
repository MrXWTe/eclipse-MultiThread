package cn.xuweiteng.ConcurrentProgramming;

/**
 * 测试不可见性
 * 结果：一直运行，不会输出“number”。因为内存可见性问题，ReadClass从内存中读取ready(false)后会在该线程中缓存一个ready(false)
 * main线程修改ready(true)后，内存中的ready(true)改变，而ReadClass线程中的ready(false)却没有改变。
 * 因此一直运行。
 * 
 * @author MrXu
 *
 */
public class NoVisibility {

	private static volatile boolean ready;
	private static int number;
	
	private static class ReadClass extends Thread{
		@Override
		public void run() {
			while(!ready) {
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		ReadClass rc = new ReadClass();
		rc.start();
		
		number = 43;
		ready = true;
	}
}
