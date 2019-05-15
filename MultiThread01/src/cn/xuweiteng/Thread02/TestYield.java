package cn.xuweiteng.Thread02;

/**
 * ����yeild()
 * �÷���������ʽ������֪ͨCPU���߳�Ը�������ǰCPU��Դ���ø������̣߳������CPU��Դ�����Ż�����������
 * ��˸÷���һ�㲻ʹ��
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
