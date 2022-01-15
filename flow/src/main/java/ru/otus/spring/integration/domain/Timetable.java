package ru.otus.spring.integration.domain;

import java.util.HashMap;
import lombok.Getter;

@Getter
public class Timetable {

  public static final HashMap<Integer, String> TIMETABLE = new HashMap<>();

  static {
    TIMETABLE.put(1, "Проект");
    TIMETABLE.put(2, "Смета");
    TIMETABLE.put(3, "Снос");
    TIMETABLE.put(4, "Выравнивание стен");
    TIMETABLE.put(5, "Стяжка");
    TIMETABLE.put(6, "Коммуникации");
    TIMETABLE.put(7, "Сантехника");
    TIMETABLE.put(8, "Отделка");
    TIMETABLE.put(9, "Заказ мебели");
    TIMETABLE.put(10, "Установка мебели");
    TIMETABLE.put(11, "Доработки");
    TIMETABLE.put(12, "Оплата");
  }
}
