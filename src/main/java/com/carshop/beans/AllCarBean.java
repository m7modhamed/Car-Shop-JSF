package com.carshop.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.carshop.Service.CarService;
import com.carshop.Service.ICarService;
import com.carshop.model.Car;

@ManagedBean(name = "allCarBean")
@RequestScoped
public class AllCarBean {

    private List<Car> cars; 
   private ICarService carService=new CarService();
    
    public void init() {

        
       
    }

    // Getter for car
    @SuppressWarnings("unchecked")
	public List<Car> getCars() {
		cars=(List<Car>) carService.getAllVehicles();

        return cars;
    }
}

