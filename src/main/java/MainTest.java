import swsk33.md.*;
import swsk33.md.Exception.ContentOutOfRangeException;
import swsk33.md.model.DialogModel;

public class MainTest {

	public static void main(String[] args) throws ContentOutOfRangeException {
		DialogModel idm = new DialogModel();
		idm.setTitle("测试标题");
		idm.setContent("正文在少时诵诗书所所所所所所所所所所所所所所所所所所所所所所所所所");
		idm.setDialogType(0);
		idm.setIsOnTop(true);
		System.out.println(new EventDialog().createBooleanEventDialog(idm));
		System.out.println("执行结束。");
	}

}
