package recursion;

import java.util.*;
import java.util.stream.Collectors;

//109 / 147 test cases passed.
//Warning:Very ugly code may demand a translator to be readable!!!
public class PartitionToEqualSubsets {
    public static boolean isValid;

    public static void main(String[] args) {
        int[] mock = {3522, 181, 521, 515, 304, 123, 2512, 312, 922, 407, 146, 1932, 4037, 2646, 3871, 269};
        int numberOfPartitions = 5;
        boolean canBe = canPartitionKSubsets(mock, numberOfPartitions);
        System.out.println(canBe);
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        isValid = false;
        if (sum % k > 0) {
            return isValid;
        }
        List<Integer> numsAsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int targetSum = sum / k;
        Collections.sort(numsAsList, Comparator.naturalOrder());
        for (int i = 0; i < k; i++) {
            isValid = false;
            checkValidity((ArrayList<Integer>) numsAsList, targetSum, 0, numsAsList.size() - 1, new Hashtable<>());
            if (!isValid) {
                break;
            }
        }
        return isValid;
    }

    public static void checkValidity(ArrayList<Integer> nums, int target, int sum, int lastIndex, Map<Integer, Integer> used) {
        for (int i = lastIndex; i >= 0; i--) {
            if (isValid) {
                break;
            }
            if (used.containsKey(i)) {
                continue;
            }
            sum += nums.get(i);
            if (sum < target) {
                used.put(i, nums.get(i));
                checkValidity(nums, target, sum, i - 1, used);
                if (!isValid) {
                    used.remove(i, nums.get(i));
                    sum -= nums.get(i);
                }
            } else if (sum == target) {
                isValid = true;
                used.put(i, nums.get(i));
                for (Integer element : used.values()) {
                    nums.remove(new Integer(element));
                }
                break;
            } else {
                sum -= nums.get(i);
            }
        }
    }


}