import java.util.*;
import java.util.stream.Collectors;

//109 / 147 test cases passed.
//Warning:Very ugly code may demand a translator to be readable!!!
public class PartitionToEqualSubsets {
    public static boolean isValid;
    public static boolean isImpossible;

    public static void main(String[] args) {
        int[] mock = {3, 3, 13, 1, 3, 12, 3, 3, 16};
        int numberOfPartitions = 3;
        boolean canBe = canPartitionKSubsets(mock, numberOfPartitions);
        System.out.println(canBe);
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        isValid = false;
        isImpossible = false;
        if (sum % k > 0) {
            return isValid;
        }
        boolean[] used = new boolean[nums.length];
        List<Integer> numsAsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int targetSum = sum / k;
        Collections.sort(numsAsList, Comparator.naturalOrder());
        checkValidity((ArrayList<Integer>) numsAsList, k, targetSum, 0, 0, nums.length - 1, used);
        return isValid;
    }

    public static void checkValidity(ArrayList<Integer> nums, int partitionCount, int target, int sum, int indexFirst, int indexSecond, boolean[] used) {
        if (partitionCount == 1) {
            ArrayList<Integer> leftOver = new ArrayList<>();
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    leftOver.add(nums.get(i));
                }
            }
            if (sumItUp(leftOver) + sum == target) {
                isValid = true;
            }
        } else if (sum == target) {
            --partitionCount;
        } else if (sum > target) {
            isImpossible = true;
            return;
        } else if (sum == 0) {
            while (used[indexSecond]) {
                --indexSecond;
            }
            sum += nums.get(indexSecond);
            used[indexSecond] = true;
            checkValidity(nums, partitionCount, target, sum, indexFirst, --indexSecond, used);
        } else if (sum > 0 && sum < target && nums.contains(target - sum) && nums.indexOf(target - sum) < nums.size() - 1) {
            int targetNum = target - sum;
            int index = nums.indexOf(target - sum);
            while (nums.get(index) == targetNum && used[index]) {
                index++;
                if (nums.get(index) != targetNum) {
                    isImpossible = true;
                    break;
                }
            }
            sum += nums.get(index);
            used[index] = true;
            checkValidity(nums, partitionCount, target, sum, indexFirst, indexSecond, used);
        } else {
            while (used[indexFirst]) {
                ++indexFirst;
            }
            int nextNotUsedIndex = indexFirst + 1;
            while (used[nextNotUsedIndex] && nextNotUsedIndex < nums.size() - 1) {
                ++nextNotUsedIndex;
            }
            int tryNext = sum + nums.get(nextNotUsedIndex);
            if (tryNext > sum + nums.get(indexFirst)) {
                indexFirst = nextNotUsedIndex;
            }
            sum += nums.get(indexFirst);
            used[indexFirst] = true;
            checkValidity(nums, partitionCount, target, sum, indexFirst, indexSecond, used);
        }
        if (isValid) {
            return;
        }
        if (isImpossible) {
            return;
        }
        sum = 0;
        checkValidity(nums, partitionCount, target, sum, indexFirst, indexSecond, used);
    }

    public static int sumItUp(ArrayList<Integer> toSum) {
        int sum = 0;
        for (Integer num : toSum) {
            sum += num;
        }
        return sum;
    }

}