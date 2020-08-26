package swsk33.md.event;

/**
 * 用于事件告示框中实现自定义按钮事件的接口
 * 
 * @author swsk33
 *
 */
public abstract class EventEditor {
	/**
	 * 通过实现此方法来实现确定按钮自定义事件
	 */
	public void customOkEvent() {}
	
	/**
	 * 通过实现此方法来实现取消按钮自定义事件
	 */
	public void customCancelEvent() {}
}
