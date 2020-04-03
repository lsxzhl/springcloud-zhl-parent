package com.zhl.servicehi.interview.arithmetic;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/19 18:17
 */
public class Once {

    /**
     *   只出现一次的数字   异或运算
     * 给定一个非空整数数组，除了某个元素只出现一次以外，
     * 其余每个元素均出现两次。找出那个只出现了一次的元素
     * @param nums 数组
     * @return int
     * solution
     */
    public int solution(int[] nums) {
        int num = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            num = num ^ nums[i];
        }
        return num;
    }


    public static void main(String[] args) {
        int[] data = new int[]{17, 12, 5, -6, 12, 4, 17, -5, 2, -3, 2, 4, 5, 16, -3, -4, 15, 15, -4, -5, -6};
        Once sol = new Once();
        System.out.println(sol.solution(data));
    }

}
