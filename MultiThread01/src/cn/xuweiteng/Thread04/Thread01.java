package cn.xuweiteng.Thread04;

/**
 * �޸����нкţ���֤ͬ��
 * @author MrXu
 *
 */
public class Thread01 extends Thread{
	//ÿ����̨������50��ҵ��
		private static final int MAX = 50;
		
		private int index = 1;
		
		private static Object o = new Object();
			
		@Override
		public void run() {
			synchronized (o) {
				while(index < MAX) {
					System.out.println("��̨��" + Thread.currentThread().getName() + "��ǰ�ĺ����ǣ�" + (index++));
				}
			}
		}

		public static void main(String[] args) {
			final Thread01 thread = new Thread01(); 
			Thread t1 = new Thread(thread, "һ�Ź�̨");
			Thread t2 = new Thread(thread, "���Ź�̨");
			Thread t3 = new Thread(thread, "���Ź�̨");
			Thread t4 = new Thread(thread, "�ĺŹ�̨");
			
			t1.start();
			t2.start();
			t3.start();
			t4.start();
		}
}
