public class LinkedListDeque<T> {
    /** Linked Node class */
    public class linkedNode {
        public linkedNode prev;
        public T item;
        public linkedNode next;

        private linkedNode(linkedNode x, T i, linkedNode y) {
            prev = x;
            item = i;
            next = y;
        }
    }

    private int size;
    private linkedNode sentinel;

    /** Create an empty deque. */
    public LinkedListDeque() {
        sentinel = new linkedNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Add a linkednode at the first of the deque.
     *
     * @param x the item that about to be added
     */
    public void addFirst(T x) {
        linkedNode toFront = new linkedNode(sentinel, x, sentinel.next);
        if(isEmpty()) {
            sentinel.prev = toFront;
        } else {
            sentinel.next.prev = toFront;
        }
        sentinel.next = toFront;
        size += 1;
    }

    /** Add a linkednode at the last of the deque.
     *
     * @param x the item that about to be added
     */
    public void addLast(T x) {
        linkedNode toLast = new linkedNode(sentinel.prev, x, sentinel);
        if(isEmpty()) {
            sentinel.next = toLast;
        } else {
            sentinel.prev.next = toLast;
        }
        sentinel.prev = toLast;
        size += 1;
    }

    /** Determine if the deque is empty.
     *
     * @return true if is empty
     */
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    /** Return the size of the Deque.
     *
     * @return size of the deque
     */
    public int size() {
        return size;
    }

    /** Print the Deque. */
    public void printDeque() {
        linkedNode ptr = sentinel;
        for(int i = 1; i <= size; i++) {
            ptr = ptr.next;
            System.out.print(ptr.item + " ");
        }
    }

    /** Remove the first node from the deque.
     *
     * @return toRemove.item
     */
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

    /** Remove the last node from the deque.
     *
     * @return the removed item
     */
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

    /** Get the ith item from the array.
     *
     * @param index the position of the item
     * @return the item
     */
    public T get(int index) {
        linkedNode ptr = sentinel.next;
        if(index > size - 1) {
            return null;
        }
        for(int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    /** Helper method for getRecursive.
     *
     * @param ptr the linked deque
     * @param index position
     * @return the ith item
     */
    private T getRec(linkedNode ptr, int index) {
        if(index > size - 1) {
            return null;
        }
        if(index == 0) {
            return ptr.item;
        }
        return getRec(ptr.next, index - 1);
    }

    /** Get the ith item of the deque using recursion
     *
     * @param index the position of the item
     * @return the ith item
     */
    public T getRecursive(int index) {
        return getRec(sentinel.next, index);
    }

}