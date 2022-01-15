package ru.otus.spring.integration;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.spring.integration.config.Renovation;
import ru.otus.spring.integration.domain.RenovationStage;

@Log4j2
@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates", "InfiniteLoopStatement"})
@ComponentScan
@EnableIntegration
public class App {

  private static final Integer[] MENU = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

  public static void main(String[] args) throws Exception {
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
    Renovation renovation = ctx.getBean(Renovation.class);
    ForkJoinPool pool = ForkJoinPool.commonPool();

    while (true) {
      Thread.sleep(1000);
      pool.execute(
          () -> {
            Integer month = getRandomMonth();
            log.info("Received month:" + month);

            List<RenovationStage> renovationStage = renovation.process(month);
            for (RenovationStage stage : renovationStage) {
              log.info("For month: " + month + ", was planned work: " + stage.getTitle());
            }
          });
    }
  }

  private static Integer getRandomMonth() {
    return MENU[new Random().nextInt(MENU.length)];
  }
}
