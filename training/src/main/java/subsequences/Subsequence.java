package subsequences;

import java.util.List;

class Subsequence {

    boolean find(List x, List y){
        if(x.isEmpty()){
            return true;
        }

        int counter = 0;

        for(int i = 0; i < y.size(); i++){
            if(x.size() == counter){
                return true;
            }

            if(x.size() - counter > y.size() - i){
                return false;
            }

            if(areEquals(y.get(i), x.get(counter))){
                counter++;
            }
        }
        return counter == x.size();
    }

    private boolean areEquals(Object object1, Object object2){
        if(object1 == null){
            return object2 == null;
        }
        else return object1.equals(object2);
    }
}
