package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import engine.Game;
import sun.audio.AudioStream;

/**
 * This object is responsible for controlling the sound that appear in each game
 * section. Example, open the game, when player hits the trap, snake, and
 * ladder.
 * 
 * @author Pasut Kittiprapas
 * @version 1.0.0
 * @since May 19, 2018
 *
 */
public class AudioController {

	/**
	 * This method play the sound when the gameUI start.
	 */
	public void startSound() {
		playSound("Sound/GameStart.wav", 3400);
	}

	/**
	 * This method play the background sound along the game.
	 */
	public void bgmStart() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource("/Sound/GameWaitting.wav")));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(15f * (float) Math.log10(0.1));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.err.println("Can't play BGM file.");
		}
	}

	/**
	 * This method play the sound when players roll the dice. The sound will
	 * depend of the face that players rolled.
	 * 
	 * @param face
	 *            is the face of the dice that player roll each turn.
	 */
	public void diceSound(int face) {
		switch (face) {
		case 1:
			playSound("Sound/dice01.wav", 0);
			break;
		case 2:
			playSound("Sound/dice02.wav", 0);
			break;
		case 3:
			playSound("Sound/dice03.wav", 0);
			break;
		case 4:
			playSound("Sound/dice04.wav", 0);
			break;
		case 5:
			playSound("Sound/dice05.wav", 0);
			break;
		case 6:
			playSound("Sound/dice06.wav", 0);
			break;
		default:
			playSound("Sound/dice01.wav", 0);
		}
	}

	/**
	 * This method play sound when players hit the snake block.
	 */
	public void snakeSound() {
		playSound("Sound/snake.wav", 1000);
	}

	/**
	 * This method play sound when players hit the ladder block.
	 */
	public void ladderSound() {
		playSound("Sound/ladder.wav", 1000);
	}

	/**
	 * This method play sound when players hit the freeze block.
	 */
	public void freezeSound() {
		playSound("Sound/freeze.wav", 1000);
	}

	/**
	 * This method play sound when players hit the reverse block.
	 */
	public void reverseSound() {
		playSound("Sound/backward.wav", 1000);
	}

	/**
	 * This method play sound when one of the player wins the game.
	 */
	public void winSound() {
		playSound("Sound/win.wav", 1000);
	}

	/**
	 * This method responsible for get resource of sound from the path we input
	 * and play it.
	 * 
	 * @param path
	 *            the path of the sound we want to play.
	 * @param sleepTime
	 *            we stop the thread for a period of time (milliseconds).
	 */
	public void playSound(String path, int sleepTime) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource("/" + path)));
			clip.start();
			sleepThread(sleepTime);
		} catch (Exception e) {
			System.err.println("Can't play audio file.");
		}
	}

	/**
	 * This method will sleep the thread for a period of time.(We want the sound
	 * to finish playing first)
	 * 
	 * @param millsec
	 */
	private void sleepThread(int millsec) {
		try {
			TimeUnit.MILLISECONDS.sleep(millsec);
		} catch (InterruptedException e) {
			System.err.println("Can't sleep audio thread.");
		}
	}

}
