package com.psa.flight_reservation_app.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psa.flight_reservation_app.entity.Flight;
import com.psa.flight_reservation_app.repository.FlightRepository;

@Controller
public class FlightController {
	
	@Autowired
	private FlightRepository flightRepo;
	
	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate, ModelMap modelMap) {
		List<Flight> findFlights = flightRepo.findFlights(from, to, departureDate);
		//System.out.println(findFlights);
		modelMap.addAttribute("findFlights", findFlights);
		return "displayFlights";
	}
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") long flightId, ModelMap modelMap) {
		//System.out.println(flightId);
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
//		System.out.println(flight.getId());
//		System.out.println(flight.getArrivalCity());
//		System.out.println(flight.getDepartureCity());
//		System.out.println(flight.getFlightNumber());
//		System.out.println(flight.getOperatingAirlines());
		modelMap.addAttribute("flight", flight);
		return "showReservation";
	}
}
