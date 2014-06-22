package com.github.bakabbq.bullets;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.github.bakabbq.BulletCollisionListener;

/**
 * Created by LBQ on 5/30/14.
 */
public class PlayerBullet extends Bullet {
    public static BulletReimuHoming reimuHoming = new BulletReimuHoming();
    public static ReimuAmulets reimuAmulet = new ReimuAmulets();


    public int damage;

    public PlayerBullet(BulletDef bd, World world, float x, float y, float angle) {
        super(bd, world, x, y, angle);
        world.destroyBody(body);

        this.bd = bd;
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodydef);
        FixtureDef fd = bd.fixtureD;
        fd.filter.categoryBits = BulletCollisionListener.ENEMY_BULLET;
        fd.filter.maskBits = (short)(BulletCollisionListener.ENEMY | 0x001);
        this.fixture = body.createFixture(fd);
        body.setTransform(x, y, angle);
        body.setUserData(this);
        setSprite();

        bd.fixtureD.density = 0.4f;
        bd.fixtureD.friction = 0.5f;
        bd.fixtureD.filter.categoryBits = BulletCollisionListener.PLAYER_BULLET;
        bd.fixtureD.filter.maskBits =(short) (BulletCollisionListener.ENEMY | 0x001);
        this.fixture = body.createFixture(bd.fixtureD);
        body.setLinearDamping(0f);
        body.setTransform(x, y, angle);

        initDamage();
    }

    public void initDamage(){
        this.damage  = 4;
    }
}
