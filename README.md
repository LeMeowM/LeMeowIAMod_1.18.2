# BetterAIMod_1.18.2

![Welcome!](src/main/resources/assets/aimod/icon.PNG)



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

| Item Name                     |                Item ID                |           Why is it not working?            |
|:------------------------------|:-------------------------------------:|:-------------------------------------------:|
| Copper Axe                    |          `aimod.copper_axe`           |                                             |
| Copper Boots                  |         `aimod.copper_boots`          |                                             |
| Copper Chestplate             |       `aimod.copper_chestplate`       |                                             |
| Copper Helmet                 |         `aimod.copper_helmet`         |                                             | 
| Copper Hoe                    |          `aimod.copper_hoe`           |                                             |
| Copper Horse Armor            |      `aimod.copper_horse_armor`       |                                             |
| Copper Leggings               |        `aimod.copper_leggings`        |                                             |
| Copper Nugget                 |         `aimod.copper_nugget`         |                                             |
| Copper Pickaxe                |        `aimod.copper_pickaxe`         |                                             |
| Copper Shovel                 |         `aimod.copper_shovel`         |                                             |
| Copper Sword                  |         `aimod.copper_sword`          |                                             |
| Void Quartz Block             |       `aimod.void_quartz_block`       |            Behavior needs fixing            |
| Void Quartz Ingot             |       `aimod.void_quartz_ingot`       |                                             |
| Void Quartz Ore               |        `aimod.void_quartz_ore`        |         Behavior needs fine tuning          |
| Void Quartz Shard             |       `aimod.void_quartz_shard`       |                                             |
| Void Quartz Axe               |        `aimod.void_quartz_axe`        |                                             |
| Void Quartz Boots             |       `aimod.void_quartz_boots`       |                                             |
| Void Quartz Chestplate        |    `aimod.void_quartz_chestplate`     |                                             |
| Winged Void Quartz Chestplate | `aimod.void_quartz_chestplate_winged` | Behavior Not Implemented & Missing Textures |
| Void Quartz Helmet            |      `aimod.void_quartz_helmet`       |                                             |
| Void Quartz Hoe               |        `aimod.void_quartz_hoe`        |                                             |
| Void Quartz Leggings          |     `aimod.void_quartz_leggings`      |               missing texture               |
| Void Quartz Pickaxe           |      `aimod.void_quartz_pickaxe`      |                                             |
| Void Quartz Shovel            |      `aimod.void_quartz_shovel`       |                                             |
| Void Quartz Sword             |       `aimod.void_quartz_sword`       |                                             |
| Infusion Table                |        `aimod.infusion_table`         | Missing Textures, Missing Texture Behavior  |
| Goat Cheese                   |          `aimod.goat_cheese`          |        missing texture and behavior         |
| Sheep Cheese                  |         `aimod.sheep_cheese`          |        missing texture and behavior         |
| Goat Milk Bucket              |       `aimod.goat_milk_bucket`        |       wrong texture, missing behavior       |
| Sheep Milk Bucket             |       `aimod.sheep_milk_bucket`       |       wrong texture, missing behavior       |
|                               |                                       |                                             |
|                               |                                       |                                             |





</details>

## MIXINS

- MixinLightningEntity -> lightning creates glass in sand
- MixinSheepEntity -> sheep can be milked
- MixinGoatEntity -> goats can be milked

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


