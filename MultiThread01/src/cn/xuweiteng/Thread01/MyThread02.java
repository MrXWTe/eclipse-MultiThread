package cn.xuweiteng.Thread01;

/**
 * Thread 模拟营业大厅叫号机程序
 * @author MrXu
 *
 */
public class MyThread02  extends Thread{
	//柜台名称
	private final String name;
	
	//每个柜台最多承受50笔业务
	private static final int MAX = 50;
	
	/**
	 * 如果不对index做修改，则该数据线程不共享，每个线程都是1~50
	 * 
	 */
	//private int index = 1;
	/**
	 * 将index该为static，则数据共享，但此种修改方式不推荐
	 * static变量生命周期很长，如果有很多数据需要共享则不适合
	 */
	private static int index = 1;
	
	public MyThread02(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while(index < MAX) {
			System.out.println("柜台：" + name + "当前的号码是：" + (index++));
		}
	}
	
	public static void main(String[] args) {
		MyThread02 ticketWindow1 = new MyThread02("一号柜台");
		ticketWindow1.start();
		MyThread02 ticketWindow2 = new MyThread02("二号柜台");
		ticketWindow2.start();
		MyThread02 ticketWindow3 = new MyThread02("三号柜台");
		ticketWindow3.start();
		MyThread02 ticketWindow4 = new MyThread02("四号柜台");
		ticketWindow4.start();
	}
	
}
