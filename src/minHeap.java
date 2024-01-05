import java.util.ArrayList;

class minHeap {
    public minHeap(long[] dist,int n) {
        values.add(-1);
        this.dist = dist;
        indexes = new int[n + 1];
    }
    private ArrayList<Integer> values = new ArrayList<>();
    private long[] dist;
    private int[] indexes;
    public boolean isEmpty() {
        return values.size() == 1;
    }
    public void add(int val) {
        values.add(val);
        indexes[val] = values.size() - 1;
        int iterIndex = values.size() - 1;
        while (iterIndex > 1) {
            if (dist[values.get(iterIndex / 2)] > dist[values.get(iterIndex)]) {
                int tmp = values.get(iterIndex / 2);
                values.set(iterIndex / 2,values.get(iterIndex));
                indexes[values.get(iterIndex)] = iterIndex / 2;
                values.set(iterIndex,tmp);
                indexes[tmp] = iterIndex;
                iterIndex = iterIndex / 2;
            }
            else break;
        }
    }
    public int removeMin() {
        int min = values.get(1);
        values.set(1,values.get(values.size() - 1));
        indexes[values.get(values.size() - 1)] = 1;
        values.remove(values.size() - 1);
        int iter = 1;
        while (iter * 2 < values.size()) {
            if (values.size() == iter * 2 + 1) {
                if (dist[values.get(iter * 2)] < dist[values.get(iter)]) {
                    int tmp = values.get(iter*2);
                    values.set(iter*2,values.get(iter));
                    indexes[values.get(iter)] = iter * 2;
                    values.set(iter,tmp);
                    indexes[tmp] = iter;
                    iter = iter * 2;
                }
                else break;
            }
            else {
                if (dist[values.get(iter*2)]<=dist[values.get(iter*2+1)]) {
                    if (dist[values.get(iter)] > dist[values.get(iter*2)]) {
                        int tmp = values.get(iter*2);
                        values.set(iter*2,values.get(iter));
                        indexes[values.get(iter)] = iter * 2;
                        values.set(iter,tmp);
                        indexes[tmp] = iter;
                        iter = iter * 2;
                    }
                    else break;
                }
                else {
                    if (dist[values.get(iter)] > dist[values.get(iter*2+1)]) {
                        int tmp = values.get(iter*2+1);
                        values.set(iter*2+1,values.get(iter));
                        indexes[values.get(iter)] = iter * 2 + 1;
                        values.set(iter,tmp);
                        indexes[tmp] = iter;
                        iter = iter * 2 + 1;
                    }
                    else break;
                }
            }
        }
        return min;
    }
    public void reBalance(int val) {

        int iter = indexes[val];
        while (iter > 1) {
            if (dist[values.get(iter)] < dist[values.get(iter / 2)]) {
                int tmp = values.get(iter);
                values.set(iter,values.get(iter / 2));
                indexes[values.get(iter / 2)] = iter;
                values.set(iter / 2,tmp);
                indexes[tmp] = iter / 2;
                iter = iter / 2;
            }
            else break;
        }
    }

    public int getMin() {
        return values.get(1);
    }
}