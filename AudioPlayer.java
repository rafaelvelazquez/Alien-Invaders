import javax.sound.sampled.*;
import java.io.*;

public class AudioPlayer {

    private Clip clip;

    public AudioPlayer(String filename) {
        try {
            // open the audio input stream
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
            
            AudioFormat format = stream.getFormat();
            
            // specify what kind of line we want to create
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            // create the line
            clip = (Clip)AudioSystem.getLine(info);
            
            //clip = AudioSystem.getClip();
            // load the samples from the stream
            clip.open(stream);
   
        }
        catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (LineUnavailableException ex) {
        ex.printStackTrace();
        }
        
        
    }    

    public void play() {
    	
    	if (clip.isRunning())
    		stop();
    	clip.setFramePosition(0); 
        clip.start();     
    }
    
    public void stop(){
    	if (clip.isRunning())
    		clip.stop();
    	clip.setFramePosition(0);
    }
    
    public void close(){
    	stop();
    	clip.close();
    }
    
    public void loop(){
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
