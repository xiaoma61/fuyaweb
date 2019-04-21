(function(){
	"use strict";
	var f='<div class="choose"><span>取消预定</span><span id="enter">进入支付</span><span id="go_chat">进入聊天</span></div>';
	var s='<div class="choose"><span id="leave">退款</span><span id="go_chat">进入聊天</span></div>';
	var e='<table><tr><td>总体评价：</td><td id="td1"><div class="_star"></div><div class="_star"></div><div class="_star"></div><div class="_star"></div><div class="_star"></div></td></tr><tr><td>服务态度：</td><td id="td2"><div class="_star"></div><div class="_star"></div><div class="_star"></div><div class="_star"></div><div class="_star"></div></td></tr><tr><td>总体评价：</td><td id="td3"><div class="_star"></div><div class="_star"></div><div class="_star"></div><div class="_star"></div><div class="_star"></div></td></tr></table><div class="evaluate"><textarea placeholder="请在此输入文字信息"></textarea></div><div class="release">发布</div>';
	success();
	function success(){
		$.ajax({
			url:"../../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{currentpage:2},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){

				$(".picture").attr("style","background:url('../../img/y1.jpg') no-repeat center center; background-size:auto 100%"); 
				$("#nam").html("刘淑芬");
				$("#star").attr("style","width:"+5*7+"%");
				$('.introduce_right').html(f);
				$('.schedule1').addClass("schedule4");
				$('.schedule1 .label1').addClass("label2");
				$('.table').css("display","none");
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});		
	}
	
	$(document).on('click','#enter',function(){
		$('.introduce_right').html(s);
		$('.schedule2 .label1').addClass("label2");
		$('.schedule1 .label1').removeClass("label2");
		$('.schedule2').addClass("schedule4");
		$('.schedule1').removeClass("schedule4");
		$('.table').css("display","block");
	});
	
	$(document).on('click','#leave',function(){
		$('.introduce_right').html(e);
		$('.schedule3 .label1').addClass("label2");
		$('.schedule2 .label1').removeClass("label2");
		$('.schedule3').addClass("schedule4");
		$('.schedule2').removeClass("schedule4");
		$('.content').css("background-color","#fcfbf6");
		$('.table').css("display","none");
	});
	
	$(document).on('click','#go_chat',function(){
		$('.gochat').css("display","block");
	});
	
	$(document).on('click','.close',function(){
		$('.gochat').css("display","none");
	});
	
	$(document).on('click','td:odd div',function(){
		var id=$(this).parent().attr('id');
        var r=$("#"+id+" div").index(this)+1;
        //alert("当前下标为："+r); 
		var w=5-r;
		change_star(id,r,w);
	});
	
	function change_star(id,r,w){
		$("#"+id).html("");
		for(var i=0;i<r;i++){
			var star='<div class="star"></div>';
			$("#"+id).append(star);
		}
		for(var j=0;j<w;j++){
			var _star='<div class="_star"></div>';
			$("#"+id).append(_star);
		}
	}
	
	var link={  　　　　　　　　　　//jQuery的AJAX执行的配置对象

		  type:"GET",　　　　　　//设置请求方式，默认为GET，

		  async:true,　　　　　　//设置是否异步，默认为异步

		  url:"customback.php",

		  dataType:"json",　　　　//设置期望的返回格式，因服务器返回json格式，这里将数据作为json格式对待

		  success:function (msg){
			  	for(var i=0;i<msg.length;i++)
				{
					var chat='<div class="l_chat"><div class="l_pic"><img src="../../img/a2.jpg"  height="30" alt=""/></div><div class="l_c"><div class="l_cha">'+msg+'</div></div></div>';
			  		$('#show_chat').append(chat);
				}
				setTimeout($.ajax(link),300);

		  }　　　　　　　　　　　　　　//成功时的回调函数，处理返回数据，并且延时建立新的请求连接

	};

	$.ajax(link);　　　　　　　　　　//执行ajax请求。
	
	

	
	$(document).on('click','.send',function(){
		if ($('#fs').val()!=="")
		{
		$.ajax({
			url:"../../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{c:$('#fs').val()},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){
				var chat='<div class="r_chat"><div class="r_pic"><img src="../../img/a2.jpg"  height="30" alt=""/></div><div class="r_c"><div class="r_cha">'+$('#fs').val()+'</div></div></div>';
				$('#show_chat').append(chat);
				$('#fs').val("");
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});	
			}
	});

	
	
})();