public class Transition<S, A> {
    private S start;
    private S destination;
    private A word;

    public Transition(S start, A word){
        this.start = start;
        this.word = word;
        this.destination = null;
    }

    public Transition(S start, A word, S destination){
        this.start = start;
        this.word = word;
        this.destination = destination;
    }

    public S getStart() {
        return start;
    }

    public void setStart(S start) {
        this.start = start;
    }

    public S getDestination() {
        return destination;
    }

    public void setDestination(S destination) {
        this.destination = destination;
    }

    public A getWord() {
        return word;
    }

    public void setWord(A word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transition<?, ?> that = (Transition<?, ?>) o;

        if (getStart() != null ? !getStart().equals(that.getStart()) : that.getStart() != null) return false;
        return getWord() != null ? getWord().equals(that.getWord()) : that.getWord() == null;
    }

    @Override
    public int hashCode() {
        int result = getStart() != null ? getStart().hashCode() : 0;
        result = 31 * result + (getWord() != null ? getWord().hashCode() : 0);
        return result;
    }
}
