package net.rk.longroads.block.custom;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class SidewalkLayer extends LayeredBlock{
    public SidewalkLayer(Properties p) {
        super(p.instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.TUFF).mapColor(MapColor.COLOR_GRAY)
                .requiresCorrectToolForDrops().strength(1f));
    }
}
