public class ArrayDeque<T> implements Deque<T> {

    private int size;
    private int nextFirst;
    private int nextLast;
    private T [] items;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Resize the array depending. */
    private void resize() {
        /* Define a usage Ratio R */
        double R = size/(double)items.length;
        if(size == items.length) {
            T [] a = (T []) new Object[size * 2];
            /* Copy all the elements to a in order */
            int first = rmNextFirst(nextFirst);
            for(int i = 0; i < size; i++) {
                a[i] = items[first];
                /* Update first */
                first = rmNextFirst(first);
            }
            /* Update nextFirst and nextLast */
            nextLast = size;
            items = a;
            nextFirst = items.length - 1;
        }
        if(R < 0.25 && items.length > 16) {
            T [] a = (T []) new Object[items.length / 2];
            /* Copy all the elements to a in order */
            int first = rmNextFirst(nextFirst);
            for(int i = 0; i < size; i++) {
                a[i] = items[first];
                /* Update first */
                first = rmNextFirst(first);
            }
            /* Update nextFirst and nextLast, make them point to an empty box */
            nextLast = size;
            items = a;
            nextFirst = items.length - 1;
        }
    }

    /** Update nextFirst to make sure that it point to an empty box.
     *
     * @param nextFirst the next first location
     * @return the new nextFirst
     */
    private int addNextFirst(int nextFirst) {
        if(nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        return nextFirst;
    }

    /** Update nextFirst to make sure that it point to an empty box.
     *
     * @param nextLast the next last location
     * @return the new nextLast
     */
    private int addNextLast(int nextLast) {
        if(nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        return nextLast;
    }

    /** Update the nextFirst when removing items.
     *
     * @param nextFirst the next first location
     * @return the new nextFirst
     */
    private int rmNextFirst(int nextFirst) {
        if(nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        return nextFirst;
    }

    /** Update nextLast when removing items.
     *
     * @param nextLast  the next last location
     * @return the new nextLast
     */
    private int rmNextLast(int nextLast) {
        if(nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        return nextLast;
    }

    /** Add an item to the first of the array.
     *
     * @param item the item to add
     */
    @Override
    public void addFirst(T item ) {
        resize();
        items[nextFirst] = item;
        size++;
        nextFirst = addNextFirst(nextFirst);
    }

    /** Add an item to the last of the array.
     *
     * @param item the item to add
     */
    @Override
    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        size++;
        nextLast = addNextLast(nextLast);
    }

    /** Check if the Deque is empty.
     *
     * @return true if is empty
     */
    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    /** Print out the Deque. */
    @Override
    public void printDeque() {
        int first = rmNextFirst(nextFirst);
        for(int i = 0; i < size; i++) {
            System.out.print(items[first] + " ");
            first = rmNextFirst(first);
        }
    }

    /** Remove the first item form the array deque.
     *
     * @return the removed item
     */
    @Override
    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        nextFirst = rmNextFirst(nextFirst);
        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        /* Update nextFirst */
        size -= 1;
        resize();
        return toRemove;
    }

    /** Remove the last item from the arrayDeque.
     *
     * @return the removed item
     */
    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        nextLast = rmNextLast(nextLast);
        T toRemove = items[nextLast];
        items[nextLast] = null;
        /* Update nextLast */
        size -= 1;
        resize();
        return toRemove;
    }

    /** Get the ith item of the array.
     *
     * @param index the location
     * @return the ith item
     */
    @Override
    public T get(int index) {
        if(index >= size) {
            return null;
        }
        if(nextFirst + index >= items.length - 1) {
            return items[nextFirst + index - items.length + 1];
        } else {
            return items[nextFirst + index + 1];
        }
    }

    /** Return the size of the arrayDeque.
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }
}
