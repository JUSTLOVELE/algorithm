package com.algorithm.unit2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 将一副扑克牌按花色排序（花色顺序是黑桃、红桃、梅花和方片），
 * 限制条件是所有牌都是背面朝上排成一列，而你一次只能翻看两张牌或者交换两张牌（保持背面朝上）。
 */
public class Ex_2_1_13 {

    public static void shuffleCard(Integer[] cards) {

        List<Integer> index = new ArrayList<>();

        for(int i=0; i<52; i++) {
            index.add(i/13 + 1);
        }

        Collections.shuffle(index);

        for(int i=0; i<52; i++) {
            cards[i] = index.get(i);
        }
    }

    public static void main(String[] args) {

        Integer[] cards = new Integer[52];
        shuffleCard(cards);
        Arrays.stream(cards).forEach(System.out::print);
        System.out.println();
        //从左到右扫描,如果数组相同则往前，如果不同则交换为相同的
        Integer first = cards[0];

        for(int i=1; i<52; i++) {

            if( first != cards[i]) {
                boolean flag = false;
                //花色不同要去找花色相同的然后交换
                for(int j=i+1; j<52; j++) {

                    if(first == cards[j]) {
                        //找到对应的花色并交换
                        Integer temp = cards[j];
                        cards[j] = cards[i];
                        cards[i] = temp;
                        flag = true;
                        break;
                    }
                }

                if(!flag) {
                    //如果之后的牌中都没有对应的花色,则把当前花色重置
                    first = cards[i];
                }

            }else{
                //花色相同则i继续向前扫描
                continue;
            }
        }

        Arrays.stream(cards).forEach(System.out::print);
    }
}
