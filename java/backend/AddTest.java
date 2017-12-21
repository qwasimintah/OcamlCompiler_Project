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
ml: 1 + 250

asml 

let _ =
  let x = 1 in
  let y = 250 in
  add x y


Expected Result = 251;

"""

*/

public class AddTest{


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
        VInteger y = new VInteger("y", -2, registers,fundef);

        locals.add(x);
        locals.add(y);

        fundef.setVariables(locals);


 

        RegisterUtils.showRegisters(registers);
        RegisterUtils.showRegisters(param_registers);

        InstructionASSIGN q = new InstructionASSIGN(fundef, x, 1);
        InstructionASSIGN p = new InstructionASSIGN(fundef, y, 250);

        InstructionADD add_f = new InstructionADD(fundef, x, y);

        //fundef.addInstruction(call);
        fundef.addInstruction(q);
        fundef.addInstruction(p);
        fundef.addInstruction(add_f);
    
        funs.add(fundef);
        arm.generate_code(funs);

        StringBuilder result= arm.textSection.text;

        System.out.println(result);


        try (FileOutputStream oS = new FileOutputStream(new File("../../ARM/add_test.s"))) {
	               oS.write(result.toString().getBytes());
              } catch (IOException e) {
	                e.printStackTrace();
        }

   }










}
