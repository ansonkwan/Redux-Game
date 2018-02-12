public class Directions extends Farmer{
    private int x;
    private int y;
    private String name;
    
    Directions(int xpos,int ypos, String n){
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
    
    public String toString(){
        return name;
    }
}