package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naoto on 3/20/2017.
 */
public class Database {

    private final List<User> users;

    public Database() throws IOException {
        users = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("./src/id.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            String[] split = line.split(",");
            String user_name = split[0];
            String password = split[1];
            UserLevel user_level = UserLevel.valueOf(split[2].toUpperCase());
            String email_address = split[3];
            if (user_level.equals(UserLevel.MANAGER)) {
                users.add(new Manager(user_name, password, user_level, email_address));
            } else if (user_level.equals(UserLevel.WORKER)) {
                users.add(new Worker(user_name, password, user_level, email_address));
            } else {
                users.add(new User(user_name, password, user_level, email_address));
            }
        }
        br.close();
    }


    /**
     * return a boolean to show if the input user is in the database
     * @param name the input name
     * @param password the input password
     * @return object, if the user is in the database return it or return null
     * */
    public User getAuth(String name, String password) {
        for (User u : users) {
            if (u.getUserName().equals(name) && u.getPassword().equals(password)) {
                return u;
            }
            System.out.println(u);
        }
        return null;
    }


    /**
     * return a boolean to show if the input username is in the database
     * @param username the input name
     * @return boolean, if the username is in the database return true else return false
     * */
    public boolean checkExistance(String username) {
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * register a user to the file
     * @param name username
     * @param password password
     * @param email email address
     * @param level user's level
     */
    public User registerUser(String name, String password, String email, UserLevel level) {
        User newuser;
        if (level.equals(UserLevel.WORKER)) {
            newuser = new Worker(name, password, level);
        } else {
            newuser = new User(name, password, level);
        }
        //implementation
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("./src/id.txt", true));
            out.newLine();
            out.write(name + "," + password + "," + level.toString() + "," + /*"none" + "," + */ email);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newuser;
    }

    /**
     * used to edit user's profile
     * @param user the old user to be change
     * @param username edited username
     * @param password edited password
     * @param email edited email address
     * @param level edited user level
     * @return the new user if finished, return null if fail
     */
    public User editProfile(User user, String username, String password, String email, UserLevel level) {
        //user.setHomeAddress(homeAddress);
        users.remove(user);
        User tempuser;
        if (level.equals(UserLevel.WORKER)) {
            tempuser = new Worker(username, password, level, email);

        } else if (level.equals(UserLevel.MANAGER)) {
            tempuser = new Manager(username, password, level, email);
        } else {
            tempuser = new User(username, password, level, email);
        }
        users.add(tempuser);
        File f = new File("./src/id.txt");
        if (!f.exists()) {
            return null;
        }
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            User u = users.get(0);
            fw.write(u.getUserName() + "," + u.getPassword() + "," + u.getUserLevel() /*+ "," + u.getHomeAddress()*/ + "," + u.getEmailAddress());
            for (int i = 1; i < users.size(); i++) {
                u = users.get(i);
                fw.write("\n");
                fw.write(u.getUserName() + "," + u.getPassword() + "," + u.getUserLevel() /*+ "," + u.getHomeAddress()*/ + "," + u.getEmailAddress());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return tempuser;
    }
}
