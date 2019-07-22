package com.zhl.servicehi.interview.tree;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/7/20 14:25
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }

}
class Solution{

    private class ResultType{
        //是否找到
        boolean found;
        //节点数目
        int val;

        ResultType(boolean found, int val){
            this.found = found;
            this.val = val;
        }
    }

    public int kthSmallest(TreeNode root, int k){
        return kthSmallestHelper(root,k).val;
    }

    private ResultType kthSmallestHelper(TreeNode root, int k){
        if(root == null){
            return new ResultType(false,0);
        }
        ResultType left = kthSmallestHelper(root.left, k);
        //左子树找到，直接返回
        if(left.found){
            return new ResultType(true, left.val);
        }
        //左子树的节点数目=k-1,结果为root的值
        if(k - left.val == 1){
            return new ResultType(true, root.val);
        }
        //右子树寻找
        ResultType right = kthSmallestHelper(root.right, k - left.val - 1);
        if(right.found){
            return new ResultType(true, right.val);
        }
        //没找到，返回节点总数
        return new ResultType(false, left.val + 1 + right.val);

    }

}
