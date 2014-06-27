package com.github.bakabbq.shooters.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.GdxGround;
import com.github.bakabbq.shooters.EnemyShooter;
import com.github.bakabbq.spellcards.SpellCard;

/**
 * Created by LBQ on 6/9/14.
 */
public class ThBoss extends EnemyShooter {
    public String name = "";
    public Array<SpellCard> spellCards = new Array();
    Texture mainTexture = new Texture(Gdx.files.internal("bosses/stg5enm.png"));
    public ThBoss(GdxGround ground) {
        super(ground);
        initSpellCards();
        initMainTexture();
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
        switch(stateId) {
            case 0:
                rId = 0;
                cId = stateTimer % 4;
                break;
            case 1:
                flip = true;
            case 2:
                rId = 1;
                cId = (stateTimer > 3 ? (3) : (stateTimer % 4));
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

    @Override
    public void updateShoot(){
        spellCards.get(0).update();
    }
}
