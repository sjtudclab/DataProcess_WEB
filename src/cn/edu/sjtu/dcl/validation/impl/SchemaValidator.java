package cn.edu.sjtu.dcl.validation.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cn.edu.sjtu.dcl.validation.IValidator;
import cn.edu.sjtu.dcl.xml.XMLHandler;

public class SchemaValidator implements IValidator
{
	private String schema;//the schema path
	public SchemaValidator(String schema)
	{
		this.schema = schema;
	}
	public boolean validate(String jcdlPath)
	{
		boolean isValid = false;
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		File schemaFile = new File(schema);
		try
		{
			adjustStartAndEnd(jcdlPath);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(jcdlPath);
			validator.validate(source);
			isValid = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			isValid = false;
		}
		return isValid;
	}
	
	private void adjustStartAndEnd(String jcdlPath) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(new File(jcdlPath));
		Element root = doc.getDocumentElement();
		NodeList start = doc.getElementsByTagName("start");
		if(start.getLength() > 0 && !root.getFirstChild().getNextSibling().getNodeName().equals("start"))
		{
			//System.out.println("1");
			Node newStart = start.item(0).cloneNode(true);
			start.item(0).getParentNode().removeChild(start.item(0));
			root.insertBefore(newStart, root.getFirstChild());
		}
		NodeList end = doc.getElementsByTagName("end");
		if(end.getLength() > 0 && !root.getLastChild().getPreviousSibling().getNodeName().equals("end"))
		{
			//System.out.println("2");
			Node newEnd = end.item(0).cloneNode(true);
			end.item(0).getParentNode().removeChild(end.item(0));
			root.appendChild(newEnd);
		}
		
		FileOutputStream outStream = null;
		try
		{
			outStream = new FileOutputStream(jcdlPath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		OutputStreamWriter outWriter = new OutputStreamWriter(outStream);
		XMLHandler.callWriteXMLFile(doc, outWriter,"utf-8");
		try
		{
			outWriter.close();
			outStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		String test = "/home/hadoop/test.xml";
		SchemaValidator sv = new SchemaValidator("/home/hadoop/Workspaces/MyEclipse 10/MRComposition/WebRoot/resource/JCDL.xsd");
		System.out.println(sv.validate(test));
	}
}
