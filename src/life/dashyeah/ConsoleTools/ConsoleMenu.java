package life.dashyeah.ConsoleTools;
/**
 * a set console UI tools.
 * @author Dash Wong
 * 
 * */
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class ConsoleMenu {
	/**
	 * item output position
	 * @see #POSTION_LEFT
	 * @see #POSTION_RIGHT
	 * */
	public static final int POSTION_CENTER = 0;
	/**
	 * item output position
	 * @see #POSTION_CENTER
	 * @see #POSTION_RIGHT
	 * */
	public static final int POSTION_LEFT = 1;
	/**
	 * item output position
	 * @see #POSTION_LEFT
	 * @see #POSTION_CENTER
	 * */
	public static final int POSTION_RIGHT = 2;
	
	/**
	 * print menu at console
	 * @param items all choices to show
	 * @param title title of this menu
	 * @return the user's choice
	 */
	public static int showMenu(String[] items, String title){
		int maxlen = title.length();
		for(String s:items)
			if(s.length()+2 > maxlen) maxlen = s.length();
		int ll = maxlen+8;
		printSep(ll);         // "*********"
		title = regainStr(title);
		printItem(title, ll, ConsoleMenu.POSTION_CENTER); // "* title *"
		printSep(ll);         // "*********"
		int no = 1;
		for(String s:items){
			s = regainStr(s);
			s = String.valueOf(no)+"."+s;
			printItem(s, ll, ConsoleMenu.POSTION_LEFT); // "* item  *"
			no ++;
		}
		printSep(ll);
		
		int choice;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.print("Enter your choice> ");
			try{
				choice = scan.nextInt();
				if(choice<1 || choice>items.length)
					throw new InputMismatchException();
			}catch(InputMismatchException e){
				System.out.println("Wrong choice, retry.");
				continue;
			}
			break;
		}
		
		return choice;
	}
	
	private static void printItem(String s, int len, int postion){
		int i;
		System.out.print("*");
		for(i=0; i<(len-s.length())/2-1; i++) System.out.print(" ");    // "* s *"
		System.out.print(s);
		for(i+=s.length(); i<len-1; i++) System.out.print(" ");
		System.out.println("*"); 
	}
	private static void printSep(int len){
		for(int i=0; i<len+1; i++)
			System.out.print("*");
		System.out.println("");      // "********"
	}
	private static String regainStr(String s){
		if(s.length()%2 > 0)
			return s+" ";
		else
			return s;
	}
	
	
	public static void main(String[] args){
		while(true){
			String[] items = {"Hello", "Hi", "Me", "Oh..."};
			System.out.println(ConsoleMenu.showMenu(items, "Test Menu"));
		}
	}
	
}