/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.dataStructures;

import g3.g3_proyecto_contactos.interfaces.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author David
 */
public class CustomCircularIterator<E> implements ListIterator<E> {  
    
    private List<E> list;
    private int index;
    
    
    public CustomCircularIterator (List<E> list){
        this.list = list;
        this.index = 0;
    }
    @Override
    public boolean hasPrevious() {
        return !list.isEmpty();
    }

    @Override
    public E previous() {
        if (!hasNext()) {
            throw new IllegalStateException();
        }
        index = (index - 1 + list.size()) % list.size();
        return list.get(index);
    }
      @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public E next(){
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E element = list.get(index);
        index = (index +1)%list.size();
        return element;
    }
    /*public E next() {
        if(indice == lista.size()-1){
            indice = 0;
            return lista.getLast();
        }
        return lista.get(indice++);
    }*/
    

    @Override
    public int nextIndex() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int previousIndex() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void set(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
}