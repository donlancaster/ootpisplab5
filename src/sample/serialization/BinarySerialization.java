package sample.serialization;

import sample.hierarchy.*;

import java.io.*;
import java.util.LinkedList;

public class BinarySerialization implements Serialization {

    private static BinarySerialization binarySerialization;

    private BinarySerialization() {
    }

    public static BinarySerialization getInstance() {
        if (binarySerialization == null) {
            binarySerialization = new BinarySerialization();
        }
        return binarySerialization;
    }

    @Override
    public void serialize(File file, LinkedList<Galaxy> list) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ////////////////
            oos.writeObject(list);
          ////////////////////
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<Galaxy> deserialize(File file) {
        LinkedList<Galaxy> deserialized = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            deserialized = (LinkedList<Galaxy>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserialized;
    }
}