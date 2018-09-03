
package com.jbm.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


public class Main {

    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        BlockChain<String> blockChain = BlockChainFactory.<String>getBlockChain();
        for(int i=1; i<=10; i++){
            Block<String> block = BlockChainFactory.getBlock(i,"Jairo "+i);
            block.autoHash();
            blockChain.add(block);
        }
        
        blockChain.linkBlocks(0,true);
        
        Optional<Block<String>> pBlock = Optional.ofNullable(blockChain.getFirst());
        Optional<Block<String>> uBlock = Optional.ofNullable(blockChain.getLast());
        
        pBlock.ifPresent(b -> System.out.println("Primer block -> "+b.getId()+" "+b.getValue()+" "+b.getHash()));
        uBlock.ifPresent(b ->System.out.println("Ultimo block -> "+b.getId()+" "+b.getValue()+" "+b.getHash()));
        
        System.out.println("LISTA DE BLOQUES");
        blockChain.forEach((Block<String> b) -> {
            System.out.println(b.getId()+"\t"+b.getValue()+" next->"+b.getNextId()+" prev->"+b.getPrevId()+" hashId->"+b.getHash()
                    +" prevHashId->"+b.getPrevHash());
        });
        
        
    }
    
}
