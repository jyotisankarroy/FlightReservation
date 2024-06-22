package com.psa.flight_reservation_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psa.flight_reservation_app.dto.ReservationRequest;
import com.psa.flight_reservation_app.entity.Reservation;
import com.psa.flight_reservation_app.service.ReservationService;
import com.psa.flight_reservation_app.utilities.EmailUtil;


@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping("/completeReservation")
	public String confirmReservation(ReservationRequest request, ModelMap modelMap) { 
	//Here we use dto(Data Transfer Object),,...but "@RequestParam()" should also be used nothing wrong in that...
		//System.out.println(reservation.getFlightId());
		Reservation reservationId = reservationService.bookFlight(request);
		modelMap.addAttribute("reservationId", reservationId.getId());
		
		return "confirmReservation";
	}
}
