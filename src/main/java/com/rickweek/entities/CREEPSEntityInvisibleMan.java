package com.rickweek.entities;

import java.util.List;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityInvisibleMan extends EntityMob
{
    private static final ItemStack defaultHeldItem;
    private static final Item dropItems[];
    protected double attackRange;
    private int angerLevel;
    private int randomSoundDelay;
    public float modelsize;
    public String texture;

    public CREEPSEntityInvisibleMan(World world)
    {
        super(world);
        texture = "mcw:textures/entity/invisibleman.png";
        angerLevel = 0;
        modelsize = 1.0F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new CREEPSEntityInvisibleMan.AIAttackEntity());
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        if(angerLevel > 0)
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
    
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000517232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	Entity entity = source.getEntity();
        if ((getAttackTarget() instanceof EntityPlayer) && angerLevel == 0)
        {
            texture = "mcw:textures/entity/invisiblemanmad.png";
            angerLevel = rand.nextInt(15) + 5;
        }
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getAttackTarget() != null ? 0.75D : 0.5D);

        if (rand.nextInt(30) == 0 && angerLevel > 0)
        {
            angerLevel--;

            if (angerLevel == 0)
            {
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_INVISIBLEMAN_FORGETIT, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                this.setAttackTarget(null);
                texture = "mcw:textures/entity/invisibleman.png";
            }
        }
		return super.attackEntityFrom(source, amount);
    }

    class AIAttackEntity extends EntityAIBase
    {
		@Override
		public boolean shouldExecute()
		{
			return CREEPSEntityInvisibleMan.this.findPlayerToAttack();
		}
		
		/*
		public void updateTask()
		{
			float f = CREEPSEntityInvisibleMan.this.getDistanceToEntity(getAttackTarget());
			if(f < 256F)
			{
				attackEntity(CREEPSEntityInvisibleMan.this.getAttackTarget(), f);
				CREEPSEntityInvisibleMan.this.getLookHelper().setLookPositionWithEntity(CREEPSEntityInvisibleMan.this.getAttackTarget(), 10.0F, 10.0F);
				CREEPSEntityInvisibleMan.this.getNavigator().clearPathEntity();
				CREEPSEntityInvisibleMan.this.getMoveHelper().setMoveTo(CREEPSEntityInvisibleMan.this.getAttackTarget().posX, CREEPSEntityInvisibleMan.this.getAttackTarget().posY, CREEPSEntityInvisibleMan.this.getAttackTarget().posZ, 0.5D);
			}
			if(f < 1F)
			{
				CREEPSEntityInvisibleMan.this.attackEntityAsMob(CREEPSEntityInvisibleMan.this.getAttackTarget());
			}
		}
		*/
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)angerLevel);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        angerLevel = nbttagcompound.getShort("Anger");
        modelsize = nbttagcompound.getFloat("ModelSize");
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected boolean findPlayerToAttack()
    {
        if (angerLevel == 0)
        {
            return false;
        }
        else
        {
            texture = "mcw:textures/entity/invisiblemanmad.png";
            return true;
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.2D * 0.800000011920929D + motionX * 0.20000000298023224D;
            motionZ = (d1 / (double)f1) * 0.2D * 0.800000011920929D + motionZ * 0.20000000298023224D;
            motionY = 0.20000000596246448D;
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    public boolean canAttackEntity22(Entity entity, float i)
    {
        if (entity instanceof EntityPlayer)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(25D, 25D, 25D));

            for (int j = 0; j < list.size(); j++)
            {
                Entity entity1 = (Entity)list.get(j);

                if (entity1 instanceof CREEPSEntityInvisibleMan)
                {
                    CREEPSEntityInvisibleMan creepsentityinvisibleman = (CREEPSEntityInvisibleMan)entity1;
                    creepsentityinvisibleman.becomeAngryAt(entity);
                }
            }

            becomeAngryAt(entity);
        }

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    private void becomeAngryAt(Entity entity)
    {
    	setRevengeTarget((EntityLivingBase) entity);
        angerLevel += rand.nextInt(40);
        texture = "mcw:textures/entity/invisiblemanmad.png";
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (angerLevel == 0)
        {
            return MCSoundEvents.ENTITY_INVISIBLEMAN;
        }
        else
        {
            return MCSoundEvents.ENTITY_INVISIBLEMAN_ANGRY;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_INVISIBLEMAN_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_INVISIBLEMAN_DEATH;
    }

    /**
     * Returns the item that this EntityLiving is holding, if any.
     */
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
        return dropItems[rand.nextInt(dropItems.length)];
    }

    static
    {
        defaultHeldItem = new ItemStack(Items.STICK, 1);
        dropItems = (new Item[]
                {
                    Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.APPLE, Items.BREAD, Items.BREAD, Items.CAKE, Items.STICK, Items.CAKE,
                    Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK,
                    Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.GOLD_INGOT, Items.STICK, Items.STICK, Items.STICK, Items.APPLE, Items.APPLE,
                    Items.STICK
                });
    }
}