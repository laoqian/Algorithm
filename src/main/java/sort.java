import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by zuoan on 2017/3/31.
 */





public class sort {

    public static void main(String[] args) {
        quickSort quickSort = new quickSort(30);

        quickSort.qSort();
    }
}


class quickSort{
   Integer[] array;

   public  quickSort(Integer len){
        this.array = arrayInit(len);
         printArray(this.array);
   }

   public void qSort(){
       this.array = sort(this.array);
       printArray(this.array);
   }


   public Integer[] arrayInit(Integer len){
        Random rand  = new Random();
        Integer[]  ints = new Integer[len];

        for ( int i = 0; i < ints.length; i++ ) {
            ints[i] = rand.nextInt(100);
        }

        return ints;
    }

    public void printArray(Integer[] array){

        String s="";

        for ( int i = 0; i < array.length; i++ ) {
            Integer integer = array[i];
            s+=integer+" ";
        }

        System.out.println(s);
    }

    public Integer[] sort(Integer[] array){
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();

        for ( int i = 1; i < array.length; i++ ){
            if(array[i]<array[0]){
                left.add(array[i]);
            }else{
                right.add(array[i]);
            }
        }

        left.add(array[0]);

        Integer[] l1,r1=null;

        if(left.size()>2){
            Integer[] l = left.toArray(new Integer[left.size()]);
            printArray(l);
             l1 = sort(l);

        }else{
            l1 = left.toArray(new Integer[left.size()]);
        }

        if(right.size()>0){
            Integer[] r = right.toArray(new Integer[right.size()]);
            printArray(r);
            r1 = sort(r);
        }

        List<Integer> result = new ArrayList();

        if(l1!=null){
            result.addAll(Arrays.asList(l1));
        }

        if(r1!=null){
            result.addAll(Arrays.asList(r1));
        }

        return result.toArray(new Integer[result.size()]);
    }
}