public class FarmerMap extends Farmer{
    private int x;
    private int y;
    private String name;
    
    FarmerMap(int xpos,int ypos, String n){
        x = xpos;
        y = ypos;
        name = n;
    }
    
    public String getName(){
        return name;
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
    
    public void useFMap(Map map){
        map.setDir1(true);
        map.setDir2(true);
        map.setDir3(true);
        map.setDir4(true);
        map.setFarmerMap(true);
        map.setShop(true);
    }
    
    public String toString(){
        return name;
    }
}