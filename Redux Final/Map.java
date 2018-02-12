import java.util.ArrayList;

public class Map{
    private Object[][] map = new Object[15][15];
    private Hero hero;
    private Inventory hinv;
    private Armor armor;
    private Weapon weapon;
    private ArrayList<Farmer> farmers;
    private Boss B1,B2,B3,B4;
    private Directions D1,D2,D3,D4;
    private Shop shop;
    private FarmerMap fmap;
    private ArrayList<Boss> bosses;
    private GoldBag g;
    private ArrayList<GoldBag> bags;
    private int bagc = 0;
    private int bcount;
    private boolean farmermap,Dir1,Dir2,Dir3,Dir4,sh;

    public Map(){
        boolean setpos;
        int x;
        int y;
        farmers = new ArrayList<Farmer>();
        bosses = new ArrayList<Boss>();
        bags = new ArrayList<GoldBag>();
        farmermap = false;
        Dir1 = false;
        Dir2 = false;
        Dir3 = false;
        Dir4 = false;
        sh = false;
        bcount = 0;

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = " ";
            }
        }

        hero = new Hero(0,map.length - 1,"Dagger","Trousers");
        hinv = new Inventory();
        armor = new Armor(hero.getName());
        weapon = new Weapon(hero.getWeapon());

        map[hero.getY()][hero.getX()] = hero;

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        fmap= new FarmerMap(x,y,"FMap");
        map[fmap.getY()][fmap.getX()] = fmap;
        farmers.add(fmap);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        D1 = new Directions(x,y,"D1");
        map[D1.getY()][D1.getX()] = D1;
        farmers.add(D1);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        D2 = new Directions(x,y,"D2");
        map[D2.getY()][D2.getX()] = D2;
        farmers.add(D2);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        D3 = new Directions(x,y,"D3");
        map[D3.getY()][D3.getX()] = D3;
        farmers.add(D3);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        D4 = new Directions(x,y,"D4");
        map[D4.getY()][D4.getX()] = D4;
        farmers.add(D4);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        shop = new Shop(x,y,"Shop");
        map[shop.getY()][shop.getX()] = shop;
        farmers.add(shop);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        B1= new Boss(x,y,"b1");
        map[B1.getY()][B1.getX()] = B1;
        bosses.add(B1);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        B2= new Boss(x,y,"b2");
        map[B2.getY()][B2.getX()] = B2;
        bosses.add(B2);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        B3= new Boss(x,y,"b3");
        map[B3.getY()][B3.getX()] = B3;
        bosses.add(B3);

        do{
            x = (int)(Math.random()*15);
            y = (int)(Math.random()*15);
            setpos = checkPos(x,y);
        }while(setpos == false);
        B4= new Boss(x,y,"b4");
        map[B4.getY()][B4.getX()] = B4;
        bosses.add(B4);

        while(bcount < 6){
            do{
                x = (int)(Math.random()*15);
                y = (int)(Math.random()*15);
                setpos = checkPos(x,y);
            }while(setpos == false);
            String name = "g" + bcount;
            g = new GoldBag(x,y,name);
            map[g.getY()][g.getX()] = g;
            bags.add(g);
            bcount++;
        }
    }

    public Hero getHero(){
        return hero;
    }

    public Inventory getInv(){
        return hinv;
    }

    public Object[][] getMap(){
        return map;
    }

    public Armor getArmor(){
        return armor;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public void setBcount(int b){
        bcount = b;
    }

    public void setDir1(boolean d){
        Dir1 = d;
    }

    public void setDir2(boolean d){
        Dir2 = d;
    }

    public void setDir3(boolean d){
        Dir3 = d;
    }

    public void setDir4(boolean d){
        Dir4 = d;
    }

    public void setFarmerMap(boolean d){
        farmermap = d;
    }

    public void setShop(boolean d){
        sh = d;
    }

    public int getBcount(){
        return bcount;
    }

    public Directions getDir1(){
        return D1;
    }

    public Directions getDir2(){
        return D2;
    }

    public Directions getDir3(){
        return D3;
    }

    public Directions getDir4(){
        return D4;
    }

    public FarmerMap getFarmerMap(){
        return fmap;
    }

    public Shop getShop(){
        return shop;
    }

    public Boss getB1(){
        return B1;
    }

    public Boss getB2(){
        return B2;
    }

    public Boss getB3(){
        return B3;
    }

    public Boss getB4(){
        return B4;
    }

    public boolean checkPos(int x, int y){
        if(map[y][x] instanceof String){
            return true;
        }else
            return false;
    }

    public boolean isFarmer(String direction){
        for(Farmer f: farmers){
            if(direction.equals("w")){
                if(((hero.getY() - 1) == f.getY()) && (hero.getX() == f.getX()) && map[f.getY()][f.getX()] instanceof Farmer){
                    return true;
                }
            }else if(direction.equals("s")){
                if(((hero.getY() + 1) == f.getY()) && (hero.getX() == f.getX()) && map[f.getY()][f.getX()] instanceof Farmer){
                    return true;
                }
            }else if(direction.equals("d")){
                if((hero.getY() == f.getY()) && ((hero.getX() + 1) == f.getX()) && map[f.getY()][f.getX()] instanceof Farmer){
                    return true;
                }
            }else if(direction.equals("a")){
                if((hero.getY() == f.getY()) && ((hero.getX() - 1) == f.getX()) && map[f.getY()][f.getX()] instanceof Farmer){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBag(String direction){
        for(GoldBag f: bags){
            if(direction.equals("w")){
                if(((hero.getY() - 1) == f.getY()) && (hero.getX() == f.getX()) && map[f.getY()][f.getX()] instanceof GoldBag){
                    return true;
                }
            }else if(direction.equals("s")){
                if(((hero.getY() + 1) == f.getY()) && (hero.getX() == f.getX()) && map[f.getY()][f.getX()] instanceof GoldBag){
                    return true;
                }
            }else if(direction.equals("d")){
                if((hero.getY() == f.getY()) && ((hero.getX() + 1) == f.getX()) && map[f.getY()][f.getX()] instanceof GoldBag){
                    return true;
                }
            }else if(direction.equals("a")){
                if((hero.getY() == f.getY()) && ((hero.getX() - 1) == f.getX()) && map[f.getY()][f.getX()] instanceof GoldBag){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBoss(String direction){
        for(Boss b: bosses){
            if(direction.equals("w")){
                if(((hero.getY() - 1) == b.getY()) && (hero.getX() == b.getX()) && map[b.getY()][b.getX()] instanceof Boss){
                    return true;
                }
            }else if(direction.equals("s")){
                if(((hero.getY() + 1) == b.getY()) && (hero.getX() == b.getX()) && map[b.getY()][b.getX()] instanceof Boss){
                    return true;
                }
            }else if(direction.equals("d")){
                if((hero.getY() == b.getY()) && ((hero.getX() + 1) == b.getX()) && map[b.getY()][b.getX()] instanceof Boss){
                    return true;
                }
            }else if(direction.equals("a")){
                if((hero.getY() == b.getY()) && ((hero.getX() - 1) == b.getX()) && map[b.getY()][b.getX()] instanceof Boss){
                    return true;
                }
            }
        }

        return false;
    }

    public Boss whichBoss(String direction){
        for(Boss b: bosses){
            if(direction.equals("w")){
                if(((hero.getY() - 1) == b.getY()) && (hero.getX() == b.getX()) && map[b.getY()][b.getX()] instanceof Boss){
                    return b;
                }
            }else if(direction.equals("s")){
                if(((hero.getY() + 1) == b.getY()) && (hero.getX() == b.getX()) && map[b.getY()][b.getX()] instanceof Boss){
                    return b;
                }
            }else if(direction.equals("d")){
                if((hero.getY() == b.getY()) && ((hero.getX() + 1) == b.getX()) && map[b.getY()][b.getX()] instanceof Boss){
                    return b;
                }
            }else if(direction.equals("a")){
                if((hero.getY() == b.getY()) && ((hero.getX() - 1) == b.getX()) && map[b.getY()][b.getX()] instanceof Boss){
                    return b;
                }
            }
        }
        return null;
    }

    public GoldBag whichBag(String direction){
        for(GoldBag b: bags){
            if(direction.equals("w")){
                if(((hero.getY() - 1) == b.getY()) && (hero.getX() == b.getX()) && map[b.getY()][b.getX()] instanceof GoldBag){
                    return b;
                }
            }else if(direction.equals("s")){
                if(((hero.getY() + 1) == b.getY()) && (hero.getX() == b.getX()) && map[b.getY()][b.getX()] instanceof GoldBag){
                    return b;
                }
            }else if(direction.equals("d")){
                if((hero.getY() == b.getY()) && ((hero.getX() + 1) == b.getX()) && map[b.getY()][b.getX()] instanceof GoldBag){
                    return b;
                }
            }else if(direction.equals("a")){
                if((hero.getY() == b.getY()) && ((hero.getX() - 1) == b.getX()) && map[b.getY()][b.getX()] instanceof GoldBag){
                    return b;
                }
            }
        }
        return null;
    }

    public Farmer whichFarmer(String direction){
        for(Farmer b: farmers){
            if(direction.equals("w")){
                if(((hero.getY() - 1) == b.getY()) && (hero.getX() == b.getX()) && map[b.getY()][b.getX()] instanceof Farmer){
                    return b;
                }
            }else if(direction.equals("s")){
                if(((hero.getY() + 1) == b.getY()) && (hero.getX() == b.getX()) && map[b.getY()][b.getX()] instanceof Farmer){
                    return b;
                }
            }else if(direction.equals("d")){
                if((hero.getY() == b.getY()) && ((hero.getX() + 1) == b.getX()) && map[b.getY()][b.getX()] instanceof Farmer){
                    return b;
                }
            }else if(direction.equals("a")){
                if((hero.getY() == b.getY()) && ((hero.getX() - 1) == b.getX()) && map[b.getY()][b.getX()] instanceof Farmer){
                    return b;
                }
            }
        }
        return null;
    }

    public String toString(){
        String format = "";
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] instanceof Hero){
                    format = format + map[i][j].toString() + "  ";
                }
                else if(map[i][j]  instanceof String){
                    format = format + "*" + "  ";
                }
                else if(map[i][j]  instanceof FarmerMap){
                    if((farmermap == true && map[i][j].toString().equals("FMap")) || (square(i,j) == true && map[i][j].toString().equals("FMap"))){
                        format = format + "F" + "  ";
                    }else
                        format = format + "*" + "  ";
                }
                else if(map[i][j] instanceof Directions){
                    if((Dir1 == true && map[i][j].toString().equals("D1")) || (square(i,j) == true && map[i][j].toString().equals("D1"))){
                        format = format + "F" + "  ";
                    }else if(Dir2 == true && map[i][j].toString().equals("D2") || (square(i,j) == true && map[i][j].toString().equals("D2"))){
                        format = format + "F" + "  ";
                    }else if(Dir3 == true && map[i][j].toString().equals("D3") || (square(i,j) == true && map[i][j].toString().equals("D3"))){
                        format = format + "F" + "  ";
                    }else if(Dir4 == true && map[i][j].toString().equals("D4") || (square(i,j) == true && map[i][j].toString().equals("D4"))){
                        format = format + "F" + "  ";
                    }else
                        format = format + "*" + "  ";
                }
                else if(map[i][j]  instanceof Shop){
                    if((sh == true && map[i][j] .toString().equals("Shop")) || (square(i,j) == true && map[i][j].toString().equals("Shop"))){
                        format = format + "F" + "  ";
                    }else
                        format = format + "*" + "  ";
                }
                else if(map[i][j]  instanceof Boss){
                    if(bcount == 1 && map[i][j].toString().equals("b1")){
                        format = format + "B" + "  ";
                    }
                    else if(bcount == 2 && map[i][j].toString().equals("b2")){
                        format = format + "B" + "  ";
                    }
                    else if(bcount == 3 && map[i][j].toString().equals("b3")){
                        format = format + "B" + "  ";
                    }
                    else if(bcount == 4 && map[i][j].toString().equals("b4")){
                        format = format + "B" + "  ";
                    }
                    else
                        format = format + "*" + "  ";
                }
                else if(map[i][j]  instanceof GoldBag){
                    if((square(i,j) == true)){
                        format = format + "G" + "  ";
                    }
                    else
                        format = format + "*" + "  ";
                }
            }
            format = format + "\n";
        }

        return format;
    }

    public boolean checkPossibleHeroPos(String direction){
        if(direction.equalsIgnoreCase("s")){
            if(((hero.getY() + 1) < 15 && hero.getY() >= 0) && (hero.getX() < 15 && hero.getX() >= 0)){
                return true;
            }
        }else if(direction.equalsIgnoreCase("w")){
            if((hero.getY() < 15 && (hero.getY() - 1) >= 0) && (hero.getX() < 15 && hero.getX() >= 0)){
                return true;
            }
        }else if(direction.equalsIgnoreCase("d")){
            if((hero.getY() < 15 && hero.getY() >= 0) && ((hero.getX() + 1) < 15 && hero.getX() >= 0)){
                return true;
            }
        }else if(direction.equalsIgnoreCase("a")){
            if((hero.getY() < 15 && hero.getY() >= 0) && (hero.getX() < 15 && (hero.getX() - 1) >= 0)){
                return true;
            }
        }else if(direction.equalsIgnoreCase("p")){
            return true;
        }

        return false;  
    }

    public void move(String direction){
        if(direction.equals("s")){
            hero.setY(hero.getY() + 1);
            map[hero.getY()][hero.getX()] = hero;
            map[hero.getY() - 1][hero.getX()] = " ";
        }else if(direction.equals("w")){
            hero.setY(hero.getY() - 1);
            map[hero.getY()][hero.getX()] = hero;
            map[hero.getY() + 1][hero.getX()] = " ";
        }else if(direction.equals("d")){
            hero.setX(hero.getX() + 1);
            map[hero.getY()][hero.getX()] = hero;
            map[hero.getY()][hero.getX() - 1] = " ";
        }else if(direction.equals("a")){
            hero.setX(hero.getX() - 1);
            map[hero.getY()][hero.getX()] = hero;
            map[hero.getY()][hero.getX() + 1] = " ";
        }
    }

    public boolean run(int speed, String d){
        int probability = (int)(Math.random()*101);
        if(speed == 0 && d.equals("S")){
            if(probability <= 75){
                return true;
            }else
                return false;
        }
        else if(speed == 1 && d.equals("S")){
            if(probability <= 50){
                return true;
            }else
                return false;
        }
        else if(speed == 2 && d.equals("S")){
            if(probability <= 25){
                return true;
            }else
                return false;
        }
        else if(speed == 3 && d.equals("S")){
            if(probability <= 10){
                return true;
            }else
                return false;
        }

        if(speed == 0 && d.equals("N")){
            if(probability <= 100){
                return true;
            }else
                return false;
        }
        else if(speed == 1 && d.equals("N")){
            if(probability <= 75){
                return true;
            }else
                return false;
        }
        else if(speed == 2 && d.equals("N")){
            if(probability <= 50){
                return true;
            }else
                return false;
        }
        else if(speed == 3 && d.equals("N")){
            if(probability <= 30){
                return true;
            }else
                return false;
        }

        if(speed == 0 && (d.equals("E") || d.equals("W"))){
            if(probability <= 85){
                return true;
            }else
                return false;
        }
        else if(speed == 1 && (d.equals("E") || d.equals("W")) ){
            if(probability <= 60){
                return true;
            }else
                return false;
        }
        else if(speed == 2 && (d.equals("E") || d.equals("W"))){
            if(probability <= 30){
                return true;
            }else
                return false;
        }
        else if(speed == 3 && (d.equals("E") || d.equals("W"))){
            if(probability <= 15){
                return true;
            }else
                return false;
        }
        return false;
    }

    public boolean miss(int speed, String d){
        int probability = (int)(Math.random()*101);
        if(speed == 0 && d.equals("S")){
            if(probability <= 5){
                return true;
            }else
                return false;
        }
        else if(speed == 1 && d.equals("S")){
            if(probability <= 7){
                return true;
            }else
                return false;
        }
        else if(speed == 2 && d.equals("S")){
            if(probability <= 10){
                return true;
            }else
                return false;
        }
        else if(speed == 3 && d.equals("S")){
            if(probability <= 12){
                return true;
            }else
                return false;
        }

        if(speed == 0 && d.equals("N")){
            if(probability < 0){
                return true;
            }else
                return false;
        }
        else if(speed == 1 && d.equals("N")){
            if(probability <= 5){
                return true;
            }else
                return false;
        }
        else if(speed == 2 && d.equals("N")){
            if(probability <= 7){
                return true;
            }else
                return false;
        }
        else if(speed == 3 && d.equals("N")){
            if(probability <= 8){
                return true;
            }else
                return false;
        }

        if(speed == 0 && (d.equals("E") || d.equals("W"))){
            if(probability <= 3){
                return true;
            }else
                return false;
        }
        else if(speed == 1 && (d.equals("E") || d.equals("W")) ){
            if(probability <= 6){
                return true;
            }else
                return false;
        }
        else if(speed == 2 && (d.equals("E") || d.equals("W"))){
            if(probability <= 8){
                return true;
            }else
                return false;
        }
        else if(speed == 3 && (d.equals("E") || d.equals("W"))){
            if(probability <= 10){
                return true;
            }else
                return false;
        }
        return false;
    }
    
    public boolean square(int frow, int fcolum){
        int xdiff = Math.abs(fcolum - hero.getX());
        int ydiff = Math.abs(frow - hero.getY());
        double dist = Math.sqrt(Math.pow(xdiff,2) + Math.pow(ydiff,2));
        if(xdiff <= 2 && ydiff <= 2){
            return true;
        }
        else if(dist <= Math.sqrt(8)){
            return true;
        }
        return false;
    }
}