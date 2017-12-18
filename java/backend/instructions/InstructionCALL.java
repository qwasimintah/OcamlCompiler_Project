


public class InstructionCALL implements Instruction {

    private List<Variables> params;
    private String return_reg;
    private String fname;


    public InstructionCALL(List<Variable> params, String return_reg, String fname){

        this.params = params;
        this.return_reg= return_reg;
        this.fname = fname;

    }

    public InstructionCALL(List<Variable> params, String fname){

        this.params = params;
        this.return_reg="r0";
        this.fname= fname;

    }


    public  List<Variable> getParams (){

          return params;
    }

    public String getReturn(){
        return return_reg;
    }

    public String getfname(){

       return fname;
    }

}
