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
```
    ADT 抽象数据类型名称
    {
        数据对象：数据对象的声明
        数据关系：数据关系的声明
        基本运算：基本运算的声明  ( 基本运算名(参数列表):运算功能描述 )
    }
```
---

## 一、 线性表

### (1) 稀疏数组
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

### (2) 队列
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
```
/**
 * 环形队列
 */
public class CircleQueue<E> {
    private final static int DEFAULT_SIZE = 10;

    //底层维护队列数据的数据
    private E[] arr;

    private int maxSize; //底层数组容量

    private int front; // 标记队列头索引

    private int rear;//标记队列尾索引

    public CircleQueue(int size){
        maxSize = size + 1; //环形队列要一位出来
        arr = (E[]) new Object[maxSize];
        //初始化标记队列头和队列尾索引的两个变量
        front = 0; //首元素下标
        rear = 0; //尾元素下标后一位
    }
    public CircleQueue(){
        this(DEFAULT_SIZE);
    }

    /**
     * 队列是否已满
     * front是首元素下标，rear是尾元素后一位;相邻时数组就已满，因为是环形的，可能front在rear前面，也可能在后面
     * @return
     */
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
     * @return
     */
    public boolean add(E e){
        if( isFull() ){
            throw new RuntimeException("队列已满！");
        }
        arr[rear] = e;
        rear = (rear+1) % maxSize ;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public E remove(){
        if( isEmpty() ){
            throw new RuntimeException("队列为空！");
        }
        E e = arr[front];
        front = (front+1) % maxSize;
        return e;
    }

    /**
     * 查看队首元素
     * @return
     */
    public E peek(){
        if( isEmpty() ){
            throw new RuntimeException("队列为空！");
        }
        return arr[front];
    }

    /**
     * 返回队列长度
     * @return
     */
    public int size(){
        return (rear+maxSize-front) % maxSize;
    }

    /**
     * 展示队列所有元素
     */
    public void showQueue(){
        if( isEmpty() ){
            throw new RuntimeException("队列为空！");
        }
        for (int i = front; i!=rear ; i = (i+1)%maxSize ) {
            System.out.printf("arr[%d]=%d; \n",i,arr[i]);

        }
    }
}
```
- 带头结点和尾结点的单链表 来实现队列结构
> 不带尾结点只带头结点的单链表，只适合在链表头进行增删,时间复杂度为O(1)；而在链表尾或链表中其他位置增删时，
时间复杂度为O(n)； 而要用链表实现队列结构，需要在一端入队，另一端出队，故只带头结点的链表并不适合用来实现队列，需要加上尾结点。 
> 在带头结点和尾结点的单链表中，在头结点增删都是O(1),而在尾结点只有删除时O(1);故用此实现栈结构时，
在头结点删除元素，尾结点增加元素，即头结点做队首，尾结点做队尾。
```
/**
 * 带头结点和尾结点的单链表（用来实现队列）
 */
public class TailLinkedList<E> {

    //头结点
    private Node<E> head;

    //尾结点
    private Node<E> tail;

    private int size;

    public TailLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    private class Node<E>{
        E e;
        Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在尾结点增加
     * @return
     */
    public boolean addLast(E e){
        if( tail == null ){ //链表为空
            tail = new Node(e);
            head = tail;
        }else{ //链表不为空
            tail.next = new Node(e);
            tail = tail.next ;
        }
        size++;
        return true;
    }

    /**
     * 在头结点删除
     * @return
     */
    public E removeFirst(){
        if(head == null){ //链表为空
            throw new RuntimeException("没有元素");
        }
        Node<E> delNode = head;
        head = head.next;
        if(head == null) tail = null;
        size-- ;
        return delNode.e;
    }

    /**
     * 查看头结点元素
     * @return
     */
    public E getFront(){
        if(isEmpty()){ //链表为空
            throw new RuntimeException("没有元素");
        }
        return head.e ;
    }

    /**
     * 查看尾结点元素
     * @return
     */
    public E getLast(){
        if(isEmpty()){ //链表为空
            throw new RuntimeException("没有元素");
        }
        return tail.e;
    }

    public int size(){
        return size;
    }
}


/**
 * 带头结点和尾结点的单链表来实现队列结构
 * @param <E>
 */
public class LinkedListQueue<E> implements MyQueue<E> {

    private TailLinkedList<E> linkedList;

    public LinkedListQueue(){
        linkedList = new TailLinkedList<E>();
    }

    public int size() {
        return linkedList.size();
    }

    public E deQueue() {
        return linkedList.removeFirst();
    }

    public boolean enQueue(E e) {
        return linkedList.addLast(e);
    }

    public E getFront() {
        return linkedList.getFront();
    }

    public E getLast() {
        return linkedList.getLast();
    }
}
```

- 阻塞队列
> 队列这种数据结构用的比较广泛的就是 阻塞队列与并发队列
> 阻塞队列其实就是在队列的基础上增加了阻塞操作，简单地说，就是在队列为空时从队头取数据会被阻塞，因为此时还没有数据可取，直到队列中有数据了才能返回；
如果队列已经满了，那么插入操作就会被阻塞，直到队列中有空闲位置后再插入数据。
> 因此阻塞队列可用于实现一个“生产者-消费者模型”。
> 这种基于阻塞队列实现的“生产者-消费者模型”，可以有效地协调生产和消费的速度。当生产者生产数据的速度过快，
消费者来不及消费时，存储数据的队列很快就满了。这个时候，生产者就会阻塞等待，直到消费者消费了数据，生产者才会被唤醒继续生产。
并且还可以通过协调生产者和消费者的个数，来提高数据的处理效率，比如多配置几个消费者，来应对一个生产者，亦或反之。
> 线程池没有空闲线程时，新的任务请求线程资源时，线程池该如何处理呢？一般有两种处理策略：
一种是非阻塞的处理方式，直接拒绝任务请求；另一种是阻塞的处理方式，将请求排队，等到有空闲线程时取出排队的请求继续处理。
那如何存储排队的请求呢？先进者先服务，所以队列这种数据结构很适合用来存储排队请求。而队列有基于数组和基于链表的两种实现方式。
那么这两种实现方式对于排队请求又有什么区别呢？
>> 基于链表的实现方式，可以实现一个无限排队的无界队列（unbounded queue）,但可能会导致过多的请求排队等待，请求处理的响应时间过长。
所以，针对响应时间比较敏感的系统，基于链表实现的无限排队的线程池是不合适的。
而基于数组实现的有界队列（bounded queue），队列的大小有限，所以线程池中排队的请求超过队列大小时，接下来的请求就会被拒绝，
这种方式对响应时间敏感的系统就更加合理。不过，设置一个合理的队列大小，也是很有讲究的——队列太大导致等待的请求太多，太小则会
导致无法充分利用系统资源、发挥最大性能。
> 实际上，对于大部分资源有限的场景，当没有空闲资源时，基本都可以通过队列这种数据结构来实现请求排队。

- 并发队列
> TODO
   
### (3) 动态数组
> 数组在存储上是顺序结构，在内存上需要一块连续的空间来存储；故可根据首地址和下标，通过寻址公式就能直接计算出对应的内存地址，因而称数组支持随机访问，根据索引查找元素的时间复杂度是O(1);
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
### (4) 链表
- 相比数组需要内存中一整块连续的空间来存储，链表则不需要一块连续的内存空间，它通过“指针”将一组零散的内存块串联起来使用。
- 为了将所有的结点“串联”起来，每个链表的结点除了存储数据之外，还需要记录链上的下一个结点地址。故每个结点分为两块区域——数据域与指针域。
- 针对链表中指定结点的插入和删除操作，只需要考虑相邻结点的指针改变，时间复杂度为O(1)。
- 无法像数组一样，根据首地址和下标，通过寻址公式直接计算出指定索引位置的内存地址值；而是需要一个个结点地遍历，直到找到指定结点，故不支持随机访问，查找操作的时间复杂度是O(n)。
- 常见的链表一般分为单链表，双向链表，循环链表；其中单链表每个结点只有一个指针，双向链表每结点有两个指针分别指向前驱和后继元素；循环链表则是一种特殊的单链表，其末尾元素的指针指向首元素，而普通单链表的指针值为NULL；
对于有序列表，双向链表比单链表的查询效率更高。

---

##### 一个基础的单链表实例
```
/**
 * 基础单链表
 */
public class SingleLinkedList{

    /**
     * 头结点
     */
    private TrueManNode head = new TrueManNode();

    public TrueManNode getHead(){
        return head;
    }

    /**
     * 添加结点
     * @return
     */
    public boolean add(TrueManNode node){
        if(isEmpty()) {
            head.next = node;
        }else{
            TrueManNode temp = head.next;
            while(true){
                if(temp.next == null){ //没有找到，就往后继结点遍历
                    temp.next = node;
                    break;
                }
                temp = temp.next;
            }
        }
        return true;
    }

    /**
     * 删除结点
     * @return
     */
    public void remove(TrueManNode node){
        TrueManNode temp = head; //指向头结点
        while(temp.next!=null){
            if(temp.next.no == node.no){
                temp.next = temp.next.next; //没有指针指向这个结点，自然便会被gc
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("没有这个结点");
    }

    /**
     * 改结点
     * @return
     */
    public boolean updateNode(TrueManNode node){
        if( isEmpty() ) throw new RuntimeException("链表为空");
        TrueManNode temp = head;
        while(temp.next != null){
            if(temp.no == node.no){
                temp = node;
                return true;
            }
        }
        throw new RuntimeException("没有编号"+node.no+"的结点");
    }

    /**
     *
     * @return
     */
    public String toString(){
        if( isEmpty() ) return "[]";
        StringBuilder sb = new StringBuilder("[");
        TrueManNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            sb.append(temp.toString());
            if(temp.next!=null) sb.append("   ");
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty(){
        return head == null || head.next == null;
    }

    /**
     * 获取链表的有效结点个数
     * @return 链表长度
     */
    public int size(){
        TrueManNode temp = head.next; //首结点
        if(temp == null){
            return 0;
        }
        int size = 1;
        while (temp.next != null){
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 翻转当前链表（复习一遍）
     * @return
     */
    public SingleLinkedList reverse(){
        if(size()<=1) return this;
        //当前表头 head
        TrueManNode newHead = new TrueManNode();

        TrueManNode current = head.next;
        while(current!=null) {
            TrueManNode next = current.next;
            current.next = newHead.next; //修改了当前遍历到的结点的后继元素地址，故要用变量提前记录下更改前的地址值
            newHead.next = current;
            current = next; //最后还原回原链表中的结点地址值，用于原链表的遍历
        }
        //讲新head的内存结点赋值给旧的表头地址变量
        head = newHead;
        return this;
    }
}
```
- 翻转单链表
```
    /**
     * 翻转链表
     * 思路：
     *  创建一个新链表的表头，顺序遍历原链表，将原链表的每一个结点元素添加到新表头的最前面
     */
    public static void reverseLink(SingleLinkedList linkedList){
        if( linkedList.size()<=1 ) return ;

        TrueManNode newHead = new TrueManNode();
        TrueManNode current = linkedList.getHead().next; //首元素开始，用于遍历原链表的
        TrueManNode next = null;
        while(current!=null){ //尾元素进不来循环
            next = current.next; //当前遍历的下一位
            current.next = newHead.next; //原来的第一位地址值，由新的第一位指向，变成第二位
            newHead.next = current;

            current = next; //赋一个新的地址值
        }
        linkedList.getHead().next = newHead.next;
    }
```
 
- 双向链表
> 单链表的缺点分析：
>> 只能往一个方向遍历     
>> 删除时需要依靠辅助结点，不能进行自我删除。（需要待删结点的前驱结点指针域指向待删结点的后继元素--使得待删结点不被引用而被gc掉）   
--- 
遍历方式和单链表相同，只是多了一个向前遍历的遍历顺序； 增加也是默认添加到最后，只要要多加一个向前的指针域； 
修改相同； 删除，不需要再遍历到前驱元素，直接找到目标元素后，temp.pre.next=temp.next;  temp.pre=temp.next.pre;

- 单向环形链表（约瑟夫问题）
   - Josephu问题为: 设编号为1, 2，… n的n个人围坐一圈，约定编号为k (1<=k<=n) 的人从1开始报数，数到m的那个人出列，
它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
   - 解决思路:
     用一个单向循环链表解决：先构成一个带有n个节点的单向环形链表，然后由k节点起从1开始计数，计到m时，对应节点从链表中删除，然后再从被删除节点的下一个节点开始数，直到最后一个节点从链表中删除
     (注意由于单链表的结点不能进行自我删除，需要依赖前驱元素，故约瑟夫问题中出圈操作需要有一个临时变量记录目标结点的前驱元素)
```
/**
 * 单向循环链表解决约瑟夫问题
 */
public class CircleLinkedList {

    private Boy first = null;

    /**
     * 往空链表加入指定个数的boy
     * @param num
     */
    public void addBoy(int num){
        if(num<1) throw new RuntimeException("加入个数异常");
        Boy curBoy = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy();
            boy.setNo(i);
            if(i == 1){ //加入第一个
                first = boy;
                boy.setNext(boy);
                curBoy = boy;
            }else{
                curBoy.next = boy;
                first = boy.next;
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历
     */
    public void showBoys(){
        if(first == null) throw new RuntimeException("为空");
        Boy curBoy = first;
        while(true){
            System.out.println(curBoy);
            if(curBoy.next == first) break; //已经遍历到最后一个了
            curBoy = curBoy.next;
        }
    }

    /**
     * 解决约瑟夫问题
     * @param startNo 开始数数的boy编号
     * @param count 数数的数量(第若干个boy出圈)
   * @param total 游戏开始前圈中的所有boy个数
     */
    public void dealJosephusQuestion(int startNo,int count,int total){
        if(first==null) throw new RuntimeException("为空");
        if(count<total) throw new RuntimeException("参数有误");
        Boy temp = first;
        while(true){
            temp = temp.next;
            if(temp.next == first) break; //末尾结点（头结点的前驱元素,单链表删除结点时需要的）
        }

        //数数前，先找出即将数数的boy结点和其前驱结点（末尾）
        for (int i = 0; i < startNo-1; i++) {
            first = first.next;
            temp = temp.next;
        }
        while (true){
            if( first == temp ) {
                System.out.println("编号"+first.no+"的小朋友出圈");
                first = null;
                break;
            }
            //找到数数后的待出圈boy结点,依靠其前驱结点，进行删除
            for (int i = 0; i < count - 1; i++) {
                first = first.next;
                temp = temp.next;
            }
            //出圈
            System.out.println("编号"+first.no+"出圈");
            first.next = temp.next;
            first = first.next;
        }
    }
}
```

### (5)栈（Stack）
> 栈是一个后进先出的（FILO - First in Last Out）有序列表
> 栈是限制线性表中元素的插入和删除只能在线性表的同一段进行的一种特殊线性表。允许插入和删除
的一端，为变化的一端，称为栈顶，另一端为固定的一端，称为栈底。
> 最先放进的元素在栈底，最后放入的元素在栈顶。
```
/**
 * 栈结构
 * 后进先出
 * 数组实现栈
 */
public class ArrayStack {

    //底层维护栈数据的数组
    private int[] arr;

    //栈顶元素索引
    private int topIndex = -1 ;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack(int size){
        arr = new int[size];
    }

    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    public boolean isFull(){
        return arr.length == topIndex+1;
    }

    public boolean isEmpty(){
        return topIndex <= -1 ;
    }

    public boolean push(int num){
        if( isFull() ){
            throw new RuntimeException("栈满");
        }
        arr[++topIndex] = num;
        return true;
    }

    public int pop(){
        if( isEmpty() ) throw new RuntimeException("栈已满");
        return arr[topIndex--];
    }

    public int size(){
        return topIndex+1;
    }

    public void list(){
        if(isEmpty()) System.out.println("栈为空");
        for (int i = topIndex; i >= 0 ; i--) {
            System.out.println(arr[i]);
        }
    }
}
```
---
用链表来实现栈结构：
```
    /**
     * 链表实现栈结构
     * 表头新增（入栈），表尾删除（出栈）
     */
    public class LinkedStack {
    
        private Node top = null;
    
        public boolean push(int value){
            Node node = new Node(value, null);
            if(top == null){
                top = node;
            }else{
                node.next = top;
                top = node;
            }
            return true;
        }
    
        public int pop(){
            if(top == null) throw new RuntimeException("栈为空");
            int data = top.data;
            top = top.next;
            return data;
        }
    
        public void printAll(){
            StringBuilder sb = new StringBuilder("Stack: [");
            if(top != null) {
                Node temp = top;
                while (true){
                    if(temp == null) break;
                    sb.append(temp.data+"  ");
                    temp = temp.next;
                }
            }
            sb.append("]");
        }
        
        private static class Node{
            private int data;
            private Node next;
    
            public int getData(){
                return data;
            }
            public Node getNext(){
                return next;
            }
    
            public Node(){}
            public Node(int data,Node next){
                this.data = data;
                this.next = next;
            }
        }
    }
```
## (6)递归
> 递归就是方法自己调用自己，每次调用时传入不同的变量。递归有助于编程者解决复杂的问题，同时可以让代码变简洁。

> 递归调用规则：当程序员执行一个方法时，就会开辟一个独立的空间（栈）；每个空间的数据（局部变量）是独立的。

> 递归需要遵守的规则：
>> 1. 执行一个方法时，就创建一个新的受保护的独立空间（栈空间）
>> 2. 方法的局部变量是独立的，不会互相影响，比如n变量
>> 3. 如果方法中使用的是引用类型变量（比如数组），就会共享该引用类型的数据。
>> 4. 递归必须向退出条件逼近，否则就是无限递归，出现栈内存溢出异常。
>> 5. 当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行
完毕或者返回时，该方法也就执行完毕。

递归-迷宫问题
![递归迷宫图](https://github.com/studygo2017/data-structure/raw/main/imgs/maze.PNG)
   
--- 

## (7)排序算法
> Sort Algorithm 排序是将一组数据,依指定的顺序进行排列的过程.
> 排序在内存占用上分为 内排序 与 外排序。
> - 内部排序: 指将需要处理的所有数据都加载到内存中进行排序.
> - 外部排序: 数据量过大无法全部加载到内存中,需要借助外部存储(文件等)
> 排序分类图: ![排序分类图](https://github.com/studygo2017/data-structure/raw/main/imgs/allsorts.PNG)
> 算法的时间复杂度与空间复杂度问题 (略, 理论部分可见搜索引擎...个人体会就是除了专业分析,日常业务看时间复杂度就从循环执行次数看,看空间复杂度就从循环创建新变量--堆内存开辟新空间)
1. 冒泡排序: 通过对待排序序列从前到后,**依次比较相邻元素的值,若发现则逆序交换(替换位置)**, 使值较大的元素逐渐从前移动到后部,就像*水底下的气泡一样逐渐向上冒*.
   - 注意对冒泡算法的优化: ***如果一趟排序下来没有一个元素进行过交换,就说明队列已经是有序的了***,因此可以在排序前设置一个交换标志flag记录该趟排序中是否进行元素位置交换,若未则停止算法,从而减少不必要的比较.
```
/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        //生成一个指定长度,指定数字范围(小于)的随机数组
        int[] nums = ArraysUtil.createRandomArr(100,1000);
        System.out.println(Arrays.toString(nums));
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void bubbleSort(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length-1 ; i++) {
            boolean flag = false;
            for (int y = 0 ; y < nums.length-1-i ; y++) {
                if(nums[y]>nums[y+1]){
                    int temp = nums[y];
                    nums[y] = nums[y+1];
                    nums[y+1] = temp;
                    flag = true;
                }
            }
            if( flag ) count++;
            else {
                System.out.printf("一共排序了%d趟\n",count);
                return;
            }
        }
        System.out.printf("一共排序了%d趟",count);
    }

}


```   


- 选择排序
