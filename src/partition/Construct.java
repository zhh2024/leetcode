package partition;

/**
 * @Desc: 四叉树
 * @Author：zhh
 * @Date：2025/5/16 22:17
 */
public class Construct {

    public static void main(String[] args) {
        //TODO  有前缀和解法
        int[][] grid = {
                {1,1,0,0},
                {0,0,1,1},
                {1,1,0,0},
                {0,0,1,1}
        };
        Node4 construct = construct(grid, 0, grid[0].length - 1, 0, grid.length - 1);
        System.out.println(construct);
    }

    public static Node4 construct(int[][] grid, int left, int right, int top, int bottom) {
        //1. 递归中止
        if(left == right && top == bottom){
            return new Node4(grid[top][left] == 1, true);
        }
        //2. 取中间
        int rowMid = top  + (bottom - top) / 2;
        int colMid = left  + (right - left) / 2;

        //3. 递归分治,回溯带回来待比较值
        Node4 node1 = construct(grid, left, colMid, top, rowMid);
        Node4 node2 = construct(grid,colMid+1,right,top,rowMid);
        Node4 node3 = construct(grid,left,colMid,rowMid+1,bottom);
        Node4 node4 = construct(grid,colMid+1,right,rowMid+1,bottom);

        //4. 归并
        Node4 node = new Node4(true, false);
        if(node1.isLeaf && node2.isLeaf && node3.isLeaf && node4.isLeaf && node1.val == node2.val && node1.val == node3.val && node1.val == node4.val){
            //全是叶子节点且节点值全相等,不赋值
            node.val = node1.val;
            node.isLeaf = true;
        }else {
            node.topLeft = node1;
            node.topRight = node1;
            node.bottomLeft = node1;
            node.bottomRight = node1;
        }
        return node;
    }
}
class Node4 {
    public boolean val;
    public boolean isLeaf;
    public Node4 topLeft;
    public Node4 topRight;
    public Node4 bottomLeft;
    public Node4 bottomRight;


    public Node4() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node4(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node4(boolean val, boolean isLeaf, Node4 topLeft, Node4 topRight, Node4 bottomLeft, Node4 bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}