/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.dataStructures;

import g3.g3_proyecto_contactos.interfaces.List;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author David
 */
public class ArrayList<E> implements List<E>, Serializable {
    private static final long serialVersionUID = 1099110916014732399L;

    private E[] elements;
    private int CAPACITY = 100;
    private int effectiveSize;

    public ArrayList() { 
        //Constructor de CircularArrayList
        elements = (E[]) new Object[CAPACITY];
        effectiveSize = 0;
    }

    @Override
    public boolean addFirst(E element) {
        //Agrega el elemento dado al "inicio" del arrayList (indice 0)(retorna bool de si se logro completar la accion)
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (CAPACITY == effectiveSize) {
            addCapacity();
        }

        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }

        elements[0] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean insert(int index, E element) {
        //Agrega un elemento en el indice indicado, despues si es necesesario, mueve todos los elementos a la derecha del nuevo elemento (retorna bool de si se logro completar la accion)
        if (element == null || index >= effectiveSize) {
            return false;
        } else if (effectiveSize == CAPACITY) {
            addCapacity();
        }
        //mueve los elementos a la derecha
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        //inserta el elemento
        elements[index] = element;
        effectiveSize++;
        return true;
    }
     @Override
    public E set(int index, E element) {
        //Remplaza el elemento dado en la posicion indicada (retorna el elemento ingresado
        if (element == null || index < 0 || index >= effectiveSize) {
            return null;
        }

        E i = elements[index];
        elements[index] = element;
        return i;
    }


    @Override
    public boolean addLast(E element) {
        //Agrega el elemento dado al "Final" del ArrayList (indice effectiveSize + 1)(retorna bool de si se logro completar la accion)
        if (element == null) {
            return false;
        } else if (effectiveSize == CAPACITY) {
            addCapacity();
        }

        elements[effectiveSize++] = element;
        return true;
    }

    private void addCapacity() {
        //Es el encargado de agregar el doble de capacidad cuando sera necesario
        E[] tmp = (E[]) new Object[CAPACITY * 2];
        for (int i = 0; i < CAPACITY; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        CAPACITY = CAPACITY * 2;
    }

    @Override
    public E getFirst() {
        //Obtiene el primer elemento
        return elements[0];
    }

    @Override
    public E getLast() {
        //Obtiene el ultimo elemento
        return elements[effectiveSize - 1];
    }

    @Override
    public boolean removeFirst() {
        //Quita el primer elemento y recorre los demas elementos hacia la izquierda (retorna bool de si se logro completar la accion)
        if (isEmpty()) {
            return false;
        }
        effectiveSize--;
        //mueve los elementos hacia la izquierda
        for (int i = 0; i < effectiveSize; i++) {
            elements[i] = elements[i + 1];
        }
        //"elimina" el ultimo elemento
        elements[effectiveSize] = null;
        return true;
    }

    @Override
    public boolean removeLast() {
        //Quita el ultimo elemento (retorna bool de si se logro completar la accion)
        if (isEmpty()) {
            return false;
        }

        elements[--effectiveSize] = null;
        return true;
    }

    @Override
    public boolean isEmpty() {
        //Esta vacio? 
        return effectiveSize == 0;
    }

    @Override
    public boolean contains(E element) {
        //Verifica si el elemento dado esta en el ArrayList (retorna bool de si se logro completar la accion)
        if (element == null || isEmpty()) {
            return false;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");

        for (int i = 0; i < effectiveSize; i++) {
            if (i != effectiveSize - 1) {
                s.append(elements[i] + ", ");
            } else {
                s.append(elements[i]);
            }
        }

        s.append("]");
        return s.toString();
    }

    public List<E> slicing(int start, int end) {
        //Retorna una nueva lista con los elemento de los indices indicados (no modifica al ArrayList original)
        List<E> lista = new ArrayList<>();
        if (start >= end || end > effectiveSize) {
            return lista;
        }

        for (int i = start; i < end; i++) {
            lista.addLast(elements[i]);
        }

        return lista;
    }

    

    @Override
    public E get(int index) {
        //Obtiene el elemento del indice dado
        if (effectiveSize == 0 || index < 0 || index >= effectiveSize) {
            return null;
        }
        return elements[index];
    }
//POR MODIFICAR ----------------------------------------------------------------------------------------------------
    @Override
    public int indexOf(E element) {
        
        if (element == null) {
            return -1;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }
//------------------------------------------------------------------------------------------------------------------
    @Override
    public E remove(int index) {
        //Remueve el elemendo del indice ingresado y mueve el ArrayList a la izquierda si es necesario (retorna el elemento removido)
        if (effectiveSize == 0 || index < 0 || index >= effectiveSize) {
            return null;
        }
        E element = elements[index];
        //mueve el ArrayList hacia la izquieda
        if (index < effectiveSize - 1) {
            for (int i = index; i < effectiveSize - 1; i++) {
                elements[i] = elements[i + 1];
            }
        }        
        elements[effectiveSize - 1] = null;
        effectiveSize--;
        return element;
    }

    @Override
    public boolean remove(E element) {
        //Remueve el elemnto ingresado (retorna bool de si se logro completar la accion o si se encontro o no el elemento)
        if (element == null) {
            return false;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                remove(i);
                return true;
            }
        }

        return false;
    }

   
    @Override
    public int size() {
        //tamaño xd
        return effectiveSize;
    }
//Implementar el iterador en una nueva clase -------------------------------------------------------------
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E element = elements[cursor];
                cursor++;
                return element;
            }
        };
        return it;
    }
//---------------------------------------------------------------------------------------------------------

    @Override
    public boolean addAll(List<E> l) {
        //Agrega los elementos de la lista ingresada al final del ArrayList (retorna bool de si se logro completar la accion)
        if (l == null) {
            return false;
        }
        for (int i = 0; i < l.size(); i++) {
            this.addLast(l.get(i));
        }
        return true;
    }

    @Override
    public boolean clear() {
        for (int to = effectiveSize; to > 0; to--){
            elements[--effectiveSize] = null;
        }
        CAPACITY = 100; 
        return true;
    }
    
    

    
    
}
