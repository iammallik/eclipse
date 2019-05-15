/*
 * 
 *   create_parking_lot 2 2
 * 		
 * 	 park KA-01-1 white 2
 *   park KA-01-2 red 2
 *   park KA-01-3 white 1
 * 
 *   reg_no_for_cars_with_color white
 *   
 *   get_slots_with_color white
 *   
 *   slot_of_car_with_reg_no KA-01-1
 *   
 *   status
 *   
 *   leave 3
 * 
 * */


import java.util.*;
import java.io.*;

public class EntryPoint {
	
	static int no_of_floors;
	static int total_slots;

	static HashSet<Floor> floors;
	static HashSet<Integer> availble_slots;
	static HashMap<String, HashSet<ParkingLot>> colour_plot;
	static HashMap<String, ParkingLot> reg_no_lot;
	
//	static HashMap<Vehicle, Floor> vehicle_floor;
//	static HashSet<Vehicle> vehicles;	
//	public EntryPoint(int slots, int entry_points) {
//		floors.add(new Floor(slots, entry_points, floors.size()));
//		p("sucessfully created parking lot with "+slots+" slots and "+entry_points+" entry points");
//		
//	}
	
	public static void main(String[] args) throws IOException {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// br.readLine().split("\\s");
		boolean[] occupied = new boolean[1000];
		total_slots = 0;
		availble_slots = new HashSet<Integer>();
		colour_plot = new HashMap<String, HashSet<ParkingLot>>();
		reg_no_lot = new HashMap<String, ParkingLot> ();
		Scanner sc = new Scanner(System.in);
		pl("please enter a command!");
		
		//<------------------------------------->
		
		while(true) {
			//pl("");
			
			String input = sc.nextLine();
			String[] arr = input.split("\\s");
			//p("input read is:"+input);
			//p(arr[0]+", "+arr[1]+", "+arr[2]);
			if(arr[0].equals("create_parking_lot")) {
				create_parking_lot(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
			}else if(arr[0].equals("park")) {
				park(arr[1], arr[2], Integer.parseInt(arr[3]));
			}else if(arr[0].equals("reg_no_for_cars_with_color")) {
				get_vehicles_with_clour(arr[1]);
			}else if(arr[0].equals("get_slots_with_color")) {
				get_slots(arr[1]);
			}else if(arr[0].equals("slot_of_car_with_reg_no")) {
				get_slot_for_no(arr[1]);
			}else if(arr[0].equals("status")) {
				status();
			}else if(arr[0].equals("leave")) {
				leave(Integer.parseInt(arr[1]));
			}else if(arr[0].equals("exit")) {
				break;
			}else {
				//pl("command is not valid!");
			}
		}
	}
	
	
	static void p(String s) {
		System.out.print(s);
	}
	
	static void pl(String s) {
		System.out.println(s);
	}
	
	static void create_parking_lot(int slots, int entry_points) {
		if(slots <= 0 || entry_points <= 0) {
			pl("enter valid input!");
			return;	
		}
		if(floors == null) floors = new HashSet<Floor>();
		ArrayList<Integer> slot_ids = new ArrayList<Integer>();
		for(int i=total_slots+1; i <= total_slots+slots; i++) {
			slot_ids.add(i);
		}
		total_slots += slots;
		floors.add(new Floor(slot_ids, entry_points, floors.size()));
		pl("sucessfully created parking lot with "+slots+" slots and "+entry_points+" entry points");
	}
	
	static void park(String no, String colour, int entry_point) {
		if(reg_no_lot.get(no) != null) {
			pl("car with given registraion exists already!");
			return;
		}
		Vehicle v  = new Vehicle(no, colour);
		boolean parked  = false;
		for(Floor floor: floors) {
			if(floor.is_parking_available()) {
				if(colour_plot.get(colour) == null) colour_plot.put(colour, new HashSet<ParkingLot>());
				ParkingLot p = new ParkingLot(v, entry_point);
				colour_plot.get(colour).add(p);
				p.vehicle = v;
				 String s = floor.park(p);
				 reg_no_lot.put(no, p);
				 parked = true;
				 pl(s);
				 break;
			}
		}
		if(!parked) pl("Parking lot not available!");
	}
	
	static void get_vehicles_with_clour(String color) {
		for(ParkingLot p: colour_plot.get(color)) {
			p(p.vehicle.reg_no+", ");
		}
		pl(";");
	}
	
	static void get_slots(String color) {
		if(colour_plot.get(color) == null) {
			pl("vehicles doesn't exist with that color!");
			return;
		}
		for(ParkingLot p: colour_plot.get(color)) {
			p(p.slot_id+", ");
		}
		pl(";");
	}
	
	static void get_slot_for_no(String no) {
		if(reg_no_lot.get(no) == null){
			pl("That vehicle doesn't exist!");
			return;
		}
		
		pl("slot:"+reg_no_lot.get(no).slot_id);
	}
	
	static void status() {
		pl("No Registration Slot-No Color");
		int i =0;
		for(String s:reg_no_lot.keySet()) {
			i++;
			ParkingLot p = reg_no_lot.get(s);
			pl(i+" "+p.vehicle.reg_no+", "+p.slot_id+", "+p.vehicle.colour);
		}
	}
	
	static void leave(int slot_id) {
		boolean availble = false;
		for(Floor f: floors ) {
			if(f.all_slots.contains(slot_id)) {
				String reg_no = f.leave(slot_id);
				availble = true;
				reg_no_lot.remove(reg_no);
				pl("Slot freed");
			}
		}
		if(!availble) pl("Given slot doesn't exist");
	}
}
