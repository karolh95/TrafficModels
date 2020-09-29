package karolh95.chowdhury.model;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.component.Road;

@Component
public class NullModel extends Model {

	public NullModel(Road road) {
		super(road);
	}

	@Override
	public void update() {

	}

}
