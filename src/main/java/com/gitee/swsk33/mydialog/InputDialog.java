package com.gitee.swsk33.mydialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.gitee.swsk33.mydialog.exception.ContentOutOfRangeException;

/**
 * 文本输入窗口
 * 
 * @author swsk33
 *
 */
public class InputDialog {

	private static String inputText;

	/**
	 * 创建一个普通文本输入框
	 * 
	 * @param dialogTitle 窗口标题
	 * @param tipContent  提示内容
	 * @return 字符串 按下确定返回输入的内容，按下取消返回null
	 * @throws ContentOutOfRangeException 提示内容字数超过限制抛出异常
	 */
	public static String createTextInputDialog(String dialogTitle, String tipContent) throws ContentOutOfRangeException {
		inputText = null;
		JButton ok = new JButton();
		JTextField normalInput = new JTextField();
		JPasswordField passwordInput = new JPasswordField();
		normalInput.setBounds(0, 0, 0, 0);
		passwordInput.setBounds(43, 86, 356, 28);
		JDialog dialog = DialogFactory.dialogSetup(dialogTitle, tipContent, ok, normalInput, passwordInput);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!normalInput.getText().toString().equals("")) {
					inputText = normalInput.getText().toString();
					dialog.dispose();
				}
			}
		});
		dialog.setVisible(true);
		return inputText;
	}

	/**
	 * 创建一个密码输入文本框
	 * 
	 * @param dialogTitle 窗口标题
	 * @param tipContent  提示内容
	 * @return 字符串 按下确定返回输入的内容，按下取消返回null
	 * @throws ContentOutOfRangeException 提示内容字数超过限制抛出异常
	 */
	public static String createPasswordInputDialog(String dialogTitle, String tipContent) throws ContentOutOfRangeException {
		inputText = null;
		JButton ok = new JButton();
		JTextField normalInput = new JTextField();
		JPasswordField passwordInput = new JPasswordField();
		normalInput.setBounds(43, 86, 356, 28);
		passwordInput.setBounds(0, 0, 0, 0);
		JDialog dialog = DialogFactory.dialogSetup(dialogTitle, tipContent, ok, normalInput, passwordInput);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!normalInput.getText().toString().equals("")) {
					inputText = passwordInput.getPassword().toString();
					dialog.dispose();
				}
			}
		});
		dialog.setVisible(true);
		return inputText;
	}

}