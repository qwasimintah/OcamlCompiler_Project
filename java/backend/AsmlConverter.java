package backend;

import java.util.*;
import java.io.*;
import backend.variables.*;
import backend.functions.*;
import backend.instructions.*;
import backend.registers.*;
import backend.exceptions.*;
import backend.booleans.*;



public class AsmlConverter {

	StringBuilder text = new StringBuilder();
	int temp_counter =0;

	public String get_temp_var (){
		temp_counter++;
		return "temp"+String.valueOf(temp_counter);
	}

	public String get_current_var (){
		return "temp"+String.valueOf(temp_counter);
	}


	public String get_parsable_name(String name){

			if (name.substring(0,1).equals("?")){
				return name.substring(1);
			}

			return name;
	}


	public StringBuilder convert(List<Function> funs){

				
		for(Function fun : funs) {

				List<Variable> arguments = fun.getArguments();
				List<Instruction> intr = fun.getInstructions();
				HashSet<Variable> locals = fun.getVariables();
	            String fname = fun.getName();
	            if(fname=="main"){
	                text.append("let _ = \n");
	            }

	            else {
	                text.append("let _").append(fname).append(" ");
	                for(Variable v: arguments){
	                	text.append(get_parsable_name(v.getName())).append(" ");
	                }
	                text.append(" = \n");
	            }

				//process all intructions of functions
				//int size= locals.size();
				int size = intr.size();
				boolean in = false;
				int count =0;
				


				for (Instruction instr : intr) {
						count++;
						if(count >= 1 && count < size) {
							in =true;
						}

					if(instr instanceof InstructionADD) {
							if(count == size){
								text.append("\tadd ");
							}
							else {
								  text.append(" \tlet ").append(get_temp_var()).append (" = add ");
							}

							Object op1= ((InstructionADD)instr).operands.get(0);
							Object op2= ((InstructionADD)instr).operands.get(1);

							if(op1 instanceof Variable || op1 instanceof VInteger) {
								if(op1 instanceof Variable) {
									text.append(get_parsable_name(((Variable)op1).getName())).append(" ");
									
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
									
										text.append(get_parsable_name(((Variable)op2).getName()));
									// if(in && count < size){
									//  text.append(" in ");
									// }
										if(count != size){
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

	                else if (instr instanceof InstructionNOTHING){


	                }

	                else if (instr instanceof InstructionSUB) {

						text.append("\tsub ");
						Object op1= ((InstructionSUB)instr).operands.get(0);
						Object op2= ((InstructionSUB)instr).operands.get(1);

						if(op1 instanceof Variable || op1 instanceof VInteger) {
							if(op1 instanceof Variable) {
								text.append(get_parsable_name(((Variable)op1).getName())).append(" ");
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
								text.append(get_parsable_name(((Variable)op2).getName()));
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
									text.append(get_parsable_name(((Variable)op1).getName())).append(" ");
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
									text.append(get_parsable_name(((Variable)op2).getName()));
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

							

							Object op1= ((InstructionASSIGN)instr).operands.get(0);
							Object op2= ((InstructionASSIGN)instr).operands.get(1);

							if(op2 !=null){
								text.append("\tlet ");
							}

							

							if(op1 instanceof Variable || op1 instanceof VInteger) {
								if(op1 instanceof Variable && op2 !=null) {
									text.append(get_parsable_name(((Variable)op1).getName())).append(" = ");
								}
								else if(op1 instanceof VInteger) {
									text.append(((VInteger)op1).getName().substring(1)).append(" = ");
								}

							}


							if(op2 instanceof Variable ) {

								text.append(get_parsable_name(((Variable)op2).getName()));
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

								else if(op2 instanceof InstructionNOTHING){


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
							if(count == size){
							text.append("\tcall _min_caml_print_int ");
							}
							else{
								text.append("\tlet ").append(get_temp_var()).append(" = ");
								text.append("call _min_caml_print_int ");
							}
							if(params.size() != 0 ) {

									if(!(params.get(0) instanceof Integer)) {

										Variable param = (Variable)params.get(0);
										if(param !=null){
										text.append(param.getName().substring(1));
										}
										else{
											text.append(get_current_var());
										}
										if(count != size){
											text.append(" in ");
										}
										text.append("\n");
										//assign("r0" , param.getRegister().getName());
						               }
									else{
										text.append((int)params.get(0));
										//  if(in && count < size){
										//  text.append(" in ");
										// }

										if(count != size){
											text.append(" in ");
										}

										text.append("\n");

									}

							}



	                    }
	                    else {

	                    	  

	                    	if(count == size){
							text.append("\tcall _").append(inst.getFname()).append(" ");
							}
							else{
								text.append("\tlet ").append(get_temp_var()).append(" = ");
								text.append("call _").append(inst.getFname()).append(" ");
							}

	                    	  for (int i = 0; i<num_params; i++){
	                    	  		Variable p = (Variable)params.get(i);
	                    	  		if(p!=null){
	                    	  			text.append(p.getName()).append(" ");
	                    	  		}
	                    	  		else{
	                    	  			text.append(get_current_var());
	                    	  		}
	                    	  }
	                    	  if(count != size){
								text.append(" in ");
							  }
	                    	  text.append("\n");


	                    

	                    }



					}

	                else if (instr instanceof InstructionIF){

	                        InstructionIF inst = (InstructionIF) instr;

	                        Function then_branch = inst.branch_then;
	                        Function else_branch = inst.branch_else;

	                        BooleanExpression exp = inst.cond.getExp();

	                        String operand1="";
	                        String operand2 = "";
	                        String offset1 = "";


	                        if(exp instanceof BooleanEQ) {
	                                Variable op1 = (Variable)(((BooleanEQ)exp).operands.get(0));
	                                Variable op2 = (Variable)(((BooleanEQ)exp).operands.get(1));
	                                text.append("\tif ").append(get_parsable_name(op1.getName())).append(" = ").append(op2.getName().substring(1)).append(" then ( \n");

	                        }

	                        else if (exp instanceof BooleanLE) {

	                                Variable op1 = (Variable)(((BooleanLE)exp).operands.get(0));
	                                Variable op2 = (Variable)(((BooleanLE)exp).operands.get(1));

	                                text.append("\tif ").append(get_parsable_name(op1.getName())).append(" <= ").append(op2.getName().substring(1)).append(" then ( \n");
	                                
	                        }

	                        else if (exp instanceof BooleanTrue) {

	                                text.append("\tif ").append(" true ").append(" then ( \n");

	                        }

	                        else if(exp instanceof BooleanFalse) {
	                                text.append("\tif ").append(" false ").append(" then ( \n");
	                        }
	                        else{

	                                System.out.println("Something crazy happenned");
	                        }


	                        // generate branch then 

	                        generate_branch(then_branch,"");

	                        // generate branch else 

	                        generate_branch(else_branch, "else");

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





	public void generate_branch(Function fun, String type){

		List<Instruction> intr = fun.getInstructions();
		int size = intr.size();
		boolean in = false;
		int count =0;

		if (type == "else"){

			text.append("\telse (\n");
		}

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

		        else if (instr instanceof InstructionIF){

		                InstructionIF inst = (InstructionIF) instr;

		                Function then_branch = inst.branch_then;
		                Function else_branch = inst.branch_else;

		                BooleanExpression exp = inst.cond.getExp();

		                String operand1="";
		                String operand2 = "";
		                String offset1 = "";


		                if(exp instanceof BooleanEQ) {
		                        Variable op1 = (Variable)(((BooleanEQ)exp).operands.get(0));
		                        Variable op2 = (Variable)(((BooleanEQ)exp).operands.get(1));

		                        text.append("\tif ").append(op1.getName().substring(1)).append(" = ").append(op2.getName().substring(1)).append(" then ( \n");


		                }

		                else if (exp instanceof BooleanLE) {

		                        Variable op1 = (Variable)(((BooleanLE)exp).operands.get(0));
		                        Variable op2 = (Variable)(((BooleanLE)exp).operands.get(0));

		                        text.append("\tif ").append(op1.getName().substring(1)).append(" <= ").append(op2.getName().substring(1)).append(" then ( \n");

		                        
		                }

		                else if (exp instanceof BooleanTrue) {

		                        text.append("\tif ").append(" true ").append(" then ( \n");


		                }

		                else if(exp instanceof BooleanFalse) {
		                        text.append("\tif ").append(" false ").append(" then ( \n");
		                }
		                else{

		                        System.out.println("Something crazy happenned");
		                }


		        }

		        else if (instr instanceof InstructionNOTHING){
					System.out.println("NOTHING CLASS");
					System.out.println(instr);
					InstructionNOTHING i = (InstructionNOTHING)instr;
					VInteger nothing = (VInteger)(i.x);
					//System.out.println(nothing.getName().substring(0,5));
					if(nothing.getName().substring(0,6).equalsIgnoreCase("tmpVar")){
						text.append("\t").append(nothing.getValue()).append("\n");
					}

					else {
						text.append("\t").append(nothing.getName()).append("\n");
					}
				}
		        else {
		        	System.out.println("Instruction Not Supported");

		        }
		    }

		text.append("\t)\n");

	}	



}



