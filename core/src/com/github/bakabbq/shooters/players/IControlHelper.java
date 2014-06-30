package com.github.bakabbq.shooters.players;

/**
 * Created by LBQ on 6/30/14.
 */


/*
    Different Platforms have different controls, which totally makes sense. So this interface is for platform specific controls
 */
public interface IControlHelper {
    public void updatePlayerControl(DanmakuPlayer player);
}
