public class Boss implements InMap{
    private int health;
    private int damage;
    private int speed;
    private int gold;
    private int x;
    private int y;
    private String name;
    public Boss(int xpos, int ypos, String name){
        x = xpos;
        y = ypos;
        this.name = name;
        if(name.equals("b1")){
            health = 100;
            damage = 30;
            speed = 4;
            gold = 50;
        }
        else if(name.equals("b2")){
            health = 250;
            damage = 40;
            speed = 4;
            gold = 100;
        }
        else if(name.equals("b3")){
            health = 500;
            damage = 50;
            speed = 4;
            gold = 200;
        }
        else if(name.equals("b4")){
            health = 800;
            damage = 65;
            speed = 4;
            gold = 400;
        }
        else {
            health = 0;
            damage = 0;
            speed = 0;
            gold = 0;
        }
    }
    
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health = health;
    }

    public int getDamage(){
        return damage;
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
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        return name;
    }
}