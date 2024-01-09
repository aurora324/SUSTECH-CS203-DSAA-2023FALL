package lab10;

import java.io.*;
import java.util.*;

public class Question6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int k = in.nextInt();
        ArrayList<Integer>[] roads = new ArrayList[n + 1];
        ArrayList<Long>[] roadsWeights = new ArrayList[n + 1];
        ArrayList<Integer>[] portals = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            roads[i] = new ArrayList<>();
            roadsWeights[i] = new ArrayList<>();
            portals[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            long w = in.nextLong();
            roads[u].add(v);
            roadsWeights[u].add(w);
        }
        for (int i = 0; i < p; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            portals[u].add(v);
        }
        int s = in.nextInt();
        int t = in.nextInt();
        long[] dist = new long[n * (k + 1) + 1];//1~n:dist[i][0]...
        for (int i = 0; i < dist.length; i++) {
            dist[i] = (i == s) ? 0 : Long.MAX_VALUE / 2;
        }
        MinHeap heap = new MinHeap(dist, n * (k + 1) + 1);
        for (int i = 0; i < dist.length; i++) {
            heap.add(i);
        }
        int iter = s;
        while (!heap.isEmpty()) {
            heap.removeMin();
            for (int i = 0; i < roads[iter].size(); i++) {
                for (int j = 0; j < k + 1; j++) {//0~k
                    if (roadsWeights[iter].get(i) + dist[iter + j * n] < dist[roads[iter].get(i) + j * n]) {
                        dist[roads[iter].get(i) + j * n] = roadsWeights[iter].get(i) + dist[iter + j * n];
                        heap.reBalance(roads[iter].get(i) + j * n);
                    }
                }
            }
            for (int i = 0; i < portals[iter].size(); i++) {
                for (int j = 0; j < k; j++) {
                    if (dist[iter + j * n] < dist[portals[iter].get(i) + (j + 1) * n]) {
                        dist[portals[iter].get(i) + (j + 1) * n] = dist[iter + j * n];
                        heap.reBalance(portals[iter].get(i) + (j + 1) * n);
                    }
                }
            }
            if (!heap.isEmpty()) {
                iter = (heap.getMin() % n == 0) ? n : heap.getMin() % n;
            }
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < k + 1; i++) {
            ans = Math.min(dist[t + i * n], ans);
        }
        System.out.println(ans);

    }
}

class MinHeap {
    public MinHeap(long[] dist, int n) {
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
                values.set(iterIndex / 2, values.get(iterIndex));
                indexes[values.get(iterIndex)] = iterIndex / 2;
                values.set(iterIndex, tmp);
                indexes[tmp] = iterIndex;
                iterIndex = iterIndex / 2;
            } else break;
        }
    }

    public int removeMin() {
        int min = values.get(1);
        values.set(1, values.get(values.size() - 1));
        indexes[values.get(values.size() - 1)] = 1;
        values.remove(values.size() - 1);
        int iter = 1;
        while (iter * 2 < values.size()) {
            if (values.size() == iter * 2 + 1) {
                if (dist[values.get(iter * 2)] < dist[values.get(iter)]) {
                    int tmp = values.get(iter * 2);
                    values.set(iter * 2, values.get(iter));
                    indexes[values.get(iter)] = iter * 2;
                    values.set(iter, tmp);
                    indexes[tmp] = iter;
                    iter = iter * 2;
                } else break;
            } else {
                if (dist[values.get(iter * 2)] <= dist[values.get(iter * 2 + 1)]) {
                    if (dist[values.get(iter)] > dist[values.get(iter * 2)]) {
                        int tmp = values.get(iter * 2);
                        values.set(iter * 2, values.get(iter));
                        indexes[values.get(iter)] = iter * 2;
                        values.set(iter, tmp);
                        indexes[tmp] = iter;
                        iter = iter * 2;
                    } else break;
                } else {
                    if (dist[values.get(iter)] > dist[values.get(iter * 2 + 1)]) {
                        int tmp = values.get(iter * 2 + 1);
                        values.set(iter * 2 + 1, values.get(iter));
                        indexes[values.get(iter)] = iter * 2 + 1;
                        values.set(iter, tmp);
                        indexes[tmp] = iter;
                        iter = iter * 2 + 1;
                    } else break;
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
                values.set(iter, values.get(iter / 2));
                indexes[values.get(iter / 2)] = iter;
                values.set(iter / 2, tmp);
                indexes[tmp] = iter / 2;
                iter = iter / 2;
            } else break;
        }
    }

    public int getMin() {
        return values.get(1);
    }
}