package at.spot.core.infrastructure.annotation.model;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemType {
	
	/**
	 * This is the bean name of the item.
	 */
	String typeCode() default "";
}