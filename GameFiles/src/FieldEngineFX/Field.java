package FieldEngineFX;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * 9/4/2015
 * The main display for all game play. This class is what reads
 * the .field files and renders them on the screen. It also handles
 * the screen resizing and scrolling. This is meant to be extended
 * for use with all .field files so you can add all custom events
 * and field nodes to it.
 *
 * @author Ben Rasmussen
 */
public class Field extends Pane {
    //Variable linked to the children inside this pane, for organization purposes
    private Grid<FieldGraphic> graphicsGrid;

    //List of events to be checked as the player moves around the screen
    protected ArrayList<FieldEvent> events;

    //Position of the graphicsGrid that the player is to start on
    private Position startingPosition;

    //player object that handles all controls and where
    protected Player player;

    //statement deciding if the map is too big to display on one screen
    private boolean scrollable;

    /**
     * Default constructor made for only use in the Database
     * Parser class.
     */

    public Field() {
        super();
    }

    /**
     * Main driver and constructor for this class. Most likely will
     * always be called as super. This constructor loads the map from
     * the specified .field file, then draws it on the screen using
     * the selected Tileset.
     *
     * @param fieldName name of the .field file your using, no need worry about
     *                  the path as it already accounts for that assuming your
     *                  files are all in the right place.
     */

    public Field(String fieldName, Position startingPosition) {
        super();
        Field loaded = DatabaseParser.loadField(fieldName);

        this.events = new ArrayList<>();
        this.startingPosition = startingPosition;
        this.graphicsGrid = loaded.getGraphicsGrid();
        this.setScrollable(loaded.isScrollable());

        super.getChildren().addAll(graphicsGrid.values());
    }

    /**
     * This method is intended to be called from the world class when the screen size changes.
     * It pulls all the FieldObjects from the graphicsGrid, then moves and re-sizes them
     * based on the changes made to the window size.
     *
     * @param newWidth new size of the window width
     * @param newHeight new size of the window height
     */

    public void changeSize(double oldWidth, double oldHeight, double newWidth, double newHeight) {
        double fillWidth = newWidth / Launcher.gridWidth;
        double fillHeight = newHeight / Launcher.gridHeight;

        int currentX = 0, currentY = 0;

        for (int y = 0; y <= graphicsGrid.getRowCount(); y++) {
            currentY = 0;
            for (int x = 0; x <= graphicsGrid.getColCount(); x++) {
                try {
                    FieldGraphic currentGraphic = graphicsGrid.get(new Position(x, y));
                    currentGraphic.changeX(currentX);
                    currentGraphic.changeY(currentY);
                    currentGraphic.setFillHeight(fillHeight);
                    currentGraphic.setFillWidth(fillWidth);

                    currentY += fillHeight;
                } catch (Exception e) {
                    System.out.println("it wasn't there...");
                }
            }
            currentX += fillWidth;
        }

        for (FieldEvent event: events)
            resizeIndividualNode(event, newWidth, newHeight);

        resizeIndividualNode(player, newWidth, newHeight);
    }

    private void resizeIndividualNode(FieldNode node, double newWidth, double newHeight) {
        double fillWidth = newWidth / Launcher.gridWidth;
        double fillHeight = newHeight / Launcher.gridHeight;
        double percentageAcrossScreenX = node.getLeftX() / Launcher.screenWidth;
        double percentageAcrossScreenY = node.getTopY() / Launcher.screenHeight;

        node.setFillWidth(fillWidth);
        node.setFillHeight(fillHeight);
        node.changeX(newWidth * percentageAcrossScreenX);
        node.changeY(newHeight * percentageAcrossScreenY);
    }

    /**
     *
     * @param player
     */

    public void addPlayer(Player player) {
        player.setFillWidth(Launcher.screenWidth / Launcher.gridWidth);
        player.setFillHeight(Launcher.screenHeight / Launcher.gridHeight);

        player.moveToPoint(Launcher.getPointAtPosition(startingPosition));
        super.getChildren().add(player);

        this.player = player;
    }

    public void runStartupEvents() {
        for (FieldEvent event: events) {
            event.finishInitializing();

            if (event.eventInvoker().equals(Action.RUN_ON_STARTUP))
                event.runEvent(Action.RUN_ON_STARTUP);
        }
    }

    public Grid<FieldGraphic> getGraphicsGrid() {
        return graphicsGrid;
    }

    public void setGraphicsGrid(Grid<FieldGraphic> graphicsGrid) {
        this.graphicsGrid = graphicsGrid;
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    public ArrayList<FieldEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<FieldEvent> events) {
        this.events = events;
    }
}
