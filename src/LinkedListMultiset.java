import java.io.PrintStream;
import java.util.*;


public class LinkedListMultiset<T extends Comparable<T>> extends Multiset<T>
{
	
	protected Node mHead;

    /** Length of list. */
    protected int mLength;
    
	public LinkedListMultiset() {
		mHead = null;
		mLength = 0;
		
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		Node newNode = new Node(item, 1);
        
        // If head is empty, then list is empty and head reference need to be initialised.
        if (mHead == null) {
            mHead = newNode;
            mLength++;
        }
        // otherwise, check if the item exists in the list.
        else {
        	Node currNode = mHead;
        	boolean notFound = true;
            for (int i = 0; i < mLength; ++i) {
            	if (currNode.getValue().equals(newNode.getValue())) {
            		// If element with the same value exist
            		currNode.increaseCount();
            		notFound = false;
            	}
                currNode = currNode.getNext();
            }
            if(notFound) { 
            	// Append this element if item is not currently existing in the list
            	newNode.setNext(mHead);
            	mHead = newNode;
            	mLength++;
            }
        }	
	} // end of add()
	
	
	public int search(T item) {
		Node currNode = mHead;
        while (currNode != null) {
        	if (currNode.getValue().compareTo(item) == 0) {
        		// return the count of the element
        		return currNode.getCount();
        	}
            currNode = currNode.getNext();
        }
		// default return, please override when you implement this method
		return 0; // return 0 if not found
	} // end of search()
	
	
	public void removeOne(T item) {
		Node currNode = mHead;
        Node preNode = mHead;
        while(currNode != null) { 
        	// Step through whole list until hit the last element
        	if(currNode.getValue().equals(item)) {
        		// Decrease count if the item is found
        		currNode.decreaseCount();
        		// If the count is 0 then remove this node
        		if(currNode.getCount() == 0) {
	        		if(currNode == mHead) {
	        			// Remove the head node
	        			mHead = currNode.getNext();
	        		} else if(currNode.getNext() != null) {
	        			// Remove the internal node
						preNode.setNext(currNode.getNext());
					} else {
						// Remove the tail node
	        			preNode.setNext(null);
	        		}
	        		mLength --;
	        		return;
        		}
        	}
        	preNode = currNode;
        	currNode = currNode.getNext();
        }
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		Node currNode = mHead;
        Node preNode = mHead;
        while(currNode != null) { // Step through whole list until hit the last element
        	if(currNode.getValue().equals(item)) {
        		// Remove this node anyway
        		if(currNode == mHead) {
        			// Remove head node
        			mHead = currNode.getNext();
        		} else if(currNode.getNext() != null) {
        			// Remove the internal node
					preNode.setNext(currNode.getNext());
				} else {
					// Remove tail node
        			preNode.setNext(null);
        		}
        		mLength --;
        		return;
        	}
        	preNode = currNode;
        	currNode = currNode.getNext();
        }
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		Node currNode = mHead;
		while(currNode != null) { // Step through whole list until hit the last element
			out.println(currNode.getValue() + printDelim + currNode.getCount());
			currNode = currNode.getNext();
        }
	} // end of print()
	
	/**
     * Node type, inner class.
     */
    class Node
    {
        /** Stored value of node. */
        protected int mNum;
        protected T mItem;
        /** Reference to next node. */
        protected Node mNext;

        public Node(T item, int num) {
            mItem = item;
        	mNum = num;
            mNext = null;
        }

        public T getValue() {
            return mItem;
        }


        public Node getNext() {
            return mNext;
        }


        public void setValue(T item) {
            mItem = item;
        }


        public void setNext(Node next) {
            mNext = next;
        }
        
        public int getCount() {
        	return mNum;
        }
        
        
        public void increaseCount() {
        	mNum ++;
        }
        
        public void decreaseCount() {
        	mNum --;
        }

    } // end of inner class Node
	
} // end of class LinkedListMultiset