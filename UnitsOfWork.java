import java.util.*;
import java.util.stream.Collectors;

interface HeroDatabase {
    boolean addHero(String[] characteristics);

    boolean remove(String name);

    List<Hero> find(String nameOrType);

    PriorityQueue<Hero> getHeroesOrderedByPower();
}

public class UnitsOfWork {
    private static final String SUCCESS = "SUCCESS:";
    private static final String FAIL = "FAIL:";
    private static final String RESULT = "RESULT:";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String commandString = in.nextLine();
        HeroDatabase database = new DefaultHeroDatabase();
        while (!commandString.equals("end")) {
            String[] brokenDown = commandString.split(" ");
            switch (brokenDown[0]) {
                case "add":
                    Hero hero = new Hero();
                    if (database.addHero(brokenDown)) {
                        System.out.println(SUCCESS + " " + brokenDown[1] + " added!");
                    } else {
                        System.out.println(FAIL + " " + brokenDown[1] + " already exists!");
                    }
                    break;
                case "remove":
                    String nameToRemove = brokenDown[1];
                    if (database.remove(nameToRemove)) {
                        System.out.println(SUCCESS + " " + nameToRemove + " removed!");
                    } else {
                        System.out.println(FAIL + " " + nameToRemove + " could not be found!");
                    }
                    break;
                case "find":
                    List<Hero> found = database.find(brokenDown[1]);
                    if (found.size() > 0) {
                        System.out.print(RESULT + " ");
                        for (int i = 0; i < found.size(); i++) {
                            if (i == found.size() - 1) {
                                System.out.print(found.get(i).toString());
                            } else {
                                System.out.print(found.get(i).toString() + ", ");
                            }
                        }
                        System.out.println();
                    } else {
                        System.out.println(RESULT + " ");
                    }
                    break;
                case "power":
                    PriorityQueue<Hero> copied = new PriorityQueue<>(database.getHeroesOrderedByPower());
                    System.out.print(RESULT + " ");
                    int size = Integer.parseInt(brokenDown[1]);
                    for (int i = 0; i < size; i++) {
                        Hero heroToShow = copied.peek();
                        if (i == size - 1) {
                            System.out.print(heroToShow.toString());
                        } else {
                            System.out.print(heroToShow.toString() + ", ");
                        }
                        copied.remove();
                    }
                    System.out.println();
                    break;
            }
            commandString = in.nextLine();
        }
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
        return this.getAttack() - other.getAttack();
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
    private Map<String, SortedSet<Hero>> heroesByTypeSortedByPower;
    private Set<Hero> allHeroes;
    private PriorityQueue<Hero> heroesOrderedByPower;

    public DefaultHeroDatabase() {
        heroesByTypeSortedByPower = new Hashtable<>();
        allHeroes = new HashSet<>();
        heroesOrderedByPower = new PriorityQueue<>(Comparator.comparing(Hero::getAttack).reversed());
    }

    @Override
    public PriorityQueue<Hero> getHeroesOrderedByPower() {
        return new PriorityQueue<>(heroesOrderedByPower);
    }

    @Override
    public boolean addHero(String[] characteristics) {
        Hero hero = new Hero(characteristics[1], characteristics[2], Integer.parseInt(characteristics[3]));
        if (allHeroes.contains(hero)) {
            return false;
        }

        allHeroes.add(hero);
        if (heroesByTypeSortedByPower.containsKey(hero.getType())) {
            heroesByTypeSortedByPower.get(characteristics[2]).add(hero);
        } else {
            heroesByTypeSortedByPower.put(hero.getType(), new TreeSet<>());
            heroesByTypeSortedByPower.get(hero.getType()).add(hero);
        }
        allHeroes.add(hero);
        heroesOrderedByPower.offer(hero);
        return true;

    }

    @Override
    public boolean remove(String name) {
        allHeroes.removeIf(hero -> hero.getName().equals(name));
        heroesByTypeSortedByPower.
        heroesOrderedByPower.remove(hero.get(0));
    }

    @Override
    public List<Hero> find(String nameOrType) {
        if (isHeroName(nameOrType)) {
            return allHeroes.stream()
                    .filter(hero -> hero.getName().equals(nameOrType))
                    .collect(Collectors.toList());
        } else {
            return findFromType(nameOrType);
        }
    }

    private boolean isHeroName(String name) {
        return heroNames.contains(name);
    }

    private List<Hero> findFromType(String type) {
        PriorityQueue<Hero> specificList;
        if (!heroesByTypeSortedByPower.containsKey(type)) {
            return new ArrayList<>();
        } else {
            specificList = heroesByTypeSortedByPower.get(type);
            if (specificList.size() > 10) {
                return specificList.stream()
                        .limit(10)
                        .collect(Collectors.toList());
            } else {
                return new ArrayList<>(new ArrayList<>(specificList));
            }
        }
    }
}
