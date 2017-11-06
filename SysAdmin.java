package Project3GitDoneRight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author zachcaton
 */
public class SysAdmin extends Employee{


    private ArrayList<Employee> users = new ArrayList<>();
    private String fileName = "users.txt";
    private File f;//todo discuss that I do not use an employees file, i create my own

    /**
     * Adds user to user database (text file)
     * @param userType what type of user this user is
     * @param phoneNumber the pnone nubmer of this user
     * @param email this users email
     * @param userName this users username
     * @param password this users unencrypted password
     * @throws FileNotFoundException
     */
    public void addUser(String userType, String phoneNumber, String email, String userName, String password) throws FileNotFoundException{

        //creating an employee from text fields
        String [] user = new String[5];
        user[0] = userType;
        user[1] = phoneNumber;
        user[2] = email;
        user[3] = userName;
        user[4] = password;
        Employee em = new Employee(user[0], user[1], user[2], user[3], user[4]);

        //checks to see if users is empty, if it is then itll read in users fro file
        if (users.isEmpty() == true){

            Scanner inVF = new Scanner(f);
            //adds users back into program
            while(inVF.hasNext()){
                String line = inVF.nextLine();
                String [] pA = line.split(",");
                Employee ba = new Employee(pA[0], pA[1], pA[2], pA[3], pA[4]);
                users.add(ba);
            }

        }else{
            //compares users to the name of the person who is trying to be added
            for (int i=0; i < users.size(); i++){
                //if new username does not match that of one in the file it'll be added
                if (users.get(i).getUsername().compareTo(userName) != 0){
                    users.add(em);
                }else{
                    System.out.println("User already exsists");
                }
            }
        }
        //after everything is complete it writes the users back into the file
        f = new File(fileName);
        try (PrintWriter p = new PrintWriter(f)) {
            for(Employee usr: users){
                p.println(usr.toString());
            }
        }

    }

    /**
     * Deletes user by his/her username
     * @param username
     * @throws FileNotFoundException
     */
    public void deleteUser(String username) throws FileNotFoundException{
        //read from user.txt
        //then compare names and delete the correct one
        f = new File(fileName);
        if(users.isEmpty() == true){

            Scanner inVF = new Scanner(f);

            while(inVF.hasNext()){
                String line = inVF.nextLine();
                String [] pA = line.split(",");
                Employee ba = new Employee(pA[0], pA[1], pA[2], pA[3], pA[4]);
                users.add(ba);
            }
            for(int i=0; i< users.size(); i++){
                if(users.get(i).getUsername().compareTo(username) == 0){
                    users.remove(i);
                }
            }
            //File f = new File(fileName);
            try (PrintWriter p = new PrintWriter(f)) {
                for(Employee usr: users){
                    p.println(usr.toString());
                }
            }
        }else{
            for(int i=0; i< users.size(); i++){
                if(users.get(i).getUsername().compareTo(username) == 0){
                    users.remove(i);
                }
            }
            //File f = new File(fileName);
            try (PrintWriter p = new PrintWriter(f)) {
                for(Employee usr: users){
                    p.println(usr.toString());
                }
            }
        }

    }
    /**
     * Resets password by user name
     * @param name
     * @param newPass
     * @throws FileNotFoundException
     */
    public void resetPassword(String name, String newPass) throws FileNotFoundException{

        for(int i=0; i< users.size(); i++){
            if(users.get(i).getUsername().compareTo(name) == 0){
                //change pass to new pass
                users.get(i).setPassword(newPass);

            }else{
                System.out.println("Something went wrong with reseting password");
            }
        }

        //File f = new File(fileName);
        try (PrintWriter p = new PrintWriter(f)) {
            for(Employee usr: users){
                p.println(usr.toString());
            }
        }

    }

}