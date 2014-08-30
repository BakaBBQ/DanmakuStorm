require 'java'
java_import com.github.bakabbq.bullets.Bullet
java_import com.github.bakabbq.bullets.BulletAmulets
java_import com.github.bakabbq.bullets.BulletKnife
java_import com.github.bakabbq.bullets.BulletBigCircle
java_import com.github.bakabbq.bullets.BulletButterfly
java_import com.github.bakabbq.bullets.BulletGunshot
java_import com.github.bakabbq.bullets.BulletTriangle
java_import com.github.bakabbq.bullets.BulletKunai



=begin
BOSS_NAME = "Kanako"
BACKGROUND = ""
NAME = "Complete Test"

=end
class TestSlave < BossSlave
  def initialize(owner)
    super(owner)
    @big_circle = com.github.bakabbq.bullets.BulletTriangle.new(0)
  end
  
  def isSlave
    return true
  end
  
  def updateShoot
    if(timer % 60 ==0 )
      
      nway_shoot(@big_circle,8,timer % 360,8)
    end
    
    if timer >= 800
      onDeath
    end
    
    self.enemyBody.applyLinearImpulse(0,-20,16,16,true);
    
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
        end
        
        every 140.frames do
          
          slave = TestSlave.new(self)
          slave.setX(self.getX)
          slave.setY(self.getY)
          spawnSlave(slave)
        end
        
    end
end