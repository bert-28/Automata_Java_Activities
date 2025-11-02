import java.util.*;

public class NFASimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        sc.close();

        // NFA states: q0 (start), q1 (saw 'a'), q2 (accept)
        Map<String, Map<Character, Set<String>>> nfa = new HashMap<>();

        // transitions for q0
        nfa.put("q0", new HashMap<>());
        nfa.get("q0").put('a', new HashSet<>(Arrays.asList("q0", "q1")));
        nfa.get("q0").put('b', new HashSet<>(Arrays.asList("q0")));

        // transitions for q1
        nfa.put("q1", new HashMap<>());
        nfa.get("q1").put('b', new HashSet<>(Arrays.asList("q2")));
        nfa.get("q1").put('a', new HashSet<>(Arrays.asList("q1"))); 

        // transitions for q2 (accepting)
        nfa.put("q2", new HashMap<>());
        nfa.get("q2").put('a', new HashSet<>(Arrays.asList("q2")));
        nfa.get("q2").put('b', new HashSet<>(Arrays.asList("q2")));

        String start = "q0";
        Set<String> acceptStates = new HashSet<>(Arrays.asList("q2"));

        if (simulateNFA(nfa, start, acceptStates, input))
            System.out.println("Accepted");
        else
            System.out.println("Rejected");
    }

    // NFA simulation using BFS
    public static boolean simulateNFA(Map<String, Map<Character, Set<String>>> nfa, String start,
                                      Set<String> accept, String input) {
        Set<String> currentStates = new HashSet<>();
        currentStates.add(start);

        for (char c : input.toCharArray()) {
            Set<String> nextStates = new HashSet<>();
            for (String state : currentStates) {
                if (nfa.containsKey(state) && nfa.get(state).containsKey(c)) {
                    nextStates.addAll(nfa.get(state).get(c));
                }
            }
            currentStates = nextStates;
        }

        // check if any final state is in the set of accept states
        for (String s : currentStates) {
            if (accept.contains(s))
                return true;
        }
        return false;
    }
}
