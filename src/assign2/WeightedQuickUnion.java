package assign2;

public class WeightedQuickUnion extends QuickUnionUF {

    public WeightedQuickUnion() {}

    public WeightedQuickUnion(int n) {
        nodes = new int[n];
        sz = new int[n];
        for(int i =0; i<n; i++) {
            nodes[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int i, int j) {
        int root_i = root(i);
        int root_j = root(j);
        if(root_i==root_j) return;
        if(sz[i] < sz[j]) {
            nodes[root_i] = root_j;
            sz[j]+=sz[i];
        } else {
            nodes[root_j] = root_i;
            sz[i]+=sz[j];
        }
    }

    public int root(int i) {
        while(nodes[i] != i) {
            nodes[i]=nodes[nodes[i]];
            i = nodes[i];
        }
        return i;
    }
}
