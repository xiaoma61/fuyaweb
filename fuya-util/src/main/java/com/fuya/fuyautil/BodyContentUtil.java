package com.fuya.fuyautil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BodyContentUtil {
    public static String GetContent(String body){
        //System.out.println(body);
        if (body!=null){
            Document doc= Jsoup.parse(body);
            String text=doc.body().text();
            System.out.println(text);
            Document doc1= Jsoup.parse(text);
            Elements elements=doc1.select("p");
            StringBuffer stringBuffer=new StringBuffer();
            for (Element element:elements){
                //System.out.println(element);
                stringBuffer.append(element.text().trim());
            }
            if(text.length()<200){
                return text;
            }
            return text.substring(0,200);
        }
        else {
            return "暂无数据";
        }


    }

}
