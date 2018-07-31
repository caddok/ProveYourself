package exam;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OnlineMarket {
    public static final String ADDED_PRODUCT = "Ok: Product";
    public static final String ADDED_PRODUCT_FAILED = "Error: Product";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        OutputWriter out = new OutputWriter();
        run(in, out);
        out.close();
    }

    public static void run(BufferedReader in, OutputWriter out) throws IOException {
        OnlineDatabase market = new Market();
        String[] command = in.readLine().split(" ");
        while (!command[0].equals("end")) {
            switch (command[0]) {
                case "add":
                    StringBuilder resultOfAdd = new StringBuilder();
                    if (market.addProduct(command[1], Double.parseDouble(command[2]), command[3])) {
                        resultOfAdd.append(ADDED_PRODUCT + " " + command[1] + " " + "added successfully");
                        out.printLine(resultOfAdd);
                    } else {
                        resultOfAdd.append(ADDED_PRODUCT_FAILED + " " + command[1] + " " + "already exists");
                        out.printLine(resultOfAdd);
                    }
                    break;
                case "filter":
                    if (command[2].equals("type")) {
                        List<Product> selected = market.filterByType(command[3]);
                        if (selected.size() > 0) {
                            out.print("Ok: ");
                            for (int i = 0; i < selected.size(); i++) {
                                if (i == selected.size() - 1) {
                                    out.print(selected.get(i).productAsString);
                                } else {
                                    out.print(selected.get(i).productAsString + ", ");
                                }
                            }
                            out.printLine();
                        } else {
                            StringBuilder error = new StringBuilder();
                            error.append("Error: Type " + command[3] + " " + "does not exists");
                            out.printLine(error);
                        }
                    } else {
                        if (command[3].equals("to")) {
                            List<Product> selected = market.filterByMaxPrice(Double.parseDouble(command[4]));
                            if (selected.size() > 0) {
                                out.print("Ok: ");
                                for (int i = 0; i < selected.size(); i++) {
                                    if (i == selected.size() - 1) {
                                        out.print(selected.get(i).productAsString);
                                    } else {
                                        out.print(selected.get(i).productAsString + ", ");
                                    }
                                }
                                out.printLine();
                            } else {
                                out.printLine("Ok: ");
                            }
                        } else if (command[3].equals("from") && command.length == 5) {
                            List<Product> selected = market.filterByMinPrice(Double.parseDouble(command[4]));
                            if (selected.size() > 0) {
                                out.print("Ok: ");
                                for (int i = 0; i < selected.size(); i++) {
                                    if (i == selected.size() - 1) {
                                        out.print(selected.get(i).productAsString);
                                    } else {
                                        out.print(selected.get(i).productAsString + ", ");
                                    }
                                }
                                out.printLine();
                            } else {
                                out.printLine("Ok: ");
                            }
                        } else {
                            double from = Double.parseDouble(command[4]);
                            double to = Double.parseDouble(command[6]);
                            List<Product> selected = market.filterByPriceInRange(from, to);
                            if (selected.size() > 0) {
                                out.print("Ok: ");
                                for (int i = 0; i < selected.size(); i++) {
                                    if (i == selected.size() - 1) {
                                        out.print(selected.get(i).productAsString);
                                    } else {
                                        out.print(selected.get(i).productAsString + ", ");
                                    }
                                }
                                out.printLine();
                            } else {
                                out.printLine("Ok: ");
                            }
                        }
                    }
            }
            command = in.readLine().split(" ");
        }
    }

    interface OnlineDatabase {
        boolean addProduct(String name, double price, String type);

        List<Product> filterByType(String type);

        List<Product> filterByPriceInRange(double from, double to);

        List<Product> filterByMinPrice(double minPrice);

        List<Product> filterByMaxPrice(double maxPrice);
    }

    static class OutputWriter {
        private final PrintWriter writer;

        OutputWriter() {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        }

        void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        void close() {
            writer.close();
        }
    }

    static class Product implements Comparable<Product> {
        private String name;
        private double price;
        private String type;
        private String productAsString;

        public Product(String name, double price, String type) {
            setName(name);
            setPrice(price);
            setType(type);
            productAsString = name + "(" + price + ")";
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        private void setPrice(double price) {
            DecimalFormat format = new DecimalFormat("0.#");
            this.price = Double.parseDouble(format.format(price));
        }

        public String getType() {
            return type;
        }

        private void setType(String type) {
            this.type = type;
        }

        @Override
        public int compareTo(Product product) {
            if (Double.compare(this.getPrice(), product.getPrice()) == 0) {
                if (this.getName().compareTo(product.getName()) == 0) {
                    return this.getType().compareTo(product.getType());
                } else {
                    return this.getName().compareTo(product.getName());
                }
            } else {
                return Double.compare(this.getPrice(), product.getPrice());
            }
        }
    }

    static class Market implements OnlineDatabase {
        private Map<String, Set<Product>> filteredByType;
        private Set<Product> productList;

        public Market() {
            filteredByType = new HashMap<>();
            productList = new TreeSet<>();
        }

        @Override
        public boolean addProduct(String name, double price, String type) {
            Product product = new Product(name, price, type);
            if (!productList.contains(product)) {
                productList.add(product);
                filteredByType.putIfAbsent(product.getType(), new TreeSet<>());
                filteredByType.computeIfPresent(product.getType(), (v, k) -> {
                    k.add(product);
                    return k;
                });
                return true;
            }
            return false;
        }

        @Override
        public List<Product> filterByType(String type) {
            if (filteredByType.containsKey(type)) {
                Set<Product> desired = filteredByType.get(type);
                if (desired.size() > 10) {
                    return desired.stream()
                            .limit(10)
                            .collect(Collectors.toList());
                } else {
                    return new ArrayList<>(desired);
                }
            }
            return new ArrayList<>();
        }

        @Override
        public List<Product> filterByPriceInRange(double from, double to) {
            List<Product> selected = productList.stream()
                    .filter(pr -> pr.getPrice() >= from && pr.getPrice() <= to)
                    .collect(Collectors.toList());
            if (selected.size() > 10) {
                return selected.stream().limit(10).collect(Collectors.toList());
            } else {
                return selected;
            }
        }

        @Override
        public List<Product> filterByMinPrice(double minPrice) {
            List<Product> selected = productList.stream()
                    .filter(pr -> pr.getPrice() >= minPrice)
                    .collect(Collectors.toList());
            if (selected.size() > 10) {
                return selected.stream().limit(10).collect(Collectors.toList());
            } else {
                return selected;
            }
        }

        @Override
        public List<Product> filterByMaxPrice(double maxPrice) {
            List<Product> selected = productList.stream()
                    .filter(product -> product.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
            if (selected.size() > 10) {
                return selected.stream().limit(10).collect(Collectors.toList());
            } else {
                return selected;
            }
        }
    }
}
