(function (){
　　"use strict";
	var index=6;
	var url= window.location.href;
	index = url.substring(url.lastIndexOf('=') + 1);
	$.ajax({
        url:'https://campus.gbdev.cn:8060/fuyaweb/fuyayuesaoindex/personalinfo',
		dataType:"json",   //返回格式为json
		async:true,//请求是否异步，默认为异步，这也是ajax重要特性
		data:{id:index},    //参数值
		type:"GET",   //请求方式
        success:function(data){

				$('.bar_td').eq(1).css('background-color','#f87c60');
			
			
				$('#t1').html('<tr><th colspan="5">月嫂选择</th></tr><tr><td rowspan="5" id="pic"></td><td class="t1">姓名</td><td width="25%">'+data.msg[0].yuesobasicinfo.NAME+'</td><td class="t1">年龄</td><td width="25%">'+data.msg[0].yuesobasicinfo.AGE+'</td></tr><tr><td class="t1">籍贯</td><td>'+data.msg[0].yuesobasicinfo.NATIVEPLACE+'</td><td class="t1">服务年限</td><td>'+data.msg[0].yuesobasicinfo.SENIORITY+'</td></tr><tr><td class="t1">学历</td><td>'+data.msg[0].yuesobasicinfo.EDUCATION+'</td><td class="t1">电话</td><td>'+data.msg[0].yuesobasicinfo.PHONE+'</td></tr><tr><td class="t1">身高体重</td><td>'+data.msg[0].yuesobasicinfo.HEIGHT+'/'+data.msg[0].yuesobasicinfo.WEIGHT+'</td><td class="t1">邮箱</td><td>'+data.msg[0].yuesobasicinfo.EMAIL+'</td></tr><tr><td class="t1">身份证</td><td>'+data.msg[0].yuesobasicinfo.IDCARD+'</td><td class="t1">工资</td><td>'+data.msg[0].yuesobasicinfo.WAGES+'</td></tr>');
			
			
			
			
				if(data.msg[0].yuesobasicinfo.PHOTO==="file:")
				{$("#pic").html('<img src="../img/name.png" width="200" alt=""/>'); }
				else
				{$("#pic").html('<img src="'+data.msg[0].yuesobasicinfo.PHOTO+'" width="200" alt=""/>'); }
				$('#money').html(data.msg[0].yuesobasicinfo.WAGES+'元');
				$('#order th').eq(4).html(data.msg[0].yuesobasicinfo.WAGES+'￥');

        },
		error:function(){}
    });
	
				$(document).on('click','#finish',function(){
					var flag=0;
					var arr=[];
					var uuid = "cms"+guid();
					for(var i=0;i<8;i++){
						arr[i]=$('input[type="text"]').eq(i).val();
					}
					arr[8]=$('input[type="radio"]:checked').val();
					arr[9]=$('textarea').val();
					
					for( var z=0;z<9;z++){
						if(arr[z]===""){flag++;}
					}
					if(flag!==0){alert("未完成");}
					if(flag===0)
					{
					$.ajax({
						url:'https://campus.gbdev.cn:8060/fuyaweb/fuyayusao/order/add',
						dataType:"json",   //返回格式为json
						async:true,//请求是否异步，默认为异步，这也是ajax重要特性
						data:{
							  toid:index,
							  childbirth:arr[0],
							  starttime:arr[1],
							  fate:$('#order th').eq(4).html(),
							  others:arr[9],
							  handsel:parseInt($('#order th').eq(6).html()),
							  sum:$('#order th').eq(4).html(),
							  name:arr[3],
							  area:arr[4],
							  address:arr[5],
							  phone:arr[6],
							  type:arr[8],
							  idcard:arr[7],
							  CONTRACTNUMBER:uuid
							 },    //参数值
						type:"GET",   //请求方式
						success:function(){

						},
						error:function(){}
					});
					$.ajax({
						url:'/fuyayusao/order/pay',
						dataType:"json",   //返回格式为json
						async:true,//请求是否异步，默认为异步，这也是ajax重要特性
						data:{
							  fate:$('#order th').eq(4).html(),
							  CONTRACTNUMBER:uuid
							 },    //参数值
						type:"GET",   //请求方式
						success:function(data){
							alert(data);
                            $("#t3").html(data);

						},
						error:function(){
                            alert("error");
						}
					});
					
					}
					
				});
	
	$(document).bind('input propertychange',function(){
		$('#order th').eq(2).html($('input[type="text"]').eq(2).val()+'天');
		
	});
	
    function S4() {
        return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    function guid() {
        return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
    }
	
	
	
})();
