package com.tiagogameover.speedtraffic.banco;

public class ListDataModel {
    private String name;
    private String phone;
    private String upload;
    private String data;
    private int icon;
    private int user_id;

    public ListDataModel(){}

    public ListDataModel(String name, String phone, int user_id, int icon) {
        this.name = name;
        this.phone = phone;
        this.user_id = user_id;
        this.icon=icon;
    }

    public ListDataModel(String name, String phone, String upload,String data) {
        this.name = name;
        this.phone = phone;
        this.upload= upload;
        this.data=data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUpload() {return upload;}

    public void setUpload(String upload) {this.upload = upload;}

    public String getData() {return data;}

    public void setData(String data) {this.data = data;}


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
