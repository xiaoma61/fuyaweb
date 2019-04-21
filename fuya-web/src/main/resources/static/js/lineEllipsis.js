  var overflowhidden = function(id, rows, str){
      var text = document.getElementById(id);
      var style = getCSS(text);
      var lineHeight = style["line-height"];   //获取到line-height样式设置的值 必须要有
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
         len = tempstr.length
         text.innerHTML = tempstr.substring(0, len-3) +"...";     //替换string后面三个字符 
         text.height = at +"px";                                  //修改文本高度 为了让CSS样式overflow：hidden生效
     }
     
 }