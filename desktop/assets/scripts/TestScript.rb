require 'java'
java_import com.github.bakabbq.bullets.Bullet
class TestScript < BaseScript
	def initialize
		super
	end

    def update
    	super
    	every 30.frames do
    		nway_shoot(Bullet.amuletBullet.debugBullet, 8, 0, 80)
    	end
    end
end