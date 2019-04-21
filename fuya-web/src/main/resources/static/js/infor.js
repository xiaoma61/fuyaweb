(function (){
　　"use strict";
	success1();
	success2();
	var id;
/*	function evil(fn) {
	var Fn = Function; //一个变量指向Function，防止有些前端编译工具报错
	return new Fn('return ' + fn)();
	}*/
	
	
	function success1(){
		$.ajax({
			url:"../../data1.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{compamy:id},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				$('td').eq(0).html('<img src="../../img/y1.jpg" width="180px" alt=""/>');
				for(var i=2;i<21;){
					$('td').eq(i).html(data.data1[0].name);
					i=i+2;	
				}
				
				//修改
				$(document).on('click','#edit',function(){
					$('td').eq(0).html('<img src="../../img/y1.jpg" width="180px" alt="" id="imgID"/><div class="file-box"><label for="fil">上传图片</label><input type="file" name="fil" class="file-btn" id="fil" onchange="imgfun(fil,imgID)"></div>');
					for(var i=2;i<21;){
						$('td').eq(i).html("");
						$('td').eq(i).append('<input type="text" style="width:90%;height:90%" value="'+data.data1[0].name+'" />');
						i=i+2;
					}
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
			url:"../../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{compamy:id},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				$('.certify_content').html("");
				for(var i=0;i<3;i++){
					var d;
					d='<div class="certify"><div class="certify_picture"><img src="../../img/certificate.jpg" width="180px"  alt=""/><a href="#" class="delete" code="'+data.data[i].CN+'"></a></div><div class="certify_title">健康证明</div></div>';
					$('.certify_content').append(d);
				}
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
	
	
/*	//回显头像
	$(document).on('click','#fil',function(){
    var fils=$('#fil').get(0).files[0];
	var srcc=window.URL.createObjectURL(fils);
    $.ajax({
        url:'../../data1.json',
        type:'get',
        dataType:'json',
		data: {c:srcc}, 
		success:function(result){
			var objs;
			if((typeof result==='object')&&result.constructor===Object){
				objs=result;
			}else{
				objs=evil("(" +result+ ")");
			}
			$('#imgID').attr("width","180px");
			$('#imgID').attr("src",objs.paths);
		},
		error:function(){alert("8");}
	});
	});
	
	//回显证书
	$(document).on('click','#pict',function(){alert("1");
		var fils=$('#pict').get(0).files[0];
		var srcc=window.URL.createObjectURL(fils);
		$.ajax({
			url:'../../data1.json',
			type:'get',
			dataType:'json',
			data: {c:srcc}, 
			success:function(result){
				var objs;
				if((typeof result==='object')&&result.constructor===Object){
					objs=result;
				}else{
					objs=evil("(" +result+ ")");
				}
				$('#addimgID').attr("width","180px");
				$('#addimgID').attr("src",objs.paths);
				$('.add').css("display","block");
			},
			error:function(){alert("8");}
		});
	});
	*/

	
})();