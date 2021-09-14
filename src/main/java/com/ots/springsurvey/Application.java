package com.ots.springsurvey;

import com.ots.springsurvey.service.SurveyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		SurveyService service = ctx.getBean(SurveyService.class);
		service.inputParticipantName();
		service.startSurvey();
		service.printResult();
	}

}
