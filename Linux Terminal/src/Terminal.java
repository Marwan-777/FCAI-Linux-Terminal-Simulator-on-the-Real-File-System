



import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
    private Path path;
    private String currentlocation;

    public Terminal(){
        path = Paths.get("C:\\Users");
        currentlocation = String.valueOf(path.toAbsolutePath());
    }

    public void setcurrentlocation(String loc) {
        path = Paths.get(loc);
        currentlocation = String.valueOf(path.toAbsolutePath());
    }

    public void ls() {
        File file = new File(currentlocation);
        String [] list = file.list();
        Arrays.sort(list);
        for(String string : list) {
            System.out.println(string);
        }
    }
    public void pwd() {
        System.out.println(currentlocation);
    }

    void deleteDir( File dir) {				// used for deleting non empty directories in rm
        File[] files = dir.listFiles();
        if(files != null) {
            for (final File leaf : files) {
                deleteDir(leaf);
            }
        }
        dir.delete();
    }
    public void rm (String name , String loc) {
        if(!loc.equals("") && name.equals("-r")) {			// therefore removing a directory not a file
            File  file = new File(currentlocation + "\\" + loc) ;
            if(file.isDirectory()) {							// to avoid -r filename(not directory)
                if(file.exists()) {
                    deleteDir(file);
                    System.out.println("Directory is removed successfuly ");
                }else {
                    System.out.println("directory doesn't exist !!");
                }
            }else {
                System.out.println("unvalid arguments for this command");
                System.out.println("To remove a file type rm filename (or path) ");
                System.out.println("To remove drectory type rm -r directory name (or path)");
            }
        }else {								// therefore removing a file
            File  file = new File(currentlocation + "\\" + name) ;
            if(!file.isDirectory()) {
                if(file.exists()) {
                    file.delete();
                    System.out.println("File is removed successfuly");
                }else {
                    System.out.println("This file is not found !!");
                }
            }else {
                System.out.println("undefined arguments for this command !!");
                System.out.println("to remove a directory use the argument -r before directory name (or path) ");
            }
        }


    }

    public void cd (String loc){
        int len = loc.length();
        if (len<=2){
            if (loc.equals("..")){
                path = path.getParent();
                currentlocation = String.valueOf(path.toAbsolutePath());
                System.out.println(currentlocation);
            }else if(loc.equals("/")){
                path = Paths.get("C:\\");
                currentlocation = String.valueOf(path.toAbsolutePath());
                System.out.println(currentlocation);
            }else if(loc.equals("~")){
                path = Paths.get("C:\\Users");
                currentlocation = String.valueOf(path.toAbsolutePath());
                System.out.println(currentlocation);
            }
        } else{
            if (loc.contains("~\\")){
                String subPath = loc.substring(2);
                path = Paths.get("C:\\Users" + subPath);
                currentlocation = String.valueOf(path.toAbsolutePath());
                System.out.println(currentlocation);
            }else if(loc.contains("/\\")) {
                String subPath = loc.substring(1);
                path = Paths.get("C:\\" + subPath);
                currentlocation = String.valueOf(path.toAbsolutePath());
                System.out.println(currentlocation);
            }else if(loc.contains(":\\")){
                path = Paths.get(loc);
                currentlocation = String.valueOf(path.toAbsolutePath());
            } else if (loc.contains("\\")){
                path = Paths.get(currentlocation + "\\" + loc);
                currentlocation = String.valueOf(path.toAbsolutePath());
            }else{
                path = Paths.get(currentlocation + "\\" + loc);
                currentlocation = String.valueOf(path.toAbsolutePath());
            }
        }
    }

    public Object date(){
        return LocalDateTime.now();
    }

    public void cp(String first , String second) throws IOException {
        if(first.contains(".txt") && second.contains(".txt")){
            BufferedReader br = new BufferedReader(new FileReader(currentlocation + "\\" + first));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            br.close();
            FileWriter fw = new FileWriter(currentlocation + "\\" + second);
            fw.write(fileAsString);
            fw.close();
        }else if(first.contains(".txt") && second.contains(":\\")){
            BufferedReader br = new BufferedReader(new FileReader(second + "\\" + first));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            br.close();
            FileWriter fw = new FileWriter(second + "\\" + first);
            fw.write(fileAsString);
            fw.close();
        }else if(first.contains(".txt") && second.contains("\\")){
            BufferedReader br = new BufferedReader(new FileReader(currentlocation + "\\" + second + "\\" + first));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            br.close();
            FileWriter fw = new FileWriter(second + "\\" + first);
            fw.write(fileAsString);
            fw.close();
        }
    }

    public void operator (String s) throws IOException {
        if (s.contains(">>")){
            int index = s.indexOf(">>");
            String sub1 = s.substring(0, index-1);
            String sub2 = s.substring(index+3);
            BufferedReader br = new BufferedReader(new FileReader(currentlocation + "\\" + sub1));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            br.close();
            FileWriter fw = new FileWriter(currentlocation + "\\" + sub2,true);
            BufferedWriter out = new BufferedWriter(fw);
            out.write("\n" + fileAsString);
            out.close();
            fw.close();
        } else if(s.contains(">")){
            int index = s.indexOf(">");
            String sub1 = s.substring(0, index-1);
            String sub2 = s.substring(index+2);
            System.out.println(sub1);
            System.out.println(sub2);
            BufferedReader br = new BufferedReader(new FileReader(currentlocation + "\\" + sub1));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            br.close();
            FileWriter fw = new FileWriter(currentlocation + "\\" + sub2);
            fw.write(fileAsString);
            fw.close();
        }
    }
    public void cat (String loc) throws IOException {
        if (!loc.contains("\\")){
            String temp = loc;
            loc = currentlocation + "\\" + temp;
        }
        File path;
        path = new File(loc).getAbsoluteFile();
        if (path.exists())
        {
            BufferedReader BufferedReader = new BufferedReader(new FileReader(path));
            String out;
            while ((out = BufferedReader.readLine()) != null)
                System.out.println(out);
        }
        else
            System.out.println(path + "path is not exist ");
    }
    public void cat(String file1, String file2)throws FileNotFoundException, IOException{
        if (!file1.contains("\\")){
            String temp = file1;
            file1 = currentlocation + "\\" + temp;
        }
        File path;
        path = new File(file1).getAbsoluteFile();
        if (path.exists())
        {
            BufferedReader BufferedReader = new BufferedReader(new FileReader(path));
            String out;
            while ((out = BufferedReader.readLine()) != null)
                System.out.println(out);
        }
        else
            System.out.println(path + "path is not exist ");

        if (!file2.contains("\\")){
            String temp = file2;
            file2 = currentlocation + "\\" + temp;
        }
        path = new File(file2).getAbsoluteFile();
        if (path.exists())
        {
            BufferedReader BufferedReader = new BufferedReader(new FileReader(path));
            String out;
            while ((out = BufferedReader.readLine()) != null)
                System.out.println(out);
        }
        else
            System.out.println(path + "path is not exist ");
    }
    public void mv(String sourcepath, String destinationpath) throws IOException, NoSuchFileException {
        if (!sourcepath.contains(":\\")) {
            path = Paths.get(currentlocation + "\\" + sourcepath);
            sourcepath = String.valueOf(path.toAbsolutePath());
        }else{
            path = Paths.get(sourcepath);
            sourcepath = String.valueOf(path.toAbsolutePath());
        }
        if (!destinationpath.contains(":\\")) {
            path = Paths.get(currentlocation + "\\" + destinationpath);
            destinationpath = String.valueOf(path.toAbsolutePath());
        }else{
            path = Paths.get(destinationpath);
            destinationpath = String.valueOf(path.toAbsolutePath());
        }

        File source = new File(sourcepath);
        File destination = new File(destinationpath);
        if (!source.exists()) {
            throw new NoSuchFileException(source.getAbsolutePath(), (String)null, "Not exist.");
        } else if (destination.isFile()) {
            throw new IOException("Unable to move this file");
        } else {
            if (!destination.exists()) {
                Files.move(source.toPath(), source.toPath().resolveSibling(destination.getName()));
            } else {
                Files.move(source.toPath(), destination.toPath().resolve(source.toPath().getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }

        }
    }
    static void Help() {
        System.out.println("date: Displays system date and time");
        System.out.println("help: List all commands and functionalities");
        System.out.println("args: List all commands arguments");
        System.out.println("clear: Clears the console");
        System.out.println("cd: Changes current working directory");
        System.out.println("ls: List all contents of current directory");
        System.out.println("man ls: Displays possible arguments and how to deal with command ls");
        System.out.println("pwd: Displays the absolute path of current directory");
        System.out.println("cp: Copies files");
        System.out.println("mv: Moves files");
        System.out.println("mkdir: Creates a new directory");
        System.out.println("rmdir: Deletes a directory");
        System.out.println("rm: Deletes a file");
        System.out.println("cat: Displays contents of a file and concatenates files and display output");
        System.out.println("more: Let us display and scroll down the output in one direction only");
        System.out.println("less: Like more but more enhanced");
        System.out.println("exit: Terminates the program");
    }

    public void mkdir(String dirname) {

        Path path = Paths.get(currentlocation+'/'+dirname);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void rmdir(String dirname) {
        File x= new File (currentlocation+'/'+dirname);
        String[] ff=x.list();
        Path path = Paths.get(currentlocation+'/'+dirname);


        if(x.isDirectory() && ff.length==0 )
        {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        else {
            System.out.println("directory is not empty ");
        }
    }

    public void more(String filename) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(currentlocation+'/'+filename));
            String line = reader.readLine();
            int i=0;
            while (line != null && i<10) {
                System.out.println(line);
                // read next line
                line = reader.readLine();
                i++;
                if(i==10) {
                    System.out.println("press x to continue & (anything to stop)");
                    char choice;
                    Scanner scan = new Scanner(System.in);
                    choice = scan.next().charAt(0);
                    if(choice=='x')
                        i=0;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void args(String cmd)
    {
        switch(cmd)
        {
            case "clear" :
                System.out.println("No arguments\n");
                break;
            case "cd" :
                System.out.println("arg1: destiniationDirectory\n");
                break;
            case "ls" :
                System.out.println("No arguments\n");
                break;
            case "cp" :
                System.out.println("arg1: sourcePath, arg2: destinationPath\n");
                break;
            case "mv" :
                System.out.println("arg1: sourcePath, arg2: destinationPath\n");
                break;
            case "rm" :
                System.out.println("arg1: fileName\n");
                break;
            case "mkdir" :
                System.out.println("arg1: directoryName\n");
                break;
            case "rmdir" :
                System.out.println("arg1: directoryName\n");
                break;
            case "cat" :
                System.out.println("arg1: filePath, arg2: filePath \n");
                break;
            case "more" :
                System.out.println("No arguments\n");
                break;
            case "pwd" :
                System.out.println("No arguments");
                break;
            case "args" :
                System.out.println("arg1: commandName");
                break;
            case "date" :
                System.out.println("No arguments");
                break;
            case "exit" :
                System.out.println("No arguments");
                break;
            default:
                System.out.println("You entered wrong command");
        }
    }

    public void clear(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
