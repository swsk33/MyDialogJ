package swsk33.md;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class InfoDialog {
	private static int x;
	private static int y;
	public final static int NONE = 0;
	public final static int INFO = 1;
	public final static int WARN = 2;
	public final static int ERROR = 3;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createShortNoticeDialog(String title, String buttonContent, String content, boolean isOnTop,
			boolean isModal) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension sc = kit.getScreenSize();
		JDialog jd = new JDialog();
		jd.setSize(500, 230);
		jd.setLocation((sc.width - jd.getWidth()) / 2, (sc.height - jd.getHeight()) / 2);
		jd.setModal(isModal);
		jd.setAlwaysOnTop(isOnTop);
		jd.setUndecorated(true);
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
		jd.show();
	}
}
