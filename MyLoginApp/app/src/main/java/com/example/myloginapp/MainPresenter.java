package com.example.myloginapp;

public class MainPresenter implements TaskContract.Presenter{
    TaskContract.View mainView;
    MainModel mainModel;



    public MainPresenter(TaskContract.View view) {
        mainView = view;
        mainModel = new MainModel(this);
    }

    @Override
    public void confirm(String a,String b) {
        int result = 0;
        if(a.equals(mainModel.Id)&&(b.equals(mainModel.passWord))){
            result = 1;
        }
        mainView.showResult(result);

    }
}
