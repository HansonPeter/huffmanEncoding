
public class freqNode {
	public char letter;
	public int freq;
	public String code;
	
	public freqNode left;
	public freqNode right;
	
	//constructor
	public freqNode(char a, int b) {
		letter = a;
		freq = b;
	}
	
	//2nd constructor
	public freqNode(int a, freqNode l, freqNode r){
		letter = ' ';
		freq = a;
		
		left = l;
		right = r;
	}
	
}
