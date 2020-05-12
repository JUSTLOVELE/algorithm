
# 知识点  
- 并查集  
   - dynamic connectivity  
   判断各个节点是否连通,常用的方法有两个:connected(a,b),union(a,b);connected返回布尔值判断a,b是否连通,union连通a,b。  
   假设现有集合:{1,...,10},经过连续的union的后连通的组件称为connected components,所以至少有一个connected components
   ![connectedComponents](./images/connectedComponents.png)  
   - Quick Find  
   为解决dynamic connectivity问题,我们提出了Quick Find算法,其本质上是一种贪心算法,例如union(3,4),要把id[4]=3,若再连通union(8,3),要把id[3]=8,同时要把3连通的对象4也要进行修改:id[4]=8,最终判断两个节点是否连通仅要看看他们对应的id值是否相同即可。  
   ![](./images/union-find01.png)  
   ![](./images/union-find02.png)  
   但是Quick-find太慢了,是一个平方量级的算法,是一个平方量级的时间太慢了,对于大型的问题没有办法接受需要平方时间的算法,当计算机变得更大更快,平方时间算法实际上变得更慢了
   - Quick Union
- 算法分析