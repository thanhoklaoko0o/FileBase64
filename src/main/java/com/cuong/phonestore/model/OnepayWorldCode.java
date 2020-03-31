package com.cuong.phonestore.model;

public class OnepayWorldCode {

	public final String VPCRequest = "https://mtf.onepay.vn/vpcpay/vpcpay.op";
	public final String Merchant = "TESTONEPAY";
	public final String AccessCode = "6BEB2546";
	public final String SECURE_SECRET = "6D0870CDE5F24F34F3915FB0045120DB";
	
	public final String ReturnURL = "http://localhost:8080/payment/OnepayWorld/result"; /*"http://localhost:8080/order/payment";*/
}
