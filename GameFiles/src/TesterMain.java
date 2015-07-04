import javafx.scene.text.Font;

import java.io.File;

/**
 * Created by Ben Rasmussen on 7/2/2015.
 */
public class TesterMain {
	public static void main(String[] args) {
		for (String font: Font.getFamilies())
			System.out.println(font);
	}
}
