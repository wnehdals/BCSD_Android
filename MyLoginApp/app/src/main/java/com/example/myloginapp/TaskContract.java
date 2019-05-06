package com.example.myloginapp;

public interface TaskContract {
    interface Presenter extends BasePresenter{
        void confirm(String a,String b);

    }
    interface View extends BaseView<Presenter>{
        void showResult(int a);
    }
}
