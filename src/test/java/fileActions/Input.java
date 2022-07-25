package fileActions;

import java.io.*;
import java.util.List;

public class Input {

    public static void inputDataIntoFile(List<String> methodsList, String fileName){
        BufferedWriter output = null;
        try {
            File file = new File(fileName);

            output = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < methodsList.size(); i++){
                if(i != (methodsList.size() - 1)){
                    output.write(methodsList.get(i) + ", ");
                }
                else {
                    output.write(methodsList.get(i));
                }
            }
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
                try {
                    output.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}

