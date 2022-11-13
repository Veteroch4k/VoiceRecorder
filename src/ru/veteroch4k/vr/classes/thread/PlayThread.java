package ru.veteroch4k.vr.classes.thread;

import ru.veteroch4k.vr.interfaces.Configuration;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class PlayThread extends Thread {

  public byte[] audioData;


  byte[] tempBuffer = new byte[10000];

  public PlayThread(byte[] z) {

    this.audioData = z;

  }

  public void run() {
    //Устанавливаем всё
    //для проигрывания
    try {
      InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
      AudioFormat audioFormat = new AudioFormat(Configuration.sampleRate, Configuration.sampleSizeInBits, Configuration.channels, Configuration.signed, Configuration.bigEndian);
      AudioInputStream audioInputStream = new AudioInputStream(byteArrayInputStream, audioFormat,
          audioData.length / audioFormat.getFrameSize());
      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
      SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();
      try {
        int cnt;

        // цикл пока не вернется -1

        while ((cnt = audioInputStream.
            read(tempBuffer, 0,
                tempBuffer.length)) != -1) {
          if (cnt > 0) {
            //Пишем данные во внутренний
            // буфер канала
            // откуда оно передастся
            // на звуковой выход
            sourceDataLine.write(
                tempBuffer, 0, cnt);
          }
        }
        sourceDataLine.drain();
        sourceDataLine.close();
      } catch (Exception e) {
        System.out.println(e);
        System.exit(0);
      }
      
    } catch (LineUnavailableException e) {
      System.out.println(e.getMessage());
    }
   
  }

}
