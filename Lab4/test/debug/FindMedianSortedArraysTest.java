package debug;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMedianSortedArraysTest {

    @Test
    public void findMedianSortedArrays1() {
        // Normal
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] A = {1, 2};
        int[] B = {3, 4};
        double ans = 2.5;
       assertEquals(true, findMedianSortedArrays.findMedianSortedArrays(A, B)-ans==0);
    }

    @Test
    public void findMedianSortedArrays2() {
        // Small
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] A = {1};
        int[] B = {3};
        double ans = 2;
        assertEquals(true,findMedianSortedArrays.findMedianSortedArrays(A, B)-ans==0);
    }

    @Test
    public void findMedianSortedArrays3() {
        // Large
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] A = {1, 3, 4, 5, 6};
        int[] B = {1};
        double ans = 3.5;
        assertEquals(true,findMedianSortedArrays.findMedianSortedArrays(A, B)-ans==0);
    }

    @Test
    public void findMedianSortedArrays4() {
        // Large
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] A = {1, 3, 4, 5, 6};
        int[] B = {8};
        double ans = 4.5;
        assertEquals(true,findMedianSortedArrays.findMedianSortedArrays(A, B)-ans==0);
    }
}