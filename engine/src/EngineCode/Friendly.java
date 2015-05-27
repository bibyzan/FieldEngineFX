package EngineCode;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * EngineCode.Friendly field actor File structure:
 *
 * dirName named identical to name of actor-
 *      generic sprite file format goes here*
 *      text file containing one line that has the indicated dialogue for actor, name: dialogue.txt
 *      png image to be displayed when dialogue is requested, name: faceImage.png
 */
public class Friendly extends FieldActor {
    private String dialogue;
    private Image faceImage;

    public Friendly(String dirName) {
        super(dirName);

        try {
            faceImage = new Image(new FileInputStream(new File(dirName + "/faceImage.png")));

            Scanner dialogueReader = new Scanner(new File(dirName + "/dialogue.txt"));
            dialogue = dialogueReader.nextLine();
            dialogueReader.close();
        } catch (IOException e) {
            System.out.println("could not find resource for: " + dirName);
            e.printStackTrace();
        }
    }

    public void addDialogueToParent(Pane parentPane) {
        DialoguePane dialoguePane = new DialoguePane(dialogue, faceImage);
        dialoguePane.addItself(parentPane);
    }

    @Override
    boolean isFriendly() {
        return true;
    }
}
