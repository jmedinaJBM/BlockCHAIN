
package com.jbm.blockchain;

import java.util.List;

/**
 * Interface para generar una cadena de bloques {@code Block Chain} con un tipo de objeto definido por {@code T}.
 * @author Jairo Medina
 * @param <T> Tipo de objeto que contedrá la cadena de bloques.
 */
public interface BlockChain<T> extends List<Block<T>> {
    
    /**
     * Obtiene el bloque de la lista donde {@link Block#getId() } sea igual a {@code blockId}.
     * @param blockId Id del bloque buscado.
     * @return Bloque recuperado. {@code null} si no lo encuentra.
     */
    public Block<T> getBlock(Integer blockId);
    
    /**
     * Obtiene el bloque siguiente al bloque dado en {@code block}.
     * Para encontrar el bloque siguiente, toma el valor de {@link Block#getPrevId() } de los bloques en la lista y lo
     * compara con el valor de {@link Block#getId() } del bloque dado en parámetro {@code block}. Devuelve el bloque
     * que cumpla esta condición.
     * @param block Bloque del cual se quiere el siguiente.
     * @return Bloque anterior. {@code null} si no lo encuentra o la lista está vacía.
     */
    public Block<T> getNext(Block<T> block);
    
    /**
     * Obtiene el bloque que antecede al bloque dado en {@code block}.
     * Para encontrar el bloque anterior, toma el valor de {@link Block#getNextId()  } de los bloques en la lista y lo
     * compara con el valor de {@link Block#getId() } del bloque dado en parámetro {@code block}. Devuelve el bloque
     * que cumpla esta condición.
     * @param block Bloque del cual se quiere el antecesor.
     * @return Bloque anterior. {@code null} si no lo encuentra o la lista está vacía.
     */
    public Block<T> getPrevius(Block<T> block);
    
    /**
     * Encuetra el primer bloque de la lista o bloque punta, que corresponde al más reciente bloque creado. 
     * Devuelve {@code null} si no se cumple la condición de que {@link Block#getNextId() () } es igual a {@code -1} 
     * o porque la lista está vacía.
     * @return Primer bloque.
     */
    public Block<T> getFirst();
    
    /**
     * Encuetra el último bloque de la lista o bloque cola, que corresponde al primer bloque creado. 
     * Devuelve {@code null} si no se cumple la condición de que {@link Block#getPrevId() () } es igual a {@code -1}
     * o la lista está vacía.
     * @return Último bloque.
     */
    public Block<T> getLast();
    
    /**
     * Agrerga un block a la cadena estableciendo los enlaces correspondientes.
     * @param block Bloque a agregar.
     * @return {@code True}: Bloque agregado.
     */
    public boolean  addNext(Block<T> block);
    
    /**
     * Encadenar los bloques apartir del índice dado en {@code index} respetando el orden en que fueron agregados a la lista.
     * @param index Índice de partida.
     * @return Número de bloques encadenados.
     */
    public int     linkBlocks(int index);
    
    /**
     * Encadenar los bloques apartir del índice dado en {@code index} respetando el orden en que fueron agregados a la lista.
     * @param index Índice de partida.
     * @param linkHash Si se debe enlazar los {@code hash}.
     * @return Número de bloques encadenados.
     */
    public int      linkBlocks(int index, boolean linkHash);
    
    /**
     * Realiza el encadenamiento de los bloques {@link Block} en la cadena, mediante los {@code hash} de calda {@link Block}.
     * El valor dado por {@link Block#getPrevHash() } debe coincidir con el valor {@link Block#getHash() } del bloque 
     * que le antecede dentro de la cadena.
     * @return Número de bloques encadenados.
     */
    public int     linkBlocks();
    
}
