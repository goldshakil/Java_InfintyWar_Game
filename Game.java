import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

//Ver 2.0 Official
// We can edit,add and delete below things:
// 1. Monster
// 2. Card
// 3. Item
// 4. Special Stage
// 5. card.Quiz
// 6. Monsters' AttackPattern
// 7. Way to score

public class Game {

	private Player player;
	private Player player2;
	Scanner scan = new Scanner(System.in);// used for inputting the numbers
	Random rand = new Random(); // used for generating any random numbers

	public Game(Player player) {
		this.player = player;
		this.player2 = new Player("empty");
	}

	public Game(Player player1, Player player2) {
		this.player = player1;
		this.player2 = player2;
	}

	public static void main(String[] args) {
		try {

			Boolean running = true;
			Scanner scan = new Scanner(System.in);

			System.out.println();
			System.out.println("\t#########################");
			System.out.println("\t#Welcome to Infinty War!#");
			System.out.println("\t#########################\n\n");

			System.out.println("----------------------------------------------");
			System.out.println("\t      [Menu]\n");
			System.out.println("\t [1]   Single Player");
			System.out.println("\t [2]   Multi Player");
			System.out.println("\t [3]   Score Board");
			System.out.println("----------------------------------------------");
			System.out.print("> > > :");

			int menu = scan.nextInt();
			for(int i = 2; !checkRange(menu, 1, 3); i--)
			{
				if(i == 0)
				{
					drawReaper();
					System.out.println("\n\n\tPlease input correct number. Try it again from starting.");
					scan.close();
					return;
				}

				System.out.printf("Wrong input! You have %d chance to input.\n", i);
				System.out.print("> > > :");
				menu = scan.nextInt();
			}

			if(menu == 3)
			{
				makeScoreBoardFile();
				ShowScoreBoard();
				System.out.println("----------------------------------------------");
				System.out.println("\t Menu\n");
				System.out.println("\t [1]   Single Player");
				System.out.println("\t [2]   Multi Player");
				System.out.println("\t [3]   Exit this game");
				System.out.println("----------------------------------------------");
				System.out.print("> > > :");
				menu = scan.nextInt();

				for(int i = 2; !checkRange(menu, 1, 3); i--)
				{
					if(i == 0)
					{
						System.out.println("Please input correct number. Try it again from starting.");
						scan.close();
						return;
					}

					System.out.printf("Wrong input! You have %d chance to input.\n", i);
					System.out.print("> > > :");
					menu = scan.nextInt();
				}
			}

			if(menu == 1)
			{
				String input = scan.nextLine();
				System.out.print("What's your nickname? ");
				input = scan.nextLine();

				System.out.println("\n\n\n\t Hello, " + input + "\n");

				//we need to add some stories of the game...

				Game game = new Game(new Player(input));

				makeScoreBoardFile();

				int stageNum = 0;
				while (running) {
					game.Stage(stageNum);
					stageNum++;
				}
				scan.close();
			}

			else if(menu == 2)
			{
				String name1 = scan.nextLine();
				System.out.print("What's player1's nickname? ");
				name1 = scan.nextLine();

				System.out.print("What's player2's nickname? ");
				String name2 = scan.nextLine();
				while(name2.equals("empty"))
				{
					System.out.print("What? Name cannot be empty..Please give lovely name, bro.");
					System.out.print("What's player2's nickname? ");
					name2 = scan.nextLine();
				}

				System.out.println("\n\n\n\t Hello, " + name1 + "  and  " + name2 + "!!!");

				//we need to add some stories of the game...
				Game game = new Game(new Player(name1),new Player(name2));

				makeScoreBoardFile();
				
				System.out.println("In 2 players Mode, the monster become stronger...");
				int stageNum = 0;
				while (running) {
					game.Stage(stageNum);
					stageNum++;
				}
				scan.close();
			}

			else if(menu == 3)
			{
				System.out.println("Thank you for visiting. Let's meet again next time.");
				scan.close();
				return;
			}
			//We will do it!
		}catch(InputMismatchException e) {
			System.out.println("You need to input in proper format!");
			System.out.println("Try again,please...");
			System.exit(0);
		}
	}

	// Randomly Generate Stage
	// Randomly choose two Stage,
	// then player can choose one of the two Stages
	// If adding boss stage, probability 50% Enemy, 10% Rest, 5% treasure, 25% unknown, 10% Boss
	// Else, probability 50% Enemy, 15% Rest, 10% treasure, 25% unknown
	// this will choose any stage randomly
	public void Stage(int stageNum) {

		try{
			if(stageNum != 0)
			{
				System.out.println("Your point increased 10*" + stageNum + " because of stageNum!");
				player.increasePoint(10*stageNum);
				if(!player2.getName().equals("empty")) player.increasePoint(10*stageNum);
			}
			
			int choice1 = 0;
			int choice2 = 0;
			int userChoice = 0;
			String[] Stages = { "Enemy", "Rest", "Treasure", "Unknown", "Boss" };

			do {
				if (player.getKillPoint() >= 3) // If player has killed monster at least three times, he or she has the chance to meet Boss!
				{
					choice1 = rand.nextInt(20);
					if (choice1 < 10)
						choice1 = 0;
					else if (choice1 < 12)
						choice1 = 1;
					else if (choice1 < 13)
						choice1 = 2;
					else if (choice1 < 18)
						choice1 = 3;
					else
						choice1 = 4;

					choice2 = rand.nextInt(20);
					if (choice2 < 10)
						choice2 = 0;
					else if (choice2 < 12)
						choice2 = 1;
					else if (choice2 < 13)
						choice2 = 2;
					else if (choice2 < 18)
						choice2 = 3;
					else
						choice2 = 4;
				} 

				else 
				{
					choice1 = rand.nextInt(20);
					if (choice1 < 10)
						choice1 = 0;
					else if (choice1 < 13)
						choice1 = 1;
					else if (choice1 < 15)
						choice1 = 2;
					else
						choice1 = 3;

					choice2 = rand.nextInt(20);
					if (choice2 < 10)
						choice2 = 0;
					else if (choice2 < 13)
						choice2 = 1;
					else if (choice2 < 15)
						choice2 = 2;
					else
						choice2 = 3;
				}
			} while (choice1 == choice2);


			System.out.println("\n\t\t # stage " + stageNum + " #\n");
			System.out.println(" > Choice your Path");
			System.out.println(" [1] " + Stages[choice1]);
			System.out.println(" [2] " + Stages[choice2]);
			System.out.print(">>> ");
			userChoice = scan.nextInt();
			System.out.println("\n");
			while (userChoice != 1 && userChoice != 2) {
				System.out.println("  Please input 1 or 2!");
				System.out.print(">>> ");
				userChoice = scan.nextInt();
			}

			// choice 1
			if (userChoice == 1) { // call the specified function
				if (Stages[choice1].equals("Enemy")) {
					EnemyStage(stageNum);
				} else if (Stages[choice1].equals("Rest")) {
					RestStage();
				}else if (Stages[choice1].equals("Treasure")) {
					TreasureStage();
				} else if (Stages[choice1].equals("Unknown")) {
					UnknownStage(stageNum);
				} else if (Stages[choice1].equals("Boss")) {
					BossStage();
				}
			}

			// choice 2
			else if (userChoice == 2) {// call the specified function
				if (Stages[choice2].equals("Enemy")) {
					EnemyStage(stageNum);
				} else if (Stages[choice2].equals("Rest")) {
					RestStage();
				}

				else if (Stages[choice2].equals("Treasure")) {
					TreasureStage();
				} else if (Stages[choice2].equals("Unknown")) {
					UnknownStage(stageNum);
				} else if (Stages[choice2].equals("Boss")) {
					BossStage();
				}
			}
		}catch(InputMismatchException e) {
			System.out.println("You need to input in proper format!");
			System.out.println("Try again,please...");
			System.exit(0);
		}

	}

	// Phase1. EnemyStage
	// > meet common Monster
	// > if player defeat monster
	// > monster randomly drop item

	
public int PlayerChoiceAction() {
		try {

			System.out.println("  \n> What would you like to do?");
			System.out.println("  [1] Use Cards");
			System.out.println("  [2] Use an Item");
			System.out.println("  [3] Turn End");
			System.out.print(">>> ");
			int input = scan.nextInt();

			while(!checkRange(input, 1, 3)) {
				System.out.println("Dormammu, I've come to bargain!\r\n" + 
						"If you want to quit, input correctly!");
				System.out.print(">>> ");
				input = scan.nextInt();
			}
			return input;

		}catch(InputMismatchException e) {
			System.out.println("You need to input in proper format!");
			System.out.println("Try again,please...");
			System.exit(0);
		}

		return 0;
	}


	public void EnemyStage(int StageNum) {
		try {
			System.out.println("In Enemy stage, you can get 50 points");
			
			player.increasePoint(50);
			if(!player2.getName().equals("empty")) 
				player2.increasePoint(50);

			player.DisCard();
			player2.DisCard();
			player.generateCards(5);
			player2.generateCards(5);
			
			String[] monsters = { "Skeleton", "Warrior", "Zombie", "Assassin" };
			Monster monster = new Monster(monsters[rand.nextInt(monsters.length)]); 
			monster.reflectStageNum(StageNum);
			//If we have time, we need to add more monsters and probability!
			// The stage_num bigger, monster level higher!

			System.out.println("\t # " + monster.getName() + " appeared! #");
			if(!player2.getName().equals("empty")) monster.Powerful();
			// game starts here
			while (monster.alive() && !player.dead() && !player2.dead()) {
				if(player2.getName().equals("empty"))
				{
					System.out.println("------------------------------------------------------------------------------------------");
					System.out.println("\n\t<Player Turn>\n");
					player.getStatus(); 	
				}

				else {
					System.out.println("------------------------------------------------------------------------------------------");
					player.getStatus(); player2.getStatus();
					System.out.println("\n\t<Player1 Turn>\n");
				}
				monster.getStatus();
				// Player's turn:
				int input = PlayerChoiceAction();
				PlayerTurn(monster, input); //Player1 turn
				if (!monster.alive())
				{
					player.kills(monster);
					break;
				}

				if(player.dead())
				{
					DeathEnd();
					return;  //Later, we will make code to challenge again!
				}

				if(!player2.getName().equals("empty")) {
					System.out.println("------------------------------------------------------------------------------------------");
					player.getStatus(); player2.getStatus(); monster.getStatus();
					System.out.println("\n\t<Player2 Turn>\n");	
					input = PlayerChoiceAction();
					Player2Turn(monster, input); //Player2 turn

					if (!monster.alive())
					{
						player2.kills(monster);
						break;
					}

					if(player2.dead())
					{
						DeathEnd();
						return;  //Later, we will make code to challenge again!
					}
					player2.DisCard();
				}
				player.DisCard(); 
				if(!player2.getName().equals("empty")) player2.DisCard();
				// Monster's turn:
				System.out.println("\n\t<Enemy Turn>\n");
				monster.attacks(player);

				if (!monster.alive())
				{
					player.kills(monster);
					break;
				}

				if(player.dead())
				{
					DeathEnd();
					return;  //Later, we will make code to challenge again!
				}

				if(!player2.getName().equals("empty"))
				{	
					monster.attacks(player2);
					if (!monster.alive())
					{
						player2.kills(monster);
						break;
					}

					if(player2.dead())
					{
						DeathEnd();
						return;  //Later, we will make code to challenge again!
					}
				}
			}
			System.out.println("\n\n");
			if (monster.hasItem()) {
				if (player.itemIsFull()) {
					System.out.println("Oh, unfortunately, Item is full!");
					System.out.println("You have items below");
					for (int i = 0; i < player.getItemSize(); i++)
						player.showItem(player.itemsInHandName[i]);

					System.out.println("Choose the item you throw away.If you don't, input 0");
					System.out.print(">>> ");
					int trash = scan.nextInt();
					while(!checkRange(trash, 0, player.getItemSize()))
					{
						System.out.println("Invalid number! Input again.");
						System.out.print(">>> ");
						trash = scan.nextInt();
					}
					trash -= 1;
					if (trash != -1) {
						player.getItemAt(trash);
						for (int i = 0; i < player.getItemSize(); i++)
							player.showItem(player.itemsInHandName[i]);
					}

					else {
						System.out.println("Ok, we respect your choice!");
					}
				}

				else {
					player.getItem();
					System.out.println("\t["+player.getName()+"]'s Itemlist"); 
					for (int i = 0; i < player.getItemSize(); i++)
						player.showItem(player.itemsInHandName[i]);
				}

				if(!player2.getName().equals("empty"))
				{
					if (player2.itemIsFull()) {
						System.out.println("Oh, unfortunately, Item is full!");
						System.out.println("You have items below");
						for (int i = 0; i < player2.getItemSize(); i++)
							player2.showItem(player2.itemsInHandName[i]);

						System.out.println("Choose the item you throw away.If you don't, input 0");
						System.out.print(">>> ");
						int trash = scan.nextInt();
						while(!checkRange(trash, 0, player.getItemSize()))
						{
							System.out.println("Invalid number! Input again.");
							System.out.print(">>> ");
							trash = scan.nextInt();
						}
						trash -= 1;

						if (trash != -1) {
							player2.getItemAt(trash);
							for (int i = 0; i < player2.getItemSize(); i++)
								player2.showItem(player2.itemsInHandName[i]);
						}

						else {
							System.out.println("Ok, we respect your choice!");
						}
					}

					else {
						player2.getItem();
						System.out.println("\t["+player.getName()+"]'s Itemlist");
						for (int i = 0; i < player2.getItemSize(); i++)
							player2.showItem(player2.itemsInHandName[i]);
					}
				}
			}
			player.increaseHp(6); if(!player2.getName().equals("empty"))player2.increaseHp(6);
			//we print some sentence like "The player became stronger unconsciously.."
		}catch(InputMismatchException e) {
			System.out.println("You need to input in proper format!");
			System.out.println("Try again,please...");
			System.exit(0);
		}
	}

	public void PlayerTurn(Monster monster, int input) {
		try {
			System.out.println();
			player.soulful();
			player.DisCard();
			player.generateCards(5);

			// use cards
			if (input == 1) {
				while (player.hasSoul(0) && monster.alive() && !player.dead())
					player.usesCardsTo(monster);
			}

			else if (input == 2) {
				if (player.itemIsEmpty()) {
					System.out.println("\t\tThere is no item " + player.getName() +" has \n");
					System.out.println("  \n> What would you like to do?");
					System.out.println("  [1] Use Cards");
					System.out.println("  [2] Turn End");
					System.out.print(">>> ");
					input = scan.nextInt();

					while(!checkRange(input, 1, 2))
					{
						System.out.println("Dormammu, I've come to bargain!\r\n" + "If you want to quit, input correctly!");
						System.out.print(">>> ");
						input = scan.nextInt();
					}
					if(input == 1)
					{
						while (player.hasSoul(0) && monster.alive() && !player.dead())
							player.usesCardsTo(monster);
					}

					else if(input == 2)
					{
						return;
					}

				}
				// If you have item,
				else {
					int choice = 1;
					while (!player.itemIsEmpty() && choice != 0) // You can use until your itemlist is empty
					{
						player.showItemList();
						System.out.println("[0] Back");
						System.out.println("Choose item to use!");
						System.out.print(">>> ");
						choice = scan.nextInt();

						while(!checkRange(choice, 0, player.getItemSize()))
						{
							System.out.println("Input correctly! Input agiain!");
							System.out.print(">>> ");
							choice = scan.nextInt();	
						}
						player.useItemsTo(monster, choice);
					}
					
						System.out.println("  \n> What would you like to do?");
						System.out.println("  [1] Use Cards");
						System.out.println("  [2] Turn End");
						System.out.print(">>> ");
						input = scan.nextInt();

						while(!checkRange(input, 1, 2))
						{
							System.out.println("Dormammu, I've come to bargain!\r\n" + "If you want to quit, input correctly!");
							System.out.print(">>> ");
							input = scan.nextInt();
						}
						if(input == 1)
						{
							while (player.hasSoul(0) && monster.alive() && !player.dead())
								player.usesCardsTo(monster);
						}

						else if(input == 2)
						{
							return;
						}
				}
			}

			System.out.println("\n\n\t\t"+player.getName() + "'s Turn ends!");
		}catch(InputMismatchException e) {
			System.out.println("You need to input in proper format!");
			System.out.println("Try again,please...");
			System.exit(0);
		}
	}

	public void Player2Turn(Monster monster, int input) {
		try {
			System.out.println();
			player2.soulful();
			player2.DisCard();
			player2.generateCards(5);

			// use cards
			if (input == 1) {
				while (player2.hasSoul(0) && monster.alive() && !player2.dead())
					player2.usesCardsTo(monster);
			}

			else if (input == 2) {
				if (player2.itemIsEmpty()) {
					System.out.println("\t\tThere is no item " + player.getName() +" has \n");
					System.out.println("  \n> What would you like to do?");
					System.out.println("  [1] Use Cards");
					System.out.println("  [2] Turn End");
					System.out.print(">>> ");
					input = scan.nextInt();

					while(!checkRange(input, 1, 2))
					{
						System.out.println("Dormammu, I've come to bargain!\r\n" + "If you want to quit, input correctly!");
						System.out.print(">>> ");
						input = scan.nextInt();
					}
					if(input == 1)
					{
						while (player2.hasSoul(0) && monster.alive() && !player.dead())
							player2.usesCardsTo(monster);
					}

					else if(input == 2)
					{
						return;
					}

				}
				// If you can use item,
				else {
					int choice = 1;
					while (!player2.itemIsEmpty() && choice != 0) {
						player2.showItemList();
						System.out.println("[0] Back");
						System.out.println("Choose item to use!");
						System.out.print(">>> ");
						choice = scan.nextInt();

						while(!checkRange(choice, 0, player2.getItemSize()))
						{
							System.out.println("Input correctly! Input agiain!");
							System.out.print(">>> ");
							choice = scan.nextInt();	
						}
						player2.useItemsTo(monster, choice);
					}
					
					
					System.out.println("  \n> What would you like to do?");
					System.out.println("  [1] Use Cards");
					System.out.println("  [2] Turn End");
					System.out.print(">>> ");
					input = scan.nextInt();

					while(!checkRange(input, 1, 2))
					{
						System.out.println("Dormammu, I've come to bargain!\r\n" + "If you want to quit, input correctly!");
						System.out.print(">>> ");
						input = scan.nextInt();
					}
					if(input == 1)
					{
						while (player.hasSoul(0) && monster.alive() && !player.dead())
							player.usesCardsTo(monster);
					}

					else if(input == 2)
					{
						return;
					}
				}
			}

			System.out.println("\n\n\t\t"+player2.getName() + "'s Turn ends!");
		}catch(InputMismatchException e) {
			System.out.println("You need to input in proper format!");
			System.out.println("Try again,please...");
			System.exit(0);
		}
	}

	// phase2. RestStage
	// > 1. HP 30% UP
	public void RestStage() {
		System.out.println("\tIn the world, there is no for free. Your point decreased 15..\n");
		player.increasePoint(-15);
		//If we input this condition, we should notice to players before coming here!
		System.out.println("\t # Rest Stage #\n");
		int RestIncreaseHp = (player.getMaxHp() / 10) * 3;
		System.out.println("Heal for 30%(" + RestIncreaseHp + ") of " + player.getName()+"'s Max HP.");
		player.increaseHp(RestIncreaseHp);

		if(!player2.getName().equals("empty"))
		{
			player2.increasePoint(-15);
			//If we input this condition, we should notice to players before coming here!
			System.out.println("\t # Rest Stage #\n");
			RestIncreaseHp = (player2.getMaxHp() / 10) * 3;
			System.out.println("Heal for 30%(" + RestIncreaseHp + ") of " + player2.getName()+"'s your Max HP.");
			player2.increaseHp(RestIncreaseHp);
		}
	}

	// phase3. TreasureStage
	// TreasureStage give some randomTreasure
	// Treasures include item(potion) special item that give strength
	public void TreasureStage() {

		try {
			//we need to change our sentence by making conditional statements!
			System.out.println("\t # Treasure Stage #\n");
			System.out.println("  > At night, he entered the cave without any wastes.");
			System.out.println("  > Now, you found bright golden box...");
			System.out.println("  > Will you open?");
			System.out.println(" [1] Yes");
			System.out.println(" [2] No");
			System.out.print(">>> ");
			int answer = scan.nextInt();

			if (answer == 1) {
				if (player.itemIsFull()) {
					System.out.println("\n\n\t\t< " + player.getName() +" >");
					System.out.println("Oh,"+player.getName()+"! Unfortunately, Item is full!");
					System.out.println("You have items below");
					for (int i = 0; i < player.getItemSize(); i++)
						player.showItem(player.itemsInHandName[i]);

					System.out.println("Choose the item you throw away.If you don't, input 0");
					System.out.print(">>> ");
					int trash = scan.nextInt() - 1;

					if (checkRange(trash, 0, 2)) {
						player.increasePoint(15);
						player.getItemAt(trash);
						for (int i = 0; i < player.getItemSize(); i++)
							player.showItem(player.itemsInHandName[i]);
					}

					else {
						player.increasePoint(30);
						//Notice with print!
						System.out.println("Ok, we respect your choice!");
					}
				}

				else {
					player.getItem();
					player.showItemList();
				}
				
				if(!player2.getName().equals("empty"))
				{
					if (player2.itemIsFull()) {
						System.out.println("\n\n\t\t< " + player2.getName() +" >");
						System.out.println("Oh,"+player2.getName()+"! Unfortunately, Item is full!");
						System.out.println("You have items below");
						for (int i = 0; i < player2.getItemSize(); i++)
							player.showItem(player2.itemsInHandName[i]);

						System.out.println("Choose the item you throw away.If you don't, input 0");
						System.out.print(">>> ");
						int trash = scan.nextInt() - 1;

						if (checkRange(trash, 0, 2)) {
							player2.increasePoint(15);
							player2.getItemAt(trash);
							for (int i = 0; i < player2.getItemSize(); i++)
								player2.showItem(player2.itemsInHandName[i]);
						}

						else {
							player2.increasePoint(30);
							//Notice with print!
							System.out.println("Ok, we respect your choice!");
						}
					}
					
					else {
						player2.getItem();
						player2.showItemList();
					}
				}
			}

			else {
				System.out.println("Player went straight out of the cave.");
				if(!player2.getName().equals("empty")) System.out.println("Surely, with Player2~");
			}
		}catch(InputMismatchException e) {
			System.out.println("You need to input in proper format!");
			System.out.println("Try again,please...");
			System.exit(0);
		}
	}

	// phase4. UnknownStage
	// In Unknown Stage, it happens random event(exclude Boss), Enemy, Rest,
	// Treasure
	// or Special event
	// probability enemy 50%, special event 20%, Rest 15%, Treasure 15%
	public void UnknownStage(int StageNum) {
		System.out.println("\tHow brave! In this stage, gift 40 points!");
		
		player.increasePoint(40);
		if(!player2.getName().equals("empty"))
			player2.increasePoint(40);

		int randStage = rand.nextInt(100);
		if (randStage < 50)
			EnemyStage(StageNum);
		else if (randStage < 65)
			RestStage();
		else if (randStage < 80)
			TreasureStage();
		else
			SpecialStage();

	}

	// phase5. BossStage
	// Player can go to BossStage when StangeNum is more than 10
	// If player defeat the boss, player can clear the game
	public void BossStage() {
		player.increasePoint(200); 
		if(!player2.getName().equals("empty")) player2.increasePoint(200);
		Monster monster = new Monster("Boss"); drawBoss();
		if(!player2.getName().equals("empty")) 
			monster.Powerful();
		
		System.out.println("\n\n\t\t\"I knew you come here someday....\"");
		System.out.println("\n\n\t\t\"" + player.getName() + "....\"");
		System.out.println("\t # " + monster.getName() + " appeared! #");
		// game starts here
		while (monster.alive() && !player.dead() && !player2.dead()) {
			if(player2.getName().equals("empty"))
			{
				System.out.println("------------------------------------------------------------------------------------------");
				System.out.println("\n\t<Player Turn>\n");
				player.getStatus(); 	
			}

			else {
				System.out.println("------------------------------------------------------------------------------------------");
				player.getStatus(); player2.getStatus();
				System.out.println("\n\t<Player1 Turn>\n");
			}
			monster.getStatus();
			// Player's turn:
			int input = PlayerChoiceAction();
			
			PlayerTurn(monster, input); //Player1 turn
			if (!monster.alive())
			{
				player.kills(monster);
				break;
			}

			if(player.poison == 1) {
				System.out.println("Because of poison, Hp decreased 5");
				player.damage(5);
			}
			
			if(player.dead())
			{
				DeathEnd();
				return;  //Later, we will make code to challenge again!
			}

			if(!player2.getName().equals("empty")) {
				System.out.println("------------------------------------------------------------------------------------------");
				player.getStatus(); player2.getStatus(); monster.getStatus();
				System.out.println("\n\t<Player2 Turn>\n");	
				input = PlayerChoiceAction();
				Player2Turn(monster, input); //Player2 turn
				
				if (!monster.alive())
				{
					player2.kills(monster);
					break;
				}
				if(player2.poison == 1) {
					System.out.println("Because of poison, Hp decreased 5");
					player2.damage(5);
				}
				if(player2.dead())
				{
					DeathEnd();
					return;  //Later, we will make code to challenge again!
				}
				player2.DisCard();
			}
			player.DisCard(); 

			// Monster's turn:
			System.out.println("\n\t<Enemy Turn>\n");
			monster.hasAbility();
			monster.attacks(player);

			if (!monster.alive())
			{
				player.kills(monster);
				break;
			}

			if(player.dead())
			{
				DeathEnd();
				return;  //Later, we will make code to challenge again!
			}

			if(!player2.getName().equals("empty"))
			{	
				monster.attacks(player2);
				if (!monster.alive())
				{
					player2.kills(monster);
					break;
				}

				if(player2.dead())
				{
					DeathEnd();
					return;  //Later, we will make code to challenge again!
				}
			}
		}
		HappyEnd();
	}

	public void SpecialStage() {
		try {
			System.out.println("Welcome to Special Stage!");
			System.out.println("You who are seeing this sentence are super lucky!");
			//We will decide!!
			System.out.println("Choose whatever you want...");
			System.out.println(" [1] Double point!");
			System.out.println(" [2] Special Monster");
			System.out.println(" [0] Exit");
			System.out.print(">>> ");
			int choice = scan.nextInt();
			
			while(!checkRange(choice, 0, 2))
			{
				System.out.println("Please check your input value again!");
				choice = scan.nextInt();
			}
			if(choice == 1) {
				System.out.println("Days of glory, We will make your score double!");
				player.increasePoint(player.getPoint());
				if(!player.getName().equals("empty")) player2.increasePoint(player2.getPoint());
				System.out.printf("Now, %d is %s's score!\n",player.getPoint(), player.getName());
				if(!player2.getName().equals("empty")) 
					System.out.printf("And, %d is %s's score!\n",player2.getPoint(), player2.getName());
			}
			
			else if(choice == 2) {
				player.increasePoint(50);
				if(!player2.getName().equals("empty")) 
					player2.increasePoint(50);

				Monster monster = new Monster("Alien"); 

				drawAlien();
				
				System.out.println("\n\n\t\t # " + monster.getName() + " appeared! #");
				// game starts here
				player.DisCard();
				player2.DisCard();
				player.generateCards(5);
				player2.generateCards(5);
				
				while (monster.alive() && !player.dead() && !player2.dead()) {
					if(player2.getName().equals("empty"))
					{
						System.out.println("------------------------------------------------------------------------------------------");
						System.out.println("\n\t<Player Turn>\n");
						player.getStatus(); 	
					}

					else {
						System.out.println("------------------------------------------------------------------------------------------");
						player.getStatus(); player2.getStatus();
						System.out.println("\n\t<Player1 Turn>\n");
					}
					monster.getStatus();
					// Player's turn:
					int input = PlayerChoiceAction();
					PlayerTurn(monster, input); //Player1 turn
					if (!monster.alive())
					{
						player.kills(monster);
						break;
					}

					if(player.dead())
					{
						DeathEnd();
						return;  //Later, we will make code to challenge again!
					}

					if(!player2.getName().equals("empty")) {
						System.out.println("------------------------------------------------------------------------------------------");
						player.getStatus(); player2.getStatus(); monster.getStatus();
						System.out.println("\n\t<Player2 Turn>\n");	
						input = PlayerChoiceAction();
						Player2Turn(monster, input); //Player2 turn

						if (!monster.alive())
						{
							player2.kills(monster);
							break;
						}

						if(player2.dead())
						{
							DeathEnd();
							return;  //Later, we will make code to challenge again!
						}
						player2.DisCard();
					}
					player.DisCard(); 
					if(!player2.getName().equals("empty")) player2.DisCard();
					// Monster's turn:
					System.out.println("\n\t<Enemy Turn>\n");
					monster.attacks(player);

					if (!monster.alive())
					{
						player.kills(monster);
						break;
					}

					if(player.dead())
					{
						DeathEnd();
						return;  //Later, we will make code to challenge again!
					}

					if(!player2.getName().equals("empty"))
					{	
						monster.attacks(player2);
						if (!monster.alive())
						{
							player2.kills(monster);
							break;
						}

						if(player2.dead())
						{
							DeathEnd();
							return;  //Later, we will make code to challenge again!
						}
					}
				}
				System.out.println("\n\n");
				if (monster.hasItem()) {
					if (player.itemIsFull()) {
						System.out.println("Oh, unfortunately, Item is full!");
						System.out.println("You have items below");
						for (int i = 0; i < player.getItemSize(); i++)
							player.showItem(player.itemsInHandName[i]);

						System.out.println("Choose the item you throw away.If you don't, input 0");
						System.out.print(">>> ");
						int trash = scan.nextInt();
						while(!checkRange(trash, 0, player.getItemSize()))
						{
							System.out.println("Invalid number! Input again.");
							System.out.print(">>> ");
							trash = scan.nextInt();
						}
						trash -= 1;
						if (trash != -1) {
							player.getItemAt(trash);
							for (int i = 0; i < player.getItemSize(); i++)
								player.showItem(player.itemsInHandName[i]);
						}

						else {
							System.out.println("Ok, we respect your choice!");
						}
					}

					else {
						player.getItem();
						System.out.println("\t["+player.getName()+"]'s Itemlist"); 
						for (int i = 0; i < player.getItemSize(); i++)
							player.showItem(player.itemsInHandName[i]);
					}

					if(!player2.getName().equals("empty"))
					{
						if (player2.itemIsFull()) {
							System.out.println("Oh, unfortunately, Item is full!");
							System.out.println("You have items below");
							for (int i = 0; i < player2.getItemSize(); i++)
								player2.showItem(player2.itemsInHandName[i]);

							System.out.println("Choose the item you throw away.If you don't, input 0");
							System.out.print(">>> ");
							int trash = scan.nextInt();
							while(!checkRange(trash, 0, player.getItemSize()))
							{
								System.out.println("Invalid number! Input again.");
								System.out.print(">>> ");
								trash = scan.nextInt();
							}
							trash -= 1;

							if (trash != -1) {
								player2.getItemAt(trash);
								for (int i = 0; i < player2.getItemSize(); i++)
									player2.showItem(player2.itemsInHandName[i]);
							}

							else {
								System.out.println("Ok, we respect your choice!");
							}
						}

						else {
							player2.getItem();
							System.out.println("\t["+player.getName()+"]'s Itemlist");
							for (int i = 0; i < player2.getItemSize(); i++)
								player2.showItem(player2.itemsInHandName[i]);
						}
					}
				}
				player.increaseHp(6); if(!player2.getName().equals("empty"))player2.increaseHp(6);
			}
			
			else {
				return;
			}
		}catch(InputMismatchException e) {
			System.out.println("You need to input in proper format!");
			System.out.println("Try again,please...");
			System.exit(0);
		}
	}

	public void DeathEnd() {
		System.out.println("\tYou are dead......");
		System.out.println("\t######################");
		System.out.println("\t# THANKS FOR PLAYING #");
		System.out.println("\t######################");
		SaveScore();
		ShowScoreBoard();
		System.exit(0);
	}

	public void HappyEnd() {
		System.out.println("\tYou are awesome! Finally you finished this game!");
		System.out.println("\t######################");
		System.out.println("\t# THANKS FOR PLAYING #");
		System.out.println("\t######################");
		System.out.printf("\t > You got %d Points\n\n", player.getPoint());
		SaveScore();
		ShowScoreBoard();
		System.exit(0);
	}

	public static void ShowScoreBoard(){
		try {
			File file = new File("record.txt");
			Scanner filescan = new Scanner(file);

			while(filescan.hasNextLine()){
				System.out.println(filescan.nextLine());
			}
			filescan.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("I think you should make the text file to save all the records");
			System.out.println("1. Open the Game.java");
			System.out.println("2. Find the function ShowScoreBoard");
			System.out.println("3. Check the path and,please edit or make the file along with the path");
			System.out.println("And then, try again!! Thank you for your cooperation!");
			System.exit(0);
		}
	}

	public void SaveScore() {
		try {
			File file = new File("record.txt");
			BufferedWriter bufwriter = new BufferedWriter(new FileWriter(file,true));
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");

			if(file.isFile() && file.canWrite())
			{
				bufwriter.write(" " + player.getName() + "\t" + player.getPoint() + "\t  " +date.format(today) + " " + time.format(today));
				bufwriter.newLine(); bufwriter.newLine();

				if(!player2.getName().equals("empty")) {
					bufwriter.write(" " + player2.getName() + "\t" + player2.getPoint() + "\t  " +date.format(today) + " " + time.format(today));
					bufwriter.newLine(); bufwriter.newLine();	
				}
			}
			bufwriter.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("I think you should make the text file to save all the records");
			System.out.println("1. Open the Game.java");
			System.out.println("2. Find the function ShowScoreBoard");
			System.out.println("3. Check the path and,please edit or make the file along with the path");
			System.out.println("And then, try again!! Thank you for your cooperation!");
			System.exit(0);
		}catch(IOException e) {
			System.out.println("Input error at SaveScore");
			System.exit(0);
		}
	}

	public static void makeScoreBoardFile() {
		try {
			File file = new File("record.txt");
			if(!file.exists()) {
				BufferedWriter bufwriter = new BufferedWriter(new FileWriter(file));
				if(file.isFile() && file.canWrite())
				{
					bufwriter.write("\t< Game name's ScoardBoard >");
					bufwriter.newLine(); bufwriter.newLine();
					bufwriter.write(" Name \t Score \t \tTime\n");
				}

				bufwriter.close();
			}
		}catch(FileNotFoundException e) {
			System.out.println("I think you should make the text file to save all the records");
			System.out.println("1. Open the Game.java");
			System.out.println("2. Find the function ShowScoreBoard");
			System.out.println("3. Check the path and,please edit or make the file along with the path");
			System.out.println("And then, try again!! Thank you for your cooperation!");
			System.exit(0);
		}catch(IOException e) {
			System.out.println("Input error at SaveScore");
			System.exit(0);
		}
	}

	public static boolean checkRange(int choice, int from, int to) {
		for(int i = from; i < to + 1; i++)
			if(i == choice)
				return true;

		return false;
	}

	public static void drawBoss()

	{
		System.out.println("\n\n\n\n, \r\n" + 
				"                  d \r\n" + 
				"                 :8;        oo \r\n" + 
				"                ,888     od88 Y \r\n" + 
				"                \" b     \"`88888  ,. \r\n" + 
				"                  :    88888888o. \r\n" + 
				"                   Y.   `8888888bo \r\n" + 
				"                    \"bo  d888888888oooooo,. \r\n" + 
				"                      \"Yo8:8888888888888Yd8. \r\n" + 
				"                        `8b;Y88888888888888' \r\n" + 
				"                        ,888d\"Y888888888888.  , \r\n" + 
				"                        P88Y8b.888888888888b.         `b \r\n" + 
				"                        :;  \"8888888888888888P8b       d; \r\n" + 
				"                         .   Y88888888888888bo   \",o ,d8 \r\n" + 
				"                              `\"8888888888888888888888\",oo. \r\n" + 
				"                               :888888888888888888888P\"\"   ` \r\n" + 
				"                               `88888888888888888888oooooo. : \r\n" + 
				"                            ;  ,888888Y888888888888888888888\" \r\n" + 
				"                            `'`P\"\"8.`8;`888888888888888888888o. \r\n" + 
				"                              `    ` :;  `888888888888888\"\"\"8P\"o. \r\n" + 
				"                                 ,  '`8    Y88888888888888;  :b Yb \r\n" + 
				"                                  8.  :.    `Y8888888888888; dP  `8 \r\n" + 
				"                                ,8d8    \"     `888888888888d      Y; \r\n" + 
				"                                :888d8;        88888888888Pb       ; \r\n" + 
				"                                :888' `   o   d888888888888;      :' \r\n" + 
				"                               oo888bo  ,.\"8888888888888888;    ' ' \r\n" + 
				"                               8888888888888888888888888888; \r\n" + 
				"                       ,.`88booo`Y888888888888888888888888P' \r\n" + 
				"                           :888888888888888888888888888888' \r\n" + 
				"                   ,ooooood888888888888888888888888888888' \r\n" + 
				"                  \"\"888888888888888888888888888888888888; \r\n" + 
				"             ,oooooo888888888888888888888888888888888888' \r\n" + 
				"               \"88888888888888888888888888888888888888P \r\n" + 
				"       ,oo bo ooo88888888888888888888888888888888888Y8 \r\n" + 
				"     .\"8P88d888888888888888888888888888888888888888\"`\"o. \r\n" + 
				"      oo888888888888888888888888888888888888888888\"    8 \r\n" + 
				"     d88Y8888888888888888888888888888888888888888' ooo8bYooooo. \r\n" + 
				"    ,\"\"o888888888888888888888888888888888P\":888888888888888888b \r\n" + 
				"    `   ,d88888888888888888888888888888\"'  :888888888888888bod8 \r\n" + 
				"      ,88888888888888888888888888888\"      `d8888888888888o`88\"b \r\n" + 
				"    ,88888888888888888888888888\"\"            ,88888' 88  Y8b \r\n" + 
				"    \" ,8888888888888888888\"\"        ,;       88' ;   `8'  P \r\n" + 
				"     d8888888888888888888boo8888888P\"         :.     ` \r\n" + 
				"    d888888888888888888888888888888boooo \r\n" + 
				"   :\"Y888888888888P':88888\"\"\"8P\"88888P' \r\n" + 
				"   ` :88888888888'   88\"\"  ,dP' :888888. \r\n" + 
				"    ,88888888888'          '`  ,88,8b d\" \r\n" + 
				"    d88888888888               dP\"`888' \r\n" + 
				"    Y :888888888;                   8' \r\n" + 
				"      :8888888888.                 ` \r\n" + 
				"      :888888888888oo                        ,ooooo \r\n" + 
				"      :8888888888888o              ,o   oo d88888oooo. \r\n" + 
				"       ' :888888888888888booooood888888888888888888888bo.        \r\n" + 
				"          Y88888888888888888888888888888888888\"\"\"888888o. \r\n" + 
				"           \"`\"Y8888888888888888888888888\"\"\"\"'     `\"88888b. \r\n" + 
				"               \"888\"Y888888888888888\"                Y888.' \r\n" + 
				"                `\"'  `\"\"' `\"\"\"\"\"'  \"          ,       8888 \r\n" + 
				"                                              :.      8888 \r\n" + 
				"                                           d888d8o    88;` \r\n" + 
				"                                           Y888888;  d88; \r\n" + 
				"                                         o88888888,o88P:' \r\n" + 
				"                                    \"ood888888888888\"' \r\n" + 
				"                                ,oo88888\"\"888888888. \r\n" + 
				"                              ,o8P\"b8YP  '`\"888888;\"b. \r\n" + 
				"                           ' d8\"`o8\",P    \"\"\"\"\"\"\"8P \r\n" + 
				"                                    `;          d8; \r\n" + 
				"                                                 8; ");
	}

	public static void drawReaper() {
		System.out.println("			                    `\"-._                    \r\n" + 
				"		                      `. \"-._                \r\n" + 
				"		                        T.   \"-.             \r\n" + 
				"		                         $$p.   \"-.          \r\n" + 
				"		                         $$$$b.    `,        \r\n" + 
				"		                      .g$$$$$$$b    ;        \r\n" + 
				"		                    .d$$$$$$$$$$;   ;        \r\n" + 
				"		                 __d$$$$$$P\"\"^T$$   :        \r\n" + 
				"		               .d$$$$P^^\"\"___       :        \r\n" + 
				"		              d$P'__..gg$$$$$$$$$$; ;        \r\n" + 
				"		             d$$ :$$$$$$$$$$$$$$$$;  ;       \r\n" + 
				"		            :$$; $$$$$$$$$$$$$$$$P  :$       \r\n" + 
				"		            $$$  $$$$$$$$$$$$$$$$b  $$       \r\n" + 
				"		           :$$$ :$$$$$$$$$$$$$$$$$; $$;      \r\n" + 
				"		           $$$; $$$$$$$$$$$$$$$$$$; $$;      \r\n" + 
				"		          :$$$  $$$$$$$$$^$$$$$$$$$ :$$      \r\n" + 
				"		          $$$; :$$$p__gP' `Tp__g$$$ :$$      \r\n" + 
				"		         :$$$  $$P`T$P' .$. `T$P'T$; $$;     \r\n" + 
				"		         $$$; :$$;     :P^T;     :$; $$;     \r\n" + 
				"		        :$$$  $$$$-.           .-$$$ :$$     \r\n" + 
				"		        $$$$ :$$$$; \\   T$P   / :$$$  $$     \r\n" + 
				"		       :$$$; $$$$$$  ; b:$;d :  $$$$; $$;    \r\n" + 
				"		       $$$$; $$$$$$; : T T T ; :$$$$$ :$$    \r\n" + 
				"		    .g$$$$$  :$$$$$$  ;' | ':  $$$$$$  T$b   \r\n" + 
				"		 .g$$$$$$$$   $$$$$$b :     ; d$$$$$;   `Tb  \r\n" + 
				"		:$$$$$$$$$;   :$$$$$$$;     :$$$$$$P       \\ \r\n" + 
				"		:$$$$$$$$$;    T$$$$$$$p._.g$$$$$$P         ;\r\n" + 
				"		$$$P^^T$$$$p.   `T$$$$$$$$$$$$$$P'     _/`. :\r\n" + 
				"		       `T$$$$$b.  `T$$$$$$$$$$P'    .g$P   \\;\r\n" + 
				"		         `T$$$$$b.  \"^T$$$$P^\"     d$P'      \r\n" + 
				"		           `T$$$$$b.             .dP'        \r\n" + 
				"		             \"^T$$$$b.        .g$P'          \r\n" + 
				"		                \"^T$$$b    .g$P^\"            \r\n" + 
				"		                   \"^T$b.g$P^\"               \r\n" + 
				"		                      \"^$^\"                  ");
	}

	public static void drawAlien() {
		System.out.println("                                .do-\"\"\"\"\"'-o..                         \r\n" + 
				"                             .o\"\"            \"\"..                       \r\n" + 
				"                           ,,''                 ``b.                   \r\n" + 
				"                          d'                      ``b                   \r\n" + 
				"                         d`d:                       `b.                 \r\n" + 
				"                        ,,dP                         `Y.               \r\n" + 
				"                       d`88                           `8.               \r\n" + 
				" ooooooooooooooooood888`88'                            `88888888888bo, \r\n" + 
				"d\"\"\"    `\"\"\"\"\"\"\"\"\"\"\"\"Y:d8P                              8,          `b \r\n" + 
				"8                    P,88b                             ,`8           8 \r\n" + 
				"8                   ::d888,                           ,8:8.          8 \r\n" + 
				":                   dY88888                           `' ::          8 \r\n" + 
				":                   8:8888                               `b          8 \r\n" + 
				":                   Pd88P',...                     ,d888o.8          8 \r\n" + 
				":                   :88'dd888888o.                d8888`88:          8 \r\n" + 
				":                  ,:Y:d8888888888b             ,d88888:88:          8 \r\n" + 
				":                  :::b88d888888888b.          ,d888888bY8b          8 \r\n" + 
				"                    b:P8;888888888888.        ,88888888888P          8 \r\n" + 
				"                    8:b88888888888888:        888888888888'          8 \r\n" + 
				"                    8:8.8888888888888:        Y8888888888P           8 \r\n" + 
				",                   YP88d8888888888P'          \"\"888888\"Y            8 \r\n" + 
				":                   :bY8888P\"\"\"\"\"''                     :            8 \r\n" + 
				":                    8'8888'                            d            8 \r\n" + 
				":                    :bY888,                           ,P            8 \r\n" + 
				":                     Y,8888           d.  ,-         ,8'            8 \r\n" + 
				":                     `8)888:           '            ,P'             8 \r\n" + 
				":                      `88888.          ,...        ,P               8 \r\n" + 
				":                       `Y8888,       ,888888o     ,P                8 \r\n" + 
				":                         Y888b      ,88888888    ,P'                8 \r\n" + 
				":                          `888b    ,888888888   ,,'                 8 \r\n" + 
				":                           `Y88b  dPY888888OP   :'                  8 \r\n" + 
				":                             :88.,'.   `' `8P-\"b.                   8 \r\n" + 
				":.                             )8P,   ,b '  -   ``b                  8 \r\n" + 
				"::                            :':   d,'d`b, .  - ,db                 8 \r\n" + 
				"::                            `b. dP' d8':      d88'                 8 \r\n" + 
				"::                             '8P\" d8P' 8 -  d88P'                  8 \r\n" + 
				"::                            d,' ,d8'  ''  dd88'                    8 \r\n" + 
				"::                           d'   8P'  d' dd88'8                     8 \r\n" + 
				" :                          ,:   `'   d:ddO8P' `b.                   8 \r\n" + 
				" :                  ,dooood88: ,    ,d8888\"\"    ```b.                8 \r\n" + 
				" :               .o8\"'\"\"\"\"\"\"Y8.b    8 `\"''    .o'  `\"\"\"ob.           8 \r\n" + 
				" :              dP'         `8:     K       dP''        \"`Yo.        8 \r\n" + 
				" :             dP            88     8b.   ,d'              ``b       8 \r\n" + 
				" :             8.            8P     8\"\"'  `\"                 :.      8 \r\n" + 
				" :            :8:           :8'    ,:                        ::      8 \r\n" + 
				" :            :8:           d:    d'                         ::      8 \r\n" + 
				" :            :8:          dP   ,,'                          ::      8 \r\n" + 
				" :            `8:     :b  dP   ,,                            ::      8 \r\n" + 
				" :            ,8b     :8 dP   ,,                             d       8 \r\n" + 
				" :            :8P     :8dP    d'                       d     8       8 \r\n" + 
				" :            :8:     d8P    d'                      d88    :P       8 \r\n" + 
				" :            d8'    ,88'   ,P                     ,d888    d'       8 \r\n" + 
				" :            88     dP'   ,P                      d8888b   8        8 \r\n" + 
				" '           ,8:   ,dP'    8.                     d8''88'  :8        8 \r\n" + 
				"             :8   d8P'    d88b                   d\"'  88   :8        8 \r\n" + 
				"             d: ,d8P'    ,8P\"\"\".                      88   :P        8 \r\n" + 
				"             8 ,88P'     d'                           88   ::        8 \r\n" + 
				"            ,8 d8P       8                            88   ::        8 \r\n" + 
				"            d: 8P       ,:  -hrr-                    :88   ::        8 \r\n" + 
				"            8',8:,d     d'                           :8:   ::        8 \r\n" + 
				"           ,8,8P'8'    ,8                            :8'   ::        8 \r\n" + 
				"           :8`' d'     d'                            :8    ::        8 \r\n" + 
				"           `8  ,P     :8                             :8:   ::        8 \r\n" + 
				"            8, `      d8.                            :8:   8:        8 \r\n" + 
				"            :8       d88:                            d8:   8         8 \r\n" + 
				" ,          `8,     d8888                            88b   8         8 \r\n" + 
				" :           88   ,d::888                            888   Y:        8 \r\n" + 
				" :           YK,oo8P :888                            888.  `b        8 \r\n" + 
				" :           `8888P  :888:                          ,888:   Y,       8 \r\n" + 
				" :            ``'\"   `888b                          :888:   `b       8 \r\n" + 
				" :                    8888                           888:    ::      8 \r\n" + 
				" :                    8888:                          888b     Y.     8, \r\n" + 
				" :                    8888b                          :888     `b     8: \r\n" + 
				" :                    88888.                         `888,     Y     8: ");
	}
}