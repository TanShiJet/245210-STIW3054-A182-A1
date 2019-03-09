package assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

class Assignment1 implements Runnable{

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Assignment1());
		
		t1.start();
	}

	@Override
	public void run() {
		double AP = 0;  //ActorPoint
		double UC = 0; //Use Case
		double UUCP = 0; //Unadjusted Use Case Point
		double TF = 0; //Technical factor
		double TCF = 0; // Technical Complexity Factor
		double SzUC = 0; // Size of the software (use case)
		double EF = 0; // Experience Factors 
		double UCP = 0; // Use case point 
		double MH = 0; // Man-hour (calculate using ucp)
		double ER = 0; //Effort Rate
		int loopcount = 1;
		
		
		Scanner option = new Scanner (System.in);
		
		System.out.println("***>>> software effort estimation calculator <<<***"+"\n" );
		System.out.println(" Enter any integer number to start the calculation. ");
		System.out.print(">>");
		
		boolean a = true;
		do {
			
			try {
				int start = option.nextInt();
				a = true ;
			}
			 catch (InputMismatchException exception)
		    {
		        System.out.println("Integers only, please try again.");
		        a = false;
		        option.nextLine();
		        System.out.print(">>");
		    }
		}while ( a == false );
		
		Questions WAFC = new Questions(); //create object for  Weighting Actors for Complexity 
		Questions WUCFC = new Questions(); // create object for Weighting Use Cases for Complexity
		Questions WTF = new Questions(); //create object for Weighting Technical Factors
		Questions WEF = new Questions(); // create object forWeighting Experience Factors
		
		
		// 
		do {
			switch (loopcount) {
			
				case 1 :
					loopcount = ActionPoint (WAFC,loopcount);
					break;
				case 2:
					loopcount =	UseCase (WUCFC,loopcount);
					break;
				case 3:
					loopcount =	TechnicalFactor (WTF,loopcount);
					break;
				case 4:
					loopcount =	ExperienceFactors (WEF,loopcount);
					break;
					
			}
		
		}while (loopcount < 5 );
		
		ER = ERCal(WEF);
		
		
		
		//Calculate software effort 
		AP = WAFC.getTotal();
		UC = WUCFC.getTotal();
		TF = WTF.getTotal();
		EF = WEF.getTotal(); 
		UUCP = AP + UC;
		TCF = (0.01 * TF) + 0.6 ;
		SzUC = UUCP * TCF;
		EF = (-0.03 *EF) + 1.4 ;
		UCP = SzUC * EF ;
		MH = ER * UCP; 
		
		//display result
		System.out.println("Result**************************************************************" );
		System.out.printf("%35s : %.2f \n","Unadjusted Use Case Point", UUCP);
		System.out.printf("%35s : %.2f \n","Technical Complexity Factor", TCF);
		System.out.printf("%35s : %.2f \n","Size of the software", SzUC);
		System.out.printf("%35s : %.2f \n","Experience Factors ", EF);
		System.out.printf("%35s : %.2f \n","Use case point ",UCP);
		if (ER ==0)
			System.out.println("\n* this project is at significant risk of failure with this team.");
		else
			System.out.printf("%35s : %.2f \n","Man-hour ", MH );
		
		
	}
	
		int ActionPoint (Questions WAFC , int loopcount) {
			
			int x = 0;int y = 0; int z = 0 ;
			boolean done = false;
			int ap = 0;
			  
			Scanner option = new Scanner (System.in);
			
			do {
					
					System.out.println("\n"+" Weighting Actors for Complexity ");
					System.out.println("******************************************************************************"+"\n" );
					System.out.println("-----------------------------------------------------------------------------");
					System.out.printf("|%4s|%15s |%40s |%10s |\n","No.","Actor type  ","Description               ","Qnty   ");
					System.out.println("-----------------------------------------------------------------------------");
					System.out.printf("|%-4s|%-15s |%-40s |%-10d |\n","1.","Simple","Defind API",WAFC.getQnty_1());
					System.out.printf("|%-4s|%-15s |%-40s |%-10d |\n","2.","Average","Interactive or Protocal driven interface",WAFC.getQnty_2());
					System.out.printf("|%-4s|%-15s |%-40s |%-10d |\n","3.","Complex","Graphical User Interface ",WAFC.getQnty_3());
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("\n"+"******************************************************************************"+"\n" );
					System.out.println("1. Please enter numberof row you want to change the quantity"+"\n"+"2. insert \"d\" after you are done \n");
					System.out.print(">>");
					
					String ans = option.nextLine();
					
					switch (ans) {
					
					
 						case "1":
							System.out.println("Please enter the quantity.");
							System.out.print(">>");
							WAFC.setQnty_1(option.nextInt());
							option.nextLine();
							x = WAFC.getQnty_1() * 1;
							break;
						
						case "2":
							System.out.println("Please enter the quantity.");
							System.out.print(">>");
							WAFC.setQnty_2(option.nextInt());
							option.nextLine();
							y = WAFC.getQnty_2() * 2;
							break;
							
						case "3":
							System.out.println("Please enter the quantity.");
							System.out.print(">>");
							WAFC.setQnty_3(option.nextInt());
							option.nextLine();
							z = WAFC.getQnty_3() * 3;
							break;
							
						case "d":
							done = true;
							loopcount++;  
							break;
						
							
						default :
							System.out.print("please try again"+"\n");
							break;
							
					}
								
			} while (done == false);
			
			WAFC.setTotal(x+y+z);
			
			return  loopcount ;
			
		}
		
		
		
		int UseCase (Questions WUCFC , int loopcount) {
			

			int x = 0;
			int y = 0;
			int z = 0;
			boolean done = false;
			int uc = 0;
			
			Scanner option = new Scanner (System.in);
			
			do {
					
					System.out.println("\n"+" Weighting Use Cases for Complexity  ");
					System.out.println("******************************************************************************"+"\n" );
					System.out.println("-----------------------------------------------------------------------------");
					System.out.printf("|%4s|%15s |%40s |%10s |\n","No.","Actor type  ","Description               ","Qnty   ");
					System.out.println("-----------------------------------------------------------------------------");
					System.out.printf("|%-4s|%-15s |%-40s |%-10d |\n","1.","Simple","3 or fewer transactions",WUCFC.getQnty_1());
					System.out.printf("|%-4s|%-15s |%-40s |%-10d |\n","2.","Average","4 to 7 transactions",WUCFC.getQnty_2());
					System.out.printf("|%-4s|%-15s |%-40s |%-10d |\n","3.","Complex","Greater than 7 transactions ",WUCFC.getQnty_3());
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("\n"+"******************************************************************************"+"\n" );
					System.out.println("1. Please enter numberof row you want to change the quantity"+"\n"+"2. insert \"d\" after you are done or insert \"b\" to back previous page.\n");
					System.out.print(">>");
					
					String ans = option.nextLine();					
					
					switch (ans){
						case "1":
							System.out.println("Please enter the quantity.");
							System.out.print(">>");
							WUCFC.setQnty_1(option.nextInt());
							option.nextLine();	
							x = WUCFC.getQnty_1() * 5;
							break;
						
						case "2":
							System.out.println("Please enter the quantity.");
							System.out.print(">>");
							WUCFC.setQnty_2(option.nextInt());
							option.nextLine();	
							y = WUCFC.getQnty_2() * 10;
							break;
							
						case "3":
							System.out.println("Please enter the quantity.");
							System.out.print(">>");
							WUCFC.setQnty_3(option.nextInt());
							option.nextLine();	
							z = WUCFC.getQnty_3() * 15;
							break;
							
						case "d":
							done = true;
							loopcount++;
							break;
							
						case "b":
							done = true;
							loopcount--;
							break;
							
						default :
							System.out.print("please try again"+"\n");
							break;
							
					}				
					
			} while (done == false);
			WUCFC.setTotal(x + y + z);
			return loopcount;
	
		}
		
		
		int  TechnicalFactor(Questions WTF , int loopcount) {
			
			double  a1 = 0;
			double  a2 = 0;
			double  a3 = 0;
			double  a4 = 0;
			double  a5 = 0;
			double  a6 = 0;
			double  a7 = 0;
			double  a8 = 0;
			double  a9 = 0;
			double  a10 = 0;
			double  a11 = 0;
			double  a12 = 0;
			double  a13 = 0;
			
			boolean done = false;
			double  tf = 0;
			
			Scanner option = new Scanner (System.in);
			
			do {
			
					System.out.println("\n"+" Weighting Technical Factors  ");
					System.out.println("***************************************************************************"+"\n" );
					System.out.println("---------------------------------------------------------------------------");
					System.out.printf("|%4s|%50s |%10s |\n","No.","Factor Description                "," Project Rating");
					System.out.println("----------------------------------------------------------------------------");
					System.out.printf("|%-4s|%-50s |%-15d |\n","1.","Must have a distributed solution",WTF.getQnty_1());
					System.out.printf("|%-4s|%-50s |%-15d |\n","2.","Must respond tospecific performance objectives",WTF.getQnty_2());
					System.out.printf("|%-4s|%-50s |%-15d |\n","3.","Must meet end-user efficieny desires",WTF.getQnty_3());
					System.out.printf("|%-4s|%-50s |%-15d |\n","4.","Complex internal processing",WTF.getQnty_4());
					System.out.printf("|%-4s|%-50s |%-15d |\n","5.","Code must be reuseable",WTF.getQnty_5());
					System.out.printf("|%-4s|%-50s |%-15d |\n","6.","Must be easy to install",WTF.getQnty_6());
					System.out.printf("|%-4s|%-50s |%-15d |\n","7.","Musr be easy to use",WTF.getQnty_7());
					System.out.printf("|%-4s|%-50s |%-15d |\n","8.","Must be portable",WTF.getQnty_8());
					System.out.printf("|%-4s|%-50s |%-15d |\n","9.","Must be easy to change ",WTF.getQnty_9());
					System.out.printf("|%-4s|%-50s |%-15d |\n","10.","Must allow concurrent users",WTF.getQnty_10());
					System.out.printf("|%-4s|%-50s |%-15d |\n","11.","Includes special security features",WTF.getQnty_11());
					System.out.printf("|%-4s|%-50s |%-15d |\n","12.","Must provide direct access for #rd parties",WTF.getQnty_12());
					System.out.printf("|%-4s|%-50s |%-15d |\n","13.","Requires special user training facilities",WTF.getQnty_13());
					System.out.println("---------------------------------------------------------------------------");
					
					System.out.println("\n"+"***************************************************************************"+"\n" );
					System.out.println("1. Please enter numberof row you want to change the Project Rating"+"\n"+"2. rate each factor from 0 to 5. A rating of 0 means the factor is \n   irrelevant for this project, 5 means it is essential \n"+"3. insert \"d\" after you are done or insert \"b\" to back previous page. ");
					System.out.print("\n>>");
					
					String ans = option.nextLine();
					

					switch (ans){
						case "1":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_1 (option.nextInt());
							option.nextLine();	
							a1 = WTF.getQnty_1() * 2;
							break;
						
						case "2":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_2 (option.nextInt());
							option.nextLine();	
							a2 = WTF.getQnty_2()* 1;
							break;
							
						case "3":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_3 (option.nextInt());;
							option.nextLine();	
							a3 = WTF.getQnty_3() * 1;
							break;
							
						case "4":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_4 (option.nextInt());
							option.nextLine();	
							a4 = WTF.getQnty_4() * 1;
							break;
							
						case "5":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_5 (option.nextInt());
							option.nextLine();	
							a5 = WTF.getQnty_5() * 1;
							break;
							
						case "6":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_6 (option.nextInt());
							option.nextLine();	
							a6 = WTF.getQnty_6() * 0.5;
							break;
							
						case "7":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_7 (option.nextInt());
							option.nextLine();	
							a7 = WTF.getQnty_7() * 0.5;
							break;
							
						case "8":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_8 (option.nextInt());
							option.nextLine();	
							a8 = WTF.getQnty_8()* 2;
							break;
							
						case "9":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_9 (option.nextInt());
							option.nextLine();	
							a9 = WTF.getQnty_9() * 1;
							break;
							
						case "10":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_10 (option.nextInt());
							option.nextLine();	
							a10 = WTF.getQnty_10() * 1;
							break;
						
						case "11":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_11 (option.nextInt());
							option.nextLine();	
							a11 = WTF.getQnty_11() * 1;
							break;
							
						case "12":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_12 (option.nextInt());
							option.nextLine();	
							a12 = WTF.getQnty_12()* 1;
							break;
							
						case "13":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WTF.setQnty_13 (option.nextInt());
							option.nextLine();	
							a13 = WTF.getQnty_13() * 8;
							break;
							
						case "d":
							done = true;
							loopcount++;
							break;
						
						case "b":
							done = true;
							loopcount--;
							break;
								
						default :
							System.out.print("please try again"+"\n");
							break;
							
					}				
					
			
			}while (done == false);
			
			WTF.setTotal(a1 + a2 + a3 + a4 + a5 + a5 + a6 + a7 + a8 + a9 + a10 + a11 + a12 + a13);
			
			return loopcount;
			
		}
		
		
		int ExperienceFactors (Questions WEF , int loopcount) {
			
			double  a1 = 0;
			double  a2 = 0;
			double  a3 = 0;
			double  a4 = 0;
			double  a5 = 0;
			double  a6 = 0;
			double  a7 = 0;
			double  a8 = 0;
			
			boolean done = false;
			double  ef = 0;
			
			Scanner option = new Scanner (System.in);
			
			do {
			
					System.out.println("\n"+" Weighting Experience Factors  ");
					System.out.println("***************************************************************************"+"\n" );
					System.out.println("---------------------------------------------------------------------------");
					System.out.printf("|%4s|%50s |%10s |\n","No.","Factor Description                "," Project Rating");
					System.out.println("----------------------------------------------------------------------------");
					System.out.printf("|%-4s|%-50s |%-15d |\n","1.","Familiar with FPT software process",WEF.getQnty_1());
					System.out.printf("|%-4s|%-50s |%-15d |\n","2.","Application experiance",WEF.getQnty_2());
					System.out.printf("|%-4s|%-50s |%-15d |\n","3.","Paradigm expirience (OO)",WEF.getQnty_3());
					System.out.printf("|%-4s|%-50s |%-15d |\n","4.","Lead analyst capability",WEF.getQnty_4());
					System.out.printf("|%-4s|%-50s |%-15d |\n","5.","Motivation",WEF.getQnty_5());
					System.out.printf("|%-4s|%-50s |%-15d |\n","6.","Stable Requirements",WEF.getQnty_6());
					System.out.printf("|%-4s|%-50s |%-15d |\n","7.","Part-time workers",WEF.getQnty_7());
					System.out.printf("|%-4s|%-50s |%-15d |\n","8.","Difficulty of Programing language",WEF.getQnty_8());
					System.out.println("--------------------------------------------------------------------------");
					System.out.println("\n"+"***************************************************************************"+"\n" );
					System.out.println("1. Please enter numberof row you want to change the Project Rating"+"\n"+"2. rate each factor from 0 to 5. A rating of 0 means the factor is \n   irrelevant for this project, 5 means it is essential \n"+"3. insert \"d\" after you are done or insert \"b\" to back previous page. ");
					System.out.print("\n>>");
					
					String ans = option.nextLine();
					
					switch (ans){
						case "1":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WEF.setQnty_1 (option.nextInt());
							option.nextLine();
							a1 = WEF.getQnty_1() * 1;
							WEF.setA1(a1);
							break;
						
						case "2":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WEF.setQnty_2 (option.nextInt());
							option.nextLine();
							a2 = WEF.getQnty_2() * 0.5;
							WEF.setA2(a2);
							break;
							
						case "3":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WEF.setQnty_3 (option.nextInt());
							option.nextLine();
							a3 = WEF.getQnty_3() * 1;
							WEF.setA3(a3);
							break;
							
						case "4":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WEF.setQnty_4 (option.nextInt());
							option.nextLine();
							a4 = WEF.getQnty_4() * 0.5;
							WEF.setA4(a4);
							break;
							
						case "5":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WEF.setQnty_5 (option.nextInt());
							option.nextLine();
							a5 = WEF.getQnty_5() * 0;
							WEF.setA5(a5);
							break;
							
						case "6":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WEF.setQnty_6 (option.nextInt());
							option.nextLine();
							a6 = WEF.getQnty_6()* 2;
							WEF.setA6(a6);
							break;
							
						case "7":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WEF.setQnty_7 (option.nextInt());
							option.nextLine();
							a7 = WEF.getQnty_7() * (-1);
							WEF.setA7(a7);
							break;
							
						case "8":
							System.out.println("Please enter Project Rating.");
							System.out.print(">>");
							WEF.setQnty_8 (option.nextInt());
							option.nextLine();
							a8 = WEF.getQnty_8() * (-1);
							WEF.setA8(a8);
							break;
							
						case "d":
							done = true;
							loopcount++;
							break;
							
						case "b":
							done = true;
							loopcount--;
							break;
								
						default :
							System.out.print("please try again"+"\n");
							break;
						
					}				
				
		
			}while (done == false);
			
			WEF.setTotal(a1 + a2 + a3 + a4 + a5 + a5 + a6 + a7 + a8);
			return loopcount;
			
		}
		
		
		
		
		double ERCal (Questions WEF ) {
			
			double a = 0;
			
			if (WEF.getQnty_1()<3) {
				a++;
			}
			if (WEF.getQnty_2()<3) {
				a++;
			}
			if (WEF.getQnty_3()<3) {
				a++;
			}
			if (WEF.getQnty_4()<3) {
				a++;
			}
			if (WEF.getQnty_5()<3) {
				a ++;
			}
			if (WEF.getQnty_6()<3) {
				a ++;
			}
			if (WEF.getQnty_7()>3) {
				a ++;
			}
			if (WEF.getQnty_8()>3) {
				a ++;
			}
			
			if (a < 3)
				a = 20;
			
		    else if (a < 6)
				a = 28;
		    
		    else 
		    	a = 0;
			
			return a ;
		}

	

}
