

#### RefreshAfterWrite

通过下面的代码我们会发现，如果访问已经过期的缓存会返回旧值，然后通过 `ListeningExecutorService` 异步加载数据到缓存中

```java
User:    0        begin
User:    3        begin
User:    4        begin
User:    1        begin
User:    2        begin
User:    5        begin
User:    6        begin
User:    7        begin
User:    8        begin
User:    9        begin
User:    8             get.value:      bigDev
User:    5             get.value:      bigDev
User:    3             get.value:      bigDev
User:    0             get.value:      bigDev
User:    2             get.value:      bigDev
User:    0                     finish.costTime:      0
User:    3                     finish.costTime:      0
User:    9             get.value:      bigDev
User:    5                     finish.costTime:      0
User:    1             get.value:      bigDev
User:    8                     finish.costTime:      0
User:    7             get.value:      bigDev
User:    7                     finish.costTime:      0
User:    6             get.value:      bigDev
16:18:060    >                  > 1.后台线程异步刷新数据库数据 oldValue:_  bigDev
User:    1                     finish.costTime:      0
User:    9                     finish.costTime:      0
User:    2                     finish.costTime:      0
User:    6                     finish.costTime:      0
begin to mock query db...
User:    4             get.value:      bigDev
User:    4                     finish.costTime:      0
success to mock query db...
16:20:081    >                  > 2.从数据库中加载的数据:_ a6e967d7ecb94b73bda8a0e3ad4c8efe
```
