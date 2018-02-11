public class Satchel extends Shop{
    String name;
    
    Satchel(String n){
        super();
        name = n;
    }
    
        public String getName(){
        return name;
    }
    
    public void useSatchel(){
        Hero.setSatchel(true);
    }
    
    public String toString(){
        return "Satchel";
    }
}