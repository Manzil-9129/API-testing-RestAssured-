import com.beust.jcommander.converters.DoubleConverter;

public class Util {


	
    public static String Token_Trello="935b646b0ada7603e72e0e6c15cd9ebfd0949a12bc1139bd9154d99030d45299";
    public static String Key_Trello="c8a00b2db94f5478c611b7a3e5e892a0";	
    public static String URL="https://api.trello.com/";
    public static String board_name_generated;
    public static String github_patch;
  
   
  //Genrate random board name 
    public static String github() {
  	   double d1=Math.random()*(100);
  	   int num =  (int) (d1);
  		String d=String.valueOf(num);
  		
  		 String name="Captain-"+d;
  		 github_patch=name;
  		 return name;
  	}
    
    
    //Genrate random board name 
  public static String Board() {
	   double d1=Math.random()*(100);
	   int num =  (int) (d1);
		String d=String.valueOf(num);
		
		 String name="Captain-India"+d;
		 board_name_generated=name;
		 return name;
	}
   
//Genrate random List name	
public static String List() {
  double d1=Math.random()*(100);
  int num =  (int) (d1);
	String d=String.valueOf(num);
	
	 String name="Captain-India list"+d;
	 return name;
}

//Generate random Card name
public static String Card() {
double d1=Math.random()*(100);
int num =  (int) (d1);
String d=String.valueOf(num);

 String name="Captain-India card"+d;
 return name;
}
	    
   
}
