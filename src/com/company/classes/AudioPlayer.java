package com.company.classes;

import com.company.interfaces.Base;
import com.company.thread.CaptureThread;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import com.company.thread.PlayThread;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFileChooser;

public class AudioPlayer  {

  public static byte[] audioData;

  public CaptureThread thread;
  public PlayThread thread228;
  public ZvykForm form;

  public AudioPlayer(ZvykForm x) {
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
    System.out.println(111);
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

    System.out.println(123);

  }

  public void save(JFileChooser fileChooser) throws IOException {

    String file = fileChooser.getSelectedFile().getAbsolutePath().replace("\\", "\\\\");
    String filePath = file + ".wav";
    System.out.println(filePath);
    byte[] audioData = thread.byteArrayOutputStream.toByteArray();


    File audioFile = new File(filePath);

    ByteArrayInputStream bais;
    AudioInputStream ais;

    bais = new ByteArrayInputStream(audioData);
    ais = new AudioInputStream(bais, new AudioFormat(Base.sampleRate, Base.sampleSizeInBits, Base.channels, Base.signed, Base.bigEndian),
        audioData.length / new AudioFormat(Base.sampleRate, Base.sampleSizeInBits, Base.channels, Base.signed, Base.bigEndian).getFrameSize());
    // Определяем окончательное имя сохраненного файла
    System.out.println("Начать создание голосового файла");
    AudioSystem.write(ais, AudioFileFormat.Type.WAVE, audioFile);
    System.out.println(12345);

  }
}
