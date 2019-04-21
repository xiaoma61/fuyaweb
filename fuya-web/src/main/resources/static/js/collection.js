(function (){
　　"use strict";
	var current1=1;//当前页数

	var count1;//页数

	
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
			data:{currentpage:current1},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){
				count1=3;//页数	
				$("#page").html('');
				if(count1!==1){

					//pageN[]分别赋值
					for(var j=1;j<=count1;j++){	
						pageN[j-1]='<span class="page" selectPage="'+j+'">'+j+'</span>';
					 }
					if(count1<5){//少于3页
						$('#page').append(pageIcon[0]);
						$('#page').append(pageIcon[1]);
						for(var z=1;z<=count1;z++){
							$('#page').append(pageN[z-1]);
						}
						$('#page').append(pageIcon[2]);
						$('#page').append(pageIcon[3]);
						$('#page .page').eq(current1-1).addClass('pageselect');//给选中按钮添加样式
					}
					else{//大于等于3页
						addpage(current1,'#page',count1);
					}

				}
				for(var i=0;i<4;i++){
					$(".picture").eq(i).attr("style","background:url('../../img/y1.jpg') no-repeat center center; background-size:auto 100%"); 
					$(".nam").eq(i).html("刘淑芬"+i+current1);
					$(".star").eq(i).attr("style","width:"+5*7+"%");
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
		success1();
	});
	
	
})();
