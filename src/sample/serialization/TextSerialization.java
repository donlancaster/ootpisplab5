package sample.serialization;

import sample.file.DefaultFileRW;
import sample.file.FileRW;
import sample.hierarchy.*;


import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class TextSerialization implements Serialization {
    private static TextSerialization textSerialization;

    private TextSerialization() {
    }

    public static TextSerialization getInstance() {
        if (textSerialization == null) {
            textSerialization = new TextSerialization();
        }
        return textSerialization;
    }

    @Override
    public void serialize(File file, LinkedList<Galaxy> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Galaxy galaxy : list) {
            System.out.println(galaxy.forSerialization());
            stringBuilder.append(galaxy.forSerialization());
            stringBuilder.append("\n");
        }
        //System.out.println(stringBuilder.toString());
        DefaultFileRW f = new DefaultFileRW();
        f.write(file, stringBuilder.toString());
    }

    @Override
    public LinkedList<Galaxy> deserialize(File file) {
        LinkedList<Galaxy> linkedList = new LinkedList<>();
        DefaultFileRW frw = new DefaultFileRW();
    String readStrings = frw.read(file);
        for (String s : readStrings.split("\n")) {
            linkedList.add(parseGalaxy(s));
        }
        return linkedList;
    }

    private Galaxy parseGalaxy(String galaxy) {
        Galaxy g = new Galaxy();
        BlackHole b = new BlackHole();


        String[] objs = galaxy.split(";");
        String f1 = objs[0].split(":")[1];
        String f2 = objs[1].split(":")[1];
        String f3 = objs[2].split(":")[1];
        String f4 = objs[3].split(":")[1];
        String f5 = objs[4].split(":")[1];
        String f6 = objs[5].split(":")[1];
        String b1 = objs[6].split(":")[1];
        String b2 = objs[7].split(":")[1];
        String b3 = objs[8].split(":")[1];
        String b4 = objs[9].split(":")[1];
        String b5 = objs[10].split(":")[1];
        String b6 = objs[11].split(":")[1];
        String b7 = objs[12].split(":")[1];
        String b8 = objs[13].split(":")[1];
        String b9 = objs[14].split(":")[1];
        String b10 = objs[15].split(":")[1];
        System.out.println(objs[6]);
        b.setName(b1);
        b.setMass(Double.parseDouble(b2));
        b.setSize(Double.parseDouble(b3));
        b.setForm(b4);
        b.setColor(b5);
        b.setStructure(b6);
        b.setXrayCoefficient(Double.parseDouble(b7));
        b.setLuminosity(Double.parseDouble(b8));
        b.setEvaporation(Double.parseDouble(b9));
        b.setEventHorizonRadius(Double.parseDouble(b10));

        g.setName(f1);
        g.setMass(Double.parseDouble(f2));
        g.setSize(Double.parseDouble(f3));
        g.setForm(f4);
        g.setGalaxyType(f5);
        g.setElements(Integer.parseInt(f6));
        g.setCenterBlackHole(b);

        return g;


    }

}
