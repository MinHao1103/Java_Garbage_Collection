Java_Garbage_Collection
=

## 什麼是 Garbage Collection ?
```text
Java 程式在執行過程中會動態分配記憶體給物件使用，當物件不再使用時應釋放它們所佔用的記憶體空間。
Garbage Collection 是一個自動化記憶體管理機制，負責追蹤並自動釋放"不被程式使用的物件"所佔用的記憶體，主要優點: 
1. 自動化：開發者不需手動管理記憶體，減少錯誤的發生
2. 提高程式可靠性：避免記憶體泄漏等問題，減少程式因記憶體管理不當而導致的錯誤
3. 提高效率：Garbage Collection 可以在適當時機回收不再使用的記憶體，讓記憶體能夠被及時重用，提高程式效率
4. 當記憶體空間不足時會觸發 Garbage Collection，也可以在程式手動執行
```

## 什麼是 Memory Leak in Java ?
```text
在 Java 中，記憶體洩漏是指程式中的物件佔用了記憶體，但該物件不再被需要且未被正確釋放時，會導致系統的性能下降或崩潰
```
## 補充說明
* java 程式底層是 jvm，所以 jvm 有所有物件和所有型別，如果物件總數越來越多，可能就有 Memory Leak 風險
* 比較常見的 Memory Leak，例如：循環參照、使用 Map 作快取
* 可在程式運行時下載 Heap Profile 檔
* 若找不到內建 jvisualvm，可手動安裝 [VisualVM](https://visualvm.github.io/download.html)
* 另一個分析程式 Eclipse Memory Analyzer (MAT)

## 測試說明
* (1). 建立一個方法，一直在 Map 裡塞資料 [MemoryLeak.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fservice%2Fservlet%2FMemoryLeak.java)
* (2). 設定 VM options 參數為：-Xmx10m -Xms10m [VM_Options.png](_doc%2FVM_Options.png)
* (3). 呼叫方法直到 OutOfMemoryError
* (4). 如果出現 OOM 需要分析時，將 VM options 改為：-Xmx10m -Xms10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./tmpError.hprof
* (5). 透過 java 內建分析程式 jvisualvm 來分析檔案

## Windows 指令
```shell
jps -l
```
```shell
jmap -dump:live,format=b,file=heap.hprof <PID>
```

## Ubuntu 指令
```shell
ps -ef | grep <Server>
```
```shell
jmap -dump:live,format=b,file=heap.hprof <PID>
```

## 如何查看 heap.hprof 檔
* (1). 每小時取一個版本
* (2). 查看 Exception 訊息
* (3). 查看 Instance 狀況