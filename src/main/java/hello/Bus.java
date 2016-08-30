package hello;

public class Bus {

    private final long id;
    private final String linha;
    private final double lat, longi;
	public long getId() {
		return id;
	}
	public String getLinha() {
		return linha;
	}
	public double getLat() {
		return lat;
	}
	public double getLongi() {
		return longi;
	}
	public Bus(long id, String linha, double lat, double longi) {
		super();
		this.id = id;
		this.linha = linha;
		this.lat = lat;
		this.longi = longi;
	}

}
