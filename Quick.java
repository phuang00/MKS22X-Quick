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
    int[] tempAry = new int[] {start, end, data[data.length/2]};
    // make int array of the start, end, and middle value
    Arrays.sort(tempAry);
    // sort the array
    int index = tempAry[1];
    // index is set to the median of start, end and middle value
    int pivot = data[index];
    // pivot is set to the int at the index index of data
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

   private static boolean parts(int index, int[] data, int start, int end){
     int pivot = data[index];
     for (int i = start; i < end + 1; i++){
       if ((i < index && data[i] > pivot) || (i > index && data[i] < pivot)){
         return false;
       }
     }
     return true;
   }

   /*Modify the array to be in increasing order.
   */
   public static void quicksort(int[] data){
     quickH(data, 0, data.length - 1);
   }

   private static void quickH(int[] data, int start, int end){
     if (start != end){
       int ans = partition(data, start, end);
       quickH(data, start, ans - 1);
       quickH(data, ans + 1, end);
     }
   }

   public static void main(String[] args) {
     int[] array = new int[] {0,999,999,999,4,1,0,3,2,999,999,999};
     System.out.println(Arrays.toString(array));
     //System.out.println(parts(partition(array, 0, 10), array, 0, 10));
     for (int i = 0; i < array.length; i++){
       System.out.println("term " + i + ": "+ quickselect(array, i));
     }

     System.out.println();

     array = new int[] {4,19,6};
     System.out.println(Arrays.toString(array));
     for (int i = 0; i < array.length; i++){
       System.out.println("term " + i + ": "+ quickselect(array, i));
     }

     System.out.println();

     array = new int[] {40,-19, 39, 5,20,390,239};
     System.out.println(Arrays.toString(array));
     for (int i = 0; i < array.length; i++){
       System.out.println("term " + i + ": "+ quickselect(array, i));
     }

     System.out.println();

     array = new int[] {4,19,6};
     System.out.println(Arrays.toString(array));
     quicksort(array);
     System.out.println(Arrays.toString(array));

     System.out.println();

     array = new int[] {40,-19, 39, 5,20,390,239};
     System.out.println(Arrays.toString(array));
     quicksort(array);
     System.out.println(Arrays.toString(array));

     System.out.println();

     array = new int[] {0,999,999,999,4,1,0,3,2,999,999,999};
     System.out.println(Arrays.toString(array));
     quicksort(array);
     System.out.println(Arrays.toString(array));

     System.out.println();

     array = new int[] {0,0,0,0,1,2,3,3,3,3,4,5,2,8,6};
     System.out.println(Arrays.toString(array));
     quicksort(array);
     System.out.println(Arrays.toString(array));

   }

}
