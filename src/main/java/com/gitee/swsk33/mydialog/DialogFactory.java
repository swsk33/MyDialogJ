package com.gitee.swsk33.mydialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.gitee.swsk33.mydialog.exception.ContentOutOfRangeException;

/**
 * 告示窗实例构造器
 * 
 * @author swsk33
 *
 */
class DialogFactory {

	/**
	 * 屏幕维度
	 */
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * 鼠标X坐标
	 */
	private static int mouseAtX;

	/**
	 * 鼠标Y坐标
	 */
	private static int mouseAtY;

	/**
	 * 生成一个信息告示窗口对象
	 * 
	 * @param width          窗口宽
	 * @param height         窗口高
	 * @param backgroundPath 背景图片
	 * @param dialogModel    窗口设置模型
	 * @return JDialog 窗口实例
	 */
	public static JDialog makeInfoDialog(int width, int height, String backgroundPath, DialogModel dialogModel) {
		JDialog dialog = new JDialog();
		dialog.setSize(width, height);
		dialog.setLocation((SCREEN_SIZE.width - dialog.getWidth()) / 2, (SCREEN_SIZE.height - dialog.getHeight()) / 2);
		dialog.setModal(dialogModel.isModal());
		dialog.setAlwaysOnTop(dialogModel.isOnTop());
		dialog.setUndecorated(true);
		URL backgroundImageUrl = DialogFactory.class.getResource(backgroundPath);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImageUrl));
		backgroundLabel.setBounds(0, 0, dialog.getWidth(), dialog.getHeight());
		((JPanel) dialog.getContentPane()).setOpaque(false);
		dialog.getLayeredPane().add(backgroundLabel, Integer.MIN_VALUE);
		dialog.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseAtX = e.getPoint().x;
				mouseAtY = e.getPoint().y;
			}

			public void mouseReleased(MouseEvent e) {
				dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		dialog.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				dialog.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				dialog.setLocation(e.getXOnScreen() - mouseAtX, e.getYOnScreen() - mouseAtY);
			}
		});
		return dialog;
	}

	/**
	 * 生成一个事件窗口实例
	 * 
	 * @param backgroundPath   窗口背景
	 * @param dialogModel      窗口模型
	 * @param okButtonInstance 是按钮实例
	 * @param noButtonInstance 否按钮实例
	 * @return 窗口实例
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public static JDialog makeEventDialog(String backgroundPath, DialogModel dialogModel, JButton okButtonInstance, JButton noButtonInstance) throws ContentOutOfRangeException {
		JDialog dialog = new JDialog();
		dialog.setSize(435, 178);
		dialog.setLocation((SCREEN_SIZE.width - dialog.getWidth()) / 2, (SCREEN_SIZE.height - dialog.getHeight()) / 2);
		dialog.setAlwaysOnTop(dialogModel.isOnTop());
		dialog.setUndecorated(true);
		dialog.setModal(true);
		URL backgroundImageUrl = DialogFactory.class.getResource(backgroundPath);
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImageUrl));
		backgroundLabel.setBounds(0, 0, dialog.getWidth(), dialog.getHeight());
		((JPanel) dialog.getContentPane()).setOpaque(false);
		dialog.getLayeredPane().add(backgroundLabel, Integer.MIN_VALUE);
		dialog.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseAtX = e.getPoint().x;
				mouseAtY = e.getPoint().y;
			}

			public void mouseReleased(MouseEvent e) {
				dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		dialog.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				dialog.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				dialog.setLocation(e.getXOnScreen() - mouseAtX, e.getYOnScreen() - mouseAtY);
			}
		});
		JLabel title = new JLabel();
		title.setText(dialogModel.getTitle());
		title.setFont(new Font("等线", Font.BOLD, 16));
		title.setBounds(6, 3, 238, 24);
		// 字体大小根据内容长度自适应
		int size = 0;
		if (dialogModel.getContent().length() <= 64) {
			size = 18;
		} else if (dialogModel.getContent().length() > 64 && dialogModel.getContent().length() <= 72) {
			size = 16;
		} else if (dialogModel.getContent().length() > 72 && dialogModel.getContent().length() <= 84) {
			size = 14;
		} else if (dialogModel.getContent().length() > 84 && dialogModel.getContent().length() <= 110) {
			size = 13;
		} else {
			throw new ContentOutOfRangeException("设定的内容字数超出限制（110长度）！超出：" + (dialogModel.getContent().length() - 110));
		}
		JLabel content = new JLabel();
		content.setText("<html>" + dialogModel.getContent() + "</html>");
		content.setFont(new Font("等线", Font.BOLD, size));
		content.setBounds(101, 40, 297, 78);
		URL buttonNormalImage = DialogFactory.class.getResource("/mydialog/button/close-normal.png");
		URL buttonMouseOnImage = DialogFactory.class.getResource("/mydialog/button/close-mouseon.png");
		String iconPath = "/mydialog/bg/ico/error-ico.png";
		if (dialogModel.getDialogType() == 0) {
			iconPath = "/mydialog/bg/ico/info-ico.png";
		} else if (dialogModel.getDialogType() == 1) {
			iconPath = "/mydialog/bg/ico/warn-ico.png";
		} else if (dialogModel.getDialogType() == 2) {
			iconPath = "/mydialog/bg/ico/error-ico.png";
		}
		URL iconUrl = InfoDialog.class.getResource(iconPath);
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon(iconUrl));
		iconLabel.setBounds(31, 62, 45, 45);
		JButton close = new JButton();
		close.setIcon(new ImageIcon(buttonNormalImage));
		close.setBounds(407, 0, 28, 28);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				close.setIcon(new ImageIcon(buttonMouseOnImage));
			}

			public void mouseExited(MouseEvent e) {
				close.setIcon(new ImageIcon(buttonNormalImage));
			}
		});
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		okButtonInstance.setText("确定");
		okButtonInstance.setFont(new Font("黑体", Font.BOLD, 13));
		okButtonInstance.setBounds(101, 130, 82, 32);
		okButtonInstance.setContentAreaFilled(false);
		noButtonInstance.setText("取消");
		noButtonInstance.setBounds(246, 130, 82, 32);
		noButtonInstance.setContentAreaFilled(false);
		noButtonInstance.setFont(new Font("黑体", Font.BOLD, 13));
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.add(title);
		panel.add(iconLabel);
		panel.add(content);
		panel.add(close);
		panel.add(okButtonInstance);
		panel.add(noButtonInstance);
		dialog.getContentPane().add(panel);
		return dialog;
	}

	/**
	 * 生成一个输入窗口实例
	 * 
	 * @param dialogTitle      窗口标题
	 * @param tipContent       窗口提示内容
	 * @param okButtonInstance 确定按钮实例
	 * @param normalInput      普通文本输入框实例
	 * @param passwordInput    密码输入框实例
	 * @return 窗口实例
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public static JDialog dialogSetup(String dialogTitle, String tipContent, JButton okButtonInstance, JTextField normalInput, JPasswordField passwordInput) throws ContentOutOfRangeException {
		JDialog dialog = new JDialog();
		dialog.setSize(435, 178);
		dialog.setLocation((SCREEN_SIZE.width - dialog.getWidth()) / 2, (SCREEN_SIZE.height - dialog.getHeight()) / 2);
		dialog.setModal(true);
		dialog.setAlwaysOnTop(true);
		dialog.setUndecorated(true);
		URL backgroundImage = DialogFactory.class.getResource("/mydialog/bg/InputBg.png");
		JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0, 0, dialog.getWidth(), dialog.getHeight());
		((JPanel) dialog.getContentPane()).setOpaque(false);
		dialog.getLayeredPane().add(backgroundLabel, Integer.MIN_VALUE);
		dialog.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseAtX = e.getPoint().x;
				mouseAtY = e.getPoint().y;
			}

			public void mouseReleased(MouseEvent e) {
				dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		dialog.addMouseMotionListener(new MouseMotionAdapter() {// 设置拖拽后，窗口的位置
			public void mouseDragged(MouseEvent e) {
				dialog.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				dialog.setLocation(e.getXOnScreen() - mouseAtX, e.getYOnScreen() - mouseAtY);
			}
		});
		JLabel title = new JLabel();
		title.setForeground(Color.WHITE);
		title.setText(dialogTitle);
		title.setFont(new Font("等线", Font.BOLD, 16));
		title.setBounds(6, 3, 238, 24);
		if (tipContent.length() > 24) {
			throw new ContentOutOfRangeException("提示内容字数超出限制（24字）");
		}
		JLabel content = new JLabel(tipContent);
		content.setFont(new Font("等线", Font.BOLD, 17));
		content.setBounds(16, 39, 408, 31);
		okButtonInstance.setText("确定");
		okButtonInstance.setFont(new Font("黑体", Font.BOLD, 14));
		okButtonInstance.setBounds(97, 136, 76, 28);
		okButtonInstance.setContentAreaFilled(false);
		JButton cancel = new JButton("取消");
		cancel.setFont(new Font("黑体", Font.BOLD, 14));
		cancel.setBounds(261, 136, 76, 28);
		cancel.setContentAreaFilled(false);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.add(title);
		panel.add(content);
		panel.add(okButtonInstance);
		panel.add(cancel);
		panel.add(normalInput);
		panel.add(passwordInput);
		dialog.getContentPane().add(panel);
		return dialog;
	}

}