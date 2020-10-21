# MyDialogJ使用说明
### 这是一个简易的、在Java中用于方便地创建各种对话框、告示框的外部包。
### 其功能有：
1，创建普通告示窗。<br>
2，创建长信息告示窗。<br>
3，创建布尔事件型告示窗。<br>
4，创建自定义按钮事件型告示窗。<br>
5，创建文本信息输入框。<br>
### 下载地址:[点击进入下载jar包](https://gitee.com/swsk33/MyDialogJ/releases)
## 使用方法：
### 1，添加依赖，有下列两种情况：
①Eclipse直接添加jar：先下载这个jar包并把这个包导入到IDE里面，例如eclipse。不知道如何导入请查看教程：[eclipse导入外部jar包](https://blog.csdn.net/czbqoo01/article/details/72803450)<br>
②Maven工程：在项目的配置文件pom.xml中的```<dependencies>```标签里加入下列依赖，此操作无需在上面手动下载jar包（推荐）：<br>
```
<dependency>
	<groupId>com.gitee.swsk33</groupId>
	<artifactId>mydialog-java</artifactId>
	<version>1.2.10</version>
</dependency>
```
### 2，依次导入swsk33.mydialogj、swsk33.mydialogj.model、swsk33.mydialogj.exception、swsk33.mydialogj.utils、swsk33.mydialogj.event（不创建自定义事件型窗口可以不用导入event包），这几个包下所有的或者需要的类。
```
import swsk33.mydialogj.*;
import swsk33.mydialogj.model.*;
import swsk33.mydialogj.exception.*;
import swsk33.mydialogj.utils.*;
import swsk33.mydialogj.event.*;
```
### 3，语法：
**说在最前：下面语法示例中用了最快捷的方法去执行了某个类中的某个方法。**<br>
**实际上这两种方式执行效果相同：**<br>
**方式一：**<br>
```A a=new A();```<br>
```a.af();```<br>
**方式二:**<br>
```new A().af();```<br>
**上述方式一、二效果相同，都是执行了A类里的af方法。只是方法一先生成了对象。下面示例基本上用方法二进行演示。**<br>
--------------------------------------------------------------------------------------------------------------------
首先我们要实例化一个窗口属性模型对象，这个对象储存着窗口的属性，然后再传入到各个创建窗口方法中去。例如：<br>
```
DialogModel dm = new DialogModel();// 实例化窗口模型对象
dm.setTitle("提示窗标题");// 设置窗口标题
dm.setContent("提示窗内容：少时诵诗书所所所所所所");// 设置窗口内容
dm.setDialogType(DialogTypeValue.INFO);// 设置窗口类型，不设置的话默认为告示信息提示窗（其中DialogTypeValue.INFO是告示信息提示窗，DialogTypeValue.WARN是警告信息提示窗，DialogTypeValue.ERROR是错误信息提示窗）
dm.setIsModal(false);// 设置窗口是否模态，不设置的话默认为不模态
dm.setIsOnTop(false);// 设置窗口是否置顶，不设置的话默认为不置顶
```
这样就实例化了一个窗口属性模型对象dm，下面都会用这个dm对象作为例子传入方法。<br>
#### (1)创建一个普通的信息告示窗：
```new InfoDialog().createShortNoticeDialog(窗口属性模型实例);```
**例如创建一个标题为“提示窗标题”，内容为“测试内容”的告示信息类型提示窗**
```
DialogModel dm = new DialogModel();// 实例化窗口模型对象
dm.setTitle("提示窗标题");// 设置窗口标题
dm.setContent("测试内容");// 设置窗口内容
dm.setDialogType(DialogTypeValue.INFO);// 设置窗口类型，不设置的话默认为告示信息提示窗
dm.setIsModal(false);// 设置窗口是否模态，不设置的话默认为不模态
dm.setIsOnTop(false);// 设置窗口是否置顶，不设置的话默认为不置顶
new InfoDialog().createShortNoticeDialog(dm);
```
效果：<br>
![普通告示窗效果](https://file.moetu.org/images/2020/08/26/1c98210268e0e3837.jpg)<br>
setDialogType()方法可以设置三种不同的告示窗类型，不同的类型告示窗旁边的小图标不同，大家可以自行尝试。<br>
#### (2)创建一个长信息告示窗：
有时候告示的信息非常长，用普通的告示框就装不下我们的信息了。这时可以使用长信息告示窗：<br>
```new InfoDialog().createLongMessageDialog(窗口属性模型实例);```
**例如创建一个标题为“提示窗标题”，内容为“测试内容测试内容。。。。。”的长信息类型提示窗**
```
DialogModel dm = new DialogModel();// 实例化窗口模型对象
dm.setTitle("提示窗标题");// 设置窗口标题
dm.setContent("测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容");// 设置窗口内容
dm.setIsModal(false);// 设置窗口是否模态，不设置的话默认为不模态
dm.setIsOnTop(false);// 设置窗口是否置顶，不设置的话默认为不置顶
new InfoDialog().createLongMessageDialog(dm);
```
效果：<br>
![长信息告示窗效果](https://file.moetu.org/images/2020/08/26/280e671fa87084c0f.jpg)<br>
需要注意的是，这里长信息提示框的窗口属性实例中不需要设置窗口类型属性。<br>
#### (3)创建一个布尔型事件告示窗：
布尔型事件窗口，是一个模态窗，点确定按钮返回true，点取消按钮或者直接关闭窗口返回false。<br>
```new EventDialog().createBooleanEventDialog(窗口属性模型实例);```
**例如创建一个标题为“提示窗标题”，内容为“测试内容”的布尔事件类型提示窗，并打印出返回值**
```
DialogModel dm = new DialogModel();// 实例化窗口模型对象
dm.setTitle("提示窗标题");// 设置窗口标题
dm.setContent("测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容");// 设置窗口内容
dm.setDialogType(DialogTypeValue.INFO);// 设置窗口类型，不设置的话默认为告示信息提示窗
dm.setIsOnTop(false);// 设置窗口是否置顶，不设置的话默认为不置顶
boolean isok = new EventDialog().createBooleanEventDialog(dm);
System.out.println(isok);
```
效果：<br>
![布尔事件型告示窗效果](https://file.moetu.org/images/2020/08/26/383fc4cf812e967a4.jpg)<br>
需要注意的是，这里布尔事件型提示框的窗口属性实例中不需要设置是否模态属性。<br>
#### (4)创建一个自定义按钮事件的告示窗：
自定义事件窗口，是一个模态窗，通过重写抽象类EventEditor中的customOkEvent()和customCancelEvent()方法，传入到此方法中来分别实现确定按钮和取消按钮的自定义事件。<br>
```new EventDialog().createCustomEventDialog(窗口属性模型实例, 实现抽象类EventEditor中的customOkEvent()和customCancelEvent()方法)```
**例如创建一个标题为“提示窗标题”，内容为“测试内容”的自定义事件类型提示窗，并设置点击确定后控制台输出"ok"，点击取消后控制台输出"cancel"**
```
DialogModel dm = new DialogModel();// 实例化窗口模型对象
dm.setTitle("提示窗标题");// 设置窗口标题
dm.setContent("测试内容");// 设置窗口内容
dm.setDialogType(DialogTypeValue.INFO);// 设置窗口类型，不设置的话默认为告示信息提示窗
dm.setIsOnTop(false);// 设置窗口是否置顶，不设置的话默认为不置顶
new EventDialog().createCustomEventDialog(dm, new EventEditor() {

	@Override
	public void customOkEvent() {
		System.out.println("ok");
	}

	@Override
	public void customCancelEvent() {
		System.out.println("cancel");
	}
});
```
效果：<br>
![布尔事件型告示窗效果](https://file.moetu.org/images/2020/08/26/494e3e7652482d9ed.jpg)<br>
需要注意的是，这里自定义事件提示框的窗口属性实例中不需要设置是否模态属性。<br>
#### (5)创建一个文本输入或者密码输入窗：
文本输入框：<br>
```new InputDialog().createTextInputDialog(窗口标题, 输入提示内容);```<br>
密码输入框：<br>
```new InputDialog().createPasswordInputDialog(窗口标题, 输入提示内容);```<br>
效果：<br>
文本输入框：<br>
![文本输入窗效果](https://file.moetu.org/images/2020/10/15/56ba94d1e482060db.jpg)<br>
密码输入框：<br>
![密码输入窗效果](https://file.moetu.org/images/2020/10/15/65da1a99ea32e9bdd.jpg)<br>