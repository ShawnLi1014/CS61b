/** Array based list.
 *  @author Josh Hug
 */

public class AList {

    private int size;
    private int [] items;
    /** Creates an empty list. */
    public AList() {
        items = new int[2];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    private void resize(int capacity){
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a; 
    }

    public void addLast(int x) {
        if(size == items.length){
            resize(size + 1);
        }
        items[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];        
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];        
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;        
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        int removed = getLast();
        size -= 1;
        return removed;
    }

    public static void main(String [] args){
        AList a = new AList();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        System.out.println(a.items[0]);

    }
} 