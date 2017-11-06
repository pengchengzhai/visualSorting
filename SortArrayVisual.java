import java.awt.Color;


public class SortArrayVisual {
	private BarChartComponent chart;
	public SortArrayVisual(BarChartComponent chart){
		this.chart = chart;
	}
	
	private void updateGUI(){
        try {
        	Thread.sleep(1);
            chart.repaint();
			chart.wait();
		} catch (Exception e) {
		}

	}
	
	private void resetDataItems(DataItem[] data){
		for(int i=0; i<data.length; i++){
			data[i].setColor(Color.GRAY);
		}
	}

	public void verifySort(DataItem[] data){
		resetDataItems(data);
		for(int i=0; i<data.length-1; i++){
			if(data[i].compareTo(data[i+1]) < 0){
				data[i].setColor(Color.GREEN);
				updateGUI();
			}else{				
				data[i].setColor(Color.RED);
				updateGUI();
			}
		}
		data[data.length-1].setColor(Color.GREEN);
		updateGUI();
	}
	
    public void selectionSort(DataItem[] a, int n){
        for (int index = 0; index < n - 1; index++) {
            
            a[index].setColor(Color.RED);
        	int indexOfNextSmallest = getIndexOfSmallest(a, index, n - 1);
        	a[indexOfNextSmallest].setColor(Color.BLUE);
            swap(a, index, indexOfNextSmallest);
            updateGUI();
            resetDataItems(a);
        } // end for
    } // end selectionSort

    /** Finds the index of the smallest value in an array a.
     * @param a An array of Comparable objects.
     * @param first An integer >= 0 and < a.length that is the index of the first
     * array entry to consider.
     * @param last An integer >= 0 and < a.length that is the index of the last
     * array entry to consider.
     * @return The index of the smallest value among
     * a[first], a[first+1], . . . , a[last].
     */
    public int getIndexOfSmallest(DataItem[] a, int first, int last){
    	DataItem min = a[first];
        int indexOfMin = first;
        a[indexOfMin].setColor(Color.BLUE);
        for (int index = first + 1; index <= last; index++) {
            a[index].setColor(Color.RED);
            updateGUI();

            if (a[index].compareTo(min) < 0) {
            	if(indexOfMin == first)
                    a[indexOfMin].setColor(Color.RED);
            	else
            		a[indexOfMin].setColor(Color.GRAY);
                min = a[index];
                indexOfMin = index;
                a[indexOfMin].setColor(Color.BLUE);
                updateGUI();
            } // end if
            if(indexOfMin != index && index != first)
            	a[index].setColor(Color.GRAY);
            // Assertion: min is the smallest of a[first] through a[index].
        } // end for
        return indexOfMin;
    } // end getIndexOfSmallest

    /** Swaps the array entries a[i] and a[j].
     * @param a An array of  objects.
     * @param i An integer >= 0 and < a.length.
     * @param j An integer >= 0 and < a.length. 
     * 
     * Modified from Carrano to use generics.
     */
    private void swap(DataItem[] a, int i, int j){
    	DataItem temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap

    
    

    
    /**************************************************************
     * ITERATIVE INSERTION SORT 
     **************************************************************/
    /** Sorts the first n objects in an array into ascending order.
     * @param a An array of Comparable objects.
     * @param n An integer > 0.
     */
    
    public void insertionSort(DataItem[] a, int n) {
        insertionSort(a, 0, n - 1);
    } // end insertionSort

    /** Iterative insertion sort of the  objects in a range of locations in an array into ascending order.
     * @param a An array of Comparable objects.
     * @param first An integer >= 0 and < a.length.
     * @param last An integer > first and < a.length.
     */
    
    public void insertionSort(DataItem[] a, int first, int last){
    	int nextToInsert;
        boolean foundLocation;
        int loc;

        for (int unsorted = first + 1; unsorted <= last; unsorted++) {
            nextToInsert = a[unsorted].getNumber();
            a[unsorted].setColor(Color.BLUE);
            updateGUI();
            insertInOrder(nextToInsert, a, first, unsorted - 1);
            resetDataItems(a);
        } // end for
    } // end insertionSort

    
    /** Inserts anEntry into the sorted entries a[begin] through a[end].
     * @param anEntry The entry to be inserted into the sorted portion of the array.
     * @param a An array of Comparable objects.
     * @param begin An integer >= 0 and < a.length.
     * @param end An integer > first and < a.length.
     */
    public void insertInOrder(int anEntry, DataItem[] a, int begin, int end){
        int index = end; // index of last entry in the sorted portion


        // Make room, if needed, in sorted portion for another entry.
        while ((index >= begin) && (Integer.compare(anEntry, a[index].getNumber())) < 1) {
    		a[index].setColor(Color.RED);
            updateGUI();
        	a[index].setColor(Color.GRAY);
            
            a[index + 1].setNumber(a[index].getNumber()); // make room
            index--;
        } // end while

        // Assertion: a[index + 1] is available.
//        a[index+1].setColor(Color.RED);
//        updateGUI();
        a[index + 1].setNumber(anEntry); // insert

    } // end insertionSort
    
    
    
    /**************************************************************
     * MERGE SORT
     **************************************************************/

    /** Merge sort on a portion of an array.  Creates a temporary array
     *  then calls the recursive function.
     * @param a An array of Comparable objects.
     * @param first An integer >= 0 that is the index of the first
     * array entry to consider.
     * @param last An integer >= 0 that is the index of the last
     * array entry to consider.
     */
    public void mergeSort(DataItem[] a, int first, int last){
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        DataItem[] tempArray = (DataItem[]) new DataItem[a.length]; // Unchecked cast
        mergeSort(a, tempArray, first, last);
    } // end mergeSort
    
    /** Recursively merge sort a portion of an array.
     * @param a An array of Comparable objects.
     * @param tempArray An array used by the merge step.
     * @param first An integer >= 0 that is the index of the first
     * array entry to consider.
     * @param last An integer >= 0 that is the index of the last
     * array entry to consider.
     */
    public
    void mergeSort(DataItem[] a, DataItem[] tempArray, int first, int last){
        if(last - first < 2)        // We have some work to do
        {
        	return;
        }
        	int mid = (first + last) / 2;
        	a[mid].setColor(Color.BLUE);
        	updateGUI();
            mergeSort(a, tempArray, first, mid);
            mergeSort(a, tempArray, mid, last);
            merge(a, tempArray, first, mid, last);
            resetDataItems(a);
    } // end mergeSort
    
    /** Merge the entries in two contiguous sorted sublists 
     * @param a An array of Comparable objects.
     * @param tempArray A temporary array used in the merge.
     * @param first An integer >= 0 and < mid.
     * @param mid An integer  <= last.
     * @param last An integer  < a.length.
     */
    public
    void merge(DataItem[] a, DataItem[] tempArray, int first, int mid, int last){
        int i0 = first, i1 = mid;
        for (int j = first; j < last; j++) {
        	// If left run head exists and is <= existing right run head.
        	a[j].setColor(Color.RED);
        	updateGUI();
        	a[j].setColor(Color.GRAY);

            if (i0 < mid && (i1 >= last || a[i0].compareTo(a[i1]) <= 0)){
                tempArray[j] = a[i0];
                i0 = i0 + 1;
            }else{
                tempArray[j] = a[i1];
                i1 = i1 + 1;
            }
        }

        for(int k = first; k < last; k++)
            a[k] = tempArray[k];
    	
    } // end merge
    
    /** 
     * Sorts the first n objects in an array into ascending order.
     * @param a An array of Comparable objects.
     * @param n An integer > 0. 
     */
    public void basicQuickSort(DataItem[] a, int n){   
        basicQuickSort(a, 0, n-1);
    } // end basicQuickSort
    
    /** Recursively sorts an array into ascending order. Does not use median of three. 
    * Does not do insertion sort on small sub arrays.
    * 
    * @param a An array of Comparable objects.
    * @param first An integer >= 0 that is the index of the first array entry to consider.
    * @param last An integer >= 0 that is the index of the last array entry to consider.
    */
    public void basicQuickSort(DataItem[] a, int first, int last){
        if (last - first + 1 > 1)  // Number of to be sorted is greater than 1
        {
        	
            a[first].setColor(Color.ORANGE);
            a[last].setColor(Color.ORANGE);
            // Create the partition: Smaller | Pivot | Larger
            int pivotIndex = basicPartition(a, first, last);
            a[pivotIndex].setColor(Color.BLUE);
            updateGUI();
            // Sort subarrays Smaller and Larger
            basicQuickSort(a, first, pivotIndex-1);
            a[pivotIndex].setColor(Color.BLUE);
            updateGUI();
            basicQuickSort(a, pivotIndex+1, last);
            resetDataItems(a);
        } // end if
    } // end basicQuickSort
   
    /** 
    * Partitions an array as part of quick sort into two subarrays
    * called Smaller and Larger that are separated by a single
    * entry called the pivot.
    * Entries in Smaller are left of the pivot and <= pivot.
    * Entries in Larger are right of the pivot and >= pivot.
    * 
    * @param a An array of Comparable objects.
    * @param first The integer index of the first array entry; first >= 0.
    * @param last The integer index of the last array entry; last >= first  last < a.length.
    * @return the index of the pivot 
    */
    public int basicPartition(DataItem[] a, int first, int last){
        
        int pivotIndex = last;
        DataItem pivot = a[pivotIndex];
        int storeIndex = first;
        for(int i = first; i<=last-1; i++){
            a[i].setColor(Color.RED);
            updateGUI();
            a[i].setColor(Color.GRAY);
        	if(a[i].compareTo(pivot) < 0){
        		DataItem tmp = a[i];
                a[i].setColor(Color.YELLOW);
                updateGUI();
                a[i].setColor(Color.GRAY);
        		a[i] = a[storeIndex];
        		a[storeIndex] = tmp;
        		storeIndex ++;
        	}       	
        }
        DataItem tmp = a[last];
		a[last] = a[storeIndex];
		a[storeIndex] = tmp;

		return storeIndex;
        
    } // end basicPartition
    
    /**************************************************************
     * ITERATIVE SHELL SORT
     **************************************************************/
    /** Sorts the first n objects in an array into ascending order.
     * @param a An array of Comparable objects.
     * @param n An integer > 0.
     */
    public void shellSort(DataItem[] a, int n){
        shellSort(a, 0, n - 1);
    } // end shellSort

    /** Use incremental insertion sort with different increments to 
     * sort a range of values in the array.
     * @param a An array of Comparable objects.
     * @param first An integer >= 0.
     * @param last An integer > first and < a.length.
     */
    public void shellSort(DataItem[] a, int first, int last){
        int n = last - first + 1; // number of array entries
        int space = n/2;
        updateGUI();

        while (space > 0) {
            for (int begin = first; begin < first + space; begin++) {
                incrementalInsertionSort(a, begin, last, space);
            }
            space = space/2;
        } // end while
        
    } // end shellSort

    /** Sorts equally spaced entries of an array into ascending order.
     * @param a An array of Comparable objects.
     * @param first The integer index of the first array entry to consider; first >= 0 and < a.length.
     * @param last The integer index of the last array entry to consider; last >= first and < a.length.
     * @param space the difference between the indices of theentries to sort.
     */
    public void incrementalInsertionSort(DataItem[] a, int first, int last, int space) {
        int unsorted, index;
        for (unsorted = first + space; unsorted <= last;
                unsorted = unsorted + space) 
        {

        	DataItem nextToInsert = a[unsorted];
            a[unsorted].setColor(Color.BLUE);
            updateGUI();
            a[unsorted].setColor(Color.GRAY);
            index = unsorted - space;
            while ((index >= first) && (nextToInsert.compareTo(a[index]) < 0)){
            	if(index != unsorted){
	                a[index].setColor(Color.RED);
	                updateGUI();
	                a[index].setColor(Color.GRAY);
            	}

            	a[index + space] = a[index];
                a[index+space].setColor(Color.YELLOW);
                updateGUI();
                a[index+space].setColor(Color.GRAY);
                index = index - space;

            } // end while
            a[unsorted].setColor(Color.GRAY);

            a[index + space] = nextToInsert;
        } // end for
    } // end incrementalInsertionSort

    
    /** Radix Sort function **/
    public void RadixSortLSD( DataItem[] a, int n)
    {
        int i, m = a[0].getNumber(), exp = 1;
        int[] b = new int[n];
        for (i = 1; i < n; i++)
            if (a[i].getNumber() > m)
                m = a[i].getNumber();
        while (m / exp > 0)
        {
            int[] bucket = new int[n];
 
            for (i = 0; i < n; i++){
            	a[i].setColor(Color.RED);
                updateGUI();
            	a[i].setColor(Color.GRAY);

                bucket[(a[i].getNumber() / exp) % 10]++;
            }
            for (i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (i = n - 1; i >= 0; i--){
            	a[i].setColor(Color.RED);
                updateGUI();
            	a[i].setColor(Color.GRAY);

            	b[--bucket[(a[i].getNumber() / exp) % 10]] = a[i].getNumber();
                
            }
            for (i = 0; i < n; i++){
            	a[i].setColor(Color.RED);
                updateGUI();
            	a[i].setColor(Color.GRAY);

                a[i].setNumber(b[i]);
            }
            exp *= 10;
        }
    }    
    
    /** Radix Sort function **/
    public void RadixSortMSD( DataItem[] a, int n)
    {
        int i, m = a[0].getNumber(), exp = 1;
        int[] b = new int[n];
        for (i = 1; i < n; i++)
            if (a[i].getNumber() > m)
                m = a[i].getNumber();
        while (m / exp > 0)
        {
            int[] bucket = new int[n];
 
            for (i = 0; i < n; i++){
            	a[i].setColor(Color.RED);
                updateGUI();
            	a[i].setColor(Color.GRAY);

                bucket[(a[i].getNumber() / exp) % 10]++;
            }
            for (i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (i = n - 1; i >= 0; i--){
            	a[i].setColor(Color.RED);
                updateGUI();
            	a[i].setColor(Color.GRAY);

            	b[--bucket[(a[i].getNumber() / exp) % 10]] = a[i].getNumber();
                
            }
            for (i = 0; i < n; i++){
            	a[i].setColor(Color.RED);
                updateGUI();
            	a[i].setColor(Color.GRAY);

                a[i].setNumber(b[i]);
            }
            exp *= 10;
        }
    }    
    
    
	public void bubbleSort(DataItem a[], int n){
		for(int i=0; i<n; i++){
			a[i].setColor(Color.BLUE);
			updateGUI();
			for(int j=i+1; j<n; j++){
				a[j].setColor(Color.RED);
				updateGUI();
				a[j].setColor(Color.GRAY);
				if(a[i].getNumber() > a[j].getNumber()){
					int tmp = a[i].getNumber();
					a[i].setNumber( a[j].getNumber());
					a[j].setNumber(tmp);
				}
			}
			resetDataItems(a);
		}
	}


}

