package ru.veteroch4k.vr.classes;

import ru.veteroch4k.vr.interfaces.Configuration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JPanel;

public class MainMenu extends JFrame implements ActionListener, Configuration {

  public JPanel zvykPanel;
  public JButton captureBtn;
  public JButton stopBtn;
  public JButton playBtn;
  public JButton saveBtn;

  public AudioPlayer player;

  public boolean stopCapture = false;


  public MainMenu() {
    setTitle("Обработка звука");
    setLocation(700, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);


    captureBtn.addActionListener(this);
    stopBtn.addActionListener(this);
    playBtn.addActionListener(this);
    saveBtn.addActionListener(this);

    captureBtn.setEnabled(true);
    stopBtn.setEnabled(false);
    playBtn.setEnabled(false);
    saveBtn.setEnabled(false);

    this.getContentPane().add(zvykPanel);


    player = new AudioPlayer(this);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == captureBtn) {
      captureBtn.setEnabled(false);
      stopBtn.setEnabled(true);
      playBtn.setEnabled(false);
      saveBtn.setEnabled(false);
      //Захват данных
      // с микрофона
      //пока не нажата Stop
      try {
        player.captureAudio();
      } catch (LineUnavailableException ex) {
        ex.printStackTrace();
      }
      //captureAudio();
    }
    if (e.getSource() == stopBtn) {
      captureBtn.setEnabled(true);
      stopBtn.setEnabled(false);
      playBtn.setEnabled(true);
      saveBtn.setEnabled(true);
      //Остановка захвата
      // информации с микрофона

      player.stopCaptureAudio();

    }
    if (e.getSource() == playBtn) {

      player.playAudio();
      //playAudio();
    }
    if (e.getSource() == saveBtn) {
      try {
        JFileChooser fileChooser = new JFileChooser();

        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {

          player.save(fileChooser);
        }
        //save();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }
}
