(function (){
　　"use strict";
	var current1=1;//当前页数
	var current2=1;//当前页数
	var count1;//页数
	var count2;//页数
	
	var pageIcon=[];//创建icon数组
	var pageN=[];//创建页数按钮数组
	//pageIcon[]分别赋值
	pageIcon[0]='<a href="#top"><span  class="pageIcon" selectPage="fir" ><img src="../../img/bb.png" width="25" height="25" alt=""/></span></a>';//第一页
	pageIcon[1]='<a href="#top"><span  class="pageIcon"  selectPage="pre"><img src="../../img/b.png" width="25" height="25" alt=""/></span></a>';//前一页
	pageIcon[2]='<a href="#top"><span  class="pageIcon"  selectPage="next"><img src="../../img/a.png" width="25" height="25" alt=""/></span></a>';//下一页
	pageIcon[3]='<a href="#top"><span  class="pageIcon"  selectPage="las"><img src="../../img/aa.png" width="25" height="25" alt=""/></span></a>';//最后一页
	
	success1();//初始数据
	success2();
	function success1(){
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaos/recentOrder",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				count1=data.msg.length;//页数	
				$("#page").html('');
				if(count1!==1){

					//pageN[]分别赋值
					for(var j=1;j<=count1;j++){	
						pageN[j-1]='<span class="page" selectPage="'+j+'">'+j+'</span>';
					 }
					if(count1<5){//少于5页
						$('#page').append(pageIcon[0]);
						$('#page').append(pageIcon[1]);
						for(var z=1;z<=count1;z++){
							$('#page').append(pageN[z-1]);
						}
						$('#page').append(pageIcon[2]);
						$('#page').append(pageIcon[3]);
						$('#page .page').eq(current1-1).addClass('pageselect');//给选中按钮添加样式
					}
					else{//大于等于5页
						addpage(current1,'#page',count1);
					}

				}	
				$("#EDC").html(data.msg[current1-1].servicecontent.CHILDBIRTH.year+'-'+data.msg[current1-1].servicecontent.CHILDBIRTH.month+'-'+data.msg[current1-1].servicecontent.CHILDBIRTH.date);
				$("#ServiceBegin").html(data.msg[current1-1].servicecontent.STARTTIME.year+'-'+data.msg[current1-1].servicecontent.STARTTIME.month+'-'+data.msg[current1-1].servicecontent.STARTTIME.date);
				$("#ServiceDays").html(data.msg[current1-1].servicecontent.FATE);
				$("#deposit").html(data.msg[current1-1].servicecontent.HANDSEL);
				$("#price").html(data.msg[current1-1].servicecontent.PRICE);
				$("#remarks").html(data.msg[current1-1].servicecontent.OTHERS);
				if(data.msg[current1-1].servicecontent.STATUS===1)
				{$("#state").html("未完成");}
				else{$("#state").html("已完成");}
				$("#sum").html(data.msg[current1-1].servicecontent.SUM);
				$("#employer").html(data.msg[current1-1].employerinformation.NAME);
				$("#area").html(data.msg[current1-1].employerinformation.AREA);
				$("#address").html(data.msg[current1-1].employerinformation.ADDRESS);
				$("#number").html(data.msg[current1-1].employerinformation.PHONE);
				$(".p2").eq(0).html();
				$(".p3").eq(0).html();
				$(".p4").eq(0).html('合同号：'+data.msg[current1-1].orders.CONTRACTNUMBER);
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
			url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaos/historyOrder",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				count2=data.msg.length;//页数	
				$("#h_page").html('');
				if(count2!==1){

					//pageN[]分别赋值
					for(var j=1;j<=count2;j++){	
						pageN[j-1]='<span class="page" selectPage="'+j+'">'+j+'</span>';
					 }
					if(count2<5){//少于3页
						$('#h_page').append(pageIcon[0]);
						$('#h_page').append(pageIcon[1]);
						for(var z=1;z<=count2;z++){
							$('#h_page').append(pageN[z-1]);
						}
						$('#h_page').append(pageIcon[2]);
						$('#h_page').append(pageIcon[3]);
						$('#h_page .page').eq(current2-1).addClass('pageselect');//给选中按钮添加样式
					}
					else{//大于等于3页
						addpage(current2,'#h_page',count2);
					}

				}	

				$("#h_EDC").html(data.msg[current2-1].servicecontent.CHILDBIRTH.year+'-'+data.msg[current2-1].servicecontent.CHILDBIRTH.month+'-'+data.msg[current2-1].servicecontent.CHILDBIRTH.date);
				$("#h_ServiceBegin").html(data.msg[current2-1].servicecontent.STARTTIME.year+'-'+data.msg[current2-1].servicecontent.STARTTIME.month+'-'+data.msg[current2-1].servicecontent.STARTTIME.date);
				$("#h_ServiceDays").html(data.msg[current2-1].servicecontent.FATE);
				$("#h_deposit").html(data.msg[current2-1].servicecontent.HANDSEL);
				$("#h_price").html(data.msg[current2-1].servicecontent.PRICE);
				$("#h_remarks").html(data.msg[current2-1].servicecontent.OTHERS);
				if(data.msg[current2-1].servicecontent.STATUS===1)
				{$("#h_state").html("是");}
				else{$("#h_state").html("否");}
				$("#h_sum").html(data.msg[current2-1].servicecontent.SUM);
				$("#h_employer").html(data.msg[current2-1].employerinformation.NAME);
				$("#h_area").html(data.msg[current2-1].employerinformation.AREA);
				$("#h_address").html(data.msg[current2-1].employerinformation.ADDRESS);
				$("#h_number").html(data.msg[current2-1].employerinformation.PHONE);
				$(".p2").eq(1).html(data.msg[current2-1].comments.CONTENT);
				$(".p3").eq(1).html();
				$(".p4").eq(1).html('合同号：'+data.msg[current2-1].orders.CONTRACTNUMBER);
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
		function addpage(current,id,count){
			current=parseInt(current);
			$(id).html('');//清空按钮
			$(id).append(pageIcon[0]);
			$(id).append(pageIcon[1]);
			if(current===1){//如果选中第一页
				for(var j=1;j<=5;j++){	
					$(id).append(pageN[j-1]);
				}
					$(''+id+' .page').eq(0).addClass('pageselect');
			}
			if(current===2){//如果选中第2页
				for(var z=1;z<=5;z++){	
					$(id).append(pageN[z-1]);
				}
					$(''+id+' .page').eq(1).addClass('pageselect');
			}
			if(current===count-1){//如果选中倒数第2页
				for(var b=count-4;b<=count;b++){	
					$(id).append(pageN[b-1]);
				}
				$(''+id+' .page').eq(3).addClass('pageselect');
			}
			else if(current===count){//如果选中最后一页
				for(var a=count-4;a<=count;a++){	
					$(id).append(pageN[a-1]);
				}
				$(''+id+' .page').eq(4).addClass('pageselect');
			}
			else if(current!==1&&current!==count&&current!==2&&current!==count-1){//其他
				var be=current-2;
				var af=current+2;
				for(var e=be;e<=af;e++){	
					$(id).append(pageN[e-1]);
				}
				$(''+id+' .page').eq(2).addClass('pageselect');
			}
			$(id).append(pageIcon[2]);
			$(id).append(pageIcon[3]);
		}
		

		

	
	
	//点击
	$(document).on('click', '#page span',function() {	
		current1=parseInt(current1);
		var selectPage=$(this).attr('selectPage');//获取当前点击按钮的页数
		$("#page .page").removeClass('pageselect');//取消全部按钮的选中样式
		if(selectPage==="fir"||(selectPage==="pre"&&current1===1)){
			selectPage=1;
		}
		if(selectPage==="las"||(selectPage==="next"&&current1===count1)){
			selectPage=count1;
		}
		if(selectPage==="pre"&&current1!==1){
			selectPage=current1-1;
		}
		if(selectPage==="next"&&current1!==count1){
			selectPage=current1+1;
		}
		current1=selectPage;
		$('.order_number').eq(0).html(current1);
		success1();
	});
	
	//点击
	$(document).on('click', '#h_page span',function() {	
		current2=parseInt(current2);
		var selectPage=$(this).attr('selectPage');//获取当前点击按钮的页数
		$("#h_page .page").removeClass('pageselect');//取消全部按钮的选中样式
		if(selectPage==="fir"||(selectPage==="pre"&&current2===1)){
			selectPage=1;
		}
		if(selectPage==="las"||(selectPage==="next"&&current2===count2)){
			selectPage=count2;
		}
		if(selectPage==="pre"&&current2!==1){
			selectPage=current2-1;
		}
		if(selectPage==="next"&&current2!==count2){
			selectPage=current2+1;
		}
		current2=selectPage;
		$('.order_number').eq(1).html(current2);
		success2();
	});
	
})();
