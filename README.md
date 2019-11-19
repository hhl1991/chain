# chain
Java区块链
Block.java 区块：每一个区块不仅包含前一个区块的hash值，同时包含自身的一个hash值，自身的hash值是通过之前的hash值+时间戳+随机数+数据date通过hash计算出来的
NodeChain.java 区块链 维护一个数组，和难度系数。
BlockChainUtil.java 1、创建数字签名 2、检查区块链的完整性
