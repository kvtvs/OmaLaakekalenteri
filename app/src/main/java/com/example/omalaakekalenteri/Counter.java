package com.example.omalaakekalenteri;

public class Counter {
    private int startNum;
    private int step;
    int minNum;

    public Counter(int startNum, int step, int minNum){
        this.startNum = startNum;
        this.step = step;
        this.minNum = minNum;
    }

    public void laskeUusi(){
        if(this.startNum >= this.minNum + this.step){
            this.startNum -= this.step;
        }
    }

    public int getUusi(){
        return this.startNum;
    }

}
