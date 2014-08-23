package com.github.bakabbq.shooters.bosses;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.*;
import com.github.bakabbq.*;
import com.github.bakabbq.shooters.*;
import com.github.bakabbq.spellcards.*;

/**
 * Created by LBQ on 6/9/14.
 *
 * Seems like there is something called Animation in gdx.... .___.
 */
public class ThBoss extends EnemyShooter {
    public String name = "";
    public Array<SpellCard> spellCards = new Array();
    public Texture mainTexture = new Texture(Gdx.files.internal("bosses/stg5enm.png"));
    public ThBoss(IDanmakuWorld ground) {
        super(ground);
        initSpellCards();
        initMainTexture();
    }

    public int updateFrame(){
        return 10;
    }



    public void initMainTexture(){
        mainTexture = new Texture(Gdx.files.internal("bosses/stg5enm.png"));
    }


    public int getTextureY(int rId){
        return rId * 64;
    }


    public int getTextureHeight(int rId){
        if(rId <= 1)
            return 64;
        else
            return 64 + 16;
    }
    //ThBoss has a modified state:  state id: 0 => staying at the same position,  1 => moving left, 2 => moving right, 3 => casting
    /*
    ThBoss States
        0 => idling
        1 => moving left
        2 => moving right
        3 => casting
        4 => modified casting
     */
    @Override
    public void updateTexture() {
        int rId, cId;
        rId = cId = 0;
        boolean flip = false;

        //Yes, Magic Indeed
        switch(stateId) {
            case 0:
                rId = 0;
                cId = (stateTimer > 3 ? (0 + stateTimer % 4) : (stateTimer % 4));
                break;
            case 1:
                flip = true;
            case 2:
                rId = 1;
                cId = (stateTimer > 3 ? (2 + stateTimer % 2) : (stateTimer % 4));
                break;
            case 3:
                rId = 2;
                cId = (stateTimer > 3 ? (3) : (stateTimer % 4));
                break;
            case 4:
                rId = 3;
                cId = (stateTimer > 3 ? (3) : (stateTimer % 4));
                break;
        }
        this.initMainTexture();
        int textureCellWidth = this.mainTexture.getWidth() / 4;
        TextureRegion resultTexture = new TextureRegion(mainTexture, cId * textureCellWidth, getTextureY(rId), textureCellWidth, getTextureHeight(rId));
        if(flip)
            resultTexture.flip(true, false); // flip x, not y

        this.texture = resultTexture;
    }


    public void initSpellCards() {
        //use spellCards.add
    }

    public void smoothMovement(int forceX, int forceY, int damping){
        this.enemyBody.setLinearDamping(damping);
        this.enemyBody.applyLinearImpulse(forceX, forceY, this.enemyBody.getLocalCenter().x,this.enemyBody.getLocalCenter().y, true);
    }



    @Override
    public void updateShoot(){
        spellCards.get(0).update();
    }
	
	@Override
	public Shape getBodyShape(){
		CircleShape circle = new CircleShape();
        circle.setRadius(4f);
		return circle;
	}


}
