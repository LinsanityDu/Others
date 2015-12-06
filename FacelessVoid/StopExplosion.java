/*
 * 大概的意思就是你需要一些card (or cardboard?) 阻止爆炸，可是你不知道要多少才能够stop the explosion. 
 * 你怎么找这个值。然后我就问说有没有upper limit， 他说这个你要自己找，我问可不可以重复实验，他说可以，
 * 可是要尽量少。所以我说的先找upper limit然后binary search。然后让我写code，
 * n
 * [1, 2, 4, ... n] logn
 * [n - 1, 2n] logn
 * logn
 */
public class GuessNumber {
	
	public boolean isStop(int n){
		if(n >= 10000){
			return true;
		}
		return false;
	}
	
	public int guessNumber(){
		int end = 1;
		while(!isStop(end)){
			end = end * 2;
			
		}
		if(end == 1) return 1;
		int start = end / 2;
		//binary search in [lower, upper] to find the first num to make it stop
		//[0, 0, 0, 0, 0, 1, 1, 1, 1, 1]
		while(start + 1 < end){
			int mid = start + (end - start) / 2;
			if(isStop(mid)){
				end = mid;
			}else{
				start = mid;
			}
		}
		if(isStop(start)) return start;
		if(isStop(end)) return end;
		return -1;
	}
	
	public static void main(String[] args){
		GuessNumber ins = new GuessNumber();
		System.out.println(ins.guessNumber());
	}

}