package cn.xuweiteng.Thread;

/**
 * ʹ��lambda���ʽ�������߳�
 * @author MrXu
 *
 */
public class MyThread01 {

	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			new Thread(MyThread01::doingHomework).start();
			talking();
		}
	}
	
	private static void doingHomework() {
		System.out.println("��������ҵ");
	}
	
	private static void talking() {
		System.out.println("��������");
	}
}
