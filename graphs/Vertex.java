package graphs;

//The structure needed for the graph
public class Vertex {
    private Integer dependantOn;

    public Vertex(Integer to) {
        this.dependantOn = to;
    }

    public Integer getTo() {
        return dependantOn;
    }
}
