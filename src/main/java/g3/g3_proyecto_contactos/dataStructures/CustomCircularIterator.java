/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.dataStructures;

import g3.g3_proyecto_contactos.interfaces.List;
import java.util.ListIterator;

/**
 *
 * @author David
 */
public class CustomCircularIterator<E> implements ListIterator<E> {  
    
    private List<E> lista;
    private int indice = 0;
    
    
    public CustomCircularIterator (List<E> lista){
        this.lista = lista;
    }
    @Override
    public boolean hasPrevious() {
        return !lista.isEmpty();
    }

    @Override
    public E previous() {
        if (indice == 0){
            indice = lista.size()-1;
            return lista.getFirst();
        }
        return lista.get(indice--);
    }
      @Override
    public boolean hasNext() {
        return !lista.isEmpty();
    }

    @Override
    public E next() {
        if(indice == lista.size()-1){
            indice = 0;
            return lista.getLast();
        }
        return lista.get(indice++);
    }

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