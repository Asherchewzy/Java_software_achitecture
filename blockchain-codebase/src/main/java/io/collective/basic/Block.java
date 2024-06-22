package io.collective.basic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
single block contain reference to 
prev block's hash, 
timestamp
nonce (number used once)
own hash
*/
public class Block {
    private final String previousHash;
    private final long timestamp;
    private final int nonce;
    private String hash;

    public Block(String previousHash, long timestamp, int nonce) throws NoSuchAlgorithmException {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.hash = calculateHash();
    }


    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public String getHash() {
        return hash;
    }
    public String calculateHash() throws NoSuchAlgorithmException {
        // concatenating the 3 before passing to calculatehash block 
        String string = previousHash + timestamp + nonce;
        return calculatedHash(string);
    }

    static String calculatedHash(String string) throws NoSuchAlgorithmException {
        // change method name for clarity 
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(string.getBytes());
        return String.format("%064x", new BigInteger(1, digest.digest()));
    }
}

