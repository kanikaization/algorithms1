package assign2;

public class WeightedQuickUnionPathCompression extends WeightedQuickUnion {

    public WeightedQuickUnionPathCompression(int n){
        super(n);
    }

    public int root(int i) {
        while(nodes[i] != i) {
            nodes[i]=nodes[nodes[i]];
            i = nodes[i];
        }
        return i;
    }

}
