package cn.xuweiteng.Thread02;

/**
 * ����join()����
 * ��ͣ��ǰ���̣߳�֪��������߳�������ϣ���ǰ�̲߳��ܼ������С�
 * ���磺
 * 		��main�������� thread1.join();
 * 		��ʾthread1�̼߳������������main�����У�
 * 		main����ֹͣ���У�������̣߳�thread1�����н����󣬲�������main
 * @author MrXu
 *
 */
public class TestJoin {

	
	public static void main(String[] args) {
		
		Runnable r = () -> {
			for(int i = 0; i<200; i++) {
				System.out.println(Thread.currentThread().getName() + i);
			}
		};
		
		Thread t = new Thread(r, "thread1");
		t.start();
		for(int i = 0; i<20; i++) {
			if(i == 10) {
				 try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
			}
			System.out.println(Thread.currentThread().getName() + i);
		}
		
		
	}
}
