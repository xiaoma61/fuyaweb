(function (){
　　"use strict";

	var current=1;//当前页数
	var code;
	var count;//页数

	var pageIcon=[];//创建icon数组
	var pageN=[];//创建页数按钮数组
	//pageIcon[]分别赋值
	pageIcon[0]='<a href="#top"><span  class="pageIcon" selectPage="fir" ><img src="../img/bb.png" width="25" height="25" alt=""/></span></a>';//第一页
	pageIcon[1]='<a href="#top"><span  class="pageIcon"  selectPage="pre"><img src="../img/b.png" width="25" height="25" alt=""/></span></a>';//前一页
	pageIcon[2]='<a href="#top"><span  class="pageIcon"  selectPage="next"><img src="../img/a.png" width="25" height="25" alt=""/></span></a>';//下一页
	pageIcon[3]='<a href="#top"><span  class="pageIcon"  selectPage="las"><img src="../img/aa.png" width="25" height="25" alt=""/></span></a>';//最后一页
	
	success();//初始数据
	
	function success(){
		var s=current-1;
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/recruit",    //请求的url地址
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
				$("#show").html('');
				
				
				/*声明必要变量*/
				count=data.msg.totalPages;//页数	
				
				/**/

				for(var i=0;i<data.msg.numberOfElements;i++)
				{
					var tr; 
					tr='<div class="item" c='+data.msg.content[i].usersid+'><table><tr><th>'+data.msg.content[i].position+'</th><th align="right"></th></tr><tr><td><span class="_salary">'+data.msg.content[i].salary+'</span><ul><li>'+data.msg.content[i].workarea+'</li><li>1-3年</li><li style="border: none">'+data.msg.content[i].education+'</li></ul></td><td></td></tr><tr><td><span class="_tag">五险一金</span><span class="_tag">加班补助</span><span class="_tag">带薪年假</span><span class="_tag">员工旅游</span><span class="_tag">节日福利</span></td><td><div  class="check" code="'+data.msg.content[i].usersid+'">查看职位</div></td></tr></table></div>';
					$("#show").append(tr);
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
	
	$(document).on('click','.check',function(){
		code = $(this).attr("code");
		window.open('recruit.html?id='+code);

	});
	
	$(document).on('click','.search',function(){
		alert($('#city').val());
		$.ajax({
			url:"",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{id:code},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){
				//请求成功时处理
				
	
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});	
	});

	$(document).on('click','#hot td',function(){
		var address=$(this).html();
		var i=$('#hot td').index(this);
		for(var j=0;j<$("#hot td").length;j++)
			{
				if(j!==i){$("#hot td").eq(j).removeClass("clicked");}
			}
		$("#hot td").eq(i).addClass("clicked");
		$.ajax({
			url:"../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{currentpage:address},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){
				//请求成功时处理
				alert(address+'搜索成功');
				
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});	
	});
	
	$(document).on('click','.salary li',function(){
		var money=$(this).html();
		var i=$('.salary li').index(this);
		for(var j=0;j<$(".salary li").length;j++)
			{
				if(j!==i){$(".salary li").eq(j).removeClass("clicked");}
			}
		$(".salary li").eq(i).addClass("clicked");
		$.ajax({
			url:"../data.json",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{currentpage:money},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){
				//请求成功时处理
				alert(money+'搜索成功');
				
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});	
	});
	
})();
