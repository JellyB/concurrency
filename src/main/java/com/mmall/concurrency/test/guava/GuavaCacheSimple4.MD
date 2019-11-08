

#### RefreshAfterWrite 验证


模拟缓存异步加载之后我们启动多个线程，并等待一段时间去请求缓存中的数据观察缓存中的数据跟随时间的变化


```java
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
User:    1             get.value:      bigDev
User:    4             get.value:      bigDev
User:    6             get.value:      bigDev
User:    5             get.value:      bigDev
User:    3             get.value:      bigDev
User:    7             get.value:      bigDev
User:    7                     finish.costTime:      0
User:    9             get.value:      bigDev
User:    9                     finish.costTime:      0
User:    4                     finish.costTime:      0
User:    1                     finish.costTime:      0
User:    3                     finish.costTime:      0
User:    0             get.value:      bigDev
30:40:925    >                  > 1. 线程异步刷新数据库数据 oldValue:_  bigDev
User:    2             get.value:      bigDev
User:    5                     finish.costTime:      0
User:    6                     finish.costTime:      0
User:    2                     finish.costTime:      0
User:    0                     finish.costTime:      0
User:    8             get.value:      bigDev
User:    8                     finish.costTime:      0
30:42:921    Cache:    0             get.value:      bigDev
30:42:945    >                  > 2. 数据库数据读入到缓存中:_ 710c004359024861b3e92111e7a9197d
30:43:123    Cache:    1             get.value:      710c004359024861b3e92111e7a9197d
30:43:325    Cache:    2             get.value:      710c004359024861b3e92111e7a9197d
30:43:523    Cache:    3             get.value:      710c004359024861b3e92111e7a9197d
30:43:725    Cache:    4             get.value:      710c004359024861b3e92111e7a9197d
30:43:924    Cache:    5             get.value:      710c004359024861b3e92111e7a9197d
30:44:131    >                  > 1. 线程异步刷新数据库数据 oldValue:_  710c004359024861b3e92111e7a9197d
30:44:130    Cache:    6             get.value:      710c004359024861b3e92111e7a9197d
30:44:329    Cache:    7             get.value:      710c004359024861b3e92111e7a9197d
30:44:526    Cache:    8             get.value:      710c004359024861b3e92111e7a9197d
30:44:728    Cache:    9             get.value:      710c004359024861b3e92111e7a9197d
30:44:923    Cache:    10             get.value:      710c004359024861b3e92111e7a9197d
30:45:126    Cache:    11             get.value:      710c004359024861b3e92111e7a9197d
30:45:324    Cache:    12             get.value:      710c004359024861b3e92111e7a9197d
30:45:523    Cache:    13             get.value:      710c004359024861b3e92111e7a9197d
30:45:724    Cache:    14             get.value:      710c004359024861b3e92111e7a9197d
30:45:927    Cache:    15             get.value:      710c004359024861b3e92111e7a9197d
30:46:126    Cache:    16             get.value:      710c004359024861b3e92111e7a9197d
30:46:133    >                  > 2. 数据库数据读入到缓存中:_ 4b4f1a3c6ee443b7a7cb8885671f349d
30:46:329    Cache:    17             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:46:530    Cache:    18             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:46:729    Cache:    19             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:46:929    Cache:    20             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:47:130    Cache:    21             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:47:330    >                  > 1. 线程异步刷新数据库数据 oldValue:_  4b4f1a3c6ee443b7a7cb8885671f349d
30:47:330    Cache:    22             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:47:529    Cache:    23             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:47:730    Cache:    24             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:47:929    Cache:    25             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:48:129    Cache:    26             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:48:330    Cache:    27             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:48:530    Cache:    28             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:48:730    Cache:    29             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:48:931    Cache:    30             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:49:130    Cache:    31             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:49:331    Cache:    32             get.value:      4b4f1a3c6ee443b7a7cb8885671f349d
30:49:335    >                  > 2. 数据库数据读入到缓存中:_ 6bad57a6f82f40ae908e9fccd6442f40
30:49:530    Cache:    33             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:49:730    Cache:    34             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:49:930    Cache:    35             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:50:130    Cache:    36             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:50:330    Cache:    37             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:50:530    >                  > 1. 线程异步刷新数据库数据 oldValue:_  6bad57a6f82f40ae908e9fccd6442f40
30:50:530    Cache:    38             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:50:729    Cache:    39             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:50:929    Cache:    40             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:51:130    Cache:    41             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:51:329    Cache:    42             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:51:529    Cache:    43             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:51:729    Cache:    44             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:51:929    Cache:    45             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:52:129    Cache:    46             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:52:329    Cache:    47             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:52:530    Cache:    48             get.value:      6bad57a6f82f40ae908e9fccd6442f40
30:52:536    >                  > 2. 数据库数据读入到缓存中:_ f5b41ebb72ab412b8271c0c708e66145
30:52:729    Cache:    49             get.value:      f5b41ebb72ab412b8271c0c708e66145

```

##### 我们可以发现：


- . 缓存过期的时候返回旧值 （1 s 过期）

```java
30:40:925    >                  > 1. 线程异步刷新数据库数据 oldValue:_  bigDev
```

- . 花费 2 s 从数据库获取新数据这段时间内获取到的任然是旧值

###### 1. 新数据读入到缓存中

**step 1** 从数据库获取到新值 `710c004359024861b3e92111e7a9197d` 并放入到缓存中

###### 2. 缓存没有过期

在 `30:43:123` 到 `30:43:924` 这 1 s 时间内，线程获取数据直接命中缓存，且缓存没有过期返回缓存数据
线程 1 到 线程 5

###### 3. 缓存过期，线程异步从数据库加载数据

1 s 后，由于缓存到达过期时间，缓存过期。此时，执行 **step 2**，后台线程异步获取数据，并放入到缓存中。
这时由于从数据库获取到的数据耗费时间为 t （我们假定每次 t 都为 2 s）。因此，在这 **2 s** 时间内线程获取到的数据都是已经过期的旧值

可以看出在 `30:44:130` 到 `30:46:126` 这 **2 s** 中，返回的是旧值 `710c004359024861b3e92111e7a9197d`，触发执行 step 3

###### 4. 后台异步线程成功获取数据写入缓存

2 s 时间过后，后台异步线程成功把数据写入到缓存中，新的缓存数据 `4b4f1a3c6ee443b7a7cb8885671f349d` 开始生效。





```java
step 1  30:42:945    >                  > 2. 数据库数据读入到缓存中:_ 710c004359024861b3e92111e7a9197d
        30:43:123    Cache:    1             get.value:      710c004359024861b3e92111e7a9197d
        30:43:325    Cache:    2             get.value:      710c004359024861b3e92111e7a9197d
        30:43:523    Cache:    3             get.value:      710c004359024861b3e92111e7a9197d
        30:43:725    Cache:    4             get.value:      710c004359024861b3e92111e7a9197d
        30:43:924    Cache:    5             get.value:      710c004359024861b3e92111e7a9197d
step 2  30:44:131    >                  > 1. 线程异步刷新数据库数据 oldValue:_  710c004359024861b3e92111e7a9197d
        30:44:130    Cache:    6             get.value:      710c004359024861b3e92111e7a9197d
        30:44:329    Cache:    7             get.value:      710c004359024861b3e92111e7a9197d
        30:44:526    Cache:    8             get.value:      710c004359024861b3e92111e7a9197d
        30:44:728    Cache:    9             get.value:      710c004359024861b3e92111e7a9197d
        30:44:923    Cache:    10             get.value:      710c004359024861b3e92111e7a9197d
        30:45:126    Cache:    11             get.value:      710c004359024861b3e92111e7a9197d
        30:45:324    Cache:    12             get.value:      710c004359024861b3e92111e7a9197d
        30:45:523    Cache:    13             get.value:      710c004359024861b3e92111e7a9197d
        30:45:724    Cache:    14             get.value:      710c004359024861b3e92111e7a9197d
        30:45:927    Cache:    15             get.value:      710c004359024861b3e92111e7a9197d
        30:46:126    Cache:    16             get.value:      710c004359024861b3e92111e7a9197d
step 3  30:46:133    >                  > 2. 数据库数据读入到缓存中:_ 4b4f1a3c6ee443b7a7cb8885671f349d
```