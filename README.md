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
	<version>2.0.0</version>
</dependency>
```
### 2，导入com.gitee.swsk33.mydialog下所有的类或者需要的类
### 3，常用类及其方法：
#### 类InfoDialog：信息告示窗，只用于显示提示信息
- static void createShortNoticeDialog(String title, String content, int dialogType)：创建一个信息告示窗（非模态、非置顶、有提示音）
- static void createShortNoticeDialog(String title, String content, int dialogType, boolean isModal, boolean isOnTop, boolean isMute)：创建一个信息告示窗
- static void createLongMessageDialog(String title, String content, boolean isModal, boolean isOnTop)：创建一个长消息信息告示框，用于大量信息告示，没有字数限制
#### 类EventDialog：事件告示窗，分为：布尔型窗口：有“确定”和“取消”两个按钮，点确定返回true点取消返回false；自定义事件窗口：有“确定”和“取消”两个按钮，通过实现接口的方法来给确定按钮和取消添加代码段

>最后更新：2020.5.8