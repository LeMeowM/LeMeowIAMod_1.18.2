package net.lemeow.iamod;

import net.fabricmc.api.ModInitializer;
import net.lemeow.iamod.block.ModBlocks;
import net.lemeow.iamod.block.entity.ModBlockEntities;
import net.lemeow.iamod.item.ModFoodComponents;
import net.lemeow.iamod.item.ModItems;
import net.lemeow.iamod.recipe.ModRecipes;
import net.lemeow.iamod.screen.ModScreenHandlers;
import net.lemeow.iamod.util.ModLootTableModifiers;
import net.lemeow.iamod.util.ModRegistries;
import net.lemeow.iamod.world.gen.ModOreGeneration;
import net.lemeow.iamod.world.gen.ModWorldGen;
import net.lemeow.iamod.world.generator.EndGeneratorOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IAMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "iamod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModBlocks.registerMudBlocks();
		ModRecipes.registerRecipes();
		ModFoodComponents.registerModFoodComponents();

		ModRegistries.registerModStuff();
		ModScreenHandlers.registerAllScreenHandlers();

		ModOreGeneration.registerConfiguredFeatures();

		ModBlockEntities.registerAllBlockEntities();


		ModWorldGen.generateModWorldGen();
		EndGeneratorOptions.init();
		ModLootTableModifiers.modifyLootTables();
	}
}
