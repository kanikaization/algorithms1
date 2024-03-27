package assign2;

public class Assign2 {

    public static void main(String[] args) {
        WeightedQuickUnionPathCompression qu = new WeightedQuickUnionPathCompression(10);
        qu.printNodes();
        qu.union(4,3);
        qu.union(3,8);
        qu.union(6,5);
        qu.union(9,4);
        qu.union(2,1);
        qu.union(5,0);
        qu.union(7,2);
        qu.union(6,1);
        qu.union(7,3);
        qu.printNodes();
    }
    public class QuickUnionUF {

        protected int[] nodes;
        protected int[] sz;

        public QuickUnionUF(int n) {
            nodes = new int[n];
            for(int i =0; i<n; i++) {
                nodes[i] = i;
            }
        }

        public QuickUnionUF() {
        }

        public boolean connected(int i, int j) {
            return root(i) == root(j);
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

        public void printNodes() {
            for(int i=0; i<nodes.length; i++) {
                System.out.print(nodes[i]);
            }
            System.out.println("----------");
        }


    }

}
