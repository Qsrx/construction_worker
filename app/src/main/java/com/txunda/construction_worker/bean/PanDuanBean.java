package com.txunda.construction_worker.bean;

import android.util.Log;

/**
 * Created by dell-pc on 2019/2/14.
 */

public class PanDuanBean {
    public String id;
    public String user;
    public String type;
    public String collection;

    public PanDuanBean(String id, String user, String type ,String collection) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.collection = collection;
    }

    @Override
    public String toString() {
        String str = "";
        Log.d("panduandata", "toString: "+this.user);
//        if (this.user.equals("0")){
//            str = "A";
//        }else if (this.user.equals("1")){
//            str = "B";
//        }else if (this.user.equals("2")){
//            str = "C";
//        }else if (this.user.equals("3")){
//            str = "D";
//        }else if (this.user.equals("4")){
//            str = "E";
//        }else if (this.user.equals("5")){
//            str = "F";
//        }else if (this.user.equals("6")){
//            str = "G";
//        }else if (this.user.equals("7")){
//            str = "H";
//        }else if (this.user.equals("8")){
//            str = "I";
//        }else if (this.user.equals("9")){
//            str = "J";
//        }else if (this.user.equals("10")){
//            str = "K";
//        }else if (this.user.equals("11")){
//            str = "L";
//        }else if (this.user.equals("12")){
//            str = "M";
//        }else if (this.user.equals("13")){
//            str = "O";
//        }else {
//            str = "";
//        }
            return "{\""+"questions_id"+"\""+":" + "\""+id+"\""+","+
                "\""+"choose_answer"+"\""+":" + "\""+this.user+"\""+","+
                "\""+"exercise_type"+"\""+":" + "\""+type+"\"}";
    }
}