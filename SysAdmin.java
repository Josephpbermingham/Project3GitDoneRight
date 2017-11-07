package Project3GitDoneRight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author zachcaton
 */
class SysAdmin extends Employee {


    private ArrayList<Employee> users = new ArrayList<>();
    private String fileName = "users.txt";
    //This must already exist or it creates null pointer exceptions
    private File f = new File("users.txt");//This is the file that lists users

    SysAdmin() {
        super("a", "b", "d", "d", "e");
    }

    /**
     * Adds user to user database (text file)
     *
     * @param userType    what type of user this user is
     * @param phoneNumber the pnone nubmer of this user
     * @param email       this users email
     * @param userName    this users username
     * @param password    this users unencrypted password
     */
    void addUser(String userType, String phoneNumber, String email, String userName, String password) {

        //creating an employee from text fields
        String[] user = new String[5];
        user[0] = userType;
        user[1] = phoneNumber;
        user[2] = email;
        user[3] = userName;
        user[4] = password;
        Employee em = new Employee(user[0], user[1], user[2], user[3], user[4]);

        //checks to see if users is empty, if it is then it will read in users from the file
        Scanner inVF = null;
        if (users.isEmpty()) {
            try {
                inVF = new Scanner(f);
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found in sysAdmin addUser, Please consult the Author");
            }
            if(inVF.hasNext()) {
                //adds users back into program
                while (inVF.hasNext()) {
                    String line = inVF.nextLine();
                    String[] pA = line.split(",");
                    Employee ba = new Employee(pA[0], pA[1], pA[2], pA[3], pA[4]);
                    users.add(ba);
                }
            }

        } else {
            //compares users to the name of the person who is trying to be added
            for (int i = 0; i < users.size(); i++) {
                //if new username does not match that of one in the file it'll be added
                if (users.get(i).getUsername().compareTo(userName) != 0) {
                    users.add(em);
                } else {
                    System.out.println("User already exsists");
                }
            }
        }
        //after everything is complete it writes the users back into the file
        f = new File(fileName);PrintWriter p=null;

        try {
            p = new PrintWriter(f);
            for (Employee usr : users) {
                p.println(usr.toString());
            }
            p.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found exception in the end of SysAdmin.addUser");
        }

    }

    /**
     * Deletes user by his/her username
     *
     * @param username The UserName of the user that you are deleting
     */
    void deleteUser(String username) {
        //read from user.txt
        //then compare names and delete the correct one
        Scanner inVF = null;
        f = new File(fileName);
        if (users.isEmpty()) {
            try {
                inVF = new Scanner(f);
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found in sysAdmin addUser, Please consult the Author");
            }

            while (inVF.hasNext()) {
                String line = inVF.nextLine();
                String[] pA = line.split(",");
                Employee ba = new Employee(pA[0], pA[1], pA[2], pA[3], pA[4]);
                users.add(ba);
            }
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().compareTo(username) == 0) {
                    users.remove(i);
                }
            }
            //File f = new File(fileName);
            try {
                PrintWriter p = new PrintWriter(f);
                for (Employee usr : users) {
                    p.println(usr.toString());
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found in SysAdmin Remove user");
            }
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().compareTo(username) == 0) {
                    users.remove(i);
                }
            }
            //File f = new File(fileName);
            try {
                PrintWriter p = new PrintWriter(f);//todo make sure that this doesnt need to append
                for (Employee usr : users) {
                    p.println(usr.toString());
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found in the end of SysAdmin Remove user");
            }
        }

    }

    /**
     * Resets password by user name
     *
     * @param name    username of the user whose password you are changing
     * @param newPass the new password that you want to add
     * @throws FileNotFoundException Throws when the file of users is not found
     */
    public void resetPassword(String name, String newPass) throws FileNotFoundException {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().compareTo(name) == 0) {
                //change pass to new pass
                users.get(i).setPassword(newPass);

            } else {
                System.out.println("Something went wrong with reseting password");
            }
        }

        //File f = new File(fileName);
        try (PrintWriter p = new PrintWriter(f)) {
            for (Employee usr : users) {
                p.println(usr.toString());
            }
        }

    }

}