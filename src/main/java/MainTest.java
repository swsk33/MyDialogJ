import swsk33.md.*;
import swsk33.md.model.InfoDialogModel;

public class MainTest {

	public static void main(String[] args) {
		InfoDialogModel idm = new InfoDialogModel();
		idm.setTitle("测试窗口");
		idm.setContent("测试的内容内容内容内容内容");
		InfoDialog id = new InfoDialog();
		id.createShortNoticeDialog(idm);
	}

}
