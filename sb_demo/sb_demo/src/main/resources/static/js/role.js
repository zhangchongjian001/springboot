$(function(){
	$("[id=addRole]").bind("click",function(){
		$("#roleList").hide();
		$("#roleEdit").show();
				
	});
	$("[id=roleSubmit]").bind("click",function(){
		var role = {};
		role.roleId=$("#roleId").val();
		role.roleName=$("[id=roleName]").val();
		$.ajax({
			type:"post",
			url:"/account/editrole",
			contentType: "application/json",
			data : JSON.stringify(role),
			success:function(data){
				alert("successful");
				location.href="/account/roles";
			},
			error:function(){
				alert("ajax请求失败");
			}
		});
	});
	
	$("[name='editRole']").bind("click", function(){
        var roleId = $(this).parents("tr").find("[name=roleId]").text();
        var roleName = $(this).parents("tr").find("[name=roleName]").text();
        $("#roleId").val(roleId);
        $("#roleName").val(roleName);
        $("#roleList").hide();
		$("#roleEdit").show();
	});
})

function deleteRole(roleId){
	var t=$("#tr"+roleId);
	$.ajax({
		type:"Delete",
		url:"/account/deleterole/"+roleId,
		success:function(data){
			if(data){
				t.remove();
				alert("删除成功");
			}else{
				alert("删除失败");
			}
		},
		error:function(){
			alert("ajax请求失败");
		}
	})
}