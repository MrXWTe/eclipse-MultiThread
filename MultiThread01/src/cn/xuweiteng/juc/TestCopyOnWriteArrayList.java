package cn.xuweiteng.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList/CopyOnWriteSet:"д�벢����"
 * ע�⣺��Ӳ�����ʱ��Ч�ʵͣ�ÿ����Ӷ����ڵײ㸴��һ���б�Ч�ʷǳ��͡��ڵ�������Ч�ʷǳ���
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
