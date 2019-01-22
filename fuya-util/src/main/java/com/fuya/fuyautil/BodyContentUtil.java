package com.fuya.fuyautil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BodyContentUtil {
    public static String GetContent(String body){
        //System.out.println(body);
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
        return stringBuffer.toString().trim();

    }

}
