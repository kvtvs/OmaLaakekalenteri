package com.example.omalaakekalenteri;

/**
 * @author Kata Sara-aho
 *
 */
public class Counter {
    private int startNum;
    private int step;
    int minNum;

    public Counter(int startNum, int step, int minNum){
        this.startNum = startNum;
        this.step = step;
        this.minNum = minNum;
    }

    /** Method will happen when button is pressed and it will decrease the amount of medicine eaten in single dose from the total amount **/

    public void laskeUusi(){
        if(this.startNum >= this.minNum + this.step){
            this.startNum -= this.step;
        }
    }

    /** Returns the new total amount **/

    public int getUusi(){
        return this.startNum;
    }

}
