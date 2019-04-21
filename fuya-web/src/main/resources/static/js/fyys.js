(function (){
　　"use strict";
	var num;
	var current=1;//当前页数
	var code;
	var count;//页数
	var na=null;
	var ty=null;
	var nat=null;
	var minage=null;
	var maxage=null;
	var minwages=null;
	var maxwages=null;
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
			url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaoindex/search/index",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{start:s,
				  name:na,
				  type:ty,
				  nativeplace:nat,
				  minage:minage,
				  maxage:maxage,
				  minwages:minwages,
				  maxwages:maxwages
				 },    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				setTimeout(function(){$('.bar_td').eq(1).css('background-color','#f87c60');},100);
				/*清空内容*/
				$("#main").html('');
				
				
				/*声明必要变量*/
				count=data.msg.totalPages;//页数	
				num=data.msg.size;
				/**/

				for(var i=0;i<num;i++)
				{
					var tr; 
					var ph;
					var ne;
					var st="";
					var ar,ty,cr;
					tr='<div class="content_example"><div class="content_example_left">';
					if(data.msg.content[i].PHOTO==="file:")
					{ph='<img src="../img/y1.jpg" width="100%" alt=""/>';}
					else{
                        ph='<img src="'+data.msg.content[i].PHOTO+'" width="100%" alt=""/>';
						}
					ne=data.msg.content[i].NAME+'<br>';
					for(var a=0;a<data.msg.content[i].LEVELS;a++)
					{
						st +='<img src="../img/star-.png" width="7%"/>';
					}
					ar='</div><div class="content_example_right">年龄：'+data.msg.content[i].AGE+'<br>类型：';
					if(data.msg.content[i].TYPE===1)
					{ty="月嫂";}
					else{ty="育婴师";}
					cr='<br>籍贯：'+data.msg.content[i].NATIVEPLACE+'<br>工资：'+data.msg.content[i].WAGES+'/月</div><div class="content_example_more"><a href="#" class="check" code="'+data.msg.content[i].USERSID+'"><u>查看更多</u></a></div></div>';
					$("#main").append(tr+ph+ne+st+ar+ty+cr);
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
                alert("error");
			},
			error:function(){
				//请求出错处理
                alert("error");
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
		window.open('introduce.html?id='+code);
	});

	$(document).on('click','.type li',function(){
		var money=$(this).html();
		var i=$('.type li').index(this);
		$(".type li").eq(i).addClass("clicked");
		for(var j=0;j<$(".type li").length;j++)
			{
				if(j!==i){$(".type li").eq(j).removeClass("clicked");}
			}
		if(money==="育婴师"){ty=2;}
		if(money==="月嫂"){ty=1;}
		if(money==="所有"){ty=null;}
		success();
	});
	
	$(document).on('click','.salary li',function(){
		var money=$(this).html();
		var i=$('.salary li').index(this);
		$(".salary li").eq(i).addClass("clicked");
		for(var j=0;j<$(".salary li").length;j++)
			{
				if(j!==i){$(".salary li").eq(j).removeClass("clicked");}
			}
		if(money==="3000以下"){minwages=0;maxwages=3000;}
		if(money==="3000-6000"){minwages=3000;maxwages=6000;}
		if(money==="6000-9000"){minwages=6000;maxwages=9000;}
		if(money==="9000以上"){minwages=9000;maxwages=1000000;}
		if(money==="所有"){minwages=null;maxwages=null;}
		success();
	});
	
	$(document).on('click','.age li',function(){
		var money=$(this).html();
		var i=$('.age li').index(this);
		$(".age li").eq(i).addClass("clicked");
		for(var j=0;j<$(".age li").length;j++)
			{
				if(j!==i){$(".age li").eq(j).removeClass("clicked");}
			}
		if(money==="35以下"){minage=0;maxage=35;}
		if(money==="35-45"){minage=35;maxage=45;}
		if(money==="45-50"){minage=45;maxage=50;}
		if(money==="50以上"){minage=50;maxage=100;}
		if(money==="所有"){minage=null;maxage=null;}
		success();
	});
	
	$(document).on('click','.native li',function(){
		var money=$(this).html();
		var i=$('.native li').index(this);
		$(".native li").eq(i).addClass("clicked");
		for(var j=0;j<$(".native li").length;j++)
			{
				if(j!==i){$(".native li").eq(j).removeClass("clicked");}
			}
		if(money==="所有"){money=null;}
		nat=money;
		success();
	});
	
	//搜索名字
	$(document).on('click','#search',function(){
		na=$("#hot").val();
		current=1;
		success();
	});
	
	//点击输入框
	$(document).on('focus','#hot',function(){	
		if ($("#hot").val()!=="") {
			showhide();
			
		}
	});
	
	function showhide(){
		$('.hide').css("display","block");
		$('.hide').css("top",$('#hot').offset().top+35);
		$('.hide').css("left",$('#hot').offset().left);
	}
	
	function hide(){
		$('.hide').css("display","none");
	}
	
	//关闭搜索结果
	$(document).on('blur','#hot',function(){
		setTimeout(hide,100);
	});
	
	//输入框输入文字
	$(document).bind('input propertychange',function(){
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaoindex/search/index",
			data:{name:$("#hot").val()},
			type:"POST",
			dataType:"json",
			success: function(result){ 
				$('.hide ul').html("");//清空
				for(var i=0; i<result.msg.list[i].length;i++){					
					var d;
					d='<li>'+result.msg.list[i].NAME+'</li>';
					$('.hide ul').append(d);
				}
					showhide();
				},
			error:function(){}
		});
	});
	
	//点击搜索结果
	$(document).on('click','.hide ul>li',function(){
		$('#hot').val($(this).html());
		$("#search").click();
	});
	
	
})();
