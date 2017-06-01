import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MyNFA {
    private static LinkedList<Transition<Integer, String>> readTransitions() throws IOException{
        LinkedList<Transition<Integer, String>> transitions = new LinkedList<>();
        Transition<Integer, String> transition;
        int q, p;
        String a;
        FileReader reader = new FileReader("H8.trans");
        int x = reader.read();
        char[] value = new char[0];
        while (x != -1) {
            value = Arrays.copyOf(value, value.length + 1);
            value[value.length - 1] = (char) x;
            x = reader.read();
        }
        String b = new String(value);
        String[] t;
        t = b.split("\r\n");
        String[][] tt = new String[t.length][3];
        for (int i = 0; i < t.length; i++){
            tt[i] = t[i].split(" ");
        }for (int i = 0; i < t.length; i++){
            q = Integer.parseInt(tt[i][0]);
            a = tt[i][1];
            p = Integer.parseInt(tt[i][2]);
            transition = new Transition<>(q, a, p);
            transitions.add(transition);
        }

        return transitions;
    }
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        Set<Integer> states = new HashSet<>();
        for(int i = 1; i < 35; i++){
            states.add(i);
        }
        List<String> alphabet = new LinkedList<>();
        alphabet.add("a");
        alphabet.add("b");

//        List<Transition<Integer, String>> trans = new LinkedList<>();
//        Transition<Integer, String> tr0 = new Transition<>(1, "a", 2);
//        Transition<Integer, String> tr1 = new Transition<>(1, "a", 3);
//        Transition<Integer, String> tr2 = new Transition<>(1, "a", 4);
//        Transition<Integer, String> tr3 = new Transition<>(1, "b", 1);
////        Transition<Integer, String> tr4 = new Transition<>(2, "b", 4);
////        Transition<Integer, String> tr5 = new Transition<>(3, "a", 2);
////        Transition<Integer, String> tr6 = new Transition<>(3, "b", 3);
////        Transition<Integer, String> tr7 = new Transition<>(4, "a", 4);
////        Transition<Integer, String> tr8 = new Transition<>(4, "b", 1);
//        trans.add(tr0);
//        trans.add(tr1);
//        trans.add(tr2);
//        trans.add(tr3);
////        trans.add(tr4);
////        trans.add(tr5);
////        trans.add(tr6);
////        trans.add(tr7);
////        trans.add(tr8);

        NFA<Integer, String> myNFA = new NFA<>(states, alphabet);
        myNFA.setTransitions(readTransitions());
        //myNFA.setTransitions(trans);

        List<String> input = new LinkedList<>();
        input.add("a");
        input.add("b");
        input.add("a");
        input.add("b");
        input.add("b");
        input.add("b");
        input.add("a");
        input.add("a");

        Set<Integer> reachable = myNFA.simulate(7, input);

        System.out.println(reachable);

    }
}
