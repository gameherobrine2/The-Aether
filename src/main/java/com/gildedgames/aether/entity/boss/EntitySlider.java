package com.gildedgames.aether.entity.boss;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.IAetherBoss;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.NameGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.WalkingBase;
import net.minecraft.entity.living.FlyingBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.Pickaxe;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntitySlider extends FlyingBase implements IAetherBoss, MobSpawnDataProvider
{
    public int moveTimer;
    public int dennis;
    public int rennis;
    public int chatTime;
    public EntityBase target;
    public boolean awake;
    public boolean gotMovement;
    public boolean crushed;
    public float speedy;
    public float harvey;
    public int direction;
    private int dungeonX;
    private int dungeonY;
    private int dungeonZ;
    public String bossName;

    public EntitySlider(final Level level)
    {
        super(level);
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.setSize(2.0f, 2.0f);
        this.health = 500;
        this.dennis = 1;
        this.texture = "aether:textures/entity/sliderSleep.png";
        this.chatTime = 60;
        this.bossName = NameGen.gen();
    }

    public void initDataTracker()
    {
        super.initDataTracker();
        this.x = Math.floor(this.x + 0.5);
        this.y = Math.floor(this.y + 0.5);
        this.z = Math.floor(this.z + 0.5);
    }

    public boolean method_940()
    {
        return false;
    }

    @Override
    protected String getAmbientSound()
    {
        return "ambient.cave.cave";
    }

    @Override
    protected String getHurtSound()
    {
        return "step.stone";
    }

    @Override
    protected String getDeathSound()
    {
        return "aether:aether.sound.bosses.slider.sliderdeath";
    }

    @Override
    public void writeCustomDataToTag(final CompoundTag tag)
    {
        super.writeCustomDataToTag(tag);
        tag.put("Speedy", this.speedy);
        tag.put("MoveTimer", (short) this.moveTimer);
        tag.put("Direction", (short) this.direction);
        tag.put("GotMovement", this.gotMovement);
        tag.put("Awake", this.awake);
        tag.put("DungeonX", this.dungeonX);
        tag.put("DungeonY", this.dungeonY);
        tag.put("DungeonZ", this.dungeonZ);
        tag.put("IsCurrentBoss", this.isCurrentBoss());
        tag.put("BossName", this.bossName);
    }

    @Override
    public void readCustomDataFromTag(final CompoundTag tag)
    {
        super.readCustomDataFromTag(tag);
        this.speedy = tag.getFloat("Speedy");
        this.moveTimer = tag.getShort("MoveTimer");
        this.direction = tag.getShort("Direction");
        this.gotMovement = tag.getBoolean("GotMovement");
        this.awake = tag.getBoolean("Awake");
        this.dungeonX = tag.getInt("DungeonX");
        this.dungeonY = tag.getInt("DungeonY");
        this.dungeonZ = tag.getInt("DungeonZ");
        if (tag.getBoolean("IsCurrentBoss"))
        {
            AetherMod.currentBoss = this;
        }
        this.bossName = tag.getString("BossName");
        if (this.awake)
        {
            if (this.criticalCondition())
            {
                this.texture = "aether:textures/entity/sliderAwake_red.png";
            }
            else
            {
                this.texture = "aether:textures/entity/sliderAwake.png";
            }
        }
    }

    public boolean criticalCondition()
    {
        return this.health <= 125;
    }

    @Override
    public void tick()
    {
        super.tick();
        final float field_1012 = 0.0f;
        this.yaw = field_1012;
        this.pitch = field_1012;
        this.field_1012 = field_1012;
        if (this.awake)
        {
            if (this.target != null && this.target instanceof Living)
            {
                final Living e1 = (Living) this.target;
                if (e1.health <= 0)
                {
                    this.awake = false;
                    AetherMod.currentBoss = null;
                    this.target = null;
                    this.texture = "aether:textures/entity/sliderSleep.png";
                    this.stop();
                    this.openDoor();
                    this.moveTimer = 0;
                    return;
                }
            }
            else
            {
                if (this.target != null && this.target.removed)
                {
                    this.awake = false;
                    AetherMod.currentBoss = null;
                    this.target = null;
                    this.texture = "aether:textures/entity/sliderSleep.png";
                    this.stop();
                    this.openDoor();
                    this.moveTimer = 0;
                    return;
                }
                if (this.target == null)
                {
                    this.target = this.level.getClosestPlayerTo(this, -1.0);
                    if (this.target == null)
                    {
                        this.awake = false;
                        AetherMod.currentBoss = null;
                        this.target = null;
                        this.texture = "aether:textures/entity/sliderSleep.png";
                        this.stop();
                        this.openDoor();
                        this.moveTimer = 0;
                        return;
                    }
                }
            }
            if (this.gotMovement)
            {
                if (this.field_1626)
                {
                    final double x = this.x - 0.5;
                    final double y = this.boundingBox.minY + 0.75;
                    final double z = this.z - 0.5;
                    this.crushed = false;
                    if (y < 124.0 && y > 4.0)
                    {
                        if (this.direction == 0)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + a), (int) (y + 1.5), (int) (z + b));
                            }
                        }
                        else if (this.direction == 1)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + a), (int) (y - 1.5), (int) (z + b));
                            }
                        }
                        else if (this.direction == 2)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + 1.5), (int) (y + a), (int) (z + b));
                            }
                        }
                        else if (this.direction == 3)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x - 1.5), (int) (y + a), (int) (z + b));
                            }
                        }
                        else if (this.direction == 4)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + a), (int) (y + b), (int) (z + 1.5));
                            }
                        }
                        else if (this.direction == 5)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + a), (int) (y + b), (int) (z - 1.5));
                            }
                        }
                    }
                    if (this.crushed)
                    {
                        this.level.playSound(this.x, this.y, this.z, "random.explode", 3.0f, (0.625f + (this.level.rand.nextFloat() - this.level.rand.nextFloat()) * 0.2f) * 0.7f);
                        this.level.playSound((EntityBase) this, "aether:aether.sound.bosses.slider.slidercollide", 2.5f, 1.0f / (this.rand.nextFloat() * 0.2f + 0.9f));
                    }
                    this.stop();
                }
                else
                {
                    if (this.speedy < 2.0f)
                    {
                        this.speedy += (this.criticalCondition() ? 0.0325f : 0.025f);
                    }
                    this.velocityX = 0.0;
                    this.velocityY = 0.0;
                    this.velocityZ = 0.0;
                    if (this.direction == 0)
                    {
                        this.velocityY = this.speedy;
                        if (this.boundingBox.minY > this.target.boundingBox.minY + 0.35)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 1)
                    {
                        this.velocityY = -this.speedy;
                        if (this.boundingBox.minY < this.target.boundingBox.minY - 0.25)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 2)
                    {
                        this.velocityX = this.speedy;
                        if (this.x > this.target.x + 0.125)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 3)
                    {
                        this.velocityX = -this.speedy;
                        if (this.x < this.target.x - 0.125)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 4)
                    {
                        this.velocityZ = this.speedy;
                        if (this.z > this.target.z + 0.125)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 5)
                    {
                        this.velocityZ = -this.speedy;
                        if (this.z < this.target.z - 0.125)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                }
            }
            else if (this.moveTimer > 0)
            {
                --this.moveTimer;
                if (this.criticalCondition() && this.rand.nextInt(2) == 0)
                {
                    --this.moveTimer;
                }
                this.velocityX = 0.0;
                this.velocityY = 0.0;
                this.velocityZ = 0.0;
            }
            else
            {
                final double a2 = Math.abs(this.x - this.target.x);
                final double b2 = Math.abs(this.boundingBox.minY - this.target.boundingBox.minY);
                final double c = Math.abs(this.z - this.target.z);
                if (a2 > c)
                {
                    this.direction = 2;
                    if (this.x > this.target.x)
                    {
                        this.direction = 3;
                    }
                }
                else
                {
                    this.direction = 4;
                    if (this.z > this.target.z)
                    {
                        this.direction = 5;
                    }
                }
                if ((b2 > a2 && b2 > c) || (b2 > 0.25 && this.rand.nextInt(5) == 0))
                {
                    this.direction = 0;
                    if (this.y > this.target.y)
                    {
                        this.direction = 1;
                    }
                }
                this.level.playSound((EntityBase) this, "aether:aether.sound.bosses.slider.slidermove", 2.5f, 1.0f / (this.rand.nextFloat() * 0.2f + 0.9f));
                this.gotMovement = true;
            }
        }
        if (this.harvey > 0.01f)
        {
            this.harvey *= 0.8f;
        }
        if (this.chatTime > 0)
        {
            --this.chatTime;
        }
    }

    private void openDoor()
    {
        final int x = this.dungeonX + 15;
        for (int y = this.dungeonY + 1; y < this.dungeonY + 5; ++y)
        {
            for (int z = this.dungeonZ + 6; z < this.dungeonZ + 10; ++z)
            {
                this.level.setTileInChunk(x, y, z, 0);
            }
        }
    }

    public void method_1353(final EntityBase entity)
    {
        if (this.awake && this.gotMovement)
        {
            final boolean flag = entity.damage(this, 6);
            if (flag && entity instanceof Living)
            {
                this.level.playSound((EntityBase) this, "aether:aether.sound.bosses.slider.slidercollide", 2.5f, 1.0f / (this.rand.nextFloat() * 0.2f + 0.9f));
                if (entity instanceof WalkingBase || entity instanceof PlayerBase)
                {
                    final Living living;
                    final Living ek = living = (Living) entity;
                    living.velocityY += 0.35;
                    final Living living2 = ek;
                    living2.velocityX *= 2.0;
                    final Living living3 = ek;
                    living3.velocityZ *= 2.0;
                }
                this.stop();
            }
        }
    }

    @Override
    protected void getDrops()
    {
        for (int i = 0; i < 7 + this.rand.nextInt(3); ++i)
        {
            this.dropItem(AetherBlocks.DUNGEON_STONE.id, 1);
        }
        this.dropItem(new ItemInstance(AetherItems.Key, 1, 0), 0.0f);
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.level.getTileId(i, j - 1, k) == BlockBase.GRASS.id && this.level.getLightLevel(i, j, k) > 8 && super.canSpawn();
    }

    public void stop()
    {
        this.gotMovement = false;
        this.moveTimer = 12;
        this.direction = 0;
        this.speedy = 0.0f;
        this.velocityX = 0.0;
        this.velocityY = 0.0;
        this.velocityZ = 0.0;
    }

    private void chatItUp(final String s)
    {
        // todo: make this neater, change to something that isn't jank?
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER)
            return;

        if (this.chatTime <= 0)
        {
            ((Minecraft) FabricLoader.getInstance().getGameInstance()).overlay.addChatMessage(s);
            this.chatTime = 60;
        }
    }

    @Override
    public boolean damage(final EntityBase target, final int amount)
    {
        if (target == null || !(target instanceof PlayerBase))
        {
            return false;
        }
        final PlayerBase p1 = (PlayerBase) target;
        final ItemInstance stack = p1.getHeldItem();
        if (stack == null || stack.getType() == null)
        {
            return false;
        }
        if (!(stack.getType() instanceof Pickaxe))
        {
            this.chatItUp("Hmm. Perhaps I need to attack it with a Pickaxe?");
            return false;
        }
        final Pickaxe tool = (Pickaxe) stack.getType();
        if (!tool.isEffectiveOn(BlockBase.STONE))
        {
            this.chatItUp("Hmm. Perhaps I need to attack it with a Pickaxe?");
            return false;
        }
        final boolean flag = super.damage(target, Math.max(0, amount));
        if (flag)
        {
            for (int j = 0; j < ((this.health <= 0) ? 16 : 48); ++j)
            {
                final double a = this.x + (this.rand.nextFloat() - this.rand.nextFloat()) * 1.5;
                final double b = this.boundingBox.minY + 1.75 + (this.rand.nextFloat() - this.rand.nextFloat()) * 1.5;
                final double c = this.z + (this.rand.nextFloat() - this.rand.nextFloat()) * 1.5;
                if (this.health <= 0)
                {
                    this.level.addParticle("explode", a, b, c, 0.0, 0.0, 0.0);
                }
            }
            if (this.health <= 0)
            {
                this.removed = true;
                this.openDoor();
                this.unlockBlock(this.dungeonX, this.dungeonY, this.dungeonZ);
                this.level.setTileWithMetadata(this.dungeonX + 7, this.dungeonY + 1, this.dungeonZ + 7, BlockBase.TRAPDOOR.id, 3);
                this.level.setTileWithMetadata(this.dungeonX + 8, this.dungeonY + 1, this.dungeonZ + 7, BlockBase.TRAPDOOR.id, 2);
                this.level.setTileWithMetadata(this.dungeonX + 7, this.dungeonY + 1, this.dungeonZ + 8, BlockBase.TRAPDOOR.id, 3);
                this.level.setTileWithMetadata(this.dungeonX + 8, this.dungeonY + 1, this.dungeonZ + 8, BlockBase.TRAPDOOR.id, 2);
                AetherMod.giveAchievement(AetherAchievements.defeatBronze);
                AetherMod.currentBoss = null;
            }
            else if (!this.awake)
            {
                this.level.playSound((EntityBase) this, "aether:aether.sound.bosses.slider.sliderawaken", 2.5f, 1.0f / (this.rand.nextFloat() * 0.2f + 0.9f));
                this.awake = true;
                this.target = target;
                this.texture = "aether:textures/entity/sliderAwake.png";
                final int x = this.dungeonX + 15;
                for (int y = this.dungeonY + 1; y < this.dungeonY + 8; ++y)
                {
                    for (int z = this.dungeonZ + 5; z < this.dungeonZ + 11; ++z)
                    {
                        this.level.setTileInChunk(x, y, z, AetherBlocks.LOCKED_DUNGEON_STONE.id);
                    }
                }
                AetherMod.currentBoss = this;
            }
            else if (this.gotMovement)
            {
                this.speedy *= 0.75f;
            }
            final double a2 = Math.abs(this.x - target.x);
            final double c2 = Math.abs(this.z - target.z);
            if (a2 > c2)
            {
                this.dennis = 1;
                this.rennis = 0;
                if (this.x > target.x)
                {
                    this.dennis = -1;
                }
            }
            else
            {
                this.rennis = 1;
                this.dennis = 0;
                if (this.z > target.z)
                {
                    this.rennis = -1;
                }
            }
            this.harvey = 0.7f - this.health / 875.0f;
            if (this.criticalCondition())
            {
                this.texture = "aether:textures/entity/sliderAwake_red.png";
            }
            else
            {
                this.texture = "aether:textures/entity/sliderAwake.png";
            }
        }
        return flag;
    }

    private void unlockBlock(final int i, final int j, final int k)
    {
        final int id = this.level.getTileId(i, j, k);
        if (id == AetherBlocks.LOCKED_DUNGEON_STONE.id)
        {
            this.level.setTileWithMetadata(i, j, k, AetherBlocks.DUNGEON_STONE.id, this.level.getTileMeta(i, j, k));
            this.unlockBlock(i + 1, j, k);
            this.unlockBlock(i - 1, j, k);
            this.unlockBlock(i, j + 1, k);
            this.unlockBlock(i, j - 1, k);
            this.unlockBlock(i, j, k + 1);
            this.unlockBlock(i, j, k - 1);
        }
        if (id == AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id)
        {
            this.level.setTileWithMetadata(i, j, k, AetherBlocks.LIGHT_DUNGEON_STONE.id, this.level.getTileMeta(i, j, k));
            this.unlockBlock(i + 1, j, k);
            this.unlockBlock(i - 1, j, k);
            this.unlockBlock(i, j + 1, k);
            this.unlockBlock(i, j - 1, k);
            this.unlockBlock(i, j, k + 1);
            this.unlockBlock(i, j, k - 1);
        }
    }

    public void accelerate(final double x, final double y, final double z)
    {
    }

    @Override
    public void method_925(final EntityBase entity, final int i, final double d, final double d1)
    {
    }

    public void blockCrush(final int x, final int y, final int z)
    {
        final int a = this.level.getTileId(x, y, z);
        final int b = this.level.getTileMeta(x, y, z);
        if (a == 0 || a == AetherBlocks.LOCKED_DUNGEON_STONE.id || a == AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id)
        {
            return;
        }
        // todo: particleMinecraftClientAccessor.getMCinstance().particleManager.addTileBreakParticles(x, y, z, a, b);
        BlockBase.BY_ID[a].onBlockRemoved(this.level, x, y, z);
        BlockBase.BY_ID[a].drop(this.level, x, y, z, b);
        this.level.setTile(x, y, z, 0);
        this.crushed = true;
        this.addSquirrelButts(x, y, z);
    }

    public void addSquirrelButts(final int x, final int y, final int z)
    {
        final double a = x + 0.5 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.375;
        final double b = y + 0.5 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.375;
        final double c = z + 0.5 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.375;
        this.level.addParticle("explode", a, b, c, 0.0, 0.0, 0.0);
    }

    public void setDungeon(final int i, final int j, final int k)
    {
        this.dungeonX = i;
        this.dungeonY = j;
        this.dungeonZ = k;
    }

    @Override
    public int getBossHP()
    {
        return this.health;
    }

    @Override
    public int getBossMaxHP()
    {
        return 500;
    }

    @Override
    public boolean isCurrentBoss()
    {
        return AetherMod.currentBoss != null && this.equals(AetherMod.currentBoss);
    }

    @Override
    public int getBossEntityID()
    {
        return this.entityId;
    }

    @Override
    public String getBossTitle()
    {
        return this.bossName + ", the Slider";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Slider");
    }
}
