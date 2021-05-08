package EveryMST.Graph;

public class Edge implements Comparable<Edge>{
    private int source;
    private int target;
    private double weight;

    public Edge(int source, int target, double weight){
        this.source = source;
        this.target = target;
        this. weight = weight;
    }

    public Edge(int source, int target){
        this(source, target, 0);
    }

    public Edge(Edge edge){
        this(edge.getSource(), edge.getTarget(), edge.getWeight());
    }

    //get methods
    public int getSource(){
        return source;
    }
    public int getTarget() {
        return target;
    }
    public double getWeight() {
        return weight;
    }

    //set methods
    public void setSource(int source) {
        this.source = source;
    }
    public void setTarget(int target) {
        this.target = target;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void addWeight(double weight){
        this.weight += weight;
    }

    //initialize
    public void init(int source, int target, double weight){
        setSource(source);
        setTarget(target);
        setWeight(weight);
    }

    @Override
    public int compareTo(Edge edge) {
        double diff = weight - edge.weight;
        if (diff > 0) return 1;
        else if (diff < 0) return -1;
        else return 0;
    }

}
