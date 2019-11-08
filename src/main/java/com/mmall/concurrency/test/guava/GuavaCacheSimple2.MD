
#### RefreshAfterAccess

通过下面的输出结果可以看出：
    
    当缓存数据过期的时候，真正去加载数据的线程会阻塞一段时间，其余线程立马返回过期的值
    显然这种处理方式更符合实际的使用场景

```bash

Connected to the target VM, address: '127.0.0.1:64174', transport: 'socket'
User:    0        begin
User:    2        begin
User:    3        begin
User:    1        begin
User:    4        begin
User:    5        begin
User:    6        begin
User:    7        begin
User:    8        begin
User:    9        begin
begin to mock query db....
User:    2             get.value:      aty
User:    3             get.value:      aty
User:    2                     finish.costTime:      0
User:    1             get.value:      aty
User:    1                     finish.costTime:      0
User:    4             get.value:      aty
User:    4                     finish.costTime:      0
User:    0             get.value:      aty
User:    9             get.value:      aty
User:    9                     finish.costTime:      0
User:    3                     finish.costTime:      0
User:    6             get.value:      aty
User:    5             get.value:      aty
User:    5                     finish.costTime:      0
User:    7             get.value:      aty
User:    7                     finish.costTime:      0
User:    0                     finish.costTime:      0
User:    6                     finish.costTime:      0
success to mock query db...
User:    8             get.value:      79d3875e-d78a-4b9b-a2b1-cbf0988c7f8b
User:    8                     finish.costTime:      2
Disconnected from the target VM, address: '127.0.0.1:64174', transport: 'socket'

Process finished with exit code 0

```

有一点需要注意：


```java

// 手动添加一条缓存数据，睡眠 1.5s 让其过期
//cache.put(KEY, VALUE);
//TimeUnit.MILLISECONDS.sleep(1500);

```
    
    我们手动向缓存中添加了一条数据，并让其过期
    如果没有这行代码，程序执行结果如下
    

```bash

User:    0        begin
User:    1        begin
User:    2        begin
User:    3        begin
User:    4        begin
User:    5        begin
User:    6        begin
User:    7        begin
User:    8        begin
User:    9        begin
begin to mock query db....
success to mock query db...
User:    8             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    5             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    8                     finish.costTime:      2
User:    7             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    7                     finish.costTime:      2
User:    2             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    2                     finish.costTime:      2
User:    9             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    9                     finish.costTime:      2
User:    0             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    0                     finish.costTime:      2
User:    6             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    6                     finish.costTime:      2
User:    5                     finish.costTime:      2
User:    4             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    4                     finish.costTime:      2
User:    1             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    1                     finish.costTime:      2
User:    3             get.value:      3d8a87d3-5b07-46c5-b999-215f6ffab6b2
User:    3                     finish.costTime:      2
Disconnected from the target VM, address: '127.0.0.1:52174', transport: 'socket'

Process finished with exit code 0
```


由于**缓存没有数据，导致一个线程去加载数据的时候，别的线程都阻塞了（因为没有旧值可以返回）**
所以，一般系统启动的时候，我们需要将数据预先加载到缓存，不然就会出现这种情况