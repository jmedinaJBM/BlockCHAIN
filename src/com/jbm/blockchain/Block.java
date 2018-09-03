
package com.jbm.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Interface utiliada para generar un bloque para utilizar con {@link BlockChain}. El tipo de objeto a contener
 * en el bloque dado por {@code T} debe ser el mismo que se defina en la interface {@link BlockChain}.
 * @author Jairo Medina
 * @param <T> Tipo de objeto a contener en el bloque.
 */
public interface Block<T> {
    
    /**
     * Evalúa si el dato está presente en el {@link Block}.
     * @return {@code True}: El dato está presente.
     */
    public boolean  isPresent();
    
    /**
     * Devuelve el valor contenido en el {@link Block} o {@code null} si no está presente.
     * @return Valor contenido en el {@link Block}.
     */
    public T        getValue();
    /**
     * Establece el valor a contener en el {@link Block}.
     * @param val Valor.
     * @return Instancia de este {@link Block}.
     */
    public Block<T> setValue(T val);
    
    /**
     * Obtiene el identificador de este {@link Block}.
     * @return Id del bloque.
     */
    public Integer  getId();
    /**
     * Establece el identificador de este {@link Block}.
     * @param id Identificador.
     * @return Instancia de este {@link Block}.
     */
    public Block<T> setId(Integer id);
    
    /**
     * Devuelve el identificador del siguiente bloque en la cadena del {@link BlockChain}.
     * @return Identificador del siguiente bloque.
     */
    public Integer  getNextId();
    /**
     * Establece el identificador del siguiente bloque en la cadena del {@link BlockChain}.
     * @param nextId Identificador del siguiente bloque.
     * @return Instancia de este {@link Block}.
     */
    public Block<T> setNextId(Integer nextId);
    
    /**
     * Devuelve el identificador del bloque a éste previo en la cadena del {@link BlockChain}.
     * @return Identificador del bloque antecesor a éste.
     */
    public Integer  getPrevId();
    /**
     * Establece el identificador del bloque previo a éste en la cadena del {@link BlockChain}.
     * @param prevId Identificador del bloque antecesor a éste.
     * @return Instancia de este {@link Block}.
     */
    public Block<T> setPrevId(Integer prevId);
    
    /**
     * Devuelve el {@code hash} del bloque previo a éste en la cadena del {@link BlockChain}.
     * @return {@code hash} del bloque antecesor a éste.
     */
    public String   getPrevHash();
    /**
     * Establece el {@code hash} del bloque previo a éste en la cadena del {@link BlockChain}.
     * @param hash {@code hash} del bloque antecesor a establecer.
     * @return Instancia de este {@link Block}.
     */
    public Block<T> setPrevHash(String hash);
    
    /**
     * Devuelve el {@code hash} de este {@link Block}.
     * @return {@code hash} de este bloque.
     */
    public String   getHash();
    /**
     * Establece el {@code hash} a este {@link Block}.
     * @param hash {@code hash} a establecer.
     * @return Instancia de este {@link Block}.
     */
    public Block<T> setHash(String hash);
    
    /**
     * Genera el {@code hash} de este bloque llamando al método {@link #generateHash() } y lo establece a éste mismo
     * utilizando {@link #setHash(java.lang.String) }
     * @return Instancia de este {@link Block}.
     * @throws NoSuchAlgorithmException Error con el algoritmo para generar el {@code hash}
     * @throws UnsupportedEncodingException Error al convertir el {@code hash} de {@link String} a {@code byte}.
     */
    public Block<T> autoHash() throws NoSuchAlgorithmException, UnsupportedEncodingException;
    
    /**
     * Genera el {@code hash} del valor contenido en el {@link Block}.
     * @return {@code hash} generado.
     * @throws NoSuchAlgorithmException Error con el algoritmo para generar el {@code hash}
     * @throws UnsupportedEncodingException Error al convertir el {@code hash} de {@link String} a {@code byte}.
     */
    public String   generateHash() throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
