# 应用基础实践二

# 课程设计



<label><b>题目名称：             

  微博应用   基于二维码的快递信息保护系统</b></label>

编写日期：             2021/1



[TOC]



## 第一章  开发工具及环境说明

### 开发机器

操作系统:Windows 10 家庭中文版

处理器:Intel(R) Core(TM) i7-8750H CPU @2.20GHz 2.21Hz

### Eclipse

Eclipse IDE for Enterprise Java Developers (includes Incubating components)

Version: 2020-09 (4.17.0)
Build id: 20200910-1200

### Android Studio

Android Studio 3.6.3
Build #AI-192.7142.36.36.6392135, built on April 14, 2020
Runtime version: 1.8.0_212-release-1586-b04 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Windows 10 10.0
GC: ParNew, ConcurrentMarkSweep
Memory: 1237M
Cores: 12
Registry: ide.new.welcome.screen.force=true
Non-Bundled Plugins: com.tabnine.TabNine, org.jetbrains.kotlin

## 第二章  微博应用业务分析

### 1 实验要求

实践教学的内容或要求:①用Android开发微博客户端；②用JSP或PHP开发微博Web服务器；③用MySQL做Web服务器的后台数据库；④可以发表微博、查看微博及微博列表等功能；⑤可以发表评论功能；⑥可以实现用户客户端登陆功能。

### 2 业务分析

1. 登录
2. 注册
3. 头像上传
4. 发微博
5. 查看微博
6. 发表评论
7. 我的空间

## 第三章  微博应用系统设计

### 1 整体架构

首先,因为微博相较于另一个选题,物流系统,有着更大的自由发挥与设计空间,而原生的安卓ui设计与逻辑设计有着庞大的体系且与html+css+js三件套不完全相同的一套体系.鉴于

1.本学没有选到android课而选到了web课

2.个人对web在未来的使用肯定会比android开发频繁

所以选择将整体程序做成一个webapp,即前端用web实现,最后android里面唯一的activity里面是一个webview,在这里面运行,相当于android只是一个壳子.

在前端部分,使用常用的html+css+js的基本三件套,对于需要的部分会采用一些框架(下文介绍)

后端部分,采取servlet+tomcat的配置

### 2.前端与交互设计

相较于传统的微博应用,本app采取了一种广义上拟物风格的设计语言,将每一条微博抽象成一张卡牌(inspired by the game 'reign'),所有的交互都是基于对一种卡牌的物理操作(翻转,发牌,拖拽等).这也是本app的核心设计理念.

前端分为两部分:登陆页面和微博浏览页面

按照上述设计理念,将具体的交互设计如下:

#### 2.1.登陆页面

卡牌正面是登陆页面,翻转后是注册页面

在注册页面上,点击上传头像的框会从屏幕上方发下来一张新牌(并且通过拖拽可以吸附到牌堆的最顶端),在这一张牌上进行头像的上传,裁剪,编辑.

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/%E6%89%8B%E6%9C%BAQQ%E8%A7%86%E9%A2%91_20201220102705%5B00-00-02--00-00-08%5D.gif" alt="手机QQ视频_20201220102705[00-00-02--00-00-08]" style="zoom: 50%;" />

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/%E6%89%8B%E6%9C%BAQQ%E8%A7%86%E9%A2%91_20201220102705%5B00-00-19--00-00-34%5D.gif" alt="手机QQ视频_20201220102705[00-00-19--00-00-34]" style="zoom:50%;" />

#### 2.2.浏览页面

浏览页面主要界面是一个牌堆,正面是当前正在浏览的微博,背面是这条微博的评论区.通过左滑和右滑可以进行浏览下一张(下一条微博)的操作

牌堆的正上方是半张牌,这一张牌通过拖拽可以吸附到牌堆的最顶端,这是写博客的界面.

整个界面上还有一个可以拖拽的悬浮按钮,只要点击就可以切换到"我的空间",也就是切换到浏览自己的博客的牌堆.

最后加了一个发送图片的功能,只要浏览到有图片的微博,右上角会掉下来一个标签,只要拽一拽就能从左边划出一张卡片,上面是附带的图片

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/37B76260C136F69B5E43D198832DA2B7.jpg" alt="37B76260C136F69B5E43D198832DA2B7" style="zoom: 25%;" />

​                                                                                                   上面这张是设计稿

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/%E6%89%8B%E6%9C%BAQQ%E8%A7%86%E9%A2%91_20201220102710%5B00-00-37--00-00-47%5D.gif" alt="手机QQ视频_20201220102710[00-00-37--00-00-47]" style="zoom:50%;" />

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/%E6%89%8B%E6%9C%BAQQ%E8%A7%86%E9%A2%91_20201220102700%5B00-00-03--00-00-17%5D.gif" alt="手机QQ视频_20201220102700[00-00-03--00-00-17]" style="zoom:50%;" />

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/QQ%E5%BD%95%E5%B1%8F20201229223731%5B00-00-00--00-00-09%5D.gif" alt="QQ录屏20201229223731[00-00-00--00-00-09]" style="zoom: 67%;" />

(电脑端查看微博图片示意图)

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/QQ%E5%BD%95%E5%B1%8F20201229222342%5B00-00-00--00-00-05%5D-1609251968604.gif" alt="QQ录屏20201229222342[00-00-00--00-00-05]" style="zoom:33%;" />



### 3.后端设计

后端采用servlet+tomcat实现,分为两个包,util和servlet,分别作为通用的工具类和服务器类,由于服务器的开发经验不足,所以为了降低类之间的耦合度,将每一个功能的请求分别传送到完全不同的服务器(服务器类)中,对于数据传送,采取http fetch+ajax实现,这两个方式都是异步的,相较于传统的form submit具有更高的灵活度,并且不会强制页面跳转.

后端具体架构将在代码分析中说明.

### 4.图标设计

图标作为app最直观的展示,尤其对于本app这样一个重设计的app来说,确实需要一些设计,根据上文提到的核心设计思想:拟物化,动画化.设计如下

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/IMG_4370.PNG" alt="IMG_4370" style="zoom: 10%;" />



其中主要元素体现了拟物化,即卡片.三张有动态关联的卡片体现了动的特点,即动画化.

在手机上效果如下:

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/Screenshot_2020-12-20-10-08-26-43.png" alt="Screenshot_2020-12-20-10-08-26-43" style="zoom: 33%;" />

## 第四章  微博应用源代码清单

源码请查看附件

### 1.代码结构

![image-20201219210903865](%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/image-20201219210903865.png)

上图是前端的代码目录结构,其中assets文件夹保存用到的字体,图片等(每一类有独立的文件夹),css,js可以"望文生义",blog.html保存浏览页面的前端,login.html保存登陆页面的前端.todos是上文提到的todo list,与实现无关.

css文件解析:

> animate.css:开源的一个基本动画库
>
> bounce.css自己基于animate.css实现的一个基本的上方发牌动画
>
> colors.css颜色管理的css
>
> cropper.css图片裁剪的开源库组件
>
> flip.css基于上述动画库实现的翻转动画
>
> style.css样式表

js文件解析

> jquery:js的补充
>
> blog.js/login.js分别对应相应的html
>
> card.js自己实现的扑克牌左右翻转的动画脚本
>
> cardtricks.js基于动画库的一个较复杂的连续发牌动画脚本
>
> cropper.js图片裁剪的库
>
> drag.js实现拖拽效果的js
>
> swipesupport移动端滑动屏幕支持的库
>
> tpunch-modified.js touch-punch库的改良版,用于在移动端支持拖拽,改良了对于ios端input的textarea不能focus的bug
>
> 此外还通过cdn引用了jqueryui库(主要是drag事件)与jquerymobile库(移动端支持,主要是swip)

### 2.关键代码解析

其实本项目最主要的亮点是设计,而其中的一大部分都在于动画效果的应用,首先解析几个关键的动画效果

#### 2.1牌堆滑动以切换下一张的动画:

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/%E6%89%8B%E6%9C%BAQQ%E8%A7%86%E9%A2%91_20201220102710%5B00-00-37--00-00-47%5D-1608433724882.gif" alt="手机QQ视频_20201220102710[00-00-37--00-00-47]" style="zoom:25%;" />

基本思想就是有两张重复叠在一起的卡片,滑动时对在上面的卡添加左右滑动的动画,下面的卡牌做一个缩放的动画,看起来就是下面的卡升上来一样.

注意的几个要点:

1.为了避免动画完成之前就被用户打断,设一个animated_done的变量,在动画完成时切换,相当于一个互斥锁

2.每一张卡有id car1 card2.切换卡牌在前后用class:front/back的动态增减

```javascript
var animate_done = 1;
$(document).ready(function () {
    $("#card1").on("swipeleft", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card1").addClass("animated rotateOutUpLeft back-card");
            setTimeout(function () {
                $("#card1").removeClass("animated rotateOutUpLeft front-card");
            }, 1000);

            $("#card2").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card2").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });
    $("#card2").on("swipeleft", function () {
       //pass
    });
    $("#card1").on("swiperight", function () {
        //pass
    });
    $("#card2").on("swiperight", function () {
        //pass
    });
}
```

#### 2.2拖拽以消失/出现

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/%E6%89%8B%E6%9C%BAQQ%E8%A7%86%E9%A2%91_20201220102700%5B00-00-03--00-00-17%5D-1608433740795.gif" alt="手机QQ视频_20201220102700[00-00-03--00-00-17]" style="zoom:25%;" />

```javascript
var openwrite = function () {
    if (animate_done == 1) {//动画锁,同上
        animate_done = 0;
        if (write == 0) {//这个页面出来了没有:二元变量
            write = 1;
            $("#card5").offset({"top":t});//设置位置在上面(只显示一小半)还是下面
            $(".write-card")
                .addClass(
                    "animated bounceInDown1");//做动画
            $(".write-card")
                .addClass(
                    "animated bounceInDown1");
            setTimeout(
                function () {
                    $(".write-card")
                        .removeClass(
                            "animated bounceInDown1");
                    $("body").addClass("body3");
                    animate_done = 1;
                }, 1000);//动画做完时去除动画并且改变动画锁
        } else {
            write = 0;
            $(".write-card")
                .addClass(
                    "animated bounceOutDown1");
            setTimeout(
                function () {                   
                    $(".write-card")
                        .removeClass(
                            "animated bounceOutDown1");
                    $("body").removeClass("body3");
                    $("#card5").offset({"top":-(t2-t+140)});
                    $("#card5").animate({
                        top: -(t2-t+140)+"px"
                    },
                    100
                );
                    animate_done = 1;
                }, 1000);
        }
    }
}
```



```javascript
$(document).ready(function () {
    $(".write-card").draggable({//可以纵向拖拽
        axis: "y"
    });
    $(".write-card").droppable();
    $(".write-card").on("dragstop", function (event, ui) {
        openwrite();
    });
});
```

#### 2.3飞牌效果

```javascript
var dealer_in = function () {
    animate_done=0;
    $(".card").addClass("AnimateFast");
    $(".blog").css("display", "none");
    $("#card1").addClass("animated");
    $("#card2").addClass("animated");
    $("#card1").css("display", "none");
    //先隐去上面的牌,当下面的牌到位之后就可以只连续变化上面的牌,看起来
    //就像是牌堆上一直在加牌
    $("#card2").addClass("fadeInLeftBig");
    setTimeout(
        function () {
            $("#card1").addClass("fadeInLeftBig");
            $("#card1").css("display", "inline");
        }, 200);

    setTimeout(
        function () {
            $("#card2").removeClass("fadeInLeftBig");
        }, 200 + 5);
    //在动画的css的class里,设置动画重复次数比较多,这样只要控制去掉动画属性的时间就可以很方便的控制动画重复次数
    //即有几张牌发到了牌堆顶上
    setTimeout(
        function () {
            $("#card1").removeClass("fadeInLeftBig");
            $(".card").removeClass("AnimateFast");
            $(".blog").css("display", "inline");
            animate_done=1;
        }, 600);

}
```

#### 2.4 图片裁剪(cropper.js的调用)

```javascript
<script type="text/javascript">
		// 选择文件触发事件  
		function selectImg(file) { //选择了图片,将图片放在框里供裁剪
			//文件为空，返回  
			if (!file.files || !file.files[0]) {
				return;
			}
			var reader = new FileReader();
			reader.onload = function (evt) {
				var replaceSrc = evt.target.result;
				// 更换cropper的图片  
				$('#tailoringImg').cropper('replace', replaceSrc, false); // 默认false，适应高度，不失真  
			}
			reader.readAsDataURL(file.files[0]);
		}
		// cropper图片裁剪  
		$('#tailoringImg').cropper({
			aspectRatio: 1 / 1, // 默认比例  
			preview: '.uploadport', // 预览视图  
			guides: false, // 裁剪框的虚线(九宫格)  
			autoCropArea: 0.5, // 0-1之间的数值，定义自动剪裁区域的大小，默认0.8  
			movable: false, // 是否允许移动图片  
			dragCrop: true, // 是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域  
			movable: true, // 是否允许移动剪裁框  
			resizable: true, // 是否允许改变裁剪框的大小  
			zoomable: true, // 是否允许缩放图片大小  
			mouseWheelZoom: true, // 是否允许通过鼠标滚轮来缩放图片  
			touchDragZoom: true, // 是否允许通过触摸移动来缩放图片  
			rotatable: true, // 是否允许旋转图片  
			crop: function (e) {
				// 输出结果数据裁剪图像。  
			}
		});
		// 确定按钮点击事件  
		$("#sureCut").on("click", function () {
			if ($("#tailoringImg").attr("src") == null) {
				return false;
			} else {
				var cas = $('#tailoringImg').cropper('getCroppedCanvas'); // 获取被裁剪后的canvas  
				var base64 = cas.toDataURL('image/jpeg'); // 转换为base64  

				$(".uploadport").prop("src", base64); // 显示图片  
				uploadFile(encodeURIComponent(base64)) //编码后上传服务器  
				closeTailor(); // 关闭裁剪框  
			}
		});
```

#### 2.5 拽绳动画

 <img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/QQ%E5%BD%95%E5%B1%8F20201229222342%5B00-00-00--00-00-05%5D.gif" alt="QQ录屏20201229222342[00-00-00--00-00-05]" style="zoom:33%;" />

这个动画由两部分组成:下落/上升+摇摆.

下落的时候会有一个回弹的细节动作,增加了下落的动感.(上升同理)

要做到一个符和物理直觉的摇摆动画,用

```css
@keyframes Pendulum {
	0% {
		-webkit-transform: rotate(10deg);
		-webkit-transform-origin: top center;
		-moz-transform: rotate(10deg);
		-moz-transform-origin: top center;
		transform: rotate(10deg);
		transform-origin: top center;
	 }
	 100% {
		-webkit-transform: rotate(-10deg);
		-webkit-transform-origin: top center;
		-moz-transform: rotate(-10deg);
		-moz-transform-origin: top center;
		transform: rotate(-10deg);
		transform-origin: top center;
	}
}
```

这样一来,必须在下坠动画的结尾就偏移到10度的位置

```css
@keyframes dropin {

	0%,
	60%,
	75%,
	90%,
	100% {
		-webkit-transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
		-webkit-transform-origin: top center;
		-moz-transform-origin: top center;
		-moz-transform-origin: top center;
		transform-origin: top center;
		transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
	}

	0% {

		-webkit-transform: rotate(0deg) translate3d(0, -3000px, 0);
		transform: rotate(0deg) translate3d(0, -3000px, 0);

	}

	60% {
		-webkit-transform: rotate(6deg) translate3d(0, 25px, 0);
		transform: rotate(6deg) translate3d(0, 25px, 0);
	}

	75% {
		-webkit-transform: rotate(7.5deg) translate3d(0, -10px, 0);
		transform: rotate(7.5deg) translate3d(0, -10px, 0);
	}

	90% {
		-webkit-transform: rotate(9deg) translate3d(0, 5px, 0);
		transform: rotate(9deg) translate3d(0, 5px, 0);
	}

	100% {
		-webkit-transform: rotate(10deg);
		transform: rotate(10deg);
	}
}

.dropin {
	-webkit-animation-name: dropin;
	animation-name: dropin;
}
```

另外,衔接两个动画需要做到一次只有一个动画属性,如果有两个的话第二个动画属性不会触发.

将动画属性封装到css类里面,动态添加删除类就可以保持只有一个动画属性



这里有一个问题,只做到了下落时的动作衔接却无法做到回升时的动作衔接,因为不知道用户会在rotate几度的时候会回升,所以无法知道要rotate到哪里.

肯定有解决方法,但是时间有限,就先放到这里了.

#### 2.6 blog类的设计

```java
package util;

import java.util.ArrayList;
import java.util.Iterator;

public class Blog {
	public int BID = -1;//博客号码
	int userID = -1;//用户号码
	String userName="";//用户名
	String text = "";//博客内容
	ArrayList<String> commentusers = new ArrayList<String>();//评论的用户名
	ArrayList<String> comments = new ArrayList<String>();//评论列表
	static  DManager dm = DManager.getdm();//数据库管

	public void Comment(String comment) {
		dm.comment(BID, comment, userID);//用数据库管评论
	}

	public Blog(int BID, int userID, String text,String userName) {
		this.BID = BID;
		this.userID = userID;
		this.text = text;
		this.userName=userName;
	}
	
}
```

### 3  问题解决

#### 3.1 嵌套查询结构混乱

这里有一个要点,本函数其中反复调用了getuname函数,即另一个查询,而rs都是一样的即一个全局变量,如果查询中嵌套一个查询,就会把外层查询结果扰乱.

```java
public Blog getcomments(Blog b) {
		ArrayList list = new ArrayList();
		String sql = "select * from bNc,comments where BID=" + String.valueOf(b.BID) + " and bNc.CID=comments.CID";
		try {
			// System.out.println(sql);
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			ResultSet rs2 = rs;// 注意这里,不然后来getuname会变掉
			int uid = -1;
			String comment;
			while (rs2.next()) {
				uid = rs2.getInt("UserID");
				comment = rs2.getString("comment");
				String name = getuname(uid);
				b.commentusers.add(name);
				b.comments.add(comment);
			}
			System.out.println(b.comments);
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
```

#### 3.2 局域网内无法连接servlet

除了开启防火墙的对应端口外,还要将jdk的对应网络权限展开

#### 3.3 touchpunch drag的对象无法输入:
stackoverflow:
https://stackoverflow.com/questions/12539072/jquery-ui-touch-punch-js-script-is-preventing-input-functionality-on-touch-devic/15373981#15373981

总而言之就是判断输入端是否是input或者textarea,如果是的话drag动画就可以停下了

#### 3.4 服务器端收到的请求有时乱码

发送请求前使用

```javascript
var url=encideURI(url);
```

改变编码

#### 3.5 WebView无法跳转

```java
webview.setWebViewClient(new WebViewClient( ){
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
```

#### 3.6  oppo手机安装应用失败

https://blog.csdn.net/qq_15867901/article/details/79259034

卸载重装即可

#### 3.7 webview支持图片上传

https://blog.csdn.net/fuzhongbin/article/details/78769527

#### 3.8 在电脑端动态调试webview

https://blog.csdn.net/qq_26585943/article/details/79863042

#### 3.9 使用本地gradle

https://www.jb51.net/article/197693.htm

 #### 3.10 设备适配

在开局的时候没有规划好,宽度高度用了px

之后用sof的js让card在屏幕中间会出问题,最后用css媒体选择-响应式布局解决,示例代码如下:

```css
@media only screen and (max-width: 330px) and (min-width: 100px) {
			.card {
				backface-visibility: hidden;
				border-radius: 30px;
				position: absolute;
				top: 10%;
				left: 1%;
				z-index: 99;
			}
		}

		@media only screen and (max-width: 420px) and (min-width: 330px) {
			.card {
				backface-visibility: hidden;
				border-radius: 30px;
				position: absolute;
				top: 10%;
				left: 8%;
				z-index: 99;
			}
		}
```

## 第五章  微博应用运行结果与测试分析

测试用例围绕需求列表进行

1. 登录

2. 注册

3. 头像上传

4. 发微博

5. 查看微博

6. 发表评论

7. 我的空间

    部分测试结果截图如下:

    <img src="%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/image-20210109193109888.png" alt="image-20210109193109888" style="zoom:25%;" /><img src="%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/image-20210109193125780.png" alt="image-20210109193125780" style="zoom:25%;" /><img src="%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/image-20210109193209317.png" alt="image-20210109193147867" style="zoom:25%;" />

更多的测试与运行其实花在了动画的测试上,有前面的动图可见,这一部分已经通过测试.



## 第六章  基于二维码的快递信息保护系统业务分析

### 1 实验要求

**实践教学的内容或要求：**①编写Android客户端应用，通过扫描二维码就能获取收件人的信息；②对用JSP或PHP开发快递信息安全管理Web服务器；③用MySQL做快递信息安全管理Web服务器的后台数据库；④服务器端二维码生成模块，将用户个人信息经过加密后保存成一个二维码；⑤快递员的注册和登录功能；⑥快递信息后台安全管理功能，管理人员管理控制快递员能接触的收件人信息。

### 2 业务需求

1. 快递员注册
2. 登录
3. 扫描二维码
4. 通过扫码获得快递信息
5. 调整包裹加密等级
6. 发送包裹

## 第七章  基于二维码的快递信息保护系统系统设计

因为第一个实验使用了webapp的架构设计,第二个就做一个纯的安卓应用的架构.

因为与第一个app不同,微博是我每天都会用到的,而这个快递管理系统不是,所以写分析应用场景

### 0 应用场景分析

快递员送快递时,根据快递盒子上的二维码,扫描之后在手机上查看快递的信息.

快递点收快递,输入快递信息,服务器端会生成一个二维码.快递点的管理人员也可以修改权限以明确包裹的加密等级

### 1安卓端设计

根据上述分析,安卓端需要五个activity

分别为登录,扫码,发包裹,改权限,相机界面

在登陆界面输入账号密码,点击登陆键则会登录,点击注册键则会注册,

后台根据账号的不同性质,自动在安卓前台跳转到不同页面,管理员则跳转到发包裹页面,快递员则会转到扫码界面

当在发包裹页面时,点击当中一个按钮可以切换到改权限界面,同样在改权限界面可以切换回发包裹界面

![image-20201223165139922](%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/image-20201223165139922.png)

### 2 服务器端设计

服务器端需要四个servlet,分别为登录,注册,发送包裹,修改权限,与上述安卓端四个activity对应

同时为了满足需要,需要定义几个通用类,管理权限的类,生成二维码的类,数据库管理类,包裹类

### 3 UI设计

#### 3.1图标设计

主体是一个快递盒子,盒子上的每一面都是一个二维码的图标,寓意即二维码和本app的相关性.

<img src="%E4%BA%8C%E7%BB%B4%E7%A0%81.assets/IMG_4111-1608714224321.PNG" alt="IMG_4111" style="zoom: 15%;" /><img src="%E4%BA%8C%E7%BB%B4%E7%A0%81.assets/IMG_4114.PNG" alt="IMG_4114" style="zoom:15%;" />

#### 3.2 UI设计

限于时间有限,且第一个项目做了很久,UI只是做了简单的设计,并没有太多可以交互的东西

<img src="%E4%BA%8C%E7%BB%B4%E7%A0%81.assets/image-20201223170708673.png" alt="image-20201223170708673" style="zoom:50%;" /><img src="%E4%BA%8C%E7%BB%B4%E7%A0%81.assets/image-20201223170730563.png" alt="image-20201223170730563" style="zoom:50%;" /><img src="%E4%BA%8C%E7%BB%B4%E7%A0%81.assets/image-20201223170808077.png" alt="image-20201223170808077" style="zoom:50%;" /><img src="%E4%BA%8C%E7%BB%B4%E7%A0%81.assets/image-20201223170749259.png" alt="image-20201223170749259" style="zoom:50%;" />

分别为登录,扫码,修改权限,发送包裹界面,使用了统一的一套颜色

## 第八章  基于二维码的快递信息保护系统源代码清单

### 1安卓代码结构

<img src="%E4%BA%8C%E7%BB%B4%E7%A0%81.assets/image-20201223171054190.png" alt="image-20201223171054190" style="zoom: 80%;" />

example.qr包里面除了activity的代码文件外,还有util包,这个包里面是二维码扫描界面的一些工具类

google.zxing包里是google的zxing开源项目的文件,主要用于二维码扫码,里面包括了扫描的activity,镜头类,解码编码等类

res文件夹保存了资源文件,包括图标,布局文件,扫码的哔哔声等

### 2后端代码结构

![image-20201223171711672](%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/image-20201223171711672.png)

分为两个包,服务器包,以及通用的工具类

服务器端的四个servlet,分别为登录,注册,发送包裹,修改权限,与上述安卓端四个activity对应

通用类分别为,管理权限的类,生成二维码的类,数据库管理类,包裹类

以及最下面的auth.txt保存了当前的安全等级,即题目中哪些信息可以展示给快递员

### 3 重点代码分析

#### 3.1 屏蔽快递信息的实现

设定了一个屏蔽等级

```java
public static int authlevel=5;
```

这是一个位串,每一位代表了可能会屏蔽的四个属性,屏蔽通过位操作实现.

```java
public Package getpackage(int pid) {//获取一个pid对应的package结构体
	int level=Authenticator.authlevel;
	String sql = "select * from packages where PID="+pid;
	try {
		sta = con.createStatement();
		rs = sta.executeQuery(sql);
		String sendername="defaultsender";
		String receivername="defaultreceiver";
		String Address="defaultaddr";
		String receievertel="12345678900";
		int state=-1;
		Package p=new Package(-1,sendername,receivername,Address,receievertel,-1);
		while (rs.next()) {
			sendername = rs.getString("sendername");
			receivername=rs.getString("receivername");
			Address=rs.getString("addr");
			receievertel=rs.getString("receievertel");
			state=rs.getInt("pstate");
		}
		if((level&1)==1) {//位操作,屏蔽对应四个属性
			p.sendername=sendername;
		}
		if((level&2)==2) {
			p.receivername=receivername;
		}
		if((level&4)==4) {
			p.Address=Address;
		}
		if((level&8)==8) {
			p.receievertel=receievertel;
		}
		p.PID=pid;
		p.state=state;
		return p;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	for (int i=0;i<level;i++) {
		if((i&level)==1) {
			
		}
	}
	return null;
}
```

#### 3.2 屏蔽等级的保存

因为涉及到信息的保存,而且信息量很小,所以保存屏蔽等级的位串采用文本文件实现

每次需要修改或者初始化的时候进行文件读取/写入操作

```java
public void changelevel(int leveltobe) {
		String fileName="H:\\java\\workspace\\tstwork\\QRcode/auth.txt";
        try
        {
                FileWriter writer=new FileWriter(fileName);
                writer.write(Integer.toString(leveltobe));
                writer.close();
                authlevel=leveltobe;
                System.out.println(authlevel);
        } catch (Exception e)
        {
                e.printStackTrace();
        }
	}
	public void readlevel() {
		String fileName="H:\\java\\workspace\\tstwork\\QRcode/auth.txt";
        int c=0;
        try
        {
                FileReader freader=new FileReader(fileName);
        	    BufferedReader reader = null;
        	    reader = new BufferedReader(freader); 	    
        	    String tempString = null;
        	    tempString = reader.readLine();
                reader.close();
                authlevel=Integer.parseInt(tempString);
                System.out.println(authlevel);
        } catch (Exception e) {
                e.printStackTrace();
        }
	}
```

#### 3.3 二维码生成

二维码在服务器端生成,采用google的zxing库实现,调用的代码为

```java
    public static void getBarCode(String msg,String path){
        try {
            File file=new File(path);
            OutputStream ous=new FileOutputStream(file);
            if(msg==null || ous==null)
                return;
            String format = "png";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType,String> map =new HashMap<EncodeHintType, String>();

            map.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            map.put(EncodeHintType.MARGIN,"2");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE,300,300,map);
            MatrixToImageWriter.writeToStream(bitMatrix,format,ous);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
```

#### 3.4 相机扫码的调用

采用zxing库,调用代码为:

```kotlin
private fun startQrCode() {
    // 申请相机权限
    if (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) !== PackageManager.PERMISSION_GRANTED
    ) {
        // 申请权限
        ActivityCompat.requestPermissions(
            this@Main2Activity,
            arrayOf(Manifest.permission.CAMERA),
            Constant.REQ_PERM_CAMERA
        )
        return
    }
override protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            val bundle = data?.extras
            val scanResult = bundle!!.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN)
            val textView: TextView = findViewById(R.id.scanResult) as TextView
            //将扫描出的信息显示出来
            //val gson= Gson()
            //val json=gson.toJson(scanResult);
            val json= JSONObject(scanResult)
            val res="发件人:"+json.getString("sendername")+"\n收件人:"+json.getString("receivername")+"\n地址:"+json.get("Address")+"\n电话:"+json.get("receievertel")
            textView.text=res
        }
    }
```

#### 3.5 子线程更新UI

子线程是不能更新ui的,toast也是不能使用的所以要将操作附着在ui线程上

```kotlin
runOnUiThread {
    Toast.makeText(
        this@Main3Activity,
        "包裹生成成功",
        Toast.LENGTH_LONG
    ).show()
}
```

#### 3.6 IDE里面包的路径问题

在android studio里面,有时候嵌套了多个包,但是IDE里面只会显示最里面那个包,比如想在com包里面新建别的包就不可能,这时只要在系统文件管理器里面新建就好了,因为包从文件管理的视角来看就是文件夹.



## 第九章  基于二维码的快递信息保护系统运行结果与测试分析

测试主要基于需求进行

1. 快递员注册

2. 登录

3. 扫描二维码

4. 通过扫码获得快递信息

5. 调整包裹加密等级

6. 发送包裹

    以上每一项需求经过测试,无异常情况.

## 第十章  结论与心得

经过接近20天的开发两个项目总算告一段落

两个app分别采用了webapp和android原生开发方式.其中一个采用了设计先行,另一个采用了代码先行的设计思路

其中让我印象深刻的点就是第一个开发了16天,第二个只开发了3天就基本完成了.尽管第一个的功能更多,但后来总结发现大部分时间都花在了设计以及完成设计的代码上(而不是功能的代码),比如挑一个配色就能花一个小时,写一个动画就写一上午等等.虽然美工耗去了大部分时间,但是结果显然也是更加令人满意的.

其中webapp方便调试,灵活性强,但可能需要做很多的客户端适配工作.

android原生app虽然没有第一种灵活,但胜于专事专做,专用的库和资料会相应的多一些.

这样掌握了两种开发方法,以后就可以做到游刃有余,尤其是webapp还为我积累了开发web的经验

webapp的开发效率如果不算各种兼容性的bug比原生要高很多,而且如此的前后端分离(web内容和安卓的壳)可以让我更轻松地修改调试功能(只需要修改服务器代码就可以),而不用每次在虚拟机上调试(更慢更麻烦)

(实验一)最令我头大的是架构设计的问题:

前端代码因css js html一大部分写到了一起,使得重构与阅读更加困难

第二点也是到了最后也没能重构一次代码,算是一个比较大的遗憾

还有一个比较大的槽点就是eclipse真的太难用了 (为什么默认编码是gbk而不是unicode,html+css+js没有高亮和补全,内置浏览器很烂等等),下次一定用idea.

在项目一中期,就将所有能开到的脑洞与大大小小的设计做了一个todo list,有些实现了,另一些限于时间的原因或者初期架构的问题未能完成

也算是一个完全的反思与总结了

todolist(第一个项目):

<img src="%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/image-20210109185836526.png" alt="image-20210109185836526" style="zoom:50%;" />

## Extras

### 早期设计(第一个项目)

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/IMG_4373(20201220-103759).PNG" alt="IMG_4373(20201220-103759)" style="zoom:25%;" /><img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/IMG_4372(20201220-103734).PNG" alt="IMG_4372(20201220-103734)" style="zoom: 50%;" />

![20201220_105209[00-00-00--00-00-05]](%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/20201220_105209%5B00-00-00--00-00-05%5D.gif)<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/543B84C76F18571719E0E12794C2C103.png" alt="543B84C76F18571719E0E12794C2C103" style="zoom: 50%;" />

### 不同设备上效果展示

![image-20201229215241379](%E5%A4%A7%E4%BD%9C%E4%B8%9A.assets/image-20201229215241379.png)

电脑web浏览器

<img src="%E5%BE%AE%E5%8D%9A%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.assets/localhost_8080_MicroBlog_blog.html(iPhone%206_7_8)%20(1).png" alt="localhost_8080_MicroBlog_blog.html(iPhone 6_7_8) (1)" style="zoom: 33%;" />

iphone 8

(p.s.这也是webapp的优点,"一次编写,处处运行")