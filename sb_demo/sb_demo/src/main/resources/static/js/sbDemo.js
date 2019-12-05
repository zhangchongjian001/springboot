$(function(){
	
	$("[id=button01]").bind("click",function(){
		cheakUserName();
	});
	
	$("[id=updatePassword]").bind("click",function(){
		$("#infoList").hide();
		$("#infoEdit").show();
		var userName=$("#userName").val();
		var password=$("#password").val();
	});
	$("[id=updatePassword]").bind("click",function(){
	});
	
	$("[name=userEdit]").bind("click",function(){
		var userId=$(this).parents("tr").find("[name=userId]").text();
		var	userName=$(this).parents("tr").find("[name=userName]").text();
		var password=$(this).parents("tr").find("[name=password]").text();
		$.ajax({
			type:"get",
			url:"/account/role/user/"+userId,
		success:function(data){
			$.each(data, function(i, item){
				$("input[name='roleCheckbox'][value=" + item.roleId + "]")
					.attr("checked","checked");
			});
		},
		error:function(data){
			$("[name=messageDiv]").show();
			$("[name=message]").html(data.message);
		}
		});
        $("#userId").val(userId);
        $("#userName").val(userName);
        $("#userList").hide();
		$("#userEdit").show();
	})
	$("[id=userSubmit]").bind("click",function(){
		alert("绑定成功");
	
		var user = {};
		user.userId = $("#userId").val();
		var roles = new Array();
//		绑定到多选框。判断选项是否背选中，若选中则将角色信息装到列表中
		$.each($("input[name='roleCheckbox']"), function(){
			if(this.checked){
				var role = {};
				role.roleId = $(this).val();
				roles.push(role);
			}
		});
		user.roles = roles;
		$.ajax({
			url : "/account/updateuser",
			type : "put",
			contentType: "application/json",
			data : JSON.stringify(user),
			success : function (data) {
				if (data.status == 200) {
					location.href = "/account/userPage/1";
				} else {
					$("[name=messageDiv]").show();
					$("[name=message]").html(data.message);
				}
			},
			error : function (data) {
				$("[name=messageDiv]").show();
				$("[name=message]").html(data.message);
			}
		});
	})
	/**
	 * User
	 * 跳转分页页面
	 */
	$("#selectPage").bind("click", function() {
		var pageNum = $("input[name=pageNum]").val();
		var s = $("span[id=pages]").text();
		var pages= parseInt(s.replace(/[^0-9]/ig,""));
		//正则表达式匹配正整数
		var re = /^[1-9]+[0-9]*]*$/;
		if (!re.test(pageNum)) {
			alert("is  NaN")
			return;
		}else if (pageNum > pages) {
			alert("has no page");
			return;
		}else{
			location.href="/account/userPage/"+pageNum;
		}
		
	})
})
function cheakUserName(){
	var userName=$("[name=userName]").val();
	$.ajax({
		type:"post",
		url:"/account/checkUserName",
		data:{userName:userName},
		success:function(data){
			alert(data);
			if(data){
				alert("用户名已存在，请重新输入");
			}else{
				
			}
		},
		error:function(){
			alert("ajax请求失败");
		}
		
	})
}
function register(){
	var userName=$("[name=userName]").val();
	var password=$("[name=password]").val();
	$.ajax({
		type:"post",
		url:"/account/registerCkeak",
		data:{
			userName:userName,
			password:password
		},
		success:function(data){
			alert(data);
			location.href="/account/dashboard";
		},
		error:function(){
			alert("ajax请求失败")
		}
	})
}
function doLogin(){
	
	var userName=$("[name=userName]").val();
	var password=$("[name=password]").val();
	$.ajax({
		type:"post",
		url:"/account/dologin",
		data:{
			userName:userName,
			password:password
		},
		success:function(data){
			if(data){
				location.href="/account/dashboard";
			}else{
				alert("登录失败，请检查你的用户名和密码输入是否正确");
			}
		},
		error:function(){
			alert("ajax请求失败")
		}
	})
}

function deleteUser(userId){
	alert(userId);
	var t=$("#tr"+userId);
	$.ajax({
		type:"Delete",
		url:"/account/deleteUser/"+userId,
		success:function(data){
			if(data){
				t.remove();
				alert("删除成功");
			}else{
				alert("删除失败");
			}
		},
		error:function(){
			alert("ajax请求失败")
		}
	})
}



