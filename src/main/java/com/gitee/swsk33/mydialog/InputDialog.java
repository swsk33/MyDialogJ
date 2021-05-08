package com.gitee.swsk33.mydialog;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
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
 * 文本输入窗口
 * 
 * @author swsk33
 *
 */
public class InputDialog {

	private static int x;
	private static int y;
	private String inputText = "";
	private JDialog jd = new JDialog();
	private JPanel jp = new JPanel();
	private JTextField normalInput = new JTextField();
	private JPasswordField passwordInput = new JPasswordField();
	private JButton ok = new JButton("确定");
	private JButton cancel = new JButton("取消");

	@SuppressWarnings("deprecation")
	private void dialogSetup(int width, int height, String bgPath, String dialogTitle, String tipContent) throws ContentOutOfRangeException {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension sc = kit.getScreenSize();
		jd.setSize(width, height);
		jd.setLocation((sc.width - jd.getWidth()) / 2, (sc.height - jd.getHeight()) / 2);
		jd.setModal(true);
		jd.setAlwaysOnTop(true);
		jd.setUndecorated(true);
		URL bg = this.getClass().getResource(bgPath);
		JLabel bl = new JLabel(new ImageIcon(bg)); // 把上面的图片对象加到一个名为bl的标签里
		bl.setBounds(0, 0, jd.getWidth(), jd.getHeight()); // 设置标签大小
		JPanel imagePanel = (JPanel) jd.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明 ，使内容窗格透明后才能显示背景图片
		imagePanel.setOpaque(false); // 把背景图片添加到分层窗格的最底层作为背景
		jd.getLayeredPane().add(bl, new Integer(Integer.MIN_VALUE));
		jd.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // 获取点击鼠标时的坐标
				x = e.getPoint().x;
				y = e.getPoint().y;
			}

			public void mouseReleased(MouseEvent e) {
				jd.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		jd.addMouseMotionListener(new MouseMotionAdapter() {// 设置拖拽后，窗口的位置
			public void mouseDragged(MouseEvent e) {
				jd.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				jd.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
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
		ok.setFont(new Font("黑体", Font.BOLD, 14));
		ok.setBounds(97, 136, 76, 28);
		ok.setContentAreaFilled(false);
		cancel.setFont(new Font("黑体", Font.BOLD, 14));
		cancel.setBounds(261, 136, 76, 28);
		cancel.setContentAreaFilled(false);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inputText = "";
				jd.dispose();
			}
		});
		jp.setOpaque(false);
		jp.setLayout(null);
		jp.add(title);
		jp.add(content);
		jp.add(ok);
		jp.add(cancel);
		jp.add(normalInput);
		jp.add(passwordInput);
		jd.getContentPane().add(jp);
	}

	/**
	 * 创建一个普通文本输入框
	 * 
	 * @param dialogTitle 窗口标题
	 * @param tipContent  提示内容
	 * @return 字符串 按下确定返回输入的内容，按下取消返回空字符串("")
	 * @throws ContentOutOfRangeException 提示内容字数超过限制抛出异常
	 */
	public String createTextInputDialog(String dialogTitle, String tipContent) throws ContentOutOfRangeException {
		this.dialogSetup(435, 178, "/mydialog/bg/InputBg.png", dialogTitle, tipContent);
		normalInput.setBounds(43, 86, 356, 28);
		passwordInput.setBounds(0, 0, 0, 0);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!normalInput.getText().toString().equals("")) {
					inputText = normalInput.getText().toString();
					jd.dispose();
				}
			}
		});
		jd.setVisible(true);
		return inputText;
	}

	/**
	 * 创建一个密码输入文本框
	 * 
	 * @param dialogTitle 窗口标题
	 * @param tipContent  提示内容
	 * @return 字符串 按下确定返回输入的内容，按下取消返回空字符串("")
	 * @throws ContentOutOfRangeException 提示内容字数超过限制抛出异常
	 */
	public String createPasswordInputDialog(String dialogTitle, String tipContent) throws ContentOutOfRangeException {
		this.dialogSetup(435, 178, "/mydialog/bg/InputBg.png", dialogTitle, tipContent);
		normalInput.setBounds(0, 0, 0, 0);
		passwordInput.setBounds(43, 86, 356, 28);
		ok.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (!normalInput.getText().toString().equals("")) {
					inputText = passwordInput.getText().toString();
					jd.dispose();
				}
			}
		});
		jd.setVisible(true);
		return inputText;
	}

}