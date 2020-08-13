# 优先队列
- <a href="#01">API and elementary implementations</a>
- <a href="#02">binary heap</a>
- 堆排序
  
---

## <a id="01">API and elementary implementations</a>

优先队列其实可以理解为一个可以删除和插入的集合,具体的实现有很多种,以下为部分例举:
- Stack:弹出最近添加的
- Queue:弹出最后添加的
- Randomized queue:随机弹出一个
- Priority queue:弹出最大或最小的

其实有经验的工程师已经看出来了,最基本的实现就是去栈或队列中找最大或最小值,实现优先队列的方式其实最重要还是看它的时间复杂度和空间复杂度

|implement|time|space|
|----|----|----|
|sort|NlogN|N|
|elementary PQ| MN| M|
|binary heap| N log M| M |
|best in theory| N | M |

显然我们的目标就是要找平均效率为logN的insert、del max、max的实现

## <a id="02">binary heap</a>

二叉堆能够很好的实现优先队列的基本操作,在二叉堆的数组中,每个元素都要保证大于等于另外两个特定位置的元素。相应的这些位置的元素又要大于等于数组的另两个元素。  
在堆有序的二叉树中,每个节点都小于等于它的父节点(如果父节点存在,所以根通常是最大或最小的)。从任意节点向上,我们都能得到一列非递减的元素;从任意节点向下,我们都能得到一列非递增的元素

|implement|insert|del max| max |
|----|----|----|----|----|
|unordered array| 1 | N | N|
|ordered array| N | 1 | 1|
|binary head| logN | logN | 1
|d-ary heap| $log_dN$| $d log_d N$| 1 |
|Fibonaci| 1 | logN (amortized) | 1|
|impossible| 1 | 1| 1 |



<style>
table {
    margin: auto;
}
</style>