package cn.xuweiteng.Thread01;

/**
 * ʵ��Runnable�ӿ�����ģ�����й�̨����
 * @author MrXu
 *
 */
public class MyThread03 implements Runnable{

	//ÿ����̨������50��ҵ��
	private static final int MAX = 50;
	
	private int index = 1;
		
	@Override
	public void run() {
		while(index < MAX) {
			System.out.println("��̨��" + Thread.currentThread().getName() + "��ǰ�ĺ����ǣ�" + (index++));
		}
	}

	public static void main(String[] args) {
		final MyThread03 thread = new MyThread03(); 
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
