package Classfiles;

import java.io.FileReader;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.Statement;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
//import org.bouncycastle.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
import org.bouncycastle.operator.InputDecryptorProvider;

public class Snowflake_connection {
		
		public static void add_values_to_snowflake_table(List<String> values) throws IOException, Exception
		{
		// TODO Auto-generated method stub
		// Register the Bouncy Castle provider
		Security.addProvider(new BouncyCastleProvider());
		
		// Snowflake JDBC connection properties
		String account=Select_credentials.get_snowflake_credentials(0, 0);
		String user = Select_credentials.get_snowflake_credentials(1, 0);
		String privateKeyFile = ".\\Authentication\\oiba_dev_snow_enc.p8";
		String privateKeyPassword = Select_credentials.get_snowflake_credentials(2, 0);
		String Database=Select_credentials.get_snowflake_credentials(3, 0);
		String Warehouse=Select_credentials.get_snowflake_credentials(4, 0);
		String Role=Select_credentials.get_snowflake_credentials(5, 0);
		String Schema=Select_credentials.get_snowflake_credentials(6, 0);
		
		// Load the private key
		PEMParser pemParser = new PEMParser(new FileReader(privateKeyFile)); 
		Object parsedobject = pemParser.readObject();
		pemParser.close();
		
		JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
		
		if (!(parsedobject instanceof PKCS8EncryptedPrivateKeyInfo)) {
			throw new IllegalArgumentException("Invalid PEM file format");
		}
		PKCS8EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = (PKCS8EncryptedPrivateKeyInfo) parsedobject;
		InputDecryptorProvider decryptorProvider= new JceOpenSSLPKCS8DecryptorProviderBuilder().setProvider("BC")
				.build(privateKeyPassword.toCharArray());
		
		PrivateKeyInfo privateKeyInfo = encryptedPrivateKeyInfo.decryptPrivateKeyInfo(decryptorProvider);
		PrivateKey privatekey = converter.getPrivateKey(privateKeyInfo);
		
		// Set up connection properties
		String url = "jdbc:snowflake://" + account;
		Properties properties = new Properties();
		properties.put("user", user);
		properties.put("privateKey", privatekey);
		properties.put("db", Database);
		properties.put("warehouse", Warehouse);
		properties.put("role", Role);
		properties.put("schema", Schema);
		
		Connection connection = DriverManager.getConnection(url, properties);
	
		//System.out.println("Connection established successfully.");
		Statement statement = connection.createStatement(); 
		
		
		//set report_status if report_elapsed_time > 60000
		if (Integer.parseInt(values.get(3)) > 60000) {
			values.set(4, "Dashboard is loading more than 1 minute");
		} else {
			values.set(4, "SUCCESS");
		}
		
		//insert list values into table
		String insertSQL = "INSERT INTO OIBA_DEV_ODW_DB.CURATED.DASHBOARD_PERFORMANCE_MONITOR (DASHBOARD_NAME, REPORT_LOAD_START_TIME,REPORT_LOAD_END_TIME,REPORT_ELAPSED_TIME,REPORT_PERFORMANCE_STATUS) VALUES ('"+values.get(0)+"', '"+values.get(1)+"', '"+values.get(2)+"', "+values.get(3)+", '"+values.get(4)+"')";
		statement.executeUpdate(insertSQL);
		System.out.println("Data inserted successfully in table for "+values.get(0));
		
		//close the connection
		statement.close();
		connection.close();
		
 }

    	public static void main(String[] args) throws Exception {
    	       // add_values_to_snowflake_table();
    	}
    	
}




