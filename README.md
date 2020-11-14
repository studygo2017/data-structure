# data-structure章节
归纳过去和现在所学习的 数据结构与常用算法知识、以及刷题

## 数据结构的基本概念
- 什么是数据？
> 数据（data）是描述客观事物的数字和字符的集合。
- 什么是数据对象？
> 数据对象（data object）是指性质相同的数据元素的集合，它是数据的一个子集。
- 什么是数据项？
> 数据项（data item）是具有独立含义的数据的最小单位，也称为字段或域（Feild）。
- 什么是数据结构？
> 数据结构（data structure）是指所有数据元素以及数据元素之间的关系，可看作是相互之间存在着某种特定关系的数据集合。
>> 数据结构可以分为逻辑结构和存储结构；
>>> 逻辑结构由数据元素之间的逻辑关系构成。
>>> 存储结构是数据元素及关系（即逻辑结构）在计算机存储器中的存储表示，也成为数据的物理结构。

> 数据的运算：施加在该数据上的操作。
> 数据逻辑结构与数据存储结构无关，是独立于计算机，可以看作对现实世界中具体问题抽象而来的数学模型。
> 在现实世界中，元素之间的关系往往是多样的，但在数据结构中聚焦于讨论数据元素间的相邻和邻接关系。

> 数据逻辑结构的描述方式：
> - 图表表示 （可以是类似于关系型数据库的数据表; 也可以是其他的简图，达意即可）
> - 二元组表示

> 逻辑结构的类型
> - 集合（彼此间没有相邻关系，仅仅是“同属一个集合”）
> - 线性结构 （彼此在排列上是一对一关系；除了首端元素的其他每个元素有且仅有一个前驱动元素，除了末端元素的其他元素有且仅有一个后继元素）
> - 树形结构 （彼此间一对一关系；除首端元素外其他有且仅有一个前驱元素，除了末端元素外其他元素有一个或多个后继元素）
> - 图形元素 （彼此间是多对多关系；每个元素都至少有一个前驱元素，至少有一个后继元素）

> 存储结构的类型
> - 顺序存储结构：采用一组连续的存储单元存放所有的数据元素，所有的数据元素在计算机存储中占有一整块存储单元。
> - 链式存储结构：每个元素用一个内存结点存储，每个节点是单独分配的，所有的节点地质不一定是连续的。为了表示元素间的逻辑关系，给每个节点附加指针域，用于存放相邻节点的内存地址。
> - 索引存储结构：在存储元素数据的同时还建立附加的索引表；存储所有数据元素信息的表称为主数据表，其中每个数据元素有一个关键字和对应的存储地址。
> - 哈希存储结构：只存储元素的数据，不存储元素之间的逻辑关系。基本思想是根据元素的关键字通过哈希算法来计算一个值，将该值作为元素的内存地址来存储。

> ADT
> 指的是用户进行软件设计从具体问题抽象而来的逻辑数据结构和基于逻辑数据结构上的运算（不考虑在计算机存储器中的具体存储）
> ADT的描述：
> ```$xslt
    ADT 抽象数据类型名称
    {
        数据对象：数据对象的声明
        数据关系：数据关系的声明
        基本运算：基本运算的声明  ( 基本运算名(参数列表):运算功能描述 )
    }
```

--- 

## 一、 线性表

### （1）稀疏数组
- ***稀疏数组的应用场景***:当某个二维数组中大部分元素都为0或者其他默认值，且只有极少量的不同值时；可以使用稀疏数组结构，
将原数组进行压缩，用压缩后的数组存储到磁盘中；从磁盘中读取出来使用时，再根据压缩逻辑将压缩后的数组
还原为原数组；这样达到了节省内存，节约磁盘的IO资源。
- ***稀疏数组的实现思路***: 1. 首行用于记录数组一共有几行几列，以及不同值数量； 2. 其他行用于记录
不同值的位置（所在行与所在列）和数值信息
```
    public class SparseArray {
    
        /**
         * 生成一个用于压缩示例的 16*16二维数组
         */
        public int[][] initArraydemo(){
            int[][] arrDemo = new int[16][16]; //默认值为0
            arrDemo[6][8] = 1;
            arrDemo[7][9] = 2;
            arrDemo[14][15] = 3;
            return arrDemo;
        }
    
        /**
         * 原数组压缩为稀疏数组
         * @return 压缩后的数组
         */
        public int[][] transferToSparse(int[][] arr){
            int count = 0;//记录不同值个数
    
            for (int i = 0; i < arr.length; i++) {
                for (int i2 = 0; i2 < arr[i].length; i2++) {
                    if(arr[i][i2] != 0){
                        count++;
                    }
                }
            }
            //初始化压缩后的数组
            int[][] newArr = new int[count + 1][3];
            newArr[0][0] = arr.length;
            newArr[0][1] = arr[0].length;
            newArr[0][2] = count;
    
            int lines = 0;//记录不同值的行数
            for (int i = 0; i < arr.length; i++) {
                for (int i2 = 0; i2 < arr[i].length; i2++) {
                    if(arr[i][i2] != 0){
                        lines++;
                        newArr[lines][0] = i;
                        newArr[lines][1] = i2;
                        newArr[lines][2] = arr[i][i2];
                    }
                }
            }
            return newArr;
        }
    
        /**
         * 将压缩后的稀疏数组还原为原数组
         */
        public int[][] rtnToArrSource(int[][] arr){
            if(arr.length<1 || arr[0].length != 3){
                throw new RuntimeException("压缩数组数据有误!");
            }
            int[][] arrSource = new int[arr[0][0]][arr[0][1]];
            for (int i = 1; i <= arr[0][2]; i++) {
                arrSource[arr[i][0]][arr[i][1]] = arr[i][2];
            }
            return arrSource;
        }
    
        /**
         * 打印数组
         * @param arr
         */
        private void printArr(int[][] arr){
            for (int i = 0; i < arr.length; i++) {
                for (int i1 = 0; i1 < arr[i].length; i1++) {
                    System.out.printf("%d\t",arr[i][i1]);
                }
                System.out.println();
            }
        }
    
        public static void main(String[] args) {
            SparseArray demo = new SparseArray();
            int[][] arrSource = demo.initArraydemo();
            demo.printArr(arrSource);
            System.out.println();
            int[][] transferedArr = demo.transferToSparse(arrSource);
            demo.printArr(transferedArr);
            System.out.println();
            demo.printArr(demo.rtnToArrSource(transferedArr));
        }
```
---

### （2）队列
- 队列是一个有序列表，可以用数组（顺序存储）或链表（链式存储）来实现；
- ***遵循先进先出***原则
#### 普通队列与循环队列
- 普通队列
   - 数组模拟普通队列的实现思路： 
      1. 两个成员变量front与rear分别记录队列的队列头和队列尾索引值;front
随着出队而改变，rear随着入队而改变
      2. 变量maxSize记录底层维护队列数据的数组的容量
      3. 底层维护队列数据的数组
   - 普通队列待优化的问题：
      1. 数组只能使用一次就不能用了，造成空间的浪费；且容易索引越界。
```$xslt
public class OrdinaryQueue {
    //底层维护队列数据的数据
    private int[] arr;

    private int maxSize; //底层数组容量

    private int front; // 标记队列头索引

    private int rear;//标记队列尾索引

    public OrdinaryQueue(int size){
        maxSize = size;
        arr = new int[maxSize];
        //初始化标记队列头和队列尾索引的两个变量
        front = -1; //在首元素索引的前一位； 出队时该值就增加
        rear = -1; //尾元素索引; 入队时改值便增加
    }
    public OrdinaryQueue(){
        this(10); //默认容量为10
    }

    /**
     * 队列是否已满
     * @return
     */
    public boolean isFull(){
        return rear == maxSize-1;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return front == rear;
    }

    /**
     * 入队
     * @param num
     * @return
     */
    public boolean add(int num){
        if(isFull()){
            throw new RuntimeException("队列已满！");
        }
        arr[++rear] = num;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public int remove(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[++front];
    }

    /**
     * 观察队列首位元素
     * @return
     */
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }

    /**
     * 展示队列所有元素
     */
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
       int index = front+1;
        for (; index <= rear ; index++) {
            System.out.printf("arr[%d],%d",index,arr[index]);
        }
    }
}
```

- 环形队列
   - TODO
   
### （3）动态数组
- 要包含维护数据的基础数组及该数组的容量，和动态数组中实际保存的元素数量。
- 扩容和缩容机制。通过获得新数组，拷贝老数组的数据过来，并允许JVM回收老数组。
- 基本的CRUD API
- 提供一个实现Iterator接口的内部类；用于维护迭代器迭代时下一项的索引；并实现Iterator的三个方法。

```$xslt
public class MyArrayList<E> implements MyList<E> {

    private final static int DEFAULT_CAPACITY = 10;

    private E[] arr;//基础数组

    private int capacity; //当前基础数组的容量

    private int size ; //当前动态数组的长度

    public MyArrayList(int initCapacity){
        arr = (E[]) new Object[initCapacity];
        this.size = 0;
    }

    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    public boolean add(E e) {
        //是否要扩容？
        ensureCapacity(size+1);
        arr[size++] = e;
        return true;
    }

    public boolean add(int index, E e) {
        checkIndexIllegal(index);
        ensureCapacity(size+1);
        for (int i = size-1; i >= index; i--) {
            arr[i+1] = arr[i];
        }
        arr[index] = e;
        size++;
        return true;
    }

    public E remove(int index) {
        checkIndexIllegal(index);
        E e = arr[index];
        for (int i = index+1; i < size; i++) {
            arr[i-1] = arr[i];
        }
        size--;
        ensureCapacity(size+1); //是否需要缩容
        return e;
    }

    public boolean set(int index, E e) {
        checkIndexIllegal(index);
        arr[index] = e;
        return true;
    }

    public E get(int index) {
        checkIndexIllegal(index);
        return arr[index];
    }

    public int size() {
        return size;
    }

    /**
     * 扩容与缩容的处理
     * 扩容缩容要注意时间复杂的震荡的问题！
     * @param needCapacity 当前需要的存储容量
     */
    private void ensureCapacity(int needCapacity) {
        if(needCapacity <= capacity) return;

        int newCapacity = needCapacity;
        if( needCapacity<<3 < capacity ) {  //容量四倍于所需以上，缩容；
            newCapacity = capacity >> 2;
        }

        E[] newArr = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /**
     * 检测参数中的索引是否合法
     */
    private void checkIndexIllegal(int index) {
        if(index < 0 || index > size-1){
            //抛出自定义异常
            throw new IndexOutOfArrayExceptiom("数组索引越界，非法的index："+index);
        }
    }

    private class ArrayListIterator<E> implements MyIterator<E>{
        private int current = 0; //当前迭代到的索引位置

        public boolean hasNext() {
            return current < size;
        }

        public E next() {
            if(current > size-1){
                throw new NoMoreElementInArray();
            }
            return (E) arr[current++];
        }

        public E remove() {
            checkIndexIllegal(current-1);
            return (E) MyArrayList.this.remove(--current);
        }
    }

    public MyIterator<E> iterator(){
        return new ArrayListIterator();
    }
}
```
   

      
   

