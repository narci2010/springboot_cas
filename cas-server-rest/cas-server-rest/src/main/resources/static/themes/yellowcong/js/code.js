$(function(){
	// 验证码验证
	$("#code").blur(function(){
		var codeStr = $("#code").val();

		if(codeIsError()){
			console.log("验证失败");
			$("#codeValid").val("false");
		}else{
			//验证码设定为 false
			$("#codeValid").val("true");
			console.log("验证成功");
		}
	});
	
	//默认开始禁用按钮的
//	disable("btn_login",true);
});

//验证数据是否正确
function checkData(){
	
	//验证码检查
	if( $("#captcha_sec").attr("style").indexOf("block") > -1 && $("#codeValid").val() == "false"){
		setCodeInfo("info",true,"验证码有问题");
		return false;
	}
	
	//用户名检查
	var username = $("input[name='username']").val();
	var password = $("input[name='password']").val();
	if(stringIsEmpty(username) ){
		setCodeInfo("info",true,"用户不能为空");
		return false;
	}
	if(stringIsEmpty(password)){
		setCodeInfo("info",true,"密码不能为空");
	}
	
	return true;
}
//判断字符串是否为空
function stringIsEmpty(val){
	if(val == undefined || val == null || val.replace(/(^\s*)|(\s*$)/g, "") == ""){
		return true;
	}
	return false;
}
//登录到服务器
function login(){
	//更新登录次数
	updateLoginCnt();
	
	//判断按钮是否可用
	/*if(isDisable("btn_login")){
		return false;
	}*/
	
	if(!checkData()){
		return false;
	}
	
	//发送数据到服务器
	var loginSuccess = postData();
	//更新登录条数
	updateLoginCnt(loginSuccess);
	
}
//更新登录失败的次数
function updateLoginCnt(loginSuccess){
	//密码登录失败次数
	var loginCnt = Number($("#loginCnt").val());
	
	//当大于等于3次的时候，显示密码
	if(loginCnt >=3 && !loginSuccess){
		$("#captcha_sec").css("display","block");
	}
	
	//最后增加登录次数
	$("#loginCnt").val(loginCnt+1);
}
//按钮不可使用
function disable(elementId, isDisabled){
	if(isDisabled){
		//添加样式
		$("#"+id).addClass("btn_disable");
	}else{
		//移除样式
		$("#"+id).removeClass()("btn_disable");
	}
}
//判断按钮是否可用
function isDisable(elementId){
	var cls = $("#"+elementId).attr("class");
	if(cls.indexOf("btn_disable") > -1){
		return true;
	}else{
		return false;
	}
}
//获取目标站点的地址
function getTargetPath(){
	var url = window.location.href;
	//解码url
	return unescape(url.substr(url.indexOf("service=")+8));
}
//发送请求到服务器
function postData(){
	var result = false;
	//发送请求到后台
	$.ajax({  
	          type : "post",  // 使用提交的方法 post、get
	          url : contextPath()+"/login",   // 提交的地址
	          data : { 
				 username:$("input[name='username']").val(),
				 password:$("input[name='password']").val(),
				 execution:$("input[name='execution']").val(),
				 _eventId:"submit",
				 geolocation:"",
				 submit:"登录"
			  },  // 数据
	          async : false,   // 配置是否异步操作
	          success : function(data, textStatus,xhr){  // 回调操作
	        	  //用户名和密码不能为空的情况错误
	        	  var msg = $(data).find("#errors").find("span").html();
	        	  setCodeInfo("info",true,msg);
                  console.log("登录失败");
		      },  error: function(xhr, textStatus, errorMsg){
			        switch(xhr.status){
			     	case 302:
			     		var tagetUrl = getTargetPath();
			     		//用命名活密码没有填写的情况
			     		setCodeInfo("info",true,"登录成功,马上条撞到目标网站："+tagetUrl);
			     		
			     		//只有这个地方，才是登录成功的。
			     		result = true;
			     		//跳转服务
			     		window.location.href=tagetUrl;
			     		break;
			     	case 401:
			     		//验证失败的情况
			     		setCodeInfo("info",true,"用户名和密码有问题");
			     		break;	
			     	}
		      }
	});
	 return result;
}
//---------------------------------------------------------------------
//显示消息用
//---------------------------------------------------------------------
function showMessage(code,data){
	var msg = $(data).find("#errors").find("span").html();
	switch(code){
	case 200:
		//用命名活密码没有填写的情况
		console.log(msg);
		break;
	case 401:
		//验证失败的情况
		console.log(msg);
		break;	
	}
}
// ---------------------------------------------------------------------
// 检查验证码是否正确
// ---------------------------------------------------------------------
function changeCode(){
	// 修改验证码
	$("#captcha_img").attr('src','/captcha?id='+uuid());
	
	//验证码设定为 false
	$("#codeValid").val("false");
	//清空以前的验证码
	$("#code").val("");
	//清空提示消息
	$("#code_str").val("");
}
// -------------------------------------------------------------------------------------------
// 生成UUID
// -------------------------------------------------------------------------------------------
function uuid(){
	// 获取系统当前的时间
	var d = new Date().getTime();
	// 替换uuid里面的x和y
	var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	  // 取余 16进制
	  var r = (d + Math.random()*16)%16 | 0;
	  // 向下去整
	  d = Math.floor(d/16);
	  // toString 表示编程16进制的数据
	  return (c=='x' ? r : (r&0x3|0x8)).toString(16);
	});
	return uuid;
};
// ---------------------------------------------------------------------
// 检查验证码是否正确
// ---------------------------------------------------------------------
function codeIsError(){
	var error = true;
	var codeStr = $("#code").val();
	if(codeStr == ""){
		setCodeInfo("code_str",error,"验证码不能为空");
		return error;
	}
	// 请求地址，你们最好注意一下，这个地方可能报错需要修改，
	$.ajax({  
        type : "post",  // 使用提交的方法 post、get
        url : contextPath()+"/chkCode",   // 提交的地址
        data : { code:$("#code").val() },  // 数据
        async : false,   // 配置是否
        dataType:"json",// 返回数据类型的格式
        success : function(data){  // 回调操作
          console.log(data);
          error = data.error;
          
          setCodeInfo("code_str",error,data.msg);
        }  
	});
	return error;
}
// 设定验证码的错误提示消息
function setCodeInfo(elementId,error,msg){
	if(error){
		$("#"+elementId).html("<font color='red'>"+msg+"</font>");
	}else{
		$("#"+elementId).html("<font color='blue'>"+msg+"</font>");
	}
}
// 获取到当前项目的名称
var contextPath = function() { 
	var path = "/" + location.pathname.split("/")[1]; 
	// 当项目的目录是根目录的情况
	if(path == "/login"){
		return "";
	}else{
		return path;
	}
}
