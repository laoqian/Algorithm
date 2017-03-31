import java.util.*;

/**
 * Created by zuoan on 2017/3/29.
 *
 * 图的最短路径Dijkstra算法。
 *
 * a.初始时，S只包含源点，即S＝{v}，v的距离为0。U包含除v外的其他顶点，即:U={其余顶点}，若v与U中顶点u有边，则<u,v>正常有权值，若u不是v的出边邻接点，则<u,v>权值为∞。

 *b.从U中选取一个距离v最小的顶点k，把k，加入S中（该选定的距离就是v到k的最短路径长度）。

 *c.以k为新考虑的中间点，修改U中各顶点的距离；若从源点v到顶点u的距离（经过顶点k）比原来距离（不经过顶点k）短，则修改顶点u的距离值，修改后的距离值的顶点k的距离加上边上的权。

 *d.重复步骤b和c直到所有顶点都包含在S中。
 */

public class Dijkstra{

    static int[][] map_init(int lv,int percent){
        int[][]  map = new int[lv][lv];
        Random random = new Random();
        int max = 100;
        String s;

        for(int i=0;i<lv;i++){
            s = "";
            for(int j=0;j<lv;j++){
                if(i==j){
                    map[i][j] = Integer.MAX_VALUE;
                }else{
                    map[i][j] = random.nextInt(max)<percent?random.nextInt(max):Integer.MAX_VALUE;
                }

//                s +=" "+(map[i][j]==Integer.MAX_VALUE?0:map[i][j]);
//                if(j==lv-1){
//                    System.out.println(s+'\n');
//                }
            }
        }

        return map;
    }

    static String getChar(int i){
      return "("+'P'+i+")";
    }

    static List getPathList(int a, int lv) throws InterruptedException {

        int[][] map = map_init(lv,30);
        boolean[] S = new boolean[lv];
        int path[] = new int [lv]; /*最短路径集合*/
        List<String> result = new LinkedList<String>(); /*最短路径路线图*/
        int minPath;
        int k=0;

//        lv = lv>20?20:lv; /*最大路径20*/

        for(int i=0;i<lv;i++){
            path[i] = map[a][i];

            S[i] = false;
            if(path[i]>0){
                result.add(getChar(a)+"->"+getChar(i)+"("+path[i]+")");
            }else{
                result.add(getChar(a));
            }
        }

        S[a]=true;

        for(int i=0;i<lv;i++){

            minPath =Integer.MAX_VALUE;
            /*找到距离A最近的点*/
            for(int j=0;j<lv;j++){

                /*找到最短距离的点*/
                if(!S[j] && path[j]<minPath){
                    k = j;
                    minPath = path[j];
                }
            }


            S[k] = true; /*这时到K的距离就是最短*/

            for(int j=0;j<lv;j++){

                if(!S[j] && map[k][j]!=0){

                    /*如果通过中间点K到j的距离小于原来a->j的距离,更新*/
                    if(!S[j] && map[k][j]!=Integer.MAX_VALUE && path[k]+map[k][j]<path[j]){

                        path[j] = path[k]+map[k][j];

                        String s = result.get(k) +"->"+getChar(j)+"("+map[k][j]+")";
//                        System.out.println("计算："+s);
                        result.remove(j);
                        result.add(j,s); /*设置路线图*/
                    }
                }

            }
        }

        return result;
    }


    public static void main(String[] args) throws InterruptedException {
        List<String> pathList =   getPathList(0,20);

        for(String s:pathList){
            System.out.println(s);
        }

    }

}
