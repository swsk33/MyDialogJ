package com.gitee.swsk33.mydialog.exception;

/**
 * 提示框设定的字数超过限制抛出此异常
 * 
 * @author swsk33
 *
 */
public class ContentOutOfRangeException extends Exception {

	/**
	 * 生成版本序列号
	 */
	private static final long serialVersionUID = -6940808414649974202L;

	/**
	 * 提示框设定的字数超出限制抛出异常
	 * 
	 * @param msg 异常提示内容
	 */
	public ContentOutOfRangeException(String msg) {
		super(msg);
	}

}