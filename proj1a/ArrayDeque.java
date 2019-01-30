public class ArrayDeque<T> {

    private int size;
    private int nextFirst;
    private int nextLast;
    private T [] items;

    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
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
                first = rmNextFirst(first);
            }
            /* Update nextFirst and nextLast */
            nextFirst = 0;
            nextLast = size - 1;
            items = a;
        }

        if(R < 0.25 && items.length > 16){
            T [] a = (T []) new Object[items.length / 2];
            /* Copy all the elements to a in order */
            int first = nextFirst;
            for(int i = 0; i < size; i++){
                a[i] = items[first];
                /* Update first */
                first = rmNextFirst(first);
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
        resize();
    }

    private void sizeMinusOne(){
        size -= 1;
        resize();
    }

    /* Update the nextFirst and nextLast to make sure they point to an empty box before assign item to the array */
    private int addNextFirst(int nextFirst){
        if(nextFirst == 0){
            nextFirst = items.length - 1;
        }else{
            nextFirst -= 1;
        }
        return nextFirst;
    }

    private int addNextLast(int nextLast){
        if(nextLast == items.length - 1){
            nextLast = 0;
        } else{
            nextLast += 1;
        }
        return nextLast;
    }

    /* Get the number of the first box */
    private int rmNextFirst(int nextFirst){
        if(nextFirst == items.length - 1){
            nextFirst = 0;
        } else{
            nextFirst += 1;
        }
        return nextFirst;
    }

    private int rmNextLast(int nextLast){
        if(nextLast == 0){
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        return nextLast;
    }


    public void addFirst(T item){
        resize();
        nextFirst = addNextFirst(nextFirst);
        items[nextFirst] = item;
        size++;

    }

    public void addLast(T item) {
        resize();
        nextLast = addNextLast(nextLast);
        items[nextLast] = item;
        size++;
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
        int first = rmNextFirst(nextFirst);
        for(int i = 0; i < size; i++){
            System.out.print(items[first] + " ");
            first = rmNextFirst(first);
        }
    }

    /* Remove the first item form the array deque */
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        nextFirst = rmNextFirst(nextFirst);
        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        /* Update nextFirst */
        sizeMinusOne();
        return toRemove;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        nextLast = rmNextLast(nextLast);
        T toRemove = items[nextLast];
        items[nextLast] = null;
        /* Update nextLast */
        sizeMinusOne();
        return toRemove;

    }

    public T get(int index) {
        if(index >= size){
            return null;
        }
        if(nextFirst + index >= items.length - 1){
            return items[nextFirst + index - items.length + 1];
        } else{
            return items[nextFirst + index + 1];
        }
    }

    public int size(){
        return size;
    }


//    public static void main(String [] args){
//        ArrayDeque<Integer> a = new ArrayDeque();
//        for(int i = 0; i < 8; i ++){
//            a.addLast(i);
//        }
//        for(int j = 0; j < 8; j ++){
//            a.removeFirst();
//        }
//        for(int i = 0; i < 8; i ++){
//            a.addLast(i);
//        }
////        a.removeLast();
////        a.removeFirst();
////        a.removeFirst();
////        a.addFirst(7);
//        a.addFirst(3);
//        int b = a.get(0);
//        a.removeLast();
//        a.printDeque();
//    }
}
