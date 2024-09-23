package com.carshop.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.carshop.Service.CarService;
import com.carshop.Service.ICarService;

@ManagedBean(name = "car" )
@RequestScoped
public class Car extends Vehicle implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ICarService carService=new CarService();
	
	private short passengerCapacity;
	
    public static final double INSURANCE_RATE = 3.5;

    private String successMessage;
    
    private String failMessage;
    
    private String errorMessage;
    
    private double insuranceCost;
    
    
    public double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}
	private List<Car> cars;
    

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public short getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(short passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
		
	}

	@Override
	public String toString() {
		return super.toString()+", " + "PassengerCapacity=" + passengerCapacity + "]";
	}
	
	public void add() {
		int isAdded= carService.addVehicle(this);
		reset();
		if(isAdded > 0) {
			this.setFailMessage(null);
			this.setSuccessMessage("added car successfully");
		}else {
			this.setFailMessage("sorry, added car fail");
			this.setSuccessMessage(null);			
		}
		
	}

	public Car getCar() {
		Car car = (Car)carService.getVehicle(this.getId());
		if(car != null) {
		this.setId(car.getId());
		this.setManufacturer(car.getManufacturer());
		this.setColor(car.getColor());
		this.setModel(car.getModel());
		this.setYear(car.getYear());
		this.setPassengerCapacity(car.getPassengerCapacity());
		this.setPrice(car.getPrice());
		this.setFailMessage(null);

		}else {
			this.setFailMessage("No car found with the specified ID.");
		}
		return car;
	}
	
	
	public String updateRequest() {
		Car car = (Car)carService.getVehicle(this.getId());
		if(car != null) {
		this.setId(car.getId());
		this.setManufacturer(car.getManufacturer());
		this.setColor(car.getColor());
		this.setModel(car.getModel());
		this.setYear(car.getYear());
		this.setPassengerCapacity(car.getPassengerCapacity());
		this.setPrice(car.getPrice());
		this.setFailMessage(null);
		
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("car", car);
		return "updateCarForm?faces-redirect=true";
		}else {
			this.setFailMessage("No car found with the specified ID.");
			return "updateCar";
		} 
	}
	
	public String updateCar() {
		int isUpdated = carService.updateVehicle(this.getId(), this);
	    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("car");

		if(isUpdated > 0) {

			return "getAllCars";
		}else {
			return "";
		}
	}
	
	public String deleteRequest() {
		
		
		int isDeleted = carService.deleteVehicle(this.getId());
		
		if(isDeleted > 0) {

			return "getAllCars?faces-redirect=true";
		}else {

			this.setFailMessage("No car found with the specified ID.");
			return "deleteCar";
		} 
	}
	
	
	public String allCars() {
		
		return "getAllCars?faces-redirect=true";

	}
	
	public void calculateInsurance() {
		Car car =(Car) this.getCar();
		if(car != null) {		
			this.setInsuranceCost(carService.calculateInsuranceCost(this.getId()));
			this.setFailMessage(null);	
		}else {
			reset();
		}
		
	}
	
	private void reset() {
		this.setId(0);
		this.setInsuranceCost(0);
		this.setColor(null);
		this.setManufacturer(null);
		this.setModel(null);
		this.setPassengerCapacity((short)0);
		this.setPrice(0);
		this.setYear(null);
		
	}

	
	
}
