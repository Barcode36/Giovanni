package com.example.giovanni.giovanni.utils;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InternalStorage {

    public static void writeObject(Context context, String key, Object object) {

        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Object readObject(Context context, String key) {

        FileInputStream fis;
        ObjectInputStream ois;
        Object object = null;
        try {
            fis = context.openFileInput(key);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return object;
    }
}