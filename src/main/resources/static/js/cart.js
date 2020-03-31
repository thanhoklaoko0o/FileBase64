//cart

/*function payment(ele){
	var payment_method = ele.getAttribute("data-type");
	console.log(payment_method);
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
}
*/
function addCartItem(ele) {
	var amount = document.getElementById('amountCart').innerText;
	var quantity = typeof $("#quantity").val() == "undefined" ? 1: $("#quantity").val(); 
    var productID = ele.getAttribute("data-id");
    $.ajax({
        url : "/cart/add/"+productID+"/"+quantity,
        type : "get",
        dataType:"text",
        data : {},
        success : function (result){
        	$('#amountCart').html(result);
        	if(result === amount){
        		swal({
	                title: 'Thông báo',
	                text: 'Hiện tại sản phẩm đã hết chúng tôi sẽ bổ sung hàng sớm!',
	                showConfirmButton: false,
	                timer: 2000,
	            })
        	}else if(result < Number(amount) + Number(quantity)){
        		swal({
	                title: 'Thông báo',
	                text: 'Hiện tại sản phẩm này chỉ còn ' + (result - amount) + ' sản phẩm!',
	                showConfirmButton: false,
	                timer: 2000,
	            })
        	} else {
                swal({
    	                title: 'Thành công',
    	                text: 'Đã thêm sản phẩm vào giỏ hàng!',
    	                showConfirmButton: false,
    	                timer: 1500,
    	            })
        	}
        }
    });
}

function updateCartItem(ele){
	var oldTotal = $('#total').text();
	var amount = document.getElementById('amountCart').innerText;
    var productID = ele.getAttribute("data-id");
    var value = ele.getAttribute("value");
    var id = '#'+productID;
    var quantity = $(id).val();
    var price = ele.getAttribute("data-price");
    value === '-' ? quantity-- : quantity++;
    if(quantity >=1){
    	value === '-' ? amount-- : amount++;
    	$.ajax({
            url : "/cart/update/"+productID+"/"+quantity,
            type : "get",
            dataType:"text",
            data : {},
            success : function (result){
            	if(formatCurency(result) === oldTotal){
            		$('#error').show();
            		$('#amountCart').html(--amount);
            		$('#error').html("Sản phẩm "+ $('#name'+productID).text() +" chỉ còn " + --quantity + " sản phẩm!");
            	} else {
            		$('#error').hide();
            		$('#amountCart').html(amount);
                    $('#total').html(formatCurency(result));
                    $(id).val(quantity);
                    $('#total'+productID).html(formatCurency(quantity*price));
            	}
            }
        });
    }
}

function formatCurency(number) {
	var array = [],
	result = "",
	count = 0;
	var number = number.toString();
	if(number.length <3){return}
	for(var i = number.length-1; i>=0; i--){
	count+=1;
	array.push(number[i]);
	if(count==3 && i>=1){ array.push(".");
	count = 0;
	}
	}
	for(var i = array.length -1; i>=0; i--){
	result += array[i];
	}
	return result;
}


function formatMoney(amount, decimalCount = 2, decimal = ".", thousands = ",") {
	  try {
	    decimalCount = Math.abs(decimalCount);
	    decimalCount = isNaN(decimalCount) ? 2 : decimalCount;

	    const negativeSign = amount < 0 ? "-" : "";

	    let i = parseInt(amount = Math.abs(Number(amount) || 0).toFixed(decimalCount)).toString();
	    let j = (i.length > 3) ? i.length % 3 : 0;

	    return negativeSign + (j ? i.substr(0, j) + thousands : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousands) + (decimalCount ? decimal + Math.abs(amount - i).toFixed(decimalCount).slice(2) : "");
	  } catch (e) {
	    console.log(e)
	  }
	}

function removeCartItem(ele){
	var amount = document.getElementById('amountCart').innerText;
    var productID = ele.getAttribute("data-id");
    var id = '#'+productID;
    var quantity = $(id).val();
    var i = '#'+productID+'ItemCart';
    swal({
        title: 'Xác nhận!',
        text: "Bạn có muốn xóa sản phẩm ra khỏi giỏ hàng không?",
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'có',
        cancelButtonText: 'hủy'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url : "/cart/update/"+productID+"/"+0,
                type : "get",
                dataType:"text",
                data : {},
                success : function (result){
                    if(result === "0"){
                        location.reload();
                    } else {
                    	$('#amountCart').html(amount-quantity);
                    	$('#total').html(formatCurency(result));
                        $(i).hide();
                        swal({
                            title: 'Đã xóa',
                            text: 'Đã xóa sản phẩm ra khỏi giỏ hàng.',
                            showConfirmButton: false,
                            timer: 1500,
                        })
                    }
                }
            });
        }
    })
}


$(document).ready(function() {
	$('#error').hide();
    $('[data-toggle="tooltip"]').tooltip();
});

