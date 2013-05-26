package api;
public class Track {
    
    public Track(String trackName, String trackArtist) {
    	this(trackName,trackArtist, null);
    }
    
    public Track(String trackName, String trackArtist, String trackURL) {
        this.trackName = trackName;
        this.trackArtist = trackArtist;
        this.trackURL = trackURL;
    }
    
    //GETTERS & SETTERS

    public String toString() {
        return trackArtist + " - " + trackName;
    }
    
    public String getTrackURL() {
		return trackURL;
	}

	public void setTrackURL(String trackURL) {
		this.trackURL = trackURL;
	}

    public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	
    public String getTrackArtist() {
		return trackArtist;
	}

	public void setTrackArtist(String trackArtist) {
		this.trackArtist = trackArtist;
	}
	private String trackArtist = null;
	private String trackURL = null;
	private String trackName = null;
	private String aid;
	private String oid;
	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String string) {
		this.oid = string;
	}
}