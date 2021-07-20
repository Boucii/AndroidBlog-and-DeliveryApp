loginname = function () {
	var username = $("#username").val();
	var pwd = $("#pwd").val();
	var para1 = "username=" + username + "&password=" + pwd;
	var para=encodeURI(para1);
	if (username.length == 0) {
		alert("请输入用户名!");
		return false;
	}
	if (pwd.length == 0) {
		alert("请输入密码!");
		return false;
	}
	if (pwd.length < 5) {
		alert("输入正确的密码!");
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
				alert("用户名或密码出错!");
			} else if (data == "innerproblem") {
				alert("服务器错误");
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
			alert("请输入用户名!");
			return false;
		}
		if (pwd.length == 0) {
			alert("请输入密码!");
			return false;
		}
		if (pwd.length < 5) {
			alert("密码太短!");
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
					alert("注册成功!");
					window.location.href = (loc);
				} else if (data == "duplicatedname") {
					alert("用户名已存在!");
				} else if (data == "innerproblem") {
					alert("服务器错误");
				}
			})
			.catch(err => console.log(err));
	} else if (portraitdone == 3) {
		alert("头像上传失败,再传一次呀!");
	}else if (portraitdone == 1) {
		alert("头像上传ing!hooold");
	}else if (portraitdone == 0) {
		alert("!请先上传头像!");
	}
}