$(function(){
	//验证码验证
	$("#code").blur(function(){
		var codeStr = $("#code").val();
		
		if(codeIsError()){
			console.log("验证失败");
		}else{
			console.log("验证成功");
		}
	});
});
//---------------------------------------------------------------------
//检查验证码是否正确
//---------------------------------------------------------------------
function changeCode(){
	//修改验证码
	$("#captcha_img").attr('src','/captcha?id='+uuid());
}
//-------------------------------------------------------------------------------------------
//生成UUID
//-------------------------------------------------------------------------------------------
function uuid(){
	//获取系统当前的时间
	var d = new Date().getTime();
	//替换uuid里面的x和y
	var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	  //取余 16进制
	  var r = (d + Math.random()*16)%16 | 0;
	  //向下去整
	  d = Math.floor(d/16);
	  //toString 表示编程16进制的数据
	  return (c=='x' ? r : (r&0x3|0x8)).toString(16);
	});
	return uuid;
};
//---------------------------------------------------------------------
//检查验证码是否正确
//---------------------------------------------------------------------
function codeIsError(){
	var error = true;
	var codeStr = $("#code").val();
	if(codeStr == ""){
		setCodeInfo(error,"验证码不能为空");
		return error;
	}
	//请求地址，你们最好注意一下，这个地方可能报错需要修改，
	$.ajax({  
        type : "post",  //使用提交的方法 post、get
        url : contextPath()+"/chkCode",   //提交的地址
        data : { code:$("#code").val() },  //数据
        async : false,   //配置是否
        dataType:"json",//返回数据类型的格式
        success : function(data){  //回调操作
          console.log(data);
          error = data.error;
          
          setCodeInfo(error,data.msg);
        }  
	});
	return error;
}
//设定验证码的错误提示消息
function setCodeInfo(error,msg){
	if(error){
		$("#code_str").html("<font color='red'>"+msg+"</font>");
	}else{
		$("#code_str").html("<font color='blue'>"+msg+"</font>");
	}
}
// 获取到当前项目的名称
var contextPath = function() { 
	var path = "/" + location.pathname.split("/")[1]; 
	//当项目的目录是根目录的情况
	if(path == "/login"){
		return "";
	}else{
		return path;
	}
}
