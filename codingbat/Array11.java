package codingbat;

public class Array11 {
    public static void main(String[] args) {
        int[] mock = {1, 2, 11, 11, 11};
        int index = 0;
        System.out.println(array11(mock,index));
    }
    public static int array11(int[] nums, int index) {
        if (index == nums.length) {
            return 0;
        }
        int count = 0;
        if (nums[index] == 11) {
            ++count;
        }
        return array11(nums,index + 1) + count;
    }

}
