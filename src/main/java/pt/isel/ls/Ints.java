package pt.isel.ls;

public class Ints {
    public static int max(int a, int b){
        return a >= b ? a : b;
    }

    public static int indexOfBinary(int[] a, int fromIndex, int toIndex, int n) {

        if (fromIndex > toIndex)
            throw new IllegalArgumentException("from(" + fromIndex + ") > to(" + toIndex + ")");

        int low = fromIndex;
        int high = toIndex-1; // before: int high = toIndex - 1;
        int mid;
        if(a.length==0) return -1; // added
        while(low <= high){
            mid = low + ((high-low)/2); //before: high + low / 2 + 1;
            if(n > a[mid]) low = mid + 1;
            else if(n < a[mid]) high = mid - 1;
            else return mid;
        }
        return -1;
    }
}
