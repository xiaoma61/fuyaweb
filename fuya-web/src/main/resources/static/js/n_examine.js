(function (){
　　"use strict";
	var num;//一页数据数

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
	
	success();//初始数据
	
	function success(){
		var s=current-1;
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/checkyuesaolist",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{start:s},    //参数值
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
				count=data.msg.pageSize;//页数	
				num=data.msg.total;

				for(var i=0;i<num;i++)
				{
					var tr; tr='<td>'+data.msg.list[i].NAME+'</td>'+'<td>'+data.msg.list[i].TYPE+'</td>'+'<td>'+data.msg.list[i].SENIORITY+'</td>'+'<td>'+data.msg.list[i].PHONE+'</td>'+'<td>'+data.msg.list[i].SCORE+'</td>'+'<td>'+data.msg.list[i].EDUCATION+'</td>'+'<td>'+data.msg.list[i].nums+'</td>'+'<td><a href="#" class="che" code="'+data.msg.list[i].USERSID+'">'+"查看"+'</a></td>'+'<td><a href="#" class="del" code="'+data.msg.list[i].USERSID+'">'+"删除"+'</a></td>';
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

		success();
	});
	
	//删除
	$(document).on('click', '.del',function() {	
		code = $(this).attr("code");
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/checkyuesaolist/delete",
			data:{id:code},
			type:"POST",
			dataType:"json",
			success: function(result){ 
				if(result.status==="success")
				  {	
					success();
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
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/checkyuesaolist/id",
			data:{id:code},
			type:"POST",
			dataType:"json",
			success: function(data){ 
				$("#see").css("display","block");//显示查看div
				$(".check_top").css("display","block");//显示关闭按钮
				$('.check_middle').html('<div class="check_middle_left"><div class="item"><span class="item_title">姓名：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.NAME+'</div></div><div class="item"><span class="item_title">电话号码：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.PHONE+'</div></div><div class="item"><span class="item_title">身份证：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.IDCARD+'</div></div><div class="item"><span class="item_title">年龄：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.AGE+'</div></div><div class="item"><span class="item_title">文化：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.EDUCATION+'</div></div><div class="item"><span class="item_title">籍贯：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.NATIVEPLACE+'</div></div><div class="item"><span class="item_title">邮箱：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.EMAIL+'</div></div><div class="item"><span class="item_title">身高体重：</span><div class="item_content2"><ul><li>'+data.msg[0].yuesobasicinfo.HEIGHT+'</li><em class="em">  </em><li>'+data.msg[0].yuesobasicinfo.WEIGHT+'</li><em>  </em></ul></div></div><div class="item"><span class="item_title">服务年限：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.SENIORITY+'</div></div><div class="item"><span class="item_title">类型：</span><div class="item_content">'+data.msg[0].yuesobasicinfo.TYPE+'</div></div></div><div class="check_middle_right"><div class="n_picture"><img src="'+data.msg[0].yuesobasicinfo.PHOTO+'" height="200px" alt="" id="imgID"/></div><div id="imgfun"></div><div class="certify_content"><div class="certify"><div class="certify_picture"><img src="'+data.msg[0].proveinfo.SERVICEPICTURE+'" width="180px"  alt=""/></div><div class="certify_title">服务照片</div></div><div class="certify"><div class="certify_picture"><img src="'+data.msg[0].proveinfo.REPORT+'" width="180px"  alt=""/></div><div class="certify_title">反馈照片</div></div><div class="certify"><div class="certify_picture"><img src="'+data.msg[0].proveinfo.YUESAOSYNDROME+'" width="180px"  alt=""/></div><div class="certify_title">月嫂证明</div></div><div class="certify"><div class="certify_picture"><img src="'+data.msg[0].proveinfo.HEALTHCERTIFICATES+'" width="180px"  alt=""/></div><div class="certify_title">健康证明</div></div></div></div>');
			},
			error:function(){}
		});
	});

	
})();
