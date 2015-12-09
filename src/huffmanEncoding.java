import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class huffmanEncoding {

	public static void main(){
		
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<freqNode> nodeArray = new ArrayList<freqNode>();
		HashMap<char, String> codeMap = new HashMap<char, String>();
		String input = new String();
		
		input = scanner.next();
		String[] tokens = input.split(" ");
		
		for(int i = 0; i < tokens.length; i += 2){
			nodeArray.add(new freqNode(tokens[i].charAt(0), Integer.parseInt(tokens[i+1])));
		}
		
		int length = nodeArray.size();
		freqNode placeholder;
		freqNode left;
		freqNode right;
		
		while(nodeArray.get(0).freq != 100){
			left = nodeArray.get(length);
			right = nodeArray.get(length -1);
			placeholder = new freqNode(left.freq + right.freq, left, right);
			nodeArray.remove(length);
			nodeArray.remove(length-1);
			for(int i = length; i > 0; i--){
				if(nodeArray.get(i).freq > placeholder.freq){
					nodeArray.add(i + 1, placeholder);
					break;
				}
			}
			
			length = nodeArray.size();
		
		}
		
		encoder("",nodeArray.get(0));
		
		
		
		scanner.close();
	}
	
	public static void encoder(String code, freqNode node){
		encoder(code.concat("0"), node.left);
		encoder(code.concat("1"), node.right);
		
		node.code = code;
	}
	
	public static void mapIt(freqNode node, )
	
}



