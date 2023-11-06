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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Add {

    public static void main(String argv[]) {
    	 Scanner scanner=new Scanner(System.in);
        System.out.println("enter data");
        String data=scanner.nextLine();
        try {
            String filepath = "src/demo/example.xml";

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node root = doc.getDocumentElement();
           
            System.out.println("enter a child element");
             String ele=scanner.next();


            // Create a new child element
            Element newElement = doc.createElement(ele);
            newElement.appendChild(doc.createTextNode(data));

            // Add the new child element to the root element
            root.appendChild(newElement);

            // Write the updated document back to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

          System.out.println("New  element added to the XML file.");

        } catch (ParserConfigurationException | IOException | TransformerException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }
}

