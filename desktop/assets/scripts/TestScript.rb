require 'java'
java_import com.github.bakabbq.bullets.Bullet
java_import com.github.bakabbq.bullets.BulletAmulets
java_import com.github.bakabbq.bullets.BulletKnife
java_import com.github.bakabbq.bullets.BulletBigCircle
java_import com.github.bakabbq.bullets.BulletButterfly

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
		@test_bullet = BulletButterfly.new(0);
    @amulet = BulletAmulets.new(0);
	end

    def update
    	super
    	every 240.frames do
            direct_position_set(20,20)
          
            spawn_slave(TestSlave.new(owner))
            #schedule_movement(10, 10, 120)
    	end

        every 40.frames do
            nway_shoot(@test_bullet, 8, 0, 80)
        end
    end
end