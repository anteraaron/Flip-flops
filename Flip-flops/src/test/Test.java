package test;

public class Test {

	public static void main(String[] args) {
		
		String equation = "Axy + Bx'y'";
		String A = "0";
		String B = "1";
		String x = "1";
		String y = "0";
		
		//Evaluator contains all the computation needed in this mp
		Evaluator evaluator = new Evaluator();
		
		//.evaluate() method returns a String. It evaluates the given string equation.
		System.out.println("The evaluated answer from given equation: " + evaluator.evaluate(equation, A, B, x, y));
		
		//JK FlipFlop
		String prevState = "0";
		String J = "1";
		String K = "1";
		System.out.println("JK Flipflop: " + evaluator.JK(J, K, prevState));
		
		//RS Flipflop
		prevState = "0";
		String R = "1";
		String S = "1";
		System.out.println("RS Flipflop: " + evaluator.RS(R, S, prevState));
		
		//D flipflop
		prevState = "0";
		String D = "1";
		System.out.println("D Flipflop: " + evaluator.D(D));
		
		prevState = "0";
		String T = "1";
		System.out.println("T Flipflop: " + evaluator.T(T, prevState));
		
		//Sample kung gagawa ka na ng table
		String JA_equation = "Bx + By";
		String KA_equation = "B'xy'";
		A = "0"; //ito yung prevstate since in terms of A sya
		B = "1";
		x = "0";
		y = "1";
		System.out.println("Next State: " + evaluator.JK(evaluator.evaluate(JA_equation, A, B, x, y), evaluator.evaluate(KA_equation, A, B, x, y), A));
		
	}

}
