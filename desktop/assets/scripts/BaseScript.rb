require 'java'
java_import com.github.bakabbq.bullets.BulletDef
class Object
	def this
		return self
	end
end

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
	def initialize
		@timer = 0
	end
	
	def every(cycle, interval = 0)
		if(@timer % cycle == interval)
			yield if block_given?
		end
	end

	def update
		@timer += 1
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