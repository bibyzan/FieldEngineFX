# FieldEngineFX
Event based 2D gaming engine in Java.

This engine utilizes a specifically organized resource directory to create simple maps and events that run inside a window for you to make a game with

With the format already implemented, these games can be ran from a standalone jar file.

#To make a simple game 

Organize your maps and boundaries for the map in the format demonstrated with the castle tileset

Build a map organized with lines and spaces in a txt editor using your graphic names to create blocks.

Name the file in the correct directory using the .field extension

Now, create a package called fields to put all your maps in.

Make a java class that extends Field for your Map class, then run super in your constructor passing in the name of your field file as a string

Add any events you want to the class as demonstrated in this example repository.

Now, have your main class extend Launcher and in your psvm put a Launch(args); statement

You also have to create and instantiate your own World object passing in an instance of the first map you want to be shown

To do this, override the @Override
    public World worldType()
Method

Finally, run your main and the game should be on your screen

#Screenshots

What's shown here is a sample game I made by borrowing zelda graphics

![Screenshot](http://i.imgur.com/FKLpA1A.png)

![Screenshot](http://i.imgur.com/84aFoKb.png)

![Screenshot](http://i.imgur.com/0OiOrqp.png)


