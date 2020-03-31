jQuery(document).ready(function($){

$("#login-form").validate({
	rules: {
		username: {
    		required: true,
    		minlength: 6
    	},
    	password: {
    		required: true,
    		minlength: 6
    	}
	}
});
	
var validator = $("#customerInfoForm").validate({
                    rules: {
                    	recipientName: {
                    		required: true,
                    		minlength: 6
                    	},                       
                        shipAddress: {
                            required: true,
                            minlength: 10
                        },
                        phone: {
                            required: true,
                            minlength: 10
                        },
                        email: {
                            required: true,
                            email: true
                        },
                        city: {
                        	required: true
                        },
                        zip: {
                        	required: true,
                    		number: true
                        },
                        district: {
                        	required: true
                        },
                        village: {
                        	required: true
                        },                        
                        rulesOrder: "required",
                        paymentMethod: "required"
                    },
                    messages: {
                    	recipientName: {
                    		required: "Vui lòng nhập!",
                    		minlength: "ít nhất 6 ký tự!"
                    	},
                    	shipAddress: {
                    		required: "Vui lòng nhập!",
                    		minlength: "Ít nhất 10 ký tự!"
                        },
                        phone: {
                        	required: "Vui lòng nhập!",
                    		minlength: "Ít nhất 10 chữ số!"
                        },
                        email: {
                        	required: "Vui lòng nhập!",
                            email: "Email không đúng định dạng!"
                        },
                        city: {
                        	required: "Vui lòng chọn!"
                        },
                        zip: {
                        	required: "Vui lòng nhập!",               
                    		number: "Phải nhập chữ số!"
                        },
                        district: {
                        	required: "Vui lòng chọn!"
                        },
                        village: {
                        	required: "Vui lòng chọn!"
                        },                                              
                        rulesOrder: "Vui lòng đọc và chấp nhận các điều khoản mua, đặt hàng!",
                        paymentMethod: "Vui lòng chọn phương thức thanh toán!",
                    }
                });
				$("#order").click(function() {
				if ($("#customerInfoForm").valid()) {
			        var provinceText = $("#ddlProvince option:selected").text();
			        var districtText = $("#ddlDistrict option:selected").text();
			        var wardText = $("#ddlWard option:selected").text();
			        var shipAddress = $("#shipAddress").val() + ', ' + provinceText + ', ' + districtText + ', ' + wardText;
					var paymentMethod = "";
					if($("#rbOnepay").is(":checked")) paymentMethod = "onepay";
					if($("#rbOnepayW").is(":checked")) paymentMethod = "onepayW";
					if($("#rbPayOffline").is(":checked")) paymentMethod = "paymentOffline";
					$.ajax({
				        url : "/order/checkout",
				        type : "post",
				        dataType:"text",
				        data : JSON.stringify({
					        	orderID:null,
					        	user:null,
					        	orderDetailsList:null,
					        	orderDiscount:null,
					        	onepayRequest:null,
					        	onepayResponse:null,
					        	recipientName:$("#recipientName").val(),
					        	shipName:"",
					        	shipAddress:shipAddress,					
					        	zip:$("#zip").val(),
					        	city:provinceText,
					        	district:districtText,
					        	village:wardText,
					        	phone:$("#phone").val(),
					        	email:$("#email").val(),
					        	orderDate:"",
					        	shipped:"-1",
					        	confirmed:"0",
					        	paid:"-1",
					        	payment_method: paymentMethod,
					        	transactionMumber: null,
					        	purchaseAmount: 0
				        }),
				        headers: {
				            'Accept': 'application/json',
				            'Content-Type': 'application/json'
				              },
				            success : function (result){
				            	location.href = result;
				            }
				        });
				    } else
				          validator.focusInvalid();
				    return false;
				});
})
 
/* function payment(ele){
		if (validateForm) {
			var payment_method = ele.getAttribute("data-type");
	        $.ajax({
	            url : "/order/checkout",
	            type : "post",
	            dataType:"text",
	            data : JSON.stringify({
	    	        	orderID:null,
	    	        	user:null,
	    	        	orderDetailsList:null,
	    	        	orderDiscount:null,
	    	        	recipientName:$("#recipientName").val(),
	    	        	shipName:"",
	    	        	shipAddress:$("#shipAddress").val(),
	    	        	country:$("#country").val(),
	    	        	zip:$("#zip").val(),
	    	        	city:$("#city").val(),
	    	        	district:$("#district").val(),
	    	        	village:$("#village").val(),
	    	        	phone:$("#phone").val(),
	    	        	email:$("#email").val(),
	    	        	orderDate:"",
	    	        	shipped:"0",
	    	        	confirmed:"0",
	    	        	paid:"0",
	    	        	payment_method: payment_method,
	    	        	transactionMumber: null
	            }),
	            headers: {
	                'Accept': 'application/json',
	                'Content-Type': 'application/json'
	              },
	            success : function (result){
	            	location.href = result;
	            }
	        });
		} else {
			validator.focusInvalid();
		} 
	}*/
