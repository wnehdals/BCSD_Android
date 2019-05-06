package com.example.myloginapp;

public class MainModel {
    TaskContract.Presenter presenter;
    public String Id;
    public String passWord;

    public MainModel(TaskContract.Presenter presenter) {
        this.presenter = presenter;
        Id = "jdm";
        passWord = "123";
    }
}
