# 第二章
- <a href="#p2-1-13">练习2.1.13-将一副扑克牌按花色排序（花色顺序是黑桃、红桃、梅花和方片），限制条件是所有牌都是背面朝上排成一列，而你一次只能翻看两张牌或者交换两张牌（保持背面朝上）</a>
- <a href="#p2-1-14">练习2.1.14-出列排序,如何将一副扑克牌排序,限制条件是只能查看最上面的两张牌,交换,最上面的两张牌,或是将最上面的一张牌放到这摞牌的最下面</a>
--------
## <a id="p2-1-13">练习2.1.13</a>

题目: 将一副扑克牌按花色排序（花色顺序是黑桃、红桃、梅花和方片），限制条件是所有牌都是背面朝上排成一列，而你一次只能翻看两张牌或者交换两张牌（保持背面朝上）。  
思路:  
- 取第一个元素为当前花色,并假设当前索引为i(初始为1)
- 然后从索引i开始由左到右扫描  
  - 如果和当前元素i的花色相同则继续往前i+1  
  - 若不同,则去后面的元素找和当前元素i相同的花色  
    - 如果找到,假设找到的元素为j,则交换j和i的值,然后i+1  
    - 若没有找到则把当前花色重置为i+1的花色然后继续往前  
```java 
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

```
----
## <a id="p2-1-14">练习2.1.14</a>
问题:出列排序,如何将一副扑克牌排序,限制条件是只能查看最上面的两张牌,交换,最上面的两张牌,或是将最上面的一张牌放到这摞牌的最下面  
思路:每次看最上面的两张牌,如果上面的牌小,则把上面的牌放到最后,若是上面的牌大,则先交换两张牌的位置,然后把最上面的牌丢到最后,这样一来第一次排序就把最大的牌放在了第一位,则第一位有序,然后从第二位开始继续扫描，按照上面的操作,然后第二位就是第二大的数字,直到扫完全部有序
```java
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
```