package exam;

import java.util.*;

public class HDNLToy {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        Stack<String> strings = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        int numberOfTags = Integer.parseInt(in.nextLine());

        for (int i = 0; i < numberOfTags; i++) {
            String temp = in.nextLine();
            int depth = Integer.parseInt(temp.substring(1));
            if (strings.isEmpty() || depth > depths.peek()) {
                strings.push(temp);
                depths.push(depth);
                printOpeningTag(temp, strings);
                continue;
            }
            while (depth < depths.peek()) {
                printClosingTag(strings, depths);
                if (depths.isEmpty()) {
                    break;
                }
            }
            if (depths.isEmpty()) {
                strings.push(temp);
                depths.push(depth);
                printOpeningTag(temp, strings);
                continue;
            }

            if (depth == depths.peek()) {
                printClosingTag(strings, depths);
                strings.push(temp);
                depths.push(depth);
                printOpeningTag(temp, strings);
                continue;
            }
            strings.push(temp);
            depths.push(depth);
            printOpeningTag(temp, strings);

        }
        while (!strings.isEmpty()) {
            printClosingTag(strings, depths);
        }
    }

    private static void printClosingTag(Stack<String> strings, Stack<Integer> depths) {
        StringBuilder builder = new StringBuilder();
        int counter = strings.size() - 1;
        while (counter > 0) {
            builder.append(" ");
            counter--;
        }
        builder.append("</" + strings.pop() + ">");
        depths.pop();

        System.out.println(builder);
    }

    private static void printOpeningTag(String temp, Stack<String> strings) {
        StringBuilder builder = new StringBuilder();
        int counter = strings.size() - 1;
        while (counter > 0) {
            builder.append(" ");
            counter--;
        }
        builder.append("<" + temp + ">");
        System.out.println(builder);
    }
}