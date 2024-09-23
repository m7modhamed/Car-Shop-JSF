package com.carshop.model;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.carshop.Service.ITruckService;
import com.carshop.Service.TruckService;

@ManagedBean(name = "truck")
public class Truck extends Vehicle{
	
	private double payloadCapacity;
	
	private double bedLength;
	
    private String successMessage;
    
    private String failMessage;
    
    private String errorMessage;
    
    private ITruckService truckService;
    
    private double tempPayload;
    
    
    
    public double getTempPayload() {
		return tempPayload;
	}

	public void setTempPayload(double tempPayload) {
		this.tempPayload = tempPayload;
	}

	public Truck() {
    	truckService=new TruckService();
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public double getPayloadCapacity() {
		return payloadCapacity;
	}

	public void setPayloadCapacity(double payloadCapacity) {
		this.payloadCapacity = payloadCapacity;
	}

	public double getBedLength() {
		return bedLength;
	}

	public void setBedLength(double bedLength) {
		this.bedLength = bedLength;
	}

	@Override
	public String toString() {
		return super.toString()+", "+"PayloadCapacity=" + payloadCapacity + ", BedLength=" + bedLength + "]";
	}
	
	

	public void add() {
		int isAdded= truckService.addVehicle(this);
		reset();
		if(isAdded > 0) {
			this.setFailMessage(null);
			this.setSuccessMessage("added truck successfully");
		}else {
			this.setFailMessage("sorry, added truck fail");
			this.setSuccessMessage(null);			
		}
		
	}
	
	public Truck getTruck() {
		Truck truck = (Truck)truckService.getVehicle(this.getId());
		if(truck != null) {
		this.setId(truck.getId());
		this.setManufacturer(truck.getManufacturer());
		this.setColor(truck.getColor());
		this.setModel(truck.getModel());
		this.setYear(truck.getYear());
		this.setBedLength(truck.getBedLength());
		this.setPayloadCapacity(truck.getPayloadCapacity());
		this.setPrice(truck.getPrice());
		this.setFailMessage(null);

		}else {
			this.setFailMessage("No truck found with the specified ID.");
		}
		return truck;
	}
	
	
	public String updateRequest() {
		Truck truck = (Truck)truckService.getVehicle(this.getId());
		if(truck != null) {
		this.setId(truck.getId());
		this.setManufacturer(truck.getManufacturer());
		this.setColor(truck.getColor());
		this.setModel(truck.getModel());
		this.setYear(truck.getYear());
		this.setBedLength(truck.getBedLength());
		this.setPayloadCapacity(truck.getPayloadCapacity());
		this.setPrice(truck.getPrice());
		this.setFailMessage(null);
		
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("truck", truck);
		return "updateTruckForm?faces-redirect=true";
		}else {
			this.setFailMessage("No truck found with the specified ID.");
			return "updateCar";
		} 
	}
	
	
	public String updateTruck() {
		int isUpdated = truckService.updateVehicle(this.getId(), this);
	    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("truck");

		if(isUpdated > 0) {

			return "getAllTrucks";
		}else {
			return "";
		}
	}
	
	public String deleteRequest() {
		
		
		int isDeleted = truckService.deleteVehicle(this.getId());
		
		if(isDeleted > 0) {

			return "getAllTrucks?faces-redirect=true";
		}else {

			this.setFailMessage("No truck found with the specified ID.");
			return "deleteTruck";
		} 
	}
	
	
	public void checkPayload() {
		boolean isOverloaded=false;
		Truck truck=getTruck();
		if(truck != null) {
		
			isOverloaded =truckService.IsPayloadOverloaded(this.getId(), this.getTempPayload());
			String message = "The truck can carry: " + this.getPayloadCapacity();

			if (isOverloaded) {
			    this.setFailMessage("Warning: The payload is overloaded! " + message);
			    this.setSuccessMessage(null);
			} else {
			    this.setSuccessMessage("The payload is within the acceptable limits. " + message);
			    this.setFailMessage(null);
			}

		}else {
			this.setFailMessage("No truck found with the specified ID.");
			this.setSuccessMessage(null);

		}
		
		
		
			
	}
	
	
	private void reset() {
		this.setId(0);
		this.setBedLength(bedLength);
		this.setColor(null);
		this.setManufacturer(null);
		this.setModel(null);
		this.setPayloadCapacity(0);
		this.setPrice(0);
		this.setYear(null);
		
	}

	
}
