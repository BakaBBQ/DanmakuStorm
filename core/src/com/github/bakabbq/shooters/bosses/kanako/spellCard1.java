package com.github.bakabbq.shooters.bosses.kanako;
import com.github.bakabbq.bullets.*;
import com.github.bakabbq.shooters.bosses.*;
import com.github.bakabbq.spellcards.*;

public class spellCard1 extends SpellCard
{ 

	public spellCard1(ThBoss owner) {
        super(owner);
    }

    @Override
    public void updateAttributes(){
        this.name = "non spell";

    }
	@Override
    public void mainLoop(){
		if(timer % 30 == 0){
			//owner.nWayAngeledSpreadShot(new BulletKunai(0), 3, 5,timer % 360, 25, 20);
            //owner.shoot(new BulletKnife(0), 0, 1);
            owner.nwayShoot(Bullet.amuletBullet,8,0,80);
		}
    } // void mainLoop
	
	
}
