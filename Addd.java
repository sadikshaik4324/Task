package demo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class Addd {

    public static void main(String argv[]) {
          Scanner sc= new Scanner(System.in);
//        System.out.println("enter data");
//        String data = scanner.nextLine();
        try {
            String filepath = "src/demo/example.xml";

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node root = doc.getDocumentElement();

            // Create a new "emp" element
            Element empElement = doc.createElement("emp");

            // Create and append child elements to "emp" element
            Element idElement = doc.createElement("id");
            System.out.println("Enter the id value:");
            String  idvalue=sc.nextLine();
            idElement.appendChild(doc.createTextNode(idvalue));
            empElement.appendChild(idElement);

            Element nameElement = doc.createElement("name");
            System.out.println("Enter the name value:");
            String namevalue=sc.nextLine();
            nameElement.appendChild(doc.createTextNode(namevalue));
            empElement.appendChild(nameElement);

            Element salaryElement = doc.createElement("salary");
            System.out.println("Enter the salary value:");
            String salaryvalue=sc.nextLine();
            salaryElement.appendChild(doc.createTextNode(salaryvalue));
            empElement.appendChild(salaryElement);

            // Add the new "emp" element to the root element
            root.appendChild(empElement);
            System.out.println("enter the attribute value!!!!!!!!!");
            String attribute=sc.next();
            
            empElement.setAttribute("type", attribute);

            // Write the updated document back to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/demo/example.xml"));
            transformer.transform(source, result);
         // Step 4: Validate the generated XML against the schema
         			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
         			Schema schema = schemaFactory.newSchema(new File("src/demo/example.xsd"));
         			Validator validator = schema.newValidator();
         			validator.validate(new StreamSource(new File("src/demo/example.xml")));
                    System.out.println("New element added to the XML file.");
         			System.out.println("Validation successful: add.xml is valid against the schema.");

            System.out.println("New element added to the XML file.");

        } catch (ParserConfigurationException | IOException | TransformerException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }
}

