package com.github.bakabbq;

import com.badlogic.gdx.physics.box2d.World;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletDef;
import com.github.bakabbq.bullets.Laser;
import com.github.bakabbq.bullets.PlayerBullet;
import com.github.bakabbq.effects.ExplosionEffect;
import com.github.bakabbq.effects.ThEffect;
import com.github.bakabbq.items.ThItem;
import com.github.bakabbq.shooters.BulletShooter;
import com.github.bakabbq.shooters.EnemyShooter;
import com.github.bakabbq.shooters.bosses.ThBoss;
import com.github.bakabbq.shooters.players.DanmakuPlayer;

/**
 * Created by LBQ on 7/14/14.
 */
public interface IDanmakuWorld {
    public BulletShooter addShooter(BulletShooter bs, float x, float y);

    public EnemyShooter addEnemy(EnemyShooter es);

    public EnemyShooter addEnemy(EnemyShooter es, float x, float y);

    public ThBoss spawnBoss(ThBoss boss, float x, float y);

    public void destroyBullet(Bullet b);

    public void addItem(float x, float y);

    public void addEffect(ThEffect effect, float x, float y);

    public void clearEffect(ThEffect effect);

    public void addLaser(Laser laser);

    public Bullet addBullet(BulletDef bd, float x, float y, float angle);

    public PlayerBullet addPlayerBullet(BulletDef bd, float x, float y, float angle);

    public BulletShooter addShooter(BulletShooter bs);

    public World getWorld();

    public DanmakuPlayer getPlayer();
}
