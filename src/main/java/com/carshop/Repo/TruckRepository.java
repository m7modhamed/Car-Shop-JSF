package com.carshop.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carshop.model.Truck;
import com.carshop.model.Vehicle;


public class TruckRepository implements IVehicleRepository{


	
	
	@Override
	public int addVehicle(Vehicle vehicle) {
		Truck truck=(Truck) vehicle;
		
		PreparedStatement st;
		try (Connection con = DbConnection.connect();) {
			st = con.
			prepareStatement("insert into CAR_SHOP.truck (MANUFACTURER, COLOR, MODEL, YEAR, PRICE, PAYLOADCAPACITY,BEDLENGTH) values (?,?,?,?,?,?,?)");
			
			st.setString(1,truck.getManufacturer());
			st.setString(2,truck.getColor());
			st.setString(3,truck.getModel());
			st.setString(4,truck.getYear());
			st.setDouble(5,truck.getPrice());
			st.setDouble(6,truck.getPayloadCapacity());
			st.setDouble(7, truck.getBedLength());
			
			 
			 return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
			
		}
		

	}

	@Override
	public int deleteVehicle(int id) {
		
		PreparedStatement st;
		try (Connection con = DbConnection.connect();){
			st = con.
			prepareStatement("delete from CAR_SHOP.truck where id=(?) ");
			
			st.setInt(1,id);
			
			return st.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
			
		}
	}

	

	@Override
	public Vehicle getVehicle(int id)  {		
		Truck truck=new Truck();

		PreparedStatement st;
		try (Connection con = DbConnection.connect();){
			st = con.
			prepareStatement("select * from CAR_SHOP.truck where id=(?) ");
			
			st.setInt(1,id);
			
			ResultSet result = st.executeQuery();
			if(!result.next()) {
				return null;
			}
			
			String manufacturer = result.getString(2);
			String color = result.getString(3);
			String model = result.getString(4);
			String year = result.getString(5);
			double price = result.getDouble(6);
			double payloadCapacity = result.getDouble(7);
			double bedLength = result.getDouble(8);
			
			truck.setId(id);
			truck.setManufacturer(manufacturer);
			truck.setColor(color);
			truck.setModel(model);
			truck.setYear(year);
			truck.setPrice(price);
			truck.setPayloadCapacity(payloadCapacity);
			truck.setBedLength(bedLength);
			
			return truck;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
			
		}
		
	}

	
	
	@Override
	public int UpdateVehicle(int id , Vehicle newCarData) {		
		Truck truck=(Truck) newCarData;

		PreparedStatement st;
		try (Connection con = DbConnection.connect();){
			st = con.
			prepareStatement("UPDATE CAR_SHOP.TRUCK SET MANUFACTURER = ?, COLOR = ?, MODEL = ?, YEAR = ?, PRICE = ?, PAYLOADCAPACITY = ? , BEDLENGTH = ? WHERE ID = ?");

			st.setString(1,truck.getManufacturer());
			st.setString(2,truck.getColor());
			st.setString(3,truck.getModel());
			st.setString(4,truck.getYear());
			st.setDouble(5,truck.getPrice());
			st.setDouble(6,truck.getPayloadCapacity());
			st.setDouble(7, truck.getBedLength());
			st.setInt(8,id);
			
			return st.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
			
		}
	}
	
	
	

	@Override
	public int getVehicleCount()  {
		

		PreparedStatement st;
		try (Connection con = DbConnection.connect();){
			st = con.
			prepareStatement("select count(*) from CAR_SHOP.truck");
			
			ResultSet result= st.executeQuery();
			if(!result.next()) {
				return 0;
			}
			return result.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
			
		}
		
	}

	@Override
	public List<? extends Vehicle> getAllVehicles()  {
		List<Truck> trucks=new ArrayList<>();

		Truck truck=null;

		PreparedStatement st;
		try (Connection con = DbConnection.connect();){
			st = con.
			prepareStatement("select * from CAR_SHOP.truck");
						
			ResultSet result = st.executeQuery();
			
			while(result.next()) {
				int id = result.getInt(1);
				String manufacturer = result.getString(2);
				String color = result.getString(3);
				String model = result.getString(4);
				String year = result.getString(5);
				double price = result.getDouble(6);
				double payloadCapacity = result.getDouble(7);
				double bedLength = result.getDouble(8);
				
				truck=new Truck();
				truck.setId(id);
				truck.setManufacturer(manufacturer);
				truck.setColor(color);
				truck.setModel(model);
				truck.setYear(year);
				truck.setPrice(price);
				truck.setPayloadCapacity(payloadCapacity);
				truck.setBedLength(bedLength);
				
				trucks.add(truck);
			}
			return trucks;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
			
		}
		
	}
	

}
