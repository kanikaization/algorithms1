package assign2.successor;

public class SuccessorWithDelete {
    private int n;
    private int[] removed;
    private int removedCnt;


    public SuccessorWithDelete(int n) {
         removed = new int[n];
         removedCnt = 0;
    }

    public void remove(int x) {
        if(removedCnt==0) {
            removed[removedCnt] = x;
            return;
        } else {
            int mid = removedCnt/2;
            do {
                if(x < removed[mid] && x > removed[mid+1]) {
                    insert(x, mid+1);
                    break;
                }
                else if (x > removed[mid]) {
                    mid = mid+1;
                } else {
                    mid = mid/2;
                }
            } while(true);

        }

    }

    private void insert(int x, int i) {
        for(int j=removedCnt; j>i; ) {
            removed[j] = removed[j--];
        }
        removed[i]=x;
        removedCnt++;
    }

    public int successor(int x) {
        while(!found(x)){
            x = x+1;
        }
        return x;
    }

    private boolean found(int x) {
        int start=0, end=removedCnt;
        while(start<end) {
            int mid = (start+end)/2;
            if(x==removed[mid]) {
                return true;
            } else if(x> removed[mid]) {
                start=mid+1;
            } else {
                end = mid-1;
            }
        }
        return false;
    }

}
