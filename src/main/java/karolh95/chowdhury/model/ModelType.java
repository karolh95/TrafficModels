package karolh95.chowdhury.model;

import com.fasterxml.jackson.annotation.JsonValue;

import karolh95.chowdhury.model.factory.ASEPModelFactory;
import karolh95.chowdhury.model.factory.NagelSchreckenbergModelFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ModelType {

	NAGEL_SCHRECKENBERG("nagel-schreckenberg", NagelSchreckenbergModelFactory.BEAN_NAME),
	ASEP("asep", ASEPModelFactory.BEAN_NAME);

	@Getter
	@JsonValue
	private final String name;

	@Getter
	private final String modelFactoryBeanName;

	public static ModelType of(String type) {

		if (type == null) {
			return null;
		}

		for (ModelType modelType : ModelType.values()) {

			if (type.equals(modelType.getName())) {
				return modelType;
			}
		}

		throw new IllegalArgumentException("ModelType: unknown value: " + type);
	}
}
