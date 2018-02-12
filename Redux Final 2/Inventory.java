public class Inventory extends Hero{
    private int amountBomb;
    private int amountSmallPotion;
    private int amountBigPotion;
    private int amountPhoenix;
    private static int x;
    private static int y;
    
    Inventory(){
        super();
        amountBomb = 0;
        amountSmallPotion = 0;
        amountBigPotion = 0;
        amountPhoenix = 0;
    }
    
    public boolean addInventory(int  input){
        if(Hero.hasSatchel() == true){
            if(input == 1){
                amountSmallPotion++;
                if(amountSmallPotion + amountBigPotion + amountBomb + amountPhoenix > 10){
                    amountSmallPotion--;
                    return false;
                }            
                else{
                    return true;
                }
            }
            else if(input == 2){
                amountBigPotion++;
                if(amountSmallPotion + amountBigPotion + amountBomb + amountPhoenix > 10){
                    amountBigPotion--;
                    return false;
                }
                else{
                    return true;
                }       
            }
            else if(input == 3){
                amountPhoenix++;
                if(amountSmallPotion + amountBigPotion + amountBomb + amountPhoenix > 10){
                    amountPhoenix--;
                    return false;
                }
                else{
                    return true;
                }                 
            }
            else if(input == 4){
                amountBomb++;
                if(amountSmallPotion + amountBigPotion + amountBomb + amountPhoenix > 10){
                    amountBomb--;
                    return false;
                }
                else{
                    return true;
                }  
            }
        }
        else if(Hero.hasSatchel() == false){
            if(input == 1){
                amountSmallPotion++;
                if(amountSmallPotion + amountBigPotion + amountBomb + amountPhoenix > 2){
                    amountSmallPotion--;
                    return false;
                }            
                else{
                    return true;
                }
            }
            else if(input == 2){
                amountBigPotion++;
                if(amountSmallPotion + amountBigPotion + amountBomb + amountPhoenix > 2){
                    amountBigPotion--;
                    return false;
                }
                else{
                    return true;
                }       
            }
            else if(input == 3){
                amountPhoenix++;
                if(amountSmallPotion + amountBigPotion + amountBomb + amountPhoenix > 2){
                    amountPhoenix--;
                    return false;
                }
                else{
                    return true;
                }                 
            }
            else if(input == 4){
                amountBomb++;
                if(amountSmallPotion + amountBigPotion + amountBomb + amountPhoenix > 2){
                    amountBomb--;
                    return false;
                }
                else{
                    return true;
                }  
            }
        }
        return true;
    }

    public int getAmountBomb(){
        return amountBomb;   
    }
    public int getAmountSmallPotion(){
        return amountSmallPotion;   
    }
    public int getAmountBigPotion(){
        return amountBigPotion;   
    }
    public int getAmountPhoenix(){
        return amountPhoenix;   
    }    
    
    public void useAmountBomb(){
        amountBomb--;
    }
    public void useAmountSmallPotion(){
        amountSmallPotion--;   
    }
    public void useAmountBigPotion(){
        amountBigPotion--;   
    }
    public void useAmountPhoenix(){
        amountPhoenix--;   
    }
    
    public String toString(){
        return "Small Potion:" + amountSmallPotion + "\tBig Potion:" + amountBigPotion + "\tPhoenix:" + amountPhoenix + "\tBomb:" + amountBomb;
    }
}    