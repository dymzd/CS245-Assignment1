public class MergeSort implements SortingAlgorithm {

    public void sort(int [] a) {
        int n = a.length;
        sort(a, 0, n-1);
    }
    public void sort(int [] a, int l, int r){
        if(l < r){
            int m = (l+r)/2;
            sort(a, l, m);
            sort(a,m+1,r);
            merge(a,l,m,r);
        }
    }
    public void merge(int [] arr, int left, int mid, int right){
        int size1 = mid - left + 1;
        int size2 = right - mid;
        int leftarr[] = new int [size1];
        int rightarr[] = new int [size2];
        for(int i = 0; i < size1; i++){
            leftarr[i] = arr[left+i];
        }
        for(int j = 0; j<size2;j++){
            rightarr[j] = arr[mid+j+1];
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