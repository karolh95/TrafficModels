package karolh95.chowdhury.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.Road;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ModelService implements Runnable {

	private final SimpMessagingTemplate msgTemplate;
	private final Road road;

	private Model model;

	@Override
	public void run() {

		updateModel();
		sendData();
	}

	public void create() {

		model.create();
	}
	
	public void changeModel(Model model) {
		
		this.model = model;
		model.reset();
		model.create();
	}

	private void updateModel() {

		if (model != null) {
			model.update();
		}
	}

	private void sendData() {

		msgTemplate.convertAndSend("/model/vehicles", road.getCells());
	}
}
