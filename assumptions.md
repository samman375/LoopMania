## ASSUMPTIONS:

### World:
-   Board size: 8 wide, 17 long.

### Experience Points  
-   Killing 1 Slug: 100 EXP
   -   Killing 1 Zombie: 300 EXP
   -   Killing 1 Vampire: 800 EXP
   -   Discard item/card (when you have too many): 100 EXP per discard.
   

### Gold
-   Killing 1 Slug: 10g
    
-   Killing 1 Zombie: 20g
    
-   Killing 1 Vampire: 50g
    
-   Picked up off ground randomly: 20g
    
-   Items lost due to being replaced: Half the sell price of the item
-   Card lost due to being replaced: 20 Gold
    

### Random Behaviour:

-   Slugs spawns randomly on path tiles
    
-   Critical bite from zombie happens randomly with a 15% chance
    

	-   Against an allied soldier, causes allied soldier to turn into zombie forever
    

-   Critical bite from vampire happens randomly with a 40% chance
	-   Causes random additional damage (between 1-10) with every vampire attack (for a random number of vampire attacks (between 1-5))
    -   Shield makes vampire attacks 60% less likely
   -   Gold spawns randomly on path tiles
   -   Staff has a 40% chance of inflicting a trance effect on enemy
-   Receive random items after battle
	-   5% chance to receive Rare Item after battle
	-   Slug drops 1 item, Zombie drops 2, Vampire drops 3
- All enemy move randomly along the path (50% clockwise or anticlockwise)
    

### Buildings:

-   The spawning radius of vampires and zombies is 2 tiles
    
-   The battle radius of the tower is 3 tiles
    
-   Character regains 50HP when passing through a village
    
-   The battle/effect radius of the campfire is 2 tiles
    
-   Player cannot purchase items from Hero’s Castle if their inventory is full
    
-   Maximum of two allied soldiers in battle, can have more in inventory
    
-   8 maximum number of cards in inventory
    

## Battle:

-   Order in which enemies and characters/allied soldiers fight:
    
	  	Character → Tower 1 → Allied Soldier 1 → Enemy 1 → Character → Allied Soldier 2 → Tower 2…
        (loops back to start of allies, towers, or enemies when end of list reached)
-  Enemies prioritise allied soldiers
-  Attacks will target the entity with the lowest current health every turn
    
-   ~~Every turn: 80% chance to attack enemy with the lowest current health, 20% chance to attack a random enemy~~
    
-   ~~Enemy attack order based on movement speed of enemy~~
    
-  Tranced enemy stays tranced for 3 cycles of battle

-   If there are multiple campfires in battle radius - Character only deals double damage (not accumulative)
    
-   When zombie bites allied soldier, zombied allied soldier retains its current HP
-  Campfire damage bonus does not stack with other campfires (max: *2)
-   Vampires change direction just before entering battle range of campfire.
-   Enemies under trance have the same damage and missing health that they had before becoming tranced
    
-   Scalar damage reductions is applied before non-scalar damage reductions

	  - For example, on any turn: Sum up scalar (percentage) defence stats, this will be redacted from the enemy’s attack damage first
    
	-   Then (non-scalar) damage reduction is applied, resulting in the final enemy attack damage.

-  Damage is rounded up to nearest integer before being applied
    

-   Health potion used by moving from unequipped inventory to equipped inventory, consuming item

-  Equipped weapon is ignored if Anduril Flame of the West is equipped as only one weapon can be used during battle.
    
### Battle and support radius:
    

-   Slug: 1 tile battle, 1 tile
    
-   Zombie: 2 tile, 2 tile
    
-   Vampire: 2 tile, 3 tile
    

### Damage:
    

-   Slugs: 7 DMG
    
-   Zombie: 14 DMG
    
-   Vampire: 25 DMG
    
-   Character: 6 Base DMG
    
-   Allied soldier: 6 DMG
    
-   Tower: 3 DMG per turn
    
-   Trap: 5 DMG to enemy as they walk by
    

###  Health:
- Health can only be an integer value, rounded up to the nearest value. 

-   Character Health 0-100 HP

-  AlliedSoldier: 50 hp
    
-   Enemy stats (HP)

-   Slug: 10 HP
    
-   Zombie: 20 HP
    
-   Vampire: 50 HP

    ---

###  Movement Speed (4 ticks = 1 second)
    
-   Slug: 1 tile per 3 ticks
    
-   Zombie: 1 tile per 4 ticks
    
-   Vampire: 1 tile per tick
    
-   Character: 1 tile per 2 ticks
    

## Equipment:

-   Equipment dropped is picked up automatically and put into the unequipped inventory when the character steps on the square the equipment is occupying.
    

-   If inventory is full then the oldest item by time in inventory is discarded and player is awarded half its sell price in gold and 100XP
    

### Equipment stats:
    

   **Sword:**
    

-   +10 damage dealt to enemy
    
-   Buy price: 40g
    
-   Sell price: 20g
    
**Stake**

-   +5 damage dealt to enemy
    
-   increased to +25 versus vampires
    
-   Buy price: 100g
    
-   Sell price: 20g
    

**Staff:**
    

-   +3 damage dealt to enemy
    
-   40% chance of trance for 3 turns
    
-   Buy price: 50g
    
-   Sell price: 20g
    

**Shield:**
    

-   -5 damage from enemy attacks
    
-   Buy price: 50g
    
-   Sell price: 20g
    

**Helmet:**
    

-   -5 damage dealt to Enemy
    
-   -20% damage from enemy attacks
    
-   Buy price: 50g
    
-   Sell price: 20g
    

**Body Armour:**
    

-   Buy Price: 150g
    
-   Sell price: 20g
    

**Health Potion:**
    

-   Buy rice: 20g
    
-   Sell price: 10g
    

**The One Ring:**
    
-   Sell price: 100g
    
**Miscellaneous**
    
-   Health potion can be used when full health with no health gain
    
-   The One Ring has no expiration time and lasts until used
    
-   Character equipment: only one of each item type can be used at once
    

-   One weapon slot: Sword, Stake, Staff
    
-   One shield slot
    
-   One helmet slot
    
-   One body armour slot
    
-   One rare item slot
  

-   Duplicate items can occur in unequipped inventory
    
-   Rare Items cannot be purchased
    
-   Unequipped item capacity is 6
    

### Game Mode:

-   Assume that players can purchase health potions and protective gears in the Standard Mode with no specific rules
    
-   Assume that players are able to pause the game during the middle of a battle
