

import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {
    Path path;
    String[] arguments = new String[2];
    String cmd ;
    String [] available_cmd = {"pwd","cd","ls","cp","cat","more","mkdir","rmdir","mv","rm","help","clear","date","args"};
    String [][] available_args = new String [14][3] ;
    {
        available_args [0][0] = "0" ;
        available_args [0][1] = "" ;
        available_args [0][2] = "" ;

        available_args [1][0] = "location" ;
        available_args [1][1] = "" ;
        available_args [1][2] = "" ;

        available_args [2][0] = "0" ;
        available_args [2][1] = "" ;
        available_args [2][2] = "" ;

        available_args [3][0] = "location" ;
        available_args [3][1] = "location" ;
        available_args [3][2] = "" ;

        available_args [4][0] = "filename" ;
        available_args [4][1] = "filename" ;
        available_args [4][2] = "0" ;

        available_args [5][0] = "filename" ;
        available_args [5][1] = "" ;
        available_args [5][2] = "" ;

        available_args [6][0] = "location" ;
        available_args [6][1] = "" ;
        available_args [6][2] = "" ;

        available_args [7][0] = "location" ;
        available_args [7][1] = "" ;
        available_args [7][2] = "" ;

        available_args [8][0] = "location" ;
        available_args [8][1] = "location" ;
        available_args [8][2] = "" ;

        available_args [9][0] = "filename" ;
        available_args [9][1] = "location" ;
        available_args [9][2] = "0" ;

        available_args [10][0] = "0" ;
        available_args [10][1] = "" ;
        available_args [10][2] = "" ;

        available_args [11][0] = "0" ;
        available_args [11][1] = "" ;
        available_args [11][2] = "" ;

        available_args [12][0] = "0" ;
        available_args [12][1] = "" ;
        available_args [12][2] = "" ;

        available_args [13][0] = "command" ;
        available_args [13][1] = "0" ;
        available_args [13][2] = "" ;

    }

    public Parser(){
        path = Paths.get("C:\\Users");
    }

    String extractFirst(String x) {
        String answer = "";
        for(int i =0 ; i<x.length(); i++) {
            if(x.charAt(i) != ' ') {
                answer = answer + x.charAt(i);
            }else {
                break;
            }
        }
        return answer ;
    }


    public boolean parse(String input) {
        boolean cmdfound = false ;
        boolean arg = false ;
        String args ;		// stores all args
        String firstArg ;	// extract the first arg
        int cmdlocation =0 ; // any random number for initialization
        cmd = extractFirst(input);					// extract the first word ( the word before first space)
        for(int i =0 ; i<available_cmd.length; i++) {
            if(cmd.equals(available_cmd[i] ) ) {
                cmdfound = true;
                cmdlocation = i ;
                break;
            }
        }
        if(cmdfound) {												// if cmd exist check args
            // reset args
            arguments[0] = "" ;
            arguments[1] = "" ;
            if(input.length() - cmd.length()  < 2) {					// user didn't enter any args , input and cmd extracted nearly equal in length
                arg = false ;
                args = "" ;										// just to avoid errors
            }else {
                args = input.substring(cmd.length()+1,input.length());
                arg = true ;
            }


            if(available_args [cmdlocation][0] .equals("0") ) {		// meaning it doesn't need args
                if(!args.equals("")) {							// user entered args
                    System.out.println("This command doesn't need arguments !");
                    return false ;
                }else {
                    return true ;
                }
            }else {
                if(arg == false) {							// means that it needs args but user didn't enter
                    System.out.println("too few arguments for this command !!");
                    return false ;
                }
                if(available_args [cmdlocation][2].equals("0")) {  		// first arg  or  first & sec
                    if(args.indexOf(' ') == -1) {						// there is no space , therefore one arg
                        arguments[0] = args ;
                        return true ;
                    }else {												// there is space , therefore 2 args
                        firstArg = extractFirst(args);
                        arguments[0] = firstArg ;
                        arguments[1] = args.substring(firstArg.length()+1 , args.length());
                        return true ;

                    }
                }else {
                    if(available_args[cmdlocation][1] .equals("0")) {  // only one arg allowd , either first or third
                        if(args.indexOf(' ') != -1) { 		// it contains space , user enters more than one arg , not allowed
                            System.out.println(" too much arguments for this command !!");
                            return false ;
                        }else {
                            arguments[0]= args ;
                            return true ;
                        }
                    }else {
                        // number of arguments depends
                        if(available_args[cmdlocation][1].isEmpty()) {			// needs one arg , since two is empty
                            if(args.indexOf(' ') != -1) {  						// user enter too much args
                                System.out.println("too much args for this command !!");
                                return false ;
                            }else {												// enters one arg
                                arguments [0] = args ;
                                return true ;
                            }
                        }else {													// needs two args
                            firstArg = extractFirst(args);
                            arguments[0] = firstArg ;
                            arguments[1] = args.substring(firstArg.length()+1 , args.length());
                            return true ;
                        }
                    }
                }
            }

        }
        return false ;   // since it doesn't enter the condition ( if it did it will return a value )
    }
    public String getcmd() {
        return cmd ;
    }
    public String [] getargs() {
        return arguments ;
    }

}
