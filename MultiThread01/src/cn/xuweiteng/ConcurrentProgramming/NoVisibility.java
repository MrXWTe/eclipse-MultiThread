package cn.xuweiteng.ConcurrentProgramming;

/**
 * ���Բ��ɼ���
 * �����һֱ���У����������number������Ϊ�ڴ�ɼ������⣬ReadClass���ڴ��ж�ȡready(false)����ڸ��߳��л���һ��ready(false)
 * main�߳��޸�ready(true)���ڴ��е�ready(true)�ı䣬��ReadClass�߳��е�ready(false)ȴû�иı䡣
 * ���һֱ���С�
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
