package com.example.omalaakekalenteri;

public class Counter {
    private int startNum;
    private int step;

    public Counter(int startNum, int step){
        this.startNum = startNum;
        this.step = step;
    }

    public void laskeUusi(){
        this.startNum -= this.step;
    }

    public String getUusi(){
        return Integer.toString(this.startNum);
    }

}
