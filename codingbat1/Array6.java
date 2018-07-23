package codingbat1;

public class Array6 {
    public static void main(String[] args) {
        int[] mock = {1, 4, 3 , 2, 5, 6};
        int index = 0;
        System.out.println(array6(mock,index));
    }
    public static boolean array6(int[] nums, int index) {
        if (index == nums.length) {
            return false;
        }
        if (nums[index] == 6 && index < nums.length) {
            return true;
        }
        return array6(nums, index + 1);
    }
}
