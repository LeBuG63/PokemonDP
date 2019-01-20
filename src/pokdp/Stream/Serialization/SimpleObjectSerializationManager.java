package pokdp.Stream.Serialization;

import java.io.*;

public class SimpleObjectSerializationManager implements IObjectSerializationManager {
    public Object readObjectFromFile(String path) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(path));
            ObjectInputStream inputObject = new ObjectInputStream(inputFile);

            Object item = inputObject.readObject();
            inputObject.close();

            inputFile.close();
            inputObject.close();

            return item;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }


        public void writeObjectFromFile(String path, Object item){
        try {
            FileOutputStream outputFile = new FileOutputStream(new File(path));
            ObjectOutputStream outputObject = new ObjectOutputStream(outputFile);

            outputObject.writeObject(item);
            outputObject.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
