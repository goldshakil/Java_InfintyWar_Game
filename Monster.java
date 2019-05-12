import java.util.Random;

public class Monster {

	private String Name;
	private int Hp;
	private int Shield;
	private int attackDamage;
	Random rand = new Random();

	public Monster(String name) {
		this.Name = name;

		switch (Name) {

		case "Skeleton":
			Hp = 45;
			Shield = 0;
			attackDamage = 7;
			break;

		case "Warrior":
			Hp = 60;
			Shield = 5;
			attackDamage = 11;
			break;

		case "Zombie":
			Hp = 75;
			Shield = 10;
			attackDamage = 15;
			break;

		case "Assassin":
			Hp = 90;
			Shield = 15;
			attackDamage = 19;
			break;


		case "Alien":
			Hp = 1;
			Shield = 100;
			attackDamage = 1;
			break;

		case "Boss":
			Hp = 150;
			Shield = 25;
			attackDamage = 25;
			break;

		default:
			System.out.println("There is something wrong! Thank you for finding error!");
			System.out.println("Please shut down this game right now!");
			System.exit(0);
		}
	}

	public boolean alive() {
		return Hp > 0;
	}

	public void damage(int damage) {
		if (damage < 0) {
			this.Hp -= damage;
			System.out.println("\t> " + Name + " heals " + (-damage) + ".");
		} else {
			if (Shield >= damage)
				Shield -= damage;

			else {
				Hp -= damage - Shield;
				Shield = 0;
			}
			System.out.println("\t> You deal " + Name + " With " + damage + " Damages.");
		}

		if (this.Hp <= 0)
			System.out.println("\t> " + Name + " was defeated! #");
		else {
			System.out.println();
		}
		getStatus();
	}

	public void getStatus() {

		System.out.println("  > " + Name + "'s HP: " + Hp);

		System.out.print("    ");
		for (int c = 1; c <= Hp / 10; c++) // Hp Bar
		{
			System.out.print("¡Ü");
		}
		if(Hp % 10 > 0) // Hp Bar
		{
			System.out.printf("¡Û");
		}
		System.out.println();

		if (Shield > 0) {
			System.out.println("  > " + Name + "'s Block: " + Shield);
			System.out.print("    ");
			for (int c = 1; c <= Shield / 10; c++) // Hp Bar
			{
				System.out.printf("¡Ü");
			}
			if(Shield % 10 > 0) // Hp Bar
			{
				System.out.printf("¡Û");
			}
		}

		else if(Shield == 0) {
			System.out.println("  > " + Name + "'s Block: ZERO");

		}
		System.out.println();


	}

	public void setHp(int n) {
		Hp = n;
	}

	public int getHp() {
		return Hp;
	}

	public void attacks(Player player) {

		System.out.println("\t > " + Name + "Atack!!");
		switch (Name) {

		case "Skeleton":
			player.damage(attackDamage - 2 + rand.nextInt(5));
			break;

		case "Warrior":
			if (rand.nextInt(3) % 2 == 0) {
				player.damage(attackDamage - 3 + rand.nextInt(7));
			}

			else // special attack ignoring shields
			{
				System.out.println("\t # Warrior can ignore shields!!");
				int temp = player.getShield();
				player.setShield(0);
				player.damage(attackDamage);
				player.increaseShield(temp);
			}
			break;

		case "Zombie":
			if (rand.nextInt(2) == 0) {
				player.damage(attackDamage - 4 + rand.nextInt(9));
			}

			else // special attack healing Hp from player's hp
			{
				System.out.println("\t # Zombie has absorbed your stamina.");
				player.damage(attackDamage);
				heals(attackDamage / 2);
			}

			break;

		case "Assassin":
			int select = rand.nextInt(4);
			if (select % 2 == 0) {
				player.damage(attackDamage - 5 + rand.nextInt(11));
			}

			else if (select == 1)// special status: HP, Shield and attackDamage increase 7!
			{
				System.out.println("\t # The assassin strengthens himself through meditation.");
				System.out.println("\t # Assassin's shield, attackDamage and Hp Increase 7!!\n");
				Shield += 7;
				attackDamage += 7;
				heals(7);
			}

			else {
				int AssI;
				System.out.println("\t # Quatorze: Instead enemy is weak that attackDamage becomes half,");
				System.out.println("\t # be able to attack three times!\n\n");
				attackDamage /= 2;
				for(AssI = 0; AssI < 3 ; AssI++)
					player.damage((attackDamage - 5 + rand.nextInt(11)));
			}
			break;


		case "Alien":
			int select1 = rand.nextInt(4);
			if (select1 == 0) // Copy Hp
			{
				System.out.println("\\t #The alien copied your Hp!");
				Hp = player.getMaxHp();
			}

			else if (select1 == 1) // Absorb player's Shield
			{
				System.out.println("\t # The alien absorbed your Shield.");
				Shield += player.getShield();
				System.out.println("\t # Alien's shield increases "+ player.getShield() +"!!\n");
				player.setShield(0);
			}


			else if(select1 == 2) //Give player 5 souls 
			{
				System.out.println("Alien's power has an effect on your Soul");
				System.out.println("Your soul increased 5!");
				player.increaseSoul(5);
			}

			else //Change its Name!
			{
				select1 = rand.nextInt(4);
				if(select1 == 0)	 Name = "Zombie";
				else if(select1 == 1)	 Name = "Warrior";
				else if(select1 == 2)	 Name ="Skeleton";
				else	 Name ="Assassin";


				System.out.println("Monster's appearance is blurred.\r\n" + 
						"It suddenly peeled off and became "+ Name +"!");
			}

			break;

		case "Boss":
			// Input any attack patterns!
			int bossAttack;
			if(player.poison == 0)
			 bossAttack = rand.nextInt(4);

			else
			 bossAttack = rand.nextInt(3);	
				
			if(bossAttack == 0) {
				System.out.printf("\n\t%d....No pain, No gain..%s\n\n", attackDamage,player.getName());
				player.damage(attackDamage);  
			}

			else if(bossAttack == 1) {
				if(player.getShield() > Shield)
				{
					System.out.println("Your shield looks delicious, You better exchange!");
					int temp = player.getShield();
					player.setShield(Shield);
					Shield = temp;
				}
				
				else{
					System.out.println("Shield is just an appearance, take it off!");
					System.out.println("All the shields became zero");
					player.setShield(0);
					Shield = 0;
				}
			}

			else if(bossAttack == 2) {
				System.out.println("\tInfinite Attack! Search recursive on Google...\n");
				this.attacks(player); 
			}

			else {
				System.out.println("Poison spread out from its mouth.");
				player.poison = 1;
			}

			break;

		default:
			System.out.println("There is something wrong! Thank you for finding error!");
			System.out.println("Please shut down this game right now!");
			System.exit(0);
		}
	}

	// only for boss!
	public void hasAbility() {
		if(Name == "Boss") {
			attackDamage += 5;
			System.out.println("When boss gets own turn, Boss gain 5 strength");
		}
	}

	public void heals(int num) {
		Hp += num;
	}

	public void setShield(int num) {
		Shield = num;
	}

	public boolean hasItem() // probability of dropping item!
	{
		switch (Name) {
		case "Skeleton":
			if (rand.nextInt(7) == 0)
				return true;
			break;

		case "Warrior":
			if (rand.nextInt(5) == 0)
				return true;
			break;

		case "Zombie":
			if (rand.nextInt(3) == 0)
				return true;
			break;

		case "Assassin":
			if (rand.nextInt(2) == 0)
				return true;
			break;

		case "Alien":   
			return true;

		case "Boss":
			return true;

		default:
			System.out.println("There is something wrong! Thank you for finding error!");
			System.out.println("Please shut down this game right now!");
			System.exit(0);
		}
		return false;
	}

	public void Powerful() //IF 2 playermode,
	{
		System.out.println("Becuase of 2 player mode, the monster became Stronger!");
		Hp *= 2;
		attackDamage *= 3;
		attackDamage /= 2;
		Shield += 5;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int num) {
		attackDamage = num;
		if(attackDamage < 0)
			attackDamage = 0;
	}

	public String getName() {
		return Name;   
	}

	public int getShield() {
		return Shield;
	}

	public void reflectStageNum(int num) {
		Hp += num;
		Shield += num;
		attackDamage += num;
	}
}