package com.chain.model;

import lombok.Getter;
import lombok.Setter;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * @ClassName hhl
 * @Description 钱包
 * @Author Administrator
 * @Date 2019/11/19 15:07
 * @Version 1.0
 **/
@Getter
@Setter
public class Wallet {
    /**
     * 私钥的作用是为了对交易进行签名,，这样其他人就不可以花费金额除非它拥有你的私钥
     **/
    private PrivateKey privateKey;
    /**
     * 公钥的作用就是地址
     **/
    private PublicKey publicKey;

    public Wallet(){
        generateKeyPair();

    }
    public void generateKeyPair(){
        try {
            KeyPairGenerator   keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(ecSpec,random);
            KeyPair keyPair = keyGen.genKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
