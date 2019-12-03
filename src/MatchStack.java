
import java.util.*;

/*Chris Chromak
This program uses a stack to check if strings containing
a mathematical equation is written correctly or not
*/
public class MatchStack {

	public static boolean isMatch(String expressionLine) {
		LinkedList<Character> stack = new LinkedList<Character>();
		
		for (int n = 0; n < expressionLine.length(); n++) {
			char c = expressionLine.charAt(n);
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else if (c == ')') {
				if(stack.isEmpty()) return false;
				if(stack.peek() == '(') {
					stack.pop();
				}else {
					return false;
				}
			}else if (c == '}') {
				if(stack.isEmpty()) return false;
				if(stack.peek() == '{') {
					stack.pop();
				}else {
					return false;
				}
			}else if (c == ']') {
				if(stack.isEmpty()) return false;
				if(stack.peek() == '[') {
					stack.pop();
				}else {
					return false;
				}
			}
	}
		return stack.isEmpty();

}
	
	public static void main(String[] args){
		 String[] expression = new String[]{"{5*(x+2)+(y-1);}", "32*(20+(x[i]*3)-1",
		"((){([][])})", "({}((0))", "{([]})", "{}())", "{"};
		 for (String st: expression)
		 System.out.println(st + " is " + isMatch(st));
		}

}