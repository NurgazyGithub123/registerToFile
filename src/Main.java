import javax.imageio.IIOException;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static String[][] customers = new String[10][5];
    public static int counter = 0;
    public static void main(String[] args) {
        run();
    }
    public static void run(){
        uploadDate();
        boolean loopContinius = true;

        while (loopContinius) {
            try {
                System.out.print("Command: ");

                switch (readUserMessage()) {
                    case "PRINT":
                        print();
                        break;
                    case "REGISTER":
                        System.out.println("Make a recording: ");
                        register(customers);
                        break;
                    case "COUNT":
                        System.out.println("count of customers: " + counter);
                        break;
                    case "REMOVE":
                        break;
                    case "EXIT":
                        saveDate();
                        loopContinius = false;
                        break;
                }
            }catch (RuntimeException ex){
                ex.printStackTrace();
                System.out.println("");
            }
        }
    }

    public static void register(String[][] customers){

        System.out.print("First Name: ");
        customers[counter][0] = scan.nextLine();

        System.out.print("Last Name: ");
        customers[counter][1] = scan.nextLine();

        System.out.print("DOB Name: ");
        customers[counter][2] = scan.nextLine();

        System.out.print("Email Name: ");
        customers[counter][3] = scan.nextLine();

        System.out.print("Phone number: ");
        customers[counter][4] = scan.nextLine();

        counter++;
    }

    public static void uploadDate(){
        System.out.println("uploading File");
        String pathToFale = "C:\\Users\\ndyykanbaev\\Desktop\\My Projects\\register\\data\\data.txt";
        File file = new File(pathToFale);

        try {

            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()){
                customers[counter][0] = scannerFile.next();
                customers[counter][1] = scannerFile.next();
                customers[counter][2] = scannerFile.next();
                customers[counter][3] = scannerFile.next();
                customers[counter][4] = scannerFile.nextLine();

                counter++;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(){

        for (int i = 0; i < counter; i++) {
            System.out.println(Arrays.toString(customers[i]));
        }
    }

    public static String readUserMessage(){
        String command = scan.nextLine().trim().toUpperCase();

        switch (command){
            case "PRINT":
            case "REGISTER":
            case "COUNT":
            case "REMOVE":
            case "EXIT":
                return command;
            default:
                throw new RuntimeException("invalid command: " + command);
        }
    }

    public static void saveDate(){
        String pathToFale = "C:\\Users\\ndyykanbaev\\Desktop\\My Projects\\register\\data\\data.txt";
        File file = new File(pathToFale);

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(new FileOutputStream(file,true));

            for (int i = 0; i < counter; i++) {
                out.print(customers[i][0] + " ");
                out.print(customers[i][1] + " ");
                out.print(customers[i][2] + " ");
                out.print(customers[i][3] + " ");
                out.println(customers[i][4] + " ");
            }
            out.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}