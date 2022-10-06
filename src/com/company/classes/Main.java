package com.company.classes;

import com.company.classes.ZvykForm;
import java.awt.Dimension;

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
