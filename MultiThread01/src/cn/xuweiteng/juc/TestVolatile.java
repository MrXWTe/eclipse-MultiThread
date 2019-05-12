package cn.xuweiteng.juc;

/**
 * 1、volatile 关键字：当多个线程访问共享数据时，可以保存内存是可见的；相较于synchronized是一种较为轻量级的同步策略
 * 	注意：1）、volatile		不具备“互斥性”
 * 		2）、volatile		不能保证变量的“原子性”
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
