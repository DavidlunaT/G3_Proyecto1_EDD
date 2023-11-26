/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package g3.g3_proyecto_contactos.interfaces;

/**
 *
 * @author David
 */
public interface List<E> extends Iterable<E> {

    public boolean addFirst(E element);

    public boolean addLast(E element);

    public boolean removeFirst();

    public boolean removeLast();

    public E getFirst();

    public E getLast();

    public boolean insert(int index, E element);

    public boolean contains(E element);

    public E get(int index);

    public int indexOf(E element);

    public boolean isEmpty();

    public E remove(int index);

    public boolean remove(E element);

    public E set(int index, E element);

    public int size();
    
    public boolean addAll (List<E> l);
    
    public boolean clear();
    
    

}
