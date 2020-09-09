package karolh95.chowdhury.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Road;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataCollectingService {

	private final SimpMessagingTemplate msgTemplate;
	private final Road road;
	
	public void sendData() {

		msgTemplate.convertAndSend("/model/vehicles", road.getCells());
	}
}
