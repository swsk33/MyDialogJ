package swsk33.md;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import swsk33.md.model.*;

import java.net.*;

public class InfoDialog {
	private static int x;
	private static int y;
	public final static int NONE = 0;
	public final static int INFO = 1;
	public final static int WARN = 2;
	public final static int ERROR = 3;

	public void createShortNoticeDialog(InfoDialogModel idm) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension sc = kit.getScreenSize();
		JDialog jd = new JDialog();
		jd.setSize(500, 230);
		jd.setLocation((sc.width - jd.getWidth()) / 2, (sc.height - jd.getHeight()) / 2);
		jd.setModal(idm.getIsModal());
		jd.setAlwaysOnTop(idm.getIsOnTop());
		jd.setUndecorated(true);
		URL bg = InfoDialog.class.getResource("./res/bg/InfoBg.png");
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
		title.setBounds(6, 4, 238, 24);
		JLabel content = new JLabel("<html>" + idm.getContent() + "</html>");
		content.setFont(new Font("等线", Font.BOLD, 18));
		content.setBounds(78, 48, 352, 131);
		URL jbnor = InfoDialog.class.getResource("./res/button/close-normal.png");
		URL jbmon = InfoDialog.class.getResource("./res/button/close-mouseon.png");
		ImageIcon nor = new ImageIcon(jbnor);
		ImageIcon mon = new ImageIcon(jbmon);
		JButton close = new JButton(nor);
		close.setBounds(468, 0, 32, 32);
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
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		jp.setLayout(null);
		jp.add(title);
		jp.add(content);
		jp.add(close);
		jd.getContentPane().add(jp);
		jd.show();
	}
}
