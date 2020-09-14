import java.util.*;

public class Main {

    public static void main(String[] args) {

//        CoolFeatures cf = new CoolFeatures(new int[]{1,2,3}, new int[]{3,4}); // [2,1]
//        cf.query(new int[]{1,5});
//        cf.query(new int[]{0,0,1});
//        cf.query(new int[]{1,5});
        CoolFeatures cf = new CoolFeatures(new int[]{1,2,2},new int[]{2,3}); //[3,4]

        cf.query(new int[]{1,4});
        cf.query(new int[]{0,0,3});
        cf.query(new int[]{1,5});

        for(int n : cf.list)
            System.out.println("->"+n);

    }

    public static class CoolFeatures {

         int[] a;
         int[] b;
         Map<Integer,Integer> map = new HashMap<>();
         List<Integer> list = new ArrayList<>();

        CoolFeatures(int[] a, int[] b){

            this.a=  a;
            this.b = b;

            preProcess(a,b);

        }

        private void preProcess(int[] a, int[] b){

            Arrays.sort(a);

            //count freq in b
            for(int n : b) {
                map.put(n,map.getOrDefault(n,0)+1);
            }

        }

        private void query(int[] q){

            if(q[0]==0) {

                update(q);

            }else if(q[0]==1) {

                list.add(getAns(q));

            }

        }

        private void update(int[] q) {

            //adjust whatever already in index q[1] -> b[q[1]]
            if(map.containsKey(b[q[1]])) {

                int freq = map.get(b[q[1]]);
                freq--;
                map.put(b[q[1]],freq);

            }

            //update b[q[1]]
            b[q[1]] = q[2];
            map.put( b[q[1]] , map.getOrDefault(b[q[1]],0)+1 );

        }

        //find total num of i and j such that sum equals x
        private int getAns(int[] q){

            int x = q[1];

            int res=0;

            for(int i = 0 ; i < a.length ; i++) {

                int leftOVer=   x-a[i];
                if( leftOVer<0) break;

                if(map.containsKey(leftOVer))
                    res += map.get(leftOVer);

            }

            return res;

        }

    }

}
