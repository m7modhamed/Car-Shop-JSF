package com.carshop.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carshop.model.Car;
import com.carshop.model.Vehicle;


public class CarRepository implements IVehicleRepository{
	

	
	@Override
	public int addVehicle(Vehicle vehicle)  {
		Car car=(Car) vehicle;
		
		PreparedStatement st;
		try (Connection con = DbConnection.connect();) {
			st = con.
			prepareStatement("insert into CAR_SHOP.car (MANUFACTURER, COLOR, MODEL, YEAR, PRICE, PASSENGERCAPACITY) values (?,?,?,?,?,?)");
			
			st.setString(1,car.getManufacturer());
			st.setString(2,car.getColor());
			st.setString(3,car.getModel());
			st.setString(4,car.getYear());
			st.setDouble(5,car.getPrice());
			st.setShort(6,car.getPassengerCapacity());
			
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
			prepareStatement("delete from CAR_SHOP.car where id=(?) ");
			
			st.setInt(1,id);
			
			 
			return st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
			
		}
	}

	

	@Override
	public Vehicle getVehicle(int id)  {		
		Car car=new Car();

		PreparedStatement st;
		try (Connection con = DbConnection.connect();){
			st = con.
			prepareStatement("select * from CAR_SHOP.car where id=(?) ");
			
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
			short passengerCapacity = result.getShort(7);
			
			car.setId(id);
			car.setManufacturer(manufacturer);
			car.setColor(color);
			car.setModel(model);
			car.setYear(year);
			car.setPrice(price);
			car.setPassengerCapacity(passengerCapacity);
			
			return car;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
			
		}
		
	}

	
	
	@Override
	public int UpdateVehicle(int id , Vehicle newCarData) {		
		Car car=(Car) newCarData;

		PreparedStatement st;
		try (Connection con = DbConnection.connect();){
			st = con.
			prepareStatement("UPDATE CAR_SHOP.CAR SET MANUFACTURER = ?, COLOR = ?, MODEL = ?, YEAR = ?, PRICE = ?, PASSENGERCAPACITY = ? WHERE ID = ?");

			st.setString(1,car.getManufacturer());
			st.setString(2,car.getColor());
			st.setString(3,car.getModel());
			st.setString(4,car.getYear());
			st.setDouble(5,car.getPrice());
			st.setShort(6,car.getPassengerCapacity());
			st.setInt(7,id);
			
			 
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
			prepareStatement("select count(*) from CAR_SHOP.car");
			
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
		List<Car> cars=new ArrayList<>();

		Car car=null;

		PreparedStatement st;
		try (Connection con = DbConnection.connect();){
			st = con.
			prepareStatement("select * from CAR_SHOP.CAR");
						
			ResultSet result = st.executeQuery();
			
			while(result.next()) {
				int id = result.getInt(1);
				String manufacturer = result.getString(2);
				String color = result.getString(3);
				String model = result.getString(4);
				String year = result.getString(5);
				double price = result.getDouble(6);
				short passengerCapacity = result.getShort(7);
				
				car=new Car();
				car.setId(id);
				car.setManufacturer(manufacturer);
				car.setColor(color);
				car.setModel(model);
				car.setYear(year);
				car.setPrice(price);
				car.setPassengerCapacity(passengerCapacity);
				
				cars.add(car);
			}
			
			return cars;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
			
		}
		
	}
	
	
}
