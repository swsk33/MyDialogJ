package com.gitee.swsk33.mydialog;

/**
 * 告示窗的属性模型，构建告示窗时使用
 * 
 * @author swsk33
 */
class DialogModel {

	/**
	 * 窗口标题
	 */
	private String title;

	/**
	 * 窗口内容
	 */
	private String content;

	/**
	 * 窗口类型（0,1,2）
	 */
	private int dialogType = 0;

	/**
	 * 是否模态
	 */
	private boolean modal = false;

	/**
	 * 是否置顶
	 */
	private boolean onTop = false;

	public DialogModel() {

	}

	public DialogModel(String title, String content, int dialogType, boolean modal, boolean onTop) {
		this.title = title;
		this.content = content;
		this.dialogType = dialogType;
		this.modal = modal;
		this.onTop = onTop;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDialogType() {
		return dialogType;
	}

	public void setDialogType(int dialogType) {
		this.dialogType = dialogType;
	}

	public boolean isModal() {
		return modal;
	}

	public void setModal(boolean modal) {
		this.modal = modal;
	}

	public boolean isOnTop() {
		return onTop;
	}

	public void setOnTop(boolean onTop) {
		this.onTop = onTop;
	}

}