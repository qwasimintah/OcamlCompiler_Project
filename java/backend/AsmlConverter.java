package backend;

import java.util.*;
import java.io.*;
import backend.variables.*;
import backend.functions.*;
import backend.instructions.*;
import backend.registers.*;
import backend.exceptions.*;



public class AsmlConverter {

StringBuilder text = new StringBuilder();

public StringBuilder convert(List<Function> funs){

			text.append("let _ = \n");

			for(Function fun : funs) {
			List<Variable> arguments = fun.getArguments();
			List<Instruction> intr = fun.getInstructions();
			HashSet<Variable> locals = fun.getVariables();
			//process all intructions of functions
			//int size= locals.size();
			int size = intr.size();
			boolean in = false;
			int count =0;
			String fname = fun.getName();


			for (Instruction instr : intr) {
					count++;
					if(count >= 1 && count < size) {
						in =true;
					}

				if(instr instanceof InstructionADD) {

						text.append("\tadd ");
						Object op1= ((InstructionADD)instr).operands.get(0);
						Object op2= ((InstructionADD)instr).operands.get(1);

						if(op1 instanceof Variable || op1 instanceof VInteger) {
							if(op1 instanceof Variable) {
								text.append(((Variable)op1).getName().substring(1)).append(" ");
							}
							else if(op1 instanceof VInteger) {
								text.append(((VInteger)op1).getName().substring(1)).append(" ");
							}

						}
						else if (op1 instanceof Integer) {

							text.append((int)op1).append(" ");
						}

						if(op2 instanceof Variable || op2 instanceof VInteger) {
							if(op1 instanceof Variable) {
								text.append(((Variable)op2).getName().substring(1));
								// if(in && count < size){
								//  text.append(" in ");
								// }
								text.append(" \n");
							}
							else if(op2 instanceof VInteger) {
								text.append(((VInteger)op2).getName().substring(1));
								// if(in && count < size){
								//  text.append(" in ");
								// }
								text.append(" \n");
							}

						}
						else if (op2 instanceof Integer) {
							text.append((int)op2);
							// if(in && count < size){
							//   text.append(" in ");
							//  }
							text.append(" \n");
						}
                }



                else if (instr instanceof InstructionSUB) {

					text.append("\tsub ");
					Object op1= ((InstructionSUB)instr).operands.get(0);
					Object op2= ((InstructionSUB)instr).operands.get(1);

					if(op1 instanceof Variable || op1 instanceof VInteger) {
						if(op1 instanceof Variable) {
														text.append(((Variable)op1).getName().substring(1)).append(" ");
						}
						else if(op1 instanceof VInteger) {
														text.append(((VInteger)op1).getName().substring(1)).append(" ");
						}

					}
					else if (op1 instanceof Integer) {

						text.append((int)op1).append(" ");
					}

					if(op2 instanceof Variable || op2 instanceof VInteger) {
						if(op1 instanceof Variable) {
							text.append(((Variable)op2).getName().substring(1));
							if(in && count < size) {
															text.append(" in ");
							}
							text.append(" \n");
						}
						else if(op2 instanceof VInteger) {
							text.append(((VInteger)op2).getName().substring(1));
							// if(in && count < size){
							//  text.append(" in ");
							// }
							text.append(" \n");
						}

					}
					else if (op2 instanceof Integer) {
						text.append((int)op2);
						// if(in){
						//   text.append(" in ");
						//  }
						text.append(" \n");
					}

                }
                else if (instr instanceof InstructionMULT) {

						text.append("\tmul ");
						Object op1= ((InstructionMULT)instr).operands.get(0);
						Object op2= ((InstructionMULT)instr).operands.get(1);

						if(op1 instanceof Variable || op1 instanceof VInteger) {
							if(op1 instanceof Variable) {
								text.append(((Variable)op1).getName().substring(1)).append(" ");
							}
							else if(op1 instanceof VInteger) {
								text.append(((VInteger)op1).getName().substring(1)).append(" ");
							}

						}
						else if (op1 instanceof Integer) {

							text.append((int)op1).append(" ");
						}

						if(op2 instanceof Variable || op2 instanceof VInteger) {
							if(op1 instanceof Variable) {
								text.append(((Variable)op2).getName().substring(1));
								if(in && count < size) {
																text.append(" in ");
								}
								text.append(" \n");
							}
							else if(op2 instanceof VInteger) {
								text.append(((VInteger)op2).getName().substring(1));
								// if(in && count < size){
								//  text.append(" in ");
								// }
								text.append(" \n");
							}

						}
						else if (op2 instanceof Integer) {
							text.append((int)op2);
							// if(in && count < size){
							//   text.append(" in ");
							//  }
							text.append(" \n");
						}
}

                else if (instr instanceof InstructionASSIGN) {

						text.append("\tlet ");

						Object op1= ((InstructionASSIGN)instr).operands.get(0);
						Object op2= ((InstructionASSIGN)instr).operands.get(1);

						if(op1 instanceof Variable || op1 instanceof VInteger) {
							if(op1 instanceof Variable) {
								text.append(((Variable)op1).getName().substring(1)).append(" = ");
							}
							else if(op1 instanceof VInteger) {
								text.append(((VInteger)op1).getName().substring(1)).append(" = ");
							}

						}


						if(op2 instanceof Variable ) {

							text.append(((Variable)op2).getName().substring(1));
							if(in && count < size) {
								text.append(" in ");
							}
							text.append("\n");
						}

						else if (op2 instanceof VInteger) {
							text.append(((VInteger)op2).getName().substring(1));
							if(in && count < size) {
															text.append(" in ");
							}
							text.append("\n");
						}

						else if (op2 instanceof Integer) {
														//System.out.print(op2);
									text.append((Integer)op2).append(" in\n");
						}

						else if (op2 instanceof Instruction) {

							if(op2 instanceof InstructionADD) {

								Object op4= ((InstructionADD)op2).operands.get(0);
								Object op3= ((InstructionADD)op2).operands.get(1);

								arithmetic("add", op4, op3, (InstructionADD)op2, in, count,size);

							}
							else if(op2 instanceof InstructionADD) {

								Object op4= ((InstructionSUB)op2).operands.get(0);
								Object op3= ((InstructionSUB)op2).operands.get(1);

								arithmetic("sub", op4, op3, (InstructionSUB)op2, in, count,size);

							}

							else if(op2 instanceof InstructionMULT) {

								Object op4= ((InstructionMULT)op2).operands.get(0);
								Object op3= ((InstructionMULT)op2).operands.get(1);

								arithmetic("mul", op4, op3, (InstructionMULT)op2, in, count,size);

							}




												}

				}
				else if (instr instanceof InstructionCALL) {

					InstructionCALL inst = (InstructionCALL)instr;

					List<Object> params = inst.getParams();
					String return_reg = inst.getReturn();
					int num_params=params.size();

					if(inst.getFname().equals("print_int")) {
													//Parameter param = (Parameter)params.get(0);
						text.append("\tcall _min_caml_print_int ");
						if(params.size() != 0) {

								if(!(params.get(0) instanceof Integer)) {

									Variable param = (Variable)params.get(0);
									text.append(param.getName().substring(1));
									if(in) {
																	// if(count < size){
																	//  text.append(" in ");
																	// }

									}
									text.append("\n");
									//assign("r0" , param.getRegister().getName());
					               }
								else{
									text.append((int)params.get(0));
									//  if(in && count < size){
									//  text.append(" in ");
									// }
									text.append("\n");

								}

						}
                    }

				}
				else{
					System.out.println("Instruction Not Supported\n");
				}
			}


			}
			return text.append("\n\n");
}



public void arithmetic(String mnemonic, Object op4, Object op3, Object inst, boolean in, int count, int size){

			Object o;
			if(mnemonic=="add") {

				o = (InstructionADD)inst;
			}

			else if(mnemonic == "sub") {

				o = (InstructionSUB)inst;
		
            }
			else if(mnemonic =="mul") {
				o = (InstructionMULT)inst;
			}

			text.append(" ").append(mnemonic).append(" ");

			if(op4 instanceof Variable || op4 instanceof VInteger) {
				if(op4 instanceof Variable) {
					text.append(((Variable)op4).getName().substring(1)).append(" ");
				}
				else if(op4 instanceof VInteger) {
					text.append(((VInteger)op4).getName().substring(1)).append(" ");
				}

			}
			else if (op4 instanceof Integer) {

				text.append((int)op4).append(" ");
			}

			if(op3 instanceof Variable || op3 instanceof VInteger) {
				if(op3 instanceof Variable) {
					text.append(((Variable)op3).getName().substring(1));

					text.append(" in ");

					text.append("\n");
				}
				else if(op3 instanceof VInteger) {
					text.append(((VInteger)op3).getName().substring(1));
					text.append(" in ");

					text.append(" \n");
				}

			}
			else if (op3 instanceof Integer) {
				text.append((int)op3);
				if(in && count < size) {
					text.append(" in ");
				}
				text.append("\n");
			}
        }



}
