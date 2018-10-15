package btloncmd;

import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * This is where all begins .
 * 
 * @author GOXR3PLUS
 *
 */
public class Voice {

	// Create a Synthesizer instance
	SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

	/**
	 * Constructor
	 */
	private String line;
	public void setLine(String a) {
		this.line=a;
	}
	
	public void speak() {
		String text=this.line;
		//System.out.println(text);

		// Create a new Thread because JLayer is running on the current Thread and will
		// make the application to lag
		Thread thread = new Thread(() -> {
			try {

				// Create a JLayer instance
				AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
				player.play();

				//System.out.println("Successfully got back synthesizer data");

			} catch (IOException | JavaLayerException e) {

				e.printStackTrace(); // Print the exception ( we want to know , not hide below our finger , like many
										// developers do...)

			}
		});

		// We don't want the application to terminate before this Thread terminates
		thread.setDaemon(false);

		// Start the Thread
		thread.start();

	}

}
