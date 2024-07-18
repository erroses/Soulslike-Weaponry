package net.soulsweaponry.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.soulsweaponry.datagen.loot_tables.BossLootTableProvider;
import net.soulsweaponry.datagen.recipe.WeaponRecipeProvider;
import net.soulsweaponry.datagen.worldgen.ModWorldGenerator;
import net.soulsweaponry.world.feature.ConfiguredFeatures;
import net.soulsweaponry.world.feature.PlacedFeatures;

public class DataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        //fabricDataGenerator.createPack().addProvider(ModelProvider::new);
        pack.addProvider(BossLootTableProvider::new);
        pack.addProvider(WeaponRecipeProvider::new);
        pack.addProvider(ModWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatures::init);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatures::init);
    }
}
