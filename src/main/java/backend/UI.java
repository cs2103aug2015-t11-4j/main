package main.java.backend;
//@@Author: Jiahuan 

 
import java.util.Scanner; 

 
public class UI { 
 	public static void welcome(){ 
 		System.out.println("Welcome to ALT4, your personlized agenda manager"); 
 	} 
 	 
 	public static String promoteCommand(){ 
 		Scanner sc = new Scanner(System.in); 
 		System.out.print("Command: "); 
 		String command = sc.nextLine(); 
 		sc.close(); 
 		return command; 
 	} 
 
 
 	public static void feedback(String action, int code) { 
 		switch (action){ 
 		case "Add": 
			if (code==0){ 
				System.out.println("Added!"); 
			}else{ 
				System.out.println("Failed!"); 
 			} 
 		} 
 		 
 	} 
 } 
