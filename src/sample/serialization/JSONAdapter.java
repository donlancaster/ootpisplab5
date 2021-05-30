package sample.serialization;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import sample.file.DefaultFileRW;
import sample.file.FileRW;
import sample.hierarchy.*;


import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class JSONAdapter implements Serialization {
    private XMLSerialization adaptXML;
    private static JSONAdapter jsonAdapter;

    private JSONAdapter() {
        adaptXML = XMLSerialization.getInstance();
    }

    public static JSONAdapter getInstance() {
        if (jsonAdapter == null) {
            jsonAdapter = new JSONAdapter();
        }
        return jsonAdapter;
    }


    @Override
    public void serialize(File file, LinkedList<Galaxy> list) {
        try {
            String temp = adaptXML.serializeToString(list);
            System.out.println(temp);
            XmlMapper xmlMapper = new XmlMapper();
            Galaxy[] galaxies = xmlMapper.readValue(temp, Galaxy[].class);
            ObjectMapper objectMapper = new ObjectMapper();
            DefaultFileRW f = new DefaultFileRW();
            f.write(file, objectMapper.writeValueAsString(galaxies));
            //     FileRW.getFileJob().write(file, objectMapper.writeValueAsString(galaxies));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<Galaxy> deserialize(File file) {
        DefaultFileRW f = new DefaultFileRW();

        String readStrings = f.read(file);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        Galaxy[] arr = new Galaxy[0];
        try {
            arr = objectMapper.reader().forType(Galaxy[].class).readValue(readStrings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LinkedList<>(Arrays.asList(arr));
    }
}