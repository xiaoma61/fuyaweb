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
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/problemlist",    //请求的url地址
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
				count=data.msg.totalPages;//页数	
				num=data.msg.size;
				/**/
				$("#num_show").append("共"+data.msg.totalElements+"条"+"共"+count+"页");//显示数据数页数

				for(var i=0;i<num;i++)
				{
					var tr; tr='<td>'+data.msg.content[i].PROBLEMID+'</td>'+'<td>'+data.msg.content[i].TITLE+'</td>'+'<td>'+data.msg.content[i].TIME+'</td>'+'<td>'+data.msg.content[i].CHOOSETYPE+'</td>'+'<td>'+data.msg.content[i].SUBJECTMATTER+'</td>'+'<td><a href="#" class="che" code="'+data.msg.content[i].PROBLEMID+'">'+"查看"+'</a></td>'+'<td><a href="#" class="rev" code1="'+data.msg.content[i].PROBLEMID+'" code2="'+data.msg.content[i].CHOOSEID+'">'+"修改"+'</a></td>'+'<td><a href="#" class="del"  code1="'+data.msg.content[i].PROBLEMID+'" code2="'+data.msg.content[i].CHOOSEID+'">'+"删除"+'</a></td>';
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
	
	
	//查看
	$(document).on('click','.che',function(){
		code=$(this).attr("code");
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/problem/id",
			data:{id:code},
			type:"POST",
			dataType:"json",
			success: function(result){ 
				$("#see").css("display","block");//显示查看div
				$(".check_bottom").css("display","none");//隐藏保存取消按钮
				$(".check_top").css("display","block");//显示关闭按钮
				
				$('.check_middle').html('<table id="qe" cellspacing="15"><tr><td>题目：</td><td class="i">'+result.msg.problem.TITLE+'</td></tr><tr><td>答案A：</td><td     class="i">'+result.msg.choose.ACHOOSE+'</td></tr><tr><td>答案B：</td><td  class="i">'+result.msg.choose.BCHOOSE+'</td></tr><tr><td>答案C：</td><td  class="i">'+result.msg.choose.CCHOOSE+'</td></tr><tr><td>答案D：</td><td  class="i">'+result.msg.choose.DCHOOSE+'</td></tr><tr><td>正确答案：</td><td  class="i">'+result.msg.choose.ANSWER+'</td></tr><tr><td>题目类型：</td><td  class="i">'+result.msg.problem.CHOOSETYPE+'</td></tr><tr><td>题材：</td><td class="i">'+result.msg.problem.SUBJECTMATTER+'</td></tr><tr><td>加入时间：</td><td class="i">'+result.msg.problem.TIME+'</td></tr></table>');

			},
			error:function(){}
		});
	});
		
	
	
	//取消
	$(document).on('click','#cancel',function(){
		$("#see").css("display","none");
	});
	
	//保存
	$(document).on('click','#save',function(){
		var flag=0;
		for(var i=0;i<=$('.check_middle table td input').length;i++){ 	
			  if($('.check_middle table td input').eq(i).val()===""){
					$('.check_middle table td input').eq(i).focus(); 
					flag++;
			  }
		}
		if(flag===0){
		var arr,choose,question;
		choose={"ACHOOSE":$('.check_middle input[type="text"]').eq(1).val(),"BCHOOSE":$('.check_middle input[type="text"]').eq(2).val(),"CCHOOSE":$('.check_middle input[type="text"]').eq(3).val(),"DCHOOSE":$('.check_middle input[type="text"]').eq(4).val(),"ANSWER":$('.check_middle input[type="text"]').eq(5).val()};
		question={"TITLE":$('.check_middle input[type="text"]').eq(0).val()};
		arr= { "CHOOSE":choose,"PROBLEM":question};
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/problem/add",
			data:{params:arr},
			type:"POST",
			dataType:"json",
			success: function(result){
				if(result.msg==="success")
				  {
					success();
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
	
	//搜索名字
	$(document).on('click','#search_name',function(){
		var na=$("#name").val();
		if(na!=="")
		{			
			$.ajax({
						url:"https://campus.gbdev.cn:8060/fuyaweb/admin/problem/title",
						data:{title:na},
						type:"POST",
						dataType:"json",
						success: function(result){ 
						$("#see").css("display","block");//显示查看div
						$(".check_bottom").css("display","none");//隐藏保存取消按钮
						$(".check_top").css("display","block");//显示关闭按钮

						$('.check_middle').html('<table id="qe" cellspacing="15"><tr><td>题目：</td><td class="i">'+result.msg.list[0].TITLE+'</td></tr><tr><td>题目类型：</td><td  class="i">'+result.msg.list[0].CHOOSETYPE+'</td></tr><tr><td>题材：</td><td class="i">'+result.msg.list[0].SUBJECTMATTER+'</td></tr><tr><td>加入时间：</td><td class="i">'+result.msg.list[0].TIME+'</td></tr></table>');

						},
						error:function(){}
					});
		}	
	});
	
	//添加
	$(document).on('click','#add_question',function(){
		code=$(this).attr("code");
		$("#see").css("display","block");//显示修改div
		$(".check_bottom").css("display","block");//显示保存取消按钮
		$(".check_top").css("display","none");//隐藏关闭按钮
		
		$('.check_middle').html('<table id="qe" cellspacing="15"><tr><td>题目：</td><td><input type="text"/></td></tr><tr><td>答案A：</td><td><input type="text"/></td></tr><tr><td>答案B：</td><td><input type="text"/></td></tr><tr><td>答案C：</td><td><input type="text"/></td></tr><tr><td>答案D：</td><td><input type="text"/></td></tr><tr><td>正确答案：</td><td><input type="text"/></td></tr></table>');
		$('.check_middle table td input').eq(0).focus(); 
		
	});	
	
	//删除
	$(document).on('click', '.del',function() {	
		var code1=$(this).attr("code1");
		var code2=$(this).attr("code2");
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/problem/delete",
			data:{problemid:code1,
				 chooseid:code2},
			type:"POST",
			dataType:"json",
			success: function(result){ 
				if(result.msg==="success")
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
	
	//修改
	$(document).on('click','.rev',function(){
		var arr=[];
		var code1=$(this).attr("code1");
		var code2=$(this).attr("code2");
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/problem/id",
			data:{id:code1},
			type:"POST",
			dataType:"json",
			success: function(result){
				
				$("#see").css("display","block");//显示修改div
				$(".check_bottom").css("display","block");//显示保存取消按钮
				$(".check_top").css("display","none");//隐藏关闭按钮
				$('.check_middle').html('<table id="qe" cellspacing="15"><tr><td>题目：</td><td><input type="text" value="'+result.msg.problem.TITLE+'"/></td></tr><tr><td>答案A：</td><td><input type="text"  value="'+result.msg.choose.ACHOOSE+'"/></td></tr><tr><td>答案B：</td><td><input type="text"  value="'+result.msg.choose.BCHOOSE+'"/></td></tr><tr><td>答案C：</td><td><input type="text"  value="'+result.msg.choose.CCHOOSE+'"/></td></tr><tr><td>答案D：</td><td><input type="text"  value="'+result.msg.choose.DCHOOSE+'"/></td></tr><tr><td>正确答案：</td><td><input type="text"  value="'+result.msg.choose.ANSWER+'"/></td></tr><tr><td>题目类型：</td><td><input type="text"  value="'+result.msg.problem.CHOOSETYPE+'"/></td></tr><tr><td>题材：</td><td><input type="text"  value="'+result.msg.problem.SUBJECTMATTER+'"/></td></tr></table>');
				
				$('.check_middle table td input').eq(0).focus(); 
				
				for(var i=0;i<8;i++){
					arr[i]=$('.check_middle input[type="text"]').eq(i).val();
				}

			},
			error:function(){}
		});
		
		
		
		$.ajax({
			url:"https://campus.gbdev.cn:8060/fuyaweb/admin/problem/update",
			data:{title:arr[0],
				  choosetype:arr[6],
				  subjectmater:arr[7],
				  choosea:arr[1],
				  chooseb:arr[2],
				  choosec:arr[3],
				  choosed:arr[4],
				  ansewer:arr[5],
				  problemid:code1,
				  chooseid:code2
			},
			type:"POST",
			dataType:"json",
			success: function(){
				current=1;
				success();

			},
			error:function(){}
		});
		

	});
	
})();
