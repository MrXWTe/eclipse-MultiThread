package cn.xuweiteng.Thread08;

/**
 * ��queue�е�runnable�ﵽ����ʱ�������������ֲ���֪ͨ�ύ��
 * @author MrXu
 *
 */
@FunctionalInterface
public interface DenyPolicy {
	
	void reject(Runnable runnable, ThreadPool threadPool);
	
	// �þܾ����Ի�ֱ�ӽ�������
	class DiscardDenyPolicy implements DenyPolicy{
		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			
		}
	}
	
	// �þܾ����Ի��������ύ���׳��쳣
	class AbortDenyPolicy implements DenyPolicy{
		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			throw new RunnableDenyException("The runnable" + runnable + "will be abort.");
		}
	}
	
	// �þܾ����Ի�ʹ�������ύ�����ڵ��߳���ִ������
	class RunnerDenyPolicy implements DenyPolicy{
		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			if(!threadPool.isShutDown()) {
				runnable.run();
			}
		}
	}
}
