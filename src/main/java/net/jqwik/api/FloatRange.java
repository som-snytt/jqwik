package net.jqwik.api;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FloatRange {
	float min() default 0.0f;
	float max();
}