import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Player {

	Scanner scan = new Scanner(System.in);// used for inputting the numbers
	Random rand = new Random(); // used for generating any random numbers
	private String Name;
	private int Hp; // Player's health
	private int maxHp = 60; // Player's max health
	private int Shield = 0; // Player can block damage until next turn
	private int Point = 0;
	private int Soul = 0;
	private int itemSize = 3;
	private int killNum = 0;
	private int dragonball = 0;
	private int standardSoul = 3;
	public int poison = 0;
	String[] cardName = new String[21]; // names of these cards
	String[] cardsInHandName = new String[10];
	String[] itemName = new String[7];
	String[] itemsInHandName = new String[itemSize];

	public Player(String Name) {
		this.Name = Name;
		this.Hp = maxHp;
		Point = 0;
		Soul = 3;
		itemSize = 3;
		killNum = 0;
		dragonball = 0;
		standardSoul = 3;
		// setting_the_deck:

		cardName[0] = "Strike";
		cardName[1] = "God's Gift";
		cardName[2] = "Devil's Justice";
		cardName[3] = "Thanos's Gauntlet";
		cardName[4] = "Defend";
		cardName[5] = "Quiz Time";
		cardName[6] = "Pandora's Box";
		cardName[7] = "Thor's Hammer";
		cardName[8] = "Mind Blast";
		cardName[9] = "Reboot";
		cardName[10] = "Offering";
		cardName[11] = "Bash";
		cardName[12] = "Quick Slash";
		cardName[13] = "BackFlip";
		cardName[14] = "Bite";
		cardName[15] = "Bludgeon";
		cardName[16] = "Twin Strike";
		cardName[17] = "Impervious";
		cardName[18] = "Flash of Steel";
		cardName[19] = "Adrenaline";
		cardName[20] = "Treasure";

		itemName[0] = "Time Stone";
		itemName[1] = "Soul Potion";
		itemName[2] = "Blood Potion";
		itemName[3] = "Fire Potion";
		itemName[4] = "Fruit Potion";
		itemName[5] = "Block Potion";
		itemName[6] = "Dragon Ball"; // please make any items!!
		for (int i = 0; i < itemSize; i++)
			itemsInHandName[i] = "empty";
		DisCard();


	}

	public void DisCard() {
		for (int i = 0; i < 10; i++)
			cardsInHandName[i] = "empty";

	}

	// function to generate 5 random cards
	public void generateCards(int num) {
		int chooser = 0;
		int checking;
		// randomly generate cards
		for (int i = 0; i < num; i++) {

			chooser = rand.nextInt(cardName.length);
			checking = 0;
			while (checking != cardsInHandName.length) {

				if (cardsInHandName[checking] == "empty") {
					cardsInHandName[checking] = cardName[chooser];
					break;
					
				} else
					checking++;
			}
			
			if (checking == cardsInHandName.length) {
				System.out.println(" > Your Hand is Full!");
				break;
			}
		}

	}

	// function to show all the cards in the hand of the player
	public void showCards() {
		for (int i = 0; i < 10; i++) {
			if (cardsInHandName[i] == "empty")
				continue;
			else {
				System.out.printf("[%d] %s : ", i + 1, cardsInHandName[i]);
				switch (cardsInHandName[i]) {
				case "empty":
					System.out.println();
					break;

				case "Strike":
					System.out.println("Deal 1~15 Damages ($)");
					break;

				case "God's Gift":
					System.out.println("Deal 15 Damages And Get 15 Shields from God ($$$)");
					break;

				case "Devil's Justice":
					System.out.println("You And Monster Will be dealt 15 Damages ($)");
					break;

				case "Thanos's Gauntlet":
					System.out.println("Lessens Monster's Hp To Half destroying its shields.($$$$)");
					break;

				case "Defend":
					System.out.println("Get 7 Shield Points ($)");
					break;

				case "Quiz Time":
					System.out.println(
							"You Will Be Challenged With A Random Question \nAnd The Results Will Be Depnding On Your Answer (???)");
					break;

				case "Pandora's Box":
					System.out.println("No One Knows What's In The Box. If You're Brave Enough, Open It! ($)");
					break;

				case "Thor's Hammer":
					System.out.println("Deal 15 Damages ignoring shields($$)");
					break;

				case "Mind Blast":
					System.out.println("Deal damage equal to player's kill point ( Point:" + getKillPoint()+ " )");
					break;

				case "Reboot":
					System.out.println("Draw 5 Cards Again ($)");
					break;

				case "Offering":
					System.out.println("Lose 5 Hp, gain 2 Souls, Draw 2 cards ()");
					break;

				case "Bash":
					System.out.println("Deal 10 damage, Apply 5 weak ($$)");
					break;

				case "Quick Slash":
					System.out.println("Deal 8 damage, Draw 1 card ($)");
					break;

				case "BackFlip":
					System.out.println("Gain 5 Shield, Draw 2 cards ($)");
					break;

				case "Bite":
					System.out.println("Deal 7 damage, Heal 5 Hp ($)");
					break;

				case "Bludgeon":
					System.out.println("Deal 23 ~ 27 damage ($$$)");
					break;

				case "Twin Strike":
					System.out.println("Deal 7 damage twice ($)");
					break;

				case "Impervious":
					System.out.println("Gain 20 Shield ($$)");
					break;

				case "Flash of Steel":
					System.out.println("Deal 6 damage, Draw 1 card ()");
					break;

				case "Adrenaline":
					System.out.println("Gain 1 soul, Draw 2 cards ()");
					break;

				case "Treasure":
					System.out.println("get 50 Points ($$$)");
					break;   

				default:
					System.out.println("There is Something Wrong! Thank you for finding error!");
					System.out.println("Please shut down this game right now!");
					System.exit(0);
				}
				System.out.println("----------------------");
			}
		}
	}

	public void usesCardsTo(Monster monster) {
	
		try {
			showSouls();
			showCards();
			System.out.println("[0] Back (If there is nothing to do)");
			System.out.print(">>> ");
			int cardNumber = scan.nextInt();
			
			while(!Game.checkRange(cardNumber, 0, 10)) {
				System.out.println("Invalid cardIndex! Input again!");
				System.out.print(">>> ");
				cardNumber = scan.nextInt();
			}
			
			cardNumber -= 1;
			if (cardNumber == -1) {
				Soul = -1;
				return;
			}

			switch (cardsInHandName[cardNumber]) {

			case "empty":
				System.out.println("\t # There is no card to use");
				break;

			case "Strike":
				if(hasSoul(1)) {
					System.out.println("\t # Player Strikes The Monster!");
					monster.damage(rand.nextInt(15) + 1);
					useSoul(1);
					cardsInHandName[cardNumber] = "empty";
				}
				
				else {
					System.out.println("\t # You Don't Have Enough Souls.");
				}
				
				break;

			case "God's Gift":
				if (hasSoul(3)) {
					System.out.println("\t # Thanks God");
					monster.damage(15);
					increaseShield(15);
					useSoul(3);
					Point += 10;
					cardsInHandName[cardNumber] = "empty";
				}

				else
					System.out.println("\t # You Don't Have Enough Souls.");

				break;

			case "Devil's Justice":
				if (hasSoul(1)) {
					System.out.println("\t # Devil \" The World Is Unfair. \"");
					useSoul(1);
					monster.damage(15);
					damage(15);
					cardsInHandName[cardNumber] = "empty";
				}

				else
					System.out.println("\t # You Don't Have Enough Souls.");
				break;

			case "Thanos's Gauntlet":
				if (hasSoul(4)) {
					System.out.println("\t # For the Peace of Humanity, Everything Will Be Halved");
					System.out.println("\t # Including Your Hp!!HAHAHAHAHA!\n");
					System.out.println("\\t # Surely, Your shield was destroyed..");
					useSoul(4);
					monster.setShield(0);
					
					if(monster.getHp() != 1)
					monster.damage(monster.getHp() / 2);
					
					else
						monster.damage(1);
					
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("\t # You Don't Have Enough Souls.");
				break;

			case "Defend":
				if (hasSoul(1)) {
					useSoul(1);
					increaseShield(7);
					cardsInHandName[cardNumber] = "empty";
				}

				else
					System.out.println("\t # You Don't Have Enough Souls.");
				break;

			case "Quiz Time":
				System.out.println("\t # Welcome to the Quiz Show!");
				cardsInHandName[cardNumber] = "empty";
				int bound = 2; // If you make a lot quizs, just edit this number!
				int choice = rand.nextInt(bound) + 1;

				switch (choice) {
				case 1:
					System.out.println("\t # Which of the following is a Markup language?");
					System.out.println("\t # [1]:Solidity [2]:HTML [3]:JavaScript [4]:Ruby");
					System.out.print(">>> ");
					int answer = scan.nextInt();

					if (answer == 2) {
						answer = rand.nextInt(3);
						System.out.printf("\t # Congratulations!! You Get %d Souls\n", answer);
						Point += 15;
						Soul += answer;
					}

					else {
						System.out.println("\t # Wrong! You will lose your soul or Hp!");
						if (hasSoul(1)) {
							System.out.println("\t # 1 Soul Disappeared...");
							useSoul(1);
						} else
							damage(rand.nextInt(10));
					}
					break;

					
				case 2:
					System.out.println("\t # Which of the following has no common attributes?");
					System.out.println("\t # [1]:Newton [2]:Eve [3]:Obama [4]:Steve Jobs");
					System.out.print(">>> ");
					answer = scan.nextInt();

					if (answer == 3) {
						answer = rand.nextInt(3);
						System.out.printf("\t # Congratulations!! You Get %d Souls\n", answer);
						Point += 15;
						Soul += answer;
					}

					else {
						System.out.println("\t # Wrong! You will lose your soul or Hp!");
						System.out.println("The hint is Apple...");
						if (hasSoul(1)) {
							System.out.println("\t # 1 Soul Disappeared...");
							useSoul(1);
						} else
							damage(rand.nextInt(10));
					}
					break;
					
					
				default:
					System.out.println("\t # There is something wrong! Thank you for finding error!");
					System.out.println("\t # Please shut down this game right now!");
					System.exit(0);
				}

				break;

			case "Pandora's Box":
				if (hasSoul(1)) {
					cardsInHandName[cardNumber] = "empty";
					System.out.println("\n\n\t\t<Welcome to Pandora's Box! You Can't Go Back Now >\n");
					if (rand.nextBoolean()) {
						System.out.println("\t # Because of Pandora, Monster Gets The Chance to Attack (50%)");
						monster.attacks(this);
						getStatus();
					}

					if (rand.nextInt(100) == 77) {
						System.out.println("\t # Miraculous Power Kills The Monster!! (1%)");
						monster.setHp(0);
					}

					if (rand.nextBoolean()) {
						System.out.println("\t # Pandora Sometimes Gives Negative Damage. It Means Like Healing! (50%)");
						monster.damage(rand.nextInt(15) - 14);
					}

					if (rand.nextInt(10) < 3) {
						System.out.println("\t # Lucky! You can Got 3 Souls(Very Rare To Get)! (30%)");
						Soul += 3;
					}

					if (rand.nextInt(10) > 6) {
						System.out.println("\t # How Kind Pandora Is! Thanks To Pandora,(30%)");
						increaseHp(rand.nextInt(15));
					}

					if (rand.nextInt(11) % 5 == 0) {
						System.out.println("\t # Pandora Steals Your Shield and 1 Soul If You Have (18%)");
						System.out.println("\t # Or, Pandora Eats Your Life~");
						if (hasSoul(1))
							useSoul(1);
						else {
							System.out.println("\t # Because Of No Soul,");
							damage(rand.nextInt(10) + 1);
						}
						if (getShield() != 0)
							setShield(0);

						else {
							System.out.println("\t # Because Of No Shield,");
							damage(rand.nextInt(10) + 1);
						}
					}
				}

				
				else {
					System.out.println("\t # You Don't Have Enough Souls.");
				}
				break;

			case "Thor's Hammer":
				if (hasSoul(2)) {
					System.out.println("\n\n\t\t Thor: God Of Thunder\n");
					cardsInHandName[cardNumber] = "empty";
					useSoul(2);
					int temp = monster.getShield();
					monster.damage(15 + temp);
					monster.setShield(temp);
				} else
					System.out.println("You Don't Have Enough Souls.");

				break;

			case "Mind Blast":
				monster.damage(getKillPoint());
				cardsInHandName[cardNumber] = "empty";
				break;

			case "Reboot":
				if (hasSoul(1)) {
					useSoul(1);
					System.out.println("\n\n\t\t New Cards!!!You Get New 5 Cards Again!\n");
					DisCard();
					generateCards(5);
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			case "Offering":
				Hp -= 5;
				Soul += 2;
				generateCards(2);
				cardsInHandName[cardNumber] = "empty";
				break;

			case "Bash":
				if (hasSoul(2)) {
					useSoul(2);
					monster.damage(10);
					monster.setAttackDamage(monster.getAttackDamage() - 5);
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			case "Quick Slash":
				if (hasSoul(1)) {
					useSoul(1);
					monster.damage(8);
					generateCards(1);
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			case "BackFlip":
				if (hasSoul(1)) {
					useSoul(1);
					increaseShield(5);
					generateCards(2);
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			case "Bite":
				if (hasSoul(1)) {
					useSoul(1);
					monster.damage(7);
					Hp += 5;
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			case "Bludgeon":
				if (hasSoul(3)) {
					useSoul(3);
					monster.damage(25 + rand.nextInt(5) - 2);
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			case "Twin Strike":
				if (hasSoul(1)) {
					useSoul(1);
					monster.damage(7);
					monster.damage(7);
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			case "Impervious":
				if (hasSoul(2)) {
					useSoul(2);
					increaseShield(20);
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			case "Flash of Steel":
				monster.damage(6);
				generateCards(1);
				cardsInHandName[cardNumber] = "empty";
				break;

			case "Adrenaline":
				System.out.println("Gain 1 soul, Draw 2 cards ()");
				Soul += 1;
				generateCards(2);
				cardsInHandName[cardNumber] = "empty";
				break;   

			case "Treasure":
				System.out.println("get 50 Points by Treasure Card.");
				if (hasSoul(3)) {
					useSoul(3);
					Point +=50 ;
					cardsInHandName[cardNumber] = "empty";
				} else
					System.out.println("You Don't Have Enough Souls.");
				break;

			default:
				System.out.println("There is something wrong! Thank you for finding error!");
				System.out.println("Please shut down this game right now!");
				System.exit(0);
			}
			System.out.println("\n\n\n\n\n");

		}catch(InputMismatchException e) {
			System.out.println("Input format is not proper.");
			System.out.println("Please try again,bro...");
			System.exit(0);
		}
	}

	// when Shield card is used
	public void increaseShield(int gain) {
		Point += gain/2;
		this.Shield += gain;
		if(gain > 0)
			System.out.println("  > " + Name + " Gain " + gain + " Shield.");
	}

	public int getShield() {
		return Shield;
	}

	// when item like med kit is used
	public void increaseHp(int gain) {
		Point += gain;
		this.Hp += gain;
		if (this.Hp >= maxHp)
			this.Hp = maxHp;

		System.out.println("  > " + Name + " gain " + gain + " HP. (Hp:" +this.Hp +")\n");

	}

	// print the player's status
	public void getStatus() {

		System.out.println("  > [" + Name + "] HP : " + Hp + " / " + maxHp);
		System.out.printf("    ");

		for (int c = 1; c <= Hp / 10; c++) // Hp Bar
		{
			System.out.printf("¡Ü");
		}
		if (Hp % 10 > 0) // Hp Bar
		{
			System.out.printf("¡Û");
		}
		System.out.println();

		if (Shield >= 0) {
			System.out.println("  > [" + Name + "] Block : " + Shield);
			System.out.printf("    ");

			for (int c = 1; c <= Shield / 10; c++) {
				System.out.printf("¡Ü");
			}
			if (Shield % 10 > 0) {
				System.out.printf("¡Û");
			}

		}
		System.out.println();

	}

	public boolean dead() {
		return Hp <= 0;
	}

	// function to handle monster's attacks
	public void damage(int damage) {
		Point += damage/2;
		if (Shield > 0) {
			if (Shield >= damage) {
				Shield -= damage;
				System.out.println("  > [" + Name + "] Block " + damage);
				getStatus();
			} else {
				System.out.println("  > [" + Name + "] Block " + Shield);
				Hp -= (damage - Shield);
				System.out.println("  > [" + Name + "] have taken " + damage + " from enemy");
				Shield = 0;
				getStatus();

			}
		} else {
			Hp -= damage;
			System.out.println("  > [" + Name + "] have taken " + damage);
			getStatus();

		}

		if (this.Hp <= 0) {
			this.Hp = 0;
			System.out.println("  > [" + Name + "] have taken too much damage, you are too weak to go on!");
		}

	}

	public void kills(Monster monster) {
		killNum +=1 ;
		switch (monster.getName()) {
		case "Skeleton":
			System.out.println();
			System.out.println(" > " + Name + " killed " + monster.getName() + "!!");
			System.out.println(" > " + Name + " got 10~20 points!");
			Point += rand.nextInt(10)+10;
			System.out.println("KillPoint: " +killNum + " Point: " + Point );
			break;

		case "Warrior":
			System.out.println();
			System.out.println(" > " + Name + " killed " + monster.getName() + "!!");
			System.out.println(" > " + Name + " got 15~30 points!");
			Point += rand.nextInt(15)+ 15;
			System.out.println("KillPoint: " +killNum + " Point: " + Point );
			break;

		case "Zombie":
			System.out.println();
			System.out.println(" > " + Name + " killed " + monster.getName() + "!!");
			System.out.println(" > " + Name + " got 20~40 points!");
			Point += rand.nextInt(20)+ 20;
			System.out.println("KillPoint: " +killNum + " Point: " + Point );
			break;

		case "Assassin":
			System.out.println();
			System.out.println(" > " + Name + " killed " + monster.getName() + "!!");
			System.out.println(" > " + Name + " got 25~50 points!");
			Point += rand.nextInt(25)+25;
			System.out.println("KillPoint: " +killNum + " Point: " + Point );
			break;

			
		case "Alien":
			System.out.println();
			System.out.println(" > " + Name + " killed " + monster.getName() + "!!");
			System.out.println(" > " + Name + " got 777 points!");
			Point += 777;
			System.out.println("KillPoint: " +killNum + " Point: " + Point );
			break;
			
		case "Boss":
			System.out.println();
			System.out.println(" > " + Name + " killed " + monster.getName() + "!!");
			System.out.println(" > " + Name + " got 50~100 points!");
			Point += rand.nextInt(50) + 50;
			System.out.println("KillPoint: " +killNum + " Point: " + Point );
			break;

		default:
			System.out.println();
			System.out.println(" > " + "There is something wrong! Thank you for finding error!");
			System.out.println(" > " + "Please shut down this game right now!");
			return;
		}
	}

	public void soulful() {
		Soul = standardSoul;
	}

	public boolean hasSoul(int num) {
		return Soul >= num;
	}

	public void useSoul(int num) {
		if (hasSoul(num)) {
			Soul -= num;
		}

		else {
			System.out.println("There is something wrong! Thank you for finding error!");
			System.out.println("Please shut down this game right now!");
			System.exit(0);

		}
	}

	public void setShield(int num) {
		if(num >= 0)
			Shield = num;

		else
			Shield = 0;
	}

	public void showSouls() {
		System.out.printf("\t> %s's Souls: ", Name);

		for (int i = 0; i < Soul; i++)
			System.out.print("$");
		System.out.println();
	}

	public boolean itemIsEmpty() {
		for (int i = 0; i < itemSize; i++)
			if (!itemsInHandName[i].equals("empty"))
				return false;

		return true;
	}

	public boolean itemIsFull() {
		for (int i = 0; i < itemSize; i++)
			if (itemsInHandName[i].equals("empty"))
				return false;

		return true;
	}

	public void getItem() {
		for (int emptyIndex = 0; emptyIndex < itemSize; emptyIndex++)
			if (itemsInHandName[emptyIndex].equals("empty")) {
				int randItem = rand.nextInt(itemName.length);
				System.out.println("\t> "+ this.Name +" gets [" + itemName[randItem] + "]\n\n");
				itemsInHandName[emptyIndex] = itemName[randItem];
				return;
			}
	}

	// If itemlist is full,
	public void getItemAt(int index) {
		itemsInHandName[index] = itemName[rand.nextInt(itemName.length)];
	}

	public void useItemsTo(Monster monster, int choice) {

		choice -= 1;

		if (choice == -1)
			return;

		switch (itemsInHandName[choice]) {
		case "empty":
			System.out.println("\n\n\t\tIt is empty or already used!");
			break;

		case "Time Stone": // example,
			System.out.println("\n\n\t\tNo One Stops Time.");
			Hp = maxHp;
			Shield += 15;
			System.out.println("\t\tPlayer's condition cannot be better than this!");

			monster.setHp(monster.getHp() / 3);
			monster.setShield(0);
			System.out.println("\t\tTime Rips off Monster's life!");
			break;

		case "Soul Potion":
			Soul += 5;
			break;

		case "Blood Potion":
			increaseHp((this.maxHp) / 10);
			break;

		case "Fire Potion":
			monster.damage(25);
			break;

		case "Fruit Potion":
			this.maxHp += 5;
			break;

		case "Shield Potion":
			increaseShield(30);
			break;

		case "Dragon Ball":
			
			dragonball += 1;
			System.out.printf("By using it, You have %d dragon balls.",dragonball);
			if(dragonball == 3)
			{
				int input = scan.nextInt(3);
				if(input == 0) {
					System.out.println("You get 1000 points!");
					Point += 1000;
				}
				
				else if(input == 1) {
					System.out.println("Your maxHp increased +10! and heals to Max!");
					this.maxHp += 10;
					Hp = maxHp;
				}
				
				else {
					System.out.println("Your original Soul increased +1! It means 4!");
					standardSoul += 1;
				}
			}
			
			dragonball = 0;
			break;

			
		case "Block Potion":
			increaseShield(30);
			System.out.println("You got 30 blocks. You are like OP!");
			break;
			
		default:
			System.out.println("There is something wrong! Thank you for finding error!");
			System.out.println("Please shut down this game right now!");
			System.exit(0);
		}
		itemsInHandName[choice] = "empty";
	}

	public void showItemList() {

		System.out.println("\t> Your items:");
		for (int i = 0; i < itemSize; i++) {
			System.out.printf("[%d] ", i + 1);
			showItem(itemsInHandName[i]);
			System.out.println("\n");
		}
	}

	public void showItem(String itemName) {

		System.out.print(itemName + " : ");
		switch (itemName) {
		// For example,
		case "empty":
			System.out.println("It is empty or already used.");
			break;

		case "Time Stone":
			System.out.println("Player comes back in the best condition, but");
			System.out.println("Monster lifetime is eaten by Time Stone");
			break;

			// Imagine whatever you want!
		case "Soul Potion":
			System.out.println("Gain 5 Soul");
			break;

		case "Blood Potion":
			System.out.println("Heal for 10%(" + maxHp / 10 + ") of your Max HP.");
			break;

		case "Fire Potion":
			System.out.println("Deal 20 Damage to enemy");
			break;

		case "Fruit Potion":
			System.out.println("Gain 5 Max HP");
			break;
			
		case "Shield Potion":
			System.out.println("Gain 30 Shield");
			break;

		case "Dragon Ball":
			System.out.println("If you use Dragon Ball 3 times, You can accomplish one of your wishes");
			break;
			
			
		case "Block Potion":
			System.out.println("Wakanda's power make your armor mighty!");
			break;
			
			default:
				System.out.println("There is an error about showItem!Thank you!");
				System.exit(0);
		}
		System.out.println();
	}

	public String getName() {
		return Name;
	}
	
public void increasePoint(int num) {
		Point += num;
		System.out.println("Now your Point: "+ Point + "\n");
	}
	
	public int getPoint(){
		return Point;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	
	public int getItemSize() {
		return itemSize;
	}
	
	public void setItemSize(int num) {
		itemSize = num; 
	}
	
	public int getKillPoint() {
		return killNum;
	}

	public void increaseSoul(int num) {
		Soul += num;
	}
}