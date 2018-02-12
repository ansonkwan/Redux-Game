public class Sandels extends Shop{
    String name;
    
    Sandels(String n){
        super();
        name = n;
    }
    
        public String getName(){
        return name;
    }
    
    public void useSandels(){
        Hero.setSandels(true);
    }
    
    public String toString(){
        return "Sandels";
    }
}
