package cn.xuweiteng.Thread;

/**
 * 使用lambda表达式创建多线程
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
		System.out.println("我在做作业");
	}
	
	private static void talking() {
		System.out.println("我在聊天");
	}
}
