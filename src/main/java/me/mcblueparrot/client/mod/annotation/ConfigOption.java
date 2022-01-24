package me.mcblueparrot.client.mod.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigOption {

	/**
	 * The option's name.
	 * @return The name.
	 */
	String value();

	int priority() default 0;

}
