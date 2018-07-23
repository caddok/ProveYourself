package linearstructures;


import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Subdomains {
    public static void main(String[] args) {
        String[] mock = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> results = subdomainVisits(mock).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (String result: results) {
            System.out.println(result);
        }
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        Hashtable<String, Integer> sortedDomains = new Hashtable<>();
        List<Integer> visits = extractVisits(cpdomains);

        for (int i = 0; i < cpdomains.length; i++) {
            String currentDomain = cpdomains[i];
            String stringValueOfDomain = extractFullDomain(currentDomain);

            sortedDomains = extractDomains(stringValueOfDomain, sortedDomains, visits, i);
            if (sortedDomains.containsKey(stringValueOfDomain)) {
                int domainValue = sortedDomains.get(stringValueOfDomain);
                int newValue = domainValue + visits.get(i);
                sortedDomains.replace(stringValueOfDomain, newValue);
            } else {
                sortedDomains.put(stringValueOfDomain, visits.get(i));
            }
        }
        Hashtable<String, Integer> finalSortedDomains = sortedDomains;
        List<String> finalResults = new ArrayList<>();
        sortedDomains.keySet().stream().forEach(key -> finalResults.add(String.format("%d %s", finalSortedDomains.get(key), key)));
        return finalResults;
    }

    private static Hashtable<String,Integer> extractDomains(String stringValueOfDomain,
                                       Hashtable<String,Integer> sorted,
                                       List<Integer> visits,
                                       int currentDomainIndex) {
        Hashtable<String, Integer> result = (Hashtable<String, Integer>) sorted.clone();
        for (int i = 0; i < stringValueOfDomain.length(); i++) {
            if (stringValueOfDomain.charAt(i) != '.') {
                continue;
            } else {
                String newDomain = stringValueOfDomain.substring(i + 1);
                if (result.containsKey(newDomain)) {
                    int domainValue = result.get(newDomain);
                    int newValue = domainValue + visits.get(currentDomainIndex);
                    result.replace(newDomain, newValue);
                } else {
                    result.put(newDomain, visits.get(currentDomainIndex));
                }
            }
        }
        return result;
    }

    private static List<Integer> extractVisits(String[] cpdomains) {
        return Arrays.asList(cpdomains)
               .stream()
               .map(current -> Integer.parseInt(current.split(" ")[0]))
               .collect(Collectors.toList());
    }

    private static String extractFullDomain(String domain) {
        return domain.split(" ")[1];
    }
}
