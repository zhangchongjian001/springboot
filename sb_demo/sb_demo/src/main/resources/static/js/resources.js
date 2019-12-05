$(function(){
	$("#addResource").bind("click", function(){
		$("#resourceList").hide();
		$("#resourceEdit").show();
	});
	$("[name='editResource']").bind("click", function(){
        var resourceId = $(this).parents("tr").find("[name=resourceId]").text();
        var resourceName = $(this).parents("tr").find("[name=resourceName]").text();
        var resourceUri = $(this).parents("tr").find("[name=resourceUri]").text();
        var permission = $(this).parents("tr").find("[name=permission]").text();
        $.ajax({
        	url : "/account/roles/resource/" + resourceId,
        	type : "get",
        	contentType: "application/json",
        	success : function (data) {
        		$.each(data, function(i, item){
        			$("input[name='roleCheckbox'][value=" + item.roleId + "]")
        				.attr("checked","checked");
        		});
        	},
        	error : function (data) {
        		$("[name=messageDiv]").show();
        		$("[name=message]").html(data.message);
        	}
        });
        	 $("#resourceId").val(resourceId);
             $("#resourceName").val(resourceName);
             $("#resourceUri").val(resourceUri);
             $("#permission").val(permission);
             $("#resourceList").hide();
     		$("#resourceEdit").show();
	});

	$("#resourceSubmit").bind("click", function(){
		var resource = {};
		resource.resourceId = $("#resourceId").val();
		resource.resourceName = $("#resourceName").val();
		resource.resourceUri = $("#resourceUri").val();
		resource.permission = $("#permission").val();
		var roles = new Array();
		$.each($("input[name='roleCheckbox']"), function(){
			if(this.checked){
				var role = {};
				role.roleId = $(this).val();
				roles.push(role);
			}
		});
		resource.roles = roles;
		$.ajax({
			url : "/account/editresource",
			type : "post",
			contentType: "application/json",
			data : JSON.stringify(resource),
			success : function (data) {
				if (data) {
					alert("successful");
					location.href = "/account/resources";
				} else {
					alert("failed");
				}
			},
			error : function (data) {
				alert("ajax请求失败");
			}
		
	});
    });
});