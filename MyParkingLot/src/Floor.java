import java.util.*;

public class Floor {
	
	int total_slots; 		  // no of slots
	boolean full;  // 
	int entry_points;
	int floor_id; 
	int filled_slots;
	
	
	HashMap<Integer, ParkingLot> occupied_slots;  // slot_id, 
	HashSet<Integer> empty_slots;
	//HashMap<Vehicle, ParkingLot> vehicle_parking; 
	public HashSet<Integer> all_slots;
	
	
	public Floor(ArrayList<Integer> slots, int entry_points, int floor) {
		this.total_slots = slots.size();
		this.entry_points = entry_points;
		this.floor_id = floor;
		this.filled_slots = 0;
		occupied_slots = new HashMap<Integer, ParkingLot>();
		empty_slots = new HashSet<Integer>();
		//vehicle_parking = new HashMap<Vehicle, ParkingLot>();
		all_slots = new HashSet<Integer>();
		
		create_slots(slots);
	}
	
	public boolean is_parking_available() {
		return total_slots > occupied_slots.size();
	}
	
	public String park(ParkingLot p) { // ParkingLot p
		if(!is_parking_available()) return "Parking lot not availble at this floor "+this.floor_id;
		int slot_id =  empty_slots.iterator().next();
		if(p.entry_point < 1 || p.entry_point > entry_points) return "Entry Point "+p.entry_point+" not valid, entry_point should be between 1 & "+entry_points;
		p.slot_id = slot_id;
		occupied_slots.put(slot_id, p);
		empty_slots.remove(slot_id);
		return ("Allocated slot no: "+slot_id);
	}
	
	public String leave(int slot_id) { // remove car with registration no
		if(occupied_slots.get(slot_id) != null) {
			ParkingLot p = occupied_slots.get(slot_id);
			occupied_slots.remove(slot_id);
			empty_slots.add(p.slot_id);
			return p.vehicle.reg_no;
		}
		
		return "Given slot is empty already!";
	}
	
	private void create_slots(ArrayList<Integer> slots) {
		if(empty_slots == null) empty_slots = new HashSet<Integer>();
		for(int i:slots) {
			empty_slots.add(i);
			all_slots.add(i);
		}
	}
	
}
