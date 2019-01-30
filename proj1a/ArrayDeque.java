public class ArrayDeque<T> {
    private int size;
    /* nextFirst and nextLast always point to an empty items position */
    private int nextFirst;
    private int nextLast;
    private T [] items;

    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    private void resize(){
        /* Define a usage Ratio R */
        double R = size/(double)items.length;
        if(size == items.length){
            T [] a = (T []) new Object[size * 2];
            /* Copy all the elements to a in order */
            int first = nextFirst;
            for(int i = 0; i < size; i++){
                a[i] = items[first];
                /* Update first */
                first = updateFirst(first);
            }
            /* Update nextFirst and nextLast */
            nextFirst = 0;
            nextLast = size - 1;
            items = a;
        }

        if(R < 0.25){
            T [] a = (T []) new Object[items.length / 2];
            /* Copy all the elements to a in order */
            int first = nextFirst;
            for(int i = 0; i < size; i++){
                a[i] = items[first];
                /* Update first */
                first = updateFirst(first);
            }
            /* Update nextFirst and nextLast */
            nextFirst = 0;
            nextLast = size - 1;
            items = a;
        }

    }

    /*if size+1 == items.length, resize items to free space for nextFirst and nextLast */
    private void sizePlusOne(){
        size += 1;
        if(size == items.length){
            resize();
        }
    }

    private void sizeMinusOne(){
        size -= 1;
        resize();
    }

    /* Update the nextFirst and nextLast to make sure they point to an empty box before assign item to the array */
    private void updateNextFirst(){
        if(items[nextFirst] != null){
            if(nextFirst == 0){
                nextFirst = items.length - 1;
            }else{
                nextFirst -= 1;
            }
        }
    }

    private void updateNextLast(){
        if(items[nextLast] != null){
            if(nextLast == items.length - 1){
                nextLast = 0;
            } else{
                nextLast += 1;
            }
        }
    }

    /* Get the number of the first box */
    private int updateFirst(int first){

        if(first == items.length - 1){
            first = 0;
        } else{
            first += 1;
        }
        return first;
    }

    private int updateLast(int last){
        if(last == 0){
            last = items.length - 1;
        } else {
            last -= 1;
        }
        return last;
    }


    public void addFirst(T item){
        updateNextFirst();
        items[nextFirst] = item;
        sizePlusOne();
    }

    public void addLast(T item) {
        updateNextLast();
        items[nextLast] = item;
        sizePlusOne();

    }



    /* Check if the Deque is empty */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    /* Print out the Deque */
    public void printDeque(){
        int first = nextFirst;
        for(int i = 0; i < size; i++){
            System.out.print(items[first] + " ");
            first = updateFirst(first);
        }
    }

    /* Remove the first item form the array deque */
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        /* Update nextFirst */
        nextFirst = updateFirst(nextFirst);
        sizeMinusOne();
        return toRemove;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T toRemove = items[nextLast];
        items[nextLast] = null;
        /* Update nextLast */
        nextLast = updateLast(nextLast);
        sizeMinusOne();
        return toRemove;

    }

    public T get(int index) {
        if(index >= size){
            return null;
        }
        int first = nextFirst;
        if(first + index > items.length - 1){
            return items[first + index - items.length];
        } else{
            return items[first + index];
        }
    }


    public static void main(String [] args){
        ArrayDeque<Integer> a = new ArrayDeque();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addLast(100);
        a.addFirst(4);
        a.addFirst(5);
        a.removeFirst();
        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
        a.removeLast();
        int b = a.get(1);
        a.printDeque();
    }
}
