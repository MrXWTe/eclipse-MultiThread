package cn.xuweiteng.Thread08;
/**
 * ������У����ڴ���ύ���̳߳ص�����
 * ����������BlockedQueueʵ��
 * @author MrXu
 *
 */
public interface RunnableQueue {

	// ������������ʱ����Ҫoffer������
	void offer(Runnable runnable);
	
	// ��������������take��ȡ����
	Runnable take();
	
	// ��ȡ���������
	int size();
}
