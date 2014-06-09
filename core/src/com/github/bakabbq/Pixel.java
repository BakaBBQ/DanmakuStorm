package com.github.bakabbq;

/**
 * Created by LBQ on 6/9/14.
 */
public class Pixel {
    public final static int CONVERSION_FACTOR = 5;
    public static float p2m(float pixels){
        return pixels * CONVERSION_FACTOR;
    }

    public static float m2p(float meters){
        return meters / CONVERSION_FACTOR;
    }
}
