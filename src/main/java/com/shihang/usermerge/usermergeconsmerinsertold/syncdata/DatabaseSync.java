package com.shihang.usermerge.usermergeconsmerinsertold.syncdata;

/**
 * @author jiangzhuangnai
 * @date 2021/3/15
 * @since 1.0.0
 **/
public class DatabaseSync<T> {

    private T data;

    private String city;

    private String event;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
