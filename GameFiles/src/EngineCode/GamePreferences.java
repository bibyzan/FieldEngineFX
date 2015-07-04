package EngineCode;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by benra_000 on 5/16/2015.
 */
public class GamePreferences {
    private double pixelWidth, pixelHeight;
    private Font titleFont, bodyFont;
    private Color neutralColor, friendlyColor, enemyColor;

    public GamePreferences() {
        InputStream inStream = getClass().getClassLoader().getResourceAsStream("genericPreferences.txt");


        this.pullSource(new Scanner(inStream));
    }

    public GamePreferences(Scanner source) {
        this.pullSource(source);
    }

    private void pullSource(Scanner source) {
        String[] splitArr = source.nextLine().split(",");
        titleFont = new Font(splitArr[0], Integer.parseInt(splitArr[1]));
        splitArr = source.nextLine().split(",");
        bodyFont = new Font(splitArr[0], Integer.parseInt(splitArr[1]));

        int[] rgb = cast(source.nextLine().split(" "));
        neutralColor = Color.rgb(rgb[0], rgb[1], rgb[2]);

        rgb = cast(source.nextLine().split(" "));
        friendlyColor = Color.rgb(rgb[0], rgb[1], rgb[2]);

        rgb = cast(source.nextLine().split(" "));
        enemyColor = Color.rgb(rgb[0], rgb[1], rgb[2]);

        String[] pixelPreferences = source.nextLine().split(" ");
        pixelWidth = Double.parseDouble(pixelPreferences[0]);
        pixelHeight = Double.parseDouble(pixelPreferences[1]);
    }

    private int[] cast(String[] array) {
        int[] newArray = new int[array.length];

        int i = 0;
        for (String s: array) {
            newArray[i] = Integer.parseInt(s);
            i++;
        }

        return newArray;
    }

    public double getPixelWidth() {
        return pixelWidth;
    }

    public void setPixelWidth(double pixelWidth) {
        this.pixelWidth = pixelWidth;
    }

    public double getPixelHeight() {
        return pixelHeight;
    }

    public void setPixelHeight(double pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    public Font getBodyFont() {
        return bodyFont;
    }

    public void setBodyFont(Font bodyFont) {
        this.bodyFont = bodyFont;
    }

    public Color getNeutralColor() {
        return neutralColor;
    }

    public void setNeutralColor(Color neutralColor) {
        this.neutralColor = neutralColor;
    }

    public Color getFriendlyColor() {
        return friendlyColor;
    }

    public void setFriendlyColor(Color friendlyColor) {
        this.friendlyColor = friendlyColor;
    }

    public Color getEnemyColor() {
        return enemyColor;
    }

    public void setEnemyColor(Color enemyColor) {
        this.enemyColor = enemyColor;
    }
}
