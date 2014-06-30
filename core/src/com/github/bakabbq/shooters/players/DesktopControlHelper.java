package com.github.bakabbq.shooters.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by LBQ on 6/30/14.
 */
public class DesktopControlHelper implements IControlHelper{
    @Override
    public void updatePlayerControl(DanmakuPlayer player) {
        Vector2 vel = player.playerBody.getLinearVelocity();
        Vector2 pos = player.playerBody.getPosition();


        //slow mode when pressing shift
        float generalV = 0.5f;

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            if (!player.slowMode)
                player.onSlowMode();
            generalV = 0.3f;

        } else {
            if(player.slowMode)
                player.onSlowModeCancel();
        }
        //player.playerBody.setLinearVelocity(0f,0f);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && vel.x > -player.MAX_VELOCITY) {
            player.setState(4);
            player.playerBody.applyLinearImpulse(-generalV, 0, pos.x, pos.y, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && vel.x < -player.MAX_VELOCITY) {
            player.setState(6);
            player.playerBody.applyLinearImpulse(generalV, 0, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && vel.x < -player.MAX_VELOCITY) {
            player.playerBody.applyLinearImpulse(0, generalV, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && vel.x < -player.MAX_VELOCITY) {
            player.playerBody.applyLinearImpulse(0, -generalV, pos.x, pos.y, true);
        }

        if (Math.abs(player.playerBody.getLinearVelocity().x) <= 1 && Math.abs(player.playerBody.getLinearVelocity().y) <= 1)
            player.setState(0);
    }
}
