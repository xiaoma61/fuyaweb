(function (){
　　"use strict";
	success1();
	success2();
	success3();
	var r=0;
	function success1(){
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaos/personalinfo",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				if(data.msg[0].yuesobasicinfo.PHOTO==="file:")
				{$('td').eq(0).html('<img src="../../img/y1.jpg" width="180px" alt=""/><div id="name">'+data.msg[0].yuesobasicinfo.NAME+'</div><div class="level"><div id="star"></div></div>');}
				else{$('td').eq(0).html('<img src="'+data.msg[0].yuesobasicinfo.PHOTO+'" width="180px" alt=""/><div id="name">'+data.msg[0].yuesobasicinfo.NAME+'</div><div class="level"><div id="star"></div></div>');}
					$('td').eq(2).html(data.msg[0].yuesobasicinfo.NAME);
					$('td').eq(4).html(data.msg[0].yuesobasicinfo.AGE);
					$('td').eq(6).html(data.msg[0].yuesobasicinfo.NATIVEPLACE);
					$('td').eq(8).html(data.msg[0].yuesobasicinfo.SENIORITY);
					$('td').eq(10).html(data.msg[0].yuesobasicinfo.EDUCATION);
					$('td').eq(12).html(data.msg[0].yuesobasicinfo.PHONE);
					$('td').eq(14).html(data.msg[0].yuesobasicinfo.HEIGHT+'/'+data.msg[0].yuesobasicinfo.WEIGHT);
					$('td').eq(16).html(data.msg[0].yuesobasicinfo.EMAIL);
					$('td').eq(18).html(data.msg[0].yuesobasicinfo.IDCARD);
					$('td').eq(20).html(data.msg[0].yuesobasicinfo.WAGES);
					$('td').eq(22).html(data.msg[0].yuesobasicinfo.WORKAREA);
					if(data.msg[0].yuesobasicinfo.TYPE===1)
					{$('td').eq(24).html("月嫂");}
					else{$('td').eq(24).html("育婴师");}

				$("#star").attr("style","width:"+data.msg[0].yuesobasicinfo.LEVELS*6+"%");
				
				$("#fil").click();

			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});		
	}
	
	function success2(){
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaos/personalinfo",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				$('.certify_content').html("");

					var d;
					if(data.msg[0].proveinfo.HEALTHCERTIFICATES==="healthcertificates")
					{d='<div class="certify"><div class="certify_picture"><img src="../../img/certificate.jpg" width="180px"  alt=""/><a href="#" class="delete" ></a></div><div class="certify_title">健康证明</div></div>';}
					else{d='<div class="certify"><div class="certify_picture"><img src="'+data.msg[0].proveinfo.HEALTHCERTIFICATES+'" width="180px"  alt=""/><a href="#" class="delete"></a></div><div class="certify_title">健康证明</div></div>';}
					$('.certify_content').append(d);
					var e;
					if(data.msg[0].proveinfo.YUESAOSYNDROME==="yuesaosyndrome")
					{e='<div class="certify"><div class="certify_picture"><img src="../../img/certificate.jpg" width="180px"  alt=""/><a href="#" class="delete" ></a></div><div class="certify_title">月嫂证明</div></div>';}
					else{d='<div class="certify"><div class="certify_picture"><img src="'+data.msg[0].proveinfo.YUESAOSYNDROME+'" width="180px"  alt=""/><a href="#" class="delete"></a></div><div class="certify_title">月嫂证明</div></div>';}
					$('.certify_content').append(e);
					var f;
					if(data.msg[0].proveinfo.REPORT==="report")
					{f='<div class="certify"><div class="certify_picture"><img src="../../img/certificate.jpg" width="180px"  alt=""/><a href="#" class="delete" ></a></div><div class="certify_title">工作现场</div></div>';}
					else{d='<div class="certify"><div class="certify_picture"><img src="'+data.msg[0].proveinfo.REPORT+'" width="180px"  alt=""/><a href="#" class="delete"></a></div><div class="certify_title">服务图片</div></div>';}
					$('.certify_content').append(f);
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});	
	}
	
	function success3(){
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaos/skillinfo",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				$(".skill_content table").html('');
				for(var i=1;i<data.msg.length+1;i++)
				{
					var tr='<td width="10px">'+i+'、</td><td>'+data.msg[i].SKILL+'</td>';
					$(".skill_content table").append('<tr class="testtd">'+tr+'</tr>');
				}
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});	
	}
	
		$(document).on('click', '#tj1',function() {	
			r++;
			if(r%2===1)
			{	
				$('#tj1').html("提交");		
				var l=$('.skill_content table td').length+2;
				var tr='<td width="10px">'+l/2+'、</td><td>类型：<input type="text" id="type"/>内容：<input type="text" id="cont"/></td>';
				$(".skill_content table").append('<tr class="testtd">'+tr+'</tr>');
				$('.skill_content table td input').eq(0).focus(); 
			}
			if(r%2===0)
			{
				$('#tj1').html("添加");		
				$.ajax({
					url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaos/skillinfo/add",
					data:{skill:$(".skill_content td input").eq(1).val(),
						  type:$(".skill_content td input").eq(0).val()},
					type:"POST",
					dataType:"json",
					success: function(){ 
						success3();
						},
					error:function(){}
				});

			}
			});
	
})();