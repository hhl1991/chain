package com.chain.model;


import com.chain.util.BlockChainUtil;
import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * @ClassName hhl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/19 10:51
 * @Version 1.0
 **/
public class NodeChain {
    public static int difficulty = 5;
    public static ArrayList<Block> blockChain = Lists.newArrayList();

    public static void main(String[] args) {
        //把区块增加到数组中去
        blockChain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).getHash()));
        System.out.println("Trying to Mine block 2... ");
        blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).getHash()));
        System.out.println("Trying to Mine block 3... ");
        System.out.println("\nBlockchain is Valid: " + BlockChainUtil.isChainValid());
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }
}
