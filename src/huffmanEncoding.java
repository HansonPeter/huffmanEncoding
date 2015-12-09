import java.util.ArrayList;
import java.util.Scanner;

public class huffmanEncoding {

	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<freqNode> nodeArray = new ArrayList<freqNode>();
		ArrayList<freqNode> codeArray = new ArrayList<freqNode>();
		String input = new String();
		
		input = scanner.nextLine();
		System.out.println(input);
		String[] tokens = input.split(" ");
		
		for(int i = 0; i < tokens.length; i += 2){
			System.out.println(i);
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
			nodeArray.remove(length - 1);
			nodeArray.remove(length - 2);
			for(int i = length - 2; i > 0; i--){
				if(nodeArray.get(i).freq > placeholder.freq){
					nodeArray.add(i + 1, placeholder);
					break;
				}
			}
			
			length = nodeArray.size();
		
		}
		encoder("",nodeArray.get(0));
		//TODO: put encodings here.
		char command;
		boolean continueReading = true;
		while(continueReading){
			command = (char) scanner.nextInt();
			if(command == 'e'){
				System.out.println(encode(codeArray, scanner.nextLine()));
			}else if(command == 'd'){
				System.out.println(decode(nodeArray, scanner.nextLine()));
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
	
	public static String encode(ArrayList<freqNode> codeArray, String plainText){
		String returnString = "";
			char[] charArray = plainText.toCharArray();
			
			for(int i = 0; i < charArray.length; i++){
				for(int j = 0; j < codeArray.size(); j++){
					if(codeArray.get(j).letter == charArray[i]){
						returnString.concat(codeArray.get(j).code);
						j = codeArray.size();
					}
				}
			}
		return returnString;
	}
	
	public static String decode(ArrayList<freqNode> nodeArray, String codedText){
		String returnString = "";
		freqNode topNode = nodeArray.get(0);
		freqNode currentNode = topNode;
		char[] charArray = codedText.toCharArray();
		for(int i = 0; i < charArray.length; i++){
			if(currentNode.left != null && currentNode.right != null){
				if(charArray[i] == '0'){
					currentNode = currentNode.left;
				}else if(charArray[i] == '1'){
					currentNode = currentNode.right;
				}
			}else{
				returnString.concat(Character.toString(currentNode.letter));
			}
		}
		
		return returnString;
	}

}



