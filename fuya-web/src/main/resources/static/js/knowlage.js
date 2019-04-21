(function (){
　　"use strict";	
	var index=2;
	var url= window.location.href;
	index = url.substring(url.lastIndexOf('=') + 1);

		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/knowledge/id",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{id:index},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				$('.bar_td').eq(5).css('background-color','#f87c60');
				/*清空内容*/

				/**/
				$('.recommend').html('<ul><li>相关推荐</li><li><span class="tag">1</span><a href="#" class="check1">爸爸如何照顾宝宝</a></li><li><span class="tag">2</span><a href="#">怎么帮助孕妈一觉睡到天亮</a></li><li><span class="tag">3</span><a href="#">备孕期间需要准备什么东西</a></li><li><span class="_tag">4</span><a href="#">备孕中嗓子哑了怎么办</a></li><li><span class="_tag">5</span><a href="#">给备孕的人送什么礼物</a></li><li><span class="_tag">6</span><a href="#">备孕要用孕妇护肤品吗？</a></li><li><span class="_tag">7</span><a href="#">产后多久可以洗澡</a></li><li><span class="_tag">8</span><a href="#">水中分娩安全吗</a></li><li><span class="_tag">9</span><a href="#">无痛分娩有副作用吗</a></li><li><span class="_tag">10</span><a href="#">打无痛分娩针有危害么</a></li></ul>');
				$('.title').html(data.article.TITLE);
				$('#time').html(data.article.TIME);
				$('.substance').html('<pre>'+data.article.CONTENT+'<pre>');
	
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});		

})();
