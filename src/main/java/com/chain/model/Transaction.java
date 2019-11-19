package com.chain.model;

import com.chain.util.BlockChainUtil;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * @ClassName hhl
 * @Description 交易
 * @Author Administrator
 * @Date 2019/11/19 15:26
 * @Version 1.0
 **/
@Setter
@Getter
public class Transaction {
    private String transactionId;
    private PublicKey sender;
    private PublicKey reciepient;
    private BigDecimal value;
    private byte[] signature;
    private ArrayList<TransactionInput> inputs = Lists.newArrayList();
    private ArrayList<TransactionOutput> outputs = Lists.newArrayList();
    public static long sequence = 0;

    public Transaction(PublicKey from,PublicKey to,BigDecimal value,ArrayList<TransactionInput> inputs){
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
        this.transactionId = calulateHash();
    }

    /**
     * @Author hhl
     * @Description //TODO 计算交易的hash值（用于交易编号）
     * @Date 15:54 2019/11/19
     * @Param []
     * @return java.lang.String
     **/
    private String calulateHash(){
        sequence++;
        return BlockChainUtil.applySha256(BlockChainUtil.getStringFromKey(this.sender)+BlockChainUtil.getStringFromKey(this.reciepient)+String.valueOf(this.value));
    }
}
