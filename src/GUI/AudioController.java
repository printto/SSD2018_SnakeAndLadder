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

	public void startSound(){
		playSound("Sound/GameStart.wav",0);
	}

	public void diceSound(int face) {
		switch(face){
		case 1 : 
			playSound("Sound/dice01.wav",0);
			break;
		case 2 : 
			playSound("Sound/dice02.wav",0);
			break;
		case 3 : 
			playSound("Sound/dice03.wav",0);
			break;
		case 4 : 
			playSound("Sound/dice04.wav",0);
			break;
		case 5 : 
			playSound("Sound/dice05.wav",0);
			break;
		case 6 : 
			playSound("Sound/dice06.wav",0);
			break;
		default :
			playSound("Sound/dice01.wav",0);
		}
	}

	public void snakeSound(){
		playSound("Sound/snake.wav",1000);
	}

	public void ladderSound(){
		playSound("Sound/ladder.wav",1000);
	}

	public void freezeSound(){
		playSound("Sound/freeze.wav",1000);
	}

	public void reverseSound(){
		playSound("Sound/backward.wav",1000);
	}

	public void winSound(){
		playSound("Sound/win.wav",1000);
	}

	public void playSound(String path,int sleepTime){
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path)));
			clip.start();
			sleepThread(sleepTime);
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
