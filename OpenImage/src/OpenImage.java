import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

// There was a access restriction error with JavaFX
// Fixed by ignore the Forbidden reference (access rule) to Ignore
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class OpenImage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
    	WritableImage writableImage = loadImage();
    	
    	// Setting the image view
    	ImageView imageView = new ImageView(writableImage);
    	
    	// Setting the position of the image
    	imageView.setX(50);
    	imageView.setY(25);
    	
    	// Setting the preserve ratio of the image view
    	imageView.setPreserveRatio(true);
    	
    	// Creating a Group object
    	Group root = new Group(imageView);
    	
    	// Creating a scene object
    	Scene scene = new Scene(root, 740, 600);
    	
    	// Setting title to the stage
    	stage.setTitle("Loading an image");
    	
    	// Adding scene to the stage
    	stage.setScene(scene);
    	
    	// Displaying the contents of the stage
    	stage.show();
    }
    
    public WritableImage loadImage() throws IOException {
    	// Load OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Initial the image class
        Imgcodecs imageCodecs = new Imgcodecs();
        // Path to the image
        String file = "rainbow.jpeg";
        // Read the image to a matrix
        Mat image = imageCodecs.imread(file);
        // Encoding the image
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpeg", image, matOfByte);
        // Storing the encoded matrix in a byte array
        byte[] byteArray = matOfByte.toArray();
        // Displaying the image
        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufImage = ImageIO.read(in);
        
        System.out.println("Image Loaded");
        WritableImage writableImage = SwingFXUtils.toFXImage(bufImage, null);
        return writableImage;
    }
    
	public static void main(String[] args) {
		
        launch(args);
	}

}
