(function (){
　　"use strict";
	window.onload=function(){  
	$('.Header1').html('<div class="header"><div id="picture"><img src="../img/love.png" width="35%"  alt=""/></div><div id="SiteName"><table width="100%" ><tr><td align="center"><font size="6em" color="#E97C91">富雅</font></td><td align="center" rowspan="2"  style="border-left:#E97C91 solid 0.1em;"><font size="4em">全心服务，用心呵护。</font></td></tr><tr><td align="center"><font size="0.1em" color="#E97C91">家庭服务</font></td></tr></table></div><div id="number">全国统一服务热线：8888888888</div></div><div class="bar"><div class="bar_tr"><div class="bar_td">首页</div><div class="bar_td">富雅月嫂</div><div class="bar_td">月嫂机构</div><div class="bar_td">成为月嫂</div><div class="bar_td">人才招聘</div><div class="bar_td">母婴知识</div><div class="bar_td">退出</div></div></div>');
	};
	$(document).on('click','.bar_td',function(){

		$(".bar_td").eq($(this).index()).css('background-color','#f87c60');
		for(var i=0;i<$('.bar_td').length;i++){
			if(i!==$(this).index())
			{$(".bar_td").eq(i).css('background-color','#f07053');}
		}

                switch($(this).index()) {

                    case 0:

						window.location.href="index.html";
						
                        break;　　　　　　　

                    case 1:

                       window.location.href="fyys.html";

                        break;
	
                    case 2:

                        window.location.href="all_organization.html";

                        break;　　　　　
						
                    case 3:

                        window.location.href="join.html";

                        break;　
						
                    case 4:

                        window.location.href="all_recruit.html";

                        break;　
						
                    case 5:

                        window.location.href="all_knowlage.html";

                        break;　
						
                    case 6:

                        window.location.href="https://campus.gbdev.cn:8060/fuyaweb/loginout";

                        break;　

                }
	});
})();
