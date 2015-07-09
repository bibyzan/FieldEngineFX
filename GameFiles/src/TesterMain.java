import javafx.scene.text.Font;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Ben Rasmussen on 7/2/2015.
 */
public class TesterMain {
	public static void main(String[] args) throws Exception {
		int c = 0;

		for (File java: (new File("GameFiles/src/FieldEngineInterface")).listFiles()) {
			Scanner reader = new Scanner(java);

			while (reader.hasNext()) {
				c++;
				reader.nextLine();
			}
		}

		System.out.println(c);
	}
}
