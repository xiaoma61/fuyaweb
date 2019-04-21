(function(){
	"use strict";
	var num=10;//一页数据数
	var first=0;//第一条
	var last=first+num;//最后一条
	var current=1;//当前页数
	var code;
	var count;//页数

	var pageIcon=[];//创建icon数组
	var pageN=[];//创建页数按钮数组
	//pageIcon[]分别赋值
	pageIcon[0]='<a href="#top"><span  class="pageIcon" selectPage="fir" ><img src="../../img/bb.png" width="25" height="25" alt=""/></span></a>';//第一页
	pageIcon[1]='<a href="#top"><span  class="pageIcon"  selectPage="pre"><img src="../../img/b.png" width="25" height="25" alt=""/></span></a>';//前一页
	pageIcon[2]='<a href="#top"><span  class="pageIcon"  selectPage="next"><img src="../../img/a.png" width="25" height="25" alt=""/></span></a>';//下一页
	pageIcon[3]='<a href="#top"><span  class="pageIcon"  selectPage="las"><img src="../../img/aa.png" width="25" height="25" alt=""/></span></a>';//最后一页
	
	success1();//初始数据
	
	function success1(){
		$.ajax({
			url:"../../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{currentpage:current},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理

				/*清空内容*/
				$("#tabletest .testtd").html('');
				$("#num_show").html('');
				
				/*声明必要变量*/
				count=16;//页数	
				
				/**/
				$("#num_show").append("共"+data.data.length+"条"+"共"+count+"页");//显示数据数页数
				
				for(var i=first;i<last;i++)
				{
					
					var tr; tr='<td>'+data.data[i].CN+'</td>'+'<td>'+data.data[i].JN+'</td>'+'<td>'+data.data[i].name+'</td>'+'<td>'+data.data[i].type+'</td>'+'<td>'+data.data[i].employer+'</td>'+'<td>'+data.data[i].startTime+'</td>'+'<td>'+data.data[i].SD+'</td>'+'<td><a href="#" class="che" code="'+data.data[i].CN+'">'+"查看"+'</a></td>'+'<td><a href="#" class="rev" code="'+data.data[i].CN+'">'+"修改"+'</a></td>'+'<td><a href="#" class="del" code="'+data.data[i].CN+'">'+"删除"+'</a></td>';
					$("#tabletest").append('<tr class="testtd">'+tr+'</tr>');
				}
				
				
				$("#page").html('');
				/*初始化分页按钮*/
				if(count!==1){

					//pageN[]分别赋值
					for(var j=1;j<=count;j++){	
						pageN[j-1]='<a href="#top"><span class="page" selectPage="'+j+'">'+j+'</span></a>';
					 }


					if(count<5){//少于5页
						$('#page').append(pageIcon[0]);
						$('#page').append(pageIcon[1]);
						for(var z=1;z<=count;z++){
							$('#page').append(pageN[z-1]);
						}
						$('#page').append(pageIcon[2]);
						$('#page').append(pageIcon[3]);
						$('.page').eq(current-1).addClass('pageselect');//给选中按钮添加样式
					}
					else{//大于等于5页
						addpage();
					}

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
	
	function success2(){
		$.ajax({
			url:"../../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{currentpage:current},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				
				for(var j=0;j<8;j++){
					$('.item_content').eq(j).html('<input type="text" style="width:90%;height:80%" value="'+data.data[j].JN+'" />');
				}
				$('.item_content input[type=text]').eq(0).focus();
				for(var z=0;z<4;z++){
					$('.item_content2 li').eq(z).html('<input type="text" style="width:90%;height:80%" value="'+data.data[z].JN+'" />');
				}
				$('.item_content3').eq(0).html('<textarea style="OVERFLOW:hidden;width:90%;height:85%" /">'+data.data[j].JN+'</textarea>');
	
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
			url:"../../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{currentpage:current},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				for(var j=0;j<8;j++){
					$('.item_content').eq(j).html(data.data[j].JN);
				}
				for(var z=0;z<4;z++){
					$('.item_content2 li').eq(z).html(data.data[z].JN);
				}
				$('.item_content3').eq(0).html(data.data[j].JN);
	
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
		for(var j=0;j<8;j++){
			$('.item_content').eq(j).html('<input type="text" style="width:90%;height:80%"  />');
		}
		$('.item_content input[type=text]').eq(0).focus();
		for(var z=0;z<4;z++){
			$('.item_content2 li').eq(z).html('<input type="text" style="width:90%;height:80%"  />');
		}
		$('.item_content3').eq(0).html('<textarea style="OVERFLOW:hidden;width:90%;height:85%" /"></textarea>');
	}
	
		/*定义改变按钮的方法*/
		function addpage(){
			current=parseInt(current);
			$("#page").html('');//清空按钮
			$('#page').append(pageIcon[0]);
			$('#page').append(pageIcon[1]);
			if(current===1){//如果选中第一页
				for(var j=1;j<=5;j++){	
					$('#page').append(pageN[j-1]);
				}
					$('.page').eq(0).addClass('pageselect');
			}
			if(current===2){//如果选中第2页
				for(var z=1;z<=5;z++){	
					$('#page').append(pageN[z-1]);
				}
					$('.page').eq(1).addClass('pageselect');
			}
			if(current===count-1){//如果选中倒数第2页
				for(var b=count-4;b<=count;b++){	
					$('#page').append(pageN[b-1]);
				}
				$('.page').eq(3).addClass('pageselect');
			}
			else if(current===count){//如果选中最后一页
				for(var a=count-4;a<=count;a++){	
					$('#page').append(pageN[a-1]);
				}
				$('.page').eq(4).addClass('pageselect');
			}
			else if(current!==1&&current!==count&&current!==2&&current!==count-1){//其他
				var be=current-2;
				var af=current+2;
				for(var e=be;e<=af;e++){	
					$('#page').append(pageN[e-1]);
				}
				$('.page').eq(2).addClass('pageselect');
			}
			$('#page').append(pageIcon[2]);
			$('#page').append(pageIcon[3]);
		}
		

		

	
	
	//点击
	$(document).on('click', '#page span',function() {	
		current=parseInt(current);
		var selectPage=$(this).attr('selectPage');//获取当前点击按钮的页数
		$(".page").removeClass('pageselect');//取消全部按钮的选中样式
		if(selectPage==="fir"||(selectPage==="pre"&&current===1)){
			selectPage=1;
		}
		if(selectPage==="las"||(selectPage==="next"&&current===count)){
			selectPage=count;
		}
		if(selectPage==="pre"&&current!==1){
			selectPage=current-1;
		}
		if(selectPage==="next"&&current!==count){
			selectPage=current+1;
		}
		if(count<=5){
			$('.page').eq(selectPage-1).addClass('pageselect');
		}
		if(count>5){
			current=selectPage;
			addpage();
		}
		current=selectPage;
		first=(selectPage-1)*num;
		last=first+num;
		success1();
	});
	
	//删除
	$(document).on('click', '.del',function() {	
		code = $(this).attr("code");
		$.ajax({
			url:"../../data.json",
			data:{c:code},
			type:"POST",
			dataType:"json",
			success: function(result){ 
				if(result.status===1)
				  {	
					success1();
					alert("删除成功！");
				  } 
				  else
				  {
					alert("删除失败！"); 
				  }
			},
			error:function(){}
		});
	});
	
	//查看
	$(document).on('click','.che',function(){
	code=$(this).attr("code");
	$.ajax({
		url:"../../data.json",
		data:{c:code},
		type:"POST",
		dataType:"json",
		success: function(){ 
			$("#see").css("display","block");
			$(".check_top").css("display","block");
			$(".check_bottom").css("display","none");
			success3();
			},
		error:function(){}
	});
	});
		
	//修改
	$(document).on('click','.rev',function(){
		code=$(this).attr("code");
		$("#see").css("display","block");
		$(".check_top").css("display","none");
		$(".check_bottom").css("display","block");
		success2();
	});
	
	//保存
	$(document).on('click','#save',function(){
		var flag=0;
		if($('.item textarea').eq(0).val()===""){
			$('.item textarea').eq(0).focus();
			flag++;}
		for(var i=0;i<=$('.item input').length;i++){ 	
			  if($('.item input').eq(i).val()===""){
					$('.item input').eq(i).focus(); 
					flag++;
			  }
		}

		if(flag===0){
		$.ajax({
			url:"../../data.json",
			data:{c:code},
			type:"POST",
			dataType:"json",
			success: function(result){
				if(result.status===1)
				  {
					success1();
					alert("保存成功！");
					$("#see").css("display","none");
				  } 
				  else
				  {
					alert("保存失败！"); 
				  }			
				},
			error:function(){}
		});
		}
	});
	
	
	//取消
	$(document).on('click','#cancel',function(){
		$("#see").css("display","none");	
	});
	
	
	

	
	$(document).on('click','#add a:first',function(){
		code=$(this).attr("code");
		$("#see").css("display","block");
		$(".check_top").css("display","none");
		$(".check_bottom").css("display","block");
		$("#add1").css("display","block");
		success4();
	});
	


	
})();