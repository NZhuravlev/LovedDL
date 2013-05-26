package api;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertyLoader {

	protected static Properties properties = new Properties();
	
	public static String get(String property){
		try {
			properties.load(new FileInputStream(new File("."+File.separator+"Configuration.properties")));
			return (String) properties.get(property);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Такой опции нет");
		return property;
	}
	
}
