package net.lemeow.iamod.block.custom.cheesemold;

import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class LeveledCheeseMoldBlock extends AbstractCheeseMoldBlock{

    public static final IntProperty LEVEL;

    public LeveledCheeseMoldBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(LEVEL, 1));
    }

    static{
        LEVEL = Properties.LEVEL_8;
    }

}
