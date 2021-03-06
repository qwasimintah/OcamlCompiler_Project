package test;


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

let _f x y =
  let z = add x y in
  let t = 2 in
  sub t z

let _ =
  let x = 0 in
  let y = 1 in
  let z = call _f x y in
  call _min_caml_print_int z


Expected Result = 1;

"""

*/

public class TestArm{


   public static void main(String[] args){

        ArmGenerator  arm = new ArmGenerator();


        List<Instruction> instr = new ArrayList<Instruction>();
        List<Instruction> add_instr = new ArrayList<Instruction>();

        //global structure
        List<Function> funs= new ArrayList<Function>();

        //functions
        Function fundef= new Function("main", null, instr);
        Function fadd = new Function("add", null, add_instr);
        Function _f = new Function("f", null, add_instr);


        //variables
        Integer x= new Integer(1);
        Integer f = new Integer(2);
        HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);
        HashMap<Register, Variable> param_registers = new HashMap<Register, Variable>(4);
        HashSet<Variable> locals = new HashSet<Variable>();
        HashSet<Variable> flocals= new HashSet<Variable>();

        RegisterUtils.initRegisters(registers, param_registers);
       //

       //RegisterUtils.showRegisters(registers);


        VInteger z = new VInteger("z", -1, registers,fundef);
        VInteger t = new VInteger("t", 2, registers,fundef);
        VInteger l = new VInteger("l", -1, registers,fundef);
        VInteger vx = new VInteger("x",0, registers,fundef);
        VInteger vy = new VInteger("y",1, registers,fundef);


        Parameter px = new Parameter(vx.getName(),vx.getRegister().getName(), param_registers, _f);
        Parameter py = new Parameter(vy.getName(),vy.getRegister().getName(), param_registers, _f);
        Parameter pz = new Parameter(l.getName(),l.getRegister().getName(), param_registers, fundef);

        flocals.add(z);
        flocals.add(t);

        locals.add(vx);
        locals.add(vy);

        fundef.setVariables(locals);
        _f.setVariables(flocals);


        List<Parameter> params = new ArrayList<Parameter>();
        params.add(px);
        params.add(py);


        RegisterUtils.showRegisters(registers);
        RegisterUtils.showRegisters(param_registers);

        InstructionASSIGN q = new InstructionASSIGN(fundef, vx, 0);
        InstructionASSIGN p = new InstructionASSIGN(fundef, vy, 1);
        InstructionCALL call_f = new InstructionCALL(params, "f");
        InstructionASSIGN lmain = new InstructionASSIGN(fundef, l, call_f);
        //InstructionCALL call_f = new InstructionCALL(params, "f");

        List<Parameter> para = new ArrayList<Parameter>();
        para.add(pz);
        InstructionCALL call_min = new InstructionCALL(para, "print_int");



        InstructionADD add_f = new InstructionADD(_f, px, py);
        InstructionASSIGN vz = new InstructionASSIGN(_f, z, add_f);
        InstructionASSIGN vt = new InstructionASSIGN(_f, t, 2);
        InstructionSUB sub_f= new InstructionSUB(_f, t, z);

        //fundef.addInstruction(call);
        fundef.addInstruction(q);
        fundef.addInstruction(p);
        fundef.addInstruction(lmain);
        fundef.addInstruction(call_min);
        //fundef.addInstruction(ci);
        //fundef.addInstruction(sub);

        //fundef.addInstruction(ass);stash
        _f.addInstruction(vz);
        _f.addInstruction(vt);
        _f.addInstruction(sub_f);

        //fadd.addInstruction(add);
        //fadd.addInstruction(fadd_ass);
        //List<Parameter> para= new ArrayList<Parameter>();
        //para.add(new Parameter(b.getName(),b.getValue(), param_registers, fadd));
        //fadd.addInstruction(new InstructionCALL(para,"print_int"));
        //fadd.addInstruction(add);


        //fundef.addInstruction(sub);
        //fundef.addInstruction(mul);
        funs.add(fundef);
        funs.add(_f);
        arm.generate_code(funs);

        StringBuilder result= arm.textSection.text;

        System.out.println(result);


        try (FileOutputStream oS = new FileOutputStream(new File("../../ARM/arm_test.s"))) {
	               oS.write(result.toString().getBytes());
              } catch (IOException e) {
	                e.printStackTrace();
        }

   }










}
