import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedIntList implements IntList {

    // define what a node is
    private class Node {
        int data;
        Node next;
    }

    // set up the head field
    private Node head;

    // set up the size field
    private int size;

    //add a constructor to initialize
    public LinkedIntList()
    {
        head = null;
        size = 0;
    }

    /**
     * Prepends (inserts) the specified value at the front of the list (at index 0).
     * Shifts the value currently at the front of the list (if any) and any
     * subsequent values to the right.
     *
     * @param value value to be inserted
     */
    @Override
    public void addFront(int value)
    {
        //considerations: empty list

        // set up a new node
        Node newNode = new Node();

        if (head == null) {
            // the list is currently empty
            head = newNode;
            head.data = value;
            size++;
        }
        else {
            // the list currently has some nodes in it
            newNode.next = head;
            head = newNode;
            head.data = value;
            size++;
        }
    }

    /**
     * Appends (inserts) the specified value at the back of the list (at index size()-1).
     *
     * @param value value to be inserted
     */
    @Override
    public void addBack(int value)
    {
        //considerations: empty list
        //create a current to traverse list
        //create new node
        Node current = head;
        Node node = new Node();
        node.data = value;

        if (head == null) {
            // the list is currently empty
            head.data = node.data;
            size++;
        }

        //otherwise, assign the current.next to the new value
        while (current.next != null)
        {
            current = current.next;
        }
        current.next = node;
    }

    /**
     * Inserts the specified value at the specified position in this list.
     * Shifts the value currently at that position (if any) and any subsequent
     * values to the right.
     *
     * @param index index at which the specified value is to be inserted
     * @param value value to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, int value)
    {
        //considerations: empty list or invalid index

        //keep track of current
        Node current = head;
        int currentIndex = 0;

        Node newNode = new Node();
        newNode.data = value;

        //invalid index
        if (index < 0)
        {
            throw new IndexOutOfBoundsException("Index is out of range");
        }

        //if head is null - index 0
        if (head == null)
        {
            head.data = value;
            size++;
        }
        //if index 1 is null, assign new node
        else if (current.next == null)
        {
            current.next = newNode;
        }
        else //if head && current.next are not null
        {
            Node previousNode = current;

            //move through until you get to the last
          while (currentIndex != index && current.next != null)
          {
              previousNode = current;
              current = current.next;
              currentIndex++;
          }
          previousNode.next = newNode;
          newNode.next = current.next;
          //shift everything over by reassigning
        }
    }

    /**
     * Removes the value located at the front of the list
     * (at index 0), if it is present.
     * Shifts any subsequent values to the left.
     */
    @Override
    public void removeFront()
    {
        Node current = head;
        //consider: empty list
        if (head == null)
        {
            throw new NoSuchElementException("The list is empty");
        }
        head = current.next;
    }

    /**
     * Removes the value located at the back of the list
     * (at index size()-1), if it is present.
     */
    @Override
    public void removeBack()
    {
        Node current = head;
        while (current.next != null)
        {
            current = current.next;
        }
        current = null;
    }

    /**
     * Removes the value at the specified position in this list.
     * Shifts any subsequent values to the left. Returns the value
     * that was removed from the list.
     *
     * @param index the index of the value to be removed
     * @return the value previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public int remove(int index)
    {
        int currentIndex = 0;
        Node current = head;
        Node previous = current;

        while (currentIndex != index)
        {
            previous = current;
            current = current.next;
            currentIndex++;
        }
        previous = current.next;
        return current.data;
    }

    /**
     * Returns the value at the specified position in the list.
     *
     * @param index index of the value to return
     * @return the value at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public int get(int index)
    {
        Node current = head;
        int currentIndex = 0;

        while (currentIndex != index && current.next != null)
        {
            current = current.next;
            currentIndex++;
        }
        return current.data;
    }

    /**
     * Returns true if this list contains the specified value.
     *
     * @param value value whose presence in this list is to be searched for
     * @return true if this list contains the specified value
     */
    @Override
    public boolean contains(int value)
    {
        Node current = head;
        while (current.data != value && current.next != null)
        {
            current = current.next;
        }
        return true;
    }

    /**
     * Returns the index of the first occurrence of the specified value
     * in this list, or -1 if this list does not contain the value.
     *
     * @param value value to search for
     * @return the index of the first occurrence of the specified value in this list
     * or -1 if this list does not contain the value
     */
    @Override
    public int indexOf(int value)
    {
        Node current = head;
        int currentIndex = 0;
        while (current.data != value)
        {
            current = current.next;
            currentIndex++;
        }
        return currentIndex;
    }

    /**
     * Returns true if this list contains no values.
     *
     * @return true if this list contains no values
     */
    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * Returns the number of values in this list.
     *
     * @return the number of values in this list
     */
    @Override
    public int size()
    {
        Node current = head;
        while (current != null)
        {
            size++;
            current = current.next;
        }
        return size - 1;
    }

    /**
     * Removes all the values from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear()
    {
        head = null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Integer> iterator()
    {
        SinglyLinkedIterator theIterator = new SinglyLinkedIterator();
        return theIterator;
    }

    //helper class/type that defines how the iterator works
    private class SinglyLinkedIterator implements Iterator<Integer>{

        private Node current;

        public SinglyLinkedIterator() {
            current = head;
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext()
        {
            //return current != null; <----- another way to write this
            if (current == null) {
                return false;
            }
            else {
                return true;
            }
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Integer next()
        {
            if (current == null)
            {
                throw new NoSuchElementException("There is no next one to go to!!");
            }
            int item = current.data;
            current = current.next;
            return item;
        }
    }
}
