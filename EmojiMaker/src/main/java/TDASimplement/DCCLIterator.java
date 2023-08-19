/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDASimplement;

import java.util.Iterator;

/**
 *
 * @author USUARIO
 */
public class DCCLIterator<E> implements Iterable<E>{
    private DCLList<E> literable;
   
    public DCCLIterator(DCLList<E> l){
        this.literable=l;
    }
    public Iterator<E> iterator() {
       return new Iterator<E>() {
           @Override
           public boolean hasNext() {
               //logica que verifica si existe un siguiente en la lista
              return false;
           }

           @Override
           public E next() {
               //logica que retorna el siguiente en la lista
               return null;
           }
           public E previous(){
               //logica que devuelve el previo en la lista
               return null;
           }
           public boolean hasPrevious(){
               //logica que verifica la existencia de un previo en la lista
               return false;
           }
       };
    }

    
    
}
