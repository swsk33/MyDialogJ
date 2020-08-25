import swsk33.md.*;
import swsk33.md.Exception.ContentOutOfRangeException;
import swsk33.md.model.InfoDialogModel;

public class MainTest {

	public static void main(String[] args) throws ContentOutOfRangeException {
		InfoDialogModel idm = new InfoDialogModel();
		idm.setTitle("测试窗口");
		idm.setContent("试测试测试测试试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
		idm.setDialogType(0);
		InfoDialog id = new InfoDialog();
		id.createShortNoticeDialog(idm);
	}

}
