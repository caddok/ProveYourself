package linearstructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeImportance {
    public static void main(String[] args) {
        int[][] mock = {{1, 5, 2, 3}, {2, 3}, {3, 3}};
        List<Employee> employeeList = new ArrayList<>();
        for (int[] emp : mock) {
            Employee newOne = new Employee();
            newOne.setId(emp[0]);
            newOne.setImportance(emp[1]);
            if (emp.length >= 2) {
                for (int i = 2; i < emp.length; i++) {
                    newOne.getSubordinates().add(emp[i]);
                }
                employeeList.add(newOne);
            } else {
                employeeList.add(newOne);
            }
        }
        int mockId = 1;
        int result = getImportance(employeeList, mockId);
        System.out.println(result);
    }

    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeeMap = employees
                .stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));

        Employee employee = employeeMap.get(id);
        return getImportance(employee, employeeMap);
    }

    public static int getImportance(Employee employee, Map<Integer, Employee> all) {

        int total = employee.getImportance();
        for (int i = 0; i < employee.getSubordinates().size(); i++) {
            final int k = i;
            Employee sub = all.get(employee.getSubordinates().get(k));
            total += getImportance(sub, all);
        }
        return total;
    }


    static class Employee {
        int id;
        int importance;
        List<Integer> subordinates;

        Employee() {
            subordinates = new ArrayList<>();
            setId(id);
            setImportance(importance);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImportance() {
            return importance;
        }

        public void setImportance(int importance) {
            this.importance = importance;
        }

        public List<Integer> getSubordinates() {
            return subordinates;
        }
    }
}
