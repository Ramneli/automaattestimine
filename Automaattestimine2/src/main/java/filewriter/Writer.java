package filewriter;

import org.json.JSONObject;
import java.io.*;

public class Writer {
    public void writeToFile(JSONObject data)  {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("output.txt")));
            bufferedWriter.write(data.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Error in writing file!" + "\n" + e);
        }
    }
}
