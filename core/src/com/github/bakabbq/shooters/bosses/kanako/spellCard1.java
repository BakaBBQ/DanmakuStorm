package com.github.bakabbq.shooters.bosses.kanako;
import com.github.bakabbq.bullets.*;
import com.github.bakabbq.shooters.bosses.*;
import com.github.bakabbq.spellcards.*;
import org.jruby.embed.ScriptingContainer;

public class spellCard1 extends SpellCard
{

    ScriptingContainer container;
	public spellCard1(ThBoss owner) {
        super(owner);
    }

    @Override
    public void updateAttributes(){
        this.name = "non spell";
        container = new ScriptingContainer();
    }
	@Override
    public void mainLoop(){
		if(timer % 30 == 0){
            container.setAttribute("owner", this.owner);
            container.put("owner",this.owner);
            container.put("bullet", Bullet.amuletBullet);
            container.runScriptlet("owner.nwayShoot(bullet,8,0,80)");
		}
    } // void mainLoop

	
	
}
