package cn.xuweiteng.Thread08;

/**
 * �����̳߳��ڲ���������õ�RunnableQueue�����ϴ�queue��ȡ��ĳ��runnable��������runnable��run����
 * @author MrXu
 *
 */
public class InternalTask implements Runnable{
	
	private final RunnableQueue runnableQueue;
	private volatile boolean running = true;
	
	public InternalTask(RunnableQueue runnableQueue) {
		this.runnableQueue = runnableQueue;
	}
	
	@Override
	public void run() {
		// �����ǰ�߳�Ϊrunning����û�б��жϣ��򲻶ϴ�runnableQueue��ȡ��runnable��Ȼ��ִ��run������
		while(running && !Thread.currentThread().isInterrupted()) {
			try {
				Runnable task = runnableQueue.take();
				task.run();
			}catch (Exception e) {
				running = false;
				break;
			}
		}
	}
	
	// ֹͣ��ǰ������Ҫ�����̳߳ص�shutdown������ʹ��
	public void stop() {
		this.running = false;
	}
}
