package cn.xuweiteng.Thread06;

public class ThreadGroupCreator {
	public static void main(String[] args) {
		// 当前线程的group
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		// 自定义的group，父亲为当前线程所在的group
		ThreadGroup g1 = new ThreadGroup("Group1");
		// true
		System.out.println(g1.getParent() == threadGroup);
		
		ThreadGroup g2 = new ThreadGroup(g1, "Group2");
		System.out.println(g2.getParent() == g1);
	}
}
