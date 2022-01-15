package ru.otus.spring.integration.config;

import java.util.List;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.integration.domain.RenovationStage;

@MessagingGateway
public interface Renovation {

  @Gateway(requestChannel = "timetableChannel", replyChannel = "renovationStageChannel")
  List<RenovationStage> process(Integer date);
}
