import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Ihm {
String input_file, output_file;
Boolean given_output, typecheck_only, parse_only, output_asml, ast, knorm, alpha_conversion, reduction, closure_conversion, arm, translation, register, complete;

public Ihm(String options[]){
        if (options.length == 0) {
                print_help();
        }
        input_file =
        output_file = "";
        given_output =
          typecheck_only =
              parse_only =
             output_asml =
                     ast =
                   knorm =
        alpha_conversion =
               reduction =
      closure_conversion =
                     arm =
                complete =
             translation =
                register = false;


        for (int i = 0; i < options.length; i++)
        {
                switch(options[i]) {
                case "-o":
                        if (i < options.length - 1) {
                                given_output = true;
                                if (i == options.length - 2){
                                  input_file = options[options.length-3];
                                }
                                output_file = options[i+1];
                        } else {
                                System.out.println("If you specify -o, you must also specified the name of the output file.");
                                System.exit(1);
                        }
                        break;
                case "-h": // TO DO : Pour faire propre, il faudrait jeter une exception :p
                        print_help();
                case "-v":
                        System.out.println("v (GNU coreutils) 1e-1000");
                        System.out.println("Copyright © 2017 Free Software Foundation, Inc.");
                        System.out.println("License GPLv3+ : GNU GPL version 3 ou ultérieure");
                        System.exit(0);
                        break;
                case "-t":
                        typecheck_only = true;
                        break;
                case "-p":
                        parse_only = true;
                        break;
                case "-asml":
                        output_asml = true;
                        break;
                case "--ast":
                        ast = true;
                        break;
                case "--knorm":
                        knorm = true;
                        break;
                case "--alpha_conversion":
                        alpha_conversion = true;
                        break;
                case "--reduction":
                        reduction = true;
                        break;
                case "--closure_conversion":
                        closure_conversion = true;
                        break;
                case "--arm":
                        arm = true;
                        break;
                case "--complete":
                        complete = true;
                case "--register":
                        register = true;
                case "--translation":
                        translation = true;
                default:
                        input_file = options[i];
                }
        }
}

private void print_help(){
        System.out.println("Help on the Mincalmc Programm of -LesConsPileurs-.");
        System.out.println("mincalmc - compile ml into arm assembly language.");
        System.out.println("use : ./mincalmc input_file [OPTIONS]");
        System.out.println("-o : output file (created if doesn't exist, overwritted otherwise).");
        System.out.println("-h : display of help (default if no argument)");
        System.out.println("-v : display of the current version (no need of input_file)");
        System.out.println("-t : type check only");
        System.out.println("-p : parse only");
        System.out.println("-asml : output ASML");
        System.out.println("-my-opt : you can add personal options (optimizations, etc.)");
        System.exit(0);
      }
}
