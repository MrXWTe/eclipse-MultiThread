package cn.xuweiteng.Thread08;
/**
 * ����֪ͨ�����ύ�ߣ�����������޷��ٽ��ܵ��µ�����
 * @author MrXu
 *
 */
public class RunnableDenyException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RunnableDenyException(String message) {
		super(message);
	}

}
