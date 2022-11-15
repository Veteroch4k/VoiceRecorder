package ru.veteroch4k.vr.classes;

import java.awt.Dimension;

public class Main {

  public static void main(String[] args) {
    MainMenu mainMenu = new MainMenu();

    // Упаковываем все элементы с нашей формы
    mainMenu.pack();

    // Изменяем размеры окна
    mainMenu.setSize(new Dimension(600, 500));

    // Отображаем созданное окно
    mainMenu.setVisible(true);
  }
}
