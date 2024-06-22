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

        for (int i = 1; i < chain.size(); i++) {
            Block previousBlock = chain.get(i-1);
            Block currentBlock = chain.get(i);

            if (!isMined(currentBlock)) {
                return false;
            }

            //current hash correctly calculated
            
            String cb_string = currentBlock.getPreviousHash() + currentBlock.getTimestamp() + currentBlock.getNonce();
            String cb_hash = Block.calculatedHash(cb_string);

            if (!currentBlock.getHash().equals(cb_hash)) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
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