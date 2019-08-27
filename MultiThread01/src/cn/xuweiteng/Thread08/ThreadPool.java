package cn.xuweiteng.Thread08;

/**
 * �̳߳ؽӿ�
 * @author MrXu
 *
 */
public interface ThreadPool {

	// �ύ�����̳߳�
	void execute(Runnable runnable);
	
	// �ر��̳߳�
	void shutdown();
	
	// ��ȡ�̳߳صĳ�ʼ����С
	int getInitSize();
	
	// ��ȡ�̳߳��������
	int getMaxSize();
	
	// ��ȡ�̳߳صĺ����߳�����
	int getCoreSize();
	
	// ��ȡ�̳߳������ڻ�����еĴ�С
	int getQueueSize();
	
	// ��ȡ�̳߳��л�Ծ�̵߳�����
	int getActiveSize();
	
	// �鿴�̳߳��Ƿ񱻹ر�
	boolean isShutDown();
}
