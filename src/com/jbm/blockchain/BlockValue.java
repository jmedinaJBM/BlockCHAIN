
package com.jbm.blockchain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


class BlockValue<T> implements Block<T> {
    public static final String  UNICODE_FORMAT  = "UTF-8";
    public static final Integer DEFUALT_ID      = -1;
    public static final String  HASH_ALGORITHMS = "SHA-256";
    public static final String  DEFAULT_HASH    = "";
    
    private Optional<Integer>   prevBlockId;
    private Optional<Integer>   nextBlockId;
    private Optional<Integer>   blockId;
    private Optional<String>    prevHash;
    private Optional<String>    hashId;
    private Optional<T>         value;
    
    //---Constructor---
    //******************************************************************************************************************
    public BlockValue(){
        this.initialize(null,null,null,null);
    }
    
    public BlockValue(Integer blockId){
        this.initialize(blockId,null,null,null);
    }
    
    public BlockValue(T value){
        this.initialize(null, value,null,null);
    }
    
    public BlockValue(Integer blockId, T value){
        this.initialize(blockId, value,null,null);
    }
    
    public BlockValue(Integer blockId, T value, Integer nextId){
        this.initialize(blockId, value, nextId,null); 
    }
    
    public BlockValue(Integer blockId, T value, Integer nextId, Integer prevId){
        this.initialize(blockId, value, nextId, prevId);
    }
    
    //---Propiedades---
    //******************************************************************************************************************
    @Override
    public boolean isPresent(){
        return(this.value.isPresent());
    }
    
    @Override
    public T                getValue(){
        return(this.value.get());
    }
    @Override
    public BlockValue<T>    setValue(T val){
        this.value = Optional.ofNullable(val);
        return (this);
    }
    
    @Override
    public Integer          getId(){
        return(this.blockId.orElse(DEFUALT_ID));
    }
    @Override
    public BlockValue<T>    setId(Integer id){
        this.blockId = Optional.ofNullable(id);
        return (this);
    }
    
    @Override
    public Integer          getNextId(){
        return(this.nextBlockId.orElse(DEFUALT_ID));
    }
    @Override
    public BlockValue<T>    setNextId(Integer nextId){
        this.nextBlockId = Optional.ofNullable(nextId);
        return (this);
    }
    
    @Override
    public Integer          getPrevId(){
        return(this.prevBlockId.orElse(DEFUALT_ID));
    }
    @Override
    public BlockValue<T>    setPrevId(Integer prevId){
        this.prevBlockId = Optional.ofNullable(prevId);
        return (this);
    }
    
    @Override
    public String           getPrevHash(){
        return(this.prevHash.orElse(DEFAULT_HASH));
    }
    @Override
    public BlockValue<T>    setPrevHash(String hash){
        this.prevHash = Optional.ofNullable(hash);
        return (this);
    }
    
    @Override
    public String           getHash(){
        return(this.hashId.orElse(DEFAULT_HASH));
    }
    @Override
    public BlockValue<T>    setHash(String hash){
        this.hashId = Optional.ofNullable(hash);
        return (this);
    }
    
    @Override
    public BlockValue<T>    autoHash() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        this.hashId = Optional.ofNullable(this.getHashValue());
        return (this);
    }
    
    @Override
    public String           generateHash() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        return (this.getHashValue());
    }
    
    //---Privados---
    //******************************************************************************************************************
    private void initialize(Integer id,T val, Integer nextId, Integer prevId){
        this.value       = Optional.ofNullable(val);
        this.blockId     = Optional.ofNullable(id);
        this.nextBlockId = Optional.ofNullable(nextId);
        this.prevBlockId = Optional.ofNullable(prevId);
        this.prevHash    = Optional.empty();
        this.hashId      = Optional.empty();
    }
    
    private String getHashValue() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Optional<String> computedHash = Optional.empty();
        if(this.value.isPresent()){
            String jsonValue = this.toJson(this.value.get());
            String hashvalue = this.toHexString(this.getDigest(HASH_ALGORITHMS, jsonValue));
            computedHash = Optional.ofNullable(hashvalue);
        }
        return(computedHash.orElse(DEFAULT_HASH));
    }
    
    /**
     * Convierte un objeto a formato JSon; la conversión se realiza con la librería Google.
     * @param <T> Tipo de objeto a convertir.
     * @param object Objeto a convertir en JSon.
     * @return JSon del objeto {@code object}.
     */
    private <T> String  toJson(T object){
        Gson gson = (new GsonBuilder()).create();
        String jsonObject = gson.toJson(object);
        return(jsonObject);
    }
    
    private byte[]       getDigest     (String algoritmo, String text) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        byte[] data = text.getBytes(UNICODE_FORMAT);
        MessageDigest messageDigest = MessageDigest.getInstance(algoritmo);
        messageDigest.update(data);
        byte[] digest = messageDigest.digest(data);
        return(digest);
    }
    
    /**
     * Convierte una arreglo de valores {@link byte} a formato hexadecimal y los devuelve como {@link String}.
     * @param value Conjunto de valores a convertir.
     * @return Valores convertidos a hexadecimal.
     */
    private String        toHexString(byte[] value) {
        String hash = "";
        for (byte aux : value) {
            int b = aux & 0xff; // Hace un cast del byte a hexadecimal
            if (Integer.toHexString(b).length() == 1)
               hash += "0";
            hash += Integer.toHexString(b);
        }
        return(hash);
    }
}
