package sample.serialization;

import sample.hierarchy.*;

import java.io.File;
import java.util.LinkedList;

public interface Serialization {
    void serialize(File file, LinkedList<Galaxy> list);
    LinkedList<Galaxy> deserialize(File file);

}