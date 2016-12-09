package com.ygg.webapp.controller.bargain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ygg.webapp.util.CacheConstant;
import com.ygg.webapp.util.CacheUtil;
import com.ygg.webapp.util.CommonConstant;
import com.ygg.webapp.util.CommonEnum;
import com.ygg.webapp.util.CommonUtil;
import com.ygg.webapp.util.JSONUtils;
import com.ygg.webapp.util.SessionUtil;
import com.ygg.webapp.util.WeixinMessageDigestUtil;
import com.ygg.webapp.util.YggWebProperties;
import com.ygg.webapp.view.AccountView;
import com.ygg.webapp.view.AddressView;
import com.ygg.webapp.view.ConfirmOrderView;
import com.ygg.webapp.view.OrderProductView;
import com.ygg.webapp.view.ReceiveAddressView;
import com.ygg.webapp.wechat.entity.req.UserInfo;
import com.ygg.webapp.service.bargain.HcbBargainService;
import com.ygg.webapp.service.bargain.HcbParticipantService;
import com.ygg.webapp.service.spokesperson.SpokesPersonService;
import com.ygg.webapp.entity.bargain.HcbBargain;
import com.ygg.webapp.entity.bargain.HcbParticipant;
import com.ygg.webapp.exception.BusException;
import com.ygg.webapp.exception.ServiceException;
import com.ygg.webapp.sdk.weixin.util.Sha1Util;
import com.ygg.webapp.service.account.HqbsAccountService;
import com.ygg.webapp.entity.QqbsAccountEntity;
import com.ygg.webapp.service.OrderService;
import com.ygg.webapp.code.ErrorCodeEnum;
import com.ygg.webapp.controller.OrderController;
import com.ygg.webapp.dao.ProductCommonDao;
import com.ygg.webapp.dao.QRCodeDao;
import com.ygg.webapp.entity.OrderEntity;
import com.ygg.webapp.entity.OrderReceiveAddressEntity;
import com.ygg.webapp.service.OrderPayService;
import com.ygg.webapp.entity.OrderWeixinPayEntity;
import com.ygg.webapp.entity.ProductCommonEntity;
import com.ygg.webapp.service.TempAccountService;

import com.ygg.webapp.service.AddressService;
import com.ygg.webapp.service.ReceiveAddressService;
import com.ygg.webapp.service.bargain.HcbOrderService;
import com.ygg.webapp.dao.OrderReceiveAddressDao;

@Controller
@RequestMapping("/bargain")
public class HcbBargainController {

	private Logger logger = Logger.getLogger(HcbBargainController.class);

	@Resource
	HcbBargainService bargainService;

	@Resource(name = "productCommonDao")
	private ProductCommonDao pcommondi = null;

	@Resource
	HcbParticipantService participantService;

	@Resource
	SpokesPersonService spokesPersonService;

	@Resource
	HqbsAccountService hqbsAccountService;

	@Resource
	OrderService orderService;

	@Resource
	OrderPayService orderPayService;

	@Resource
	TempAccountService tempAccountService;

	@Resource
	AddressService addressService;

	@Resource
	ReceiveAddressService receiveAddressService;

	@Resource
	HcbOrderService hcbOrderService;

	@Resource
	OrderReceiveAddressDao orderReceiveAddressDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/index")
	public String indexPage(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	        throws Exception {

//		ModelAndView modelAndView = new ModelAndView("bargain/activity");

//		return modelAndView;
		return "redirect:/bargain/activity_a";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/next_activity")
	public ModelAndView nextActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	        throws Exception {

		ModelAndView modelAndView = new ModelAndView("bargain/activityEnd");

		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qrcode")
	public ModelAndView QRCodeDao(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(required = false, defaultValue = "null") String qrCodeImg)
			throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("bargain/activityCode");
		
		modelAndView.addObject("qrCodeImg", qrCodeImg);
		
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activity_a")
	public ModelAndView activityA(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	        throws Exception {

		ModelAndView modelAndView = new ModelAndView("bargain/activityA");

		AccountView av = SessionUtil.getCurrentUser(request.getSession());
		
		modelAndView.addObject("accountId", String.valueOf(av.getId()));

		String accountId = String.valueOf(av.getId());

		HcbBargain bargain = bargainService.selectByBargainerUuid(accountId);

		if (bargain == null) {

			bargain = new HcbBargain();

			bargain.setBargainerUuid(accountId);
			bargain.setCurrentPrice("87.00");
			bargain.setCreateDatetime(new Date());
			bargain.setUserUuid(accountId);
			bargain.setPeopleBargain(av.getNickname());
			bargainService.insertSelective(bargain);
		}

		Float currentPrice = Float.valueOf(bargain.getCurrentPrice());
		bargain.setCurrentPrice(String.format("%.2f", currentPrice));

		modelAndView.addObject("bargain", bargain);

		Integer leftNumber = 200 - bargainService.countTotalPayed();
		modelAndView.addObject("leftNumber", String.valueOf(leftNumber));
		modelAndView.addObject("soldNumber", String.valueOf(200 - leftNumber));

		// 注入已砍价的人的总数
		Integer bargainerNumber = bargainService.countTotalBargained("87.00");
		
		modelAndView.addObject("bargainerNumber", String.valueOf(bargainerNumber));
		
		if (leftNumber <= 0) {

			modelAndView.addObject("isSoldOut", "1");
		} else {

			modelAndView.addObject("isSoldOut", "0");
		}
		
		if (leftNumber <= 30) {
			
			modelAndView.addObject("lessThanThirty", "1");
			
		} else if (leftNumber <= 150) {
			
			modelAndView.addObject("lessThanHundred", "1");
		}

		if (bargain != null && bargain.getBargainerUuid() != null) {

			List<HcbParticipant> participants = participantService
			        .listSelectByBargainerUuid(bargain.getBargainerUuid());

			if (participants != null && participants.size() > 0) {

				for (HcbParticipant participant : participants) {

					if (participant.getUserUuid().equals(String.valueOf(av.getId()))) {

						modelAndView.addObject("isBargained", "1");
					}
				}

				modelAndView.addObject("participantList", participants);
			}
		}

		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activity_b")
	public ModelAndView activityB(HttpServletRequest request, HttpServletResponse response, HttpSession session,
	        @RequestParam(required = false) String accountId, @RequestParam(required = false) String scene)
	        throws Exception {

		ModelAndView modelAndView = new ModelAndView("bargain/activityB");

		// 查看页面的用户
		AccountView av = SessionUtil.getCurrentUser(request.getSession());
		// 分享该页面的用户（被帮忙砍价的用户）
		QqbsAccountEntity accountEntity = hqbsAccountService.getAccountByAccountId(Integer.valueOf(accountId));

		// 如果未传入accountId或查询不到砍价邀请者的账户信息或者传入的为自己的accountId，则展示自己的砍价进度
		if (accountId == null || accountId.equals("") || accountEntity == null 
				|| (av != null && av.getId() == Integer.valueOf(accountId))) {

			modelAndView = new ModelAndView("bargain/activityA");
			accountId	= String.valueOf(av.getId());
		}
		
		if (accountEntity == null) {
			
			accountEntity = hqbsAccountService.getAccountByAccountId(Integer.valueOf(accountId));
		}

		HcbBargain bargain = bargainService.selectByBargainerUuid(accountId);

		if (bargain == null) {

			bargain = new HcbBargain();
			bargain.setBargainerUuid(accountId);
			bargain.setCurrentPrice("87.00");
			bargain.setCreateDatetime(new Date());
			bargain.setUserUuid(accountId);
			
			if (accountEntity != null) {
				
				bargain.setPeopleBargain(accountEntity.getNickName());
			}
			
			bargainService.insertSelective(bargain);
		}

		Float currentPrice = Float.valueOf(bargain.getCurrentPrice());
		bargain.setCurrentPrice(String.format("%.2f", currentPrice));

		Integer leftNumber = 200 - bargainService.countTotalPayed();
		modelAndView.addObject("leftNumber", String.valueOf(leftNumber));
		modelAndView.addObject("soldNumber", String.valueOf(200 - leftNumber));

		if (leftNumber <= 0) {

			modelAndView.addObject("isSoldOut", "1");
		} else {

			modelAndView.addObject("isSoldOut", "0");
		}
		
		if (leftNumber <= 30) {
			
			modelAndView.addObject("lessThanThirty", "1");
			
		} else if (leftNumber <= 150) {
			
			modelAndView.addObject("lessThanHundred", "1");
		}

		// 注入已砍价的人的总数
		Integer bargainerNumber = bargainService.countTotalBargained("87.00");
				
		modelAndView.addObject("bargainerNumber", String.valueOf(bargainerNumber));
		
		// 添加用户A的二维码
		String qrCodeImg = spokesPersonService.getQRCode(accountEntity);

		QqbsAccountEntity myEntity = hqbsAccountService.getAccountByAccountId(av.getId());
		String accessToken = WeixinMessageDigestUtil.getAccessToken(CommonConstant.APPID, CommonConstant.APPSECRET);
		UserInfo userInfo  = WeixinMessageDigestUtil.getUserInfoByHqbsAccessToken(accessToken, myEntity.getOpenId());
		
		if (userInfo.getSubscribe() == null || userInfo.getSubscribe().equals("0")) {
			
			modelAndView.addObject("notSubscribe", "1");
		} else {
			
			modelAndView.addObject("notSubscribe", "0");
		}
		
		if (bargain != null && bargain.getBargainerUuid() != null) {

			List<HcbParticipant> participants = participantService
			        .listSelectByBargainerUuid(bargain.getBargainerUuid());

			if (participants != null && participants.size() > 0) {

				for (HcbParticipant participant : participants) {

					if (participant.getUserUuid().equals(String.valueOf(av.getId()))) {

						modelAndView.addObject("isBargained", "1");
					}
				}

				modelAndView.addObject("participantList", participants);
			}
		}

		modelAndView.addObject("bargain", bargain);
		modelAndView.addObject("qrCodeImg", qrCodeImg);
		modelAndView.addObject("accountId", accountId);
		modelAndView.addObject("currentAccountId", String.valueOf(av.getId()));

		if (scene != null && scene.equals("inapp")) {

			modelAndView.addObject("isInApp", "1");
		} else {

			modelAndView.addObject("isInApp", "");
		}

		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/create_order")
	public ModelAndView createOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session,
	        @RequestParam(required = false) String price) throws Exception {

		AccountView av = SessionUtil.getCurrentUser(request.getSession());
		
		if (price == null || price.equals("") || Float.valueOf(price) < 1.0 || Float.valueOf(price) > 87.0) {

			HcbBargain	bargain	= bargainService.selectByBargainerUuid(String.valueOf(av.getId()));

			if (bargain.getCurrentPrice() != null && !bargain.getCurrentPrice().equals("")) {
				
				price = String.format("%.2f", Double.valueOf(bargain.getCurrentPrice()));
			} else {
				
				price = "87";
			}
		}

		ModelAndView modelAndView = new ModelAndView("bargain/order");

		modelAndView.addObject("accountId", String.valueOf(av.getId()));

		// String requestparams = "{'accountId':'" + accountId +
		// "','cartToken':'" + tempAccountId + "','type':'" + ordertype + "'}";
		// String responseparams = "";
		String addressId = "-1"; // －１没有收货地址，需要新增
		String isBonded = "0"; // 1表示保税区
		String endSecond = "-1";
		String errorCode = "";
		String confirmId = "";
		String status = CommonEnum.BUSINESS_RESPONSE_STATUS.FAILED.getValue();
		List<ConfirmOrderView> covs = new ArrayList<>();

		ReceiveAddressView rav = null;
		Set<String> tipsset = null;
		String isbondtips = "";
		String couponStr = "使用优惠券";
		String couponId = "0";
		String ismailbag = "0"; // 运费为 0包邮 , 1 不包邮

		if (SessionUtil.getCurrentSelectedAddressId(request.getSession()) != null 
				&& SessionUtil.getCurrentSelectedAddressId(request.getSession()) != "-1") {
			
			addressId = SessionUtil.getCurrentSelectedAddressId(request.getSession());
			rav = receiveAddressService.findReceiveAddressViewById(Integer.valueOf(addressId));
		} else {
			
			rav = receiveAddressService.findDefaultAddressByAccountId(av.getId());
			addressId = String.valueOf(rav.getId());
		}
		
		// rav =
		// addressService.findDefaultAddressViewByAccountId(String.valueOf(av.getId()));

		
		// 购物商品修改
		ConfirmOrderView cov = new ConfirmOrderView();
		List<OrderProductView> bondedOrderDetailList = new ArrayList<>();
		OrderProductView opv = new OrderProductView();
		ProductCommonEntity pce = pcommondi.findProductCommonInfoById(15);
		opv.setProductId(pce.getProductId() + "");
		opv.setImage(pce.getSmallImage());
		opv.setShortName(pce.getShortName());
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		opv.setSalesPrice(decimalFormat.format(pce.getSalesPrice())); // pce.getSalesPrice()
		                                                              // + "");
		opv.setCount("1");
		opv.setStatus(CommonEnum.ORDER_PRODUCT_STOCK_STATUS.SALE.getValue());
		opv.setStockCount("");
		opv.setRestriction("");
		opv.setSalesPrice(price);
		bondedOrderDetailList.add(opv);
		cov.setOrderDetailList(bondedOrderDetailList);
		cov.setSellerName("心动慈露");
		cov.setSendAddress("杭州慈露有限公司");
		covs.add(cov);
		modelAndView.addObject("ismailbag", ismailbag);
		modelAndView.addObject("status", status);
		modelAndView.addObject("addressId", addressId);
		modelAndView.addObject("defaultAddress", rav);
		modelAndView.addObject("isBonded", isBonded);
		modelAndView.addObject("availablePoint", "0");
		modelAndView.addObject("errorCode", errorCode);
		modelAndView.addObject("endSecond", endSecond);
		modelAndView.addObject("confirmOrderList", covs);
		modelAndView.addObject("confirmId", confirmId);
		modelAndView.addObject("ordertype", "4");
		modelAndView.addObject("allTotalPrice", price); // 总共定单的总价和
		modelAndView.addObject("couponPrice", price); // 优惠后总价格
		modelAndView.addObject("isbondtips", isbondtips);
		modelAndView.addObject("tips", tipsset);
		modelAndView.addObject("couponStr", couponStr);
		modelAndView.addObject("couponId", couponId);
		modelAndView.addObject("point", "0");

		// //////////////////配置微信公众账号H5支付的配置config ////////////////////////////
		String weixin_pay_url_one = YggWebProperties.getInstance().getProperties("weixin_pay_url_one");
		weixin_pay_url_one = weixin_pay_url_one + "/" + "4";
		String resquestParams = "{'url':'" + weixin_pay_url_one + "','totalPrice':'" + "57" + "'}";
		// String responseParams = orderPayService.configPay(request, response,
		// resquestParams) ;
		getWeiXinAccessToken(request, response, resquestParams, modelAndView);

		// /////////////////////////// end///////////////////////////////////

		return modelAndView;
	}

	private void getWeiXinAccessToken(HttpServletRequest request, HttpServletResponse response, String resquestParams,
	        ModelAndView mv) throws Exception {
		String responseParams = orderPayService.configPay(request, response, resquestParams);
		JsonParser parser = new JsonParser();
		JsonParser requestparser = new JsonParser();
		JsonObject param = (JsonObject) parser.parse(responseParams);
		String timestamp = "";
		String nonceStr = "";
		String signature = "";
		String accessToken = "";
		String jsApiTicket = "";
		float totalPrice = 0f;
		timestamp = param.get("timestamp") == null ? Sha1Util.getTimeStamp() : param.get("timestamp").getAsString();
		nonceStr = param.get("nonceStr") == null ? "" : param.get("nonceStr").getAsString();
		signature = param.get("signature") == null ? "" : param.get("signature").getAsString();
		accessToken = JSONUtils.getValue(param, CacheConstant.WEIXIN_ACCESS_TOKEN, "");
		jsApiTicket = JSONUtils.getValue(param, CacheConstant.WEIXIN_JSAPI_TICKET, "");
		JsonObject paramrequest = (JsonObject) requestparser.parse(resquestParams);
		totalPrice = paramrequest.get("totalPrice") == null ? 0f : paramrequest.get("totalPrice").getAsFloat();

		mv.addObject(CacheConstant.WEIXIN_ACCESS_TOKEN, accessToken);
		mv.addObject(CacheConstant.WEIXIN_JSAPI_TICKET, jsApiTicket);
		// zhangld 去掉判断 isQqbs
		mv.addObject("appid", CommonConstant.APPID);
		mv.addObject("timestamp", timestamp); // 必填，生成签名的时间戳
		mv.addObject("nonceStr", nonceStr); // 必填，生成签名的随机串
		mv.addObject("signature", signature); // 必填，签名
		// mv.addObject("jsApiList", "[]"); // 必填，需要使用的JS接口列表，
		// this.logger.info("---------getWeiXinAccessToken-----responseParams---is:
		// "+ responseParams);

		// 1.判断是否是微信浏览器，而已要是5.0以上版本才能支付，否则给出提示
		boolean flag = CommonUtil.isWeiXinVersionMoreThan5(request.getHeader("User-Agent"));
		mv.addObject("iswx5version", (flag ? "1" : "0")); // 1表示 >=5.0以上的版本

		// 发起统一支付请求得到prepar_id
		// resquestParams =
		// "{'totalPrice':'"+totalPrice+"','openId':'"+SessionUtil.getCurrentWeiXinOpenId(request.getSession())+"'}";
		// responseParams = this.orderPayService.requestBeforePay(request,
		// response, resquestParams) ;
		// System.out.println("-----getWeiXinAccessToken---beforeResponseParams----is:"
		// + responseParams);
	}

	/**
	 * 微信定单新增,分两步完成 addoderstatus表示成功或失败
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addajax")
	@ResponseBody
	public String orderAddAjax(HttpServletRequest request,
	        @CookieValue(value = "ygguuid", required = true, defaultValue = "tmpuuid") String ygguuid,
	        @RequestParam(value = "totalPrice", required = false, defaultValue = "0") String totalPrice,
	        @RequestParam(value = "couponPrice", required = false, defaultValue = "0") String couponPrice, // 优惠后的订单总价，值为totalPrice减去积分和优惠的价格
	        @RequestParam(value = "usedPoint", required = false, defaultValue = "0") String usedPoint, // 使用的积分数
	        @RequestParam(value = "couponId", required = false, defaultValue = "0") String couponId, // 使用的优惠券ID
	        @RequestParam(value = "ordertype", required = false, defaultValue = "1") String ordertype,
	        @RequestParam(value = "confirmId", required = false, defaultValue = "0") String confirmId,
	        @RequestParam(value = "addressId", required = false, defaultValue = "-1") String addressId,
	        @RequestParam(value = "paytype", required = false, defaultValue = "2") String paytype // /
	                                                                                              // //
	                                                                                              // 1[银联]、2[支付宝]、3[微信])
	) throws Exception {
		JsonObject result = new JsonObject();

		int accountId = CommonConstant.ID_NOT_EXIST;
		int tempAccountId = CommonConstant.ID_NOT_EXIST;
		AccountView av = SessionUtil.getCurrentUser(request.getSession());
		if (av == null) {
			SessionUtil.removeCurrentSelectedCouponId(request.getSession(),
			        SessionUtil.getCurrentOrderConfirmId(request.getSession()));
			SessionUtil.removeCurrentOrderConfirmId(request.getSession());
			result.addProperty("redirecturl", "/user/tologin");
			result.addProperty("ordertype", ordertype);
			result.addProperty("errorCode", "7"); //
			result.addProperty("addoderstatus", CommonEnum.BUSINESS_RESPONSE_STATUS.FAILED.getValue());
			return result.toString();
		} else if (addressId == null || addressId.equals("-1") || addressId.equals("0")) {
		
			result.addProperty("redirecturl", "/bargain/create_order");
			result.addProperty("ordertype", ordertype);
			result.addProperty("errorCode", "3"); //
			result.addProperty("addoderstatus", CommonEnum.BUSINESS_RESPONSE_STATUS.FAILED.getValue());
			return result.toString();
		} else {
		
			accountId = av.getId();
			tempAccountId = this.tempAccountService.findTempAccountIdByUUID(ygguuid);
		}

		String oscValue = CommonUtil.getCookieValue(request, CommonConstant.OrderSoucrChannelKey);
		// zhangld 去掉判断 isQqbs
		String requestParams = "{'accountId':'" + accountId + "','cartToken':'" + tempAccountId + "','type':'"
		        + ordertype + "','confirmId':'" + confirmId + "','addressId':'" + addressId + "','couponPrice':'"
		        + couponPrice + "','usedPoint':'" + usedPoint + "','couponId':'" + couponId + "','totalPrice':'"
		        + totalPrice + "','paytype':'" + paytype + "','osc':'" + oscValue + "'}";
		Object orderIdsJson = "";
		List<Integer> orderIds = new ArrayList<Integer>();
		try {
			String batchNumber = CommonUtil.generateOrderNumber();
			String sameBatchNumber = CommonUtil.generateOrderNumber();

			OrderEntity order = new OrderEntity();

			order.setAccountId(accountId);
			order.setReceiveAddressId(Integer.valueOf(addressId));
			order.setCouponPrice(0);
			order.setBatchNumber(batchNumber);
			order.setSameBatchNumber(sameBatchNumber);
			order.setNumber(CommonUtil.generateOrderNumber());
			order.setRealPrice(Float.valueOf(totalPrice));
			order.setTotalPrice(Float.valueOf(totalPrice));

			ReceiveAddressView rae = receiveAddressService.findReceiveAddressViewById(Integer.valueOf(addressId));

			if (rae == null) {

				result.addProperty("redirecturl", "/bargain/create_order?price=" + totalPrice);
				result.addProperty("errorCode", "3");
			} else {

				OrderReceiveAddressEntity orae = new OrderReceiveAddressEntity();
				// modify by zhangld end 2016/3/16
				orae.setCity(rae.getCity() != null ? rae.getCity() : "杭州");
				orae.setDetailAddress(rae.getDetailAddress() != null ? rae.getDetailAddress() : "西湖区万塘路30号");
				orae.setDistrict(rae.getDistrict() != null ? rae.getDistrict() : "西湖区");
				orae.setFullName(rae.getFullName() != null ? rae.getFullName() : "许通");
				orae.setMobileNumber(rae.getMobileNumber() != null ? rae.getMobileNumber() : "13162578665");
				orae.setProvince(rae.getProvince() != null ? rae.getProvince() : "浙江省");
				orae.setIdCard("test");
				int orderReceiveAddId = orderReceiveAddressDao.addAddress(orae);
				if (orderReceiveAddId == 0) {

				}

				order.setReceiveAddressId(orae.getId());
			}

			Integer orderId = hcbOrderService.insertOrder(order);

			orderIds.add(orderId);
		} catch (Exception e) {
			this.logger.error("OrderController-----add---err:", e);
			if (e instanceof ServiceException) {
				BusException se = new BusException("支付接口报错");
				se.setViewName("forward:/bargain_order/confirm/" + ordertype);
				se.setErrorCode("支付出错，请刷新页面!");
				throw se;
			}
			throw e;
		}

		result.addProperty("accountId", accountId);

		String orderIdArray = "";
		if (orderIds != null && !orderIds.isEmpty()) {
			for (Integer oId : orderIds)
				orderIdArray += oId + ",";
		}

		if (orderIdArray != null && !orderIdArray.equals("")) {
			orderIdArray = orderIdArray.substring(0, orderIdArray.length() - 1);

			SessionUtil.addCurrentOrderId(request.getSession(), orderIdArray);
			result.addProperty("orderIdList", orderIdArray);
		}

		// result.addProperty("redirecturl", "/order/topay/"+paytype);
		// if(paytype!=null && paytype.equals("3")) //微信支付
		result.addProperty("redirecturl", "/bargain_order/topaywx/3");

		result.addProperty("addoderstatus", CommonEnum.BUSINESS_RESPONSE_STATUS.SUCCEED.getValue());
		
		return result.toString();
	}
}
