package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class PokeLocations {
	
	private JSONObject locations;
	private JSONObject conf;
	String current[];
	final String CONFPATH = "../Release/Config/config.json";
	
	public PokeLocations() throws IOException{
	  this.conf  = new JSONObject(readFile(CONFPATH ,  StandardCharsets.UTF_8));
	  this.current = new String[] {this.conf.get("DefaultLatitude").toString(), this.conf.get("DefaultLongitude").toString()}; 
	  this.locations = new JSONObject(readFile("locations.json" ,  StandardCharsets.UTF_8));  
	}
	

	static String readFile(String path, Charset encoding) 
	  throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}
	
	
	public String[] getCurrentLocation(){
		return this.current;
	}
	
	public ArrayList<String> getLocations(){
		 return new ArrayList<>(this.locations.keySet());
	}
	
	public String[] getLatLon(String place){
		String pos = (String) this.locations.get(place);
		return pos.split(",");
	}
	
	public void saveFileConf() throws IOException{
		File conf = new File(CONFPATH);
		FileOutputStream fooStream = new FileOutputStream(conf, false); // true to append
		byte[] myBytes = this.conf.toString(3).getBytes();  
		fooStream.write(myBytes);
		fooStream.close();
	}
	
	public void saveFileLocations() throws IOException{
		File conf = new File("locations.json");
		FileOutputStream fooStream = new FileOutputStream(conf, false); // true to append
		byte[] myBytes = this.locations.toString(3).getBytes();  
		fooStream.write(myBytes);
		fooStream.close();
	}
	
	//Val has to be turned into float
	public void setCoordinate(String name, String val){
		this.conf.put(name, Double.parseDouble(val));
	}
	public void createLocation(String name, String val){
		this.locations.put(name, val);
	}
	
}
