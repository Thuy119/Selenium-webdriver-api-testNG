package Java_basic;

public class Topic_05_Split {

	public static void main(String[] args) {
		String oldUrl = "http://the-internet.herokuapp.com/basic_auth";
		
		String urlValue[] = oldUrl.split("//");
		//http:
		// the-internet.herokuapp.com/basic_auth
		System.out.println(urlValue[0]);
		System.out.println(urlValue[1]);
		
		String likes = "1,938 likes";
		
		String likenumber = likes.split(" ")[0].replace(",", "");
		System.out.println(likenumber);
		
		int likeNumbers = Integer.parseInt(likenumber);
		System.err.println(likenumber);
		
		
	}
	

}
