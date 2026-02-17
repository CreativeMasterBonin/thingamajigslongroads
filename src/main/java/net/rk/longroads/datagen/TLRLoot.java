package net.rk.longroads.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.item.TLRItems;

import java.util.ArrayList;
import java.util.List;

public class TLRLoot extends VanillaBlockLoot{
    public TLRLoot(HolderLookup.Provider p){super(p);}

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.dropSelf(TLRBlocks.ROAD_SIGN.get());
        this.dropSelf(TLRBlocks.STRAIGHT_ROAD_SIGN.get());
        this.dropSelf(TLRBlocks.VERTICAL_REDSTONE_ROAD_SIGN.get());
        this.dropSelf(TLRBlocks.THREE_WAY_ROAD_SIGN.get());

        this.dropSelf(TLRBlocks.ASPHALT.get());
        this.dropSelf(TLRBlocks.ASPHALT_OK.get());
        this.dropSelf(TLRBlocks.ASPHALT_MEDIOCRE.get());
        this.dropSelf(TLRBlocks.ASPHALT_OLD.get());
        this.dropSelf(TLRBlocks.SIDEWALK.get());
        this.dropSelf(TLRBlocks.SIDEWALK_SECTIONED.get());
        this.dropSelf(TLRBlocks.SIDEWALK_BLOCKED.get());
        this.dropSelf(TLRBlocks.SIDEWALK_CRACKED.get());

        this.dropSelf(TLRBlocks.DOUBLE_WHITE_ASPHALT.get());
        this.dropSelf(TLRBlocks.DOUBLE_YELLOW_ASPHALT.get());
        this.dropSelf(TLRBlocks.DOUBLE_CORNER_WHITE_ASPHALT.get());
        this.dropSelf(TLRBlocks.DOUBLE_CORNER_YELLOW_ASPHALT.get());
        this.dropSelf(TLRBlocks.WHITE_PARKING_ASPHALT.get());
        this.dropSelf(TLRBlocks.YELLOW_PARKING_ASPHALT.get());
        this.dropSelf(TLRBlocks.BLUE_PARKING_ASPHALT.get());

        this.dropOther(TLRBlocks.GREEN_ROADWAY_STANDING_SIGN.get(),TLRItems.GREEN_ROADWAY_SIGN_ITEM.asItem());
        this.dropOther(TLRBlocks.GREEN_ROADWAY_WALL_SIGN.get(),TLRItems.GREEN_ROADWAY_SIGN_ITEM.asItem());

        this.dropOther(TLRBlocks.RED_ROADWAY_STANDING_SIGN.get(),TLRItems.RED_ROADWAY_SIGN_ITEM.asItem());
        this.dropOther(TLRBlocks.RED_ROADWAY_WALL_SIGN.get(),TLRItems.RED_ROADWAY_SIGN_ITEM.asItem());

        this.dropOther(TLRBlocks.BLUE_ROADWAY_STANDING_SIGN.get(),TLRItems.BLUE_ROADWAY_SIGN_ITEM.asItem());
        this.dropOther(TLRBlocks.BLUE_ROADWAY_WALL_SIGN.get(),TLRItems.BLUE_ROADWAY_SIGN_ITEM.asItem());

        this.dropOther(TLRBlocks.BROWN_ROADWAY_STANDING_SIGN.get(),TLRItems.BROWN_ROADWAY_SIGN_ITEM.asItem());
        this.dropOther(TLRBlocks.BROWN_ROADWAY_WALL_SIGN.get(),TLRItems.BROWN_ROADWAY_SIGN_ITEM.asItem());

        this.dropOther(TLRBlocks.GREEN_HANGING_SIGN.get(),TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.asItem());
        this.dropOther(TLRBlocks.GREEN_WALL_HANGING_SIGN.get(),TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.asItem());

        this.dropSelf(TLRBlocks.VERTICAL_REDSTONE_SIDEWALK.get());

        this.add(TLRBlocks.ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.ASPHALT_OK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.ASPHALT_OK_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.ASPHALT_OK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.ASPHALT_OK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.ASPHALT_OLD_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.ASPHALT_OLD_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.ASPHALT_OLD_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.ASPHALT_OLD_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        //sidewalk
        this.add(TLRBlocks.SIDEWALK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.SIDEWALK_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.SIDEWALK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.SIDEWALK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.CRACKED_SIDEWALK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.CRACKED_SIDEWALK_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.CRACKED_SIDEWALK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.CRACKED_SIDEWALK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(TLRBlocks.SECTIONED_SIDEWALK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.SECTIONED_SIDEWALK_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.SECTIONED_SIDEWALK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.SECTIONED_SIDEWALK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.BLOCKED_SIDEWALK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.BLOCKED_SIDEWALK_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.BLOCKED_SIDEWALK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.BLOCKED_SIDEWALK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));




        //
        this.add(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(TLRBlocks.WHITE_DT_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_DT_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_DT_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_DT_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(TLRBlocks.YELLOW_D_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_D_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_D_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_D_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(TLRBlocks.WHITE_D_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_D_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_D_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_D_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));



        this.add(TLRBlocks.WHITE_ROAD_MARKING.get(),noDrop());
        this.add(TLRBlocks.YELLOW_ROAD_MARKING.get(),noDrop());
        this.add(TLRBlocks.BLUE_ROAD_MARKING.get(),noDrop());
        this.add(TLRBlocks.PURPLE_ROAD_MARKING.get(),noDrop());
        this.add(TLRBlocks.MULTICOLORED_ROAD_MARKING.get(),noDrop());
        this.add(TLRBlocks.ASPHALT_LAYER.get(),noDrop());
        this.add(TLRBlocks.OK_ASPHALT_LAYER.get(),noDrop());
        this.add(TLRBlocks.MEDIOCRE_ASPHALT_LAYER.get(),noDrop());
        this.add(TLRBlocks.OLD_ASPHALT_LAYER.get(),noDrop());
        this.add(TLRBlocks.SIDEWALK_LAYER.get(),noDrop());
        this.add(TLRBlocks.SIDEWALK_LAYER_LEFT.get(),noDrop());
        this.add(TLRBlocks.SIDEWALK_LAYER_RIGHT.get(),noDrop());
        this.add(TLRBlocks.CRACKED_SIDEWALK_LAYER.get(),noDrop());
        this.add(TLRBlocks.CRACKED_SIDEWALK_LAYER_LEFT.get(),noDrop());
        this.add(TLRBlocks.CRACKED_SIDEWALK_LAYER_RIGHT.get(),noDrop());
        this.add(TLRBlocks.BLOCKED_SIDEWALK_LAYER.get(),noDrop());
        this.add(TLRBlocks.BLOCKED_SIDEWALK_LAYER_LEFT.get(),noDrop());
        this.add(TLRBlocks.BLOCKED_SIDEWALK_LAYER_RIGHT.get(),noDrop());
        this.add(TLRBlocks.SECTIONED_SIDEWALK_LAYER.get(),noDrop());
        this.add(TLRBlocks.SECTIONED_SIDEWALK_LAYER_LEFT.get(),noDrop());
        this.add(TLRBlocks.SECTIONED_SIDEWALK_LAYER_RIGHT.get(),noDrop());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> knownBlocks = new ArrayList<>();
        knownBlocks.addAll(TLRBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::get).toList());
        return knownBlocks;
    }
}
