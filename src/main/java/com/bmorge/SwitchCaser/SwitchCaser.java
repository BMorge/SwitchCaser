package com.bmorge.switchcaser;

public class SwitchCaser {

    private SwitchCaser(){
    }

    public static Case switchIt(Object sample){
        return new Case(sample);
    }
}
