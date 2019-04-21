(function (){
　　"use strict";
	

	var p=0;
	var n=0;
	var current=1;//当前页数
	var code;
	var count;//页数
	var ch;
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
			url:"https://campus.gbdev.cn:8060/fuyaweb/knowledge",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{type:p,
				  start:s},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(data){
				//请求成功时处理
				$('.bar_td').eq(5).css('background-color','#f87c60');
				/*清空内容*/
				$("#show").html('');
				$('#hot').attr('placeholder',data.searchResult.objects[0].TITLE);
				$('.top').eq(p).addClass("clicked").siblings().removeClass("clicked");
				/*声明必要变量*/
				count=data.searchResult.totalPage;//页数	
				/**/
				$('.recommend').html('<ul><li>相关推荐</li><li><span class="tag">1</span><a href="#" class="check1"  code=1>爸爸如何学习照顾满月的婴儿</a></li><li><span class="tag">2</span><a href="#"  class="check1"  code=16>备孕爸爸应该注意什么好</a></li><li><span class="tag">3</span><a href="#"  class="check1"  code=17>备孕吃什么下火</a></li><li><span class="_tag">4</span><a href="#"  class="check1"  code=18>备孕要用孕妇护肤品吗?</a></li><li><span class="_tag">5</span><a href="#"  class="check1" code=19 >备孕期间可以做美容吗？</a></li><li><span class="_tag">6</span><a href="#"  class="check1"  code=20>孕妇能做核磁共振吗？</a></li><li><span class="_tag">7</span><a href="#"  class="check1" code=21 >孕妇怎么补钙好</a></li><li><span class="_tag">8</span><a href="#"  class="check1"  code=22>孕妇一直便秘怎么办</a></li><li><span class="_tag">9</span><a href="#" class="check1"  code=23>孕妇夏季上火怎么办？</a></li><li><span class="_tag">10</span><a href="#" class="check1" code=24 >孕妇感冒怎么办</a></li></ul>');
				for(var i=0;i<data.searchResult.resultCount;i++)
				{
					var tr; 
					tr='<div class="item" c='+data.searchResult.objects[i].ARTICLEID+'><table><tr><th>'+data.searchResult.objects[i].TITLE+'</th></tr><tr><td><ul><li></li><li>'+data.searchResult.objects[i].TIME+'</li><li></li><li style="border: none"></li></ul></td></tr><tr><td  class="overflowhidden">'+data.searchResult.objects[i].CONTENT+'</td></tr></table></div>';
					$("#show").append(tr);
					var str = $('.overflowhidden').eq(i).html(); 
					overflowhidden("overflowhidden",2,str,i,data.searchResult.objects[i].ARTICLEID);
					//$('.recommend a').eq(i).html(data.searchResult.objects[i].TITLE);
				}
				n++;
				$("#page").html('');
				/*初始化分页按钮*/
				if(count!==1){

					//pageN[]分别赋值
					for(var j=1;j<=count;j++){	
						pageN[j-1]='<a href="#top"><span class="page" selectPage="'+j+'">'+j+'</span></a>';
					 }


					if(count<5){//少于3页
						$('#page').append(pageIcon[0]);
						$('#page').append(pageIcon[1]);
						for(var z=1;z<=count;z++){
							$('#page').append(pageN[z-1]);
						}
						$('#page').append(pageIcon[2]);
						$('#page').append(pageIcon[3]);
						$('.page').eq(current-1).addClass('pageselect');//给选中按钮添加样式
					}
					else{//大于等于3页
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
	
	$(document).on('click','.check1',function(){
		code = $(this).attr("code");
		window.open('knowlage.html?id='+code+'');

	});


	
	var overflowhidden = function(id, rows, str,j,c){
		var text = document.getElementsByClassName(id)[j];
		var lineHeight = 35;   //获取到line-height样式设置的值 必须要有
		var at = rows*parseInt(lineHeight);      //计算包含文本的div应该有的高度
		var tempstr = str;                       //获取到所有文本
		text.innerHTML = tempstr;                //将所有文本写入html中
		var len = tempstr.length;
		var i = 0;
		if(text.offsetHeight <= at){             //如果所有文本在写入html后文本没有溢出，那不需要做溢出处理
			/*text.innerHTML = tempstr;*/
		}
		else {                                   //否则 一个一个字符添加写入 不断判断写入后是否溢出
			var temp = "";
			text.innerHTML = temp;
			while(text.offsetHeight <= at){
				temp = tempstr.substring(0, i+1);
				i++;
				text.innerHTML = temp;
			}
			var slen = temp.length;
			tempstr = temp.substring(0, slen-1);
			len = tempstr.length;
			text.innerHTML = tempstr.substring(0, len-7) +"... "+"<span style='color:#E97C91;cursor: pointer' class='check1' code="+c+">查看全文 >></span>";     //替换string后面三个字符 
			text.height = at +"px";                                  //修改文本高度 为了让CSS样式overflow：hidden生效
		}

	};
	
	$(document).on('click','.top',function(){
		p=$(this).index();
		current=1;
		success();
	});
	
	//搜索名字
	$(document).on('click','#search',function(){
		if($("#hot").val()==="")
			{ch=$('.item').eq(0).attr("c");}
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/knowledge/id",    //请求的url地址
			dataType:"json",   //返回格式为json
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			data:{id:ch},    //参数值
			type:"GET",   //请求方式
			beforeSend:function(){
				//请求前的处理
			},
			success:function(){
				//请求成功时处理
				window.open('knowlage.html?id='+ch+'');
				
			},
			complete:function(){
				//请求完成的处理
			},
			error:function(){
				//请求出错处理
			}
		});	
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
		if($("#hot").val()!==""){
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/knowledge/searchbytitle",
			data:{Title:$("#hot").val()},
			type:"get",
			dataType:"json",
			success: function(result){ 
				$('.hide ul').html("");//清空
				for(var i=0; i<result.resultCount;i++){					
					var d;
					d='<li>'+result.objects[i].TITLE+'</li>';
					$('.hide ul').append(d);
					ch=result.objects[i].ARTICLEID;
				}
					showhide();
				},
			error:function(){}
		});
		}
	});
	
	//点击搜索结果
	$(document).on('click','.hide ul>li',function(){
		$('#hot').val($(this).html());
	});
	

	

	
})();
