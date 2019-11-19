package com.chain.model;

import com.chain.util.BlockChainUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName @{NAME}
 * @Description TODO 区块 每一个区块不仅包含前一个区块的hash值，同时包含自身的一个hash值，自身的hash值是通过之前的hash值+时间戳+随机数+数据date通过hash计算出来的
 * @Author Administrator
 * @Date 2019/11/19 10:15
 * @Version 1.0
 **/
@Getter
@Setter
public class Block {

    /**
     * 数字签名
     **/
    private String hash;
    /**
     * 前一个区块的hash值
     **/
    private String preHash;
    /**
     * 数据
     **/
    private String data;
    /**
     * 时间戳
     **/
    private long timeStamp;
    /**
     * 随机数
     **/
    private int nonce;

    public Block(String data, String preHash) {
        this.data = data;
        this.preHash = preHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash=calculateHash();
        mineBlock();
    }

    public String calculateHash() {
        String calculatedhash = BlockChainUtil.applySha256(this.preHash + Long.toString(this.timeStamp) + Integer.toString(nonce) + this.data);
        return calculatedhash;
    }

    public void mineBlock() {
        //创建一个string值由难度的位数来决定
        String target = new String(new char[NodeChain.difficulty]).replace('\0', '0');
        while (!hash.substring(0, NodeChain.difficulty).equals(target)) {
            nonce++;
            this.hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
