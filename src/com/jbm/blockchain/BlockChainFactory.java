
package com.jbm.blockchain;

/** 
 * Factory de {@link Block} y de {@link BlockChain}.
 * @author Jairo Medina
 */
public final class BlockChainFactory {
    
    private BlockChainFactory(){}
    
    //---BLOCK---
    //******************************************************************************************************************
    public static <T> Block<T> getBlock(){
        return(BlockChainFactory.<T>getInstanceBlock());
    }
    
    public static <T> Block<T> getBlock(T value){
        return(BlockChainFactory.getInstanceBlock(value));
    }
    
    public static <T> Block<T> getBlock(Integer id, T value){
        return(BlockChainFactory.<T>getInstanceBlock(id, value));
    }
    
    public static <T> Block<T> getBlock(Integer id, T value, Integer nextId){
        return(BlockChainFactory.<T>getInstanceBlock(id, value,nextId));
    }
    
    //---BlockCHAIN
    //******************************************************************************************************************
    
    public static <T> BlockChain<T> getBlockChain(){
        return(BlockChainFactory.<T>getInstanceBlockChain());
    }
    
    //---BLOCK Instance---
    //******************************************************************************************************************
    private static <T> BlockValue<T>    getInstanceBlock(){
        BlockValue<T> block = new BlockValue<>();
        return(block);
    }
    private static <T> BlockValue<T>    getInstanceBlock(T value){
        BlockValue<T> block = new BlockValue<>(value);
        return(block);
    }
    private static <T> BlockValue<T>    getInstanceBlock(Integer id, T value){
        BlockValue<T> block = new BlockValue<>(id,value);
        return(block);
    }
    private static <T> BlockValue<T>    getInstanceBlock(Integer id, T value, Integer nextId){
        BlockValue<T> block = new BlockValue<>(id,value,nextId);
        return(block);
    }
    //---BlockCHAIN Instance---
    //******************************************************************************************************************
    private static <T> BlockChain<T> getInstanceBlockChain(){
        BlockChain<T> blockChain = new BlockList<>();
        return(blockChain);
    }
}
