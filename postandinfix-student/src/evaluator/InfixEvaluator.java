package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {

  private LinkedStack<String> operators = new LinkedStack<String>();
  private LinkedStack<Integer> operands  = new LinkedStack<Integer>();

  /** return stack object (for testing purpose). */
  public LinkedStack<String> getOperatorStack() { 
    return operators; 
  }
  
  public LinkedStack<Integer> getOperandStack() { 
    return operands;
  }


  /** This method performs one step of evaluation of a infix expression.
   *  The input is a token. Follow the infix evaluation algorithm
   *  to implement this method. If the expression is invalid, throw an
   *  exception with the corresponding exception message.
   */
  public void evaluate_step(String token) throws Exception {
    if (isOperand(token)) {
    	operands.push(Integer.parseInt(token));
    } else {
    	switch(token) {
    	case "(":
    		operators.push(token);
    		break;
    	case ")":
    		for(int i = 0; i < 34 ;i++) {
    			if(operators.top() == null) {
    				throw new Exception ("missing (");
    			}
    			if(operators.top().equals("(")) {
    				break;
    			}
    			else {
    				process_operator(operators.pop());
    			}
    		}
    			operators.pop();
    			break;
    		default:
    			while(!operators.isEmpty() && 
    					!(precedence(token)>precedence(operators.top()))) {
    				process_operator(operators.pop());
    			}
    			if(operators.isEmpty() || 
    					precedence(token)>precedence(operators.top())) {
    			operators.push(token);
    			}
    			else {
    				operators.push(token);
    			}
    			}
    	return;
    		}
    }
  
  /** This method evaluates an infix expression (defined by expr)
   *  and returns the evaluation result. It throws an Exception object
   *  if the infix expression is invalid.
   */
  private void process_operator(String operator) throws Exception {
	if(operator.equals("!")) {
  		if(operands.top() == null) {
  			throw new Exception("too few operands");
  		}
  		int num1 = operands.pop();
  		operands.push(-1 * num1);
  	}
  	else {
  	if(operands.top() == null) {
  		throw new Exception("too few operands");
  	}
  	int num2 = operands.pop();
  	if(operands.top() == null) {
  		throw new Exception("too few operands");
  	}
  	int num1 = operands.pop();
  	switch(operator) {
  	case "+":
  		operands.push(num1+num2);
  		break;
  	case "-":
  		operands.push(num1-num2);
  		break;
  	case "*":
  		operands.push(num1*num2);
  		break;
  	case "/":
  		if(num2 == 0) {
  			throw new Exception("division by zero");
  		}
  		operands.push(num1/num2);
  		break;
  	default:
  		throw new Exception("invalid operator");
  	} 
  }
}
  public Integer evaluate(String expr) throws Exception {

    for (String token : ArithParser.parse(expr)) {
      evaluate_step(token);
    }
    while(operators.isEmpty() == false){
    	process_operator(operators.pop());
    }
    if (operands.size() > 1) {
      throw new Exception("too many operands");
    } else if (operands.size() < 1) {
      throw new Exception("too few operands");
    }

    return operands.pop();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(new InfixEvaluator().evaluate("1 + 2 + 5)"));
  }

  }
