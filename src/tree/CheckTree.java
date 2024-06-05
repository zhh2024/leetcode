package tree;

import java.util.HashMap;


/**
 * @Desc: 检查是否是一棵树
 * <p>
 * 1. 单节点或空节点也是树
 * 2. 寻找根节点,根节点有且仅有一个
 * 3. 通过根节点寻找子树和子树关系(这个好难！！！)
 * 4. 求子树是否有交集
 * 5. 求子树关系是否有交集
 * 6. 将子树进行递归
 * @Author：zhh
 * @Date：2024/5/30 11:19
 */
public class CheckTree {

    public static void main(String[] args) {
        int[] d = {1, 2, 3, 4, 5, 6, 7, 8};
        int[][] h = {
                {1, 2}, {1, 4}, {1, 8},
                {2, 3}, {2, 5},
                {4, 6}, {4, 7}
        };
        System.out.println(checkTree(d, h));
    }

    public static Boolean checkTree(int[] d, int[][] h) {
        //1.单节点或空节点也是树
        if (d.length < 2) {
            return true;
        }
        int root = 0;
        //2.寻找根节点,根节点有且仅有一个
        for (int i = 0; i < h.length; i++) {
            //前驱节点不是后驱节点就是根节点
            int pNode = h[i][0];
            for (int j = 0; j < h.length; j++) {
                if (pNode == h[j][1]) {
                    pNode = -1;
                    break;
                }
            }
            if (pNode != -1 && root != 0 && pNode != root) {
                return false;
            }
            if (pNode != -1 && root == 0) {
                root = pNode;
            }
        }
        //3. 通过根节点寻找子树和子树关系
        HashMap<int[], int[][]> cTrees = new HashMap<>();
        for (int i = 0; i < h.length; i++) {
            findCRoot(root, h);
        }
        //4. 求子树是否有交集

        //5. 求子树关系是否有交集

        //6. 将子树进行递归
        return true;
    }

    public static void findCRoot(int root, int[][] h) {
        for (int i = 0; i < h.length; i++) {
            if (root == h[i][0]) {
                //子树的根节点
                Integer cRoot = h[i][1];
                //寻找子树的其余节点
                findCRoot(cRoot, h);
            }
        }
    }

}

