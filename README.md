# fe-tunnel
提供 TLS/SSL 加密流量的端口转发功能

## 如何使用
* `mvn assembly:assebmly` 编译出jar文件
* 运行服务器端 `java -jar fetrunnel -s`
* 运行客户端 `java -jar fetrunnel -c -rh <服务器地址>`
* 参数`-lp`,`-rh`,`-rp`可指定地址和端口

## 原理
程序会在客户建立一个监听端口，在该端口的流量均会加密发往服务器端
服务器端会进行还原。

## 实现简介
* 使用的netty来实现底层数据的转发
* 参考netty的例子中的HexDumpProxy和securechat扩展改造而来

## 应用
* 加密一些明文流量。
* 包装http proxy，实现 fq。

## 其它
* stunnel的简化版，仅供学习研究之用
* 未提供设置用户自定的证书，后面可能加上
* 未对性能和资源占用情况做评估，基于netty，不会差到哪里去。
