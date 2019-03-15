import java.util.*;

public class Quick{

    /*Choose a random pivot element between the start and end index inclusive,
   Then modify the array such that:
   *1. Only the indices from start to end inclusive are considered in range
   *2. A random index from start to end inclusive is chosen, the corresponding
   *   element is designated the pivot element.
   *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
   *4. all elements in range that are larger than the pivot element are placed after the pivot element.
   *@return the index of the final position of the pivot element.
   */
  private static int partition(int[] data, int start, int end){
    if (start == end) return start;
    // if start and end are the same (meaning nothing is to be sorted), return start
    int[] tempAry = new int[] {data[start], data[end], data[data.length/2]};
    // make int array of the start, end, and middle value
    Arrays.sort(tempAry);
    // sort the array
    int pivot = tempAry[1];
    // pivot is set to the medium
    int index = data.length/2;
    if (data[start] == pivot){
      index = start;
    }
    else if (data[end] == pivot){
      index = end;
    }
    data[index] = data[start];
    data[start] = pivot;
    // pivot swaps places with the value at index start of data
    index = start;
    start++;
    // start is increased by one
    Random rand = new Random();
    // initialize random number generator
    while (start != end){
      // while start is not equal to end
      if (data[start] > pivot || (data[start] == pivot && rand.nextInt() % 2 == 0)){
        // if the value at start is greater than pivot or
        // the value at start is equal to pivot and the randomly generated int is multiple of 2 (50% chance)
        int temp = data[start];
        // value at index start is stored in a temp variable
        data[start] = data[end];
        data[end] = temp;
        // value at start switch places with value at end
        end--;
        // end is decreased by one
      }
      else start++;
      // else if value at start is less than pivot, increase start by one
    }
    //System.out.println(start);
    //System.out.println(pivot);
    //System.out.println(Arrays.toString(data));
    if (data[start] < pivot){
      // if value at start is less than or equal to pivot
      data[index] = data[start];
      data[start] = pivot;
      // pivot and the value at start swap places

      //System.out.println(Arrays.toString(data));
      //System.out.println("pivot:" + pivot);
      return start;
      // return start, the index of pivot
    }
    else{
      // else
      data[index] = data[start - 1];
      data[start - 1] = pivot;
      // pivot and the value at index (start - 1) swap places

      //System.out.println(Arrays.toString(data));
      //System.out.println("pivot:" + pivot);
      return start - 1;
      // return start - 1, the index of pivot
    }
  }

  /*return the value that is the kth smallest value of the array. k=0 is the smallest
   */
   public static int quickselect(int[] data, int k){
     int start = 0;
     int end = data.length - 1;
     // set start to index 0 and end to last index
     //System.out.println(end);
     int ans = partition(data, start, end);
     // temp ans or pivot is equal to the return value after partitioning it once
     while (ans != k){
       // while ans is not equal to k
       //System.out.println("index : " + ans);
       //System.out.println("data : " + Arrays.toString(data));
       if (ans > k){
         // if ans is greater than k
         end = ans - 1;
         // set end (upper bound) to one less than ans
       }
       else{
         // else (if ans is less than k)
         start = ans + 1;
         // set start (lower bound) to one more than ans
       }
       //System.out.println("start " + start + " end " + end);
       ans = partition(data,start,end);
       // call partition again with new start/end values and set the return value to ans
     }
     return data[ans];
     // return the value at index ans
   }

   /*private static boolean parts(int index, int[] data, int start, int end){
     int pivot = data[index];
     for (int i = start; i < end + 1; i++){
       if ((i < index && data[i] > pivot) || (i > index && data[i] < pivot)){
         return false;
       }
     }
     return true;
   }*/

   /*Modify the array to be in increasing order.
   */
   public static void oldQuicksort(int[] data){
     oldQuickH(data, 0, data.length - 1);
   }

   private static void oldQuickH(int[] data, int start, int end){
     if (start >= end){
       return;
     }
     int ans = partition(data, start, end);
     //System.out.println("start : " + start + "   end: " + end);
     //System.out.println(Arrays.toString(data));
     //System.out.println("ans: " + ans);
     oldQuickH(data, start, ans - 1);
     oldQuickH(data, ans + 1, end);
   }

   private static int[] partitionDutch(int[] data, int lo, int hi){
     int lt = lo;
     //set lt to the lowest index
     int gt = hi;
     // set gt to the highest index
     int index = (int)(Math.random() * (hi - lo + 1)) + lo;
     // index of pivot would be a randomly generated int between lo and hi
     int pivot = data[index];
     // set pivot to the value at the index
     data[index] = data[lo];
     data[lo] = pivot;
     // swap pivot with the value at lo
     //System.out.println("pivot: " + pivot);
     int i = lt + 1;
     // start comparing values from index lt + 1
     while (i <= gt){
       // while the index being checked is less than or equal to gt
       //System.out.println("lt: " + lt + "\ti: " + i + "\tgt" + gt + "\t" + Arrays.toString(data));
       if (data[i] < pivot){
         // if the value is less than pivot
         data[lt] = data[i];
         data[i] = pivot;
         // swap the value at the current index with the first one of the duplicate pivots
         lt++;
         i++;
         // increase current index (i) and lt by one
       }
       else if (data[i] == pivot){
         // else if the value is equal to pivot, increase i by one
         i++;
       }
       else{
         // else (if the value is greater than pivot)
         int temp = data[gt];
         data[gt] = data[i];
         data[i] = temp;
         // swap the value at gt with the value at i
         gt--;
         // decrease gt by one
       }
     }
     //System.out.println("lt: " + lt + "\ti: " + i + "\tgt" + gt + "\t" + Arrays.toString(data));
     return new int[] {lt, gt};
     //return the array [lt,gt]
   }

   public static void quicksort(int[] data){
     quickH(data, 0, data.length - 1);
     // call helper method
   }

   private static void quickH(int[] data, int lo, int hi){
     if (lo >= hi){
       //if the lower index is less than or equal to the higher index, do nothing
       return;
     }
     int[] pivots = partitionDutch(data, lo, hi);
     // pivots array is equal to the return values of Dutch partition
     quickH(data, lo, pivots[0] - 1);
     // call function itself with upper bound being one less than the gt index given by pivots
     quickH(data, pivots[1] + 1, hi);
     // call function itself with lower bound being one more than the lt index given by pivots
   }

   public static void main(String[]args){
     System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
     int[]MAX_LIST = {1000000000,500,10};
     for(int MAX : MAX_LIST){
       for(int size = 31250; size < 2000001; size*=2){
         long qtime=0;
         long btime=0;
         //average of 5 sorts.
         for(int trial = 0 ; trial <=5; trial++){
           int []data1 = new int[size];
           int []data2 = new int[size];
           for(int i = 0; i < data1.length; i++){
             data1[i] = (int)(Math.random()*MAX);
             data2[i] = data1[i];
           }
           long t1,t2;
           t1 = System.currentTimeMillis();
           Quick.quicksort(data2);
           t2 = System.currentTimeMillis();
           qtime += t2 - t1;
           t1 = System.currentTimeMillis();
           Arrays.sort(data1);
           t2 = System.currentTimeMillis();
           btime+= t2 - t1;
           if(!Arrays.equals(data1,data2)){
             System.out.println("FAIL TO SORT!");
             System.exit(0);
           }
         }
         System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
       }
       System.out.println();
     }
     int[] list = new int[1000000];
     int[] ary = new int[1000000];
     for (int i = 0; i < list.length; i++){
       list[i] = 1000000 - i;
       ary[i] = list[i];
     }
     long t1 = System.currentTimeMillis();
     quicksort(list);
     long t2 = System.currentTimeMillis();
     long qtime = t2-t1;
     t1 = System.currentTimeMillis();
     Arrays.sort(ary);
     t2 = System.currentTimeMillis();
     long btime = t2-t1;
     System.out.println(1.0*qtime/btime);
   }

}
