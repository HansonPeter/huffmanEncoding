import java.util.ArrayList;
import java.util.Scanner;

public class huffmanEncoding {

	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<freqNode> nodeArray = new ArrayList<freqNode>();
		ArrayList<freqNode> codeArray = new ArrayList<freqNode>();
		String input = new String();
		
		input = scanner.nextLine();
		//System.out.println(input);
		String[] tokens = input.split(" ");
		
		for(int i = 0; i < tokens.length; i += 2){
			//System.out.println(i);
			nodeArray.add(new freqNode(tokens[i].charAt(0), Integer.parseInt(tokens[i+1])));
		}
		
		int length = nodeArray.size();
		freqNode placeholder;
		freqNode left;
		freqNode right;
		
		while(nodeArray.get(0).freq != 100){
			left = nodeArray.get(length - 1);
			right = nodeArray.get(length - 2);
			placeholder = new freqNode(left.freq + right.freq, left, right);
			
			//System.out.println(nodeArray);
			
			nodeArray.remove(length - 1);
			nodeArray.remove(length - 2);
			int i = 0;
			for(i = nodeArray.size()-1; i >= 0; i--){
				if(nodeArray.get(i).freq > placeholder.freq){
					//System.out.println(i);
					//i = i;
					break;
				}
			}
			nodeArray.add(i+1, placeholder);
			length = nodeArray.size();
			//System.out.println(nodeArray.get(0).freq);
		}
		
		encoder("",nodeArray.get(0));
		
		char command;
		
		boolean continueReading = true;
		
		while(continueReading){
			command = scanner.next().charAt(0);
			if(command == 'e'){
				System.out.println("Encode: " + encode(nodeArray.get(0), scanner.next()));
			}else if(command == 'd'){
				System.out.println("Decode: " + decode(nodeArray.get(0), scanner.next()));
			}else if(command == 'x'){
				continueReading = false;
			}
		}
		
		scanner.close();
	}
	
	public static void encoder(String code, freqNode node){
		if(node.left != null && node.right != null){
			encoder(code.concat("0"), node.left);
			encoder(code.concat("1"), node.right);
		}
		node.code = code;
	}
	
	public static void mapIt(freqNode node, ArrayList<freqNode> list){
		
		if(node.letter != ' '){
			list.add(node);
		}
		if(node.left != null && node.right != null){
			mapIt(node.left, list);
			mapIt(node.right, list);
		}
	}
	
	public static String encode(freqNode node, String plainText){
		String returnString = "";
		char[] charArray = plainText.toCharArray();
		String encodeString = "";
		//System.out.println(charArray.length);
		char c;
		for(int i = 0; i < charArray.length; i++){
			c = charArray[i];
			//System.out.println(c);
			encodeString = getEncode(node,c);
			//System.out.println("endcodeded " + encodeString);
			returnString += encodeString;
			//System.out.println("returnString: " + returnString);
		}
		
		//System.out.println(returnString);	
		return returnString;
	}
	
	public static String getEncode(freqNode node, char target){
		String encoding = "";
		String leftResult;
		String rightResult;
			if(node.letter==target){
				encoding = node.code;
				//System.out.println("current node = " + node.letter);
			}else {
				if(node.left != null){
					//System.out.println("leftR");
					leftResult = getEncode(node.left, target);
					if(leftResult.compareTo("") != 0){
						encoding = leftResult;
					}
				}
				if(node.right != null){
					//System.out.println("rightR");
					rightResult = getEncode(node.right, target);
					if(rightResult.compareTo("") != 0){
						encoding = rightResult;
					}
				}	
			}
		//System.out.println("code " + encoding);
		return encoding;
	}
	
	public static String decode(freqNode node, String codedText){
		//String returnString = "";
		freqNode topNode = node;
		char[] charArray = codedText.toCharArray();
		
		//System.out.println("return " + returnString);
		return getDecode(topNode, charArray);
	}
	
	public static String getDecode(freqNode node, char[] charArray){
		String decoding = "";
		
		freqNode currentNode = node;
		
		for(int i = 0; i < charArray.length; i++){
			//System.out.println("code: " + charArray[i]);
			if(charArray[i] == '0' && currentNode.left != null){
				//leftResult
				//System.out.println("left");
				currentNode = currentNode.left;
			} else if(charArray[i] == '1' && currentNode.right != null){
				//rightResult
				//System.out.println("right");
				currentNode = currentNode.right;
			}
			if(currentNode.left == null && currentNode.right == null){
				decoding += currentNode.letter;
				currentNode = node;
			}
		}
		//System.out.println("decoding " + decoding);
		return decoding;
	}
	
}



