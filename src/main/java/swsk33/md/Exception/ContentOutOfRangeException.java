package swsk33.md.Exception;

/**
 * 提示框设定的字数超过限制抛出此异常
 * 
 * @author swsk33
 *
 */
public class ContentOutOfRangeException extends Exception {
	/**
	 * 提示框设定的字数超出限制抛出异常
	 * 
	 * @param msg 异常提示内容
	 */
	public ContentOutOfRangeException(String msg) {
		super(msg);
	}
}
