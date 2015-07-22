package cn.edu.sjtu.dcl.xml;

import java.io.OutputStreamWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLHandler
{
	public static void callWriteXMLFile(Document doc, OutputStreamWriter w, String encoding)
	{
		try
		{
			Source source = new DOMSource(doc);
			Result ret = new StreamResult(w);
			TransformerFactory tf = TransformerFactory.newInstance();
			tf.setAttribute("indent-number", new Integer(4));
			Transformer xformer=tf.newTransformer();
			xformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			xformer.setOutputProperty(OutputKeys.INDENT, "yes");
			xformer.transform(source, ret);
		}
		catch(TransformerConfigurationException e)
		{
			e.printStackTrace();
		}
		catch(TransformerException e)
		{
			e.printStackTrace();
		}
	}
}
