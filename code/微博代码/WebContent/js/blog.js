var postBlog = function() {
	if (write == 0) {
		return false;
	}
	var btext = $(".bloginput").val();
	if (btext == "") {
		alert("-憋灌水啊你-");
		return false;
	}
	if (blogimgdone == 1) {
		alert("图片还在上传\\(0 0)/");
		return false;
	}
	var para = "btext=" + btext;
	var myRequest = new Request('/MicroBlog/BlogServlet?' + para, {
		credentials: 'include'
	});
	fetch(myRequest)
		.then((res) => res.text())
		.then(data => {
			if (data == "postsuccessful!") {
				alert("发布成功!!!");
				$(".bloginput").val("");
				update_blog();
				openwrite();
			} else if (data == "rats--and that was an error=_=") {
				alert("NO!出问题了啊+_<");
			}
		})
		.catch(err => console.log(err));
}
var openwrite = function() {
	if (animate_done == 1) {
		animate_done = 0;
		if (write == 0) {
			write = 1;
			$("#card5").offset({
				"top": t
			});
			$(".write-card")
				.addClass(
					"animated bounceInDown1");
			$(".write-card")
				.addClass(
					"animated bounceInDown1");
			setTimeout(
				function() {
					$(".write-card")
						.removeClass(
							"animated bounceInDown1");
					$("body").addClass("body3");
					animate_done = 1;
				}, 1000);
		} else {
			write = 0;
			$(".write-card")
				.addClass(
					"animated bounceOutDown1");
			setTimeout(
				function() {
					$(".write-card")
						.removeClass(
							"animated bounceOutDown1");
					$("body").removeClass("body3");
					$("#card5").offset({
						"top": -(t2 - t + 140)
					});
					$("#card5").animate({
						top: -(t2 - t + 140) + "px"
					},
						100
					);
					animate_done = 1;
				}, 1000);
		}
	}
}
var t = 0;
var t2 = 0;
var isimg = 0; //博客图片打开了吗
var blogpic = 0;
var placewrite = function() {
	t = $(".write-card").offset()['top'];
	t2 = $(".postbutton").offset()['top'];
	$("#card5").offset({
		"top": -(t2 - t + 70)
	});
}
var changeblogimg = function() {
	if (isimg == 0 && animate_done == 1) {
		animate_done = 0;

		$(".img-card").css("display", "inline");
		document.getElementsByClassName("img-card")[0].style.dispaly = "inline";
		$(".img-card")
			.addClass(
				"animated slideInLeft");
		setTimeout(
			function() {
				$(".img-card")
					.removeClass(
						"animated slideInLeft");
				animate_done = 1;
				isimg = 1;
			}, 400)

	} else if (isimg == 1 && animate_done == 1) {
		animate_done = 0;

		$(".img-card")
			.addClass(
				"animated slideOutRight");
		setTimeout(
			function() {
				$(".img-card")
					.removeClass(
						"animated slideOutRight");

				$(".img-card").css("display", "none");
				isimg = 0;
				animate_done = 1;
			}, 400)
	}
}
var reseticon = function() {
	$(".icondrag").animate({
		top: "-30%"
	},
		50
	);


}
var iconup = 1; //icon是不是在上面
var pullicon = function(flag) { //收起或者展开下拉标签
	//if (animate_done == 1) { 
	//animate_done = 0;
	if (flag == 1) {
		if (iconup == 1) {//下拉
			$(".icondrag").css("display", "inline");
			//document.getElementsByClassName("icondrag")[0].style.dispaly = "inline";
			$(".icondrag")
				.removeClass(
					"pendulum");
			$(".icondrag")
				.addClass(
					"animated dropin");
			setTimeout(
				function() {
					$(".icondrag")
						.removeClass(
							"animated dropin");
					$(".icondrag")
						.addClass(
							"pendulum");
					//animate_done = 1;
					iconup = 0;
				}, 1000)

		}
	} else {
		if (iconup == 0) { //拉上去
			$(".icondrag")
				.removeClass(
					"pendulum");
			$(".icondrag")
				.addClass(
					"animated bounceOutUp");
			setTimeout(
				function() {
					$(".icondrag")
						.removeClass(
							"animated bounceOutUp");
					$(".icondrag").css("display", "none");
					iconup = 1;
					//animate_done = 1;
				}, 1000)
		}
	}
	// }


}
$(document).ready(function() {
	$(".write-card").draggable({
		axis: "y",
	});
	$(".write-card").droppable();
	$("#tomyblog").draggable();
	$("#tomyblog").droppable();
	$(".write-card").on("dragstop", function(event, ui) {
		openwrite();
	});
	$(".icondrag").draggable({
		axis: "y"
	});
	$(".icondrag").droppable();
	$(".icondrag").on("dragstop", function(event, ui) {
		reseticon();
		changeblogimg();
	});
    /*
    $(".img-card").draggable({
        axis: "y"
    });
    $(".img-card").droppable();
 
    $(".icondrag").on("dragstart", function (event, ui) {
        reseticon();
        changeblogimg();
    });
       */
	$(".img-card").css("display", "none");
	$(".icondrag").css("display", "none");
});
var blogimgdone = 0;

function image2Base64(img) { //转base64的方法
	var canvas = document.createElement("canvas");
	canvas.width = img.width;
	canvas.height = img.height;
	var ctx = canvas.getContext("2d");
	ctx.drawImage(img, 0, 0, img.width, img.height);
	var dataURL = canvas.toDataURL("image/png"); //规定图片是什么格式，image/格式
	return dataURL;
}

function uploadPicFile(file) {
	blogimgdone = 1;
	$.ajax({
		url: '/MicroBlog/UploadBlogimgServlet',
		type: 'POST',
		data: "file=" + file,
		async: true,
		success: function(data) {
			console.log(data);
			if (data == "picuploadsuccess") {
				blogimgdone = 0;
			} else {
				alert("啊咧咧,头像上传失败");
			}
		}
	});
}

function showImg(file) { //获取图片路径赋给img标签，从而展示出所选择的图片
	if (!file.files || !file.files[0]) {
		return;
	}
	var image = new Image();
	image.src = window.URL.createObjectURL(file.files[0]);
	image.onload = function() {
		var base64 = image2Base64(image);
		uploadPicFile(base64);
	}
}