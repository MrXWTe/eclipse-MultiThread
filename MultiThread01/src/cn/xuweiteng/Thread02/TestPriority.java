package cn.xuweiteng.Thread02;

/**
 * �����߳����ȼ�
 * ��ƶ��߳�Ӧ�ó����ʱ�򣬲������������߳����ȼ��������̵߳��ȵ��Ⱥ�����û�б��ϵ�
 * @author MrXu
 *
 */
public class TestPriority {

	public static void main(String[] args) {
		Runnable r = ()->{
			for(int i = 0; i<200; i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		};
		
		Thread t1 = new Thread(r, "thread1");
		Thread t2 = new Thread(r, "thread2");
		
		t1.setPriority(Thread.MAX_PRIORITY);	// �����߳����ȼ�Ϊ 1
		t2.setPriority(Thread.MIN_PRIORITY);	// �����߳����ȼ�Ϊ 10
		
		t2.start();
		t1.start();
	}
	
}
