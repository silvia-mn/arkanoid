package reproductor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class jlap
{
	private String filename = "";
	private boolean repetir = false;
	private static Thread hilo; 
	public static boolean iniciar = false;
	public AdvancedPlayer player;

        private boolean silenciado = false;
        
	public jlap(String file) throws JavaLayerException, IOException{
		this.filename = file;
		play(false);
	}

	public void play(boolean inicial) throws JavaLayerException, IOException
	{
		jlap.iniciar = false;
		InfoListener lst = new InfoListener();
		player = playMp3(new File(filename), lst);
	}
	
	public void play() throws JavaLayerException, IOException
	{
		jlap.iniciar = true;
		InfoListener lst = new InfoListener();
		player = playMp3(new File(filename), lst);
	}
        

        public void stop()
	{
		player.stop();
	}
        

  public static AdvancedPlayer playMp3(File mp3, PlaybackListener listener) throws IOException, JavaLayerException
  {
    return playMp3(mp3, 0, Integer.MAX_VALUE, listener);
  }

  public static AdvancedPlayer playMp3(File mp3, int start, int end, PlaybackListener listener) throws IOException, JavaLayerException
  {
    return playMp3(new BufferedInputStream(new FileInputStream(mp3)), start, end, listener);
  }

  public static AdvancedPlayer playMp3(final InputStream is, final int start, final int end, PlaybackListener listener) throws JavaLayerException
  {
    final AdvancedPlayer player = new AdvancedPlayer(is);
    player.setPlayBackListener(listener);
    // run in new thread
    if(jlap.iniciar){
	    hilo = new Thread()
	    {
	      public void run()
	      {
	        try
	        {
	          player.play(start, end);
	        }
	        catch (Exception e)
	        {
	          throw new RuntimeException(e.getMessage());
	        }
	      }
	    };
	    hilo.start();
    }
    return player;
  }

  public class InfoListener extends PlaybackListener
  {
      public void playbackSamples(PlaybackEvent event, byte[] samples, int offset, int length) {
            if (silenciado) {
                for (int i = 0; i < length; i++) {
                    samples[offset + i] = 0; // Establece los datos de audio a 0 para silenciar el sonido
                }
            }
        }
    public void playbackStarted(PlaybackEvent evt)
    {
    }

    public void playbackFinished(PlaybackEvent evt)
    {
    	if(repetir){
    		hilo.start();
    	}
    }
  }
}