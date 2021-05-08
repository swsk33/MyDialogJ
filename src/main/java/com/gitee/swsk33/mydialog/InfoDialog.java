package com.gitee.swsk33.mydialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import com.gitee.swsk33.mydialog.exception.ContentOutOfRangeException;

/**
 * 信息告示窗，只用于显示提示信息
 * 
 * @author swsk33
 *
 */
public class InfoDialog {

	/**
	 * 创建一个信息告示窗（非模态、非置顶、不静音）
	 * 
	 * @param title      窗口标题
	 * @param content    窗口内容
	 * @param dialogType 窗口类型（类型使用com.gitee.swsk33.mydialog.util.DialogTypeValue类中的静态常量值，DialogTypeValue.INFO为信息告示窗，DialogTypeValue.WARN为警告信息窗，DialogTypeValue.ERROR为错误信息窗）
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public static void createShortNoticeDialog(String title, String content, int dialogType) throws ContentOutOfRangeException {
		DialogModel dialogModel = new DialogModel();
		dialogModel.setTitle(title);
		dialogModel.setContent(content);
		dialogModel.setDialogType(dialogType);
		JDialog dialog = DialogFactory.makeInfoDialog(435, 178, "/mydialog/bg/InfoBg.png", dialogModel);
		JLabel titleLabel = new JLabel();
		titleLabel.setText(dialogModel.getTitle());
		titleLabel.setFont(new Font("等线", Font.BOLD, 16));
		titleLabel.setBounds(6, 3, 238, 24);
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
		JLabel contentLabel = new JLabel("<html>" + dialogModel.getContent() + "</html>");
		contentLabel.setFont(new Font("等线", Font.BOLD, size));
		contentLabel.setBounds(101, 40, 297, 78);
		URL buttonNormalImage = InfoDialog.class.getResource("/mydialog/button/close-normal.png");
		URL buttonMouseOnImage = InfoDialog.class.getResource("/mydialog/button/close-mouseon.png");
		String iconPath = "/mydialog/bg/ico/error-ico.png";
		if (dialogModel.getDialogType() == 0) {
			iconPath = "/mydialog/bg/ico/info-ico.png";
		} else if (dialogModel.getDialogType() == 1) {
			iconPath = "/mydialog/bg/ico/warn-ico.png";
		} else if (dialogModel.getDialogType() == 2) {
			iconPath = "/mydialog/bg/ico/error-ico.png";
		}
		URL iconUrl = InfoDialog.class.getResource(iconPath);
		JLabel iconLabel = new JLabel(new ImageIcon(iconUrl));
		iconLabel.setBounds(31, 62, 45, 45);
		JButton close = new JButton(new ImageIcon(buttonNormalImage));
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
		JButton ok = new JButton("知道了");
		ok.setFont(new Font("黑体", Font.BOLD, 13));
		ok.setBounds(168, 130, 82, 32);
		ok.setContentAreaFilled(false);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.add(titleLabel);
		panel.add(iconLabel);
		panel.add(contentLabel);
		panel.add(close);
		panel.add(ok);
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
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

	/**
	 * 创建一个信息告示窗
	 * 
	 * @param title      窗口标题
	 * @param content    窗口内容
	 * @param dialogType 窗口类型（类型使用com.gitee.swsk33.mydialog.util.DialogTypeValue类中的静态常量值，DialogTypeValue.INFO为信息告示窗，DialogTypeValue.WARN为警告信息窗，DialogTypeValue.ERROR为错误信息窗）
	 * @param isModal    窗口是否模态
	 * @param isOnTop    窗口是否总在最前
	 * @param isMute     是否关闭提示音
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public static void createShortNoticeDialog(String title, String content, int dialogType, boolean isModal, boolean isOnTop, boolean isMute) throws ContentOutOfRangeException {
		DialogModel dialogModel = new DialogModel(title, content, dialogType, isModal, isOnTop);
		JDialog dialog = DialogFactory.makeInfoDialog(435, 178, "/mydialog/bg/InfoBg.png", dialogModel);
		JLabel titleLabel = new JLabel();
		titleLabel.setText(dialogModel.getTitle());
		titleLabel.setFont(new Font("等线", Font.BOLD, 16));
		titleLabel.setBounds(6, 3, 238, 24);
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
		JLabel contentLabel = new JLabel("<html>" + dialogModel.getContent() + "</html>");
		contentLabel.setFont(new Font("等线", Font.BOLD, size));
		contentLabel.setBounds(101, 40, 297, 78);
		URL buttonNormalImage = InfoDialog.class.getResource("/mydialog/button/close-normal.png");
		URL buttonMouseOnImage = InfoDialog.class.getResource("/mydialog/button/close-mouseon.png");
		String iconPath = "/mydialog/bg/ico/error-ico.png";
		if (dialogModel.getDialogType() == 0) {
			iconPath = "/mydialog/bg/ico/info-ico.png";
		} else if (dialogModel.getDialogType() == 1) {
			iconPath = "/mydialog/bg/ico/warn-ico.png";
		} else if (dialogModel.getDialogType() == 2) {
			iconPath = "/mydialog/bg/ico/error-ico.png";
		}
		URL iconUrl = InfoDialog.class.getResource(iconPath);
		JLabel iconLabel = new JLabel(new ImageIcon(iconUrl));
		iconLabel.setBounds(31, 62, 45, 45);
		JButton close = new JButton(new ImageIcon(buttonNormalImage));
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
		JButton ok = new JButton("知道了");
		ok.setFont(new Font("黑体", Font.BOLD, 13));
		ok.setBounds(168, 130, 82, 32);
		ok.setContentAreaFilled(false);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.add(titleLabel);
		panel.add(iconLabel);
		panel.add(contentLabel);
		panel.add(close);
		panel.add(ok);
		dialog.getContentPane().add(panel);
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
	}

	/**
	 * 创建一个长消息信息告示框，用于大量信息告示，没有字数限制
	 * 
	 * @param title   窗口标题
	 * @param content 窗口内容
	 * @param isModal 是否为模态窗
	 * @param isOnTop 是否置顶
	 */
	public static void createLongMessageDialog(String title, String content, boolean isModal, boolean isOnTop) {
		DialogModel dialogModel = new DialogModel();
		dialogModel.setTitle(title);
		dialogModel.setContent(content);
		dialogModel.setModal(isModal);
		dialogModel.setOnTop(isOnTop);
		JDialog dialog = DialogFactory.makeInfoDialog(325, 345, "/mydialog/bg/LongMessageBg.png", dialogModel);
		JLabel titleLabel = new JLabel(dialogModel.getTitle());
		titleLabel.setFont(new Font("黑体", Font.BOLD, 15));
		titleLabel.setBounds(3, 3, 156, 18);
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("等线", Font.BOLD, 15));
		textArea.setText(dialogModel.getContent());
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 36, 301, 257);
		URL normalButtonImage = InfoDialog.class.getResource("/mydialog/button/long-close-normal.png");
		URL mouseOnButtonImage = InfoDialog.class.getClass().getResource("/mydialog/button/long-close-mouseon.png");
		JButton close = new JButton(new ImageIcon(normalButtonImage));
		close.setBounds(299, 2, 24, 24);
		close.setBorderPainted(false);
		close.setContentAreaFilled(false);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		close.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				close.setIcon(new ImageIcon(mouseOnButtonImage));
			}

			public void mouseExited(MouseEvent e) {
				close.setIcon(new ImageIcon(normalButtonImage));
			}
		});
		JButton ok = new JButton("知道了");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		ok.setFont(new Font("黑体", Font.BOLD, 14));
		ok.setContentAreaFilled(false);
		ok.setBounds(122, 305, 79, 28);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.add(titleLabel);
		panel.add(scrollPane);
		panel.add(close);
		panel.add(ok);
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}

}