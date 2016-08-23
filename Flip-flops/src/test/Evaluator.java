package test;
/**
 *
 * @author Anter Aaron M. Custodio && Carl Zachery Viernes
 *
 *A class that contains a String equation parser for boolean algebra and A flipflop next-state evaluator.
 *
 */
public class Evaluator {

	private String answer;
	
	/**
	 * Default Constructor
	 */
	public Evaluator(){
		this.answer = null;
	}
	/**
	 * Evaluates the Given string equation.
	 * @param equation The string equation given by the user
	 * @param A initial state of A
	 * @param B initial state of B
	 * @param x The first inputted variable by the user
	 * @param y The 2nd inputted variable by the user
	 * @return returns 1 or 0
	 */
	public String evaluate(String equation, String A, String B, String x, String y){
		int begin = 0;
		String substr = null;
	
		equation = equation.replaceAll(" ", "");
		equation = '(' + equation + ')';
	
		while(equation.contains("(")||equation.contains(")")){
			
			for(int i=0;i<equation.length();i++){
				if(equation.charAt(i)=='(')
					begin = i;
				
				if(equation.charAt(i)==')'){
					substr = equation.substring(begin, i+1);
					substr = substr.replaceAll("A", A);
					substr = substr.replaceAll("B", B);
					substr = substr.replaceAll("x", x);
					substr = substr.replaceAll("y", y);
					
					//Complement
					for(int j=0;j<substr.length();j++){
						if(substr.charAt(j)=='\''){
							if(substr.charAt(j-1)=='1')
								substr = substr.substring(0,j-1) + "0" + substr.substring(j+1, substr.length());
							else if(substr.charAt(j-1)=='0')
								substr = substr.substring(0,j-1) + "1" + substr.substring(j+1, substr.length());
							j=0;
						}
						if(!substr.contains("'"))
							break;
					}
					
					//AND
					for(int j=0;j<substr.length();j++){
						if((substr.charAt(j)=='1'||substr.charAt(j)=='0')&&(substr.charAt(j+1)=='1'||substr.charAt(j+1)=='0')){
							substr = substr.substring(0, j+1) + "*" + substr.substring(j+1, substr.length());
							j=0;
						}
					}
			
					//compute
					for(int j=0; j<substr.length(); j++){
						if(substr.charAt(j)=='*'){
							if(substr.charAt(j-1)=='1'&&substr.charAt(j+1)=='1')
								substr = substr.substring(0, j-1) + "1" + substr.substring(j+2, substr.length());
							else
								substr = substr.substring(0, j-1) + "0" + substr.substring(j+2, substr.length());
							j=0;
						}
						
						if(!substr.contains("*"))
							break;
					}
				
					for(int j=0; j<substr.length(); j++){
						if(substr.charAt(j)=='@'){
							if((substr.charAt(j-1)=='1'||substr.charAt(j+1)=='1')&&!(substr.charAt(j-1)=='1'&&substr.charAt(j+1)=='1'))
								substr = substr.substring(0, j-1) + "1" + substr.substring(j+2, substr.length());
							else
								substr = substr.substring(0, j-1) + "0" + substr.substring(j+2, substr.length());
							j=0;
						}
							
						if(!substr.contains("@"))
							break;
					}
					
					for(int j=0; j<substr.length(); j++){
						if(substr.charAt(j)=='+'){
							if(substr.charAt(j-1)=='1'||substr.charAt(j+1)=='1')
								substr = substr.substring(0, j-1) + "1" + substr.substring(j+2, substr.length());
							else
								substr = substr.substring(0, j-1) + "0" + substr.substring(j+2, substr.length());
							j=0;
						}
							
						if(!substr.contains("+"))
							break;
					}
					
					substr = substr.substring(1,substr.length()-1);
					equation = equation.substring(0, begin) + substr + equation.substring(i+1, equation.length());
				}

			}
		}
		
		setAnswer(equation);
		return getAnswer();
	}
	/**
	 * JK Flipflop
	 * @param J The J variable in JK Flip-flop
	 * @param K The K variable in JK Flip-flop
	 * @param prevState The previous state of the variable to be evaluated
	 * @return returns 1 or 0
	 */
	public String JK(String J, String K, String prevState){
		if(J.equals("0")&&K.equals("0")){
			setAnswer(prevState);
		}else if(J.equals("0")&&K.equals("1")){
			setAnswer("0");
		}else if(J.equals("1")&&K.equals("0")){
			setAnswer("1");
		}else if(J.equals("1")&&K.equals("1")){
			if(prevState.equals("1"))
				setAnswer("0");
			else
				setAnswer("1");
		}
		return getAnswer();
	}
	/**
	 * RS Flipflop
	 * @param R The R variable in RS Flip-flops
	 * @param S The S variable in RS Flip-flops
	 * @param prevState The previous state of the variable to be evaluated
	 * @return returns 1 or 0
	 */
	public String RS(String R, String S, String prevState){
		if(R.equals("0")&&S.equals("0")){
			setAnswer(prevState);
		}else if(R.equals("0")&&S.equals("1")){
			setAnswer("0");
		}else if(R.equals("1")&&S.equals("0")){
			setAnswer("1");
		}else if(R.equals("1")&&S.equals("1")){
			setAnswer("Indeterminate");
		}
		return getAnswer();
	}
	/**
	 * D flipflop
	 * @param D the D variable in D flip-flop
	 * @return returns 1 or 0
	 */
	public String D(String D){
		if(D.equals("0"))
			setAnswer("0");
		else
			setAnswer("1");
		
		return getAnswer();
	}
	/**
	 * T Flipflop
	 * @param T the T variable in T flipflop
	 * @param prevState The previous state of the variable to be evaluated
	 * @return returns 1 or 0
	 */
	public String T(String T, String prevState){
		if(T.equals("0"))
			setAnswer(prevState);
		else{
			if(prevState.equals("0"))
				setAnswer("1");
			else
				setAnswer("0");
		}
		return getAnswer();
	}
	/**
	 * Setter for the final answer
	 * @param equation The final answer in the evaluated equation
	 */
	private void setAnswer(String equation){
		this.answer = equation;
	}
	/**
	 * Getter for the answer
	 * @return Returns 1 or 0 . The final answer
	 */
	private String getAnswer(){
		return this.answer;
	}
}
