package io.github.fallOut015.extra_arrows.entity.projectile;

import io.github.fallOut015.extra_arrows.entity.EntitiesExtraArrows;
import io.github.fallOut015.extra_arrows.item.ItemsExtraArrows;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class FireArrowEntity extends AbstractArrowEntity {
    public FireArrowEntity(EntityType<? extends FireArrowEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }
    public FireArrowEntity(World worldIn, double x, double y, double z) {
        super(EntitiesExtraArrows.FIRE_ARROW.get(), x, y, z, worldIn);
    }
    public FireArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntitiesExtraArrows.FIRE_ARROW.get(), shooter, worldIn);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemsExtraArrows.FIRE_ARROW.get());
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    public boolean isOnFire() {
        return true;
    }
    @Override
    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround) {
            this.level.addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }
}