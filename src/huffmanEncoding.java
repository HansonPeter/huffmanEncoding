import java.util.ArrayList;
import java.util.Scanner;

public class huffmanEncoding {

	public static void main(){
		
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<freqNode> charArray = new ArrayList<freqNode>();
		
		boolean continueInput = true;
			
		while(continueInput){
			if(scanner.hasNext()){
				charArray.add(new freqNode(scanner.next().charAt(0), scanner.nextInt()));
			}
			else {
				continueInput = false;
			}
		}
		
		
		
		scanner.close();
	}
}