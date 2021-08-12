package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
  private LLNode<Integer> head; // head of the list
  private int size; // size (i.e. number of digits)

  // Returns size
  public int size() { 
    return size; 
  }
  
  // Returns the linked list (used only for JUnit test purpose)
  public LLNode<Integer> getList() { 
    return head; 
  }

  public LargeInteger() {
    head = null; 
    size = 0;
  }

  /** Constructor that takes a String as input and constructs the linked list.
   *  You can assume that the input is guaranteed to be valid: i.e. every character
   *  in the string is between '0' and '9', and the first character is never '0'
   *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
   *  to convert the character at index i to the integer value of that digit.
   *  Remember: the list nodes must be in reverse order as the characters in the string.
   */
  public LargeInteger(String input) {
      if(input.equals("0")) {
    	  head = new LLNode<Integer>(0,null);
    	  size++;
      }
      else {
    	  size = input.length();
    	  for(int i = 0; i < size; i++) {
    		  LLNode<Integer> temp = new LLNode<Integer>(input.charAt(i)-'0',null);
    		  temp.link = head;
    		  head = temp;
    	  }
      }
  }

  /** Divide *this* large integer by 10 and return this.
   *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
   */
  public LargeInteger divide10() {
	  if(this.size > 1) {
		  head = head.link;
		  size --;
		  return this;
	  }
	  else {
		  head.data = 0;
		  return this;
	  }
  }

  /** Multiply *this* large integer by 10 and return this.
   *  For example, 23*10 = 230, 0*10 = 0 etc.
   */
  public LargeInteger multiply10() {
        if(head.data == 0) {
        	return this;
        }
        else {
        	LLNode<Integer> add = new LLNode<Integer>(0,head);
        	head = add;
        	size++;
        	return this;
        }
  }

  /** Returns a *new* LargeInteger object representing the sum of this large integer
   *  and another one (given by that). Your code must correctly handle cases such as
   *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
   *  carry over at the highest digit (e.g. 9999+2=10001).
   */
  public LargeInteger add(LargeInteger that) {
        // TODO
	  	LLNode<Integer> thisTrav = this.head;
	  	LLNode<Integer> thatTrav = that.head;
	  	LLNode<Integer> newHead = null;
	  	LLNode<Integer> newHeadTrav = null;
	  	LLNode<Integer> temp = null;
	  	int passin = 0;
	  	int adding = 0;
	  	if(this.size > that.size) {
	  		for(int i = 0; i < this.size;i++) {
				if(thisTrav!= null && thatTrav != null) {
	  			 passin = thisTrav.data + thatTrav.data + adding;
	  			 if(passin >= 10) {
	  				 adding = passin/10;
	  				 passin = passin % 10;
	  				 temp = new LLNode<Integer>(passin,null); 
	  			 }
	  			 else {
	  				 temp = new LLNode<Integer>(passin,null);
	  				 adding = 0;
	  				 passin = 0;
	  			 }
				}
				else {
					passin = thisTrav.data + adding;
					if(passin >= 10) {
						adding = passin/10;
						passin = passin % 10;
					}
					else {
						adding = 0;
					}
					temp = new LLNode<Integer>(passin,null);
				}
				if(newHead == null) {
					newHead = temp;
					newHeadTrav = newHead;
				}
				else {
					newHeadTrav.link = temp;
					newHeadTrav = newHeadTrav.link;
				}
				if(thatTrav == null) {
					thisTrav = thisTrav.link;
	  			}
				else {
					thisTrav = thisTrav.link;
					thatTrav = thatTrav.link;
				}
				if(i == this.size()-1 && adding > 0) {
	  				newHeadTrav.link = new LLNode<Integer>(1,newHeadTrav.link);
	  				break;
	  		}
	  	}
	  	}
	  	if(this.size == that.size) {
	  		for(int i = 0; i < this.size; i++) {
	  			passin = thisTrav.data + thatTrav.data + adding;
	  			if(passin >= 10) {
	  				adding = passin/10;
	  				passin = passin % 10;
	  				temp = new LLNode<Integer>(passin,null); 
	  			}
	  			else {
	  				temp = new LLNode<Integer>(passin,null);
	  				adding = 0;
	  			}
	  			
	  			if(newHead == null) {
	  				newHead = temp;
	  				newHeadTrav = newHead;
	  			}
	  			else {
	  				newHeadTrav.link = temp;
	  				newHeadTrav = newHeadTrav.link;
	  			}
	  			
	  			if(i == this.size()-1 && adding > 0) {
	  				newHeadTrav.link = new LLNode<Integer>(1,newHeadTrav.link);
	  				break;
	  			}
	  			else {
	  				thisTrav = thisTrav.link;
		  			thatTrav = thatTrav.link;
	  			}
	  		}
	  	}
	  	if(this.size < that.size) {
	  		for(int i = 0; i < that.size;i++) {
				if(thisTrav != null && thatTrav != null) {
	  			 passin = thisTrav.data + thatTrav.data + adding;
	  			 if(passin >= 10) {
	  				 adding = passin/10;
	  				 passin = passin % 10;
	  				 temp = new LLNode<Integer>(passin,null); 
	  			 }
	  			 else {
	  				 temp = new LLNode<Integer>(passin,null);
	  				 adding = 0;
	  				 passin = 0;
	  			 }
				}
				else {
					passin = thatTrav.data + adding;
					if(passin >= 10) {
						adding = passin/10;
						passin = passin % 10;
					}
					else {
						adding = 0;
					}
					temp = new LLNode<Integer>(passin,null);
				}
				if(newHead == null) {
					newHead = temp;
					newHeadTrav = newHead;
				}
				else {
					newHeadTrav.link = temp;
					newHeadTrav = newHeadTrav.link;
				}
				if(thisTrav == null) {
					thatTrav = thatTrav.link;
				}
				else {
					thisTrav = thisTrav.link;
					thatTrav = thatTrav.link;
				}
				if(i == that.size()-1 && adding > 0) {
	  				newHeadTrav.link = new LLNode<Integer>(adding,newHeadTrav.link);
	  				break;
	  			}
	  		}
	  	}
	  	LargeInteger toReturn = new LargeInteger(toString(newHead));
        return toReturn;
  }

  /** Returns a new LargeInteger object representing the result of multiplying
   *  this large integer with a non-negative integer x. You can assume x is either
   *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
   *  above to accomplish the 'multiply'.
   */
  public LargeInteger multiply(int x) {
      LargeInteger toReturn = new LargeInteger();
      LargeInteger temp = new LargeInteger();
	  if(x == 0) {
		 toReturn = new LargeInteger("0");
	  }
	  else if(x == 1) {
		  toReturn = new LargeInteger(this.toString());
	  }
	  else {
		  toReturn = new LargeInteger(this.toString());
		  temp = new LargeInteger(this.toString());
		  for(int i = 1; i < x; i++) {
			  toReturn = toReturn.add(temp);
		  } 
	  }
        return toReturn;
  }

  /** Recursive method that converts the list referenced by curr_node back to
   *  a string representing the integer. Think about what's the base case and
   *  what it should return. Then think about what it should return in non-base case.
   *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
   */
  private String toString(LLNode<Integer> node) {
        if(node == null) {
        	return "";
        }
        return toString(node.link)+node.data;
  }

  /** Convert this list back to a string representing the large integer.
   *  This is a public method that jump-starts the call to the recursive method above.
   */
  public String toString() {
    return toString(head);
  }

  /** Recursive method to compute factorial. */
  public static LargeInteger factorial(int n) {
    if (n == 0) {
      return new LargeInteger("1");
    }
    return factorial(n - 1).multiply(n);
  }

  /** Recursive method to compute power. */
  public static LargeInteger pow(int x, int y) {
    if (y == 0) {
      return new LargeInteger("1");
    }
    return pow(x, y - 1).multiply(x);
  }
}
