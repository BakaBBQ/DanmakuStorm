package com.github.bakabbq.shooters.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.bakabbq.datas.Pixel;
import com.github.bakabbq.bullets.BulletDef;

/**
 * Created by LBQ on 5/29/14.
 */
public class DanmakuOption {

    public DanmakuPlayer player;
    public int id;

    public float x;
    public float y;
    public int angle;
    public TextureRegion texture;
    float ori_x;
    float ori_y;
    float target_x;
    float target_y;
    float rel_x;
    float rel_y;
    int total;

    public DanmakuOption(DanmakuPlayer player, int id, int total) {
        this.total = total;
        this.player = player;
        angle = 0;
        this.id = id;
        texture = new TextureRegion(player.textureSheet, 4 * 16, 9 * 16, 16, 16);
        //stopMoving();
    }

    public void stopMoving() {
        rel_x = Pixel.m2p(DanmakuPlayer.option_positions[player.getOptionsCnt() - 1][id].x);
        rel_y = Pixel.m2p(DanmakuPlayer.option_positions[player.getOptionsCnt() - 1][id].y);
        target_x = ori_x = idealPosition().x;
        target_y = ori_y = idealPosition().y;
    }

    public void update() {
        this.x = player.getX() + rel_x;
        this.y = player.getY() + rel_y;
        angle += 2;

        //updateMoveTowards();
    }

    public void refresh(int total) {
        this.total = total;
        startMoving();
    }

    public void startMoving() {
        stopMoving();
        this.ori_x = this.rel_x;
        this.ori_y = this.rel_y;

        this.target_x = idealPosition().x;
        this.target_y = idealPosition().y;
        Gdx.app.log("Options", " Ori_X " + ori_x + " Ori_Y " + ori_y + " Target_X " + target_x + " Target_Y " + target_y);
    }

    public Vector2 idealPosition() {
        if (player.slowMode)
            return DanmakuPlayer.optionSlow_positions[player.getOptionsCnt() - 1][id];
        else
            return DanmakuPlayer.option_positions[player.getOptionsCnt() - 1][id];
    }

    void updateMoveTowards() {
        this.rel_x += (target_x - ori_x) / 10;
        this.rel_y += (target_y - ori_y) / 10;
        //Gdx.app.log("Abs", String.valueOf(Math.abs(this.x - target_x)));
        if (Math.abs(this.x - target_x) <= 60.0f && Math.abs(this.y - target_y) <= 60.0f) {
            stopMoving();
        }

    }


    public void moveTowards(float a, float b) {
        this.x += 0.01f;
        this.y += 0.01f;
        ori_x = this.x;
        ori_y = this.y;

        target_x = a;
        target_y = b;
    }

    public void shoot(BulletDef bd) {
        player.ground.addPlayerBullet(bd, this.x, this.y, 180).setSpeed(800);
    }
    public void shoot(BulletDef bd, float angle, int speed) {
        player.ground.addPlayerBullet(bd, this.x, this.y, angle).setSpeed(speed);
    }

    public void nwayShoot(BulletDef bd, int ways, int angleFix, int speed) {
        if (ways <= 0)
            ways = 1;
        for (int i = 0; i < ways; i++) {
            shoot(bd, i * 360 / ways + angleFix, speed);
        }
    }

    public void angledSpreadShot(BulletDef bd, int ways, int mainAngle, float totalAngleDiff, int speed){
        if (ways <= 0)
            ways = 1;

        float singleAngleDiff = totalAngleDiff / (float) ways;
        float startAngle = mainAngle - totalAngleDiff / 2.0f ;
        for (int i  = 0; i < ways; i ++){
            shoot(bd, singleAngleDiff * i + startAngle,speed);
        }
    }

    public void nWayAngeledSpreadShot(BulletDef bd, int nways, int ways, int angleFix, float totalAngleDiff, int speed){
        for (int i = 0; i < nways; i++){
            float angle = 360f/nways;
            angledSpreadShot(bd, ways, (int)angle * i+angleFix, totalAngleDiff, speed);
        }

    }


}
