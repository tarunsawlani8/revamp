package com.amaropticals.common;

import java.util.Calendar;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class CommonUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
	private static long invoiceId;

	public static long getNextInvoiceId() {
		long nextInvoiceId;

		Calendar cal = Calendar.getInstance();

		StringBuilder date = new StringBuilder();
		date.append(cal.get(1));
		if (cal.get(2)+1 < 10) {
			date.append(0);
		}
		date.append(cal.get(2)+1);
		if (cal.get(5) < 10) {
			date.append(0);
		}
		date.append(cal.get(5));

		if (Long.parseLong(date.toString() + "000") > invoiceId) {
			nextInvoiceId = Long.parseLong(date.toString() + "001");
		} else {

			nextInvoiceId = invoiceId + 1;
		}
		
		invoiceId = nextInvoiceId;

		LOGGER.info("Next Invoice Id={}", nextInvoiceId);
		return nextInvoiceId;
	}
	
	
/*public static boolean sendMessages(Orders orders) {	
		
		boolean sent = false;
		try {

			
			DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
			SSLContext sslContext = SSLContext.getInstance("SSL");
			ServerTrustManager manager = new ServerTrustManager();
			sslContext.init(null, new TrustManager[] {manager}, null);
			defaultClientConfig.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(null, sslContext));
			Client client = Client.create(defaultClientConfig);

			WebResource webResource = client
			   .resource("https://www.fast2sms.com/dev/bulk");

			ClientResponse response = webResource.
					header("authorization", "4cuqXLQCY3ePfnk7tpz5vsG2DoNiBMhFI0RdZKrwSA98bxOWyE4mZQ1iXFxpAY6wDKegnzjIUyW8J9st")
				  .header("cache-control", "no-cache")
				  .header("content-type", "application/x-www-form-urlencoded")
					.type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, getMessage(orders));

		if (response.getStatus() == 200 || response.getStatus() == 201 ) {
			sent = true;
			} else {
				
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				
				System.out.println(output);
			 
			}
		
		

		 } catch (Exception e) {

			e.printStackTrace();

		  }
//	return response.getStatusText();
		return sent;
	}*/

	
/*	private static String getMessage(Orders orders) {
		
		String message = "sender_id=AMROPT&message=543&language=english&route=qt&numbers="+orders.getCustomerContact()+
				  "&variables={#DD#}|{#EE#}&variables_values="+orders.getCustomerName()+"|"+orders.getDeliveryStatus()+"&flash=0";
		
		
		if ("READY FOR PICKUP".equalsIgnoreCase(orders.getDeliveryStatus())) {
			
		message = 	"sender_id=AMROPT&message=542&language=english&route=qt&numbers="+orders.getCustomerContact()+
				  "&variables={#EE#}&variables_values= "+orders.getCustomerName()+"&flash=0";
		}
		
		return message;
		
	}*/

	public static long getInvoiceId() {
		return invoiceId;
	}

	public static synchronized void setInvoiceId(long invoiceId) {
		CommonUtils.invoiceId = invoiceId;
	}
}
