# 知识点
- stacks and queues

- ---
## Stacks and queues
栈是在先进后出,push和pop的操作在同一端(LIFO,last in first out)  
队列是先进先出,操作在两端(FIFO, first in first out)
 ![](images/01.png)  
 通常栈跟队列都是用数组实现的,数组空时最好要减少数组的长度,通常缩小为四分一,数组满时扩容,扩充一倍。一般是根据需要再建个数组(扩容或缩小长度都应该是已知的了),然后再把值赋进去就可以了。