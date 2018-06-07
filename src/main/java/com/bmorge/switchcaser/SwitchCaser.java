package com.bmorge.switchcaser;

import com.sun.istack.internal.Nullable;

public class SwitchCaser {

    private SwitchCaser(){
    }

    /**
     * Initial method for start switch-case process.
     * @param sample Sample object for comparing in Case. Can be null.
     * @return {@link com.bmorge.switchcaser.Case}
     */
    public static Case switchIt(@Nullable Object sample){
        return new Case(sample);
    }
}
