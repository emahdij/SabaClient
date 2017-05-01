package sample;

import java.util.ArrayList;

/**
 * Created by mehdi on 4/25/17.
 */
public class Chat {

    private String number;
    private String name;
    private String type;
    private String Admin;
    private String seen;

    private sound Notification;

    public enum sound {
        Enable, Disable
    }

    public ArrayList<ArrayList<ArrayList<String>>> msg = new ArrayList<>();

    public Chat(String name, String type) {
        if (type.equals("1")) {
            this.number = name;
            this.type = type;
        } else {
            this.name = name;
            this.type = type;
        }
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }

    public sound getNotification() {
        return Notification;
    }

    public void setNotification(sound notification) {
        Notification = notification;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<ArrayList<ArrayList<String>>> msg) {
        this.msg = msg;
    }
}
