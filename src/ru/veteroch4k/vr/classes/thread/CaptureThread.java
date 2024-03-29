package ru.veteroch4k.vr.classes.thread;

import ru.veteroch4k.vr.interfaces.Configuration;
import java.io.ByteArrayOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class CaptureThread extends Thread {
  public ByteArrayOutputStream byteArrayOutputStream;
  public boolean stopCapture;

  byte[] tempBuffer = new byte[10000];

  public CaptureThread(boolean y) {

    stopCapture = y;
  }

  public void run()  {
    try {
      Info dataLineInfo = new Info(TargetDataLine.class, new AudioFormat(Configuration.sampleRate, Configuration.sampleSizeInBits, Configuration.channels, Configuration.signed, Configuration.bigEndian));
      TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
      targetDataLine.open(new AudioFormat(Configuration.sampleRate, Configuration.sampleSizeInBits, Configuration.channels, Configuration.signed, Configuration.bigEndian));
      targetDataLine.start();

      this.byteArrayOutputStream = new ByteArrayOutputStream();
      try {

        while (!stopCapture) {

          int cnt = targetDataLine.read(tempBuffer,
              0,
              tempBuffer.length);
          if (cnt > 0) {
            //Сохраняем данные в выходной поток

            this.byteArrayOutputStream.write(
                tempBuffer, 0, cnt);
          }
        }
        this.byteArrayOutputStream.close();
        targetDataLine.close();
      } catch (Exception e) {
        System.out.println(e);
        System.exit(0);
      }
    } catch (LineUnavailableException e) {
      System.out.println(e.getMessage());
    }
  }
}
