package com.gildedgames.aether.entity.projectile;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Lightning;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;
import net.modificationstation.stationapi.api.block.BlockState;

public class EntityLightningKnife extends EntityBase{
	
	private int xTileSnowball, yTileSnowball, zTileSnowball;
	private int inTileSnowball;
	private boolean inGroundSnowball;
	public int shakeSnowball;
	private Living thrower;
	private int ticksInGroundSnowball;
	private int ticksInAirSnowball;
	public EntityLightningKnife(Level level) {
		super(level);
		this.xTileSnowball = this.yTileSnowball = this.zTileSnowball = -1;
		this.inTileSnowball = 0;
		this.inGroundSnowball = false;
		this.shakeSnowball = 0;
		this.ticksInAirSnowball = 0;
		this.setSize(0.25f, 0.25f);
	}
	public EntityLightningKnife(Level level, Living thrower) {
		this(level);
		
		this.setPositionAndAngles(thrower.x, thrower.y + thrower.getEyeHeight(), thrower.z, thrower.yaw, thrower.pitch);
		this.x -= MathHelper.cos((this.yaw / 180f) * 3.141593f) * 0.16f;
		this.y -= 0.1;
		this.z -= MathHelper.sin((this.yaw / 180f) * 3.141593f) * 0.16f;
		this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
		this.standingEyeHeight = 0;
		float f = 0.4F;
		this.velocityX = -MathHelper.sin((this.yaw / 180F) * 3.141593F) * MathHelper.cos((this.pitch / 180F) * 3.141593F) * f;
		this.velocityZ = MathHelper.cos((this.yaw / 180F) * 3.141593F) * MathHelper.cos((this.pitch / 180F) * 3.141593F) * f;
		this.velocityY = -MathHelper.sin((this.pitch / 180F) * 3.141593F) * f;
		this.setSnowballHeading(this.velocityX, this.velocityY, this.velocityZ, 1.5F, 1.0F);
	}
	
	public EntityLightningKnife(Level level, double x, double y, double z) {
		this(level);
		ticksInGroundSnowball = 0;
		this.method_1338(x, y, z, this.yaw, this.pitch);
		this.standingEyeHeight = 0;
	}
	
	public void setSnowballHeading(double x, double y, double z, float f, float f1) {
		float f2 = MathHelper.sqrt(x * x + y * y + z * z);
		x /= f2;
		y /= f2;
		z /= f2;
		x += this.rand.nextGaussian() * 0.0075D * (double)f1;
		y += this.rand.nextGaussian() * 0.0075D * (double)f1;
		z += this.rand.nextGaussian() * 0.0075D * (double)f1;
		x *= f;
		y *= f;
		z *= f;
		this.velocityX = x;
		this.velocityY = y;
		this.velocityZ = z;
		float f3 = MathHelper.sqrt(x * x + z * z);
		this.prevYaw = this.yaw = (float)((Math.atan2(x, z) * 180D) / Math.PI);
		this.prevPitch = this.pitch = (float)((Math.atan2(x, f3) * 180D) / Math.PI);
		this.ticksInGroundSnowball = 0;
	}
	
	@Override
	public void setVelocity(double x, double y, double z)
	{
		this.velocityX = x;
		this.velocityY = y;
		this.velocityZ = z;
		if(this.prevPitch == 0.0F && this.prevYaw == 0.0F)
		{
			float f = MathHelper.sqrt(x * x + z * z);
			this.prevYaw = this.yaw = (float)((Math.atan2(x, z) * 180D) / Math.PI);
			this.prevPitch = this.pitch = (float)((Math.atan2(y, f) * 180D) / Math.PI);
		}
	}
	
	@Override
	public void tick()
	{
		this.prevX = this.x;
		this.prevY = this.y;
		this.prevZ = this.z;
		super.tick();
		if(this.shakeSnowball > 0)
		{
			this.shakeSnowball--;
		}
		if(this.inGroundSnowball)
		{
			int i = this.level.getBlockState(this.xTileSnowball, this.yTileSnowball, this.zTileSnowball).getBlock().id;
			if(i != this.inTileSnowball)
			{
				this.inGroundSnowball = false;
				this.velocityX *= this.rand.nextFloat() * 0.2F;
				this.velocityY *= this.rand.nextFloat() * 0.2F;
				this.velocityZ *= this.rand.nextFloat() * 0.2F;
				this.ticksInGroundSnowball = 0;
				this.ticksInAirSnowball = 0;
			} else
			{
				this.ticksInGroundSnowball++;
				if(ticksInGroundSnowball == 1200)
				{
					this.remove();
				}
				return;
			}
		} else
		{
			this.ticksInAirSnowball++;
		}
		
		Vec3f vec3d = Vec3f.from(this.x, this.y, this.z);
		Vec3f vec3d1 = Vec3f.from(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
		HitResult result = this.level.method_160(vec3d, vec3d1);
		vec3d = Vec3f.from(this.x, this.y, this.z);
		vec3d1 = Vec3f.from(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
		if(result != null)
		{
			vec3d1 = Vec3f.from(result.field_1988.x, result.field_1988.y, result.field_1988.z);
		}
		if(!this.level.isServerSide)
		{
			EntityBase entity = null;
			List<EntityBase> list = this.level.getEntities(this, boundingBox.method_86(this.velocityX, this.velocityY, this.velocityZ).expand(1.0D, 1.0D, 1.0D));
			double d = 0.0D;
			for(int j2 = 0; j2 < list.size(); j2++)
			{
				EntityBase entity1 = list.get(j2);
				if(!entity1.method_1356() || entity1 == thrower && ticksInAirSnowball < 5)
				{
					continue;
				}
				float f4 = 0.3F;
				Box axisalignedbb = entity1.boundingBox.expand(f4, f4, f4);
				HitResult movingobjectposition1 = axisalignedbb.method_89(vec3d, vec3d1);
				if(movingobjectposition1 == null)
				{
					continue;
				}
				double d1 = vec3d.method_1294(movingobjectposition1.field_1988); //distance
				if(d1 < d || d == 0.0D)
				{
					entity = entity1;
					d = d1;
				}
			}

			if(entity != null)
			{
				result = new HitResult(entity);
			}
		}
		if(result != null)
		{
			if(result.field_1989 != null)
			{
				if(result.field_1989.damage(thrower, 0));
				int j = MathHelper.floor(result.field_1989.boundingBox.minX);
				int i1 = MathHelper.floor(result.field_1989.boundingBox.minY);
				int k1 = MathHelper.floor(result.field_1989.boundingBox.minZ);
				Lightning entitylightningbolt = new Lightning(this.level, j, i1, k1);
				entitylightningbolt.setPositionAndAngles(j, i1, k1, this.yaw, 0.0F);
				this.level.spawnEntity(entitylightningbolt);
			} else
			{
				int k = MathHelper.floor(this.x);
				int j1 = MathHelper.floor(this.y);
				int l1 = MathHelper.floor(this.z);
				Lightning entitylightningbolt1 = new Lightning(this.level, this.x, this.y, this.z);
				entitylightningbolt1.setPositionAndAngles(k, j1, l1, this.yaw, 0.0F);
				this.level.spawnEntity(entitylightningbolt1);
			}
			for(int l = 0; l < 8; l++)
			{
				this.level.addParticle("largesmoke", this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
			}

			this.remove();
		}
		this.x += this.velocityX;
		this.y += this.velocityY;
		this.z += this.velocityZ;
		float f = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
		this.yaw = (float)((Math.atan2(this.velocityX, this.velocityZ) * 180D) / Math.PI);
		for(this.pitch = (float)((Math.atan2(this.velocityY, f) * 180D) / Math.PI); this.pitch - this.prevPitch < -180F; this.prevPitch -= 360F) { }
		for(; this.pitch - this.prevPitch >= 180F; this.prevPitch += 360F) { }
		for(; this.yaw - this.prevYaw < -180F; this.prevYaw -= 360F) { }
		for(; this.yaw - this.prevYaw >= 180F; this.prevYaw += 360F) { }
		this.pitch = this.prevPitch + (this.pitch - this.prevPitch) * 0.2F;
		this.yaw = this.prevYaw + (this.yaw - this.prevYaw) * 0.2F;
		float f1 = 0.99F;
		float f2 = 0.03F;
		if(this.method_1334()) //isInWater
		{
			for(int i2 = 0; i2 < 4; i2++)
			{
				this.level.addParticle("bubble", this.x - this.velocityX * 0.25d, this.y - this.velocityY * 0.25d, this.z - this.velocityZ * 0.25d, this.velocityX, this.velocityY, this.velocityZ);
			}

			f1 = 0.8F;
		}
		this.velocityX *= f1;
		this.velocityY *= f1;
		this.velocityZ *= f1;
		this.velocityY -= f2;
		this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
	}
	
	
	@Override
	protected void initDataTracker() {
	
	}
	
	@Override
	protected void readCustomDataFromTag(CompoundTag nbt) {
		this.xTileSnowball = nbt.getShort("xTile");
		this.yTileSnowball = nbt.getShort("yTile");
		this.zTileSnowball = nbt.getShort("zTile");
		
		this.inTileSnowball = nbt.getByte("inTile") & 0xff;
		this.shakeSnowball = nbt.getByte("shake") & 0xff;
		this.inGroundSnowball = nbt.getByte("inGround") == 1;
	}

	@Override
	protected void writeCustomDataToTag(CompoundTag nbt) {
		nbt.put("xTile", (short)this.xTileSnowball);
		nbt.put("yTile", (short)this.yTileSnowball);
		nbt.put("zTile", (short)this.zTileSnowball);

		nbt.put("inTile", (byte)this.inTileSnowball);
		nbt.put("shake", (byte)this.shakeSnowball);
		nbt.put("inGround", (byte)(this.inGroundSnowball ? 1 : 0));
	}
	@Override
	public void onPlayerCollision(PlayerBase player) 
	{
		if(this.inGroundSnowball && this.thrower == player && this.shakeSnowball <= 0 && player.inventory.addStack(new ItemInstance(ItemBase.arrow, 1))) {
			this.level.playSound(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			player.onItemPickup(this, 1);
			this.remove();
		}
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public float getEyeHeight() {
		return 0.0f;
	}
	
	public boolean shouldRenderAtDistance(double d) {
		double d1 = boundingBox.averageDimension() * 4 * 64;
		return d < d1 * d1;
	}
	
}
