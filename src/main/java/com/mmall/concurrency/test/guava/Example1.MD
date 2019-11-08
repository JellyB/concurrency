
下面的输出结果可以看到：只有一个线程去数据库中加载数据，其他线程都在等待（每个线程都耗时 2 s）

使用 Guava 确实可以做到：
    
    对于同一个 key，无论有多少请求，都只允许一个线程去加载数据

但是也有一个很致命的缺陷： 上面 10 个线程中，有一个线程实际去加载数据，其余 9 个线程都被阻塞了。
如果能做到，当一个线程去加载数据，其余线程发现这个数据正在加载中，那么直接读取老的数据，这样就不会阻塞了。
既然是缓存，读取旧一点的数据也没有多大问题，却可以提高系统吞吐量



```bash
User:    0        begin
User:    2        begin
User:    1        begin
User:    3        begin
User:    4        begin
User:    5        begin
User:    6        begin
User:    7        begin
User:    8        begin
User:    9        begin
begin to query from db...
success load db from db...
User:    5             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    1             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    0             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    2             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    0                     finish.costTime:      2
User:    4             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    8             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    4                     finish.costTime:      2
User:    7             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    7                     finish.costTime:      2
User:    2                     finish.costTime:      2
User:    1                     finish.costTime:      2
User:    9             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    5                     finish.costTime:      2
User:    3             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    6             get.value:      fd730415-9ebb-47b6-b763-bd08bb3a2eef
User:    3                     finish.costTime:      2
User:    9                     finish.costTime:      2
User:    8                     finish.costTime:      2
User:    6                     finish.costTime:      2

Process finished with exit code 0
```