<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8">
     <title>心动测试</title>
     <link rel="shortcut icon" href="${rc.contextPath}/pages/images/favicon.ico}">
     <link rel="apple-touch-icon" href="custom_icon.png">
	<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection" />
	<meta property="wb:webmaster" content="1d1b018b9190b96c" />
	<meta property="qc:admins" content="342774075752116375" />
	<link href="${rc.contextPath}/pages/css/base.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="${rc.contextPath}/scripts/css/list.css">
	<script type="text/javascript" src="${rc.contextPath}/scripts/js/zepto.min.js"></script>
	<script src="${rc.contextPath}/pages/js/jquery-1.11.2.min.js"></script>

</head>
<body>
     <div class="main">
     	<!--页面个人信息start-->
         <div class="touxiang vertical-center-outer t_c" style="background:#000000;">
         	<span class="vertical-center">
         		<div class="circleImg"><img src="${image}}"></div>
         		  <div class="desc">
         		     <ul>
         		     	<li><p>${nickName}</p></li>
         		        <li><p class="id">ID:${account_id}</p></li>
         		        <#if spokesperson?? && spokesperson=='0'>
         		        <li><a href="http://b2.waibao.help/cilu/article/promotion.html><p class="people">如何成为行动派</p></a></li>
         		        </#if>
         		     </ul>
         		 </div>
         	<span>
         </div>
       <!--页面个人信息end-->
       
       <!--信息列表start-->
       <div class="list">
      		<ul>		
      			<!--我的二维码start-->
      			<li>
      			    <a href="${rc.contextPath}/selftestcontroller/getQrCode">
      			        <div class="erweima">
      			        	<p>我的二维码</p>
      			        	<img class="code" src="${rc.contextPath}/scripts/images/code.png">
      			        	<img class="right" src="${rc.contextPath}/scripts/images/right.png">
      			        </div>   			    
      			    </a>
      			</li>
      			<!--我的二维码end-->
      			
      			<!--累计奖励-->
      			<li>
      			   <a href="${rc.contextPath}/selftestcontroller/getReward">
      			     <div class="tixian">
      			         <p>历史累计奖励（元）<p>
      			         <h1>${Reward?replace('""',"")}</h1>
      			         <img class="right" src="${rc.contextPath}/scripts/images/right.png">
      			         <p class="tixian1">可提现金额<span>${withdrawCash?replace('""',"")}</span>元</p>
      			     </div>
      			   </a>
      			</li>   			
      			<!--累计奖励-->
      			
      			<!--行动币-->
      			<li>
      			   <a href="#">
      			      <div class="tixian">
      			          <p>行动币</p>
      			          <h1>${point?replace('""',"")}</h1>
      			      </div>
      			   </a>
      			</li>
      			<!--行动币-->
      			
      			<!--我的粉丝及粉丝销量-->
      			<li>
      			     <a href="${rc.contextPath}/selftestcontroller/getFansLists">
      			        <div class="myfans">我的粉丝
      			            <img class="rightIcon" class="right" src="${rc.contextPath}/scripts/images/right.png" >
      			            <span>${fans}人</span>
      			        </div>
      			     </a>
      			     
      			     <a href="${rc.contextPath}/selftestcontroller/getFansOrderList">
      			        <div class="sale">粉丝销量
      			            <img class="rightIcon"  class="right" src="${rc.contextPath}/scripts/images/right.png"></img>
      			            <span class="price">${fansOrderPrice?replace('""',"")}元</span>
      			        </div>
      			     </a>    			
      			</li>   			
                <!--我的粉丝及粉丝销量-->

      		</ul>
       </div>
       <!--信息列表end-->

	   <p class="tj">您是由【${recommendedPerson?replace('""',"")}】推荐</p>
	</div>
	
	  <script src="${rc.contextPath}/scripts/js/jquery-1.11.2.min.js"></script>
      <script src="${rc.contextPath}/pages/js/touch.js"></script>
      
      <!--底部导航start-->
      <#include "navFooter.ftl">
      <!--底部导航end-->
      <div class="tongjicnzz" style="display:none">
      <#include "../common/tongjicnzz.ftl">
      </div>

</body>

</html>
