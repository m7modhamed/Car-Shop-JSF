package com.carshop.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.carshop.Service.ITruckService;
import com.carshop.Service.TruckService;
import com.carshop.model.Truck;

@ManagedBean(name = "allTruckBean")
@RequestScoped
public class AllTruckBean {

    private List<Truck> trucks; 
   private ITruckService truckService=new TruckService();
    
    public void init() {

        
       
    }

    // Getter for car
    @SuppressWarnings("unchecked")
	public List<Truck> getTrucks() {
		trucks=(List<Truck>) truckService.getAllVehicles();

        return trucks;
    }
}

