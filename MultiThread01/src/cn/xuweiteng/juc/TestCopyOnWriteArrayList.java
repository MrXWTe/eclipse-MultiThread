package cn.xuweiteng.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList/CopyOnWriteSet:"写入并复制"
 * 注意：添加操作多时，效率低；每次添加都会在底层复制一份列表，效率非常低。在迭代遍历效率非常高
 * @author MrXu
 *
 */
public class TestCopyOnWriteArrayList {
	public static void main(String[] args) {
		
		HelloThread ht = new HelloThread();
		for(int i = 0; i<10; i++) {
			new Thread(ht).start();
		}
	}
}

class HelloThread implements Runnable{
	//private static List<String> list = Collections.synchronizedList(new ArrayList<>());
	
	private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	
	static {
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
	}
	
	@Override
	public void run() {
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
			list.add("AA");
		}
	}
	
}
