/**
 * Loai Gamal Mohamed 20180206
 * Ahmed Kadry Abd El-Shafy 20180018
 * Mohamed Sayed Hassan 20180224
 * Marwan Yasser Mostafa 20180270
 */

import java.io.IOException;
import java.util.Scanner;
import java.lang.ClassNotFoundException;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner (System.in);
        String input ;
        Parser prs = new Parser();
        Terminal run = new Terminal();


        Scanner sc = new Scanner(System.in);
        do {

            System.out.println("  >>  ");
            input = sc.nextLine();
            if (input.contains(">") || input.contains(">>")){
                run.operator(input);
            }
            else if(prs.parse(input) == false && !input.equals("exit") ) {
                System.out.println("command not found !");
                continue;
            }else {
                switch(prs.getcmd()) {      	// calls a function from terminal (run) class according to the cmd from parser (prs)
                    case "ls":
                        run.ls(); 		// getlocation() returns the current location of the shell
                        break;
                    case "pwd":
                        run.pwd();
                        break;
                    case "rm":
                        run.rm(prs.arguments[0],prs.arguments[1]);
                        break;
                    case "cd":
                        run.cd(prs.arguments[0]);
                        break;
                    case "cp":
                        run.cp(prs.arguments[0] , prs.arguments[1]);
                        break;
                    case "date":
                        System.out.println(run.date());
                        break;
                    case "cat" :
                        if(prs.arguments[1].equals(""))
                            run.cat(prs.arguments[0]);
                        else
                            run.cat(prs.arguments[0],prs.arguments[1]);
                        break;
                    case "mv" :
                        run.mv(prs.arguments[0] , prs.arguments[1]);
                        break;
                    case "help":
                        run.Help();
                        break;
                    case "mkdir":
                        run.mkdir(prs.arguments[0]);
                        break;
                    case "rmdir":
                        run.rmdir(prs.arguments[0]);
                        break;
                    case "more":
                        run.more(prs.arguments[0]);
                        break;
                    case "clear":
                        run.clear();
                        break;
                    case "args":
                        run.args(prs.arguments[0]);
                    default:
                        break;
                }
            }

        }while(!input.equals("exit"));
        System.out.println("shell terminated !");
    }
}

