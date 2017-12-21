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
import exceptions.*;
import java.io.*;



public class ArmGenerator {

    public DataSection dataSection;
    public TextSection textSection;
    private int HEAP_SIZE=1024*4; // Heap size in bytes
    //private HashMap<String, Integer> fun_arg_locations;
    private int available_reg=9;
    private int available_reg_param=2;


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

    }




    public void generate_code(List<Function>  functions){
         //loop through all the available functions
         for(Function fun : functions){
             List<Parameter> arguments = fun.getArguments();
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

                  //function_epilogue();
                  main_epilogue();
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

        if(size > available_reg){
          textSection.text.append("\tADD sp, #").append((size-available_reg)*4).append("\n");
        }
    }


    public void reserve_space_param(int size){

      if(size > available_reg){
       textSection.text.append("\tSUB sp, #").append((size-available_reg_param)*4).append("\n");
    }
  }

    public void pop_params(int size){

        if(size > available_reg){
          textSection.text.append("\tADD sp, #").append((size-available_reg_param)*4).append("\n");
        }
    }


    public  void generate_addition(InstructionADD instr){
            Object op1= instr.operands.get(0);
            Object op2= instr.operands.get(1);
            String rd="r0";
            String operand1="";
            String operand2="";

            // if operand1 is an local integer variable
            if(op1 instanceof VInteger){
                  if(((VInteger)op1).getRegister()!=null){
                    operand1=((VInteger)op1).getRegister().getName();
                  }
                  else{

                    operand1="[fp, #" + ((VInteger)op1).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                    operand1="r0";
                  }
            }
            // else if operand1 is a function parameter
            else if(op1 instanceof Parameter){
              if(((Parameter)op1).getRegister()!=null){
                operand1=((Parameter)op1).getRegister().getName();
              }
              else{

                operand1="[fp, #" + ((Parameter)op1).getOffset().toString()+"]";
                textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                operand1="r0";
              }

            }


            if(op2 instanceof VInteger){
                  if(((VInteger)op2).getRegister()!=null){
                    operand2=((VInteger)op2).getRegister().getName();
                  }
                  else{
                    operand2="[fp ,#" + ((VInteger)op2).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                    operand2="r1";

                  }
            }

            else if(op2 instanceof Parameter){
              if(((Parameter)op2).getRegister()!=null){
                operand2=((Parameter)op2).getRegister().getName();
              }
              else{

                operand2="[fp, #" + ((Parameter)op1).getOffset().toString()+"]";
                textSection.text.append("\tLDR r0 , "). append(operand2).append("\n");
                operand2="r0";
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

            // if operand1 is an local integer variable
            if(op1 instanceof VInteger){
                  if(((VInteger)op1).getRegister()!=null){
                    operand1=((VInteger)op1).getRegister().getName();
                  }
                  else{

                    operand1="[fp, #" + ((VInteger)op1).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                    operand1="r0";
                  }
            }
            // else if operand1 is a function parameter
            else if(op1 instanceof Parameter){
              if(((Parameter)op1).getRegister()!=null){
                operand1=((Parameter)op1).getRegister().getName();
              }
              else{

                operand1="[fp, #" + ((Parameter)op1).getOffset().toString()+"]";
                textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                operand1="r0";
              }

            }


            if(op2 instanceof VInteger){
                  if(((VInteger)op2).getRegister()!=null){
                    operand2=((VInteger)op2).getRegister().getName();
                  }
                  else{
                    operand2="[fp ,#" + ((VInteger)op2).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                    operand2="r1";

                  }
            }

            else if(op2 instanceof Parameter){
              if(((Parameter)op2).getRegister()!=null){
                operand2=((Parameter)op2).getRegister().getName();
              }
              else{

                operand2="[fp, #" + ((Parameter)op1).getOffset().toString()+"]";
                textSection.text.append("\tLDR r0 , "). append(operand2).append("\n");
                operand2="r0";
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

               // if operand1 is an local integer variable
               if(op1 instanceof VInteger){
                     if(((VInteger)op1).getRegister()!=null){
                       operand1=((VInteger)op1).getRegister().getName();
                     }
                     else{

                       operand1="[fp, #" + ((VInteger)op1).getOffset().toString()+"]";
                       textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                       operand1="r0";
                     }
               }
               // else if operand1 is a function parameter
               else if(op1 instanceof Parameter){
                 if(((Parameter)op1).getRegister()!=null){
                   operand1=((Parameter)op1).getRegister().getName();
                 }
                 else{

                   operand1="[fp, #" + ((Parameter)op1).getOffset().toString()+"]";
                   textSection.text.append("\tLDR r0 , "). append(operand1).append("\n");
                   operand1="r0";
                 }

               }


               if(op2 instanceof VInteger){
                     if(((VInteger)op2).getRegister()!=null){
                       operand2=((VInteger)op2).getRegister().getName();
                     }
                     else{
                       operand2="[fp ,#" + ((VInteger)op2).getOffset().toString()+"]";
                       textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                       operand2="r1";

                     }
               }

               else if(op2 instanceof Parameter){
                 if(((Parameter)op2).getRegister()!=null){
                   operand2=((Parameter)op2).getRegister().getName();
                 }
                 else{

                   operand2="[fp, #" + ((Parameter)op1).getOffset().toString()+"]";
                   textSection.text.append("\tLDR r0 , "). append(operand2).append("\n");
                   operand2="r0";
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

            if(op1 instanceof VInteger){
                  if(((VInteger)op1).getRegister()!=null){
                    operand1=((VInteger)op1).getRegister().getName();
                  }
                  else{

                    offset1="[fp , #" + ((VInteger)op1).getOffset().toString()+"]";
                    //textSection.text.append("\tSTR r0 , "). append(operand1).append("\n");
                    operand1="r0";
                  }
            }


            if(op2 instanceof VInteger){
                  if(((VInteger)op2).getRegister()!=null){
                    operand2=((VInteger)op2).getRegister().getName();
                  }
                  else{
                    operand2="[fp ,#" + ((VInteger)op2).getOffset().toString()+"]";
                    textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                    operand2="r1";

                  }
            }

            else if(op2 instanceof Parameter){

                    if(((Parameter)op2).getRegister()!=null){
                      operand2=((Parameter)op2).getRegister().getName();
                    }
                    else{
                      operand2="[fp ,#" + ((Parameter)op2).getOffset().toString()+"]";
                      textSection.text.append("\tLDR r1 , "). append(operand2).append("\n");
                      operand2="r1";

                    }


            }



               if(op1 instanceof VInteger && op2 instanceof Variable){
                     assign(operand1, operand2);
                     if(offset1!=""){
                      textSection.text.append("\tSTR r0 , "). append(offset1).append("\n");
                     }
               }

               else if(op1 instanceof VInteger && op2 instanceof Integer){

                    assign(operand1, ((int)op2));
                    if(offset1!=""){
                      textSection.text.append("\tSTR r0 , "). append(offset1).append("\n");
                     }

               }

               else if(op1 instanceof VInteger && op2 instanceof Instruction){

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

                      else if(op2 instanceof InstructionCALL){

                        generate_function_call((InstructionCALL) op2);
                        assign(reg, ((InstructionCALL)op2).getReturn());

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

        textSection.text.append("\tSTMFD sp!,{r4-r12}\n");
   }

   public void restore_locals(){
        textSection.text.append("\tLDMFD sp!, {r4-r12}\n");

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


        List<Parameter> params = instr.getParams();
        String return_reg = instr.getReturn();
        String fname = get_label(instr.getFname());
        int num_params=params.size();

        if(instr.getFname().equals("print_int")){
          Parameter param = (Parameter)params.get(0);
          assign("r0" , param.getVReg());
          textSection.text.append("\tBL min_caml_print_int\n");
          textSection.text.append("\tBL min_caml_print_newline\n");
          return;
        }
        stash_locals();
         for(int i=0; i<num_params; i++){
           Parameter param = (Parameter)params.get(i);

            if(param.getRegister()!= null){
              assign(param.getRegister().getName() , param.getVReg());
            }
          }

          if(num_params >= available_reg_param){
            //stash_parameters();stash
            for(int i =2; i< num_params; i++){
                //System.out.println("came here\n");
                String value =((Parameter)params.get(i)).getVReg();
                reserve_space_param(4);
                textSection.text.append("\tSUB sp, #4\n");
                assign("r0", value);
                textSection.text.append("\tSTR r0, [sp]\n");
              }
        }

         textSection.text.append("\tBL ").append(fname).append("\n");

         if(num_params >= available_reg_param){
              //restore_parameters();

         }
         restore_locals();

  }


   public static void main(String[] args){

        /*ArmGenerator  arm = new ArmGenerator();


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
        HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);
        HashMap<Register, Variable> param_registers = new HashMap<Register, Variable>(4);
        HashSet<Variable> locals = new HashSet<Variable>();

        RegisterUtils.initRegisters(registers, param_registers);
       //

       //RegisterUtils.showRegisters(registers);


        VInteger y = new VInteger("y", 10, registers,fundef);
        VInteger w = new VInteger("w", 11, registers,fundef);
        VInteger a = new VInteger("a",20, registers,fundef);
        VInteger b = new VInteger("b",40, registers,fundef);
        VInteger y1 = new VInteger("y",60, registers,fundef);
        VInteger w1 = new VInteger("w",80, registers,fundef);
        VInteger a1 = new VInteger("a",100, registers,fundef);
        VInteger b1 = new VInteger("b",110, registers,fundef);
        VInteger y2 = new VInteger("y",120, registers,fundef);
        VInteger w2 = new VInteger("w",130, registers,fundef);
        VInteger a2 = new VInteger("a",140, registers,fundef);
        VInteger b2 = new VInteger("b",150, registers,fundef);



        Parameter c = new Parameter("c",1, param_registers,fundef);
        Parameter d = new Parameter("d",2, param_registers,fundef);
        Parameter r = new Parameter("r",512, param_registers,fundef);
        Parameter t = new Parameter("t",512, param_registers,fundef);
        Parameter g = new Parameter("g",512, param_registers,fundef);

        locals.add(y);
        locals.add(w);

        fundef.setVariables(locals);
        fadd.setVariables(locals);

        
          y.allocRegister();
          w.allocRegister();
          a.allocRegister();
          b.allocRegister();
          y1.allocRegister();
          w1.allocRegister();
          a1.allocRegister();
          b1.allocRegister();
          y2.allocRegister();
          w2.allocRegister();
          a2.allocRegister();
          b2.allocRegister();



          c.allocRegister();
          d.allocRegister();
          r.allocRegister();
          t.allocRegister();
          g.allocRegister();


        

        List<Parameter> params = new ArrayList<Parameter>();
        params.add(c);
        params.add(d);
        params.add(r);
        //params.add(r);
        //params.add(t);
        //params.add(g);
        //params.add(w);

        y.getSaveState();
        System.out.println(w.getOffset());
        g.getSaveState();
        System.out.println(g.getOffset());
        RegisterUtils.showRegisters(registers);
        RegisterUtils.showRegisters(param_registers);

        InstructionASSIGN q = new InstructionASSIGN(fundef, y, 5);
        InstructionASSIGN p = new InstructionASSIGN(fundef, w, 9);
        InstructionASSIGN ai = new InstructionASSIGN(fundef, a, 20);
        InstructionASSIGN bi = new InstructionASSIGN(fundef, b, 40);
        InstructionASSIGN ci = new InstructionASSIGN(fundef, c, 200);


        InstructionADD add = new InstructionADD(fundef, c, d);
        InstructionASSIGN fadd_ass= new InstructionASSIGN(fadd,b, r);

        InstructionSUB sub = new InstructionSUB(fundef, c, b);
        InstructionMULT mul = new InstructionMULT(fundef, a, y);

        InstructionCALL call = new InstructionCALL(params, "add");
       // add.show();
        InstructionASSIGN ass = new InstructionASSIGN(fundef, c, sub);
       // ass.show();
        //fundef.addInstruction(call);
        fundef.addInstruction(q);
        fundef.addInstruction(p);
        fundef.addInstruction(call);
        //fundef.addInstruction(bi);
        //fundef.addInstruction(ci);
        //fundef.addInstruction(sub);

        //fundef.addInstruction(ass);stash


        fadd.addInstruction(add);
        fadd.addInstruction(fadd_ass);
        List<Parameter> para= new ArrayList<Parameter>();
        para.add(new Parameter(b.getName(),b.getValue(), param_registers, fadd));
        fadd.addInstruction(new InstructionCALL(para,"print_int"));
        //fadd.addInstruction(add);


        //fundef.addInstruction(sub);
        //fundef.addInstruction(mul);
        funs.add(fundef);
        funs.add(fadd);
        arm.generate_code(funs);

        StringBuilder result= arm.textSection.text;

        System.out.println(result);


        try (FileOutputStream oS = new FileOutputStream(new File("../../ARM/call_test.s"))) {
	               oS.write(result.toString().getBytes());
              } catch (IOException e) {
	                e.printStackTrace();
        }*/

   }










}
