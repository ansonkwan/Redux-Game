public class Bomb extends Shop{
    String name;
    
    Bomb(String n){
        super();
        name = n;
    }
    
    public String getName(){
        return name;
    }
    
    public int useBomb(int MHP){
        MHP = 0;
        return MHP;
    }
    
    public String toString(){
        return "Bomb";
    }
}