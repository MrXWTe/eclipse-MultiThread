package cn.xuweiteng.juc;

/**
 * 1��volatile �ؼ��֣�������̷߳��ʹ�������ʱ�����Ա����ڴ��ǿɼ��ģ������synchronized��һ�ֽ�Ϊ��������ͬ������
 * 	ע�⣺1����volatile		���߱��������ԡ�
 * 		2����volatile		���ܱ�֤�����ġ�ԭ���ԡ�
 * @author MrXu
 *
 */
public class TestVolatile {
	
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		new Thread(t1).start();
		while(true) {
			if(t1.isFlag()) {
				System.out.println("--------------");
				break;
			}
		}
	}

}

class MyThread implements Runnable{
	
	private volatile boolean flag = false;
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		flag = true;
		System.out.println("flag = " + flag);
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
