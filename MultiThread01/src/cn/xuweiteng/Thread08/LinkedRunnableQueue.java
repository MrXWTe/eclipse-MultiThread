package cn.xuweiteng.Thread08;

import java.util.LinkedList;

/**
 * ������У��̳߳��еĶ������Ტ����ö�����ȡ���������ִ��
 * @author MrXu
 *
 */
public class LinkedRunnableQueue implements RunnableQueue{
	
	// ��������������������ڹ���ʱ����
	private final int limit;
	
	// ������������������ִ�иþܾ�����
	private final DenyPolicy denyPolicy;
	
	// �������Ķ���
	private final LinkedList<Runnable> runnableList = new LinkedList<>();
	
	// �̳߳�
	private final ThreadPool threadPool;
	
	public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}

	
	/**
	 *  ������������������
	 *  ��Ϊ���ж���߳������������Ϣ�������Ҫ����ͬ��
	 */
	@Override
	public void offer(Runnable runnable) {
		synchronized (runnableList) {
			if(runnableList.size() >= limit) {
				// ��ʾ�����е��������ˣ���Ҫִ�оܾ�����
				denyPolicy.reject(runnable, threadPool);
			}else {
				// ���������������β��
				runnableList.addLast(runnable);
				runnableList.notifyAll();
			}
		}
		
	}
	
	
	// �����������ȡ������
	@Override
	public Runnable take(){
		synchronized (runnableList) {
			while(runnableList.isEmpty()) {
				try {
					// ������������û�п�ִ�е�������ǰ�߳̽�����𣬽���runnableList������monitor waitset
					// �еȴ�����
					runnableList.wait();
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// ���������ͷ����
			return runnableList.removeFirst();
		}
	}
	
	
	// ���ص�ǰ��������е�������
	@Override
	public int size() {
		synchronized (runnableList) {
			return runnableList.size();
		}
	}
	
	
}
