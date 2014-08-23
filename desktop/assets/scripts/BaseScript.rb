require 'java'
class Object
	def this
		return self
	end
end
load 'ShotDef.rb'
java_import com.github.bakabbq.bullets.BulletDef


class BulletDef
	def recolor(color_id)
		self.colorId = color_id
		setTextureIndex
		updateTexture
		initCreationTexture
	end
end
class Fixnum
	def frames()
		return self
	end
	
	alias frame frames
	
	def minutes
		return self * 60
	end
	
	alias minute minutes
end

class BaseScript
	include_package com.github.bakabbq.bullets
	attr_accessor	:owner
	attr_accessor	:timer

	attr_accessor	:schedule_movement_timer, :schedule_x, :schedule_y
	def initialize
		@timer = 0
		@schedule_movement_timer = 0
	end
	
	def every(cycle, interval = 0)
		if(@timer % cycle == interval)
			yield if block_given?
		end
	end

	def update
		@timer += 1
		update_schedule
	end

	def velocity_movement(x, y, damping)
		owner.enemyBody.setLinearDamping(damping)
		owner.enemyBody.set_linear_velocity(x,y)
	end

	def direct_position_set(x,y)
		owner.enemyBody.set_transform(x,y,0)
	end

	def schedule_movement(vX, vY, time)
		@schedule_x = vX
		@schedule_y = vY
		@schedule_movement_timer = time
	end

	def update_schedule
		@schedule_movement_timer -= 1
		if(@schedule_movement_timer > 20)
			owner.enemyBody.set_linear_velocity(@schedule_x, @schedule_y)
		elsif @schedule_movement_timer > 0
			owner.enemyBody.setLinearDamping(3)
			owner.enemyBody.set_linear_velocity(@schedule_x, @schedule_y) # yeah being lazy
		end
	end

	
	def method_missing(name, *params, &block)
		if self.owner.respond_to? name
			self.owner.send(name, *params, &block)
		else
			super(name, *params, &block)
		end
	end
end

module BulletDB
	
end