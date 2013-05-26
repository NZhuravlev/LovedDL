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

public class VKAPI {
	
	private static Document dom;
	private static List<Track> ltbList;

	private static List<Track> getTrackList(Track lovedTrack) throws IOException, SAXException, ParserConfigurationException{
    	
    	//Accumulating a URL for XML with audiotrack data
		StringBuffer buff = new StringBuffer();
    	buff.append(PropertyLoader.get("vk_com_ws_address"));
    	buff.append("audio.search.xml");
    	buff.append("?q=");
    	buff.append(lovedTrack.toString());
    	buff.append("&");
    	buff.append("access_token=");
    	buff.append(PropertyLoader.get("vk_com_access_token"));
    	String urlString = buff.toString();
    	urlString = urlString.replaceAll(" ", "%20");
    	URL httpGet = new URL(urlString);
    	//System.out.println(urlString);
    	   
		URLConnection connection = httpGet.openConnection();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		try{
		dom = db.parse(connection.getInputStream());
		}catch(java.net.ConnectException e){
			getTrackList(lovedTrack);
		}
		dom.getDocumentElement().normalize();
	
		Element docElement = dom.getDocumentElement();
		NodeList nl = docElement.getElementsByTagName("audio");
		ltbList = new ArrayList<Track>();
		if(nl != null && nl.getLength()>0){
			for(int i=0; i<nl.getLength(); i++){
				Element el = (Element)nl.item(i);
				Track ltb = new Track(el.getElementsByTagName("title").item(0).getFirstChild().getNodeValue(),
						el.getElementsByTagName("artist").item(0).getFirstChild().getNodeValue(),
						el.getElementsByTagName("url").item(0).getFirstChild().getNodeValue());
				ltb.setAid(el.getElementsByTagName("aid").item(0).getFirstChild().getNodeValue());
				ltb.setOid(el.getElementsByTagName("owner_id").item(0).getFirstChild().getNodeValue());
				ltbList.add(ltb);				
			}
		}
		return ltbList;		
	}
	
	public static URL getTrackURL(Track lovedTrack, boolean audioAdd) throws IOException, SAXException, ParserConfigurationException{
		URL trackURL = null;
		List<Track> ltList = getTrackList(lovedTrack);
		try{
			trackURL = new URL(ltList.get(0).getTrackURL());
		}catch(java.lang.IndexOutOfBoundsException e){
		System.out.println("нету ни хуя там"+lovedTrack);
		}
		for(Track ltb : ltbList){
		
			if(ltb.getTrackArtist() == lovedTrack.getTrackArtist() && ltb.getTrackName() == lovedTrack.getTrackName()){
				trackURL = new URL(ltb.getTrackURL());
				if(audioAdd){
					addTrack(ltb);
				}
				return trackURL;
			}
		}
		return trackURL;
	}
	
	private static boolean addTrack(Track track) throws IOException{
		StringBuffer buff = new StringBuffer();
    	buff.append(PropertyLoader.get("vk_com_ws_address"));
    	buff.append("audio.add.xml");
    	buff.append("?aid=");
    	buff.append(track.getAid());
    	buff.append("&oid="+track.getOid());
    	buff.append("access_token=");
    	buff.append(PropertyLoader.get("vk_com_access_token"));
    	String urlString = buff.toString();
    	urlString = urlString.replaceAll(" ", "%20");
    	URL httpGet = new URL(urlString);
    	httpGet.openConnection();		
		return true;//TODO
	}
	
	public static void addAllTracks(Track track) throws IOException, SAXException, ParserConfigurationException{
		List<Track> ltList = getTrackList(track);
		for(Track t : ltList){
			if(t.getTrackArtist() == track.getTrackArtist() && t.getTrackName() == track.getTrackName()){
		StringBuffer buff = new StringBuffer();
    	buff.append(PropertyLoader.get("vk_com_ws_address"));
    	buff.append("audio.add.xml");
    	buff.append("?aid=");
    	buff.append(track.getAid());
    	buff.append("&oid="+track.getOid());
    	buff.append("access_token=");
    	buff.append(PropertyLoader.get("vk_com_access_token"));
    	String urlString = buff.toString();
    	urlString = urlString.replaceAll(" ", "%20");
    	URL httpGet = new URL(urlString);
    	httpGet.openConnection();
    	System.out.println(httpGet);
		}}
	}
}
