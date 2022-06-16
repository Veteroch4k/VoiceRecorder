package com.company.classes;

import com.company.interfaces.Base;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ZvykForm extends JFrame implements ActionListener, Base {

  /**
   * 1) Тут дохера грязи, надо сделать нормальный человеческий код, а не этот пережеванный калл. +
   * 2) Надо будет подумать насчет интерфейса, потому что он убогий.
   * 3) Надо будеть реализовать .exe запуск данного приложения.
   * 4) По-хорошему, чтобы был установочный пакет.
   * 5) После всего этого надо будет преступить к реализации конвертации речи в текст.
   * 6) Если получится 5 пункт, то нужно начинать пилить перевод текста на другие языки.
   */

  public JPanel zvykPanel;
  public JButton captureBtn;
  public JButton stopBtn;
  public JButton playBtn;
  public JButton saveBtn;

  public AudioPlayer player;

  public boolean stopCapture = false;


  public ZvykForm() {
    setTitle("Обработка звука");
    setLocation(700, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);


    captureBtn.addActionListener(this);
    stopBtn.addActionListener(this);
    playBtn.addActionListener(this);
    saveBtn.addActionListener(this);


    JMenuBar menuBar = new JMenuBar();
    /**
     * Информация обо мне на НПК не пригодится
     */

    JMenu editMenu = new JMenu("Информация о величайщем");
    editMenu.setMnemonic(KeyEvent.VK_E);
    menuBar.add(editMenu);

    JMenuItem menuItem4 = new JMenuItem("Veteroch4k");
    editMenu.add(menuItem4);

    menuItem4.addActionListener(e -> {
      try {
        Desktop.getDesktop()
            .browse(new URL("https://steamcommunity.com/profiles/76561198796674435/").toURI());
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });

    captureBtn.setEnabled(true);
    stopBtn.setEnabled(false);
    playBtn.setEnabled(false);
    saveBtn.setEnabled(false);

    this.setJMenuBar(menuBar);
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