package FieldEngineInterface;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ben Rasmussen on 7/3/2015.
 */
public class SpritePreview extends ImageView {
	private ArrayList<Image> images;
	private int index;

	public SpritePreview(ArrayList<Image> images) {
		super(images.get(0));
		this.images = images;
		this.index = 0;

		super.setFitWidth(70);
		super.setFitHeight(70);

		Timeline animator = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setImage(images.get(index));

				index++;

				if (index == images.size())
					index = 0;
			}
		}));
		animator.setCycleCount(Animation.INDEFINITE);
		animator.play();

	}
}
