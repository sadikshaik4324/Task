package demo;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;



public class XmlValidator {

    public static void main(String[] args) {
        String xmlFilePath = "src/demo/example.xml";
        String xsdFilePath = "src/demo/example.xsd";

        boolean isValid = validateXMLSchema(xsdFilePath, xmlFilePath);

        if (isValid) {
            System.out.println("The XML is valid.");
        } else {
            System.out.println("The XML is not valid.");
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File xsdFile = new File(xsdPath);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            validator.validate(source);
            return true;
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}