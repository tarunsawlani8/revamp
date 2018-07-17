package com.amaropticals.revamp;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com.amaropticals.model.CreateInvoiceRequest;
import com.amaropticals.model.ItemModel;
import com.amaropticals.model.LensModel;

public class FOPPdfDemo {

    public static void main(String[] args) throws JAXBException {
        FOPPdfDemo fOPPdfDemo = new FOPPdfDemo();
        
        CreateInvoiceRequest model = new CreateInvoiceRequest();
        model.setInvoiceId(18070601);
        model.setTotalAmount("120");
        model.setPendingAmount("0");
        model.setInitialAmount("123");
        model.setName("aa");
        ItemModel item = new ItemModel();
        item.setProductName("FRAME RIMLESS");
        item.setBuyQuantity(1);
        item.setUnitPrice(120);
        item.setTotalCost("120");
        
        ItemModel item2 = new ItemModel();
        item2.setLensActive(true);
        item2.setProductName("ARC");
        item2.setBuyQuantity(2);
        item2.setUnitPrice(240);
        item2.setTotalCost("480");
        LensModel lefteye0 = new  LensModel();
        LensModel lefteye1 = new  LensModel();
        LensModel righteye0 = new  LensModel();
        LensModel righteye1 = new  LensModel();
        lefteye0.setSph("-0.5");
        lefteye0.setCyl("-1.0");
        lefteye0.setAxial("-1.5");
        lefteye1.setSph("0.5");
       lefteye1.setCyl("1.0");
       lefteye1.setAxial("1.5"); 
       
       
       righteye0.setSph("-2.0");
       righteye0.setCyl("-2.5");
       righteye0.setAxial("-3.0");
       
       righteye1.setSph("2.0");
       righteye1.setCyl("2.5");
       righteye1.setAxial("3.0");
       item2.setLeftEye(Arrays.asList(lefteye0, lefteye1));
        item2.setRightEye(Arrays.asList(righteye0, righteye1));
        model.setPurchaseItems(Arrays.asList(item,item2 ));
        
        
        JAXBContext jaxbContext = JAXBContext.newInstance(CreateInvoiceRequest.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(model, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
        try {
            fOPPdfDemo.convertToPDF(xmlString);
        } catch (FOPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    
    public void convertToPDF(String xml)  throws IOException, FOPException, TransformerException {
        // the XSL FO file
        File xsltFile = new File("C:\\Users\\Sonu\\Desktop\\empx.xsl");
        // the XML file which provides the input
        StringReader s = new StringReader(xml);	
        StreamSource xmlSource = new StreamSource(s) ;    // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance(new File("C:\\DemoWorld\\revamp\\src\\main\\resources\\pdf.xconf").toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // Setup output
        OutputStream out;
        out = new java.io.FileOutputStream("C:\\Users\\Sonu\\Desktop\\emp.pdf");
    
        try {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}