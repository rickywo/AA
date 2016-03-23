/*
 * Copyright (C) 2016 Ricky Wu.
 */
import java.io.PrintStream;
import java.util.*;



public class BstMultiset<T extends Comparable <T>> extends Multiset<T>
{
	protected Treenode mRoot;
	
	public BstMultiset() {
		mRoot = null;
	} // end of BstMultiset()

	public void add(T item) {
		// Implement me!
		Treenode newNode = new Treenode(item, 1);
        newNode.setLchild(null);
        newNode.setRchild(null);
        // If heap is empty, then put element as the root node.
        if (mRoot == null) {
        	mRoot = newNode;
        } else {
        	Treenode currNode = mRoot;
        	while(currNode != null) {
        		if(item.compareTo(currNode.getValue()) == 0) {
        			// If node has some value as input item
        			currNode.increaseCount();
        			break;
        		} else if(item.compareTo(currNode.getValue()) > 0) {
        			// if item is greater than the value of current node
        			if(currNode.getRchild() != null) {
        				currNode = currNode.getRchild();
        			} else {
        				currNode.setRchild(newNode);
        				break;
        			}
        		} else {
        			// If item is less than the value of current node
        			if(currNode.getLchild() != null)
        				currNode = currNode.getLchild();
        			else {
        				currNode.setLchild(newNode);
        				break;
        			}
        		}
        	}
        }
	} // end of add()


	public int search(T item) {
		// Implement me!
		Treenode currNode = mRoot;
		while(currNode != null) {
    		if(item.compareTo(currNode.getValue()) == 0) {
    			// return the count of the given item
    			return currNode.getCount();
    		} else if(item.compareTo(currNode.getValue()) > 0) {
    			// if item is greater than the value of current node
    			if(currNode.getRchild() != null)
    				currNode = currNode.getRchild();
    		} else {
    			// If item is less than the value of current node
    			if(currNode.getLchild() != null)
    				currNode = currNode.getLchild();
    		}
    	}
		// End of while loop, and item is not found in the heap
		return 0;
	} // end of search()
	private Treenode delete(Treenode tnode, T item) {
		int identical = item.compareTo(tnode.getValue());
		if(identical > 0) tnode.setRchild(delete(tnode.getRchild(), item));
		else if(identical < 0) tnode.setLchild(delete(tnode.getLchild(), item));
		else {
			if(tnode.getCount() > 1) {
				tnode.decreaseCount();
				return tnode;
			}
			if(tnode.getRchild() == null) {
				// If target node has no R child then take L child to replace
				// target node
				return tnode.getLchild();
			}
			if(tnode.getLchild() == null) {
				// If target node has no L child then take R child to replace
				// target node
				return tnode.getRchild();
			}
			
			Treenode temp = tnode;
			tnode = getMin(temp.getRchild());
			
			tnode.setRchild(deleteMin(temp.getRchild()));
			tnode.setLchild(temp.getLchild());
		}
		return tnode;
	}
	
	private Treenode deleteMin(Treenode tnode) {
		if(tnode.getLchild() == null) return tnode.getRchild();
		tnode.setLchild(deleteMin(tnode.getLchild()));
		return tnode;
	}

	public void removeOne(T item) {
		mRoot = delete(mRoot, item);
    	
		// Implement me!
	} // end of removeOne()

	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()


	public void print(PrintStream out) {
		// Implement me!
		
		// Use stack structure to store nodes
		Stack<Treenode> stack = new Stack<Treenode>();
		Treenode currNode = mRoot;
		
	
		if(currNode == null) {
			return;
		}
		while(!stack.empty() || currNode != null) { // Step through whole list until stack is empty
			
			//			6
			//		   / \
			//		  3	  8
			//		 / \
			//		2	4
			//
			//  The traversal will be 2->3->4->6->8
			
			if(currNode != null) {
				// Traverse until hit the most left child and push every lchild into stack
				stack.push(currNode);
				currNode = currNode.getLchild();	
			} else {
				currNode = stack.pop();
				out.println(currNode.getValue() + printDelim + currNode.getCount());
				currNode = currNode.getRchild();
			}
        }
	} // end of print()
	
	
	private Treenode getMin(Treenode root) {
		Treenode currNode = root;
		while(currNode.getLchild() != null) {
			currNode = currNode.getLchild();
		}
		
		return currNode;
	}
	
	private boolean isLeaf(Treenode tnode) {
		if(tnode.getLchild() == null && tnode.getRchild() == null)
			return true;
		return false;
	}

	/**
     * Node type, inner class.
     */
    class Treenode
    {
        /** Stored value of node. */
        protected int mCount;
        protected T mItem;
        /** Reference to next node. */
        protected Treenode lChild;
        protected Treenode rChild;

        public Treenode(T item, int count) {
            mItem = item;
        	mCount = count;
            lChild = null;
            rChild = null;
        }

        public T getValue() {
            return mItem;
        }


        public Treenode getLchild() {
            return lChild;
        }
        
        public Treenode getRchild() {
        	return rChild;
        }


        public void setValue(T item) {
            mItem = item;
        }


        public void setLchild(Treenode tnode) {
            lChild = tnode;
        }
        
        public void setRchild(Treenode tnode) {
            rChild = tnode;
        }
        
        public int getCount() {
        	return mCount;
        }
        
        public void setCount(int count) {
        	mCount = count;
        }
        
        
        public void increaseCount() {
        	mCount ++;
        }
        
        public void decreaseCount() {
        	mCount --;
        }
        
        public boolean isLeaf() {
        	if(lChild == null && rChild == null)
        		return true;
        	return false;
        }

    } // end of inner class Node

} // end of class BstMultiset
