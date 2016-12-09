<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>砍价帮帮忙</title>
<link href="${rc.contextPath}/resources/bargain/styles.css" rel="stylesheet" type="text/css" />
<script src="${rc.contextPath}/resources/bargain/js/jquery-1.11.0.min.js"></script>
<script src="${rc.contextPath}/resources/bargain/js/jquery.lazyload.js"></script>
<script>
 function mycut(){
	 
	 var concerns=true;
	 
	 if (${notSubscribe} == "1") {
	 	concerns=false
	 }
	 
	 if(concerns==false){
	 
	 	window.location.href ="qrcode?qrCodeImg=${qrCodeImg}"
	 	
	 } else {
	 
	 	 $.ajax({
                    type: "POST",
                    url: "${rc.contextPath}/bargain/participant/bargain?bargainerUuid=${accountId}",
                    data: {acountId:"${accountId}"},
                    success: function(response) {
                    	
                    	var data = JSON.parse(response);
             			console.log(data);
                     	
                     	if(data.code == "0"){
                     	
                     	// right  return
                   			document.getElementById('myhit').style.display='block'; document.getElementById('fade').style.display='block';
                   			$('#asyncPrice').html(data.price);
                     	// location.href = location.href+"&d="+Math.random();
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
	 }
	};

</script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    var appId = "${jsSdk.appId?default("")}";
    var timestamp = "${jsSdk.timestamp?default("")}";
    var noncestr = "${jsSdk.noncestr?default("")}";
    var signature = "${jsSdk.signature?default("")}";
    var sharePath = "http://weixin.cilu.com.cn/ygg-hqbs/bargain/activity_b?accountId=${accountId}";
    var shareImage = "http://www.cilu.com.cn/climg/images/goods6/goods6_a.jpg";
    var shareTitle="来吧，互相伤害！砍我一下，我要1元拿走它！";
    var shareContent="全家必备慈露本草爽肤水，防蚊驱蚊、祛痱止痒，等你来助力！";
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: appId, // 必填，公众号的唯一标识
    timestamp: timestamp, // 必填，生成签名的时间戳
    nonceStr: noncestr, // 必填，生成签名的随机串
    signature: signature,// 必填，签名，见附录1
    jsApiList: ['onMenuShareAppMessage','onMenuShareTimeline','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone','hideMenuItems','showMenuItems']
});	
wx.ready(function(){ 
		wx.hideMenuItems({
    		menuList: ['menuItem:copyUrl','menuItem:share:qq','menuItem:share:email'
        ,'menuItem:openWithSafari','menuItem:favorite','menuItem:readMode'
        ,'menuItem:share:QZone'
        ,'menuItem:share:qq'
        ,'menuItem:share:weiboApp'
        ] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
		});
		wx.onMenuShareAppMessage({
    	   title: shareTitle, // 分享标题
           desc: shareContent, // 分享描述
           link: sharePath, // 分享链接
           imgUrl: shareImage, // 分享图标
           success: function () { 
                 // 用户确认分享后执行的回调函数
            },
           cancel: function () { 
               // 用户取消分享后执行的回调函数
            },
           fail: function (res) { 
           }
		});
      	wx.onMenuShareTimeline({
             title: shareTitle, // 分享标题
             link: sharePath, // 分享链接
             imgUrl: shareImage, // 分享图标
             success: function () { 
                   // 用户确认分享后执行的回调函数
               },
             cancel: function () { 
                  // 用户取消分享后执行的回调函数
             },
            fail: function (res) {  
            }
		});
	});
	
	wx.error(function(res){
	});
</script>
<link rel="stylesheet" href="${rc.contextPath}/resources/bargain/css/swiper.min.css">
</head>
<body style="background:#f6ebd9;">
<div class="box_a">
<img src="${rc.contextPath}/resources/bargain/images/goods6c_01.jpg" width="100%">
<div class="guize" onClick="document.getElementById('rules').style.display='block'; document.getElementById('fade').style.display='block';"><p>查看详情</p></div>
</div>

<div class="box2">
<!--价格 start-->
<div class="jia_lf">产品原价:<span>87.00</span>
</div>
<div class="jia_lf">
<span style="font-size:1.1em;">当前价格:</span><span id="nowprice">${bargain.currentPrice}</span>
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
		<div class="find_bt" id="cut_button" onClick="mycut()" > 帮他砍价</div>
	</#if>

	<a href="activity_a"><div class="find_bt"> 我要参加</div></a>
</div>
<!--按钮 end-->

<!--库存显示 start-->
<div class="jia_lf">已抢成功:<span>${soldNumber}</span>
</div>
<div class="jia_lf">
剩余库存:<span id="surplus">${leftNumber}</span>
</div>
<!--库存显示 end-->
<div class="myclear"></div>

<!--跑马灯文字 start-->
<div style="width:100%; height:30px;  padding:5% 0;">
<div id="andyscroll2">  
<div id="scrollmessage">  
<ul>  

<#if lessThanThirty ? exists>

<!--砍到 20——30 start-->
<li id="condition3" style="display:none;"><marquee direction=left scrollamount=3> <a style="width:100%">机会万年难得！犹豫1秒就错过啦，立即下单！</a><a style="margin-left:50%">买买买！马上买！这个价格值哭了！<a style="margin-left:50%">库存所剩无几，努力这么久，下单就赢了！</a>
<a style="margin-left:50%">便宜都被你占啦！不下单就是耍流氓！</a>
<a style="margin-left:50%">啥也别说了，马上下单下单下单，马上得到得到得到！</a></marquee> </li> 
<!--砍到20——30 end--> 

<#elseif lessThanHundred ? exists>

<!--大于等于150个已抢 start-->
<li id="condition2" style="display:none;"><marquee direction=left scrollamount=3> <a style="width:100%">加油加油，库存不多啦，马上去下单！</a><a style="margin-left:50%">要哭了，已经这么便宜啦，快下单吧！<a style="margin-left:50%">还不下单？世界上最痛苦的事情莫过于1元砍到了，但是没货啦！</a>
<a style="margin-left:50%">现价就是最低价，买到就是赚翻！数量不多，时间有限，错过就没啦！</a>
<a style="margin-left:50%">已经有<span style="color:#f54d4d;">${bargainerNumber}</span>人参与砍价，人品不够魄力来凑！下单就是要快！</a></marquee> </li> 
<!--大于等于150个已抢 end-->  

<#else>

<li id="condition1"><marquee direction=left scrollamount=3> <a style="width:100%">哎哟不错哦，您真会砍！邀请更多好友助力砍价吧！</a><a style="margin-left:50%">好厉害！居然砍了这么多！邀请更多好友还可以砍更多！<a style="margin-left:50%">主银，我等着急了，邀请更多好友砍价就能把我带走啦！</a>
<a style="margin-left:50%">还差一点点，赶快邀请更多好友帮你砍价！</a>
<a style="margin-left:50%">砍了很多了，快去炫耀一下你的战绩！</a></marquee> </li>  

</#if>
 
</ul>  
</div>
</div>
</div>
<!--跑马灯文字 end--> 

<div class="myclear"></div>

<#if participantList ? exists>
<!--好友助力列表 start-->
<div class="helplist">
<div class="titleA_bt">好友砍价列表</div>

<div class="swiper-container" style="margin-top:10px; height:60px;">

<ul class="swiper-wrapper" >

<#list participantList as participant>

<li class="swiper-slide">
<div class="helperimg">
	<#if participant.avatar ? exists>
		<img src="${participant.avatar}" width="40px" height="40px">
	<#else>
		<img src="${rc.contextPath}/resources/bargain/images/1_ico.png" width="40px" height="40px">
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
<div class="swiper-button-prev" style="background:url(${rc.contextPath}/resources/bargain/images/left_03.png) no-repeat; background-size:9px 12px;top:60%;width:9px; height:12px;"></div>
<div class="swiper-button-next" style="background:url(${rc.contextPath}/resources/bargain/images/right_05.png) no-repeat; background-size:9px 12px; top:60%; width:9px; height:12px;"></div>
</div> </div> </div>
 <!--好友助力列表 end-->
 
 </#if>

 <!--商品详情 start-->
<div class="product_details">
产品详情</div>
<div class="mydetails" id="mydetails">
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_02.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_03.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_04.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_05.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_06.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_07.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_08.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_09.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_10.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_11.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_12.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_13.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_14.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_15.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_16.jpg"  width="100%"/>
<img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_17.jpg"  width="100%"/>
<a href="next_activity"><img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_18.jpg"  width="100%"/></a>
</div>
<br/><br/>
<!--商品详情 end-->
 <!--帮ta砍价 二维码弹窗 start-->
  <div id="fade" class="black_overlay" onClick="document.getElementById('mycode').style.display='none';document.getElementById('myhit').style.display='none';document.getElementById('rules').style.display='none';  document.getElementById('fade').style.display='none';"></div> 
 <div class="rules" id="mycode"> 
 
 <img src="${rc.contextPath}/resources/bargain/images/code.jpg" width="100%">
 <p></p>
  
 </div>
 <!--帮ta砍价 二维码弹窗窗 end--> 
 

 <!--活动规则弹窗 start-->
  <div id="fade" class="black_overlay"></div> 
 <div class="rules" id="rules"> 
 <div class="rules_top">活动规则</div>
 <br/>
<p>1、活动时间：7月28日-7月29日</p>
<p>2、用户通过微信平台进入活动页面参与活动，每位用户只限购买一次。</p>
<p>3、活动发起人需要邀请好友为他砍价，原价87元，每次砍价金额随机，最终可以砍到1元购买（限量200瓶，先砍先得，砍完为止），也可以按当时实际砍到的金额购买，发起人的朋友可以帮他砍价也同时可以自己参与砍价活动。</p>
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
 <p> 您帮好友成功砍价<span  id="asyncPrice">8.8</span>元！</p>
 <div class="rules_bottom" onClick="document.getElementById('myhit').style.display='none';document.getElementById('fankan').style.display='block';">知道了</div>
 </div>
 <div class="myhit" id="fankan"> 
  <p> 啊！您的好友反砍一刀！</p>
 <p> 点击<a href="activity_a"><span>我要参加</span></a>爆发你的小宇宙吧!</p>
 <div class="rules_bottom" onClick="document.getElementById('fankan').style.display='none'; document.getElementById('fade').style.display='none';
 location.href = location.href+'&dafsafdas='+Math.random();">知道了</div>
 </div>
 <!--砍价弹窗 end--> 
 
 
   <!--侧边按钮 start--> 
<div id="to_top">
<img onclick="location.href = location.href" src="http://www.cilu.com.cn/climg/activity/ico_new.png" width="100%"/>
<hr style="padding:0; margin:0;"/>
<img onclick="document.documentElement.scrollTop = document.body.scrollTop =0;" src="http://www.cilu.com.cn/climg/activity/ico_up.png" width="100%"/>
</div>
<!--侧边按钮 end--> 
 
<br/><br/><br/>

<!--底部导航 Start-->
<span  id="showCartCount4" name="showCartCount4"></span>
 <div id="footBar">
		<a href="http://weixin.cilu.com.cn/ygg-hqbs/hqbsHomeInfo/getHomeInfo#wechat_redirect">
			<div class="house"></div>
			<div class="text01">商城首页</div>
		</a>

		<a href="http://weixin.cilu.com.cn/ygg-hqbs/spcart/listsc#wechat_redirect">
			<div class="car">
				<span  id="showCartCount4" name="showCartCount4" class="carNum"></span>
			     <!-- <div class="carNum">0</div> -->
			</div>
			<div class="text01">购物车</div>
		</a><a href="http://weixin.cilu.com.cn/ygg-hqbs/order/list/0#wechat_redirect">
			<div class="person"></div>
			<div class="text01">我的订单</div>
		</a><a href="http://weixin.cilu.com.cn/ygg-hqbs/spokesPerson/getList#wechat_redirect">
			<div class="star"></div>
			<div class="text01">我是行动派</div>
		</a>
		
</div> 
<!--底部导航 End-->
<script src="${rc.contextPath}/resources/bargain/js/myjs.js"></script>
<script src="${rc.contextPath}/resources/bargain/js/swiper.min.js"></script>
<script type="text/javascript">
  var mySwiper = new Swiper('.swiper-container',{
    loop: false,
	slidesPerView : 2,
	autoplay: 3000,
	nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
  });
   $(document).ready(function() { 
  
   var mysur=$("#surplus").html();
   var myprice=$("#nowprice").html();
   if(mysur>50){$("#condition1").show();$("#condition2").hide();$("#condition3").hide();}
   if(mysur<=50){$("#condition1").hide();$("#condition2").show();$("#condition3").show();}
   if(myprice<=30){$("#condition1").hide();$("#condition2").hide();$("#condition3").show();}
}); 
</script>
</body>
</html>