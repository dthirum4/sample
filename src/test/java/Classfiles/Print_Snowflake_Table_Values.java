package Classfiles;

import java.io.FileReader;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;

public class Print_Snowflake_Table_Values {
	
	public static void print_table_values() throws IOException, Exception {
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
		
		//print the table
		String selectSQL = "SELECT * FROM OIBA_DEV_ODW_DB.CURATED.DASHBOARD_PERFORMANCE_MONITOR";
		ResultSet resultSet1 = statement.executeQuery(selectSQL);
		while (resultSet1.next()) {
			String dashboardName = resultSet1.getString("DASHBOARD_NAME");
			String reportLoadStartTime = resultSet1.getString("REPORT_LOAD_START_TIME");
			String reportLoadEndTime = resultSet1.getString("REPORT_LOAD_END_TIME");
			int reportElapsedTime = resultSet1.getInt("REPORT_ELAPSED_TIME");
			String reportPerformanceStatus = resultSet1.getString("REPORT_PERFORMANCE_STATUS");
		
			System.out.println("Dashboard Name: " + dashboardName);
			System.out.println("Report Load Start Time: " + reportLoadStartTime);
		    System.out.println("Report Load End Time: " + reportLoadEndTime);
		    System.out.println("Report Elapsed Time: " + reportElapsedTime);
		    System.out.println("Report Performance Status: " + reportPerformanceStatus);
		}
		//close the connection
		resultSet1.close();
		statement.close();
		connection.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			print_table_values();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
