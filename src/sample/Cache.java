package sample;


import java.util.ArrayList;

/**
 * Created by mehdi on 4/24/17.
 */
public class Cache {

    public static ArrayList<Chat> ChatroomsArrayList = new ArrayList<>();
    private static String name;
    private static String number;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Cache.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number2) {
        this.number = number2;
    }


}

