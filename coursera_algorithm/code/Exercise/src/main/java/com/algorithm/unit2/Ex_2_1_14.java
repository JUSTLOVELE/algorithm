package com.algorithm.unit2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 出列排序,如何将一副扑克牌排序,限制条件是只能查看最上面的两张牌,交换
 * 最上面的两张牌,或是将最上面的一张牌放到这摞牌的最下面
 */
public class Ex_2_1_14 {

    public static Integer[] getCards() {

        Integer[] cards = new Integer[52];
        List<Integer> index = new ArrayList<>();

        for(int i=0; i<52; i++) {
            index.add(i+1);
        }

        Collections.shuffle(index);

        for(int i=0; i<52; i++) {
            cards[i] = index.get(i);
        }

        return cards;
    }

    /**
     * 把第一个元素放到最后一个位置
     * @param cards
     */
    private static  void firstMoveToLast(Integer[] cards, int initIndex) {

        int firstValue = cards[initIndex];

        for(int i=initIndex; i<cards.length-1; i++){
            cards[i] = cards[i+1];
        }

        cards[cards.length-1] = firstValue;
    }

    private static void exchangeFirstAndSecond(Integer[] cards, int initIndex) {

        int temp = cards[initIndex];
        cards[initIndex] = cards[initIndex+1];
        cards[initIndex+1] = temp;
    }


    public static void main(String[] args) {

        Integer[] cards = getCards();
        //Arrays.stream(cards).forEach(System.out::println);

        for(int j=1; j<cards.length; j++) {

            for(int i=1; i<cards.length; i++) {

                if(cards[j-1] < cards[j]) {
                    //若最上面的值小于第二张的值,则把最上面的值和最后的值互换
                    firstMoveToLast(cards, j-1);
                }else{
                    //如果最上面的值大于第二张的值,则交换两者位置,然后再把最上面的值丢到最后去
                    exchangeFirstAndSecond(cards, j-1);
                    firstMoveToLast(cards, j-1);
                }
            }

            System.out.print("第" + j + "次排序");

            for(Integer card: cards) {
                System.out.print(card + ",");
            }

            System.out.println();
        }
    }

}
