import java.io.*;
import java.util.*;

public class Driver{
    public static void main(String args[]){
        Scanner reader = new Scanner(System.in);
        String dir = null;
        String action;
        String menui;
        int runCount = 0;
        int missCount = 0;
        boolean checkMap;
        boolean checkDir;
        boolean checkAction;
        boolean checkS;
        boolean checkSL;
        boolean checkSS;
        boolean run;
        boolean miss;
        boolean monster;
        Map map = new Map();

        System.out.println("** Hero & Monsters **\n");
        System.out.println("* The map has been generated *");
        System.out.println("* The enemies have been placed *");
        System.out.println("* The items have been placed *\n");
        System.out.println("Hero begins his journey in the Southwest corner of Fiona");

        while(true){
            System.out.println(map.toString());
            System.out.println(map.getInv().toString());
            System.out.println("Health: " + map.getHero().getHP() + "\tWeapon: " + map.getHero().getWeapon() + "\tArmor: " + map.getHero().getArmor());
            System.out.println("Gold: " + map.getHero().getGold() + "\n");

            do{

                System.out.print("Enter an action (w,a,s,d,p): ");
                dir = reader.next();
                checkDir = checkDir(dir);

                while(checkDir == false){
                    System.out.print("Re-enter an action (w,a,s,d,p): ");
                    dir = reader.next();
                    checkDir = checkDir(dir);
                }

                if(map.checkPossibleHeroPos(dir) == false){
                    System.out.println("Can not go off the map! Please try again.");
                }
            }while(map.checkPossibleHeroPos(dir) == false);
            
            if(dir.equalsIgnoreCase("p")){
                System.out.print("1. Small Potion: " + map.getInv().getAmountSmallPotion()+ "\n\tRestore 50 HP\n" + 
                    "2. Big Potion: "+ map.getInv().getAmountBigPotion() +"\n\tRestore to full health\n3. Phoenix: "+map.getInv().getAmountBigPotion()+"\n\tRestore to full health and immediate revive\n\n4. Back");
                int potioni = reader.nextInt();
                checkSL = checkSLong(potioni);

                while(checkSL == false){
                    System.out.print("Please enter a valid number");
                    potioni = reader.nextInt();
                    checkSL = checkSLong(potioni);
                }

                if(potioni == 1){
                    if(map.getInv().getAmountSmallPotion() <= 0){
                        System.out.println("Do not have any Small Potions to use. Please buy at the Shop.");
                    }else{
                        map.getInv().useAmountSmallPotion();
                        int HP = map.getHero().getHP() + 50;
                        if(HP > 100){
                            map.getHero().setHP(100);
                        }
                        else
                            map.getHero().setHP(HP);

                        System.out.println("Hero's health is " + map.getHero().getHP() + "/100 !");
                    }
                }
                else if(potioni == 2){
                    if(map.getInv().getAmountBigPotion() <= 0){
                        System.out.println("Do not have any Big Potions to use. Please buy at the Shop.");
                    }else{
                        map.getInv().useAmountBigPotion();
                        map.getHero().setHP(100);
                        System.out.println("Hero used a Big Potion! Hero's health is " + map.getHero().getHP() + "/100 !");
                    }
                }
                else if(potioni == 3){
                    if(map.getInv().getAmountPhoenix() <= 0){
                        System.out.println("Do not have any Phoenix to use. Please buy at the Shop.");
                    }else{
                        map.getInv().useAmountPhoenix();
                        map.getHero().setHP(100);
                        System.out.println("Hero used a Phoenix! Hero's health is " + map.getHero().getHP() + "/100 !");
                    }
                }else if(potioni == 4)
                    continue;
            }

            int prob = (int) (Math.random()*101);
            if(prob <= 10){
                System.out.println("\nHero encounters a monster! The monster engages!");
                Monster m = new Monster(map.getHero().getWeapon()); 
                final int mHP = m.getHealth();
                while(true){
                    System.out.print("Enter an action (1. run\t\t2. attack\t3. Bomb\n\t\t4. Small Potion\t5. Big Potion\t6. Phoenix): ");
                    action = reader.next();
                    checkAction = checkAction(action);
                    while(checkAction == false){
                        System.out.print("Re-enter an action (1. run\t2. attack\t3. Bomb\n\t\t4. Small Potion\t5. Big Potion\t6. Phoenix): ");
                        action = reader.next();
                        checkAction = checkAction(action);
                    }

                    if(runCount > 0 || missCount > 0){
                        m.setDir("S");
                    }
                    run = map.run(m.getSpeed(),m.getDir());
                    miss = map.miss(m.getSpeed(),m.getDir());
                    if(action.equalsIgnoreCase("1")){
                        if(run == false){
                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroHP = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHP);

                            if(map.getHero().getHP() <= 0){
                                break;
                            }else{
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");
                                runCount++;
                            }
                        }
                        else if(run == true){
                            if(map.getHero().getHP() <= 0){
                                break;
                            }
                            else{
                                System.out.println("The hero successfuly ran away! Hero health is " + map.getHero().getHP() + "/100 !");
                                map.move(dir);
                                break;
                            }
                        }
                    }
                    else if(action.equalsIgnoreCase("2") && miss == false){
                        if(map.getHero().hasSandels() == false){
                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroATK = map.getWeapon().useWeapon(map.getHero().getWeapon());
                            int monsterHPS = m.getHealth()- heroATK;
                            m.setHealth(monsterHPS);

                            if(m.getHealth() <= 0){
                                System.out.println("Hero has succesdfully killed the monster! Hero's health is " + map.getHero().getHP() + "/100 !");
                                map.getHero().setGold(map.getHero().getGold() + m.getGold());
                                map.move(dir);
                                break;
                            }else
                                System.out.println("Hero successfully atacked the monster! Monster health is now " + monsterHPS +  "/"  + mHP +" !");
                            int heroHPS = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHPS);
                            if(map.getHero().getHP() <= 0){
                                break;
                            }else
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHPS + "/100 !");
                            missCount++;
                        }
                        else if(map.getHero().hasSandels() == true){
                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroATK1 = map.getWeapon().useWeapon(map.getHero().getWeapon());
                            int monsterHPS1 = m.getHealth()- heroATK1;
                            int heroATK2 = map.getWeapon().useWeapon(map.getHero().getWeapon());
                            int monsterHPS2 = monsterHPS1 - heroATK2;
                            m.setHealth(monsterHPS2);

                            if(m.getHealth() <= 0){
                                System.out.println("Hero has succesdfully killed the monster! Hero's health is " + map.getHero().getHP() + "/100 !");
                                map.getHero().setGold(map.getHero().getGold() + m.getGold());
                                map.move(dir);
                                break;
                            }else{
                                System.out.println("Hero successfully atacked the monster! Monster health is now " + monsterHPS1 +  "/"  + mHP +" !");
                                System.out.println("Hero successfully atacked the monster again! Monster health is now " + monsterHPS1 +  "/"  + mHP +" !");
                            }
                            int heroHPS = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHPS);
                            if(map.getHero().getHP() <= 0){
                                break;
                            }else
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHPS + "/100 !");
                            missCount++;
                        }
                    }
                    else if(action.equalsIgnoreCase("2") && miss == true){
                        int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                        int heroHP = map.getHero().getHP() - mATK;
                        map.getHero().setHP(heroHP);

                        if(map.getHero().getHP() <= 0){
                            break;
                        }else{
                            System.out.println("Hero's attack missed!");
                            System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");
                        }
                    }
                    else if(action.equalsIgnoreCase("3")){
                        if(map.getInv().getAmountBomb() <= 0){
                            System.out.println("Do not have any Bombs to use. Please buy at the Shop.");

                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroHP = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHP);

                            if(map.getHero().getHP() <= 0){
                                break;
                            }else
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                            continue;
                        }else{
                            map.getInv().useAmountBomb();
                            m.setHealth(0);
                            System.out.println("Hero has successfully blown up the monster! Hero's health is " + map.getHero().getHP() + "/100 !");
                            map.move(dir);
                            break;
                        }
                    }
                    else if(action.equalsIgnoreCase("4")){
                        if(map.getInv().getAmountSmallPotion() <= 0){
                            System.out.println("Do not have any Small Potions to use. Please buy at the Shop.");

                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroHP = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHP);

                            if(map.getHero().getHP() <= 0){
                                break;
                            }else
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                            continue;
                        }else{
                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroHP = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHP);

                            if(map.getHero().getHP() <= 0){
                                break;
                            }else
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                            map.getInv().useAmountSmallPotion();
                            int HP = map.getHero().getHP() + 50;
                            if(HP > 100){
                                map.getHero().setHP(100);
                            }
                            else
                                map.getHero().setHP(HP);

                            System.out.println("Hero's health is " + map.getHero().getHP() + "/100 !");

                            continue;
                        }
                    }else if(action.equalsIgnoreCase("5")){
                        if(map.getInv().getAmountBigPotion() <= 0){
                            System.out.println("Do not have any Big Potions to use. Please buy at the Shop.");

                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroHP = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHP);

                            if(map.getHero().getHP() <= 0){
                                break;
                            }else
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                            continue;
                        }else{
                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroHP = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHP);

                            if(map.getHero().getHP() <= 0){
                                break;
                            }else
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                            map.getInv().useAmountBigPotion();
                            map.getHero().setHP(100);

                            System.out.println("Hero used a Big Potion! Hero's health is " + map.getHero().getHP() + "/100 !");

                            continue;
                        }
                    }else if(action.equalsIgnoreCase("6")){
                        if(map.getInv().getAmountPhoenix() <= 0){
                            System.out.println("Do not have any Phoenix to use. Please buy at the Shop.");

                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroHP = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHP);

                            if(map.getHero().getHP() <= 0){
                                break;
                            }else
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                            continue;
                        }else{
                            map.getInv().useAmountPhoenix();

                            int mATK = map.getArmor().useArmor(m,map.getHero().getArmor(),map.getHero().getWeapon());
                            int heroHP = map.getHero().getHP() - mATK;
                            map.getHero().setHP(heroHP);

                            if(map.getHero().getHP() <= 0){
                                map.getHero().setHP(100);
                                System.out.println("Hero is revived by Phoenix! Hero's health is " + map.getHero().getHP() + "/100 !");
                            }else{
                                System.out.println("Monster successfully atacked the hero! Hero health is now " + heroHP + "/100 !");
                                System.out.println("Hero used a Phoenix! Hero's health is " + map.getHero().getHP() + "/100 !");
                            }

                            continue;
                        }
                    }
                }

            }
            else if(map.isFarmer(dir) == true){
                if(map.whichFarmer(dir).getName().equals("FMap")){
                    map.getFarmerMap().useFMap(map);
                    System.out.println("Here is a where all my fellow Farmers are!");
                }
                else if(map.whichFarmer(dir).getName().equals("D1")){
                    if(thereBoss(map.getMap(),map.getB1()) == true && map.getHero().hasSatchel() == true){
                        map.setBcount(1);
                        System.out.println("Please Kill the Boss Monster that is terrorizing the town!");
                    }
                    else if(thereBoss(map.getMap(),map.getB1()) == true && map.getHero().hasSatchel() == false){
                        map.setDir1(true);
                        System.out.println("Please buy a Satchel at the Shop to have the location of this Boss");
                    }
                    else if(thereBoss(map.getMap(),map.getB1()) == false){
                        System.out.println("Thank you for saving the town!");
                    }
                }
                else if(map.whichFarmer(dir).getName().equals("D2")){
                    if(thereBoss(map.getMap(),map.getB2()) == true && map.getHero().getWeapon().equals("Short Sword")){
                        map.setBcount(2);
                        System.out.println("Please Kill the Boss Monster that is terrorizing the town!");
                    }
                    else if(thereBoss(map.getMap(),map.getB2()) == true && map.getHero().getWeapon().equals("Dagger")){
                        map.setDir2(true);
                        System.out.println("Please buy atleast a Short Sword at the Shop or kill the First Boss to have the location of this Boss");
                    }
                    else if(thereBoss(map.getMap(),map.getB2()) == false){
                        System.out.println("Thank you for saving the town!");
                    }
                }
                else if(map.whichFarmer(dir).getName().equals("D3")){
                    if(thereBoss(map.getMap(),map.getB3()) == true && map.getHero().getWeapon().equals("Long Sword")){
                        map.setBcount(3);
                        System.out.println("Please Kill the Boss Monster that is terrorizing the town!");
                    }
                    else if(thereBoss(map.getMap(),map.getB3()) == true && ( map.getHero().getWeapon().equals("Dagger") || map.getHero().getWeapon().equals("Short Sword") )){
                        map.setDir3(true);
                        System.out.println("Please buy atleast a Long Sword at the Shop or kill the Second Boss to have the location of this Boss");
                    }
                    else if(thereBoss(map.getMap(),map.getB3()) == false){
                        System.out.println("Thank you for saving the town!");
                    }
                }
                else if(map.whichFarmer(dir).getName().equals("D4")){
                    if(thereBoss(map.getMap(),map.getB4()) == true && map.getHero().getWeapon().equals("Axe") && map.getHero().hasSandels() == true && !map.getHero().getArmor().equals("Trousers") && map.getInv().getAmountPhoenix() >= 3){
                        map.setBcount(4);
                        System.out.println("Please Kill the Boss Monster that is terrorizing the town!");
                    }
                    else if(thereBoss(map.getMap(),map.getB4()) == false){
                        System.out.println("Thank you for saving the town!");
                    }
                    else{
                        map.setDir4(true);
                        System.out.println("Please buy an Axe,Armor,Sandels, and/or 3 Phoenix at the Shop or kill the Third Boss to have the location of this Boss");
                    }
                }
                else if(map.whichFarmer(dir).getName().equals("Shop")){
                    map.setShop(true);
                    int count = 0;

                    do{
                        System.out.print("What would you like to purchase? (Enter number)\n1. Potions\n2. Bomb\n3. Weapons\n4. Armor\n5. Satchel\n6. Sandels\n7. Exit");
                        menui = reader.next();
                        int gold = map.getHero().getGold();
                        checkS = checkShop(menui);

                        while(checkS == false){
                            System.out.print("Please enter a valid number");
                            menui = reader.next();
                            checkS = checkShop(menui);
                        }
                        if(menui.equals("1")){
                            System.out.print("Which potion would you like to buy?\n1. Small Potion\t\t10 Gold\n\tRestore 50 HP\n" + 
                                "2. Big Potion\t\t25 Gold\n\tRestore to full health\n3. Phoenix\t\t55 Gold\n\tRestore to full health and immediate revive\n\n4. Back");
                            int potioni = reader.nextInt();
                            checkSL = checkSLong(potioni);

                            while(checkSL == false){
                                System.out.print("Please enter a valid number");
                                potioni = reader.nextInt();
                                checkSL = checkSLong(potioni);
                            }

                            if(potioni == 1){
                                if(map.getInv().addInventory(potioni) == true && map.getHero().getGold() >= 10){
                                    map.getHero().setGold(gold - 10);
                                    System.out.println("Thank you for buying! You have " + (gold - 10) + " Gold\n");
                                }
                                else
                                    System.out.println("Do not have enough Gold or Inventory Space\n");
                            }
                            else if(potioni == 2){
                                if(map.getInv().addInventory(potioni) == true && map.getHero().getGold() >= 25){
                                    map.getHero().setGold(gold - 25);
                                    System.out.println("Thank you for buying! You have " + (gold - 25) + " Gold\n");
                                }
                                else
                                    System.out.println("Do not have enough Gold or Inventory Space\n");
                            }
                            else if(potioni == 3){
                                if(map.getInv().addInventory(potioni) == true && map.getHero().getGold() >= 55){
                                    map.getHero().setGold(gold - 55);
                                    System.out.println("Thank you for buying! You have " + (gold - 55) + " Gold\n");
                                }
                                else
                                    System.out.println("Do not have enough Gold or Inventory Space\n");
                            }else if(potioni == 4)
                                continue;
                        }else if(menui.equals("2")){
                            System.out.print("1. Bomb\t\t20 Gold\n\tKills low level monsters instantly\n\n2. Back");
                            int bombi = reader.nextInt();
                            checkSS = checkSShort(bombi);

                            while(checkSS == false){
                                System.out.print("Please enter a valid number");
                                bombi = reader.nextInt();
                                checkSS = checkSShort(bombi);
                            }

                            if(bombi == 1){
                                if(map.getInv().addInventory(4) == true && map.getHero().getGold() >= 20){
                                    map.getHero().setGold(gold - 20);
                                    System.out.println("Thank you for buying! You have " + (gold - 20) + " Gold\n");
                                }
                                else
                                    System.out.println("Do not have enough Gold or Inventory Space\n");
                            }else if(bombi == 2)
                                continue;
                        }else if(menui.equals("3")){
                            System.out.print("Which Weapon would you like to buy?\n1. Short Sword\t\t45 Gold\n\tDamage Range: 30-50\n2. Long Sword\t\t80 Gold\n\tDamage Range: 50-70" +
                                "\n3. Axe\t\t       135 Gold\n\tDamage Range: 80-110\n\n4. Back");
                            int weaponi = reader.nextInt();
                            checkSL = checkSLong(weaponi);

                            while(checkSL == false){
                                System.out.print("Please enter a valid number");
                                weaponi = reader.nextInt();
                                checkSL = checkSLong(weaponi);
                            }
                            if(weaponi == 1){
                                if(map.getHero().getGold() >= 45){
                                    map.getHero().setGold(gold - 45);
                                    map.getHero().setWeapon("Short Sword");
                                    System.out.println("Thank you for buying! You have " + (gold - 45) + " Gold");
                                }
                                else
                                    System.out.println("Do not have enough Gold\n");
                            }else if(weaponi == 2){
                                if(map.getHero().getGold() >= 80){
                                    map.getHero().setGold(gold - 80);
                                    map.getHero().setWeapon("Long Sword");
                                    System.out.println("Thank you for buying! You have " + (gold - 80) + " Gold");
                                }
                                else
                                    System.out.println("Do not have enough Gold\n");
                            }else if(weaponi == 3){
                                if(map.getHero().getGold() >= 150){
                                    map.getHero().setGold(gold - 150);
                                    map.getHero().setWeapon("Axe");
                                    System.out.println("Thank you for buying! You have " + (gold - 150) + " Gold");
                                }
                                else
                                    System.out.println("Do not have enough Gold\n");
                            }else if(weaponi == 4)
                                continue;
                        }else if(menui.equals("4")){
                            System.out.print("Which Armor would you like to buy?\n1. Leather\t\t30 Gold\n\t15% Damage Reduction\n2. Chain\t\t60 Gold\n\t25% Damage Reduction" + 
                                "\n3. Dragon\t\t110 Gold\n\t45% Damage Reduction\n\n4. Back");
                            int armori = reader.nextInt();
                            checkSL = checkSLong(armori);

                            while(checkSL == false){
                                System.out.print("Please enter a valid number");
                                armori = reader.nextInt();
                                checkSL = checkSLong(armori);
                            }
                            if(armori == 1){
                                if(map.getHero().getGold() >= 30){
                                    map.getHero().setGold(gold - 30);
                                    map.getHero().setArmor("Leather");
                                    System.out.println("Thank you for buying! You have " + (gold - 30) + " Gold");
                                }
                                else
                                    System.out.println("Do not have enough Gold\n");
                            }else if(armori == 2){
                                if(map.getHero().getGold() >= 60){
                                    map.getHero().setGold(gold - 60);
                                    map.getHero().setArmor("Chain");
                                    System.out.println("Thank you for buying! You have " + (gold - 60) + " Gold");
                                }
                                else
                                    System.out.println("Do not have enough Gold\n");
                            }else if(armori == 3){
                                if(map.getHero().getGold() >= 110){
                                    map.getHero().setGold(gold - 110);
                                    map.getHero().setArmor("Dragon");
                                    System.out.println("Thank you for buying! You have " + (gold - 110) + " Gold");
                                }
                                else
                                    System.out.println("Do not have enough Gold\n");
                            }else if(armori == 4)
                                continue;
                        }else if(menui.equals("5")){
                            System.out.print("1. Satchel\t\t20 Gold\n\tAdds 7 slots to Inventory\n\n2. Back");
                            int satcheli = reader.nextInt();
                            checkSS = checkSShort(satcheli);

                            while(checkSS == false){
                                System.out.print("Please enter a valid number");
                                satcheli = reader.nextInt();
                                checkSS = checkSShort(satcheli);
                            }
                            if(satcheli == 1){
                                if(map.getHero().getGold() >= 20){
                                    map.getHero().setGold(gold - 20);
                                    map.getHero().setSatchel(true);
                                    System.out.println("Thank you for buying! You have " + (gold - 20) + " Gold");
                                }
                                else
                                    System.out.println("Do not have enough Gold\n");
                            }else if(satcheli == 2)
                                continue;
                        }else if(menui.equals("6")){
                            System.out.print("1. Sandels\t\t30 Gold\n\tDoubles Attack Speed\n\n2. Back");
                            int sandeli = reader.nextInt();
                            checkSS = checkSShort(sandeli);

                            while(checkSS == false){
                                System.out.print("Please enter a valid number");
                                sandeli = reader.nextInt();
                                checkSS = checkSShort(sandeli);
                            }
                            if(sandeli == 1){
                                if(map.getHero().getGold() >= 30){
                                    map.getHero().setGold(gold - 30);
                                    map.getHero().setSandels(true);
                                    System.out.println("Thank you for buying! You have " + (gold - 30) + " Gold");
                                }
                                else
                                    System.out.println("Do not have enough Gold\n");
                            }else if(sandeli == 2)
                                continue;
                        }else if(menui.equals("7")){
                            count = 7;
                        }
                    }while(count != 7);
                }
            }else if(map.isBoss(dir) == true){
                Boss b = map.whichBoss(dir);
                if(map.getBcount() == 1 || map.getBcount() == 2 || map.getBcount() == 3 || map.getBcount() == 4){
                    final int mHP = b.getHealth();
                    while(true){
                        System.out.print("Enter an action (1. run\t\t2. attack\t3. Bomb\n\t\t4. Small Potion\t5. Big Potion\t6. Phoenix): ");
                        action = reader.next();
                        checkAction = checkAction(action);
                        while(checkAction == false){
                            System.out.print("Re-enter an action (1. run\t\t2. attack\t3. Bomb\n\t\t4. Small Potion\t5. Big Potion\t6. Phoenix): ");
                            action = reader.next();
                            checkAction = checkAction(action);
                        }

                        run = map.run(b.getSpeed(),"");

                        if(action.equalsIgnoreCase("1")){
                            if(run == false){
                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroHP = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHP);

                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHP + "/100 !");
                            }
                            else if(run == true){
                                if(map.getHero().getHP() <= 0){
                                    break;
                                }
                                else{
                                    System.out.println("The hero successfuly ran away! Hero health is " + map.getHero().getHP() + "/100 !");
                                    map.move(dir);
                                    break;
                                }
                            }
                        }
                        else if(action.equalsIgnoreCase("2")){
                            if(map.getHero().hasSandels() == false){
                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroATK = map.getWeapon().useWeapon(map.getHero().getWeapon());
                                int bossHPS = b.getHealth()- heroATK;
                                b.setHealth(bossHPS);

                                if(b.getHealth() <= 0){
                                    System.out.println("Hero has succesdfully killed the Boss! Hero's health is " + map.getHero().getHP() + "/100 !");
                                    map.getHero().setGold(map.getHero().getGold() + b.getGold());
                                    map.move(dir);
                                    break;
                                }else
                                    System.out.println("Hero successfully atacked the Boss! Boss health is now " + bossHPS +  "/"  + mHP +" !");
                                int heroHPS = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHPS);
                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHPS + "/100 !");

                            }
                            else if(map.getHero().hasSandels() == true){
                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroATK1 = map.getWeapon().useWeapon(map.getHero().getWeapon());
                                int bossHPS1 = b.getHealth()- heroATK1;
                                int heroATK2 = map.getWeapon().useWeapon(map.getHero().getWeapon());
                                int bossHPS2 = bossHPS1 - heroATK2;
                                b.setHealth(bossHPS2);

                                if(b.getHealth() <= 0){
                                    System.out.println("Hero has succesdfully killed the Boss! Hero's health is " + map.getHero().getHP() + "/100 !");
                                    map.getHero().setGold(map.getHero().getGold() + b.getGold());
                                    map.move(dir);
                                    break;
                                }else{
                                    System.out.println("Hero successfully atacked the Boss! Boss health is now " + bossHPS1 +  "/"  + mHP +" !");
                                    System.out.println("Hero successfully atacked the Boss again! Boss health is now " + bossHPS1 +  "/"  + mHP +" !");
                                }
                                int heroHPS = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHPS);
                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHPS + "/100 !");
                            }

                        }
                        else if(action.equalsIgnoreCase("3")){
                            if(map.getInv().getAmountBomb() <= 0){
                                System.out.println("Do not have any Bombs to use. Please buy at the Shop.");

                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroHP = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHP);

                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                                continue;
                            }else{
                                map.getInv().useAmountBomb();
                                System.out.println("Boss is stunned and does not attack this turn! Bomb does no damage.");
                            }
                        }
                        else if(action.equalsIgnoreCase("4")){
                            if(map.getInv().getAmountSmallPotion() <= 0){
                                System.out.println("Do not have any Small Potions to use. Please buy at the Shop.");

                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroHP = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHP);

                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                                continue;
                            }else{
                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroHP = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHP);

                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                                map.getInv().useAmountSmallPotion();
                                int HP = map.getHero().getHP() + 50;
                                if(HP > 100){
                                    map.getHero().setHP(100);
                                }
                                else
                                    map.getHero().setHP(HP);

                                System.out.println("Hero's health is " + map.getHero().getHP() + "/100 !");

                                continue;
                            }
                        }else if(action.equalsIgnoreCase("5")){
                            if(map.getInv().getAmountBigPotion() <= 0){
                                System.out.println("Do not have any Big Potions to use. Please buy at the Shop.");

                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroHP = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHP);

                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                                continue;
                            }else{
                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroHP = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHP);

                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                                map.getInv().useAmountBigPotion();
                                map.getHero().setHP(100);

                                System.out.println("Hero used a Big Potion! Hero's health is " + map.getHero().getHP() + "/100 !");

                                continue;
                            }
                        }else if(action.equalsIgnoreCase("6")){
                            if(map.getInv().getAmountPhoenix() <= 0){
                                System.out.println("Do not have any Phoenix to use. Please buy at the Shop.");

                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroHP = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHP);

                                if(map.getHero().getHP() <= 0){
                                    break;
                                }else
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHP + "/100 !");

                                continue;
                            }else{
                                map.getInv().useAmountPhoenix();

                                int bATK = map.getArmor().useArmorBoss(b,map.getHero().getArmor(),map.getHero().getWeapon());
                                int heroHP = map.getHero().getHP() - bATK;
                                map.getHero().setHP(heroHP);

                                if(map.getHero().getHP() <= 0){
                                    map.getHero().setHP(100);
                                    System.out.println("Hero is revived by Phoenix! Hero's health is " + map.getHero().getHP() + "/100 !");
                                }else{
                                    System.out.println("Boss successfully atacked the hero! Hero health is now " + heroHP + "/100 !");
                                    System.out.println("Hero used a Phoenix! Hero's health is " + map.getHero().getHP() + "/100 !");
                                }

                                continue;
                            }
                        }
                    }
                }else
                    System.out.println("There is a strong aura and mysterious power that is in this area");
            }
            else if(map.isBag(dir) == true){
                map.getHero().setGold(map.getHero().getGold() + map.whichBag(dir).getGold());
                System.out.println("Hero found " + map.whichBag(dir).getGold() + " Gold!");
                map.move(dir);
            }
            else if(!dir.equalsIgnoreCase("p")){
                map.move(dir);
            }
            else
                continue;

            checkMap =  emptyMap(map.getMap());

            if(map.getHero().getHP() <= 0){
                System.out.println("\n* Hero is struck with a deadly blow and collapses *");
                System.out.println("** Monsters in the surrounding area converge on the carcass. **");
                break;
            }else if(emptyMap(map.getMap()) == true){
                System.out.println("\n* Hero has cleansed the land of all the evil! *");
                System.out.println("** The land returns to peace. **");
                break;
            }
        }

    }

    public static boolean checkDir(String dir){
        String[] possibleDir = {"w","a","s","d","p"};

        for(String d : possibleDir){
            if(d.equalsIgnoreCase(dir)){
                return true;
            }
        }

        return false;
    }

    public static boolean checkAction(String action){
        String[] possibleAction = {"1","2","3","4", "5", "6"};

        for(String a : possibleAction){
            if(a.equalsIgnoreCase(action)){
                return true;
            }
        }

        return false;
    }

    public static boolean checkShop(String action){
        String[] possibleAction = {"1","2","3","4", "5", "6","7"};

        for(String a : possibleAction){
            if(a.equalsIgnoreCase(action)){
                return true;
            }
        }

        return false;
    }

    public static boolean checkSLong(int action){
        int[] possibleAction = {1,2,3,4};

        for(int a : possibleAction){
            if(a == action){
                return true;
            }
        }

        return false;
    }

    public static boolean checkSShort(int action){
        int[] possibleAction = {1,2};

        for(int a : possibleAction){
            if(a == action){
                return true;
            }
        }

        return false;
    }

    public static boolean emptyMap(Object map[][]){
        boolean check = true;

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] instanceof String || map[i][j] instanceof Hero || map[i][j] instanceof Farmer || map[i][j] instanceof GoldBag){
                    check = check && true;
                }else
                    check = check && false;
            }
        }

        return check;
    }

    public static boolean thereBoss(Object map[][], Boss B){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] instanceof Boss && map[i][j] == B){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean MonsterProb(){
        int prob = (int) Math.random()*101;
        if(prob <= 10){
            return true;
        }
        else 
            return false;
    }
}