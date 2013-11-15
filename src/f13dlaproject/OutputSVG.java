/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static f13dlaproject.Crystal2D.*;
import java.util.List;

/**
 *
 * @author stu738510
 */
public class OutputSVG {
    
     private static OutputSVG instance;
     
     private OutputSVG(){
         
     }
    
    
    public void toFile(List<Crystal2D> c){
    try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("svg");
		doc.appendChild(rootElement);
                
                // set attribute to xmlns
		Attr attr = doc.createAttribute("xmlns");
		attr.setValue("http://www.w3.org/2000/svg");
		rootElement.setAttributeNode(attr);
 
                // set attribute to version
		Attr attr2 = doc.createAttribute("version");
		attr2.setValue("1.1");
		rootElement.setAttributeNode(attr2);
                                              
		// staff elements
		Element circle = doc.createElement("circle");
		rootElement.appendChild(circle);
                
                // set attribute to cx(circle x pos)
		Attr attr3 = doc.createAttribute("cx");
		attr3.setValue("150");
		circle.setAttributeNode(attr3);
                
                // set attribute to cy(circle y pos)
		Attr attr4 = doc.createAttribute("cy");
		attr4.setValue("100");
		circle.setAttributeNode(attr4);
                
                // set attribute to r(circle radius)
		Attr attr5 = doc.createAttribute("r");
		attr5.setValue("20");
		circle.setAttributeNode(attr5);
                
                // set attribute to fil(circle fill color)
		Attr attr6 = doc.createAttribute("fill");
		attr6.setValue("rgb(0,0,255)");
		circle.setAttributeNode(attr6);
 
			
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("C:\\Users\\stu738510\\Desktop\\Crystal.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
    }
        public static OutputSVG outputSVG() {
        if (instance == null) {
            instance = new OutputSVG();
        }
        return instance;
    }
}
