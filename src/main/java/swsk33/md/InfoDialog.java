package swsk33.md;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import swsk33.md.model.*;
import swsk33.md.Exception.*;

public class InfoDialog {
	private static int x;
	private static int y;
	/**
	 * 告示信息提示窗
	 */
	public final static int INFO = 0;
	/**
	 * 警告信息提示窗
	 */
	public final static int WARN = 1;
	/**
	 * 错误信息提示窗
	 */
	public final static int ERROR = 2;

	/**
	 * 创建一个信息告示窗
	 * 
	 * @param idm 信息告示窗的属性模型。需要先实例化InfoDialogModel类并用里面的set方法设定属性，然后把这个实例作为此参数传入
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public void createShortNoticeDialog(InfoDialogModel idm) throws ContentOutOfRangeException {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension sc = kit.getScreenSize();
		JDialog jd = new JDialog();
		jd.setSize(435, 178);
		jd.setLocation((sc.width - jd.getWidth()) / 2, (sc.height - jd.getHeight()) / 2);
		jd.setModal(idm.getIsModal());
		jd.setAlwaysOnTop(idm.getIsOnTop());
		jd.setUndecorated(true);
		URL bg = InfoDialog.class.getResource("/res/bg/InfoBg.png");
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
		JLabel title = new JLabel(idm.getTitle());
		title.setFont(new Font("等线", Font.BOLD, 16));
		title.setBounds(6, 3, 238, 24);
		// 字体大小根据内容长度自适应
		int size = 0;
		if (idm.getContent().length() <= 64) {
			size = 18;
		} else if (idm.getContent().length() > 64 && idm.getContent().length() <= 72) {
			size = 16;
		} else if (idm.getContent().length() > 72 && idm.getContent().length() <= 84) {
			size = 14;
		} else if (idm.getContent().length() > 84 && idm.getContent().length() <= 110) {
			size = 13;
		} else {
			throw new ContentOutOfRangeException("设定的内容字数超出限制（110长度）！超出：" + (idm.getContent().length() - 110));
		}
		JLabel content = new JLabel("<html>" + idm.getContent() + "</html>");
		content.setFont(new Font("等线", Font.BOLD, size));
		content.setBounds(101, 40, 297, 78);
		URL jbnor = InfoDialog.class.getResource("/res/button/close-normal.png");
		URL jbmon = InfoDialog.class.getResource("/res/button/close-mouseon.png");
		ImageIcon nor = new ImageIcon(jbnor);
		ImageIcon mon = new ImageIcon(jbmon);
		String ico = "";
		if (idm.getDialogType() == 0) {
			ico = "/res/bg/ico/info-ico.png";
		} else if (idm.getDialogType() == 1) {
			ico = "/res/bg/ico/warn-ico.png";
		} else if (idm.getDialogType() == 2) {
			ico = "/res/bg/ico/error-ico.png";
		}
		URL frico = InfoDialog.class.getResource(ico);
		JLabel icol = new JLabel(new ImageIcon(frico));
		icol.setBounds(31, 62, 45, 45);
		JButton close = new JButton(nor);
		close.setBounds(406, 1, 28, 28);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				close.setIcon(mon);
			}

			public void mouseExited(MouseEvent e) {
				close.setIcon(nor);
			}
		});
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jd.dispose();
			}
		});
		JButton ok = new JButton("知道了");
		ok.setFont(new Font("黑体", Font.BOLD, 13));
		ok.setBounds(168, 130, 82, 32);
		ok.setContentAreaFilled(false);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jd.dispose();
			}
		});
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		jp.setLayout(null);
		jp.add(title);
		jp.add(icol);
		jp.add(content);
		jp.add(close);
		jp.add(ok);
		jd.getContentPane().add(jp);
		jd.show();
	}
}