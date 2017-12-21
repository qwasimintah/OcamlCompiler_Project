package instructions;

import variables.*;
import java.util.*;


public class InstructionCALL implements Instruction{

    private List<Parameter> params;
    private String return_reg;
    private String fname;


    public InstructionCALL(List<Parameter> params, String return_reg, String fname){

        this.params = params;
        this.return_reg= return_reg;
        this.fname = fname;

    }

    public InstructionCALL(List<Parameter> params, String fname){

        this.params = params;
        this.return_reg="r0";
        this.fname= fname;

    }


    public  List<Parameter> getParams (){

          return params;
    }

    public  List<Object> getOperands (){

          return new ArrayList<Object>();
    }

    public String getReturn(){
        return return_reg;
    }

    public String getFname(){

       return fname;
    }

    public void show(){

    }

}
