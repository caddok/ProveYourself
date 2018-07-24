package linearstructures;

import java.util.ArrayList;
import java.util.Stack;

public class Asteroids {
    public static void main(String[] args) {
        int[] mockAsteroids = {1, -2, -2, -2};
        int[] mockAsteroids2 = {10, 2, -5};
        System.out.println(asteroidCollision(mockAsteroids).toString());
        System.out.println(asteroidCollision(mockAsteroids2).toString());
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> afterCollisions = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            boolean bigBangOccured = false;
            if (afterCollisions.empty()) {
                afterCollisions.push(asteroids[i]);
            }
            else if (afterCollisions.peek() > 0 && asteroids[i] < 0) {
                while (!afterCollisions.empty()) {
                    int nextAsteroid = afterCollisions.peek();
                    if (nextAsteroid < 0) {
                        afterCollisions.push(asteroids[i]);
                        break;
                    }
                    if (nextAsteroid < Math.abs(asteroids[i])) {
                        afterCollisions.pop();
                    } else if (nextAsteroid == Math.abs(asteroids[i])) {
                        bigBangOccured = true;
                        afterCollisions.pop();
                        break;
                    } else {
                        break;
                    }
                }
                if(afterCollisions.empty() && !bigBangOccured) {
                    afterCollisions.push(asteroids[i]);
                }
            } else if(afterCollisions.peek() < 0  && asteroids[i] < 0
                    || asteroids[i] > 0) {
                afterCollisions.push(asteroids[i]);
            }
        }
        ArrayList<Integer> resultList = new ArrayList<>(afterCollisions);
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
