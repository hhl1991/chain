package com.chain.util;

import com.chain.model.Block;
import com.chain.model.NodeChain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @ClassName @{NAME}
 * @Description TODO 
 * @Author Administrator
 * @Date 2019/11/19 10:28
 * @Version 1.0
 **/
public class BlockChainUtil {
/**
 * @Author  创建数字签名
 * @Description //TODO 
 * @Date 10:40 2019/11/19
 * @Param [input]
 * @return java.lang.String
 **/
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer haxString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hax = Integer.toHexString(0XFF & hash[i]);
                if (hax.length() == 1) {
                    haxString.append("0");
                }
                haxString.append(hax);

            }
            return haxString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author 检查区块链的完整性
     * @Description //TODO 
     * @Date 11:03 2019/11/19
     * @Param []
     * @return java.lang.Boolean
     **/
    public static Boolean isChainValid(){
        Block curBlock;
        Block preBlock;
        List<Block> list = NodeChain.blockChain;
        String hashTarget = new String(new char[NodeChain.difficulty]).replace('\0', '0');
        for (int i=1;i<list.size();i++){
            curBlock = list.get(i);
            preBlock = list.get(i-1);
            if (!curBlock.getHash().equals(curBlock.calculateHash())){
                return false;
            }
            //比对前一个区块的hash值和previousHash值
            if (!preBlock.getHash().equals(curBlock.getPreHash())){
                return false;
            }
            //增加hash值是否已经计算过
            if(!curBlock.getHash().substring(0,NodeChain.difficulty).equals(hashTarget)) {
                return false;
            }
        }
        return true;
    }

}
