package karolh95.chowdhury.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import karolh95.chowdhury.model.VehicleDescriptor;
import karolh95.chowdhury.model.designer.VehiclesDesigner;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class VehiclesDesigningController {

	private final VehiclesDesigner designer;

	@PostMapping("model/setVehicles")
	public List<VehicleDescriptor> addVehicles(@RequestBody List<VehicleDescriptor> descriptors) {

		return designer.setVehicles(descriptors);
	}
}
