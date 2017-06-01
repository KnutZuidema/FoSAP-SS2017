import java.util.*;


public class NFA<S, A> {
    private Set<S> s;
    private List<A> a;
    private List<Transition<S, A>> transitions = new LinkedList<>();

    public NFA(){
        this.setS(new HashSet<S>());
        this.setA(new LinkedList<A>());
    }

    public NFA(Set<S> states){
        this.setS(states);
        this.setA(new LinkedList<A>());
    }

    public NFA(List<A> alphabet){
        this.setS(new HashSet<S>());
        this.setA(alphabet);
    }

    public NFA(Set<S> states, List<A> alphabet){
        this.setS(states);
        this.setA(alphabet);
    }

    public Set<S> getS() {
        return s;
    }

    public void setS(Set<S> s) {
        this.s = s;
    }

    public List<A> getA() {
        return a;
    }

    public void setA(List<A> a) {
        this.a = a;
    }

    public List<Transition<S, A>> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition<S, A>> transitions) {
        this.transitions = transitions;
    }

    public void addTransition(S q, A a, S p){
        Transition<S, A> transition = new Transition<>(q, a, p);
        this.transitions.add(transition);

    }

    public Set<S> simulate(S q, List<A> w){
        Set<S> reachable = new HashSet<>();
        simulateHelp(q, w, reachable);
        return reachable;
    }

    private void simulateHelp(S q, List<A> w, Set<S> reachable){
        int[] path = new int[0];
        path = Arrays.copyOf(path, path.length + 1);
        path[path.length - 1] = (Integer) q;
        if (w.size() >= 2) {
            try {
                for (A a : w) {
                    w.remove(0);
                    Transition<S, A> currentTrans = new Transition<>(q, a);
                    for (Transition<S, A> transition : transitions) {
                        if (transition.equals(currentTrans)) {
                            simulateHelp(transition.getDestination(), w, reachable);
                        }
                    }
                }
            }catch(ConcurrentModificationException ignored){}
        }else{
            Transition<S, A> currentTrans = new Transition<>(q, w.get(0));
            for (Transition<S, A> transition : transitions){
                if (transition.equals(currentTrans)){
                    if(!reachable.contains(transition.getDestination())){
                        reachable.add(transition.getDestination());
                    }
                }
            }
        }

    }


}