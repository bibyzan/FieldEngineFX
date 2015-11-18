import java.io.File;
import java.util.Scanner;

/**
 * Created by Ben on 9/29/2015.
 */
public class ProgressMain {
    public static void main(String[] args) throws Exception {
        int totalCounter = 0, codeCounter = 0;

        for (File classFile: (new File("src/FieldEngineFX")).listFiles()) {
            System.out.println("Reading: " + classFile.getName());

            Scanner scanner = new Scanner(classFile);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.endsWith(";") || line.endsWith("{") || line.endsWith("}"))
                    codeCounter++;

                totalCounter++;
            }
        }

        System.out.println("Total lines: " + totalCounter);
        System.out.println("Lines of code: " + codeCounter);
    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }
}
