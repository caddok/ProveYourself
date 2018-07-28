import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

interface HeroDatabase {
    boolean addHero(String[] characteristics);

    boolean remove(String name);

    List<Hero> find(String nameOrType);

    List<Hero> getHeroesOrderedByPower(int count);
}

public class UnitsOfWork {
    private static final String SUCCESS = "SUCCESS:";
    private static final String FAIL = "FAIL:";
    private static final String RESULT = "RESULT:";
    private static final StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String commandString = in.readLine();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        HeroDatabase database = new DefaultHeroDatabase();
        while (!commandString.equals("end")) {
            String[] brokenDown = commandString.split(" ");
            switch (brokenDown[0]) {
                case "add":
                    if (database.addHero(brokenDown)) {
                        out.write(SUCCESS);
                        out.write(" ");
                        out.write(brokenDown[1]);
                        out.write(" added!");
                        out.write("\n");
                    } else {
                        out.write(FAIL);
                        out.write(" ");
                        out.write(brokenDown[1]);
                        out.write(" already exists!");
                        out.write("\n");
                    }
                    break;
                case "remove":
                    String nameToRemove = brokenDown[1];
                    if (database.remove(nameToRemove)) {

                        out.write(SUCCESS);
                        out.write(" ");
                        out.write(brokenDown[1]);
                        out.write(" removed!");
                        out.write("\n");

                    } else {

                        out.write(FAIL);
                        out.write(" ");
                        out.write(brokenDown[1]);
                        out.write(" could not be found!");
                        out.write("\n");
                    }
                    break;
                case "find":
                    List<Hero> found = database.find(brokenDown[1]);
                    if (found.size() > 0) {
                        out.write(RESULT);
                        out.write(" ");
                        for (int i = 0; i < found.size(); i++) {
                            out.write(found.get(i).toString());
                            out.write(i == found.size() - 1 ? "\n" : ", ");
                        }
                    } else {
                        out.write(RESULT);
                        out.write(" ");
                        out.write("\n");
                    }
                    break;
                case "power":
                    List<Hero> heroesByPower = database.getHeroesOrderedByPower(Integer.parseInt(brokenDown[1]));
                    out.write(RESULT);
                    out.write(" ");
                    for (int i = 0; i < heroesByPower.size(); i++) {
                        Hero heroToShow = heroesByPower.get(i);
                        out.write(heroToShow.toString());
                        out.write(i == heroesByPower.size() - 1 ? "\n" : ", ");
                    }
                    break;
            }
            commandString = in.readLine();
        }
        out.flush();
    }
}

class Hero implements Comparable<Hero> {
    private String name;
    private String type;
    private int attack;

    public Hero() {
        setName(name);
        setType(type);
        setAttack(attack);
    }

    public Hero(String name, String type, int attack) {
        setName(name);
        setType(type);
        setAttack(attack);
    }

    public Hero(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return String.format("%s[%s](%d)", getName(), getType(), getAttack());
    }

    @Override
    public int compareTo(Hero other) {
        if (this.getAttack() != other.getAttack()) {
            return other.getAttack() - this.getAttack();
        }

        return this.getName().compareTo(other.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Hero)) {
            return false;
        }
        return ((Hero) other).getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}

class DefaultHeroDatabase implements HeroDatabase {
    private Map<String, Hero> allHeroes;
    private Set<Hero> heroesByPower;
    private Map<String, Set<Hero>> heroesByTypeSortedByPower;

    public DefaultHeroDatabase() {
        allHeroes = new HashMap<>();
        heroesByPower = new TreeSet<>();
        heroesByTypeSortedByPower = new HashMap<>();
    }

    @Override
    public List<Hero> getHeroesOrderedByPower(int count) {
        return heroesByPower.parallelStream()
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addHero(String[] characteristics) {
        Hero hero = new Hero(characteristics[1], characteristics[2], Integer.parseInt(characteristics[3]));
        Hero result = allHeroes.putIfAbsent(hero.getName(), hero);

        if (result == null) {
            heroesByPower.add(hero);
            heroesByTypeSortedByPower.putIfAbsent(hero.getType(), new TreeSet<>());
            heroesByTypeSortedByPower.computeIfPresent(hero.getType(), (k, v) -> {
                v.add(hero);
                return v;
            });
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String name) {
        boolean existsInMap = allHeroes.containsKey(name);

        if (existsInMap) {
            Hero toBeRemoved = allHeroes.get(name);
            allHeroes.remove(name);
            heroesByTypeSortedByPower.get(toBeRemoved.getType()).remove(toBeRemoved);
            heroesByPower.remove(toBeRemoved);
        }
        return existsInMap;
    }

    @Override
    public List<Hero> find(String nameOrType) {
        if (isType(nameOrType)) {
            return findFromType(nameOrType);
        } else if (isHeroName(nameOrType)) {
            Hero res = allHeroes.get(nameOrType);
            List<Hero> resList = new ArrayList<>();
            resList.add(res);
            return resList;
        } else {
            return new ArrayList<>();
        }
    }

    private boolean isType(String nameOrType) {
        return heroesByTypeSortedByPower.containsKey(nameOrType);
    }

    private boolean isHeroName(String name) {
        return allHeroes.containsKey(name);
    }

    private List<Hero> findFromType(String type) {
        if (heroesByTypeSortedByPower.get(type).size() == 0) {
            return new ArrayList<>();
        } else if (heroesByTypeSortedByPower.get(type).size() < 10) {
            return new ArrayList<>(heroesByTypeSortedByPower.get(type));
        } else {
            return heroesByTypeSortedByPower
                    .get(type)
                    .parallelStream()
                    .filter(e -> allHeroes.containsKey(e.getName()))
                    .limit(10)
                    .collect(Collectors.toList());
        }
    }
}