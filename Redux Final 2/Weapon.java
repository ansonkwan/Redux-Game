public class Weapon extends Shop{
    String name;
    
    public Weapon(String n){
        super();
        name = n;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public int useWeapon(String name){
        if(name.equals("Dagger")){
            return (int)(Math.random()*21 + 10);
        }
        else if(name.equals("Short Sword")){
            return (int)(Math.random()*21 + 30);
        }
        else if(name.equals("Long Sword")){
            return (int)(Math.random()*21 + 50);
        }
        else if(name.equals("Axe"))
            return (int)(Math.random()*31 + 80);
            
        return 0;
    }
    
    public String toString(){
        return name;
    }
    
}