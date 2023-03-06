package com.fdmgroup.tdd.Calculator;
import java.util.ArrayList;

public class Calculator implements ICalculator{
	
	private  ArrayList<String> expression = new ArrayList<String>();	
	private boolean hasBracket = true;
	
	public double evaluate(String input) {
		transferExpression(input,0);
		calculateBrackets(0);
		hasBracket = false; //after bracket calculation so the operation get in a loop to keep calculate to the end.
		String temp = calculation(0,expression.size());
		double result = Double.parseDouble(temp);	
		return result;		
	}
	
	public String calculation(int offset, int limit) {
		exponents(offset, limit);
		division(offset, limit);
		multiply(offset, limit);		
		additionAndSubtract(offset, limit);	
			 if(expression.size() > 2 && !hasBracket)  // repeat operation till it has one element left.
				return calculation(offset, limit);
			 else if (expression.size() == 2 && expression.get(0).equals("-")) // combine the negative symbol and the result to generate final result correctly. 
				 return expression.get(0)+ expression.get(1);	
		return expression.get(0);
		}
	
	public void operation (int offset, String operation) { //for reuse of repeated lines in different operation methods.
		double x = toDouble(expression.get(offset-1));
		double y = toDouble(expression.get(offset+1));				
		double temp = returnOperation(operation, x,y);
		expression.set(offset-1,Double.toString(temp));
		expression.remove(offset);
		expression.remove(offset);					
	}
	
	public double returnOperation(String operation, double x, double y) {
		if(operation.equals("+")) 
			return x+y;
		else if(operation.equals("-")) 
			return x-y;
		else if(operation.equals("*")) 			
			return x*y;
		else  if(operation.equals("/"))
			return x/y;		
		else
			return calculateExponents(x,y, 0);
	}

	public ArrayList<String> additionAndSubtract(int offset, int limit) {
		if (offset < expression.size()&& !expression.get(offset).equals(")")) {			
			if(!isNumber(expression.get(offset))) {
				if(expression.get(offset).equals("+")) {
					operation(offset, "+");
					return expression;
					}
				else if (offset != 0 && expression.get(offset).equals("-")) {
					operation(offset, "-");
					return expression;
					}
				}
			return additionAndSubtract(offset + 1, limit);
			}
		return expression;
	}
	
	public void multiply (int offset, int limit) {
		if(offset < limit && offset <expression.size()) {
			if(!isNumber(expression.get(offset))) {
				if(expression.get(offset).equals("*")) {
				operation(offset, "*");
				}				
			}
			multiply( offset+1, limit);
		}	
			}
		
	public void division(int offset, int limit) { // This allows calculate division in order.	
		if(offset < limit && offset <expression.size()) {		
			divisionLoop (offset, limit);
			division(offset+1, limit);
		}	
	}
	
	public int divisionLoop(int offset, int limit) { 	//after operate first division it returns offset to finish the recursion.
		if(offset < limit && offset <expression.size()) {
				if(expression.get(offset).equals("/")) {
				operation(offset, "/");
				return offset;
				}
				divisionLoop(offset+1, limit);
				}
	return offset;
	}
	
	public void exponents(int offset, int limit) {
		if (offset < limit&& offset <expression.size()) {
			if(!isNumber(expression.get(offset))) {
				if(expression.get(offset).equals("^")) {
					operation(offset, "^");
				}
			}
			exponents( offset + 1, limit);					
		}
	}
	
	public double calculateExponents(double x, double y, int offset) {		
		if(y == 0) 
			return 1;	
		else if (y > 0 && offset < y) //positive exponents		
			return x*calculateExponents(x,y, offset +1);				
		else if(y < 0 && offset < -y)  //negative exponents
			return 1/x*calculateExponents(x,y, offset +1);					
		return 1;
	}	
	
	public boolean isNumber(String str) { //prevent null point exception error
		char ch0 = str.charAt(0);
		if (str.length()>1)
			return true;
		else if(Character.isDigit(ch0)) {
			return true;
		}
		return false;
	}
	
	public double toDouble(String str) {
		return Double.parseDouble(str);		
	}

	public  void transferExpression(String printString, int offset) {	//change the expression to arrayList as desired.		
		String numbers = "";
		boolean numberValue = true;
		boolean isBracket = true;
		
		loopForTransfer(printString, offset, numbers, numberValue, isBracket);
	}

	public  void loopForTransfer(String printString, int offset, String numbers, boolean numberValue, boolean isBracket){
		
		if (offset < printString.length()) {
			char ch = printString.charAt(offset);			
            String value = printString.substring(offset, offset+1);
       
	     if(ch != ' ') { //remove whitespace
			if(Character.isDigit(ch) || ch == '.') {
				numberValue = true; 
				numbers = numbers + value;				
			}			
			else if(numbers == "" ) {	
				if( ch == '-') {	
					if(offset == 0 || isBracket) {
						if(offset == 0 && printString.charAt(offset+2)=='^' ) // for negative base of exponents when it is first element.
							expression.add(value);
						else {
						numberValue = true;						
						numbers = numbers + value;
						}
					}
					else {	//to keep negative number as negative when the bracket closed
						expression.add(value);
						isBracket =true;
					}
				}						
				else 	//all other exponents kept separately except -.				
					expression.add(value);
			}					
			else if (!numberValue) { //combine numbers together.
				expression.add(numbers); 
				expression.add(value); 
				numbers = ""; 	
            }					
			if(ch == ')') 
					isBracket = false;
			
	        numberValue = false;	            
	     }
        if(offset == printString.length()-1){
            if (Character.isDigit(ch) || ch == ' ')// last condition is to prevent error when the last char is whitespace
            expression.add(numbers); 	                                                  
        }        
        loopForTransfer(printString, offset + 1, numbers, numberValue, isBracket);	
		}
	}

	public void calculateBrackets( int offset) { //calculate inside of brackets.
		int limit = findEndBracket(offset); //find end of bracket to calculate bracket first.
		int start = findStartBracket(limit); // take limit as the offset to find the start of inner bracket.

		if (start < expression.size()&& limit !=0 ) {			
			if(expression.get(start).equals("(")) {
			 calculation(start, limit);				
				 if(hasBracketFinished(start+1,limit)) {
				 expression.remove(start); //remove bracket
				 expression.remove(start+1);	
				 }
			}
			calculateBrackets(offset+1);
		}
	}

	public boolean hasBracketFinished(int offset, int limit) { //check if the brackets has been finished.
		if(offset < limit+1 && !expression.get(offset).equals( ")")) {
			if(!isNumber(expression.get(offset)))		
				return false; 			
			return hasBracketFinished(offset+1, limit);
		}
		return true;
	}
	
	public int findStartBracket(int offset) {	// find start of bracket	
		if(offset>0) {
			if(expression.get(offset).equals("(")) 
				return offset;
		}
		else if (offset == 0) {
			return offset;
		}
			return findStartBracket(offset-1);	// finding start bracket goes backward to find inner brackets first.
	}	
	
	public int findEndBracket(int offset) {	// find end of bracket			
		if (offset < expression.size()) {				
			if(expression.get(offset).equals(")"))					
				return offset;						
		}
		else if (offset == expression.size()) {
			return 0;
		}
		return findEndBracket(offset+1);
	}					
}