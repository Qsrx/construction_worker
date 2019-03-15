package com.ants.theantsgo.base;

import java.io.Serializable;

/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/5 0005
 * 时间：09:12
 * 描述：所有Mode的基类
 * ===============Txunda===============
 */

public class BaseMode implements Serializable {
    public String flag;
    public String message;

    public BaseMode(String flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
