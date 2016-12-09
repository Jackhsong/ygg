<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<title>砍价帮帮忙</title>
<link href="${rc.contextPath}/resources/bargain/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${rc.contextPath}/resources/bargain/styles.css?798">
<script src="${rc.contextPath}/pages/js/jquery-1.11.2.min.js"></script>
</head>
<body style="background:#f7eb03;">

<div class="box_a">
<img src="${rc.contextPath}/resources/bargain/images/top_a.jpg"   width="100%">
<div class="guizeB" onClick="document.getElementById('rules').style.display='block'; document.getElementById('fade').style.display='block';"><p>活动规则</p></div>
<div class="guizeA">
<p> 
	<#if isBargained ? exists>
		我已帮
		<span>
		${bargain.peopleBargain}
	</span>助力一次
	<#else>
		${bargain.peopleBargain}邀请你帮忙砍价
	</#if>
</p>
</div>
</div>

<!--价格 start-->
<div class="jia_lf">
<div class="jiage_lf"><p>当前价格</p></div><span>${bargain.currentPrice}</span>
</div>
<div class="jia_lf">
<div class="jiage_lf"><p>最低价格</p></div><span>1.00</span>
</div>
<!--价格 end-->

<div class="myclear"></div>

<!--按钮 start-->
<div class="mybuttom">
	<#if isSoldOut == "1">
		<div class="find_bt" > 商品售罄</div>
	<#elseif bargain.isBargain ? exists>
		<div class="find_bt" > 砍价结束</div>
	<#else>
		<div class="find_bt" id="cut_button" > 帮他砍价</div>
	</#if>

<#if isSoldOut == "1" >
	<div class="find_bt"> 商品售罄</div>
<#else>
	<a href="index"><div class="find_bt"> 我要参加</div></a>
</#if>
</div>
<!--按钮 end-->

<!--侧边按钮 start--> 
<div id="to_top" style="position:fixed;bottom:20%; right:0; width:50px; height:100px; background:#CCCCCC; opacity:0.5; border-radius:10px;">
<img onclick="location.reload()" src="http://www.cilu.com.cn/climg/activity/ico_new.png" width="100%"/>
<hr style="padding:0; margin:0;"/>
<img onclick="document.documentElement.scrollTop = document.body.scrollTop =0;" src="http://www.cilu.com.cn/climg/activity/ico_up.png" width="100%"/>
</div>
<!--侧边按钮 end--> 

<!--库存显示 start-->
<div class="jia_lf" style="text-align:center; font-size:1.3em; line-height:2.8em;font-weight:bold;color:#da1617; ">
<p>已抢成功&nbsp;: &nbsp;${soldNumber}</p>
</div>
<div class="jia_lf" style="text-align:center; font-size:1.3em; line-height:2.8em;font-weight:bold;color:#da1617;">
<p style="margin-left:-2%;">剩余库存&nbsp;: &nbsp;${leftNumber}</p>
</div>
<!--库存显示 end-->

<div class="myclear"></div>


<#if participantList ? exists>

<!--好友助力列表 start-->
<div class="helplist">
<div class="opendetails" style="width:100%; height:50px; line-height:50px; margin-top:20px;text-align:center;  font-weight:bold;"  onclick="location.reload()" ><h1>好友砍价详情<span style="color:#da1617;">&nbsp;点击刷新</span></h1>
<img style="margin-top:-15px;" src="http://www.cilu.com.cn/CiluActivity1/images/xiajiantou.png" width="40px;" height="40px;"></div>
<div class="myclear" style=" margin-top:20px;"></div>

<div class="mylist" style=" min-height:50px;max-height:200px; overflow-y:scroll;">
<ul>

<#list participantList as participant>

<li style="clear:both;margin-bottom:5px;">
<div class="helperimg">
	<#if participant.avatar ? exists>
		<img src="${participant.avatar}" width="50px" height="50px">
	<#else>
		<img src="${rc.contextPath}/resources/bargain/images/1_ico.png" width="50px" height="50px">
	</#if>
</div>
<div class="namenum">
	<div class="helpername">
		<#if participant.fullName ? exists>
			${participant.fullName}
		<#else>
			${participant.userUuid}
		</#if>
	</div>
	<div class="helpernum">已帮忙砍价<span>${participant.goodsPrice}</span>元</div>
</div>
</li>

</#list>

</ul>
</div>
</div>

</#if>

 <!--好友助力列表 end-->
 
 
 <div class="opendetails" style="width:100%; height:50px; line-height:50px; margin-top:20px;text-align:center;  font-weight:bold;"  onClick="document.getElementById('mydetails').style.display='block';"><h1>活动产品详情</h1>
<img style="margin-top:-15px;" src="http://www.cilu.com.cn/CiluActivity1/images/xiajiantou.png" width="40px;" height="40px;"></div>
<div class="mydetails" id="mydetails"  style=" width:90%; margin-left:5%;height:auto; margin-top:20px;">
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_01.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_02.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_03.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_04.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_05.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_06.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_07.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_08.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_09.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_10.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_11.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11b/goods11b_12.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_01.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_02.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_03.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_07.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_08.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_09.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_10.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_11.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_12.jpg"  width="100%"/>
<img src="http://www.cilu.com.cn/climg/images/goods11c/goods11c_13.jpg"  width="100%"/>
</div>
<br/><br/>
 
 <!--帮ta砍价 二维码弹窗 start-->
  <div id="fade" class="black_overlay" onClick="document.getElementById('mycode').style.display='none';document.getElementById('rules').style.display='none';  document.getElementById('fade').style.display='none';"></div> 
 <div class="rules" id="mycode"> 
 
 <img src="${qrCodeImg}" width="100%">
 长按识别二维码，开始砍价
 <p></p>
  
 </div>
 <!--帮ta砍价 二维码弹窗窗 end--> 
 

 <!--活动规则弹窗 start-->
  <div id="fade" class="black_overlay"></div> 
 <div class="rules" id="rules"> 
 <div class="rules_top">活动规则</div>
 <br/>
<p> 
1、活动时间：7月19日-7月21日</p>
<p>2、用户通过微信平台进入活动页面参与活动，每位用户只限领取一
   次。</p>
<p>3、活动发起人需要邀请好友为他砍价，原价57元，每次砍价金额随
   机，最终可以砍到1元（限量600瓶，先砍先得，砍完为止），也
   可以按当时实际砍到的金额购买，发起人的朋友可以帮他砍价也
   同时可以自己参与砍价活动。</p>
<p>4、活动结束后按付款时间的先后顺序发货。</p>
   <br/>
   <hr size="1" noshade="noshade" style="border:1px #cccccc dashed;"/>
 <div class="rules_bottom" onClick="document.getElementById('rules').style.display='none'; document.getElementById('fade').style.display='none';">知道了</div>
 </div>
 <!--活动规则弹窗 end--> 
 
 
 
 <!--砍价弹窗 start-->
  <div id="fade" class="black_overlay"></div> 
 <div class="myhit" id="myhit"> 
 <p> 太给力啦！</p>
 <p> 您帮好友成功砍价<span>1.5</span>元！</p>
 <div class="rules_bottom" onClick="document.getElementById('myhit').style.display='none'; document.getElementById('fade').style.display='none';">知道了</div>
 </div>
 <!--砍价弹窗 end--> 
 
<br/><br/><br/>
</body>
</html>
<script type="text/javascript">

  $("#cut_button").click(function(){

	if ("${isSoldOut}" == "1") {
	
		// 商品售空时不进行任何操作
	} else if ("${isInApp}" == "1") {
	
		 $.ajax({
                    type: "POST",
                    url: "${rc.contextPath}/bargain/participant/bargain?bargainerUuid=${accountId}",
                    data: {acountId:"${accountId}"},
                    success: function(response) {
                    	
                    	var data = JSON.parse(response);
             			console.log(data);
                     	
                     	if(data.code == "0"){
                     	
                     	// right  return
                     		location.href = location.href+"&d="+Math.random();
                     	} else if (data.code == "1") {
                     	
                     		alert("该好友已达最低价格")
                     	} else if (data.code == "2") {
                     	
                     		alert("您已经帮好友砍过价了")
                     	} else if (data.code == "3") {
                     	
                     	} else {
                     	
                     	}
                    },
                    error: function (){
                    	alert("net error");
                    }
                });
	} else {
	
		document.getElementById('mycode').style.display='block';
		document.getElementById('fade').style.display='block';
	}
  })
  

</script>
