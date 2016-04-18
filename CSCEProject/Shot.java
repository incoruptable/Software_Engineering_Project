
public class Shot {

	private String shotID;
	private String manufacturer;
	private String manufacturerPN;
	private String vendor;
	private String vendorPN;
	private String commonName;
	private int quantity;
	private int parLevel;
	private int reorderLevel;
	private boolean phaseOut;
	private boolean active;
	private String Notes;
	public String getShotID() {
		return shotID;
	}
	public void setShotID(String shotID) {
		this.shotID = shotID;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getManufacturerPN() {
		return manufacturerPN;
	}
	public void setManufacturerPN(String manufacturerPN) {
		this.manufacturerPN = manufacturerPN;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getVendorPN() {
		return vendorPN;
	}
	public void setVendorPN(String vendorPN) {
		this.vendorPN = vendorPN;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getParLevel() {
		return parLevel;
	}
	public void setParLevel(int parLevel) {
		this.parLevel = parLevel;
	}
	public int getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
	public boolean isPhaseOut() {
		return phaseOut;
	}
	public void setPhaseOut(boolean phaseOut) {
		this.phaseOut = phaseOut;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
}
