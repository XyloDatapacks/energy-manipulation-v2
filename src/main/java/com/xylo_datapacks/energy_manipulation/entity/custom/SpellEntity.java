package com.xylo_datapacks.energy_manipulation.entity.custom;

import com.xylo_datapacks.energy_manipulation.entity.ModEntities;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.SpellExecutor;
import com.xylo_datapacks.energy_manipulation.item.custom.spell_book.node.spell.SpellNode;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtTypes;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpellEntity extends PersistentProjectileEntity implements SpellExecutor {
    /**
     * The name of the compound tag that stores the marker's custom data.
     */
    private static final String DATA_KEY = "data";
    private NbtCompound data = new NbtCompound();
    
    private SpellNode spellNode;

    public SpellEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
    }

    public SpellEntity(World world, double x, double y, double z) {
        super(ModEntities.SPELL, x, y, z, world);
    }

    public SpellEntity(World world, LivingEntity owner) {
        super(ModEntities.SPELL, owner, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }


    /**
     * Reads custom data from {@code nbt}. Subclasses has to implement this.
     *
     * <p>NBT is a storage format; therefore, a data from NBT is loaded to an entity instance's
     * fields, which are used for other operations instead of the NBT. The data is written
     * back to NBT when saving the entity.
     *
     * <p>{@code nbt} might not have all expected keys, or might have a key whose value
     * does not meet the requirement (such as the type or the range). This method should
     * fall back to a reasonable default value instead of throwing an exception.
     *
     * @param nbt
     * @see #writeCustomDataToNbt
     */
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.data = nbt.getCompound(DATA_KEY);
    }
    
    /**
     * Writes custom data to {@code nbt}. Subclasses has to implement this.
     *
     * <p>NBT is a storage format; therefore, a data from NBT is loaded to an entity instance's
     * fields, which are used for other operations instead of the NBT. The data is written
     * back to NBT when saving the entity.
     *
     * @param nbt
     * @see #readCustomDataFromNbt
     */
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put(DATA_KEY, this.data.copy());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void runSpell() {
        spellNode.executeSpell(this);
    }

    public void setSpellNode(SpellNode spellNode) {
        this.spellNode = spellNode;
    }

    @Override
    public LivingEntity getCaster() {
        return (LivingEntity) getOwner();
    }

    @Override
    public Vec3d getContextPosition() {
        NbtList list = (NbtList) data.get("position");
        if (list != null && list.size() == 3) {
            return new Vec3d(list.getDouble(0), list.getDouble(1), list.getDouble(2));
        }
        return getPos();
    }

    @Override
    public Vec2f getContextDirection() {
        NbtList list = (NbtList) data.get("rotation");
        if (list != null && list.size() == 2) {
            return new Vec2f(list.getFloat(0), list.getFloat(1));
        }
        return new Vec2f(getYaw(), getPitch());
    }

    @Override
    public void setContextPosition(Vec3d position) {
        NbtList list = new NbtList();
        list.add(NbtDouble.of(position.x));
        list.add(NbtDouble.of(position.y));
        list.add(NbtDouble.of(position.z));
        data.put("position", list);
    }
    
}
