package sample.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import sample.hierarchy.*;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;


import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class XMLSerialization implements Serialization {
    private static XMLSerialization xmlSerialization;

    private XMLSerialization() {
    }

    public static XMLSerialization getInstance() {
        if (xmlSerialization == null) {
            xmlSerialization = new XMLSerialization();
        }


        return xmlSerialization;
    }

    @Override
    public void serialize(File file, LinkedList<Galaxy> list) {

        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
            e.writeObject(list);
            e.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

    }

    @Override
    public LinkedList<Galaxy> deserialize(File file) {
        try{
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
            LinkedList<Galaxy> list = (LinkedList<Galaxy>) d.readObject();
            d.close();

            return list;
        }
        catch   (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }


    public String serializeToString(LinkedList<Galaxy> list) {
        Galaxy array [] = new Galaxy[list.size()];
        Arrays.setAll(array, list::get);
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.writeValueAsString(array);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}


