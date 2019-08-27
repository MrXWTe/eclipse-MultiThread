package cn.xuweiteng.Thread08;
/**
 * 用于通知任务提交者，任务队列以无法再接受到新的任务
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
