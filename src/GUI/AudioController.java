package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import engine.Game;
import sun.audio.AudioStream;

public class AudioController {

	public void diceSound(int face) {
		try {
			Clip clip = AudioSystem.getClip();
			
			switch(face){
			case 1 : 
				clip.open(AudioSystem.getAudioInputStream(new File("Sound/dice01.wav")));
				break;
			case 2 : 
				clip.open(AudioSystem.getAudioInputStream(new File("Sound/dice02.wav")));
				break;
			case 3 : 
				clip.open(AudioSystem.getAudioInputStream(new File("Sound/dice03.wav")));
				break;
			case 4 : 
				clip.open(AudioSystem.getAudioInputStream(new File("Sound/dice04.wav")));
				break;
			case 5 : 
				clip.open(AudioSystem.getAudioInputStream(new File("Sound/dice05.wav")));
				break;
			case 6 : 
				clip.open(AudioSystem.getAudioInputStream(new File("Sound/dice06.wav")));
				break;
			default :
				clip.open(AudioSystem.getAudioInputStream(new File("Sound/dice01.wav")));
			}
			clip.start();
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
