(function(){
	"use strict";

	
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
	
	$(document).bind("resize","#show_chat",function(){
		$("#show_chat").scrollTo("100%");
		alert("1");
	});
	
})();