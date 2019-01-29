public class ArrayDeque<T> {
    private int size;
    /* nextFirst and nextLast always point to an empty items position */
    private int nextFirst;
    private int nextLast;
    private T [] items;

    public ArrayDeque(){
        items = (T []) new Object[2];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    private void resize(){
        T [] a = (T []) new Object[size * 2];
//        System.arraycopy(items, 0, a, 0, size);
        int first = nextFirst;
        for(int i = 0; i < size; i++){
            a[i] = items[first];
            if(first == items.length - 1){
                first = 0;
            } else{
                first += 1;
            }
        }
        nextFirst = 0;
        nextLast = items.length - 1;
        items = a;


    }

    /*if size+1 == items.length, resize items to free space for nextFirst and nextLast */
    private void sizePlusOne(){
        size += 1;
        if(size == items.length){
            resize();
        }
    }

    /* Update the nextFirst and nextLast to make sure they point to an empty box before assign item to the array */
    private void updateFirst(){
        if(items[nextFirst] != null){
            if(nextFirst == 0){
                nextFirst = items.length - 1;
                /* if the new position is occupied, continue search for empty position */
            }else{
                nextFirst -= 1;
            }
        }
    }

    private void updateLast(){
        if(items[nextLast] != null){
            if(nextLast == items.length - 1){
                nextLast = 0;
            } else{
                nextLast += 1;
            }
        }
    }


    public void addFirst(T item){
        updateFirst();
        items[nextFirst] = item;
        sizePlusOne();
    }

    public void addLast(T item) {
        updateLast();
        items[nextLast] = item;
        sizePlusOne();

    }

//    private T getFirst(){
//
//    }

    /* Check if the Deque is empty */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    /* Print out the Deque */
    public void printDeque(){
        for(int i = 0; i < items.length; i++){
            while(items[i] != null){
                System.out.print(items[i] + " ");
            }
        }
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        return toRemove;
    }





    public static void main(String [] args){
        ArrayDeque<Integer> a = new ArrayDeque();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addLast(100);
        a.addFirst(4);
        a.addFirst(5);
    }
}
