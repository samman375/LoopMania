# Meeting Minutes

  

## Meeting #1: 25th June

  
### **Attendees:**  
[Leslie, Kylee, Sam, Jannet, Dan]

### **Discussion Points**

-   UML diagram    
-   Drew up rough draft on UML diagram together based on Spec and starter code    
-   Requirements Analysis
-   Decided on key epic stories and underlying user stories    
-   Split them up to work on a few each including acceptance criteria
    

### **Action Items**

-   Requirements Analysis:
	-   View: Jan    
	-   Building: Jan    
	-   Purchase: Dan    
	-   Obtaining items: Leslie    
	-   Items: Sam    
	-   Game State: Kylee    
	-   Assumptions based on requirements analysis - all    

## Meeting #2: 29th June

### **Attendees** 
Leslie, Kylee, Sam, Jannet

### **Discussion Points**

-   Lo-fi Interface design    
	-   Shared draw.io diagram    
	-   Discussed different interfaces to show
-   Assumptions
	-  	 Decide on how to answer assumption questions
-  Notes from  Tutorial Check in with Harrison:
	-   Decide on specific values (health of character/enemies, points of damage with different items, crit damage)
	-   Include specific values in both user stories and assumptions
	-   Defined as integer within range (e.g. damage from 50-60, 100% health, radius of towers)
   -   Answered some assumptions together and thought of new ones
-   Added new epic and user stories

### **Action Items**  by next meeting: 10pm Thursday 1st

-   Finish Low Fidelity Interface Design:
	-   Normal world map: Leslie
	-   Inputs: All
	-   Drink potion, equip items, etc.
	-   Battle: Jannet
	-   Hero’s Castle menu screen: Sam
	-   Start menu: Kylee
	-   Pause Game, Win Screen, Quit Screen & general touchups: Dan

-   Finish Epics and user stories:
	-   Add more epics and user stories to complete spec: Leslie
	-   Add stats from assumptions to epics: All
    

**Plan for UML:**
    
-   Everyone think of patterns to use (strategy, observer, state, etc)
-   Think of other classes required for patterns and relationships they will have to each other

**Finish Assumptions:**    
-   Add more assumptions from new epics and user stories: Leslie    
-   Everyone finish off answering your own assumptions    
-   Add assumptions from the user stories you wrote if you haven’t yet    
-   Continue with Timeline: Sam      

## Meeting #3: 1st July

### Attendees 
[Leslie, Kylee, Sam, Jannet, Dan]

### Discussion Points

-   Discussed assumptions
-   Specified undecided assumptions and stats
-   Discussed missing epics and user stories
-   Finished and finalised Lo-fi interface design
-   Finished and finalised UML diagram
-   Added similar behaviours
-   Cleaned up arrows
   

### Action Items by next meeting: 9:30am Friday 2nd
-   Add fixes to all Epics and User Stories    
-   Finalise timeline    
-   Add fixes to assumptions    

## Meeting #4: 2nd July

### Attendees 
[Leslie, Kylee, Sam, Jannet, Dan, Leslie]

 
### Discussion Points

-   Discussed any last uncertainties on assumptions / User Stories and added fixes    
-   Uploaded user stories to gitlab boards    
-   Updated assumptions.md
-   Updated frontend.pdf    
-   Uploaded timeline.planning    
-   Staged all files for Milestone 1 submission
### Action Items by next meeting: 10am Monday 5th July ( 7pm Illinois Sunday?)

-   ALL: Learn JavaFX    
-   Undecided: Setup starting code for JavaFX    
-   ALL: Familiarise with project starting code    
-   Discuss & Prepare questions about milestone 2 for tutorial


## Meeting #5: 5th July

### Attendees [Leslie, Kylee, Sam, Jannet]

### Completed from last meeting
- Submitted milestone 1

### Discussion Points
- Possible patterns to apply to UML + how

### Action Items before next meeting
- Add patterns, methods, fields to UML (Allocated by user stories from milestone 1)
	- Automatic Play, Cards/Buildings: Jan
	- Purchase/Selling Item, Battle: Dan
	- Obtaining items/gold/experience/cards: Leslie
	- Item objects/using items: Sam
	- Game State, Enemies: Kylee
- Read starter code explanations and understand → there’s an explanation linked in the spec
- Learn unit tests
	- Try write one each first (User Story allocations)
- Next meeting: Wednesday 10pm


## Meeting #6: 7th July

### Attendees [Daniel, Leslie, Kylee, Sam, Jannet]

### Completed from last meeting
- Parts of UML

### Discussion Points
- Patterns to apply for UML and possibilities

### Action Items before next meeting
- Finish patterns, methods, fields to UML - allocated by user stories from milestone 1
	- Automatic Play, Cards/Buildings: Jan
	- Purchase/Selling Item, Battle: Dan
	- Obtaining items/gold/experience/cards: Leslie
	- Item objects/using items: Sam
	- Game State, Enemies: Kylee
- Read starter code explanations and understand → there’s an explanation linked in the spec
- Learn how to write Junit tests
- Try write one each first (User Story allocations)
- Next meeting: Thursday 10am

## Meeting #7: 8th July

### Attendees [Daniel, Leslie, Kylee, Sam, Jannet]

### Completed from last meeting
- Finished UML

### Discussion Points
- Discussed how UML implements our user stories

### Action Items before next meeting
- Tests:
	- Unit Tests
		- Jan’s parts (thanks guys :))
			- Cards (leslie)
			- Buildings (kylee)
			- Automatic play (incl. battle?) (dan)
		- And your own Epic parts too
	- 1 integration test (sam)
- Run the starter code
- Next meeting: Sunday 10am


## Meeting #8: 11th July

### Attendees: Sam, Kylee, Leslie

###Completed from last meeting:
- Some of the tests are completed
### Discussion Points
- Questions about tests and integration tests
- Discussed Timeline for next week
- Discuss MVP functions and delegate tasks
### Action Items 
- Battle - Sam and Dan
- Game State - Kylie
- Automatic Play - Leslie
- Next meeting: Lab Tuesday 2pm

### Questions for Harrison
- What is the position array in PathPosition for?


## Meeting #9 13th July

### Attendees: Sam, Kylee, Leslie, Dan, Jannet

### Completed from last meeting:
- All unit tests
- some backend MVP

### Discussion Points
- Work to be done
- Any questions we had

### Action Items by Thursday 10pm
- FInish backend MVP
	- Battle - Sam and Dan
	- Game State - Kylie
	- Automatic Play - Leslie
- Add goals - composition pattern to UML - Kylie
- Integration test fix up - Sam
- Finish frontend MVP - Jan
	- Inventory unequipped
	- Win/loss screen
	- gold/exp/health stats
- Backend other stuff
	- Implement card/buildings - Jan
	- Items - sam
	- Enemies - Les

### Questions for Harrison
- What is the position array in PathPosition for?
- Frontend in UML?
- How detailed in integration testing?

### Next meeting: Thursday 10pm

## Meeting #10 15th July

### Attendees:Sam, Leslie, Dan, Jannet

### Completed from last meeting:
- Sam: Added getting into battle and other functions
- Leslie: Added stats for enemies and getters
- Dan: Still working on battle order 
	-  Can be multiple towers in battle
- Jannet: 
	- Frontend 
		- Updates Added win/lose screen
		- Need a reset game function in backend
		- Missing equipped inventory
	- Backend: Worked on buildings
- @Kylee What are the Maven stuff and the other imported packages 

### Action Items by Saturday 10am
- Merge test branches first
- File organisation with folders
- Check board for allocated issues
- AIM TO HAVE BACKEND COMPLETE - FRONTEND ATTEMPTED

### Next meeting: Saturday 10am

## Meeting #11 17th July

### Attendees:Sam, Leslie, Kylee, Jannet

### Completed from last meeting:
- Finished most of backend

### Discussion Points
- Went through frontend code together
- Asked clarifying questions for integration of our individual parts
- Housekeeping: 
	- Tick off issue board tasks as you go
	- Once you complete a section (unit tests have all passed) - merge branch into Milestone2/StartCode
	- Keep UML updated 

### Action Items by Saturday 10am
- Fix units tests - make sure methods in classes work
- Backend: finish backend code and pass unit tests
- Frontend
	- Understand front end 
	- Implement/modify events to occur each tick in LoopManiaWorldController - StartTimer method 
		- Jannet/Kylee - Win/lose Condition
		- Kylee - spawn potion, gold on path tile; obtain gold, items
		- Jannet - Spawn enemies, card, discard
		- Leslie - modify runTickMoves() for enemies
		- Sam - modify methods that are already called here
	- Add your images to code
- Try to do before next meeting
	- Link frontend and backend for the code you wrote

### Next meeting: Sunday 10am

## Meeting #11 17th July

### Attendees:Sam, Leslie, Kylee, Jannet

### Completed from last meeting:
- Finished most of backend

### Discussion Points
- Went through frontend code together
- Asked clarifying questions for integration of our individual parts
- Housekeeping: 
	- Tick off issue board tasks as you go
	- Once you complete a section (unit tests have all passed) - merge branch into Milestone2/StartCode
	- Keep UML updated 

### Action Items by Saturday 10am
- Fix units tests - make sure methods in classes work
- Backend: finish backend code and pass unit tests
- Frontend
	- Understand front end 
	- Implement/modify events to occur each tick in LoopManiaWorldController - StartTimer method 
		- Jannet/Kylee - Win/lose Condition
		- Kylee - spawn potion, gold on path tile; obtain gold, items
		- Jannet - Spawn enemies, card, discard
		- Leslie - modify runTickMoves() for enemies
		- Sam - modify methods that are already called here
	- Add your images to code
- Try to do before next meeting
	- Link frontend and backend for the code you wrote

### Next meeting: Sunday 10am

## Meeting #11 17th July

### Attendees:Sam, Leslie, Kylee, Jannet

### Completed from last meeting:
- Finished most of backend

### Discussion Points
- Went through frontend code together
- Asked clarifying questions for integration of our individual parts
- Housekeeping: 
	- Tick off issue board tasks as you go
	- Once you complete a section (unit tests have all passed) - merge branch into Milestone2/StartCode
	- Keep UML updated 

### Action Items by Sunday 10am
- Fix units tests - make sure methods in classes work
- Backend: finish backend code and pass unit tests
- Frontend
	- Understand front end 
	- Implement/modify events to occur each tick in LoopManiaWorldController - StartTimer method 
		- Jannet/Kylee - Win/lose Condition
		- Kylee - spawn potion, gold on path tile; obtain gold, items
		- Jannet - Spawn enemies, card, discard
		- Leslie - modify runTickMoves() for enemies
		- Sam - modify methods that are already called here
	- Add your images to code
- Try to do before next meeting
	- Link frontend and backend for the code you wrote

### Next meeting: Sunday 10am

## Meeting #12 18th July

### Attendees:Sam, Leslie, Kylee, Jannet

### Completed from last meeting:
- Jannet
	- Random reward strategy 
- Leslie
	- Frontend - health exp and gold
- Sam
	- Tests for loop mania world - add unequippedItem, equipItem
	- One ring revive test, health potion test
	- Losing a game in battle 
- Kylee
	- LoopManiaWorld - comment boxes to make neater
	- LoopManiaWorld - created method to check when two entities on the same path tile
	- LoopManiaWorld - Spawn enemies
	- HeroCastleBuilding - tested and added new functions (Jan check)

### Discussion Points
- House keeping
	- Keep frontend working
	- Update the chat when major update 
	- Keep UML updated, assumptions
	- Meeting minutes
	- Coverage

### Action Items by Sunday 10pm
- Sam
	- Fight method - using AttackStrategies
	- Testing battle 
	- Include itemAttackstrategies as well 
	- Implement remove equipped item
	- Pass in rare item - read from JSON file in loader class, then pass
- Kylee
	- Add tests for spawning enemies
	- Test - method for checking two entities on the same path tile
	- Frontend equipped and unequipped item
- Leslie
	- Frontend Adding images 
	- Spawn gold and potions on path - add tests as well
	- Connect gameMode to backend
	- Fix enemies unit test
- Jannet
	- Add createClass to RewardStrategy
	- Menu switcher
	- Win and lose condition - Frontend exit 
	- Card into buildings
	- Fix buildings unit test

### Next meeting: Sunday 10pm

## Meeting #13 20th July

### Attendees: Sam, Leslie, Kylee, Jannet, Dan

### Completed from last meeting:
- Submitted Milestone 2

### Discussion Points
- Overall spec for milestone 3
- Timeline for milestone 3

### Action Items by Saturday 10am
- Things to complete from milestone 2:
	- Complete testing
	- Fix existing tests - everyone
		- Add new ones - Dan
	- Reupload UML to master
	- Backend
		- battleRewards - perhaps separate Item and Card rewards so that we can onload them in the frontend - Sam
	- Frontend
		- Spawn enemies (non-slugs) - Jannet
		- Despawn items after picked up - Kylee
		- Place buildings properly - Jannet
		- Equipped -> Unequipped Items - Sam
		- Equip items in correct slot (possibly fixed/related with battle rewards)
		- Change castle menu screen to popup window with inventory in view - Leslie
		- Buy items, sell items
### Next meeting: Saturday 10am




## Meeting 14: 24th July

### Attendees: Sam, Leslie, Kylee, Jannet, Dan

### Completed from last meeting:
- Fixed up bugs from milestone 2

### Discussion Points:
- Split up work for the given milestone 3 parts
- Any remaining queries/bugs about milestone 2

### Action Items by Tuesday 2pm:
- User Stories, Tests and UML
- New bosses - dan
	- Doggie
	- Elan muske
- Doggiecoin - Lez
- New rare items - Sam
	- Anduril
	- Tree stump
- New ‘confusing mode’ - jan
- New Killing all bosses goal - Kylee

### Next Meeting: 27th July 10am


## Meeting 15: 27th July

### Attendees: Sam, Leslie, Kylee, Jannet, Dan

### Completed from last meeting:
- UML, tests, user stories for milestone 3

### Discussion Points:
- Extension ideas to be approved

### Action Items by Friday 10am:
- Implementing given milestone 3
	- New bosses - dan
		- Doggie
		- Elan muske
		- Doggiecoin - Lez
	- New rare items - Sam
		- Anduril
		- Tree stump
- New ‘confusing mode’ - jan
- New Killing all bosses goal - Kylee

### Next meeting: 30 July 10am


## Meeting 16: 30 July

### Attendees: Sam, Kylee, Dan, Leslie

### Completed from last meeting:
- Most Milestone 3

### Discussion Points:
- Split work for approved extensions
- Work done from last meeting

### Action Items:
- Fixing up given milestone 3 with bosses
	- New bosses - dan
		- Doggie
		- Elan muske
		- Doggiecoin - Lez
	- New rare items - Sam
		- Anduril
		- Tree stump
- New ‘confusing mode’ - jan
- New Killing all bosses goal - Kylee
