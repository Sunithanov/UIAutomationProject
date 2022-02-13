package com.automation.blazedemo.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
 * Represents test annotation transformer listener implements IAnnotationTransformer.
 * @author Sunitha
 * 
 */
public class AnnotationTransformer implements IAnnotationTransformer{

	
	/*
	 * (non-Javadoc)
	 * @see org.testng.IAnnotationTransformer#transform(org.testng.annotations.ITestAnnotation, java.lang.Class, java.lang.reflect.Constructor, java.lang.reflect.Method)
	 * 
	 * It takes four parameters. First parameter is of type ITestAnnotation and it represents @Test annotation. 
	 * Most common use of @Test annotation is at method level but it can also be placed at class or constructor level.
	 * The last three parameters tell us, on which Java element the annotation was found: a class, a constructor, or a constructor. 
	 * Only one of them will be non-null.
	 * @param testannotation -> It represents @Test annotation.
	 * @param testClass -> It tell us, on which Java element the annotation was found: a class.
	 * @param testConstructor -> It tell us, on which Java element the annotation was found: a constructor.
	 * @param testMethod -> It tell us, on which Java element the annotation was found: a constructor.
	 * @return boolean -> Returns true if the test method has to be retried, false otherwise.
	 */
	
	  public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	        annotation.setRetryAnalyzer(RetryFailedTestCases.class);
	    }

}
	
	 
	