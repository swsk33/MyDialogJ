package com.gitee.swsk33.mydialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import com.gitee.swsk33.mydialog.event.EventEditor;
import com.gitee.swsk33.mydialog.exception.ContentOutOfRangeException;

/**
 * 事件告示窗，分为：<br>
 * <ol>
 * <li>布尔型窗口：有“确定”和“取消”两个按钮，点确定返回true点取消返回false
 * <li>自定义事件窗口：有“确定”和“取消”两个按钮，通过实现接口的方法来给确定按钮和取消添加代码段
 * </ol>
 * 事件窗口不需要设置模态，因为它固定了就是模态
 * 
 * @author swsk33
 *
 */
public class EventDialog {

	private static boolean isOk = false;

	/**
	 * 布尔型事件窗口，是一个模态且置顶的窗口，点确定返回true，取消或者直接关闭窗口返回false
	 * 
	 * @param title      窗口标题
	 * @param content    窗口内容
	 * @param dialogType 窗口类型（类型使用com.gitee.swsk33.mydialog.util.DialogTypeValue类中的静态常量值，DialogTypeValue.INFO为信息告示窗，DialogTypeValue.WARN为警告信息窗，DialogTypeValue.ERROR为错误信息窗）
	 * @param isMute     是否关闭提示音
	 * @return 点击确定返回true，取消返回false
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public static boolean createBooleanEventDialog(String title, String content, int dialogType, boolean isMute) throws ContentOutOfRangeException {
		isOk = false;
		DialogModel dialogModel = new DialogModel(title, content, dialogType, true, true);
		JButton ok = new JButton();
		JButton no = new JButton();
		JDialog dialog = DialogFactory.makeEventDialog("/mydialog/bg/BooleanDialog.png", dialogModel, ok, no);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isOk = true;
				dialog.dispose();
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isOk = false;
				dialog.dispose();
			}
		});
		dialog.setVisible(true);
		if (!isMute) {
			// 播放提示音
			String audioPath = "/mydialog/audio/error.au";
			if (dialogType == 0) {
				audioPath = "/mydialog/audio/info.au";
			} else if (dialogType == 1) {
				audioPath = "/mydialog/audio/warn.au";
			} else if (dialogType == 2) {
				audioPath = "/mydialog/audio/error.au";
			}
			DialogUtils.playAudioAsync(InfoDialog.class.getResource(audioPath));
		}
		return isOk;
	}

	/**
	 * 自定义事件窗口，是一个模态且置顶的窗口，通过重写抽象类EventEditor中的customOkEvent()和customCancelEvent()方法，传入到此方法中来分别实现确定按钮和取消按钮的自定义事件
	 * 
	 * @param title          窗口标题
	 * @param content        窗口内容
	 * @param dialogType     窗口类型（类型使用com.gitee.swsk33.mydialog.util.DialogTypeValue类中的静态常量值，DialogTypeValue.INFO为信息告示窗，DialogTypeValue.WARN为警告信息窗，DialogTypeValue.ERROR为错误信息窗）
	 * @param isMute         是否关闭提示音
	 * @param eventInterface 接口，需要在这里实现接口的两个方法分别实现两个按钮的事件自定义
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public static void createCustomEventDialog(String title, String content, int dialogType, boolean isMute, EventEditor eventInterface) throws ContentOutOfRangeException {
		DialogModel dialogModel = new DialogModel(title, content, dialogType, true, true);
		JButton ok = new JButton();
		JButton no = new JButton();
		JDialog dialog = DialogFactory.makeEventDialog("/mydialog/bg/EventBg.png", dialogModel, ok, no);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventInterface.customOkEvent();
				dialog.dispose();
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventInterface.customCancelEvent();
				dialog.dispose();
			}
		});
		if (!isMute) {
			// 播放提示音
			String audioPath = "/mydialog/audio/error.au";
			if (dialogType == 0) {
				audioPath = "/mydialog/audio/info.au";
			} else if (dialogType == 1) {
				audioPath = "/mydialog/audio/warn.au";
			} else if (dialogType == 2) {
				audioPath = "/mydialog/audio/error.au";
			}
			DialogUtils.playAudioAsync(InfoDialog.class.getResource(audioPath));
		}
		dialog.setVisible(true);
	}

}