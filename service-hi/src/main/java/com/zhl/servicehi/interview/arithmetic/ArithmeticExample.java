package com.zhl.servicehi.interview.arithmetic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/18 11:51
 */
public class ArithmeticExample {

    public String dictionary(String[] words) {
        AtomicReference<String> ans = new AtomicReference<>("");
        Set<String> wordSet = new HashSet<>();
        Arrays.stream(words).forEach(word -> {
            wordSet.add(word);
        });
        Arrays.stream(words).forEach(word ->{
            int wordLen = word.length();
            int ansLen = ans.get().length();
            if (wordLen > ansLen || wordLen == ansLen && word.compareTo(ans.get()) < 0){
                boolean good = true;
                for (int i = 1; i < wordLen; ++i) {
                    if(!wordSet.contains(word.substring(0,i))){
                        good = false;
                        break;
                    }
                }
                if (good){
                    ans.set(word);
                }
            }
        });
        return ans.get();
    }
}
