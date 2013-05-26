package api;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LastFMAPI {
	
	public static List<Track> getUserLovedTracks(String user) throws IOException, 
	ParserConfigurationException, SAXException{
		List<Track> ltList;
		//TODO Make "Utis" class for all extra methods, include method for accumulating URL
		StringBuffer buff = new StringBuffer();
        buff.append(PropertyLoader.get("last_fm_ws_address"));
        buff.append("?method=");
        buff.append("user.getlovedtracks");
        buff.append("&user=");
        buff.append(user);
        buff.append("&api_key=");
        buff.append(PropertyLoader.get("last_fm_api"));
        String urlString = buff.toString();
        
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = (Document) db.parse(connection.getInputStream());
        doc.getDocumentElement().normalize();
        
        NodeList nl = doc.getElementsByTagName("track");
        
        ltList = new ArrayList<Track>(nl.getLength());
        for (int i=0; i<nl.getLength(); i++){
        	Element el = (Element)nl.item(i);
        	String trackname = el.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
        	
        	Element internals = (Element)nl.item(i).getChildNodes();;
        	Element artistEl = (Element)internals.getElementsByTagName("artist").item(0);
        	String artist = artistEl.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
        	
        	Track ltb = new Track(trackname, artist);
        	ltList.add(ltb);        	
        }
		
		return ltList;
	}

}
