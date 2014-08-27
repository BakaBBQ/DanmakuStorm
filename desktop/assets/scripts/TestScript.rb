require 'java'
java_import com.github.bakabbq.bullets.Bullet
java_import com.github.bakabbq.bullets.BulletAmulets
java_import com.github.bakabbq.bullets.BulletKnife
java_import com.github.bakabbq.bullets.BulletBigCircle
java_import com.github.bakabbq.bullets.BulletButterfly
java_import com.github.bakabbq.bullets.BulletGunshot
java_import com.github.bakabbq.bullets.BulletTriangle
java_import com.github.bakabbq.bullets.BulletKunai

class TestSlave < BossSlave
  def initialize(owner)
    super(owner)
    
    @big_circle = com.github.bakabbq.bullets.BulletBigCircle.new(0)
  end
  
  def updateShoot
    if(timer % 30 ==0 )
      
      nway_shoot(@big_circle,8,0,80)
    end
    self.enemyBody.applyLinearImpulse(0,10,16,16,true);
    
  end
end

class TestScript < BaseScript
	def initialize
		super
		@test_bullet = BulletTriangle.new(0);
		@amulet = BulletAmulets.new(0);
		@bigCircle = BulletBigCircle.new(0)
		@kunai = BulletKunai.new(3);
	end

    def update
    	super
        every 20.frames do
            nway_shoot(@kunai, 10, timer % 360, 14)
        end
    end
end