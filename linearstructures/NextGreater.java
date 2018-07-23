package linearstructures;

import java.util.*;
import java.util.stream.Collectors;

/*[1,3,5,2,4]
  [6,5,4,3,2,1,7]*/
/*[1,3,5,2,4]
  [5,4,3,2,1]*/
//TODO:finish tomorrow
public class NextGreater {
    public static void main(String[] args) {
        int[] mockFirst = {4,1,2};
        int[] mockSecond = {1,3,4,2};
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
