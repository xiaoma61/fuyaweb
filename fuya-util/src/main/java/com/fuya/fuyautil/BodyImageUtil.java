package com.fuya.fuyautil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BodyImageUtil {
    public static  String GetIMage(String  Body){

        Document doc= Jsoup.parse(Body);
        String text=doc.body().text();
        System.out.println(text);
        Document doc1= Jsoup.parse(text);
       // Elements elements=doc1.select("p");
        //分解body

        Elements pngs= doc1.select("img[src]");
        for (Element element : pngs){
            String imgUrl=element.attr("src");
            return imgUrl;
        }
        return null;

    }

}
