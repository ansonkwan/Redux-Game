public class Armor extends Shop{
    String name;
    
    public Armor(String n){
        super();
        name = n;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public int useArmor(Monster m, String name,String weapon){
        if(name.equals("Trousers")){
            return (int)(m.getDamage(weapon)*.95);
        }
        else if(name.equals("Leather")){
            return (int)(m.getDamage(weapon)*.90);
        }
        else if(name.equals("Chain")){
            return (int)(m.getDamage(weapon)*.85);
        }else if (name.equals("Dragon")){
            return (int)(m.getDamage(weapon)*.7);
        }
        else 
            return m.getDamage(weapon);
    }
    
    public int useArmorBoss(Boss b, String name,String weapon){
        if(name.equals("Trousers")){
            return (int)(b.getDamage()*.95);
        }
        else if(name.equals("Leather")){
            return (int)(b.getDamage()*.85);
        }
        else if(name.equals("Chain")){
            return (int)(b.getDamage()*.75);
        }else if (name.equals("Dragon")){
            return (int)(b.getDamage()*.55);
        }
        else 
            return b.getDamage();
    }
    
    public String toString(){
        return name;
    }
}