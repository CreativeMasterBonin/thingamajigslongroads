package net.rk.longroads.block.custom;

import net.minecraft.world.level.block.SoundType;

public class MarkedAsphaltSlab extends RotatingSlab{
    public MarkedAsphaltSlab(Properties p) {
        super(p.sound(SoundType.TUFF).strength(2F).requiresCorrectToolForDrops());
    }
}
