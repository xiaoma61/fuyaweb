package com.fuya.fuyautil;

public class TotalPages {
    public static  int  GetIMage(int counts,int rows){

        int totalpages=0;
        if (counts%rows!=0){
            totalpages=(counts/rows)+1;
        }else {
            totalpages=(counts/rows);
        }
        return rows;
    }
}
