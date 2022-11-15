package ru.veteroch4k.vr.classes;

import ru.veteroch4k.vr.classes.thread.CaptureThread;
import ru.veteroch4k.vr.classes.thread.PlayThread;
import ru.veteroch4k.vr.interfaces.Configuration;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFileChooser;

public class AudioPlayer implements Configuration {

  public static byte[] audioData;

  public CaptureThread thread;
  public PlayThread thread228;
  public MainMenu form;

  public AudioPlayer(MainMenu x) {
    this.form = x;

  }

  public void playAudio() {
    try {

      audioData = this.thread.byteArrayOutputStream.toByteArray();

      //Создаем поток для проигрывания
      // данных и запускаем его
      // он будет работать пока
      // все записанные данные не проиграются
      this.thread228 = new PlayThread(audioData);
      Thread playThread = new Thread(thread228);
      playThread.start();

    } catch (Exception e) {
      System.out.println(e);
      System.exit(0);
    }
  }

  public void stopCaptureAudio() {
    this.thread.stopCapture = true;
  }


  public void captureAudio() throws LineUnavailableException {

    //Установим все для захвата

    //Создаем поток для захвата аудио и запускаем его, он будет работать, пока не нажмут кнопку
    this.thread = new CaptureThread(this.form.stopCapture);
    Thread captureThread = new Thread(thread);
    captureThread.start();


  }

  public void save(JFileChooser fileChooser) throws IOException {

    String file = fileChooser.getSelectedFile().getAbsolutePath().replace("\\", "\\\\");
    String filePath = file + fileFormat;
    System.out.println(filePath);
    byte[] audioData = thread.byteArrayOutputStream.toByteArray();


    File audioFile = new File(filePath);

    ByteArrayInputStream bais;
    AudioInputStream ais;

    bais = new ByteArrayInputStream(audioData);
    ais = new AudioInputStream(bais, new AudioFormat(Configuration.sampleRate, Configuration.sampleSizeInBits, Configuration.channels, Configuration.signed, Configuration.bigEndian),
        audioData.length / new AudioFormat(Configuration.sampleRate, Configuration.sampleSizeInBits, Configuration.channels, Configuration.signed, Configuration.bigEndian).getFrameSize());
    // Определяем окончательное имя сохраненного файла
    System.out.println("Начать создание голосового файла");
    AudioSystem.write(ais, AudioFileFormat.Type.WAVE, audioFile);

  }
}
