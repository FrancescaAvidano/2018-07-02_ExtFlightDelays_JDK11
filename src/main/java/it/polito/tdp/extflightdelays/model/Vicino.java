package it.polito.tdp.extflightdelays.model;

public class Vicino implements Comparable<Vicino>{
	private Airport aeroporto;
	private Double distanza;
	/**
	 * @param aeroporto
	 * @param distanza
	 */
	public Vicino(Airport aeroporto, Double distanza) {
		super();
		this.aeroporto = aeroporto;
		this.distanza = distanza;
	}
	public Airport getAeroporto() {
		return aeroporto;
	}
	public void setAeroporto(Airport aeroporto) {
		this.aeroporto = aeroporto;
	}
	public Double getDistanza() {
		return distanza;
	}
	public void setDistanza(Double distanza) {
		this.distanza = distanza;
	}
	@Override
	public int compareTo(Vicino o) {
		return -this.distanza.compareTo(o.getDistanza());
	}
	@Override
	public String toString() {
		return "Vicino [aeroporto=" + aeroporto + ", distanza=" + distanza + "]";
	}
	
	
}
