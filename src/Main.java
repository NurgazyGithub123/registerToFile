import javax.imageio.IIOException;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static Customer[] customers = new Customer[10];
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
                        register();
                        break;
                    case "COUNT":
                        System.out.println("count of customers: " + counter);
                        break;
                    case "REMOVE":
                        System.out.print("remove customers ID: ");
                        int index = scan.nextInt() - 1;
                        scan.nextLine();
                        remove(index);
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

    public static void register(){

        Customer customer = new Customer();

        System.out.print("First Name: ");
        customer.firstName = scan.nextLine();

        System.out.print("Last Name: ");
        customer.lastName = scan.nextLine();

        System.out.print("DOB Name: ");
        customer.dateOfBirth = LocalDate.parse(scan.nextLine());

        System.out.print("Email Name: ");
        customer.email= scan.nextLine();

        System.out.print("Phone number: ");
        customer.phoneNumber = scan.nextLine();

        customers[counter] = customer;

        counter++;
    }

    public static void remove(int index){
        counter--;

        customers[index] = customers[counter];
        customers[counter] = null;

        // доработать, сместить по порядку
    }

    public static void uploadDate(){
        System.out.println("uploading File");

        String pathToFale = "C:\\Users\\ndyykanbaev\\Desktop\\My Projects\\register\\data\\data.txt";
        File file = new File(pathToFale);

        try {
            Scanner scannerFile = new Scanner(file);

            while (scannerFile.hasNextLine()){

                Customer customer = new Customer();
                customer.firstName = scannerFile.next();
                customer.dateOfBirth = LocalDate.parse(scannerFile.next());
                customer.lastName = scannerFile.next();
                customer.email = scannerFile.next();
                customer.phoneNumber = scannerFile.nextLine();

                customers[counter] = customer;
                counter++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(){

        for (int i = 0; i < counter; i++) {
            System.out.println((customers[i].toString()));
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
                Customer customer = new Customer();
                out.print(customers[i].firstName);
                out.print(customers[i].lastName);
                out.print(customers[i].dateOfBirth);
                out.print(customers[i].email);
                out.println(customers[i].phoneNumber);
            }
            out.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}