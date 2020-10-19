package Java_basic;

public class Topic_04_Parameter {
	
	static String address = "TP DA NANG";

	public static void main(String[] args) {
		System.out.println();
		//Nguyen Thi Thanh Thuy: đối số
		//gọi hàm
		showName("Nguyen Thi Thanh Thuy");
		getAddress();
		
	}
	//showName: hàm/function/method
	//String name: tham số của hàm (parameter)
	public static void showName(String name) {
		System.out.println("xin chao:" + name);
	}
		
		public static String getAddress() {
			return address;
			
		}
		
		// http://prntscr.com/uqaysn
		
	}


