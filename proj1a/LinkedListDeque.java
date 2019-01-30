public class LinkedListDeque<T>{
    /* Linked Node class */
    public class linkedNode {
        public linkedNode prev;
        public T item;
        public linkedNode next;

        private linkedNode(linkedNode x, T i, linkedNode y){
            prev = x;
            item = i;
            next = y;
        }
    }

    private int size;
    private linkedNode sentinel;

    /* Create an empty deque */
    public LinkedListDeque(){
        sentinel = new linkedNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /* add a linkednode at the first of the deque */
    public void addFirst(T x) {
        linkedNode toFront = new linkedNode(sentinel, x, sentinel.next);
        if(isEmpty()){
            sentinel.prev = toFront;
        }else {
            sentinel.next.prev = toFront;
        }
        sentinel.next = toFront;
        size += 1;
    }

    public void addLast(T x) {
        linkedNode toLast = new linkedNode(sentinel.prev, x, sentinel);
        if(isEmpty()){
            sentinel.next = toLast;
        } else{
            sentinel.prev.next = toLast;
        }
        sentinel.prev = toLast;
        size += 1;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        linkedNode ptr = sentinel;
        for(int i = 1; i <= size; i++){
            ptr = ptr.next;
            System.out.print(ptr.item + " ");
        }
    }

    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }

        linkedNode toRemove = sentinel.next;
        linkedNode toFront = toRemove.next;
        sentinel.next = toFront;
        toFront.prev = sentinel;
        toRemove.next = null;
        toRemove.prev = null;
        size -= 1;
        return toRemove.item;
    }

    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        linkedNode toRemove = sentinel.prev;
        linkedNode toLast = toRemove.prev;
        sentinel.prev = toLast;
        toLast.next = sentinel;
        toRemove.prev = null;
        toRemove.next = null;
        size -= 1;
        return toRemove.item;
    }

    public T get(int index){
        linkedNode ptr = sentinel.next;
        if(index > size - 1) {
            return null;
        }
        for(int i = 0; i < index; i++){
            ptr = ptr.next;
        }
        return ptr.item;
    }

    private T getRec(linkedNode ptr, int index){
        if(index > size - 1){
            return null;
        }
        if(index == 0){
            return ptr.item;
        }
        return getRec(ptr.next, index - 1);
    }

    public T getRecursive(int index){
        return getRec(sentinel.next, index);
    }

}