package com.example.mvc;

public class RecycleRecord {
    private String boardText;

    public RecycleRecord(String name){
        this.boardText = name;
    }
    public String getTimeRecord(){
        return boardText;
    }
}
