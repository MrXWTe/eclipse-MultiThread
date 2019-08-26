package cn.xuweiteng.Thread06;

public class ThreadGroupCreator {
	public static void main(String[] args) {
		// ��ǰ�̵߳�group
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		// �Զ����group������Ϊ��ǰ�߳����ڵ�group
		ThreadGroup g1 = new ThreadGroup("Group1");
		// true
		System.out.println(g1.getParent() == threadGroup);
		
		ThreadGroup g2 = new ThreadGroup(g1, "Group2");
		System.out.println(g2.getParent() == g1);
	}
}
