loginname = function () {
	var username = $("#username").val();
	var pwd = $("#pwd").val();
	var para1 = "username=" + username + "&password=" + pwd;
	var para=encodeURI(para1);
	if (username.length == 0) {
		alert("�������û���!");
		return false;
	}
	if (pwd.length == 0) {
		alert("����������!");
		return false;
	}
	if (pwd.length < 5) {
		alert("������ȷ������!");
		return false;
	}
	var myRequest = new Request('/MicroBlog/LoginServlet?' + para, {
		credentials: 'include',
	});
	fetch(myRequest)
		.then((res) => res.text())
		.then(data => {
			if (data == "loginsuccess!") {
				var path = window.location.href;
				var i = path.lastIndexOf("/") + 1;
				var loc = path.substring(0, i).concat("blog.html");
				window.location.href = (loc);
				//window.location.href = "http://127.0.0.1:8080/MicroBlog/blog.html";
				//window.location.href = ("www.baidu.com");
			} else if (data == "namenotfound") {
				alert("�û������������!");
			} else if (data == "innerproblem") {
				alert("����������");
			}
		})
		.catch(err => console.log(err));
}
registername = function () {
	if (portraitdone == 2) {
		var username = $("#register-username").val();
		var pwd = $("#register-pwd").val();
		var para = "username=" + username + "&password=" + pwd;
		if (username.length == 0) {
			alert("�������û���!");
			return false;
		}
		if (pwd.length == 0) {
			alert("����������!");
			return false;
		}
		if (pwd.length < 5) {
			alert("����̫��!");
			return false;
		}
		var myRequest = new Request('/MicroBlog/RegisterServlet?' + para, {
			credentials: 'include'
		});
		fetch(myRequest)
			.then((res) => res.text())
			.then(data => {
				if (data == "registersuccess!") {
					var path = window.location.href;
					var i = path.lastIndexOf("/") + 1;
					var loc = path.substring(0, i).concat("blog.html");
					alert("ע��ɹ�!");
					window.location.href = (loc);
				} else if (data == "duplicatedname") {
					alert("�û����Ѵ���!");
				} else if (data == "innerproblem") {
					alert("����������");
				}
			})
			.catch(err => console.log(err));
	} else if (portraitdone == 3) {
		alert("ͷ���ϴ�ʧ��,�ٴ�һ��ѽ!");
	}else if (portraitdone == 1) {
		alert("ͷ���ϴ�ing!hooold");
	}else if (portraitdone == 0) {
		alert("!�����ϴ�ͷ��!");
	}
}