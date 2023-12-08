package tour;

public class Participants {
    private int adults;
    private int children;

    public Participants(int adults, int children) {
        this.adults = adults;
        this.children = children;
    }

    public int getAdults() { return adults; }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Взрослых: " + adults + ", Детей: " + children;
    }
}

