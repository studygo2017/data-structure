# data-structure章节
归纳过去和现在所学习的 数据结构与常用算法知识、以及刷题

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
TODO

      
   

