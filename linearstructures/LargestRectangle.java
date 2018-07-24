package linearstructures;

import java.util.Stack;

public class LargestRectangle {
    public static void main(String[] args) {
        int[] mockHeights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(mockHeights));
    }

    public static int largestRectangleArea(int[] heights) {
        int result = 0;
        Stack<Integer> heightss = new Stack<>();
        Stack<Integer> positions = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            if (heightss.empty()) {
                heightss.push(heights[i]);
                positions.push(i);
            }
            if (heights[i] > heightss.peek()) {
                heightss.push(heights[i]);
                positions.push(i);
            }else {
                int currentHeight;
                while (!heightss.empty() && heightss.peek() > heights[i]) {
                    currentHeight = heightss.peek() * (i - positions.peek());
                    heightss.pop();
                    if (heightss.peek() > heights[i]) {
                        positions.pop();
                    }
                    if (currentHeight > result) {
                        result = currentHeight;
                    }
                }
            }
        }
        int heightOfLeftOver;
        while (!heightss.empty()) {
            heightOfLeftOver= heightss.peek() * (heights.length - positions.peek());
            heightss.pop();
            positions.pop();
            if (heightOfLeftOver > result) {
                result = heightOfLeftOver;
            }
        }
        return result;
    }
}
