<!DOCTYPE html>
<html>
  	<head>
	<title>心动慈露 用户登陆</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${rc.contextPath}/pages/css/bootstrap.min.css" rel="stylesheet">
	<link href="${rc.contextPath}/pages/css/styles.css" rel="stylesheet" type="text/css">

	</head>
    <body>
	<img class="img-responsive" src="${rc.contextPath}/pages/images/banner_login.png"  />
	<div class="container" >  
	<form class="form-horizontal" role="form1" id="form1" name="form1" action="" method="post">
		<div class="login">
	   		<div class="login_lf">
	    		<img src="${rc.contextPath}/pages/images/ico_phone.png" width="32" height="40"/>
	    	</div>
	    
	    	<div class="login_lm" >
	      		<input type="text" class="form-control"  id="myphone" placeholder="手机号">
	    	</div>
	    
	     	<div class="login_lr" id="check1"> 获取验证码</div>
	        <div class="login_lr" id="check2"  style="display:none">再次发送</div>
  		</div>
  
  
 
  		<div class="login" >
   			<div class="login_lf">
    			<img src="${rc.contextPath}/pages/images/ico_lock.png" width="32" height="40"/>
    		</div>
    
    		<div class="login_lm" >
      			<input type="text" class="form-control" name="smsverify"  id="vfy"  maxlength="6" placeholder="验证码">
    		</div>
 
  		</div>
  
  
  		<div class="form-group1">
      		<button type="button" class="btn btn-default" >登&nbsp;&nbsp;&nbsp;陆</button>
  		</div>
  		<input type="hidden"  name="sendnumberString" id="sendnumberString" value="${(sendnumberString)!("0")}"/> 
  		<input type="hidden"  name="sendMessagePhone" id="sendMessagePhone" value="${(sendMessagePhone)!("0")}"/> 
	</form>
    </div>  
      
    <script src="https://code.jquery.com/jquery.js"></script>
	<script src="${rc.contextPath}/pages/js/bootstrap.min.js"></script>
	
	<script>
	
	var sendtimer=null;  
	
	//初次发送验证码
	$('#check1').click(function(){
		var phone = $("#myphone").val().trim();
		if(phone && /^1[3|4|5|8]\d{9}$/.test(phone)){
			
			$.ajax({
			   url:"${rc.contextPath}/userPhoneBindController/sendsms",
			   data:{"phone":phone},
			   type:"post",
			   dataType:"json",
			   success:function(data){
			       var sendnumberString=data.sendnumberString;
			       var sendMessagePhone=data.sendMessagePhone;
			       $("#sendnumberString").val(sendnumberString);
			       $("#sendMessagePhone").val(sendMessagePhone);
			       
			       $("#check1").hide();
			       $("#check2").show();
			       $("#check2").html('<span class="second">60</span>s后重发');
			       if(sendtimer!=null){
			            clearInterval(sendtimer);
			       }
			       var timer=60;
			       var sid="#check2";
			       sendtimer=setInterval(function(){
			           timer--;
			           sendCdown(timer,sid);
			       },1000);
			   },
			   error:function(){
			      alert("发送验证码失败，请稍后重试！");
			   }
			});
		}else{
			alert("请输入正确的手机号码！");
		}
	});
	 
	 
	 //计算60秒时间
	 function sendCdown(time,id){
	    var second_elem = $(id).find('.second');
		var end_time = time;
		if (end_time > 0) {			
			var second = Math.floor(end_time % 60);			
			$(second_elem).text(second<10?'0'+second:second);//计算秒数
		} else {
			//时间到达后执行操作		
			$('#check2').text('再次发送');
			clearInterval(sendtimer);	
		}
	 };
	 
	 
	  
	 //再次发送验证码
	 $('#check2').click(function(){
		var phone = $("#myphone").val().trim();
	    if(phone && /^1[3|4|5|8]\d{9}$/.test(phone)){

			$.ajax({
			    url:"${rc.contextPath}/userPhoneBindController/sendsms",
			    data:{"phone":phone},
			    type:"post",
			    dataType:"json",
			    success:function(data){
			       var sendnumberString=data.sendnumberString;
			       var sendMessagePhone=data.sendMessagePhone;
			       $("#sendnumberString").val(sendnumberString);
			       $("#sendMessagePhone").val(sendMessagePhone);

			       if(sendtimer!=null){
				       clearInterval(sendtimer);
				   }
			       $('#check2').html('<span class="second">60</span>s后重发');
				   var etime=60;
				   var sid="#check2";
				   sendtimer = setInterval(function(){	
					   etime--;					  
					   sendCdown(etime,sid);
				   },1000);
			    },
			    error:function(){
			       alert("验证码发送失败！");
			    }			    
			});							
         }else{
			alert("请输入正确的手机号码！");
		 }
	}); 
	  
	  
	  
	<!--点击登陆进行登陆-->
	$(".btn-default").click(function(){
	    var phone = $("#myphone").val().trim();
	    var code = $("#vfy").val().trim();
	    var sendnumberString=$("#sendnumberString").val();
	    var sendMessagePhone=$("#sendMessagePhone").val();
	    if(phone==""||code==""){
		    alert("您的输入有误！");
		    return false;
		}else{
		      <!--判断发送验证码的手机和最后登陆的手机是否一样-->
		      if(phone==sendMessagePhone){
				    if(sendnumberString==code){
				        
				        $.ajax({
				            url:"${rc.contextPath}/userPhoneBindController/tocheckphone",
				            type:"post",
				            data:{"phone":phone},
				            dataType:"json",
				            success:function(data){
				               var type=data.type;
				               if(type==1){
				                   alert("您的手机号未绑定！");
				                   return false;
				               }else if(type==2){
				                   alert("登陆成功！");
				                   document.form1.action="${rc.contextPath}/user/tologin" ;
						           document.form1.submit(); 
				               }
				            },
				            error:function(){
				                 alert("登陆失败！");
				            }		        
				        }); 
				    }else{
				       alert("验证码错误！");
				       return false;	       
				    }
		      }else{
			        alert("手机或验证码错误！");
			        return false;
		      }
   
		}
	});
	  
	</script>

	</body>
</html>