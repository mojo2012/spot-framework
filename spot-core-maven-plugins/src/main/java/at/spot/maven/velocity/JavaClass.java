package at.spot.maven.velocity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@TemplateFile("class.vm")
public class JavaClass extends AbstractJavaType {
	private static final long serialVersionUID = 1L;

	protected final List<JavaField> fields = new ArrayList<>();

	public List<JavaField> getFields() {
		return Collections.unmodifiableList(fields);
	}

	public void addField(JavaField field) {
		this.getFields().add(field);
		this.imports.add(field.getType().getName());
	}
}