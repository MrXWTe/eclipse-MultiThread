package cn.xuweiteng.Thread01;

/**
 * Thread ģ��Ӫҵ�����кŻ�����
 * @author MrXu
 *
 */
public class MyThread02  extends Thread{
	//��̨����
	private final String name;
	
	//ÿ����̨������50��ҵ��
	private static final int MAX = 50;
	
	/**
	 * �������index���޸ģ���������̲߳�����ÿ���̶߳���1~50
	 * 
	 */
	//private int index = 1;
	/**
	 * ��index��Ϊstatic�������ݹ����������޸ķ�ʽ���Ƽ�
	 * static�����������ںܳ�������кܶ�������Ҫ�������ʺ�
	 */
	private static int index = 1;
	
	public MyThread02(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while(index < MAX) {
			System.out.println("��̨��" + name + "��ǰ�ĺ����ǣ�" + (index++));
		}
	}
	
	public static void main(String[] args) {
		MyThread02 ticketWindow1 = new MyThread02("һ�Ź�̨");
		ticketWindow1.start();
		MyThread02 ticketWindow2 = new MyThread02("���Ź�̨");
		ticketWindow2.start();
		MyThread02 ticketWindow3 = new MyThread02("���Ź�̨");
		ticketWindow3.start();
		MyThread02 ticketWindow4 = new MyThread02("�ĺŹ�̨");
		ticketWindow4.start();
	}
	
}
