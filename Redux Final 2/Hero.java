public class Hero implements InMap{
    private int health;
    private String weapon;
    private String name;
    private String armor;
    private static boolean satchel,sandels;
    private int x;
    private int y;
    private int gold;
    
    public Hero(int xpos, int ypos, String w, String a){
        health = 100;
        name = "Hero";
        weapon = w;
        armor = a;
        gold = 0;
        satchel = false;
        sandels = false;
        x = xpos;
        y = ypos;
    }
    
    public Hero(){
        
    }
    
    public int getHP(){
        return health;
    }
    
    public void setHP(int HP){
        health = HP;
    }
    
    public String getName(){
        return name;
    }
    
    public String getWeapon(){
        return weapon;
    }
    
    public void setWeapon(String w){
        weapon = w;
    }
    
    public String getArmor(){
        return armor;
    }
    
    public void setArmor(String a){
        armor = a;
    }
    
    public int getX(){
        return x;
    }
    
    public void setX(int xpos){
        x = xpos;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY(int ypos){
        y = ypos;
    }
    
    public int getGold(){
        return gold;
    }
    
    public void setGold(int g){
        gold = g;
    }
    
    public static boolean hasSandels(){
        return sandels;
    }
    
    public static void setSandels(boolean s){
        sandels = s;
    }
    
    public static boolean hasSatchel(){
        return satchel;
    }
    
    public static void setSatchel(boolean s){
        satchel = s;
    }
    
    public String toString(){
        return "H";
    }
}