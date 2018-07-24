package linearstructures;

import java.util.*;
public class NextGreater {
    public static void main(String[] args) {
        int[] mockFirst = {1,5,3,4,7};
        int[] mockSecond = {6,5,4,3,2,1,7};
        int[] result = nextGreaterElement(mockFirst, mockSecond);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> greaterElements = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                greaterElements.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = greaterElements.getOrDefault(nums1[i],-1);
        }
        return nums1;
    }

}
