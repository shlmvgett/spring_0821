package ru.otus.spring.integration.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Date;
import ru.otus.spring.integration.domain.RenovationStage;
import ru.otus.spring.integration.domain.Timetable;

@Log4j2
@Service
public class RenovationService {

  public RenovationStage proceed(Date date) throws Exception {
    log.info("Check for date: " + date.getMonth());
    Thread.sleep(3000);
    return new RenovationStage(Timetable.TIMETABLE.get(date.getMonth()));
  }
}
