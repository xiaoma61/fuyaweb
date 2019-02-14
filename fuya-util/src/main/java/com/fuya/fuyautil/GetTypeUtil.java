package com.fuya.fuyautil;

public class GetTypeUtil {
    public static int GetType(String articletype){
        int type=0;
        if (articletype.equals("备孕知识")){
            type=1;
        }else if(articletype.equals("孕期知识")){
            type=2;
        }else if(articletype.equals("月子知识")){
            type=3;
        }else if(articletype.equals("新生儿知识")){
            type=4;
        }
        return type;
    }
}
