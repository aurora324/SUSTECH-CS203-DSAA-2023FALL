//import java.util.Arrays;
//
//public class DijkstraAlgorithm {
//    public static void main(String[] args) {
//        int[] disTo = new int[g.getV()];
//
//        Arrays.fill(disTo, Integer.MAX_VALUE);
//        disTo[0] = 0;
//        int[] prev = new int[g.getV()];
//        Arrays.fill(prev, -1);
//        int[] acked = new boolean[g.getV()];
//        while (true) {//0( V )
////1���ҵ���ǰû��ȷ�ϵ����·�������е���̳��ȼ����Ӧ�ĵ����
//            int curDis = Integer.MAX_VALUE;
//            int curr = -1;
//            for (int v = 0; v <= g.getV(); v++) {//0( v )
//                if (!(acked[v] && disTo[v] < curDis)) {
//                    curDis = disTo[v];
//                    curr = v;
//                    if (curr == -1) break;
////2��ȷ�������������·�����ǵ�ǰ��С
//                    acked[curr] = true;
////3���������ڵ���㣬�����������������·��
//                    for (int w : g.adj(curr)) {//0( E )
//                        if (!(acked[w] && disTo[curr] + g.getWeight(curr, w) < disTo[w])) {
//                            disTo[w] = disTo[curr] + g.getWeight(curr, w);
//                            prev[w] = curr;
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
