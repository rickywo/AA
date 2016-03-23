import java.io.PrintStream;
import java.util.*;



public class SortedLinkedListMultiset<T extends Comparable<T>> extends LinkedListMultiset<T>
{
	protected Node mHead;

    /** Length of list. */
    protected int mLength;
    
	public SortedLinkedListMultiset() {
		mHead = null;
		mLength = 0;
		
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		Node newNode = new Node(item, 1);
        
        // If head is empty, then list is empty and head reference need to be initialised.
        if (mHead == null || item.compareTo(mHead.getValue()) < 0 ) {
        	newNode.setNext(mHead);
            mHead = newNode;
            mLength ++;
        }
        // otherwise, check if the item exists in the list.
        else {
        	Node currNode = mHead;
            while ( currNode.getNext()!=null && 
            		item.compareTo(currNode.getNext().getValue())>=0 ) {
            	currNode = currNode.getNext();
            }
            // currNode: the place to insert new element or increase count
           
			if (currNode.getValue().equals(item)) {
				currNode.increaseCount();
			} else if (currNode.getNext() == null) {
				newNode.setNext(null);
				currNode.setNext(newNode);
			} else {
				newNode.setNext(currNode.getNext());
				currNode.setNext(newNode);
			}
			mLength ++;
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
	} // end of add()
	
	
	public void removeOne(T item) {
		Node currNode = mHead;
        Node preNode = mHead;
        while(currNode != null) { // Step through whole list until hit the last element
        	if(currNode.getValue().equals(item)) {
        		// Decrease count if the item is found
        		currNode.decreaseCount();
        		// If the count is 0 then remove this node
        		if(currNode.getCount() == 0) {
	        		if(currNode == mHead) {
	        			mHead = currNode.getNext();
	        		} else if(currNode.getNext() != null) {
						preNode.setNext(currNode.getNext());
					} else {
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
        			mHead = currNode.getNext();
        		} else if(currNode.getNext() != null) {
					preNode.setNext(currNode.getNext());
				} else {
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
	
} // end of class SortedLinkedListMultiset