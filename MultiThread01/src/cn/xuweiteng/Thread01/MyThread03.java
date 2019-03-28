package cn.xuweiteng.Thread01;

/**
 * 实现Runnable接口重新模拟银行柜台功能
 * @author MrXu
 *
 */
public class MyThread03 implements Runnable{

	//每个柜台最多承受50笔业务
	private static final int MAX = 50;
	
	private int index = 1;
		
	@Override
	public void run() {
		while(index < MAX) {
			System.out.println("柜台：" + Thread.currentThread().getName() + "当前的号码是：" + (index++));
		}
	}

	public static void main(String[] args) {
		final MyThread03 thread = new MyThread03(); 
		Thread t1 = new Thread(thread, "一号柜台");
		Thread t2 = new Thread(thread, "二号柜台");
		Thread t3 = new Thread(thread, "三号柜台");
		Thread t4 = new Thread(thread, "四号柜台");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
