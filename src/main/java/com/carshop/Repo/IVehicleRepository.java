package com.carshop.Repo;

import java.util.List;

import com.carshop.model.Vehicle;

public interface IVehicleRepository {

	public int addVehicle(Vehicle vehicle);
		
	public int deleteVehicle(int id) ;
	
	public int UpdateVehicle(int id,Vehicle newCarData) ;
	
	public Vehicle getVehicle(int id) ;
	
	public int getVehicleCount() ;

	public List<? extends Vehicle> getAllVehicles();
}
