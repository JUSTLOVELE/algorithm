
# 知识点  
- 并查集  
   - dynamic connectivity  
   判断各个节点是否连通,常用的方法有两个:connected(a,b),union(a,b);connected返回布尔值判断a,b是否连通,union连通a,b。  
   假设现有集合:{1,...,10},经过连续的union的后连通的组件称为connected components,所以至少有一个connected components
   ![avatar](./images/connectedComponents.png)
   - Quick Find  
   平方量级的时间太慢了
- 算法分析