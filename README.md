# LeMeowIAMod_1.18.2

![Welcome!](src/main/resources/assets/iamod/icon.PNG)



As much as the name of this project may suggest, this project is less to do with AI and more to add features that me and 
my friends wanted that seemed to be in line with the "vanilla experience". This includes copper as a usable material,
void quartz as a new endgame material, more passive mobs, as well as maybe a new boss following the Ender Dragon?


### Adding Additional Tools:
- Copper Tools & Armor & Nuggets
- Void Quartz Tools, Armor and World Generation

### New Features in World Generation:
- Huge Mushrooms
- Void Quartz Ore 

### Adding New Mobs:
- Capybara
- Owls
- Rats

<details><summary><h2>List of Items Added (and their tags):</h2></summary>

| Item Name                     |                Item ID                |     Why is it not working?      |
|:------------------------------|:-------------------------------------:|:-------------------------------:|
| Copper Axe                    |          `iamod.copper_axe`           |                                 |
| Copper Boots                  |         `iamod.copper_boots`          |                                 |
| Copper Chestplate             |       `iamod.copper_chestplate`       |                                 |
| Copper Helmet                 |         `iamod.copper_helmet`         |                                 | 
| Copper Hoe                    |          `iamod.copper_hoe`           |                                 |
| Copper Horse Armor            |      `iamod.copper_horse_armor`       |                                 |
| Copper Leggings               |        `iamod.copper_leggings`        |                                 |
| Copper Nugget                 |         `iamod.copper_nugget`         |                                 |
| Copper Pickaxe                |        `iamod.copper_pickaxe`         |                                 |
| Copper Shovel                 |         `iamod.copper_shovel`         |                                 |
| Copper Sword                  |         `iamod.copper_sword`          |                                 |
| Void Quartz Block             |       `iamod.void_quartz_block`       |      Behavior needs fixing      |
| Void Quartz Ingot             |       `iamod.void_quartz_ingot`       |                                 |
| Void Quartz Ore               |        `iamod.void_quartz_ore`        |   Behavior needs fine tuning    |
| Void Quartz Shard             |       `iamod.void_quartz_shard`       |                                 |
| Void Quartz Axe               |        `iamod.void_quartz_axe`        |                                 |
| Void Quartz Boots             |       `iamod.void_quartz_boots`       |                                 |
| Void Quartz Chestplate        |    `iamod.void_quartz_chestplate`     |                                 |
| Winged Void Quartz Chestplate | `iamod.void_quartz_chestplate_winged` |                                 |
| Void Quartz Helmet            |      `iamod.void_quartz_helmet`       |                                 |
| Void Quartz Hoe               |        `iamod.void_quartz_hoe`        |                                 |
| Void Quartz Leggings          |     `iamod.void_quartz_leggings`      |                                 |
| Void Quartz Pickaxe           |      `iamod.void_quartz_pickaxe`      |                                 |
| Void Quartz Shovel            |      `iamod.void_quartz_shovel`       |                                 |
| Void Quartz Sword             |       `iamod.void_quartz_sword`       |                                 |
| Infusion Table                |        `iamod.infusion_table`         |    Missing Texture Behavior     |
| Goat Cheese                   |          `iamod.goat_cheese`          |  missing texture and behavior   |
| Sheep Cheese                  |         `iamod.sheep_cheese`          |  missing texture and behavior   |
| Goat Milk Bucket              |       `iamod.goat_milk_bucket`        | wrong texture, missing behavior |
| Sheep Milk Bucket             |       `iamod.sheep_milk_bucket`       | wrong texture, missing behavior |
|                               |                                       |                                 |
|                               |                                       |                                 |





</details>

## MIXINS

- MixinLightningEntity -> lightning creates glass in sand
- MixinSheepEntity -> sheep can be milked
- MixinGoatEntity -> goats can be milked
- EmptyBottleMixin -> you can now use empty bottles as a temporary air storage to stay underwater for longer
- PotionMixin -> you can no longer drink potions underwater
- PlatedElytraRendererMixin -> adds elytra wings to players wearing plated elytras.

## MISSING CODE
- [X] copper horse armor
  - [X] textures
  - [X] add to minecraft loot table
- [X] copper nugget textures
- [ ] capybara
  - [ ] animation
  - [ ] ai
  - [ ] models
- [ ] owl
  - [ ] animation
  - [ ] models
  - [ ] ai
- [ ] rats
  - [ ] animation
  - [ ] models
  - [ ] ai
- [X] Lightning turning sand into glass
- [ ] void quartz
  - [X] ores -> spawn in the world
  - [X] shards
  - [X] ingots
  - [X] blocks
  - [X] infusion table -> works with comparators
  - [ ] tools
    - [ ] axe
    - [ ] hoe
    - [ ] pickaxe
    - [ ] shovel
    - [ ] sword
  - [ ] armor
    - [X] helmet
    - [X] chestplate 
    - [ ] (elytra?)
    - [X] leggings
    - [X] boots
- [ ] maybe end biomes??

## MISSING TEXTURES
- [X] copper horse armor
  - [X] textures
- [X] copper nugget textures
- [ ] capybara
  - [ ] animation
  - [ ] models
- [ ] owl
  - [ ] animation
  - [ ] models
- [ ] rats
  - [ ] animation
  - [ ] models
- [ ] void quartz
  - [ ] ores
  - [X] shards
  - [ ] ingots
  - [X] blocks
  - [ ] crafting bench
  - [X] tools
    - [X] axe
    - [X] hoe
    - [X] pickaxe
    - [X] shovel
    - [X] sword
  - [ ] armor
    - [X] helmet
    - [ ] chestplate (elytra?)
    - [X] leggings
    - [X] boots

### ALL CREDIT FOR ART *EXCEPT THE COVER AND INFUSION TABLE* GOES TO [SAMANTHA STINSON](https://instagram.com/hellspawn_exhibit?igshid=YmMyMTA2M2Y=)
### SPECIAL THANKS TO HARRISON HOLLAND FOR HELPING BALANCING AND DESIGNING THE VOID QUARTZ


