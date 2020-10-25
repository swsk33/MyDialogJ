package swsk33.mydialogj;

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
import swsk33.mydialogj.event.EventEditor;
import swsk33.mydialogj.exception.ContentOutOfRangeException;
import swsk33.mydialogj.model.DialogModel;

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

	private static int x;
	private static int y;
	private boolean isok = false;
	private JDialog jd = new JDialog();
	private JButton ok = new JButton();
	private JButton no = new JButton();
	private Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();

	private void dialogSetup(int width, int height, String bgPath, DialogModel dm) throws ContentOutOfRangeException { // 设置窗口基本属性和特性
		jd.setSize(width, height);
		jd.setLocation((sc.width - jd.getWidth()) / 2, (sc.height - jd.getHeight()) / 2);
		jd.setAlwaysOnTop(dm.getIsOnTop());
		jd.setUndecorated(true);
		jd.setModal(true);
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
		title.setText(dm.getTitle());
		title.setFont(new Font("等线", Font.BOLD, 16));
		title.setBounds(6, 3, 238, 24);
		// 字体大小根据内容长度自适应
		int size = 0;
		if (dm.getContent().length() <= 64) {
			size = 18;
		} else if (dm.getContent().length() > 64 && dm.getContent().length() <= 72) {
			size = 16;
		} else if (dm.getContent().length() > 72 && dm.getContent().length() <= 84) {
			size = 14;
		} else if (dm.getContent().length() > 84 && dm.getContent().length() <= 110) {
			size = 13;
		} else {
			throw new ContentOutOfRangeException("设定的内容字数超出限制（110长度）！超出：" + (dm.getContent().length() - 110));
		}
		JLabel content = new JLabel();
		content.setText("<html>" + dm.getContent() + "</html>");
		content.setFont(new Font("等线", Font.BOLD, size));
		content.setBounds(101, 40, 297, 78);
		URL jbnor = this.getClass().getResource("/res/button/close-normal.png");
		URL jbmon = this.getClass().getResource("/res/button/close-mouseon.png");
		String ico = "";
		if (dm.getDialogType() == 0) {
			ico = "/res/bg/ico/info-ico.png";
		} else if (dm.getDialogType() == 1) {
			ico = "/res/bg/ico/warn-ico.png";
		} else if (dm.getDialogType() == 2) {
			ico = "/res/bg/ico/error-ico.png";
		}
		URL frico = InfoDialog.class.getResource(ico);
		JLabel icol = new JLabel();
		icol.setIcon(new ImageIcon(frico));
		icol.setBounds(31, 62, 45, 45);
		JButton close = new JButton();
		close.setIcon(new ImageIcon(jbnor));
		close.setBounds(407, 0, 28, 28);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				close.setIcon(new ImageIcon(jbmon));
			}

			public void mouseExited(MouseEvent e) {
				close.setIcon(new ImageIcon(jbnor));
			}
		});
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isok = false;
				jd.dispose();
			}
		});
		ok.setText("确定");
		ok.setFont(new Font("黑体", Font.BOLD, 13));
		ok.setBounds(101, 130, 82, 32);
		ok.setContentAreaFilled(false);
		no.setText("取消");
		no.setBounds(246, 130, 82, 32);
		no.setContentAreaFilled(false);
		no.setFont(new Font("黑体", Font.BOLD, 13));
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		jp.setLayout(null);
		jp.add(title);
		jp.add(icol);
		jp.add(content);
		jp.add(close);
		jp.add(ok);
		jp.add(no);
		jd.getContentPane().add(jp);
	}

	/**
	 * 布尔型事件窗口，是一个模态窗，点确定返回true，取消或者直接关闭窗口返回false
	 * 
	 * @param dm 告示窗的属性模型。需要先实例化DialogModel类并用里面的set方法设定属性，然后把这个实例作为此参数传入<br>
	 *           这个窗口中传入的DialogModel实例需要设置的属性（4个，其中有2个有默认值）：<br>
	 *           <ol>
	 *           <li>setTitle(String title)：设置窗口标题</li>
	 *           <li>setContent(String content)：设置窗口内容</li>
	 *           <li>setDialogType(int dialogType)：设置窗口类型，不设置的话默认为告示信息提示窗</li>
	 *           <li>setIsOnTop(boolean isOnTop)：设置窗口是否置顶，不设置的话默认为不置顶</li>
	 *           </ol>
	 * @return 布尔值，点击确定返回true，取消返回false
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public boolean createBooleanEventDialog(DialogModel dm) throws ContentOutOfRangeException {
		this.dialogSetup(435, 178, "/res/bg/BooleanDialog.png", dm);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isok = true;
				jd.dispose();
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isok = false;
				jd.dispose();
			}
		});
		jd.setVisible(true);
		return isok;
	}

	/**
	 * 自定义事件窗口，是一个模态窗，通过重写抽象类EventEditor中的customOkEvent()和customCancelEvent()方法，传入到此方法中来分别实现确定按钮和取消按钮的自定义事件。
	 * 
	 * @param dm  告示窗的属性模型。需要先实例化DialogModel类并用里面的set方法设定属性，然后把这个实例作为此参数传入<br>
	 *            这个窗口中传入的DialogModel实例需要设置的属性（4个，其中有2个有默认值）：<br>
	 *            <ol>
	 *            <li>setTitle(String title)：设置窗口标题</li>
	 *            <li>setContent(String content)：设置窗口内容</li>
	 *            <li>setDialogType(int dialogType)：设置窗口类型，不设置的话默认为告示信息提示窗</li>
	 *            <li>setIsOnTop(boolean isOnTop)：设置窗口是否置顶，不设置的话默认为不置顶</li>
	 *            </ol>
	 * @param ete 接口，需要在这里实现接口的两个方法分别实现两个按钮的事件自定义
	 * @throws ContentOutOfRangeException 设定的内容字数超出了限制（110字）抛出异常
	 */
	public void createCustomEventDialog(DialogModel dm, EventEditor ete) throws ContentOutOfRangeException {
		this.dialogSetup(435, 178, "/res/bg/EventBg.png", dm);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ete.customOkEvent();
				jd.dispose();
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ete.customCancelEvent();
				jd.dispose();
			}
		});
		jd.setVisible(true);
	}

}