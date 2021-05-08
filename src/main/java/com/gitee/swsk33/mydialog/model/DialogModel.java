package com.gitee.swsk33.mydialog.model;

/**
 * 告示窗的属性模型，构建告示窗时使用。 模型中有五个参数在实例化后需要设置，分别是：<br>
 * <ul>
 * <li>title 窗口标题</li>
 * <li>content 窗口内容</li>
 * <li>dialogType 窗口类型（0,1,2）</li>
 * <li>isModal 窗口是否模态 isOnTop</li>
 * <li>窗口是否置顶</li>
 * </ul>
 * 这五个参数不一定会全部用到！
 * 
 * @author swsk33
 *
 */
public class DialogModel {

	private String title; // 窗口标题
	private String content; // 窗口内容
	private int dialogType = 0; // 窗口类型（0,1,2）
	private boolean isModal = false; // 窗口是否模态
	private boolean isOnTop = false; // 窗口是否置顶

	/**
	 * 设置窗口标题
	 * 
	 * @param title 窗口标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * 设置窗口内容
	 * 
	 * @param content 窗口内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	/**
	 * 设置窗口类型，其中DialogTypeValue.INFO是告示信息提示窗，DialogTypeValue.WARN是警告信息提示窗，DialogTypeValue.ERROR是错误信息提示窗。
	 * 
	 * @param dialogType DialogTypeValue.INFO(0),DialogTypeValue.WARN(1),DialogTypeValue.ERROR(2)
	 */
	public void setDialogType(int dialogType) {
		this.dialogType = dialogType;
	}

	public int getDialogType() {
		return dialogType;
	}

	/**
	 * 设置是否为模态窗
	 * 
	 * @param isModal 布尔值
	 */
	public void setIsModal(boolean isModal) {
		this.isModal = isModal;
	}

	public boolean getIsModal() {
		return isModal;
	}

	/**
	 * 设置是否为置顶
	 * 
	 * @param isOnTop 布尔值
	 */
	public void setIsOnTop(boolean isOnTop) {
		this.isOnTop = isOnTop;
	}

	public boolean getIsOnTop() {
		return isOnTop;
	}

}