public class GoldBag implements InMap{
    int x;
    int y;
    int gold;
    String name;
    
    GoldBag(int xpos, int ypos, String name){
        x = xpos;
        y = ypos;
        gold = (int)(Math.random()*16 + 5);
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
}
