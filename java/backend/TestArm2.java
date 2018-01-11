/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;
/**
 *
 * @author DJAN DENNIS MINTAH
 */
import java.util.*;
import java.io.*;
import backend.variables.*;
import backend.functions.*;
import backend.instructions.*;
import backend.registers.*;
import backend.exceptions.*;

/*"""

ASML

let _f x y =
  let z = add x y in
  let t = 2 in
  let q = sub t z in
  add q t

let _ =
  let x = 0 in
  let y = 1 in
  let w = 2 in
  let z = call _f x y in
  call _min_caml_print_int z

Expected Result= 3;


"""

/*/

public class TestArm2 {


   public static void main(String[] args){

        ArmGenerator  arm = new ArmGenerator();


        List<Instruction> instr = new ArrayList<Instruction>();
        List<Instruction> add_instr = new ArrayList<Instruction>();

        //global structure
        List<Function> funs= new ArrayList<Function>();

        

        //variables
        Integer x= new Integer(1);
        Integer f = new Integer(2);
        
        ArrayList<Register> registers = new ArrayList<Register>(9);
        ArrayList<Register> param_registers = new  ArrayList<Register>(2);

        ArrayList<Register> registers1 = new ArrayList<Register>(9);
        ArrayList<Register> param_registers1 = new  ArrayList<Register>(2);

        HashSet<Variable> locals = new HashSet<Variable>();
        HashSet<Variable> flocals= new HashSet<Variable>();

        RegisterUtils.initRegisters(registers, param_registers);
        RegisterUtils.initRegisters(registers1, param_registers1);
       //

        //functions
        Function fundef= new Function("main", null, instr, registers, param_registers);
        //Function fadd = new Function("add", null, add_instr, registers, param_registers);
        Function _f = new Function("f", null, add_instr, registers1, param_registers1);


       //RegisterUtils.showRegisters(registers);

        ArrayList<Object> _f_params = new ArrayList<Object>();
        VInteger a = new VInteger("a", -1,_f);
        VInteger b = new VInteger("b", -1,_f);
        a.allocParametersRegister();
        a.getSaveState();
        b.allocParametersRegister();
        b.getSaveState();
        _f_params.add(a);
        _f_params.add(b);



        VInteger fz = new VInteger("z", -1, _f);
        VInteger ft = new VInteger("t", 2, _f);
        VInteger fq = new VInteger("q", -1, _f);

        VInteger mz = new VInteger("z", -1, fundef);
        VInteger vx = new VInteger("x",0, fundef);
        VInteger vy = new VInteger("y",1, fundef);
        VInteger vw = new VInteger("w",3, fundef);

        vx.allocRegister();
        vy.allocRegister();
        vw.allocRegister();
        mz.allocRegister();

        fq.allocRegister();
        fz.allocRegister();
        ft.allocRegister();


        flocals.add(fz);
        flocals.add(ft);
        flocals.add(fq);

        locals.add(vx);
        locals.add(vy);
        locals.add(vw);
        locals.add(mz);

        fundef.setVariables(locals);
        _f.setVariables(flocals);

        vx.allocParametersRegister();
        vy.allocParametersRegister();

        vx.getSaveState();
        vy.getSaveState();

        List<Object> params = new ArrayList<Object>();
        params.add(vx);
        params.add(vy);

        RegisterUtils.showRegisters(registers);
        RegisterUtils.showRegisters(param_registers);

        InstructionASSIGN q = new InstructionASSIGN(fundef, vx, 0);
        InstructionASSIGN p = new InstructionASSIGN(fundef, vy, 1);
        InstructionASSIGN wass = new InstructionASSIGN(fundef, vw, 2);
        InstructionCALL call_f = new InstructionCALL(params, "f");
        InstructionASSIGN lmain = new InstructionASSIGN(fundef, mz, call_f);
        //InstructionCALL call_f = new InstructionCALL(params, "f");

        List<Object> para = new ArrayList<Object>();
        mz.allocParametersRegister();
        para.add(mz);
        InstructionCALL call_min = new InstructionCALL(para, "print_int");



        InstructionADD add_f = new InstructionADD(_f, a, b);
        InstructionASSIGN vz = new InstructionASSIGN(_f, fz, add_f);
        InstructionASSIGN vt = new InstructionASSIGN(_f, ft, 2);
        InstructionSUB sub_f= new InstructionSUB(_f, ft, fz);
        InstructionASSIGN qass = new InstructionASSIGN(_f, fq, sub_f);
        InstructionADD add_q = new InstructionADD(_f, fq, ft);

        //fundef.addInstruction(call);
        fundef.addInstruction(q);
        fundef.addInstruction(p);
        fundef.addInstruction(wass);
        fundef.addInstruction(lmain);
        fundef.addInstruction(call_min);
        //fundef.addInstruction(ci);
        //fundef.addInstruction(sub);

        //fundef.addInstruction(ass);stash
        _f.addInstruction(vz);
        _f.addInstruction(vt);
        _f.addInstruction(qass);
        _f.addInstruction(add_q);

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


        try (FileOutputStream oS = new FileOutputStream(new File("../../ARM/arm_test2.s"))) {
	               oS.write(result.toString().getBytes());
              } catch (IOException e) {
	                e.printStackTrace();
        }

   }










}
