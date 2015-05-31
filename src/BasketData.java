
import java.util.ArrayList;

class BasketData {
    ArrayList<Integer> keys;
    ArrayList<Integer> quantities;
    int maxProducts = 5; // did this initially to allow product quantity to be changed in update stock, 
                         //but is not possible because of the way I did the buttons.
    BasketData() {

    keys = new ArrayList();
    quantities = new ArrayList();
    }
    
    void update(String keyString, int quantityAdded) {
        int key = Integer.parseInt(keyString);
         boolean found = false;
                    for (int i = 0; i < keys.size(); ++i) {
                        if(keys.get(i).equals(0) && quantities.get(i).equals(0)){
                            found = true;
//                            int qInBasket = quantities.get(i);
//
//                            qInBasket += quantityAdded;
//                            quantities.set(i, qInBasket);
                            keys.set(i, key);
                        quantities.set(i, quantityAdded);
                        }else if (keys.get(i).equals(key)) {
                            found = true;
                            int qInBasket = quantities.get(i);

                            qInBasket += quantityAdded;
                            quantities.set(i, qInBasket);
                        }
                    }
                    if (!found) {

                        keys.add(key);
                        quantities.add(quantityAdded);
                    }
    }
    
    public ArrayList getKeys(){
        
        return keys;
    }
    
    public ArrayList getQuantities(){
        
        return quantities;
    }
    
    public int getMaxProducts(){
        return maxProducts;
    }
    
    public int keyActualSize(){
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < keys.size(); ++i){
            ++a;
        if (keys.get(i).equals(0)){
            ++b;
            
            
        } else {
            
        }
    }
        c = a - b;
        
        return c;
    }
    
    void empty() {
        keys.clear();
        quantities.clear();
    }
    
    public int getBasketQuantity(int key){
        int qInBasket = 0;
        int arrayKey = 0;
        for (int i = 0; i < keys.size(); ++i){
            arrayKey = keys.get(i);
            
            if ( arrayKey == key) {
                
                
                qInBasket = quantities.get(i);
            }
        }

        System.out.println("this is basketData " + qInBasket +" "+key);
        return qInBasket;
    }
  
}
    
   