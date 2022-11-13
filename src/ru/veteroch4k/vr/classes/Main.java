package ru.veteroch4k.vr.classes;

import java.awt.Dimension;
import java.util.Locale;

public class Main {

  public static void main(String[] args) {


    ZvykForm zvykForm = new ZvykForm();

    // Упаковываем все элементы с нашей формы
    zvykForm.pack();

    // Изменяем размеры окна
    zvykForm.setSize(new Dimension(600, 500));

    // Отображаем созданное окно
    zvykForm.setVisible(true);
  }
}
