package karolh95.chowdhury.service;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ModelService implements Runnable {

	private final DataCollectingService dataCollecting;
	private final ModelFactoryService factoryService;

	@Override
	public void run() {

		updateModel();
		sendData();
	}

	private void updateModel() {

		factoryService.getModel().update();
	}

	private void sendData() {

		dataCollecting.sendData();
	}
}
