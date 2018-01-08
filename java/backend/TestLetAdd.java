
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

/*
"""
ml: let x = 3 in x + x

asml 

let _ =
  let x = 3 in
  add x x

Expected Result = 6;

"""

*/

public class TestLetAdd{


   public static void main(String[] args){

        ArmGenerator  arm = new ArmGenerator();


        List<Instruction> instr = new ArrayList<Instruction>();


        //global structure
        List<Function> funs= new ArrayList<Function>();

        //functions
        Function fundef= new Function("main", null, instr);


        HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);
        HashMap<Register, Variable> param_registers = new HashMap<Register, Variable>(4);
        HashSet<Variable> locals = new HashSet<Variable>();
        HashSet<Variable> flocals= new HashSet<Variable>();

        RegisterUtils.initRegisters(registers, param_registers);
       //

       //RegisterUtils.showRegisters(registers);


        VInteger x = new VInteger("x", -1, registers,fundef);

        locals.add(x);

        fundef.setVariables(locals);


 

        //RegisterUtils.showRegisters(registers);
        //RegisterUtils.showRegisters(param_registers);

        InstructionASSIGN q = new InstructionASSIGN(fundef, x, 3);


        InstructionADD add_f = new InstructionADD(fundef, x, x);

        //fundef.addInstruction(call);
        fundef.addInstruction(q);
        fundef.addInstruction(add_f);
    
        funs.add(fundef);
        arm.generate_code(funs);

        StringBuilder result= arm.textSection.text;

        System.out.println(result);


        try (FileOutputStream oS = new FileOutputStream(new File("../../ARM/let_add.s"))) {
	               oS.write(result.toString().getBytes());
              } catch (IOException e) {
	                e.printStackTrace();
        }

   }










}
