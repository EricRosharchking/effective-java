package com.liyuan.hong.globaljavamentor.extramile.sort;

/**
 * Integrate sort implementations into binary search implementation in the
 * most efficient way using appropriate software design and patterns. Use
 * parametrized tests for testing similar cases in different sort implementations
 */
public class BinarySearchSort implements SortInterface{

    @Override
    public int[] sort(int[] arr) {
        if (arr == null || arr.length < 2) {

        } else {
            sort(arr, 1);
        }
        return arr;
    }

    /**
     * @param arr:   the array to be sorted
     * @param index: first index of the unsorted part of the array
     */
    public static void sort(int[] arr, int index) {
        if (index < arr.length) {
            int position = findPosition(arr, 0, index - 1, index);
            if (position != index) {
                InsertionSort.insert(arr, position, index);
            }
            sort(arr, index + 1);
        }
    }

    private static int findPosition(int[] arr, int left, int right, int index) {
        if (arr[left] >= arr[index]) {
            return left;
        } else if (arr[right] <= arr[index]) {
            return index;
        } else if (right - left > 1) {
            int mid = (right - left) / 2 + left;
            if (arr[mid] > arr[index]) {
                return findPosition(arr, left, mid, index);
            }
            return findPosition(arr, mid, right, index);
        }
        return right;
    }


}
