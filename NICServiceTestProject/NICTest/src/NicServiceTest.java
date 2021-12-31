import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.axis2.java.security.TrustAllTrustManager;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.security.SSLProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import com.sap.nic.client.NICService.APIStub;
import com.sap.nic.client.NICService.APIStub.GetInvestorInfo;
import com.sap.nic.client.NICService.APIStub.GetInvestorInfoResponse;
import com.sap.nic.client.NICService.APIStub.NIC_Request;

public class NicServiceTest {

	public static void main(String[] args) {
		
		try (InputStream input = new FileInputStream("./config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);


            String url = prop.getProperty("nic.url","http://rhoysrdev1.mysagia.gov/NIC_GetService/API.asmx");
            int idType = Integer.parseInt(prop.getProperty("nic.id.type","3"));
            String number = prop.getProperty("nic.id.number","19800708");
            String dob = prop.getProperty("nic.dob","19800708");
			
            System.out.println("number:: "+number);
			// TODO Auto-generated method stub
			try {
				
				 SSLContext sslCtx = SSLContext.getInstance("TLS");
				 sslCtx.init(null, new TrustManager[] {new TrustAllTrustManager()}, null);
				APIStub client = new APIStub(url);
				client._getServiceClient().getOptions().setProperty(
						HTTPConstants.CUSTOM_PROTOCOL_HANDLER,
						new Protocol("https", (ProtocolSocketFactory)new SSLProtocolSocketFactory(sslCtx), 443));
				
				GetInvestorInfo investorInfo = new GetInvestorInfo();
				NIC_Request request = new NIC_Request();
				request.setID_Type(idType);
				request.setID_Number(number);
				request.setDOB(dob);
				investorInfo.setRequest(request);
				//investorInfo.
				GetInvestorInfoResponse response = client.getInvestorInfo(investorInfo);
				System.out.println(response.getGetInvestorInfoResult().getResult());
				System.out.println(response.getGetInvestorInfoResult().getExpiryDate());
				System.out.println(response.getGetInvestorInfoResult().getFirstName_AR());
				System.out.println(response.getGetInvestorInfoResult().getFullName_EN());
				System.out.println(response.getGetInvestorInfoResult().getGender());
				System.out.println(response.getGetInvestorInfoResult().getLastName_AR());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	    } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
