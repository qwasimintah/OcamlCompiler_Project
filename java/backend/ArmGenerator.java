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
import registers.*;
import utils.*;
import exceptions.*;
import java.io.*;



public class ArmGenerator {

    public DataSection dataSection;
    public TextSection textSection;
    private int HEAP_SIZE=1024*4; // Heap size in bytes
    private HashMap<String, Integer> fun_arg_locations;
    private int available_reg=1;


    public ArmGenerator(){
        //initialise the .data=new ArrayList<Object>();
        dataSection=new DataSection();
        dataSection.data.append("\t.data\n");

        //initialise the .text
        textSection= new TextSection();
        textSection.text.append("\t.text\n");
        textSection.text.append("\t.global _start\n");
        textSection.text.append("_start:\n");
        textSection.text.append("\tBL _main\n");
        //generate_function_label("main");

        //functions argument offsets;
        fun_arg_locations= new HashMap<String, Integer>();
        //main_prologue();

    }




    public void generate_code(List<Function>  functions){
         //loop through all the available functions
         for(Function fun : functions){
             List<Variable> arguments = fun.getArguments();
 	           List<Instruction> intr = fun.getInstructions();
             HashSet<Variable> locals = fun.getVariables();
             //process all intructions of functions
            int size= locals.size();


            String fname = fun.getName();

            if(fname.equals("main")){
                generate_function_label(fname);

                main_prologue();
                reserve_space(size);
            }
            else{

                generate_function_label(fname);
                function_prologue(size);
                //pushing_local_variables(locals,size);

                if(arguments != null){
                    int argument_size= arguments.size();

                    // calculate offset of function(only need when params are more than four)
                    if(argument_size > 4){
                        int i=1;
                        for(Variable arg : arguments){
                          int offset =4 * i;
                          fun_arg_locations.put(arg.getName(), offset);
                          i++;
                        }
                    }
              }
            }


       	    for (Instruction inst : intr){

                 if(inst instanceof InstructionADD){
                    generate_addition((InstructionADD) inst);
                 }
                 else if (inst instanceof InstructionSUB) {
                    generate_sub((InstructionSUB) inst);
                 }
                  else if (inst instanceof InstructionMULT) {
                    generate_mult((InstructionMULT) inst);
                 }

                 else if (inst instanceof InstructionASSIGN){
                     generate_assign((InstructionASSIGN) inst);

                 }
                 else if (inst instanceof InstructionCALL){
                     generate_function_call((InstructionCALL) inst);

                 }
                 else{
                      System.out.println("Instruction Not Supported\n");
                 }
              }


              //popping local variables;
              pop_locals(size);

              if(fname.equals("main")){

                  function_epilogue();
                  //main_epilogue();
                  output_terminal();
              }
              else{
                  function_epilogue();
              }



         }

    }



    public void pushing_local_params(HashSet<Variable> locals ,int size){

          int num_of_args = size;
          System.out.println(num_of_args);

          if (num_of_args>1){
            int off=1;
            for (int i =available_reg; i<=num_of_args; i++){

                for (Variable l : locals){
                  if(l.getOffset()!=null && l.getOffset() == off*4 ){

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

      if(size > available_reg){
       textSection.text.append("\tSUB sp, #").append((size-available_reg)*4).append("\n");
    }
  }

    public void pop_locals(int size){

        if(size > 1){
          textSection.text.append("\tADD sp, #").append((size-available_reg)*4).append("\n");
        }
    }


    public  void generate_addition(InstructionADD instr){
            Object op1= instr.operands.get(0);
            Object op2= instr.operands.get(1);
            String rd="r0";
            String operand1="";
            String operand2="";

            if(op1 instanceof Variable){
                  if(((Variable)op1).getRegister()!=null){
                    operand1=((Variable)op1).getRegister().getName();
                  }
                  else{

                    operand1="[fp, #" + ((Variable)op1).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                    operand1="r0";
                  }
            }


            if(op2 instanceof Variable){
                  if(((Variable)op2).getRegister()!=null){
                    operand2=((Variable)op2).getRegister().getName();
                  }
                  else{
                    operand2="[fp ,#" + ((Variable)op2).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                    operand2="r1";

                  }
            }


            if(op1 instanceof Integer && op2 instanceof Variable){

                  arith_operation("ADD",rd ,(int)op1, operand2);
            }
            else if(op1 instanceof Variable && op2 instanceof Variable){
                  arith_operation("ADD",rd ,operand1, operand2);
            }
            else if(op1 instanceof Integer && op2 instanceof Integer){

                arith_operation("ADD",rd ,(int)op1, (int)(op2));
            }
            else if(op1 instanceof Variable && op2 instanceof Integer){

              arith_operation("ADD",rd ,operand1, ((int)op2));

            }


       }


     public  void generate_mult(InstructionMULT instr){
            Object op1= instr.operands.get(0);
            Object op2= instr.operands.get(1);
            String rd="r0";

            String operand1="";
            String operand2="";

            if(op1 instanceof Variable){
                  if(((Variable)op1).getRegister()!=null){
                    operand1=((Variable)op1).getRegister().getName();
                  }
                  else{

                    operand1="[fp , #" + ((Variable)op1).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                    operand1="r0";
                  }
            }


            if(op2 instanceof Variable){
                  if(((Variable)op2).getRegister()!=null){
                    operand2=((Variable)op2).getRegister().getName();
                  }
                  else{
                    operand2="[fp ,#" + ((Variable)op2).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                    operand2="r1";

                  }
            }



            if(op1 instanceof Integer && op2 instanceof Variable){

                  arith_operation("MUL",rd ,(int)op1, operand2);
            }
            else if(op1 instanceof Variable && op2 instanceof Variable){
                  arith_operation("MUL",rd ,operand1, operand2);
            }
            else if(op1 instanceof Integer && op2 instanceof Integer){

                arith_operation("MUL",rd ,(int)op1, (int)(op2));
            }
            else if(op1 instanceof Variable && op2 instanceof Integer){

              arith_operation("MUL",rd ,operand1, ((int)op2));

            }



       }





        public void generate_sub(InstructionSUB instr){

               Object op1= instr.operands.get(0);
               Object op2= instr.operands.get(1);

               String rd="r0";

               String operand1="";
               String operand2="";

                if(op1 instanceof Variable){
                      if(((Variable)op1).getRegister()!=null){
                        operand1=((Variable)op1).getRegister().getName();
                      }
                      else{

                        operand1="[fp , #" + ((Variable)op1).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                        operand1="r0";
                      }
                }


                if(op2 instanceof Variable){
                      if(((Variable)op2).getRegister()!=null){
                        operand2=((Variable)op2).getRegister().getName();
                      }
                      else{
                        operand2="[fp ,#" + ((Variable)op2).getOffset().toString()+"]";
                        textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                        operand2="r1";

                      }
                }


                if(op1 instanceof Integer && op2 instanceof Variable){

                      arith_operation("SUB",rd ,(int)op1, operand2);
                }
                else if(op1 instanceof Variable && op2 instanceof Variable){
                      arith_operation("SUB",rd ,operand1, operand2);
                }
                else if(op1 instanceof Integer && op2 instanceof Integer){

                    arith_operation("SUB",rd ,(int)op1, (int)(op2));
                }
                else if(op1 instanceof Variable && op2 instanceof Integer){

                  arith_operation("SUB",rd ,operand1, ((int)op2));

                }



       }


       public  void  generate_assign(InstructionASSIGN instr){

               Object op1= instr.operands.get(0);
               Object op2= instr.operands.get(1);

               String operand1="";
               String offset1="";
               String operand2="";

            if(op1 instanceof Variable){
                  if(((Variable)op1).getRegister()!=null){
                    operand1=((Variable)op1).getRegister().getName();
                  }
                  else{

                    offset1="[fp , #" + ((Variable)op1).getOffset().toString()+"]";
                    //textSection.text.append("\tSTR r0 , "). append(operand1).append("\n");
                    operand1="r0";
                  }
            }


            if(op2 instanceof Variable){
                  if(((Variable)op2).getRegister()!=null){
                    operand2=((Variable)op2).getRegister().getName();
                  }
                  else{
                    operand2="[fp ,#" + ((Variable)op2).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                    operand2="r1";

                  }
            }



               if(op1 instanceof Variable && op2 instanceof Variable){
                     assign(operand1, operand2);
                     if(offset1!=""){
                      textSection.text.append("\tSTR r0 , "). append(offset1).append("\n");
                     }
               }

               else if(op1 instanceof Variable && op2 instanceof Integer){

                    assign(operand1, ((int)op2));
                    if(offset1!=""){
                      textSection.text.append("\tSTR r0 , "). append(offset1).append("\n");
                     }

               }

               else if(op1 instanceof Variable && op2 instanceof Instruction){

                      String reg= operand1;

                      if(op2 instanceof InstructionADD){

                        generate_addition((InstructionADD) op2);
                        assign(reg, "r0" );

                      }
                      else if(op2 instanceof InstructionSUB){

                        generate_sub((InstructionSUB) op2);
                        assign(reg, "r0");

                      }

                      else if(op2 instanceof InstructionMULT){

                        generate_mult((InstructionMULT) op2);
                        assign(reg, "r0");

                      }





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

      textSection.text.append("\tMOV ").append(dest).append(", ").append(src).append("\n");

   }



   private void assign(String dest, int src ){

        textSection.text.append("\tMOV ").append(dest).append(", #").append(src).append("\n");
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



  public void main_prologue(){

      //textSection.text.append("\t@MAIN PROLOGUE\n");
      //textSection.text.append("\tSUB sp, #4\n");
      //textSection.text.append("\tLDR lr, [sp]\n");
      //textSection.text.append("\tSUB sp, #4\n");
      //textSection.text.append("\tSTR fp, [sp]\n");
      //textSection.text.append("\tMOV fp, sp\n\n");

          textSection.text.append("\t@MAIN PROLOGUE\n");
          textSection.text.append("\tSTMFD sp!, {fp, lr}\n");
          textSection.text.append("\tADD fp, sp, #4\n");


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


  public void output_terminal(){

      textSection.text.append("\tMOV r7, #1\n");
      textSection.text.append("\tswi 0\n");

  }

public String get_label(String name){

    return "_"+name;

}

  public void generate_function_call(InstructionCALL instr){


      // TODO: check if call is to predefined function (in this case print)

        List<Variable> params = instr.getParams();
        String return_reg = instr.getReturn();
        String fname = get_label(instr.getFname());
        int num_params=params.size();
       // set arguments for functions


         for(int i=0; i<num_params; i++){
           VInteger param = (VInteger)params.get(i);

            assign("r"+ Integer.toString(i) , param.getValue());

         }

         textSection.text.append("\tBL ").append(fname).append("\n");
  }


   public static void main(String[] args){

        ArmGenerator  arm = new ArmGenerator();


        List<Instruction> instr = new ArrayList<Instruction>();
        List<Instruction> add_instr = new ArrayList<Instruction>();

        //global structure
        List<Function> funs= new ArrayList<Function>();

        //functions
        Function fundef= new Function("main", null, instr);
        Function fadd = new Function("add", null, add_instr);

        //variables
        Integer x= new Integer(1);
        Integer f = new Integer(2);
        HashMap<Register, Variable> registers = new HashMap<Register, Variable>(1);
        HashSet<Variable> locals = new HashSet<Variable>();

        RegisterUtils.initRegisters(registers);
       //

       //RegisterUtils.showRegisters(registers);


        VInteger y = new VInteger("y", f, registers,fundef);
        VInteger w = new VInteger("w", f, registers,fundef);

        locals.add(y);
        locals.add(w);

        fundef.setVariables(locals);
        fadd.setVariables(locals);

        try{
          y.allocRegister();
          w.allocRegister();
        }catch (NoAvailableRegister e) {

        }

        List<Variable> params = new ArrayList<Variable>();
        params.add(y);
        params.add(w);

        //System.out.println(w.getOffset());
        RegisterUtils.showRegisters(registers);
        InstructionASSIGN q = new InstructionASSIGN(fundef, y, 5);
        InstructionASSIGN p = new InstructionASSIGN(fundef, w, 9);
        InstructionADD add = new InstructionADD(fundef, y, w);
        InstructionSUB sub = new InstructionSUB(fundef, w, y);
        InstructionMULT mul = new InstructionMULT(fundef, w, y);

        InstructionCALL call = new InstructionCALL(params, "add");
       // add.show();
        InstructionASSIGN ass = new InstructionASSIGN(fundef, y, add);
       // ass.show();

        //fundef.addInstruction(q);
        //fundef.addInstruction(p);
        fundef.addInstruction(sub);
        //fundef.addInstruction(call);

        fadd.addInstruction(q);
        fadd.addInstruction(p);
        fadd.addInstruction(add);


        //fundef.addInstruction(sub);
        //fundef.addInstruction(mul);
        funs.add(fundef);
        //funs.add(fadd);
        arm.generate_code(funs);

        StringBuilder result= arm.textSection.text;

        System.out.println(result);


        try (FileOutputStream oS = new FileOutputStream(new File("../../ARM/call_test.s"))) {
	               oS.write(result.toString().getBytes());
              } catch (IOException e) {
	                e.printStackTrace();
        }

   }










}
