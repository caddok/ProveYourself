import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

interface SupermarketDatabase {
    void appendAtTheEndOfQueue(String name);

    boolean insert(int position, String name);

    int find(String name);

    List<Person> serve(int count);
}

public class SupermarketQueue {
    public static final String okResult = "OK";
    public static final String error = "Error";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        String[] command = in.readLine().split(" ");
        SupermarketDatabase supermarket = new Supermarket();
        while (!command[0].equals("End")) {
            switch (command[0]) {
                case "Append":
                    String nameToAppend = command[1];
                    supermarket.appendAtTheEndOfQueue(nameToAppend);
                    out.write(okResult);
                    out.write("\n");
                    break;
                case "Insert":
                    int position = Integer.parseInt(command[1]);
                    String nameToInsert = command[2];
                    boolean result = supermarket.insert(position, nameToInsert);
                    if (result) {
                        out.write(okResult);
                        out.write("\n");
                    } else {
                        out.write(error);
                        out.write("\n");
                    }
                    break;
                case "Find":
                    String nameToFind = command[1];
                    int countOfNames = supermarket.find(nameToFind);
                    out.write(String.valueOf(countOfNames));
                    out.write("\n");
                    break;
                case "Serve":
                    int peopleToBeServed = Integer.parseInt(command[1]);
                    List<Person> toBeServed = supermarket.serve(peopleToBeServed);
                    if (toBeServed.size() == 0) {
                        out.write(error);
                        out.write("\n");
                    } else {
                        for (int i = 0; i < toBeServed.size(); i++) {
                            Person nextInQueue = toBeServed.get(i);
                            out.write(nextInQueue.toString());

                            out.write(i == toBeServed.size() - 1 ? "\n" : " ");
                        }
                    }
                    break;
            }
            String nextCommand = in.readLine();
            command = nextCommand.split(" ");
        }
        out.flush();
    }
}

class Person {
    private String name;

    Person(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s", getName());
    }
}

class Supermarket implements SupermarketDatabase {
    private Map<String, Integer> countOfPersonsByName;
    private List<Person> queue;

    public Supermarket() {
        countOfPersonsByName = new HashMap<>();
        queue = new LinkedList<>();
    }

    @Override
    public void appendAtTheEndOfQueue(String name) {
        Person person = new Person(name);
        queue.add(person);
        countOfPersonsByName.putIfAbsent(person.getName(), 0);
        countOfPersonsByName.computeIfPresent(person.getName(), (v, k) -> k + 1);
    }

    @Override
    public boolean insert(int position, String name) {
        Person person = new Person(name);
        countOfPersonsByName.putIfAbsent(person.getName(), 0);
        countOfPersonsByName.computeIfPresent(person.getName(), (v, k) -> k + 1);
        if (position == 0) {
            queue.add(position, person);
            return true;
        }else if (position > queue.size()) {
            return false;
        } else if (position == queue.size()) {
            queue.add(person);
            return true;
        } else {
            queue.add(position, person);
            return true;
        }
    }

    @Override
    public int find(String name) {
        return countOfPersonsByName.getOrDefault(name, 0);
    }

    @Override
    public List<Person> serve(int count) {
        if (count > queue.size()) {
            return new ArrayList<>();
        } else {
            List<Person> toBeServed = queue.stream()
                    .limit(count)
                    .collect(Collectors.toList());
            for (int i = 0; i < count; i++) {
                String key = queue.get(0).getName();
                countOfPersonsByName.computeIfPresent(key, (k, v) -> v - 1);
                queue.remove(0);
            }
            return toBeServed;
        }
    }
}