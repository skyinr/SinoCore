package games.moegirl.sinocraft.sinocore.api.block;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

/**
 * A block with {@link  BlockEntity}.
 * <p>Base on {@link  BaseEntityBlock}, use model render and impl getTicker method</p>
 * <p>If entity need update, impl {@link  BlockEntityTicker} on BlockEntity.</p>
 */
public abstract class AbstractEntityBlock extends BaseEntityBlock {

    public AbstractEntityBlock(Properties properties) {
        super(properties);
    }

    public AbstractEntityBlock() {
        this(BlockBehaviour.Properties.of(Material.METAL));
    }

    public AbstractEntityBlock(Material material, float strength) {
        super(BlockBehaviour.Properties.of(material).strength(strength));
    }

    @Override
    @SuppressWarnings("deprecation")
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType instanceof BlockEntityTicker t ? t : null;
    }
}
