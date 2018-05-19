package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import engine.Game;
import sun.audio.AudioStream;

public class AudioController {

	public void startGame(){
		playEffect("Sound/GameStart.wav");
	}
	
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
		} catch (Exception e) {
			System.err.println("Can't play dice audio file.");
		}
	}
	
	public void snakeSound(){
		playEffect("Sound/snake.wav");
	}
	
	public void ladderSound(){
		playEffect("Sound/ladder.wav");
	}
	
	public void freezeSound(){
		playEffect("Sound/freeze.wav");
	}
	
	public void reverseSound(){
		playEffect("Sound/backward.wav");
	}
	
	public void winSound(){
		playEffect("Sound/win.wav");
	}
	
	public void playEffect(String path){
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path)));
			clip.start();
			sleepThread(1000);
		} catch (Exception e) {
			System.err.println("Can't play audio file.");
		}
	}
	
	public void sleepThread(int millsec){
		try {
			TimeUnit.MILLISECONDS.sleep(millsec);
		} catch (InterruptedException e) {
			System.err.println("Can't sleep audio thread.");
		}
	}
	
}
