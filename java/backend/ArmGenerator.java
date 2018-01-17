
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJAN DENNIS MINTAH
 */
package backend;

import java.util.*;
import java.io.*;
import backend.variables.*;
import backend.functions.*;
import backend.instructions.*;
import backend.registers.*;
import backend.exceptions.*;
import backend.booleans.*;

public class ArmGenerator {

public DataSection dataSection;
public TextSection textSection;
private int available_reg=9;
private int available_reg_param=2;
private int label_counter=0;


private Integer labelNumber = 0;

public String getNewLabel() {
        labelNumber++;
        return "label" + labelNumber.toString();
}

public ArmGenerator(){
        //initialise the .data=new ArrayList<Object>();
        dataSection=new DataSection();
        dataSection.data.append("\t.data\n");
        dataSection.data.append("\t.global heap_ptr\n");
        dataSection.data.append("\theap: .skip 4096\n");
        dataSection.data.append("\theap_ptr: .word heap\n");



        //initialise the .text
        textSection= new TextSection();
        textSection.text.append("\t.text\n");
        textSection.text.append("\t.global _start\n");
        textSection.text.append("_start:\n");
        textSection.text.append("\tBL _main\n");

}







public void generate_code(List<Function>  functions){
        //loop through all the available functions
        for(Function fun : functions) {
                List<Variable> arguments = fun.getArguments();
                List<Instruction> intr = fun.getInstructions();
                HashSet<Variable> locals = fun.getVariables();
                //process all intructions of functions
                int size= locals.size();


                String fname = fun.getName();

                if(fname.equals("main")) {
                        generate_function_label(fname);

                        main_prologue();
                        reserve_space(size);
                }
                else{

                        generate_function_label(fname);
                        function_prologue(size);
                }


                for (Instruction inst : intr) {

                        if(inst instanceof InstructionADD) {
                                generate_addition((InstructionADD) inst);
                        }
                        else if (inst instanceof InstructionSUB) {
                                generate_sub((InstructionSUB) inst);
                        }
                        else if (inst instanceof InstructionMULT) {
                                generate_mult((InstructionMULT) inst);
                        }

                        else if (inst instanceof InstructionASSIGN) {
                                generate_assign((InstructionASSIGN) inst);

                        }

                        else if (inst instanceof InstructionCALL) {
                                generate_function_call((InstructionCALL) inst);

                        }
                        else if(inst instanceof InstructionIF) {

                                String ret=generate_if((InstructionIF) inst);
                                generate_branch_label(ret);
                        }
                        else if(inst instanceof InstructionNOTHING) {
                                // DO NOTHING
                        }
                        else{
                                System.out.println("Instruction Not Supported\n");
                        }
                }


                //popping local variables;
                pop_locals(size);

                if(fname.equals("main")) {

                        //function_epilogue();
                        main_epilogue();
                        output_terminal();
                }
                else{
                        function_epilogue();
                }



        }

}




public void generate_branch(Function fun, String return_label){
        //loop through all the available functions

        List<Variable> arguments = fun.getArguments();
        List<Instruction> intr = fun.getInstructions();
        HashSet<Variable> locals = fun.getVariables();
        //process all intructions of functions
        int size= locals.size();
        int instr_count = intr.size();
        int count=0;

        String fname = fun.getName();
        generate_branch_label(fname);



        for (Instruction inst : intr) {
                count++;
                if(inst instanceof InstructionADD) {
                        generate_addition((InstructionADD) inst);
                }
                else if (inst instanceof InstructionSUB) {
                        generate_sub((InstructionSUB) inst);
                }
                else if (inst instanceof InstructionMULT) {
                        generate_mult((InstructionMULT) inst);
                }

                else if (inst instanceof InstructionASSIGN) {
                        generate_assign((InstructionASSIGN) inst);

                }
                else if (inst instanceof InstructionCALL) {
                        generate_function_call((InstructionCALL) inst);

                }
                else if(inst instanceof InstructionIF) {

                        String ret = generate_if((InstructionIF) inst);
                        generate_branch_label(ret);
                }
                else if(inst instanceof InstructionNOTHING) {

                        if(count == instr_count) {
                                generate_nothing((InstructionNOTHING) inst);
                        }

                }
                else{
                        System.out.println("Instruction Not Supported\n");
                }
        }

        textSection.text.append("\tb ").append(return_label).append("\n");



}



public void pushing_local_params(HashSet<Variable> locals,int size){

        int num_of_args = size;
        // System.out.println(num_of_args);

        if (num_of_args>1) {
                int off=1;
                for (int i =available_reg; i<=num_of_args; i++) {

                        for (Variable l : locals) {
                                if(l.getOffset()!=null && l.getOffset() == off*4 ) {

                                        textSection.text.append("\tSUB sp, #4\n");
                                        assign("r0",((VInteger)l).getValue());
                                        textSection.text.append("\tSTR r0, [sp]\n");
                                        off++;
                                }
                        }

                }

        }
}


public void reserve_space(int size){

        if(size > available_reg) {
                textSection.text.append("\tSUB sp, #").append((size-available_reg)*4).append("\n");
        }
}

public void pop_locals(int size){

        if(size > available_reg) {
                textSection.text.append("\tADD sp, #").append((size-available_reg)*4).append("\n");
        }
}


public void reserve_space_param(int size){

        if(size > available_reg) {
                textSection.text.append("\tSUB sp, #").append((size-available_reg_param)*4).append("\n");
        }
}

public void pop_params(int size){

        if(size > available_reg) {
                textSection.text.append("\tADD sp, #").append((size-available_reg_param)*4).append("\n");
        }
}



public void generate_nothing(InstructionNOTHING instr) {
        if (instr.x instanceof VInteger) {
                assign("r0", ((VInteger)instr.x).getValue());
        }
        else if (instr.x instanceof Variable) {

                Variable v = (Variable)(instr.x);

                if(v.getRegister() != null) {
                        assign("r0", ((Variable)instr.x).getRegister().getName());
                }
                else if(v.getParametersRegister()!=null) {
                        assign("r0", ((Variable)instr.x).getParametersRegister().getName());
                }

        }
}

public void generate_addition(InstructionADD instr){
        Object op1= instr.operands.get(0);
        Object op2= instr.operands.get(1);
        String rd="r0";
        String operand1="";
        String operand2="";
        // System.out.println("begin");
        // System.out.println(((Variable)op1).getName());
        // System.out.println(((Variable)op1).getRegister());
        // System.out.println(((Variable)op1).getParametersRegister());
        //
        // System.out.println(((Variable)op2).getName());
        // System.out.println(((Variable)op2).getRegister());
        // System.out.println(((Variable)op2).getParametersRegister());
        // System.out.println("end");









        if(op1 instanceof Variable) {
                //VARIABLE WITH REGISTER
                if(((Variable)op1).getRegister()!=null &&   ((Variable)op1).getParametersRegister()==null && ((Variable)op1).getParametersOffset()==null) {
                        operand1=((Variable)op1).getRegister().getName();
                }
                //VARIABLE WITH OFFSET
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getOffset()!=null && ((Variable)op1).getParametersRegister()==null) {

                        operand1="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";

                }

                // if its a parameter with a register
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getOffset()==null && ((Variable)op1).getParametersRegister()!=null) {

                        operand1=((Variable)op1).getParametersRegister().getName();
                }


                // if its a paramter with an offset
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getParametersOffset()!=null && ((Variable)op1).getOffset()==null ) {

                        operand1="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";
                }
                //SHOULD NOT FIRE
                else{

                        operand1="[fp, #" + ((Variable)op1).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";
                }
        }

        if(op2 instanceof Variable) {

                // case where variable has a register and not use as a paramter (VARIABLE WITH REGISTER)
                if(((Variable)op2).getRegister()!=null &&   ((Variable)op2).getParametersRegister()==null && ((Variable)op2).getParametersOffset()==null) {
                        operand2=((Variable)op2).getRegister().getName();
                }
                // case where variable has no register, no local vaiable offset and no parameter register(PARAMETER WITH OFFSET)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getOffset()==null && ((Variable)op2).getParametersRegister()==null) {

                        operand2="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";

                }

                //case where vaiable has no register , no local variable offset and no parameter offset(PARAMATER WITH REGISTER)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getOffset()==null && ((Variable)op2).getParametersRegister()!=null) {

                        operand2=((Variable)op2).getParametersRegister().getName();
                }


                // case where variable has offset(VARIABLE WITH OFFSET)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getParametersOffset()==null && ((Variable)op2).getOffset()!=null ) {

                        operand2="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }
                //case SHOULD NOT FIRE
                else{

                        operand2="[fp, #" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }
        }



        if(op1 instanceof Integer && op2 instanceof Variable) {

                arith_operation("ADD",rd,(int)op1, operand2);
        }

        else if(op1 instanceof Variable && op2 instanceof Variable) {
                arith_operation("ADD",rd,operand1, operand2);
        }
        else if(op1 instanceof Integer && op2 instanceof Integer) {

                arith_operation("ADD",rd,(int)op1, (int)(op2));
        }
        else if(op1 instanceof Variable && op2 instanceof Integer) {

                arith_operation("ADD",rd,operand1, ((int)op2));

        }


}


public void generate_mult(InstructionMULT instr){
        Object op1= instr.operands.get(0);
        Object op2= instr.operands.get(1);
        String rd="r0";

        String operand1="";
        String operand2="";

        if(op1 instanceof Variable) {
                //VARIABLE WITH REGISTER
                if(((Variable)op1).getRegister()!=null &&   ((Variable)op1).getParametersRegister()==null && ((Variable)op1).getParametersOffset()==null) {
                        operand1=((Variable)op1).getRegister().getName();
                }
                //VARIABLE WITH OFFSET
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getOffset()!=null && ((Variable)op1).getParametersRegister()==null) {

                        operand1="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";

                }

                // if its a parameter with a register
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getOffset()==null && ((Variable)op1).getParametersRegister()!=null) {

                        operand1=((Variable)op1).getParametersRegister().getName();
                }


                // if its a paramter with an offset
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getParametersOffset()!=null && ((Variable)op1).getOffset()==null ) {

                        operand1="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";
                }
                //SHOULD NOT FIRE
                else{

                        operand1="[fp, #" + ((Variable)op1).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";
                }
        }

        if(op2 instanceof Variable) {

                // case where variable has a register and not use as a paramter (VARIABLE WITH REGISTER)
                if(((Variable)op2).getRegister()!=null &&   ((Variable)op2).getParametersRegister()==null && ((Variable)op2).getParametersOffset()==null) {
                        operand2=((Variable)op2).getRegister().getName();
                }
                // case where variable has no register, no local vaiable offset and no parameter register(PARAMETER WITH OFFSET)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getOffset()==null && ((Variable)op2).getParametersRegister()==null) {

                        operand2="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";

                }

                //case where vaiable has no register , no local variable offset and no parameter offset(PARAMATER WITH REGISTER)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getOffset()==null && ((Variable)op2).getParametersRegister()!=null) {

                        operand2=((Variable)op2).getParametersRegister().getName();
                }


                // case where variable has offset(VARIABLE WITH OFFSET)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getParametersOffset()==null && ((Variable)op2).getOffset()!=null ) {

                        operand2="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }
                //case SHOULD NOT FIRE
                else{

                        operand2="[fp, #" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }
        }




        if(op1 instanceof Integer && op2 instanceof Variable) {

                arith_operation("MUL",rd,(int)op1, operand2);
        }
        else if(op1 instanceof Variable && op2 instanceof Variable) {
                arith_operation("MUL",rd,operand1, operand2);
        }
        else if(op1 instanceof Integer && op2 instanceof Integer) {

                arith_operation("MUL",rd,(int)op1, (int)(op2));
        }
        else if(op1 instanceof Variable && op2 instanceof Integer) {

                arith_operation("MUL",rd,operand1, ((int)op2));

        }



}





public void generate_sub(InstructionSUB instr){

        Object op1= instr.operands.get(0);
        Object op2= instr.operands.get(1);

        String rd="r0";

        String operand1="";
        String operand2="";

        if(op1 instanceof Variable) {
                //VARIABLE WITH REGISTER
                if(((Variable)op1).getRegister()!=null &&   ((Variable)op1).getParametersRegister()==null && ((Variable)op1).getParametersOffset()==null) {
                        operand1=((Variable)op1).getRegister().getName();
                }
                //VARIABLE WITH OFFSET
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getOffset()!=null && ((Variable)op1).getParametersRegister()==null) {

                        operand1="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";

                }

                // if its a parameter with a register
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getOffset()==null && ((Variable)op1).getParametersRegister()!=null) {

                        operand1=((Variable)op1).getParametersRegister().getName();
                }


                // if its a paramter with an offset
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getParametersOffset()==null && ((Variable)op1).getOffset()==null ) {

                        operand1="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";
                }
                //SHOULD NOT FIRE
                else{

                        operand1="[fp, #" + ((Variable)op1).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";
                }
        }

        if(op2 instanceof Variable) {

                // case where variable has a register and not use as a paramter (VARIABLE WITH REGISTER)
                if(((Variable)op2).getRegister()!=null &&   ((Variable)op2).getParametersRegister()==null && ((Variable)op2).getParametersOffset()==null) {
                        operand2=((Variable)op2).getRegister().getName();
                }
                // case where variable has no register, no local vaiable offset and no parameter register(PARAMETER WITH OFFSET)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getOffset()==null && ((Variable)op2).getParametersRegister()==null) {

                        operand2="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";

                }

                //case where vaiable has no register , no local variable offset and no parameter offset(PARAMATER WITH REGISTER)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getOffset()==null && ((Variable)op2).getParametersRegister()!=null) {

                        operand2=((Variable)op2).getParametersRegister().getName();
                }


                // case where variable has offset(VARIABLE WITH OFFSET)
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getParametersOffset()==null && ((Variable)op2).getOffset()!=null ) {

                        operand2="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }
                //case SHOULD NOT FIRE
                else{
                        //System.out.println("should not fire");
                        operand2="[fp, #" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }
        }




        if(op1 instanceof Integer && op2 instanceof Variable) {

                arith_operation("SUB",rd,(int)op1, operand2);
        }
        else if(op1 instanceof Variable && op2 instanceof Variable) {
                arith_operation("SUB",rd,operand1, operand2);
        }
        else if(op1 instanceof Integer && op2 instanceof Integer) {

                arith_operation("SUB",rd,(int)op1, (int)(op2));
        }
        else if(op1 instanceof Variable && op2 instanceof Integer) {

                arith_operation("SUB",rd,operand1, ((int)op2));

        }



}


public void generate_create_array(){

        int size = 5;
        int initial=0;

        textSection.text.append("\tMOV r0, #0\n");
        textSection.text.append("create_array: \n");
        textSection.text.append("\tCMP r0, #").append(size).append("\n");
        textSection.text.append("\tBEQ end_create\n");
        textSection.text.append("\tLDR r1, =heap_ptr\n");
        textSection.text.append("\tLSL r2, r0, #2\n");
        textSection.text.append("\tADD r2, r1, r2\n");
        textSection.text.append("\tMOV r3, #").append(initial).append("\n");
        textSection.text.append("\tSTR r3, [r2]").append("\n");
        textSection.text.append("\tADD r0, r0, #1").append("\n");
        textSection.text.append("\tB create_array\n");

        textSection.text.append("end_create: \n");
        textSection.text.append("\tMOV r0, #0\n\n");

}


public void generate_get_array(){

        int index = 2;

        textSection.text.append("get_array_element: \n");
        textSection.text.append("\tMOV r0, #").append(index).append("\n");
        textSection.text.append("\tLDR r1, =heap_ptr\n");
        textSection.text.append("\tLSL r2, r0, #2\n");
        textSection.text.append("\tADD r2, r1, r2\n");
        textSection.text.append("\tLDR r1, [r2]\n\n");

}


public void generate_put_element(){

        int index = 2;
        int value =7;

        textSection.text.append("put_array_element: \n");
        textSection.text.append("\tMOV r0, #").append(index).append("\n");
        textSection.text.append("\tLDR r1, =heap_ptr\n");
        textSection.text.append("\tLSL r2, r0, #2\n");
        textSection.text.append("\tADD r2, r1, r2\n");
        textSection.text.append("\tMOV r3, #").append(value).append("\n");
        textSection.text.append("\tSTR r3, [r2]").append("\n");

}


public void generate_create_tuples(){


        int index = 1;
        int value = 2;


        textSection.text.append("\tMOV r0, #0\n");
        textSection.text.append("\t");
        textSection.text.append("\tLDR r1, =heap_ptr\n");
        textSection.text.append("\tLSL r2, r0, #2\n");
        textSection.text.append("\tADD r2, r1, r2\n");
        textSection.text.append("\tMOV r3, #").append(value).append("\n");
        textSection.text.append("\tSTR r3, [r2]").append("\n");
        textSection.text.append("\tADD r0, r0, #1").append("\n");



}

public void generate_get_tuples(){



}


public void  generate_assign(InstructionASSIGN instr){


        // System.out.println("SHOULD FIRE");


        Object op1= instr.operands.get(0);
        Object op2= instr.operands.get(1);

        // System.out.println(op2);

        String operand1="";
        String offset1="";
        String operand2="";


        if(op1 instanceof Variable) {
                if(((Variable)op1).getRegister() != null) {
                        operand1=((Variable)op1).getRegister().getName();
                }
                else{
                        // System.out.println("var name");
                        // System.out.println(((Variable)op1).getName());
                        // System.out.println(((Variable)op1).getParametersOffset());
                        // System.out.println(((Variable)op1).getOffset());
                        offset1="[fp , #-" + ((Variable)op1).getOffset().toString()+"]";
                        //textSection.text.append("\tSTR r0 , "). append(offset1).append("\n");
                        operand1="r0";
                }
        }



        if(op2 instanceof Variable) {
                //op2=(Variable)op2;
                // if its a local variable with a register
                if(((Variable)op2).getRegister() != null && ((Variable)op2).getParametersRegister() == null && ((Variable)op2).getParametersOffset() == null) {
                        operand2=((Variable)op2).getRegister().getName();
                }
                // it its a local variable with an offset
                else if(((Variable)op2).getRegister() == null && ((Variable)op2).getOffset() != null && ((Variable)op2).getParametersRegister() == null) {

                        operand2="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";

                }

                // if its a parameter with a register
                else if (((Variable)op2).getOffset() == null &&
                         ((Variable)op2).getParametersRegister() != null) {

                        operand2=((Variable)op2).getParametersRegister().getName();
                }


                // if its a parameter with an offset
                else if (((Variable)op2).getParametersOffset() != null &&
                         ((Variable)op2).getOffset() == null) {

                        operand2="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }
                // else{
                //         operand2="[fp ,#" + ((Variable)op2).getOffset().toString()+"]";
                //         textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                //         operand2="r1";

                // }
        }




        if((op1 instanceof VInteger || op1 instanceof Variable)&& op2 instanceof Variable) {
                //System.out.println("fire");
                assign(operand1, operand2);
                if(offset1!="") {
                        textSection.text.append("\tSTR r0 , ").append(offset1).append("\n");
                }
        }

        else if((op1 instanceof VInteger || op1 instanceof Variable) && op2 instanceof Integer) {
                //System.out.println("fire2");
                assign(operand1, ((int)op2));
                if(offset1!="") {
                        textSection.text.append("\tSTR r0 , ").append(offset1).append("\n");
                }

        }

        else if(op1 instanceof Variable && op2 instanceof Instruction) {

                String reg= operand1;


                if(op2 instanceof InstructionADD) {

                        //generate_addition((InstructionADD) op2);


                        assign(reg, "r0" );

                }
                else if(op2 instanceof InstructionSUB) {

                        //generate_sub((InstructionSUB) op2);
                        assign(reg, "r0");

                }

                else if(op2 instanceof InstructionMULT) {

                        //generate_mult((InstructionMULT) op2);
                        assign(reg, "r0");

                }

                else if(op2 instanceof InstructionCALL) {
                        generate_function_call((InstructionCALL) op2);
                        //System.out.println("Should fire");
                        // System.out.println(((InstructionCALL)op2).getFname());

                        if(offset1!="") {
                                System.out.println("Offset part ");
                                textSection.text.append("\tMOV  r1 , ").append(((InstructionCALL)op2).getReturn()).append("\n");
                                textSection.text.append("\tSTR r1 , ").append(offset1).append("\n");
                        }
                        else{

                                assign(reg, ((InstructionCALL)op2).getReturn());
                        }
                }

                else if (op2 instanceof InstructionASSIGN) {

                        System.out.println("This fires");
                        InstructionASSIGN ass = (InstructionASSIGN)op2;

                        generate_assign(ass);

                        Variable v = (Variable)(ass.operands.get(0));

                        assign(reg, v.getRegister().getName());
                }

                else if(op2 instanceof InstructionIF) {

                        String ret = generate_if((InstructionIF) op2);
                        generate_branch_label(ret);
                        assign(reg, "r0");

                }





        }

        /*else if(op2 == null){
           assign(operand1, "r2");
           }*/


}



/* Offset with imemediate value as offset*/
private void offset_operations(String mnemonic, String dest, String src, int offset){

        textSection.text.append("\t").append(mnemonic).append(" ").append(dest).append("[")
        .append(src).append(" ,#").append(offset).append(" ]\n");
}


/*offset mode with register as offset*/
private void offset_operations(String mnemonic, String dest, String src, String offset){

        textSection.text.append("\t").append(mnemonic).append(" ").append(dest).append("[")
        .append(src).append(" ,#").append(offset).append(" ]\n");
}


private void move_operations(String dest, String src){

        textSection.text.append("\t").append("MOV ").append(dest).append(", ")
        .append(src).append("\n");
}


public void assign(String dest, String src){

        textSection.text.append("\tMOV ").append(dest).append(", ").append(src).append("\n");

}



private void assign(String dest, int src ){

        textSection.text.append("\tLDR ").append(dest).append(", =").append(src).append("\n");
}


/*
   @des: This function genererates code for the arithmetic operations with register
   as first operand and a intermediate value(INT)
   @input: mnemonic: ADD/SUB dest: register operand1: register operand2: register
 */

public void arith_operation(String mnemonic, String dest, String operand1, String operand2){

        textSection.text.append("\t").append(mnemonic).append(" ").append(dest).append(", ")
        .append(operand1).append(", ").append(operand2).append("\n");
}

/*
   @des: This function genererates code for the arithmetic operations with register
   as first operand and a intermediate value(INT)
   @input: mnemonic: ADD/SUB dest: register operand1: register operand2: int
 */
public void arith_operation(String mnemonic, String dest, String operand1, int operand2){

        textSection.text.append("\t").append(mnemonic).append(" ").append(dest).append(", ")
        .append(operand1).append(", #").append(operand2).append("\n");
}

/*
   @des: This function genererates code for the arithmetic operations with register
   as first operand and a intermediate value(INT)
   @input: mnemonic: ADD/SUB dest: register operand1: int operand2: register
 */

public void arith_operation(String mnemonic, String dest, int operand1, String operand2){
        textSection.text.append("\t").append(mnemonic).append(" ").append(dest).append(", #")
        .append(operand1).append(", ").append(operand2).append("\n");
}

/*
   @des: This function genererates code for the arithmetic operations with register
   as first operand and a intermediate value(INT)
   @input: mnemonic: ADD/SUB dest: register operand1: int operand2: int
 */

public void arith_operation(String mnemonic, String dest, int operand1, int operand2){

        textSection.text.append("\t").append(mnemonic).append(" ").append(dest).append(", #")
        .append(operand1).append(", #").append(operand2).append("\n");
}


public void stash_locals(){

        textSection.text.append("\tSTMFD sp!,{r2-r12}\n");
}

public void restore_locals(){
        textSection.text.append("\tLDMFD sp!, {r2-r12}\n");

}

public void stash_parameters(){
        textSection.text.append("\tSTMFD sp!, {r0-r3}\n");
}

public void restore_parameters(){
        textSection.text.append("\tLDMFD sp!, {r0-r3}\n");


}


public void main_prologue(){

        textSection.text.append("\t@MAIN PROLOGUE\n");
        textSection.text.append("\tSUB sp, #4\n");
        textSection.text.append("\tLDR lr, [sp]\n");
        textSection.text.append("\tSUB sp, #4\n");
        textSection.text.append("\tSTR fp, [sp]\n");
        textSection.text.append("\tMOV fp, sp\n\n");

        //textSection.text.append("\t@MAIN PROLOGUE\n");
        //textSection.text.append("\tSTMFD sp!, {fp, lr}\n");
        //textSection.text.append("\tADD fp, sp, #4\n");


}

public void main_epilogue(){

        textSection.text.append("\n\t@MAIN EPILOGUE\n");
        textSection.text.append("\tADD sp, #4\n");
        textSection.text.append("\tMOV sp, fp\n");
        textSection.text.append("\tLDR fp, [sp]\n");
        textSection.text.append("\tADD sp, #4\n\n");

}

public void function_prologue(int size){

        textSection.text.append("\t@FUNCTION PROLOGUE\n");
        textSection.text.append("\tSTMFD sp!, {fp, lr}\n");
        textSection.text.append("\tADD fp, sp, #4\n");
        reserve_space(size);
        textSection.text.append("\n");

}

public void function_epilogue(){

        textSection.text.append("\n\t@FUNCTION EPILOGUE\n");
        textSection.text.append("\tSUB sp, fp, #4\n");
        textSection.text.append("\tLDMFD sp!, {fp, lr}\n");
        textSection.text.append("\tBX lr\n\n");

}


public void generate_function_label(String fname){

        textSection.text.append("_").append(fname).append(":\n");
}

public void generate_branch_label(String fname){

        textSection.text.append(fname).append(":\n");
}




public void output_terminal(){

        textSection.text.append("\tMOV r7, #1\n");
        textSection.text.append("\tswi 0\n");
}

public String get_label(String name){

        return "_"+name;

}

public String gen_temp_label(){
        label_counter++;
        return "cont" + String.valueOf(label_counter);
}

public String generate_if(InstructionIF inst){

        Function then_branch = inst.branch_then;
        Function else_branch = inst.branch_else;

        BooleanExpression exp = inst.cond.getExp();
        // System.out.println(inst.cond);
        // System.out.println(exp);

        String operand1="";
        String operand2 = "";
        String offset1 = "";

        if(exp instanceof BooleanEQ) {
                Variable op1 = (Variable)(((BooleanEQ)exp).operands.get(0));
                Variable op2 = (Variable)(((BooleanEQ)exp).operands.get(1));


                //VARIABLE WITH REGISTER
                if(((Variable)op1).getRegister()!=null &&   ((Variable)op1).getParametersRegister()==null && ((Variable)op1).getParametersOffset()==null) {
                        operand1=((Variable)op1).getRegister().getName();
                }
                //VARIABLE WITH OFFSET
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getOffset()!=null && ((Variable)op1).getParametersRegister()==null) {

                        operand1="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";

                }

                // if its a parameter with a register
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getOffset()==null && ((Variable)op1).getParametersRegister()!=null) {

                        operand1=((Variable)op1).getParametersRegister().getName();
                }


                // if its a paramter with an offset
                else if(((Variable)op1).getRegister()==null && ((Variable)op1).getParametersOffset()!=null && ((Variable)op1).getOffset()==null ) {

                        operand1="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";
                }

                if(((Variable)op2).getRegister()!=null &&   ((Variable)op2).getParametersRegister()==null && ((Variable)op2).getParametersOffset()==null) {
                        operand2=((Variable)op2).getRegister().getName();
                }
                // it its a local variable with an offset
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getOffset()==null && ((Variable)op2).getParametersRegister()==null) {

                        operand2="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";

                }

                // if its a parameter with a register
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getOffset()==null && ((Variable)op2).getParametersRegister()!=null) {

                        operand2=((Variable)op2).getParametersRegister().getName();
                }


                // if its a paramter with an offset
                else if(((Variable)op2).getRegister()==null && ((Variable)op2).getParametersOffset()!=null && ((Variable)op2).getOffset()==null ) {

                        operand2="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }


                textSection.text.append("\tCMP ").append(operand1).append(" , ").append(operand2).append("\n");
                textSection.text.append("\tBEQ ").append(inst.branch_then.getName()).append("\n");;
                textSection.text.append("\tB ").append(inst.branch_else.getName()).append("\n");;

        }

        else if (exp instanceof BooleanLE) {

                Variable op1 = (Variable)(((BooleanLE)exp).operands.get(0));
                Variable op2 = (Variable)(((BooleanLE)exp).operands.get(1));


                //VARIABLE WITH REGISTER
                if(((Variable)op1).getRegister() != null &&
                   ((Variable)op1).getParametersRegister() == null &&
                   ((Variable)op1).getParametersOffset() == null) {

                        operand1=((Variable)op1).getRegister().getName();
                }
                //VARIABLE WITH OFFSET
                else if (((Variable)op1).getRegister() == null &&
                         ((Variable)op1).getOffset() != null &&
                         ((Variable)op1).getParametersRegister() == null) {

                        operand1="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";

                }

                // if its a parameter with a register
                else if (((Variable)op1).getRegister() == null &&
                         ((Variable)op1).getOffset() == null &&
                         ((Variable)op1).getParametersRegister() != null) {

                        operand1=((Variable)op1).getParametersRegister().getName();
                }


                // if its a paramter with an offset
                else if (((Variable)op1).getRegister() == null &&
                         ((Variable)op1).getParametersOffset() != null &&
                         ((Variable)op1).getOffset() == null ) {

                        operand1="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , ").append(operand1).append("\n");
                        operand1="r0";
                }

                if (((Variable)op2).getRegister() != null &&
                    ((Variable)op2).getParametersRegister() == null &&
                    ((Variable)op2).getParametersOffset() == null) {

                        operand2=((Variable)op2).getRegister().getName();
                }
                // it its a local variable with an offset
                else if (((Variable)op2).getRegister() == null &&
                         ((Variable)op2).getOffset() == null &&
                         ((Variable)op2).getParametersRegister() == null) {

                        operand2="[fp ,#-" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";

                }

                // if its a parameter with a register
                else if (((Variable)op2).getRegister() == null &&
                         ((Variable)op2).getOffset() == null &&
                         ((Variable)op2).getParametersRegister() != null) {

                        operand2=((Variable)op2).getParametersRegister().getName();
                }

                // if its a paramter with an offset
                else if (((Variable)op2).getRegister() == null &&
                         ((Variable)op2).getParametersOffset() != null &&
                         ((Variable)op2).getOffset() == null) {

                        operand2="[fp ,#" + ((Variable)op2).getParametersOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , ").append(operand2).append("\n");
                        operand2="r1";
                }

                textSection.text.append("\tCMP ").append(operand1).append(" , ").append(operand2).append("\n");
                textSection.text.append("\tBLE ").append(inst.branch_then.getName()).append("\n");
                textSection.text.append("\tB ").append(inst.branch_else.getName()).append("\n");



        }

        else if (exp instanceof BooleanTrue) {
                textSection.text.append("\tB ").append(inst.branch_then.getName()).append("\n");
        }

        else if(exp instanceof BooleanFalse) {
                textSection.text.append("\tB ").append(inst.branch_else.getName()).append("\n");
        }
        else{
                System.out.println("Something crazy happenned");
        }

        String return_label = gen_temp_label();

        //generate code for branch then

        generate_branch(then_branch, return_label);


        //generate code for branch then

        generate_branch(else_branch, return_label);

        return return_label;


}


public void generate_function_call(InstructionCALL instr) {
        List<Object> params = instr.getParams();
        String return_reg = instr.getReturn();
        String fname = get_label(instr.getFname());
        int num_params=params.size();

        if(instr.getFname().equals("print_int")) {
                if(params.get(0)!=null) {
                        if(params.size() != 0) {
                                if(!(params.get(0) instanceof Integer)) {
                                        Variable param = (Variable)params.get(0);
                                        //assign("r0", param.getRegister().getName());

                                        //VARIABLE WITH A PARAMETER REGISTER
                                        if(param.getRegister() != null ) {
                                                assign("r0", param.getRegister().getName());
                                        }

                                        // case where local variable has an offset but the paramter has a register
                                        //VAIABLE WITH AN OFFSET
                                        else if(param.getRegister() == null ) {
                                                //System.out.println("case2");
                                                // load variable from the stack
                                                String localoffset="[fp ,#-" + ((Variable)param).getOffset().toString()+"]";
                                                textSection.text.append("\tLDR r0 , ").append(localoffset).append("\n");

                                        }


                                        // case where both local and parameters have and offset

                                        else {
                                                //System.out.println("case3");
                                                String localoffset="[fp ,#-" + ((Variable)param).getParametersOffset().toString()+"]";
                                                textSection.text.append("\tLDR r0 , ").append(localoffset).append("\n");
                                        }

                                }

                        }
                }
                textSection.text.append("\tBL min_caml_print_int\n");
                textSection.text.append("\tBL min_caml_print_newline\n");
                return;
        }

        stash_locals();

        //check if function has parameters
        if(params.size() != 0) {

                //get first parameter
                Object par = params.get(0);

                if(par instanceof Variable) {

                        push_params(params);

                }

                else if(par ==null) {

                        textSection.text.append("\tMOV r2, r0").append("\n");
                }

        }

        textSection.text.append("\tBL ").append(fname).append("\n");
        int diff = num_params - 2;
        if(diff>=1) {
                textSection.text.append("\tADD sp, #").append(diff*4).append("\n");
        }
        restore_locals();
        if(num_params >= available_reg_param) {
                //restore_parameters();

        }

}

public void push_params(List<Object> params){


        int l = params.size();
        Variable param;
        int i;

        if(l <= 2) {
                for(i=0; i<l; i++) {
                        param= (Variable)params.get(i);
                        // case where the local var has a register and the paramter has a register
                        if(param.getRegister()!= null && param.getParametersRegister()!=null) {
                                // System.out.println("1");
                                assign(param.getParametersRegister().getName(), param.getRegister().getName());
                        }
                }
        }
        else{

                for(i=0; i<2; i++) {
                        param = (Variable)params.get(i);
                        // case where the local var has a register and the paramter has a register
                        if(param.getRegister()!= null && param.getParametersRegister()!=null) {
                                // System.out.println("1");
                                assign(param.getParametersRegister().getName(), param.getRegister().getName());
                        }
                }

                int left = l-2;
                for (i = l-1; i>=left; i--) {
                        param = (Variable)params.get(i);
                        // case where the local var has a register and the paramter has a register
                        if(param.getRegister()!= null && param.getParametersRegister()!=null) {
                                // System.out.println("1");
                                assign(param.getParametersRegister().getName(), param.getRegister().getName());
                        }
                        // case where pushing the local variable has a register but the parameter must be pushed on the stack
                        else if (param.getRegister()!= null && param.getParametersRegister()==null) {
                                // System.out.println("2");
                                String value =param.getRegister().getName();
                                //reserve_space_param(4);
                                textSection.text.append("\tSUB sp, #4\n");
                                assign("r0", value);
                                textSection.text.append("\tSTR r0, [sp]\n");
                        }

                        // case where local variable has an offset but the paramter has a register

                        else if(param.getRegister()==null && param.getParametersRegister()!=null ) {
                                // System.out.println("3");
                                // load variable from the stack
                                String localoffset="[fp ,#" + ((Variable)param).getOffset().toString()+"]";
                                textSection.text.append("\tLDR r0 , ").append(localoffset).append("\n");

                                String value =param.getParametersRegister().getName();
                                assign(value, "r0");
                        }

                        // case where local var has an offset and the parameter has an offset

                        else if(param.getRegister()==null && param.getParametersRegister() == null) {
                                // System.out.println("4");
                                // load variable from the stack
                                String localoffset="[fp ,#" + ((Variable)param).getOffset().toString()+"]";
                                textSection.text.append("\tLDR r0 , ").append(localoffset).append("\n");
                                textSection.text.append("\tSUB sp, #4\n");
                                textSection.text.append("\tSTR r0, [sp]\n");

                        }

                }


        }


}










}
