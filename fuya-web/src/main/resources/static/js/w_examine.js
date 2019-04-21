(function (){
　　"use strict";

		$(document).on('click','#confirm',function() {
			var v1=$('#hot1').val();
			var v2=$('#hot3').val();
			var v3=$('#editor').html();
			if(v2==="月嫂"){v2=1;}
			if(v2==="育婴师"){v2=2;}
			var sw=/^\s*$/;
			if(v1!==""&&v2!==""&&v3!==""&&!sw.test(v3))
			{	
				$.ajax({
							url:"https://campus.gbdev.cn:8060/fuyaweb/admin/article/add",    //请求的url地址
							dataType:"json",   //返回格式为json
							async:true,//请求是否异步，默认为异步，这也是ajax重要特性
							data:{TYPE:v1,
								  TITLE:v2,
								  CONTENT:v3},    //参数值
							type:"GET",   //请求方式
							beforeSend:function(){
								//请求前的处理
							},
							success:function(){
								//请求成功时处理
								alert("成功");


							},
							complete:function(){
								//请求完成的处理
							},
							error:function(){
								//请求出错处理
							}
						});	}
		});
	
	$(document).on('focus','#hot3',function(){				
				var d;
				d='<li>月嫂</li><li>育婴师</li>';
				$('.hide ul').html(d);
				showhide();
	});
	
	function showhide(){
		$('.hide').css("display","block");
		$('.hide').css("top",$('#hot3').offset().top+30);
		$('.hide').css("left",$('#hot3').offset().left);
	}
	
	function hide(){
		$('.hide').css("display","none");
	}
	
	//关闭搜索结果
	$(document).on('blur','#hot3',function(){
		setTimeout(hide,100);
	});
	
	
	//点击搜索结果
	$(document).on('click','.hide ul>li',function(){
		$('#hot3').val($(this).html());
	});
	
})();
