package spittr.web;

import org.junit.Test;

/**
 * @author arc3102
 * @date 2021/2/28 11:24
 */
public class Mytest {


    public boolean isMonotonic(int[] A) {
        boolean ok = true;
        int n = A.length;
        int now = A[0];
        int z = -1;
        for(int i = 1; i < n; i++) {
            if(A[i] > A[i-1] && (z == 1 || z == -1)){
                z = 1;
            } else if(A[i] > A[i-1] && (z == 2 || z == -1)) {
                z = 2;
            } else if(A[i] == A[i-1]){
                continue;
            } else {
                System.out.println(A[i]);
                System.out.println(A[i - 1]);
                ok = false;
                break;
            }
        }
        return ok;
    }

    @Test
    public void test(){
        int[] a = {6,5,4,4};
        boolean ans = isMonotonic(a);
        System.out.println(ans);
    }
}
