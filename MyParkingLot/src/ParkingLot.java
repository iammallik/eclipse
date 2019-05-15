public class ParkingLot {
	
	public int slot_id;
	public int entry_point;
	public int floor;
	public Vehicle vehicle;
	
	public ParkingLot(int i1, int f, Vehicle v1) {
		this.slot_id = i1;
		this.floor = f;
		this.vehicle = v1; 
	}
	
	public ParkingLot(Vehicle v1, int entry_point) {
		this.vehicle = v1; 
		this.entry_point = entry_point;
	}
}
