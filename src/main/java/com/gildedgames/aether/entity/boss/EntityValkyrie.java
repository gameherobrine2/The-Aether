package com.gildedgames.aether.entity.boss;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityDungeonMob;
import com.gildedgames.aether.entity.base.IAetherBoss;
import com.gildedgames.aether.entity.mobs.EntityHomeShot;
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
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.AbstractTag;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.DoubleTag;
import net.minecraft.util.io.ListTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

public class EntityValkyrie extends EntityDungeonMob implements IAetherBoss
{
    public boolean isSwinging;
    public boolean boss;
    public boolean duel;
    public boolean hasDungeon;
    public int teleTimer;
    public int angerLevel;
    public int timeLeft;
    public int chatTime;
    public int dungeonX;
    public int dungeonY;
    public int dungeonZ;
    public int dungeonEntranceZ;
    public double safeX;
    public double safeY;
    public double safeZ;
    public float sinage;
    public double lastMotionY;
    public String bossName;

    public EntityValkyrie(final Level level)
    {
        super(level);
        this.setSize(0.8f, 1.6f);
        this.texture = "aether:textures/entity/valkyrie.png";
        this.teleTimer = this.rand.nextInt(250);
        this.health = 50;
        this.movementSpeed = 0.5f;
        this.timeLeft = 1200;
        this.attackStrength = 7;
        this.safeX = this.x;
        this.safeY = this.y;
        this.safeZ = this.z;
    }

    public EntityValkyrie(final Level world, final double x, final double y, final double z, final boolean flag)
    {
        super(world);
        this.setSize(0.8f, 1.6f);
        this.bossName = NameGen.gen();
        this.texture = "aether:textures/entity/valkyrie.png";
        if (flag)
        {
            this.texture = "aether:textures/entity/valkyrie2.png";
            this.health = 500;
            this.boss = true;
        }
        else
        {
            this.health = 50;
        }
        this.teleTimer = this.rand.nextInt(250);
        this.movementSpeed = 0.5f;
        this.timeLeft = 1200;
        this.attackStrength = 7;
        this.x = x;
        this.safeX = x;
        this.y = y;
        this.safeY = y;
        this.z = z;
        this.safeZ = z;
        this.hasDungeon = false;
    }

    public void handleFallDamage(final float height)
    {
    }

    public void tick()
    {
        this.lastMotionY = this.velocityY;
        super.tick();
        if (!this.onGround && this.entity != null && this.lastMotionY >= 0.0 && this.velocityY < 0.0 && this.distanceTo(this.entity) <= 16.0f && this.method_928(this.entity))
        {
            final double a = this.entity.x - this.x;
            final double b = this.entity.z - this.z;
            final double angle = Math.atan2(a, b);
            this.velocityX = Math.sin(angle) * 0.25;
            this.velocityZ = Math.cos(angle) * 0.25;
        }
        if (!this.onGround && !this.method_932() && Math.abs(this.velocityY - this.lastMotionY) > 0.07 && Math.abs(this.velocityY - this.lastMotionY) < 0.09)
        {
            this.velocityY += 0.054999999701976776;
            if (this.velocityY < -0.2750000059604645)
            {
                this.velocityY = -0.2750000059604645;
            }
        }
        this.movementSpeed = ((this.entity == null) ? 0.5f : 1.0f);
        if (this.level.difficulty <= 0 && (this.entity != null || this.angerLevel > 0))
        {
            this.angerLevel = 0;
            this.entity = null;
        }
        if (this.isSwinging)
        {
            this.lastHandSwingProgress += 0.15f;
            this.handSwingProgress += 0.15f;
            if (this.lastHandSwingProgress > 1.0f || this.handSwingProgress > 1.0f)
            {
                this.isSwinging = false;
                this.lastHandSwingProgress = 0.0f;
                this.handSwingProgress = 0.0f;
            }
        }
        if (!this.onGround)
        {
            this.sinage += 0.75f;
        }
        else
        {
            this.sinage += 0.15f;
        }
        if (this.sinage > 6.283186f)
        {
            this.sinage -= 6.283186f;
        }
        if (!this.otherDimension())
        {
            --this.timeLeft;
            if (this.timeLeft <= 0)
            {
                this.removed = true;
                this.onSpawnedFromSpawner();
            }
        }
    }

    public boolean otherDimension()
    {
        return true;
    }

    public void teleport(double x, double y, double z, final int rad)
    {
        int a = this.rand.nextInt(rad + 1);
        int b = this.rand.nextInt(rad / 2);
        int c = rad - a;
        a *= this.rand.nextInt(2) * 2 - 1;
        b *= this.rand.nextInt(2) * 2 - 1;
        c *= this.rand.nextInt(2) * 2 - 1;
        x += a;
        y += b;
        z += c;
        int newX = (int) Math.floor(x - 0.5);
        int newY = (int) Math.floor(y - 0.5);
        int newZ = (int) Math.floor(z - 0.5);
        boolean flag = false;
        for (int q = 0; q < 32 && !flag; ++q)
        {
            final int i = newX + (this.rand.nextInt(rad / 2) - this.rand.nextInt(rad / 2));
            final int j = newY + (this.rand.nextInt(rad / 2) - this.rand.nextInt(rad / 2));
            final int k = newZ + (this.rand.nextInt(rad / 2) - this.rand.nextInt(rad / 2));
            if (j <= 124)
            {
                if (j >= 5)
                {
                    if (this.isAirySpace(i, j, k) && this.isAirySpace(i, j + 1, k) && !this.isAirySpace(i, j - 1, k) && (!this.hasDungeon || (i > this.dungeonX && i < this.dungeonX + 20 && j > this.dungeonY && j < this.dungeonY + 12 && k > this.dungeonZ && k < this.dungeonZ + 20)))
                    {
                        newX = i;
                        newY = j;
                        newZ = k;
                        flag = true;
                    }
                }
            }
        }
        if (!flag)
        {
            this.teleFail();
        }
        else
        {
            this.onSpawnedFromSpawner();
            this.setPosition(newX + 0.5, newY + 0.5, newZ + 0.5);
            this.velocityX = 0.0;
            this.velocityY = 0.0;
            this.velocityZ = 0.0;
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            this.pitch = 0.0f;
            this.yaw = 0.0f;
            this.setTarget(null);
            this.field_1012 = this.rand.nextFloat() * 360.0f;
            this.onSpawnedFromSpawner();
            this.teleTimer = this.rand.nextInt(40);
        }
    }

    public boolean isAirySpace(final int x, final int y, final int z)
    {
        final int p = this.level.getTileId(x, y, z);
        return p == 0 || BlockBase.BY_ID[p].getCollisionShape(this.level, x, y, z) == null;
    }

    public boolean method_940()
    {
        return !this.boss;
    }

    public boolean interact(final PlayerBase entityplayer)
    {
        this.method_924(entityplayer, 180.0f, 180.0f);
        if (!this.boss)
        {
            if (this.timeLeft >= 1200)
            {
                final ItemInstance itemstack = entityplayer.getHeldItem();
                if (itemstack != null && itemstack.itemId == AetherItems.VictoryMedal.id && itemstack.count >= 0)
                {
                    if (itemstack.count >= 10)
                    {
                        this.chatItUp("Umm... that's a nice pile of medallions you have there...");
                    }
                    else if (itemstack.count >= 5)
                    {
                        this.chatItUp("That's pretty impressive, but you won't defeat me.");
                    }
                    else
                    {
                        this.chatItUp("You think you're a tough guy, eh? Well, bring it on!");
                    }
                }
                else
                {
                    final int pokey = this.rand.nextInt(3);
                    if (pokey == 2)
                    {
                        this.chatItUp("What's that? You want to fight? Aww, what a cute little human.");
                    }
                    else if (pokey == 1)
                    {
                        this.chatItUp("You're not thinking of fighting a big, strong Valkyrie are you?");
                    }
                    else
                    {
                        this.chatItUp("I don't think you should bother me, you could get really hurt.");
                    }
                }
            }
        }
        else if (this.duel)
        {
            this.chatItUp("If you wish to challenge me, strike at any time.");
        }
        else if (!this.duel)
        {
            final ItemInstance itemstack = entityplayer.getHeldItem();
            if (itemstack != null && itemstack.itemId == AetherItems.VictoryMedal.id && itemstack.count >= 10)
            {
                final ItemInstance itemInstance = itemstack;
                itemInstance.count -= 10;
                if (itemstack.count <= 0)
                {
                    itemstack.unusedEmptyMethod1(entityplayer);
                    entityplayer.breakHeldItem();
                    this.chatTime = 0;
                    this.chatItUp("Very well, attack me when you wish to begin.");
                    this.duel = true;
                }
            }
            else
            {
                this.chatItUp("Show me 10 victory medals, and I will fight you.");
            }
        }
        return true;
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

    public void makeHomeShot(final int shots, final Living ep)
    {
        for (int i = 0; i < shots; ++i)
        {
            final EntityHomeShot e1 = new EntityHomeShot(this.level, this.x - this.velocityX / 2.0, this.y, this.z - this.velocityZ / 2.0, ep);
            this.level.spawnEntity(e1);
        }
    }

    protected void getDrops()
    {
        if (this.boss)
        {
            this.dropItem(new ItemInstance(AetherItems.Key, 1, 1), 0.0f);
            this.dropItem(ItemBase.goldSword.id, 1);
        }
        else
        {
            this.dropItem(AetherItems.VictoryMedal.id, 1);
        }
    }

    public void tickHandSwing()
    {
        super.tickHandSwing();
        ++this.teleTimer;
        if (this.teleTimer >= 450)
        {
            if (this.entity != null)
            {
                if (this.boss && this.onGround && this.rand.nextInt(2) == 0 && this.entity != null && this.entity instanceof Living)
                {
                    this.makeHomeShot(1, (Living) this.entity);
                    this.teleTimer = -100;
                }
                else
                {
                    this.teleport(this.entity.x, this.entity.y, this.entity.z, 7);
                }
            }
            else if (!this.onGround || this.boss)
            {
                this.teleport(this.safeX, this.safeY, this.safeZ, 6);
            }
            else
            {
                this.teleport(this.x, this.y, this.z, 12 + this.rand.nextInt(12));
            }
        }
        else if (this.teleTimer < 446 && (this.y <= 0.0 || this.y <= this.safeY - 16.0))
        {
            this.teleTimer = 446;
        }
        else if (this.teleTimer % 5 == 0 && this.entity != null && !this.method_928(this.entity))
        {
            this.teleTimer += 100;
        }
        if (this.onGround && this.teleTimer % 10 == 0 && !this.boss)
        {
            this.safeX = this.x;
            this.safeY = this.y;
            this.safeZ = this.z;
        }
        if (this.entity != null && this.entity.removed)
        {
            this.entity = null;
            if (this.boss)
            {
                this.unlockDoor();
                AetherMod.currentBoss = null;
            }
            this.angerLevel = 0;
        }
        if (this.chatTime > 0)
        {
            --this.chatTime;
        }
    }

    public void swingArm()
    {
        if (!this.isSwinging)
        {
            this.isSwinging = true;
            this.lastHandSwingProgress = 0.0f;
            this.handSwingProgress = 0.0f;
        }
    }

    public void teleFail()
    {
        this.teleTimer -= this.rand.nextInt(40) + 40;
        if (this.y <= 0.0)
        {
            this.teleTimer = 446;
        }
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.level.getLightLevel(i, j, k) > 8 && this.level.canSpawnEntity(this.boundingBox) && this.level.method_190(this, this.boundingBox).size() == 0 && !this.level.method_218(this.boundingBox);
    }

    @Override
    public void writeCustomDataToTag(final CompoundTag tag)
    {
        super.writeCustomDataToTag(tag);
        tag.put("Anger", (short) this.angerLevel);
        tag.put("TeleTimer", (short) this.teleTimer);
        tag.put("TimeLeft", (short) this.timeLeft);
        tag.put("Duel", this.duel);
        tag.put("DungeonX", this.dungeonX);
        tag.put("DungeonY", this.dungeonY);
        tag.put("DungeonZ", this.dungeonZ);
        tag.put("DungeonEntranceZ", this.dungeonEntranceZ);
        tag.put("SafePos", (AbstractTag) this.method_1329(this.safeX, this.safeY, this.safeZ));

        tag.put("Boss", this.boss);
        if (this.boss)
        {
            tag.put("IsCurrentBoss", this.isCurrentBoss());
            tag.put("BossName", this.bossName);
        }
    }

    @Override
    public void readCustomDataFromTag(final CompoundTag tag)
    {
        super.readCustomDataFromTag(tag);
        this.angerLevel = tag.getShort("Anger");
        this.teleTimer = tag.getShort("TeleTimer");
        this.timeLeft = tag.getShort("TimeLeft");
        this.duel = tag.getBoolean("Duel");
        this.boss = tag.getBoolean("Boss");
        this.dungeonX = tag.getInt("DungeonX");
        this.dungeonY = tag.getInt("DungeonY");
        this.dungeonZ = tag.getInt("DungeonZ");
        this.dungeonEntranceZ = tag.getInt("DungeonEntranceZ");
        if (this.boss)
        {
            this.texture = "aether:textures/entity/valkyrie2.png";
            this.bossName = tag.getString("BossName");
            if (tag.getBoolean("IsCurrentBoss"))
            {
                //todo: boss refactor
                AetherMod.currentBoss = this;
            }

        }
        final ListTag nbttaglist = tag.getListTag("SafePos");
        this.safeX = ((DoubleTag) nbttaglist.get(0)).data;
        this.safeY = ((DoubleTag) nbttaglist.get(1)).data;
        this.safeZ = ((DoubleTag) nbttaglist.get(2)).data;
    }

    @Override
    protected EntityBase getAttackTarget()
    {
        if (this.otherDimension() && (this.level.difficulty <= 0 || (this.boss && !this.duel) || this.angerLevel <= 0))
        {
            return null;
        }
        return super.getAttackTarget();
    }

    @Override
    public boolean damage(final EntityBase target, final int amount)
    {
        if (!(target instanceof PlayerBase) || this.level.difficulty <= 0)
        {
            this.teleport(this.x, this.y, this.z, 8);
            this.fire = 0;
            return false;
        }
        if (this.boss && (!this.duel || this.level.difficulty <= 0))
        {
            this.onSpawnedFromSpawner();
            final int pokey = this.rand.nextInt(2);
            if (pokey == 2)
            {
                this.chatItUp("Sorry, I don't fight with weaklings.");
            }
            else
            {
                this.chatItUp("Try defeating some weaker valkyries first.");
            }
            return false;
        }
        if (this.boss)
        {
            if (this.entity == null)
            {
                AetherMod.currentBoss = this;
                this.chatTime = 0;
                this.chatItUp("This will be your final battle!");
            }
            else
            {
                this.teleTimer += 60;
            }
        }
        else if (this.entity == null)
        {
            this.chatTime = 0;
            final int pokey = this.rand.nextInt(3);
            if (pokey == 2)
            {
                this.chatItUp("I'm not going easy on you!");
            }
            else if (pokey == 1)
            {
                this.chatItUp("You're gonna regret that!");
            }
            else
            {
                this.chatItUp("Now you're in for it!");
            }
        }
        else
        {
            this.teleTimer -= 10;
        }
        this.becomeAngryAt(target);
        final boolean flag = super.damage(target, amount);
        if (flag && this.health <= 0)
        {
            final int pokey2 = this.rand.nextInt(3);
            this.removed = true;
            if (this.boss)
            {
                this.removed = false;
                this.unlockDoor();
                this.unlockTreasure();
                this.chatItUp("You are truly... a mighty warrior...");
                AetherMod.currentBoss = null;
            }
            else if (pokey2 == 2)
            {
                this.chatItUp("Alright, alright! You win!");
            }
            else if (pokey2 == 1)
            {
                this.chatItUp("Okay, I give up! Geez!");
            }
            else
            {
                this.chatItUp("Oww! Fine, here's your medal...");
            }
            this.onSpawnedFromSpawner();
        }
        return flag;
    }

    @Override
    protected void tryAttack(final EntityBase target, final float f)
    {
        if (this.attackTime <= 0 && f < 2.75f && target.boundingBox.maxY > this.boundingBox.minY && target.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.swingArm();
            target.damage(this, this.attackStrength);
            if (target != null && this.entity != null && target == this.entity && target instanceof Living)
            {
                final Living e1 = (Living) target;
                if (e1.health <= 0)
                {
                    this.entity = null;
                    this.angerLevel = 0;
                    final int pokey = this.rand.nextInt(3);
                    this.chatTime = 0;
                    if (this.boss)
                    {
                        this.chatItUp("As expected of a human.");
                        this.unlockDoor();
                        AetherMod.currentBoss = null;
                    }
                    else if (pokey == 2)
                    {
                        this.chatItUp("You want a medallion? Try being less pathetic.");
                    }
                    else if (pokey == 1 && e1 instanceof PlayerBase)
                    {
                        final PlayerBase ep = (PlayerBase) e1;
                        final String s = ep.name;
                        this.chatItUp("Maybe some day, " + s + "... maybe some day.");
                    }
                    else
                    {
                        this.chatItUp("Humans aren't nearly as cute when they're dead.");
                    }
                }
            }
        }
    }

    private void becomeAngryAt(final EntityBase entity)
    {
        this.entity = entity;
        this.angerLevel = 200 + this.rand.nextInt(200);
        if (this.boss)
        {
            for (int k = this.dungeonZ + 2; k < this.dungeonZ + 23; k += 7)
            {
                if (this.level.getTileId(this.dungeonX - 1, this.dungeonY, k) == 0)
                {
                    this.dungeonEntranceZ = k;
                    this.level.setTileWithMetadata(this.dungeonX - 1, this.dungeonY, k, AetherBlocks.LOCKED_DUNGEON_STONE.id, 1);
                    this.level.setTileWithMetadata(this.dungeonX - 1, this.dungeonY, k + 1, AetherBlocks.LOCKED_DUNGEON_STONE.id, 1);
                    this.level.setTileWithMetadata(this.dungeonX - 1, this.dungeonY + 1, k + 1, AetherBlocks.LOCKED_DUNGEON_STONE.id, 1);
                    this.level.setTileWithMetadata(this.dungeonX - 1, this.dungeonY + 1, k, AetherBlocks.LOCKED_DUNGEON_STONE.id, 1);
                    return;
                }
            }
        }
    }

    private void unlockDoor()
    {
        this.level.setTileInChunk(this.dungeonX - 1, this.dungeonY, this.dungeonEntranceZ, 0);
        this.level.setTileInChunk(this.dungeonX - 1, this.dungeonY, this.dungeonEntranceZ + 1, 0);
        this.level.setTileInChunk(this.dungeonX - 1, this.dungeonY + 1, this.dungeonEntranceZ + 1, 0);
        this.level.setTileInChunk(this.dungeonX - 1, this.dungeonY + 1, this.dungeonEntranceZ, 0);
    }

    private void unlockTreasure()
    {
        this.level.setTileWithMetadata(this.dungeonX + 16, this.dungeonY + 1, this.dungeonZ + 9, BlockBase.TRAPDOOR.id, 3);
        this.level.setTileWithMetadata(this.dungeonX + 17, this.dungeonY + 1, this.dungeonZ + 9, BlockBase.TRAPDOOR.id, 2);
        this.level.setTileWithMetadata(this.dungeonX + 16, this.dungeonY + 1, this.dungeonZ + 10, BlockBase.TRAPDOOR.id, 3);
        this.level.setTileWithMetadata(this.dungeonX + 17, this.dungeonY + 1, this.dungeonZ + 10, BlockBase.TRAPDOOR.id, 2);
        AetherMod.giveAchievement(AetherAchievements.defeatSilver);
        for (int x = this.dungeonX - 26; x < this.dungeonX + 29; ++x)
        {
            for (int y = this.dungeonY - 1; y < this.dungeonY + 22; ++y)
            {
                for (int z = this.dungeonZ - 5; z < this.dungeonZ + 25; ++z)
                {
                    final int id = this.level.getTileId(x, y, z);
                    if (id == AetherBlocks.LOCKED_DUNGEON_STONE.id)
                    {
                        this.level.setTileWithMetadata(x, y, z, AetherBlocks.DUNGEON_STONE.id, this.level.getTileMeta(x, y, z));
                    }
                    if (id == AetherBlocks.TRAPPED_DUNGEON_STONE.id)
                    {
                        this.level.setTileWithMetadata(x, y, z, AetherBlocks.DUNGEON_STONE.id, this.level.getTileMeta(x, y, z));
                    }
                    if (id == AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id)
                    {
                        this.level.setTileWithMetadata(x, y, z, AetherBlocks.LIGHT_DUNGEON_STONE.id, this.level.getTileMeta(x, y, z));
                    }
                }
            }
        }
    }

    public void setDungeon(final int i, final int j, final int k)
    {
        this.hasDungeon = true;
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
        return this.bossName + ", the Valkyrie Queen";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("entity_valkyrie");
    }
}
