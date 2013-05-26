import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import api.LastFMAPI;
import api.Track;
import api.PropertyLoader;
import api.Utils;
import api.VKAPI;


public class RunLoading {

	/**
	 * @param args
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		List<Track> arr = LastFMAPI.getUserLovedTracks(PropertyLoader.get("last_fm_user"));
		for(Track ltb : arr){
			Utils.download(VKAPI.getTrackURL(ltb, false), ltb.toString());
		}
	}
}
