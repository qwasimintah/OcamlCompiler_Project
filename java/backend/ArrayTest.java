package backend;

import java.util.*;
import java.io.*;
import backend.variables.*;
import backend.functions.*;
import backend.instructions.*;
import backend.registers.*;
import backend.exceptions.*;
import backend.booleans.*;


public class ArrayTest{


	   public static void main(String[] args){

	   		StringBuilder text = new StringBuilder();
	   		ArmGenerator arm = new ArmGenerator();

	   		arm.generate_create_array();
	   		arm.generate_put_element();
	   		arm.generate_get_array();
	   		text = arm.textSection.text;
	   		System.out.println(arm.dataSection.data);
	   		System.out.println(text);




	   } 


}