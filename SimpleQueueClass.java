/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p5_package;
/**
 *
 * @author mohammedalfouzan
 */
public class SimpleQueueClass {
    
    /* Provides constant for default capacity */
    private final int DEFAULT_CAPACITY = 10;

    /* Provides constant -999999 for access failure messaging */
    public static final int FAILED_ACCESS = -999999;
    
    /* Stores current capacity of queue class */
    private int capacity;
    
    /* Stores current size of queue class */
    private int size;
    
    /* Stores queue head index */
    private int headIndex;
    
    /* Stores queue tail index */
    private int tailIndex;
    
    /* Integer array stores queue data */
    private int[] queueData;

    /**
     * Default constructor
     */
    public SimpleQueueClass()
    {
        headIndex = 0;
        tailIndex = -1;
        size = 0;
        capacity = DEFAULT_CAPACITY;
        queueData = new int [capacity];
    }
    
    /**
     * Initialization constructor
     * <p>
     * @param capacitySetting - initial capacity of queueData class
     */
    public SimpleQueueClass(int capacitySetting)
    {
        headIndex = 0;
        tailIndex = -1;
        size = 0;
        capacity = capacitySetting;
        queueData = new int [capacitySetting];
    }
    
    /**
     * Copy constructor
     * Note: queue is copied so that head index is at zero and tail index is 
     * at size-1; i.e., this resets the array to start at zero
     * <p>
     * @param copied - SimpleQueueClass object to be copied
     */
    public SimpleQueueClass(SimpleQueueClass copied)
    {
        this.capacity = copied.capacity;
        this.queueData = new int[capacity];
        this.headIndex = copied.headIndex;
        this.tailIndex = copied.tailIndex;
        this.size = copied.size;
       
        int freeIndex;
        for( freeIndex = 0; freeIndex < size; freeIndex++)
        {
            this.queueData[freeIndex] = copied.queueData[this.headIndex];
            updateHeadIndex(); 
            updateTailIndex();
        }

    }
    
    /**
     * Checks for resize, then enqueues value
     * Note: Updates tail index, then appends value to array at tail index
     * <p>
     * @param newValue - Value to be enqueued
     */
    public void enqueue(int newValue)
    {
        checkForReSize();

        updateTailIndex();
        queueData[tailIndex] = newValue;
        size++;
        
    }
   
    
    /**
     * Removes and returns value from front of queue
     * Note: Acquires data from head of queue, then updates head index 
     * <p>
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
    {
        int toDeq;
        if (isEmpty())
        {
            return FAILED_ACCESS;
        }
        
        toDeq = peekFront();
        updateHeadIndex();
        size--;
        return toDeq;
        
    }
    
    /**
     * Provides peek at front of queue
     * <p>
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
    {
        int peekVal;
        if (isEmpty())
        {
            return FAILED_ACCESS;
        }
        peekVal = queueData[headIndex];
        return peekVal;
        
    }
    
    
   
    
    /**
     * Reports queue empty state
     * Note: Does not use if/else
     * <p>
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        return size == 0;
        
    }
    
    /**
     * Checks for resize and resizes to twice the current capacity if needed
     * Note: Returns true if resize is necessary and is conducted; returns false
     * if no action is taken
     * <p>
     * @return success of operation
     */
    private boolean checkForReSize()
    {
        int newArray[];
        int index;
        int doubledArray = capacity * 2;
        if ( capacity == size )
        {
            newArray = new int [ doubledArray ];
         
            for ( index = 0 ; index < size ; index++ )
            {
                newArray[ index ] = queueData[ index ];
            }
            capacity = doubledArray;
            queueData = newArray;
            return true;
            
        }
        
        return false;
        
    }
    
    /**
     * Clears the queue by setting the size to zero, the tail index to -1 
     * and the head index to zero
     */
    public void clear()
    {
        size = 0;
        tailIndex = -1;
        headIndex = 0;
    }
    
    
    /**
     * Updates queue head index to wrap around array as needed
     * Note: Does not use if/else
     */
    private void updateHeadIndex()
    {   
        headIndex++;
        headIndex %= capacity;
    }
    
    /**
     * Updates queue tail index to wrap around array as needed
     * Note: Does not use if/else
     */
    private void updateTailIndex()
    {
        tailIndex++;
        tailIndex %=  capacity;
    }
}
