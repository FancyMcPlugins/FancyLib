package de.oliver.fancylib.tests.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FPBeforeEach is a custom annotation designed to be used on methods that should be executed before each test method.
 * Methods annotated with FPBeforeEach are typically used to perform setup operations needed before executing each test case.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FPBeforeEach {
}

