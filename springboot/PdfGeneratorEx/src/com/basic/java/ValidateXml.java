package com.basic.java;

import java.io.File;

import javax.xml.validation.SchemaFactory;

public class ValidateXml {

	public static void main(String[] args) {
		System.out.println("Xml validation with xsd file :"+validateXmlSchema("a","b"));
	}
	
	public static boolean validateXmlSchema(String xsdPath, String xmlPath) {
		File f  = new File("");
		SchemaFactory factory = SchemaFactory.newInstance(xmlPath);
		
		return false;
	}
	
}
