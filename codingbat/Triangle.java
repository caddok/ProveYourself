package codingbat;

public class Triangle {
    public static void main(String[] args) {
        int rows = 3;
        System.out.println(triangle(rows));
    }
    public static int triangle(int rows) {
        if (rows == 0) {
            return rows;
        }
        return rows + triangle(rows - 1);
    }
}
