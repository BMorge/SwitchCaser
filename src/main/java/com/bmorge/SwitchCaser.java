package com.bmorge;

public class SwitchCaser {

    private SwitchCaser(){
    }

    public static Case switchIt(Object sample){
        return new Case(sample);
    }

    public static <T extends Cloneable> Case switchItClone(T sample){
        //todo Clone it
        return new Case(sample);
    }

}
