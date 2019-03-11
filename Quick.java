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
  private static int partition(int [] data, int start, int end){
    int index = (int)(Math.random() * (end - start + 1)) + start;
    // index is set to random index within the range start and end, inclusive
    int pivot = data[index];
    // pivot is set to the int at the index index of data
    data[index] = data[0];
    data[0] = pivot;
    // pivot swaps places with the value at index 0 of data
    start++;
    // start is increased by one
    while (start != end){
      // while start is not equal to end
      if (data[start] > pivot){
        // if the value at start is greater than pivot
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
    if (data[start] < pivot){
      // if value at start is less than pivot
      data[0] = data[start];
      data[start] = pivot;
      // pivot and the value at start swap places

      //System.out.println(Arrays.toString(data));
      //System.out.println("pivot:" + pivot);
      return start;
      // return start, the index of pivot
    }
    else{
      // else
      data[0] = data[start - 1];
      data[start - 1] = pivot;
      // pivot and the value at index (start - 1) swap places

      //System.out.println(Arrays.toString(data));
      //System.out.println("pivot:" + pivot);
      return start - 1;
      // return start - 1, the index of pivot
    }
  }

    /*return the value that is the kth smallest value of the array.
   */
   public static int quickselect(int []data, int k){

   }

}
