package pyramid;

import java.util.*;

class Pyramid {

    private void checkForNulls(List<Integer> list) throws CannotBuildPyramidException{
        if(list == null){
            throw new CannotBuildPyramidException("List is null");
        }

        if(list.contains(null)){
            throw new CannotBuildPyramidException("List contains null element(s)");
        }
    }

    private int[][] listToArray(List<Integer> list) throws CannotBuildPyramidException{
        int counter = 0, size = 3, rows = 2, columns = 3;

        do{
            if(list.size() == size){
                return new int[rows][columns];
            }
            size += 3 + counter++;
            rows++;
            columns += 2;

            if(list.size() < size){
                throw new CannotBuildPyramidException("Incorrect number of arguments");
            }
        }
        while(size <= list.size());
        return null;
    }

     int[][] buildPyramid(List<Integer> list) throws CannotBuildPyramidException{
        checkForNulls(list);
        int[][] array = listToArray(list);
        int med = array[0].length / 2, counter = 0;
        Collections.sort(list);

        for (int i = 0; i < array.length; i++) {
            for (int j = med - i; j <= med + i; j += 2) {
                array[i][j] = list.get(counter++);
            }
        }

        return array;
    }
}
