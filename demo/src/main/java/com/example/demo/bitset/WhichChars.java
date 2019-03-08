package com.example.demo.bitset;

import java.util.BitSet;

public class WhichChars {

    private BitSet bitSet = new BitSet();

    public WhichChars(String str){
        int length = str.length();
        for (int i = 0; i < length; i++){
            bitSet.set(str.charAt(i));
        }
    }

    @Override
    public String toString(){
        String desc = "[";
        int size  = bitSet.size();
        for(int i = 0; i < size; i++){
            if(bitSet.get(i)){
                desc += (char)i;
            }
        }
        return desc + "]";
    }

    public static void main(String[] args) {
        WhichChars w = new WhichChars("How do you do");
        System.out.println(w);
    }

}
