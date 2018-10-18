public class HybridSort implements SortingAlgorithm{

    public void sort(int [] a){
        int n = a.length;
        //RUN_SIZE = 16 FOR TESTING PURPOSES.
        sort(a,0,n-1,5);
    } 
    public void sort(int [] a, int run_size){
        int n = a.length;
        sort(a,0,n-1,run_size);
    } 
       
    public int findNextRun(int [] a, int index, int run_size){
        // System.out.println("run size : "+ run_size);
        if(index>a.length-1){
            return 0;
        }
        int k = findRun(a,index);
        //If index == a run;
        if(k >= run_size-1){
            return index;
        } 
        //ELSE LOOK FOR INDEX WITH A RUN
        for(int i = index+k+1; i < a.length;i++){
            int runs = findRun(a, i);
            if(runs >= run_size-1){
                return i;
            } else if(runs > 0){
                i = i + runs;
            }
        }
        //IF THERE IS NO RUNS:
        // System.out.println("No runs with Run_Size: "+ run_size);
        return index;
    }
    public int findRun(int [] a, int i){
        // System.out.println("Find run is at "+i);
        if(i > a.length-1){
            return 0;
        }
        int count = 1;
        for(int index = i; i < a.length-2;){
            if(a[index+count] <= a[index+1+count]){
                // System.out.println("Comparing a["+(index+count)+"] "+ a[index+count]+ "to  a["+(index+count+1)+"] "+ a[index+count+1]);
                i++;
                count++;
                System.out.println("Comparing a["+(i)+"] "+ a[i]+ " to  a["+(i+1)+"] "+ a[i+1]+ " count is " + count);

            } else {
                break;
            }
        }

        return count;
      
    }

    public void sort(int [] a, int l , int r, int run_size) {
        if( run_size >= 0 ){
            int run = findNextRun(a,l,run_size); //next index 
            int x = findRun(a,run)+1; //FIND RUNS: x = # of acending items.

            int y = findNextRun(a, run+x, run_size);
            int z = findRun(a,y)+1;
            // System.out.println("START Runsize: " + run_size +  " : L : "+ l +" r : "+ r +" run : "+ run +" x : "+ x + " y: " + y + " z: "+ z);
            if(run < run_size && z < run_size  ){
                // System.out.println("RUN SIZE DOWN CALLED SORT "+"Merged Runsize: " + run_size +  " : L : "+ l +" r : "+ r +" run : "+ run +" x : "+ x + " y: " + y + " z: "+ z);
                sort(a, l, r, run_size-1);
            }

            if(x == a.length || y == a.length){
                // System.out.println("Return flag 1");
                return;
            }
            
            // if(x == z && run == y-1){
            //     sort(a,0,r,run_size-1);
            // }
            if( x < run_size || z < run_size){ //x < run_size-1 || z < run_size-1
                // System.out.println("Runsize: " +run_size+ " Run: "+ run + " l: "+ l+ " x: "+ x + " y: "+ y+ " z: "+ z);
                // System.out.println("Intercepted...");
                sort(a, 0, r, run_size - 1);
            }

            if(y == a.length && x != a.length){
                sort(a,l,r, run_size-1);
            }
            if(run == 0 && y == a.length && z == 1){
                // System.out.println("Return flag 2");

                return;
            }

            if(run_size == 0 && l == 0 && run == 0&& x == 1 && y == 1 && z == a.length-1){
                l = r;
                // System.out.println("Pew pew done.");
                // System.out.println("Return flag 7");
                

                return;
            } 

            // System.out.println("Merging Runsize: " + run_size +  " : L : "+ l +" r : "+ r +" run : "+ run +" x : "+ x + " y: " + y + " z: "+ z);
            if(( x ) >= a.length || (y )>= a.length){
                // System.out.println("Return flag 3");

                return;
            }
            if(run == l && x == 1){
                sort(a,0,r,run_size-1);
            }
            System.out.println("Merged Runsize: " + run_size +  " : L : "+ l +" r : "+ r +" run : "+ run +" x : "+ x + " y: " + y + " z: "+ z);

            if(y<(run+x)){
                // System.out.println("REVERSED MERGE");
                merge(a,y,y+z,run,run+x);
            } else {
                merge(a,run,run+x,y,y+z);
            }
            // System.out.println("Merged Runsize: " + run_size +  " : L : "+ l +" r : "+ r +" run : "+ run +" x : "+ x + " y: " + y + " z: "+ z);
            
            if(x == a.length-1|| y==a.length){
                // System.out.println("Return flag 4");

                return;
            }
            if(( x + run) > a.length && (y+z )> a.length){
                // System.out.println("Return flag 5");

                return;
            }
            // System.out.println("Calling sort, run+x: " + (run+x+z));
            if(run_size == 0 && l == 0 && run == 0&& x == 1 && y == 1 && z == a.length-1){
                l = r;
                // System.out.println("Pew pew done.");
                // System.out.println("Return flag 7");


                return;
            } else{
                if(y > a.length-1||(run+x+z) >= a.length-1){ //y+z==a.length-1
                    // System.out.println("RUN SIZE DOWN CALLED SORT "+"Merged Runsize: " + run_size +  " : L : "+ l +" r : "+ r +" run : "+ run +" x : "+ x + " y: " + y + " z: "+ z);
                    sort(a,0,r,run_size-1);
                    // System.out.println("Return flag 8");

                } else{
                    System.out.println("L CHANGEDCALLED SORT "+"Merged Runsize: " + run_size +  " : L : "+ l +" r : "+ r +" run : "+ run +" x : "+ x + " y: " + y + " z: "+ z);
                    sort(a,run+x+z,x, run_size);
                }

            }

        } 
        // System.out.println("Return flag 9");

        return;
    }
          
    public void insertionSort(int [] a) {
        int n = a.length;
        for(int i = 1; i < n; i++){ 
            int x = a[i];
            int j = i-1;
            while(j >= 0  && a[j] > x){
                a[j+1]= a[j];
                j = j -1; 
            }
            a[j+1]= x;
        }
    }
    public void merge(int [] arr, int left, int leftr,int rightl, int right){
        if(leftr == arr.length|| right == arr.length){
            return;
        }

        int size1 = leftr - left;
        int size2 = right - rightl;
        int leftarr[] = new int [size1];
        int rightarr[] = new int [size2];
        for(int i = 0; i < arr.length; i++){
            System.out.println("MAIN ARR "+ i + " : " + arr[i]);
        }

        for(int i = 0; i < size1; i++){
            leftarr[i] = arr[left+i];
            System.out.println("Left array "+(i+left)+" : "+leftarr[i] + " size 1: "+ size1);
        }
        insertionSort(leftarr);
        for(int j = 0; j< size2;j++){
            rightarr[j] = arr[rightl+j];
            System.out.println("Right array "+(j+rightl)+" : "+rightarr[j]+ " size 2: "+ size2);
        }
        insertionSort(rightarr);

        // System.out.println("Moving in between");

        int x = rightl-leftr;
        System.out.println("Left is" + left + " LeftR is" + leftr + " Rightl is " + rightl + " Right is " + right);

        // System.out.println("x is " + x);

        for(int i = 0; i < x; i++){
            arr[right-i-1] = arr[rightl-1-i];
            // System.out.println("Moved in between");
        }

        int i = 0;
        int j = 0;
        int k = left;
        while(i < size1 && j < size2){
            if(leftarr[i] <= rightarr[j]){
                arr[k] = leftarr[i];
                i++;
            }else{
                arr[k] = rightarr[j];
                j++;
            }
            k++;
        }
        while(i<size1){
            arr[k] = leftarr[i];
            i++;
            k++;
        }
        while(j<size2){
            arr[k] = rightarr[j];
            j++;
            k++;
        }
    }

}    
