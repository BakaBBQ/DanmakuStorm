#Library : BossMovement
require 'java'
#library 'Aliases'
java_import com.github.bakabbq.shooters.bosses.ThBoss
java_import com.github.bakabbq.shooters.EnemyShooter
module BossMovement
  def move_to_pos target_x, target_y, time
    @move_to_pos_timer = 1000000
    @target_x = target_x
    @target_y = target_y
    dif_x = target_x - cur_pos.x
    dif_y = target_y - cur_pos.y
    #puts "Frome: #{cur_pos.x}, #{cur_pos.y} Final Difference#{dif_x}, #{dif_y}"
    #puts "#{dif_x / time.to_f}, #{dif_y / time.to_f}"
    set_linear_speed dif_x / time.to_f, dif_y / time.to_f
  end
  
  def update_move_to_pos
    #puts "b"
    return unless @move_to_pos_timer
    #puts "c"
    @move_to_pos_timer -= 1
    #puts @move_to_pos_timer
    return unless @target_x && @target_y
    dif_x = @target_x - cur_pos.x
    dif_y = @target_y - cur_pos.y
    #puts "#{dif_x}, #{dif_y}"
    if (dif_x - 0).abs <= 0.05 && (dif_y - 0).abs <= 0.05#@move_to_pos_timer <= 0
      @move_to_pos_timer = 0
      
      set_linear_speed 0, 0
    end
  end
  
  # hook method
  def onActive
    
  end
  
  
  def onLeave
    @leaving = true
    move_to_pos 20, 40, 1.5
  end
  
  def move_to_uppercenter
    move_to_pos 23, 62, 2
  end
  
  
  def finishedLeaving
    return @leaving && (@move_to_pos_timer == 0)
  end
  
  def moving?
    return true unless @move_to_pos_timer
    return @move_to_pos_timer > 0
  end
  
end

class EnemyShooter
	def direct_position_set(x,y)
		enemyBody.set_transform(x,y,0)
	end
end


class ThBoss
  include BossMovement
  alias boss_movement_updateShoot updateShoot
  def updateShoot
    boss_movement_updateShoot
    update_move_to_pos
  end
end

class BaseScript
  alias boss_movement_script_update update
  def update
    boss_movement_script_update
    owner.update_move_to_pos
  end
end

