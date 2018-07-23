package linearstructures;

import java.util.HashSet;
import java.util.Set;

public class Single {
    public static void main(String[] args) {
        int[] mock = {2,2,1};
        System.out.println(singleNumber(mock));
    }
    public static int singleNumber(int[] nums) {
        Set<Integer> unique = new HashSet<>();

        for(int num : nums){
            if(unique.contains(num)) {
                unique.remove(num);
            }
            else{
                unique.add(num);
            }
        }
        return (int)unique.toArray()[0];
    }
}
