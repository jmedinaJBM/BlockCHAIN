
package com.jbm.blockchain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;

/**
 * Lista de bloques enlazados.
 * @author Jairo Medina
 * @param <T> Tipo de objeto de la cadena de bloques.
 */
class BlockList<T> implements BlockChain<T> {
    
    private List<Block<T>> blockList;
    
    //---Constructores---
    //******************************************************************************************************************
    public BlockList(){
        this.initialize();
    }
    
    //---Operaciones Override---
    //******************************************************************************************************************
    @Override
    public int size() {
        return(this.blockList.size());
    }

    @Override
    public boolean isEmpty() {
        return(this.blockList.isEmpty());
    }

    @Override
    public boolean contains(Object o) {
        if(o!=null){
            if(o instanceof BlockValue){
                return(this.blockList.contains(o));
            }else{
                return(false);
            }
        }else{
            return(false);
        }
    }

    @Override
    public Iterator<Block<T>> iterator() {
        return(this.blockList.iterator());
    }

    @Override
    public Object[] toArray() {
        return(this.blockList.toArray());
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return(this.blockList.toArray(a));
    }

    @Override
    public boolean add(Block<T> e) {
        if(e!=null){
            return(this.blockList.add(e));
        }else{
            return(false);
        }
    }

    @Override
    public boolean remove(Object o) {
        if(o!=null){
            if(o instanceof BlockValue){
                return(this.blockList.remove(o));
            }else{
                return(false);
            }
        }else {
            return(false);
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return(this.blockList.containsAll(c));
    }

    @Override
    public boolean addAll(Collection<? extends Block<T>> c) {
        return(this.blockList.addAll(c));
    }

    @Override
    public boolean addAll(int index, Collection<? extends Block<T>> c) {
        return(this.blockList.addAll(index, c));
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return(this.blockList.removeAll(c));
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return(this.blockList.retainAll(c));
    }

    @Override
    public void clear() {
        this.blockList.clear();
    }

    @Override
    public Block<T> get(int index) {
        return(this.blockList.get(index));
    }

    @Override
    public Block<T> set(int index, Block<T> element) {
        return(this.blockList.set(index, element));
    }

    @Override
    public void add(int index, Block<T> element) {
        this.blockList.add(index, element);
    }

    @Override
    public Block<T> remove(int index) {
        return(this.blockList.remove(index));
    }

    @Override
    public int indexOf(Object o) {
        if(o!=null){
            if(o instanceof BlockValue){
                return(this.blockList.indexOf(o));
            }else{
                return(-1);
            }
        }else{
            return(-1);
        }
    }
    
    @Override
    public int lastIndexOf(Object o) {
        if(o!=null){
            if(o instanceof BlockValue){
                return(this.blockList.lastIndexOf(o));
            }else{
                return(-1);
            }
        }else{
            return(-1);
        }
    }

    @Override
    public ListIterator<Block<T>> listIterator() {
        return(this.blockList.listIterator());
    }

    @Override
    public ListIterator<Block<T>> listIterator(int index) {
        return(this.blockList.listIterator(index));
    }

    @Override
    public List<Block<T>> subList(int fromIndex, int toIndex) {
        return(this.blockList.subList(fromIndex, toIndex));
    }
    
    /** {@inheritDoc } */
    @Override
    public Block<T> getBlock(Integer blockId){
        Optional<Integer> id = Optional.ofNullable(blockId);
        Optional<Block<T>> block = id.isPresent() ? this.blockList.stream()
                .filter(b -> Objects.equals(b.getId(), blockId)).findFirst() : Optional.empty();
        return(block.orElse(null));
    }
    
    /** {@inheritDoc } */
    @Override
    public Block<T> getNext(Block<T> block){
        Optional<Block<T>> bk = Optional.ofNullable(block);
        Optional<Block<T>> blockNext = bk.isPresent() ? this.blockList.stream()
                .filter(b -> Objects.equals(b.getPrevId(), bk.get().getId())).findFirst() : Optional.empty();
        return(blockNext.orElse(null)); 
    }
    
    /** {@inheritDoc } */
    @Override
    public Block<T> getPrevius(Block<T> block){
        Optional<Block<T>> bk = Optional.ofNullable(block);
        Optional<Block<T>> blockPrev = bk.isPresent() ? this.blockList.stream()
                .filter(b -> Objects.equals(b.getNextId(), bk.get().getId())).findFirst() : Optional.empty();
        return(blockPrev.orElse(null));
    }
    
    /** {@inheritDoc } */
    @Override
    public Block<T> getFirst(){
        Optional<Block<T>> block = this.blockList.stream().filter(b -> b.getPrevId()==-1).findFirst();
        return(block.orElse(null));
    }
    
    /** {@inheritDoc } */
    @Override
    public Block<T> getLast(){
        Optional<Block<T>> block = this.blockList.stream().filter(b -> b.getNextId()==-1).findFirst();
        return(block.orElse(null));
    }
    
    /** {@inheritDoc } */
    @Override
    public boolean  addNext(Block<T> block){
        Optional<Block<T>> previusBlock = Optional.ofNullable(this.blockList.size() > 0
                ? (this.blockList.get(this.blockList.size() - 1)) 
                : null);
        Integer prevId = previusBlock.isPresent()? previusBlock.get().getId() : BlockValue.DEFUALT_ID;
        block.setPrevId(prevId);
        previusBlock.ifPresent(b -> {
            b.setNextId(block.getId());
            block.setPrevHash(b.getHash());
        });
        return(this.blockList.add(block));
    }
    
    @Override
    public int      linkBlocks(int index){
        return(this.linkBlocksHash(index, false));
    }
    
    @Override
    public int      linkBlocks(int index, boolean linkHash){
        return(this.linkBlocksHash(index, linkHash));
    }
    
    /** {@inheritDoc } */
    @Override
    public int      linkBlocks() {
        int blocksLinked = 0;
        if(this.blockList.size() > 0){
            ListIterator<Block<T>> listIterator = this.blockList.listIterator(0);
            while(listIterator.hasNext()){
                Block<T> block = listIterator.next();
                int index = listIterator.nextIndex();
                
                ListIterator<Block<T>> listIterator2 = this.blockList.listIterator(index);
                boolean found = false;
                while(listIterator2.hasNext() && !found){
                    Block<T> block2 = listIterator2.next();
                    if(block.getHash().equals(block2.getPrevHash())){
                        block.setNextId(block2.getId());
                        block2.setPrevId(block.getId());
                        found = true;
                    }
                }
                blocksLinked += found? 1 : 0;
            }
        }
        return(blocksLinked);
    }
    
    //---Privados---
    //******************************************************************************************************************
    private void initialize(){
        this.blockList = new ArrayList<>();
    }
    
    private int  linkBlocksHash(int index, boolean linkHash){
        int blocksLinked = 0;
        if(index < this.blockList.size()){
            Optional<Block<T>> previusBlock = Optional.empty();
            
            ListIterator<Block<T>> listIterator = this.blockList.listIterator(index);   //Para recorrido secuencial.
            while(listIterator.hasNext()){
                Block<T> block = listIterator.next();
                if(previusBlock.isPresent()){
                    block.setPrevId(previusBlock.get().getId());
                    previusBlock.get().setNextId(block.getId());
                    if(linkHash){
                        block.setPrevHash(previusBlock.get().getHash());
                    }
                    blocksLinked++;
                }
                previusBlock = Optional.ofNullable(block);
            }
        }
        return(blocksLinked);
    }
}
