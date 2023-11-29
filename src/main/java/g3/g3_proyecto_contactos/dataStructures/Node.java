public class Node<E> {

    private E content;
    private Node<E> preview;
    private Node<E> next;

    public Node(E data) {
        this.content = data;
        this.next = null;
        this.preview = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E data) {
        this.content = data;
    }

    public Node<E> getNext() {
        return next;
    }
    public Node<E> getPreview(){
        return preview
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    public void setPreview(Node<E> preview) {
        this.preview = preview;
    }
}
