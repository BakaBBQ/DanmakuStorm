require 'java'
java_import com.github.bakabbq.bullets.Bullet
java_import com.github.bakabbq.bullets.BulletAmulets

class TestScript < BaseScript
	def initialize
		super
		@test_bullet = Bullet.amuletBullet;
        @test_bullet.recolor(3)
	end

    def update
    	super
    	every 240.frames do
            direct_position_set(20,20)
            schedule_movement(10, 10, 120)
    	end

        every 40.frames do
            nway_shoot(@test_bullet, 8, 0, 80)
        end
    end
end