package swsk33.md;

public class InfoDialogModel {

	private String title; // 窗口标题
	private String buttonContent; // 按钮文字
	private String content; // 窗口内容
	private int dialogType; // 窗口类型（0,1,2,3）
	private boolean isModal = false; // 窗口是否模态
	private boolean isOnTop = false; // 窗口是否置顶

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setButtonContent(String buttonContent) {
		this.buttonContent = buttonContent;
	}

	public String getButtonContent() {
		return buttonContent;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setDialogType(int dialogType) {
		this.dialogType = dialogType;
	}

	public int getDialogType() {
		return dialogType;
	}

	public void setIsModal(boolean isModal) {
		this.isModal = isModal;
	}

	public boolean getIsModal() {
		return isModal;
	}

	public void setIsOnTop(boolean isOnTop) {
		this.isOnTop = isOnTop;
	}

	public boolean getIsOnTop() {
		return isOnTop;
	}
}
