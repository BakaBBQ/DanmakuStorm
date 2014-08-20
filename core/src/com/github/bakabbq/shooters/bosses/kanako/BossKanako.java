package com.github.bakabbq.shooters.bosses.kanako;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.github.bakabbq.BulletScript;
import com.github.bakabbq.IDanmakuWorld;
import com.github.bakabbq.datas.StateHelper;
import com.github.bakabbq.shooters.bosses.ThBoss;
import com.github.bakabbq.spellcards.JRubySpellCard;
import com.github.bakabbq.spellcards.ScWindGodRad;

/**
 * Created by LBQ on 8/2/14.
 */
public class BossKanako extends ThBoss{
    public BossKanako(IDanmakuWorld ground) {
        super(ground);
        stateId = StateHelper.IDLE;
    }


    @Override
    public int updateFrame(){
        return 4;
    }

    @Override
    public void initSpellCards() {
        //
        //spellCards.add(new spellCard1(this));
        spellCards.add(new JRubySpellCard(this, BulletScript.load("TestScript")));
    }

    @Override
    public void initMainTexture(){
        mainTexture = new Texture(Gdx.files.internal("bosses/stg6enm.png"));
    }
}
