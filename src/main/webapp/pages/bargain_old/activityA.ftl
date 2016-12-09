<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<title>砍价帮帮忙</title>
<link href="${rc.contextPath}/resources/bargain/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${rc.contextPath}/resources/bargain/styles.css#2" />
<script src="http://cdn.bootcss.com/bootswatch/2.0.2/js/jquery.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    var appId = "${jsSdk.appId?default("")}";
    var timestamp = "${jsSdk.timestamp?default("")}";
    var noncestr = "${jsSdk.noncestr?default("")}";
    var signature = "${jsSdk.signature?default("")}";
    var sharePath = "http://weixin.cilu.com.cn/ygg-hqbs/bargain/activity_b?accountId=${accountId}";
    var shareImage = "http://www.cilu.com.cn/climg/images/goods11/goods11_a.jpg";
    var shareTitle="江湖救急，我就是要1元得清洗剂，还不出手相助！";
    var shareContent="慈露清洗剂，家用清洁小能手，现在就帮他砍价！";
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
</head>
<body style="background:#f7eb03;">

<div class="box_a">
<img src="${rc.contextPath}/resources/bargain/images/top_a.jpg" width="100%">
<div class="guize" onClick="document.getElementById('rules').style.display='block'; document.getElementById('fade').style.display='block';"><p>活动规则</p></div>
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
		<div class="find_bt"> 
			商品售罄
		</div>
	<#elseif bargain.isBargain ? exists>
		<div class="find_bt" onClick="alert('请点击右上角进行分享')"> 
			炫耀一下
		</div>
	<#else>
		<div class="find_bt" onClick="alert('请点击右上角进行分享')"> 
			找人砍价
		</div>
	</#if>
	
<#if bargain.isPay ? exists>
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
	<a style="color:#FFF;text-decoration:none;" href="${rc.contextPath}/product/single/8">
		原价购买
	</a>
	</div>
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
 <!--好友助力列表 end-->

</#if>

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
 
<br/><br/><br/>
</body>
</html>

