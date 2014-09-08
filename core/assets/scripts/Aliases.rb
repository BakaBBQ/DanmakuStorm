require 'java'
java_import com.github.bakabbq.shooters.bosses.ThBoss
java_import com.github.bakabbq.shooters.EnemyShooter
module Aliases
  module Script
    
  end
  
  module Enemy
    def body
      return enemyBody
    end
    
    def cur_pos
      return enemyBody.get_position
    end
    
    def set_linear_speed x, y
      body.setLinearVelocity(x,y)
    end
    alias set_linear_velocity set_linear_speed
  end
  
  module Boss
  end
  
  
end

class BaseScript
  include Aliases::Script
end

class ThBoss
  include Aliases::Boss
end

class EnemyShooter
  include Aliases::Enemy
end
