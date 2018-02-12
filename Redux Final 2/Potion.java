public class Potion extends Shop{
    private int x;
    private int y;
    private String name;
    
    Potion(String n){
        super();
        name = n;
    }
    
    public void usePot(Hero h){
        if(name.equals("Small Poion")){
            h.setHP(h.getHP() + 50);
        }
        else if(name.equals("Big Potion")){
            h.setHP(100);
        }
        else if(name.equals("Phoenix")){
            h.setHP(100);
        }
    }
    
    public String toString(){
        return name;
    }
}