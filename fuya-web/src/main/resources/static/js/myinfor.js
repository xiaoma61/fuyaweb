(function (){
　　"use strict";
	success1();
	success2();
	success3();
	var id;
	var r=0;
	var f=0;
	var fordate1;
	var fordate2;
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
				
				//修改
				$(document).on('click','#edit',function(){
				if(data.msg[0].yuesobasicinfo.PHOTO==="file:")
				{$('td').eq(0).html('<img src="../../img/y1.jpg" width="180px" alt="" id="imgID"/><div class="file-box"><label for="fil">上传图片</label><input type="file" name="fil" class="file-btn" id="fil" onchange="imgfun(fil,imgID)"></div>');}
				else{$('td').eq(0).html('<img src="'+data.msg[0].yuesobasicinfo.PHOTO+'" width="180px" alt="" id="imgID"/><div class="file-box"><label for="fil">上传图片</label><input type="file" name="fil" class="file-btn" id="fil" onchange="imgfun(fil,imgID)"></div>');}

					for(var i=2;i<25;){
						$('td').eq(i).html("");
						i=i+2;
					}
					$('td').eq(2).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.NAME+'" />');
					$('td').eq(4).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.AGE+'" />');
					$('td').eq(6).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.NATIVEPLACE+'" />');
					$('td').eq(8).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.SENIORITY+'" />');
					$('td').eq(10).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.EDUCATION+'" />');
					$('td').eq(12).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.PHONE+'" />');
					$('td').eq(14).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.HEIGHT+'/'+data.msg[0].yuesobasicinfo.WEIGHT+'" />');
					$('td').eq(16).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.EMAIL+'" />');
					$('td').eq(18).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.IDCARD+'" />');
					$('td').eq(20).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.WAGES+'" />');
					$('td').eq(22).append('<input type="text" style="width:90%;height:90%" value="'+data.msg[0].yuesobasicinfo.WORKAREA+'" />');
					if(data.msg[0].yuesobasicinfo.TYPE===1)
					{$('td').eq(24).append('<input type="text" style="width:90%;height:90%" value="月嫂" />');}
					else{$('td').eq(24).append('<input type="text" style="width:90%;height:90%" value="育婴师" />');}
					
					$('#change').html('<img src="../../img/save.png" width="25px"  alt=""   id="save"/>');
				});
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
				
				$('.certify_content').append('<div class="certify"><div class="certify_add"><input type="file" name="pict" class="add-btn" id="pict" onchange="addimg()" ></div></div><div style="clear:both"></div>');
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
			url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaos/personalinfo",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){
				//请求成功时处理
				$(".skill_content table").html('');
				for(var i=1;i<4;i++)
				{
					var tr='<td width="10px">'+i+'、</td><td>哈哈哈哈哈哈哈</td>';
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
	
	function success4(){
		$.ajax({
			url:"../../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{compamy:id},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){
				//请求成功时处理
				$(".skill_content table").html('');
				for(var i=1;i<4;i++)
				{
					var tr='<td width="10px">'+i+'、</td><td>哈哈哈哈哈哈哈</td>';
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
	
	//取消添加证书
	$(document).on('click','#cancel',function(){
		$(".add").css("display","none");
		$('.add_title input').eq(0).val("");//清空
	});
	
	//确认添加证书
	$(document).on('click','#confirm',function(){
		var flag=0;
		if($('.add_title input').eq(0).val()===""){
					$('.add_title input').eq(0).focus(); 
					flag++;
			  }
		if(flag===0){
		var fordate=new FormData();
		var fils=$('#pict').get(0).files[0];
		fordate.append("pic",fils);
		fordate.append("a",$('.add_title input').eq(0).val());
		$.ajax({
			url:"../../data1.json",
			data:fordate,
			type:"POST",
			dataType:"json",
			processData : false,
			contentType : false,
			cache: false,
			success: function(data){
					if(data.status===1){
						alert("保存成功!");
					}
					else{
						alert("保存失败");
					}
					success2();
					$('.add').css("display","none");
					$('.add_title input').eq(0).val("");//清空
				},
			error:function(){}
		});
		}
	});
	

	
		//点击保存
		$(document).on('click','#save',function(){
			var flag=0;
			for(var i=1;i<=$('td input').length;i++){ 	
				  if($('td input').eq(i).val()===""){
						$('td input').eq(i).focus(); 
						flag++;
				  }
			}
			if(flag===0){
			var fordate=new FormData();
			var fils=$('#fil').get(0).files[0];
			fordate.append("pic",fils);
			fordate.append("a",$('td input').eq(1).val());
			fordate.append("b",$('td input').eq(2).val());
			fordate.append("c",$('td input').eq(3).val());
			fordate.append("d",$('td input').eq(4).val());
			fordate.append("e",$('td input').eq(5).val());
			fordate.append("f",$('td input').eq(6).val());
			fordate.append("g",$('td input').eq(7).val());
			fordate.append("h",$('td input').eq(8).val());
			fordate.append("i",$('td input').eq(9).val());
			fordate.append("j",$('td input').eq(10).val());
			$.ajax({
				url:"../../data1.json",
				data:fordate,
				type:"POST",
				dataType:"json",
				processData : false,
				contentType : false,
				cache: false,
				success: function(data){
					if(data.status===1){
						alert("保存成功!");
					}
					else{
						alert("保存失败");
					}
					success1();
					},
				error:function(){}
			});
				$('#change').html('<img src="../../img/edit.png" width="30px"  alt=""  id="edit"/>');
			}
		});
	
	
		//删除证书
		$(document).on('click', '.delete',function() {	
			var code = $(this).attr("code");
			$.ajax({
				url:"../../data.json",
				data:{c:code},
				type:"POST",
				dataType:"json",
				success: function(data){ 
				if(data.status===1)
				  {
					alert("删除成功!");
				  } 
				  else
				  {
					alert("删除失败！"); 
				  }
					success2();
					},
				error:function(){}
			});
		});
	
		$(document).on('click', '#tj1',function() {	
			r++;
			if(r%2===1)
			{	
				$('#tj1').html("提交");		
				var l=$('.skill_content table td').length+2;
				var tr='<td width="10px">'+l/2+'、</td><td><input type="text"/></td>';
				$(".skill_content table").append('<tr class="testtd">'+tr+'</tr>');
				$('.skill_content table td input').eq(0).focus(); 
			}
			if(r%2===0)
			{
				$('#tj1').html("添加");		
				$.ajax({
					url:"../../data.json",
					data:{c:$(".skill_content td input").val()},
					type:"POST",
					dataType:"json",
					success: function(){ 
						if(f===0)
						{success3();}
						if(f===1){success4();}
						},
					error:function(){}
				});

			}
			});
	
		$(document).on('click', '#baby',function() {
				f=0;
				$('#baby').css('background-color','#f07053');
				$('#mother').css('background-color','#FF0004');
				success3();
			});
	
		$(document).on('click', '#mother',function() {	
				f=1;
				$('#mother').css('background-color','#f07053');
				$('#baby').css('background-color','#FF0004');
				success4();
			});
	
})();