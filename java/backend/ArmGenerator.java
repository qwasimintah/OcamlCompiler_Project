/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DJAN DENNIS MINTAH
 */
import java.util.*;
import variables.*;
import functions.*;
import instructions.*;



public class ArmGenerator {

    DataSection dataSection;
    TextSection textSection;
    private int HEAP_SIZE=1024*4; // Heap size in bytes

    public ArmGenerator(){
        //initialise the .data
        dataSection=new DataSection();
        dataSection.data.append("\t.data\n");

        //initialise the .text
        textSection= new TextSection();
        textSection.text.append("\t.text\n");
        textSection.text.append("\t.global _start\n");
        textSection.text.append("_start:\n");

    }


    public void generate_code(List<Function>  functions){
         //loop through all the available functions
         for(Function fun : functions){
             //List<Variables> var = fun.getArguments();
 	          List<Instruction> intr = fun.getInstructions();
             //process all intructions of functions

       	    for (Instruction inst : intr){

                 if(inst instanceof InstructionADD){
                    generate_addition((InstructionADD) inst);
                 }
                 else if (inst instanceof InstructionSUB) {
                    generate_sub((InstructionSUB) inst);
                 }

                 else if (inst instanceof InstructionASSIGN){
                     generate_assign((InstructionASSIGN) inst);

                 }


         		}


         }

    }



    public  void generate_addition(InstructionADD instr){
            Object op1= instr.operands.get(0);
            Object op2= instr.operands.get(1);

            if(op1 instanceof Integer && op2 instanceof Variable){
                  arith_operation("ADD","r1" ,(int)op1, ((Variable)op2).getRegister().getName());
            }
            else if(op1 instanceof Variable && op2 instanceof Variable){
                  arith_operation("ADD","r1" ,((Variable)op1).getRegister(), ((Variable)op2).getRegister().getName());
            }
            else if(op1 instanceof Integer && op2 instanceof Integer){

                arith_operation("ADD","r1" ,(int)op1, (int)(op2));
            }
            else if(op1 instanceof Variable && op2 instanceof Integer){

              arith_operation("ADD","r1" ,((Variable)op1).getRegister(), ((int)op2));

            }


       }

       public void generate_sub(InstructionSUB instr){

               Object op1= instr.operands.get(0);
               Object op2= instr.operands.get(1);

               if(op1 instanceof Integer && op2 instanceof Variable){
                     arith_operation("SUB","r1" ,(int)op1, ((Variable)op2).getRegister());
               }
               else if(op1 instanceof Variable && op2 instanceof Variable){
                     arith_operation("SUB","r1" ,((Variable)op1).getRegister(), ((Variable)op2).getRegister());
               }
               else if(op1 instanceof Integer && op2 instanceof Integer){

                   arith_operation("SUB","r1" ,(int)op1, (int)(op2));
               }
               else if(op1 instanceof Variable && op2 instanceof Integer){

                 arith_operation("SUB","r1" ,((Variable)op1).getRegister(), ((int)op2));

               }

       }

       public  void  generate_assign(InstructionASSIGN instr){

               Object op1= instr.operands.get(0);
               Object op2= instr.operands.get(1);

               if(op1 instanceof Integer && op2 instanceof Variable){
                     arith_operation("ADD","r1" ,(int)op1, ((Variable)op2).getRegister());
               }
               else if(op1 instanceof Variable && op2 instanceof Variable){
                     arith_operation("ADD","r1" ,((Variable)op1).getRegister(), ((Variable)op2).getRegister());
               }
               else if(op1 instanceof Integer && op2 instanceof Integer){

                   arith_operation("ADD","r1" ,(int)op1, (int)(op2));
               }
               else if(op1 instanceof Variable && op2 instanceof Integer){

                 arith_operation("ADD","r1" ,((Variable)op1).getRegister(), ((int)op2));

               }
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

      textSection.text.append("\tLDR ").append(dest).append(", ").append(src).append("\n");

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






   public static void main(String[] args){

        ArmGenerator  arm = new ArmGenerator();

        arm.move_operations("r4", "r5");
        arm.arith_operation("ADD","r5","r6", "r7");
        arm.arith_operation("SUB","r5",1, "r7");
        arm.arith_operation("ADD","r5","r6", 1);
        arm.assign("r5",256);

        List<Instruction> instr = new ArrayList<Instruction>();
        List<Function> funs= new ArrayList<Function>();
        Function fundef= new Function(null, instr);
        Integer x= new Integer(1);
        Integer f = new Integer(2);
        VInteger y = new VInteger("r1", f, null,fundef);
        //fundef.putInstruction(new InstructionADD(x,y));




        InstructionADD add = new InstructionADD(fundef, x, y);

        fundef.addInstruction(add);
        funs.add(fundef);
        arm.generate_code(funs);

        StringBuilder result= arm.textSection.text;

        System.out.println(result);

   }










}
