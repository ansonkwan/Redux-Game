public class Monster implements InMap{
    private int health;
    private int damage;
    private int speed;
    private int gold;
    private int x;
    private int y;
    private String direction;
    public Monster(String name){
        if(name.equals("Dagger")){
            health = (int)(Math.random() * 41 + 20);
        }
        else if(name.equals("Short Sword")){
            health = (int)(Math.random() * 21 + 50);
        }
        else if(name.equals("Long Sword")){
            health =  (int)(Math.random() * 41 + 100);
        }
        else if(name.equals("Axe")){
            health = (int)(Math.random() * 41 + 140);
        }
        else{
            health = 0;
        }
        if(name.equals("Dagger")){
            damage = (int)(Math.random() * 16 + 5);
        }
        else if(name.equals("Short Sword")){
            damage = (int)(Math.random() * 21 + 10);
        }
        else if(name.equals("Long Sword")){
            damage = (int)(Math.random() * 26 + 20);
        }
        else if(name.equals("Axe")){
            damage = (int)(Math.random() * 26 + 25);
        }
        else{
            damage = 0;
        }     
        if(name.equals("Dagger")){
            gold = (int)(Math.random() * 21 + 5);
        }
        else if(name.equals("Short Sword")){
            gold = (int)(Math.random() * 31 + 10);
        }
        else if(name.equals("Long Sword")){
            gold = (int)(Math.random() * 31 + 15);
        }
        else if(name.equals("Axe")){
            gold = (int)(Math.random() * 31 + 20);
        }
        else{
            gold = 0;
        }
        int dir = (int)(Math.random()*101);
        if(dir <= 25){
            direction = "N";
        }
        else if(dir <= 50 && dir > 25){
            direction = "S";
        }
        else if(dir <= 75 && dir > 50){
            direction = "W";
        }
        else if(dir > 75){
            direction = "E";
        }
        speed = (int)(Math.random() * 3 + 1);
        this.y = y;
        this.x = x;
    }  

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getDamage(String name){
        if(name.equals("Dagger")){
            return (int)(Math.random() * 21 + 5);
        }
        else if(name.equals("Short Sword")){
            return (int)(Math.random() * 31 + 10);
        }
        else if(name.equals("Long Sword")){
            return (int)(Math.random() * 31 + 20);
        }
        else if(name.equals("Axe")){
            return (int)(Math.random() * 31 + 40);
        }
        else{
            return 0;
        }
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getGold(){
        return gold;
    }

    public void setGold(int gold){
        this.gold = gold;
    }
    
    public String getDir(){
        return direction;
    }

    public void setDir(String dir){
        direction = dir;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }    

}