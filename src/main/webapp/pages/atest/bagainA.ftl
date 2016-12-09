<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>慈露1元砍</title>
<link href="${rc.contextPath}/resources/bargain/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${rc.contextPath}/resources/bargain/css/swiper.min.css">
<script src="${rc.contextPath}/resources/bargain/js/jquery-1.11.0.min.js"></script>
<script src="${rc.contextPath}/resources/bargain/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>

<body style="background:#f6ebd9;">
<div class="box_a">
<img src="${rc.contextPath}/resources/bargain/images/goods6c_01.jpg"   width="100%">
<div class="guize" onClick="document.getElementById('rules').style.display='block'; document.getElementById('fade').style.display='block';"><p>活动规则</p></div>
</div>

<div class="box2">
<!--价格start-->
<div class="jia_lf">产品原价:<span>88.00</span>
</div>
<div class="jia_lf">
<span style="font-size:1.1em;">当前价格:</span><span id="nowprice">${bargain.currentPrice}</span>
</div>
<!--价格end-->

<div class="myclear"></div>

<!--按钮start-->
<div class="mybuttom">
	<#if isSoldOut == "1">
		<div class="find_bt"> 
			商品售罄
		</div>
	<#elseif bargain.isBargain ? exists && bargain.isBargain=="1">
		<div class="find_bt" onClick="alert('请点击右上角进行分享')"> 
			炫耀一下
		</div>
	<#else>
		<div class="find_bt"><a style="color:#FFF;" href="${rc.contextPath}/selfBargainController/activity_b88/164643275">
			找人砍价
		</div>
	</#if>
<#if bargain.isPay ? exists && bargain.isPay=="1">
	<div class="find_bt" > 
		已经购买
	</div>
<#elseif isSoldOut == "1">
	<div class="find_bt" > 
		商品售罄
	</div>
<#elseif participantList ? exists>    
	<div class="find_bt" > 
	<a style="color:#FFF;" href="${rc.contextPath}/bargain/create_order?price=${bargain.currentPrice}">
		立即购买
	</a>
	</div>
<#else>   
   <div class="find_bt" > 
	<a style="color:#FFF;text-decoration:none;" href="${rc.contextPath}/product/single/15">
		原价购买
	</a>
	</div>
</#if>  
</div>
<!--按钮end-->

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
		<img src="images/1_ico.png" width="40px" height="40px">
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
<a href="${rc.contextPath}/selfBargainController/next_activity"><img  class="lazy" data-original="${rc.contextPath}/resources/bargain/images/goods6c_18.jpg"  width="100%"/></a>
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
<p>1、活动时间：10月28日-10月29日</p>
<p>2、用户通过微信平台进入活动页面参与活动，每位用户只限购买一次。</p>
<p>3、活动发起人需要邀请好友为他砍价，原价88元，每次砍价金额随机，最终可以砍到1元购买（限量200瓶，先砍先得，砍完为止），也可以按当时实际砍到的金额购买，发起人的朋友可以帮他砍价也同时可以自己参与砍价活动。</p>
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
 <p> 您帮好友成功砍价<span>8.8</span>元！</p>
 <div class="rules_bottom" onClick="document.getElementById('myhit').style.display='none'; document.getElementById('fade').style.display='none';location.reload();">知道了</div>
 </div>
 <!--砍价弹窗 end--> 
 
 
   <!--侧边按钮 start--> 
<div id="to_top">
<!--
<img onclick="location.href = location.href" src="${rc.contextPath}/climg/activity/ico_new.png" width="100%"/>
<hr style="padding:0; margin:0;"/>
<img onclick="document.documentElement.scrollTop = document.body.scrollTop =0;" src="${rc.contextPath}/climg/activity/ico_up.png" width="100%"/>
-->
</div>
<!--侧边按钮 end--> 
 
<br/><br/><br/>

<!--底部导航 Start-->
<span  id="showCartCount4" name="showCartCount4"></span>
 <div id="footBar">
		<a href="${rc.contextPath}/ygg-hqbs/hqbsHomeInfo/getHomeInfo#wechat_redirect">
			<div class="house"></div>
			<div class="text01">商城首页</div>
		</a>

		<a href="${rc.contextPath}/ygg-hqbs/spcart/listsc#wechat_redirect">
			<div class="car">
				<span  id="showCartCount4" name="showCartCount4" class="carNum"></span>
			     <!-- <div class="carNum">0</div> -->
			</div>
			<div class="text01">购物车</div>
		</a><a href="${rc.contextPath}/ygg-hqbs/order/list/0#wechat_redirect">
			<div class="person"></div>
			<div class="text01">我的订单</div>
		</a><a href="${rc.contextPath}/ygg-hqbs/spokesPerson/getList#wechat_redirect">
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

	autoplay: 3000,
	slidesPerView : 2,
	nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
  });
  
  $(document).ready(function() { 
  
   var mysur=$("#surplus").html();
   var myprice=$("#nowprice").html();
   if(mysur>50){$("#condition1").show();$("#condition2").hide();$("#condition3").hide();}
   if(mysur<=50){$("#condition1").hide();$("#condition2").show();$("#condition3").show();}
   if(myprice<=30){$("#condition1").hide();$("#condition2").hide();$("#condition3").show();}
   if(myprice<=1.00){$("#mynotice").show();$("#fade").show();}
}); 
  
</script>
 
 <!--提示弹窗 start-->
 <div class="myhit" id="mynotice"> 
 <p> 太棒啦！恭喜您成功砍价到1元！</p>
 <p> 点击立即购买按钮，把宝贝抢回家！</p>
 <div class="rules_bottom" onClick="document.getElementById('mynotice').style.display='none'; document.getElementById('fade').style.display='none';">知道了</div>
 </div>
 <!--提示弹窗 end--> 


</body>
</html>