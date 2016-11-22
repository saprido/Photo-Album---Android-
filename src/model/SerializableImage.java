package model;

import java.io.InputStream;
import java.io.Serializable;

import javafx.scene.image.Image;

public class SerializableImage extends Image implements Serializable{

	public SerializableImage(InputStream is) {
		super(is);
		// TODO Auto-generated constructor stub
	}

}
