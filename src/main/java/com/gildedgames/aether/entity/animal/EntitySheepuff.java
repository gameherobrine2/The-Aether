package com.gildedgames.aether.entity.animal;
import java.util.Random;

import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.entity.Item;
import net.minecraft.block.Wool;
import net.minecraft.entity.EntityBase;
import net.minecraft.item.ItemBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.block.BlockBase;
import net.minecraft.level.Level;

public class EntitySheepuff extends EntityAetherAnimal {
    public static final float[][] fleeceColorTable;
    private int amountEaten;
    
    public EntitySheepuff(final Level level) {
        super(level);
        this.texture = "aether:textures/entity/sheepuff.png";
        this.setSize(0.9f, 1.3f);
        this.setFleeceColor(getRandomFleeceColor(this.rand));
        this.amountEaten = 0;
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(16, new Byte((byte)0));
    }
    
    @Override
    protected void getDrops() {
        if (!this.getSheared()) {
            this.dropItem(new ItemInstance(BlockBase.WOOL.id, 1 + this.rand.nextInt(2), this.getFleeceColor()), 0.0f);
        }
    }
    
    @Override
    public boolean interact(final PlayerBase entityplayer) {
        final ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        if (itemstack != null && itemstack.itemId == ItemBase.shears.id && !this.getSheared()) {
            if (!this.level.isServerSide) {
                if (this.getPuffed()) {
                    this.setPuffed(false);
                    for (int i = 2 + this.rand.nextInt(3), j = 0; j < i; ++j) {
                        final Item dropItem;
                        final Item entityitem = dropItem = this.dropItem(new ItemInstance(BlockBase.WOOL.id, 1, this.getFleeceColor()), 1.0f);
                        dropItem.velocityY += this.rand.nextFloat() * 0.05f;
                        final Item item = entityitem;
                        item.velocityX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
                        final Item item2 = entityitem;
                        item2.velocityZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
                    }
                }
                else {
                    this.setSheared(true);
                    for (int i = 2 + this.rand.nextInt(3), j = 0; j < i; ++j) {
                        final Item dropItem2;
                        final Item entityitem = dropItem2 = this.dropItem(new ItemInstance(BlockBase.WOOL.id, 1, this.getFleeceColor()), 1.0f);
                        dropItem2.velocityY += this.rand.nextFloat() * 0.05f;
                        final Item item3 = entityitem;
                        item3.velocityX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
                        final Item item4 = entityitem;
                        item4.velocityZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
                    }
                }
            }
            itemstack.applyDamage(1, entityplayer);
        }
        if (itemstack != null && itemstack.itemId == ItemBase.dyePowder.id && !this.getSheared()) {
            final int colour = Wool.getColour(itemstack.getDamage());
            if (this.getFleeceColor() != colour) {
                if (this.getPuffed() && itemstack.count >= 2) {
                    this.setFleeceColor(colour);
                    final ItemInstance itemInstance = itemstack;
                    itemInstance.count -= 2;
                }
                else if (!this.getPuffed()) {
                    this.setFleeceColor(colour);
                    final ItemInstance itemInstance2 = itemstack;
                    --itemInstance2.count;
                }
            }
        }
        return false;
    }
    
    @Override
    protected void jump() {
        if (this.getPuffed()) {
            this.velocityY = 1.8;
            this.velocityX += this.rand.nextGaussian() * 0.5;
            this.velocityZ += this.rand.nextGaussian() * 0.5;
        }
        else {
            this.velocityY = 0.41999998688697815;
        }
    }
    
    @Override
    public void tick() {
        super.tick();
        if (this.getPuffed()) {
            this.fallDistance = 0.0f;
            if (this.velocityY < -0.05) {
                this.velocityY = -0.05;
            }
        }
        if (this.rand.nextInt(100) == 0) {
            final int x = MathHelper.floor(this.x);
            final int y = MathHelper.floor(this.y);
            final int z = MathHelper.floor(this.z);
            if (this.level.getTileId(x, y - 1, z) == AetherBlocks.AETHER_GRASS_BLOCK.id) {
                this.level.setTile(x, y - 1, z, AetherBlocks.AETHER_DIRT.id);
                ++this.amountEaten;
            }
        }
        if (this.amountEaten == 5 && !this.getSheared() && !this.getPuffed()) {
            this.setPuffed(true);
            this.amountEaten = 0;
        }
        if (this.amountEaten == 10 && this.getSheared() && !this.getPuffed()) {
            this.setSheared(false);
            this.setFleeceColor(0);
            this.amountEaten = 0;
        }
    }
    
    @Override
    public void writeCustomDataToTag(final CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("Sheared", this.getSheared());
        tag.put("Puffed", this.getPuffed());
        tag.put("Color", (byte)this.getFleeceColor());
    }
    
    @Override
    public void readCustomDataFromTag(final CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.setSheared(tag.getBoolean("Sheared"));
        this.setPuffed(tag.getBoolean("Puffed"));
        this.setFleeceColor(tag.getByte("Color"));
    }
    
    @Override
    protected String getAmbientSound() {
        return "mob.sheep";
    }
    
    @Override
    protected String getHurtSound() {
        return "mob.sheep";
    }
    
    @Override
    protected String getDeathSound() {
        return "mob.sheep";
    }
    
    public int getFleeceColor() {
        return this.dataTracker.getByte(16) & 0xF;
    }
    
    public void setFleeceColor(final int i) {
        final byte byte0 = this.dataTracker.getByte(16);
        this.dataTracker.setInt(16, (byte)((byte0 & 0xF0) | (i & 0xF)));
    }
    
    public boolean getSheared() {
        return (this.dataTracker.getByte(16) & 0x10) != 0x0;
    }
    
    public void setSheared(final boolean flag) {
        final byte byte0 = this.dataTracker.getByte(16);
        if (flag) {
            this.dataTracker.setInt(16, (byte)(byte0 | 0x10));
        }
        else {
            this.dataTracker.setInt(16, (byte)(byte0 & 0xFFFFFFEF));
        }
    }
    
    public boolean getPuffed() {
        return (this.dataTracker.getByte(16) & 0x20) != 0x0;
    }
    
    public void setPuffed(final boolean flag) {
        final byte byte0 = this.dataTracker.getByte(16);
        if (flag) {
            this.dataTracker.setInt(16, (byte)(byte0 | 0x20));
        }
        else {
            this.dataTracker.setInt(16, (byte)(byte0 & 0xFFFFFFDF));
        }
    }
    
    public static int getRandomFleeceColor(final Random random) {
        final int i = random.nextInt(100);
        if (i < 5) {
            return 3;
        }
        if (i < 10) {
            return 9;
        }
        if (i < 15) {
            return 5;
        }
        if (i < 18) {
            return 6;
        }
        return (random.nextInt(500) != 0) ? 0 : 10;
    }
    
    static {
        fleeceColorTable = new float[][] { { 1.0f, 1.0f, 1.0f }, { 0.975f, 0.85f, 0.6f }, { 0.95f, 0.75f, 0.925f }, { 0.8f, 0.85f, 0.975f }, { 0.95f, 0.95f, 0.6f }, { 0.75f, 0.9f, 0.55f }, { 0.975f, 0.85f, 0.9f }, { 0.65f, 0.65f, 0.65f }, { 0.8f, 0.8f, 0.8f }, { 0.65f, 0.8f, 0.85f }, { 0.85f, 0.7f, 0.95f }, { 0.6f, 0.7f, 0.9f }, { 0.75f, 0.7f, 0.65f }, { 0.7f, 0.75f, 0.6f }, { 0.9f, 0.65f, 0.65f }, { 0.55f, 0.55f, 0.55f } };
    }
}
