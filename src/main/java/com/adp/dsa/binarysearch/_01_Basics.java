package com.adp.dsa.binarysearch;

public class _01_Basics {

    // Returns index of x if it is present in arr[].
    public static int binarySearch(int arr[], int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if target is present at mid
            if (arr[mid] == target)
                return mid;
            // If target greater, ignore left half
            if (arr[mid] < target)
                low = mid + 1;
            // If target is smaller, ignore right half
            else
                high = mid - 1;
        }

        // If we reach here, then element was
        // not present
        return -1;
    }

    //the smallest index, ind, where arr[ind] >= target.
    // But if any such index is not found, the lower bound algorithm returns n i.e. size of the given array.
    public static int lowerBound(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {             // maybe an answer
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    /*
    The upper bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than the given key i.e. x.

The upper bound is the smallest index, ind, where arr[ind] > x.

But if any such index is not found, the upper bound algorithm returns n i.e. size of the given array. The main difference between the lower and upper bound is in the condition. For the lower bound the condition was arr[ind] >= x and here, in the case of the upper bound, it is arr[ind] > x.
     */
    public static int upperBound(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > target) {             // maybe an answer
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 6, 7, 9, 12, 16, 17};
        int target = 6;
        int ind = binarySearch(a, target);
        if (ind == -1)
            System.out.println("The target is not present.");
        else
            System.out.println("The target is at index: " + ind);

        int[] arr = {3, 5, 8, 15, 19};
        int x = 9;
        ind = lowerBound(arr, x);
        System.out.println("The lower bound is the index: " + ind);

        arr = new int[]{3, 5, 8, 9, 15, 19};
        x = 9;
        ind = upperBound(arr, x);
        System.out.println("The upper bound is the index: " + ind);
    }


}
