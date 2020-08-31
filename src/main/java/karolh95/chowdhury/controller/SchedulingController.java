package karolh95.chowdhury.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import karolh95.chowdhury.service.SchedulingService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("task")
@RequiredArgsConstructor
public class SchedulingController {

	private final SchedulingService schedulingService;

	@GetMapping("run")
	public void run() {

		schedulingService.runScheduledTask();
	}

	@GetMapping("pause")
	public void pause() {

		schedulingService.cancelScheduledTask();
	}
}
