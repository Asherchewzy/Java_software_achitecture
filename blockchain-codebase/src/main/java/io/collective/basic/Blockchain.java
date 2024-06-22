package io.collective.basic;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    ///prevent 
    private final List<Block> chain;

    public Blockchain() {
        this.chain = new ArrayList<>();
    }

    public boolean isEmpty() {
        return chain.isEmpty();
    }

    public void add(Block block) {
        chain.add(block);
    }

    public int size() {
        return chain.size();
    }

    public boolean isValid() throws NoSuchAlgorithmException {
        if (chain.isEmpty()) {
            return true;
        }

        Block previousBlock = chain.get(0);
        for (int i = 1; i < chain.size(); i++) {
            //skip first block since there isnt a prev block to compare to
            // loop the rest

            Block currentBlock = chain.get(i);
            

            //current hash correctly calculated
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }


            //previous hash match
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }

            //check if block is mined (below it checks if it starts with 00)
            if (!isMined(currentBlock)) {
                return false;
            }
            previousBlock = currentBlock;
        }
        return true; 
    }

    /// Supporting functions that you'll need.

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), block.getNonce());

        while (!isMined(mined)) {
            mined = new Block(mined.getPreviousHash(), mined.getTimestamp(), mined.getNonce() + 1);
        }
        return mined;
    }

    public static boolean isMined(Block minedBlock) {
        return minedBlock.getHash().startsWith("00");
    }
}