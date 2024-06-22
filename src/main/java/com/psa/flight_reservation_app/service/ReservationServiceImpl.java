package com.psa.flight_reservation_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.flight_reservation_app.utilities.EmailService;
import com.psa.flight_reservation_app.dto.ReservationRequest;
import com.psa.flight_reservation_app.entity.Flight;
import com.psa.flight_reservation_app.entity.Passenger;
import com.psa.flight_reservation_app.entity.Reservation;
import com.psa.flight_reservation_app.repository.FlightRepository;
import com.psa.flight_reservation_app.repository.PassengerRepository;
import com.psa.flight_reservation_app.repository.ReservationRepository;
//import com.psa.flight_reservation_app.utilities.EmailUtil;
import com.psa.flight_reservation_app.utilities.PDFgenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	//Note:"@Service" it helps us to define that it has the service layer of our annotations...
	// and it is a spring boot annotations,,... if you don't use this annotations then it becomes an ordinary class...
	// and for an ordinary class a bean cannot be created...
	//How do you create service layer/what is @Service??-->> It is an annotation that helps us to define the service layer...
	//Why should we create the service layer??-->> Let's take an scenario I am booking a flight where in the 
	//flight details, passenger details and the card details needs to be saved in the database in this case we will create a service layer 
	//which will now deal with three database operation inserting passenger details, inserting flight details etc.. 
	//and that will help me out to build the project in a structured manner....
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private PDFgenerator pdfGenerator;
	
//	@Autowired
//	private EmailUtil emailUtil;
	
	@Autowired
	private EmailService emailService;
	
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		//String filePath = "C:\\Users\\PRANESH GHOSH\\Documents\\workspace-spring-tool-suite-4-4.8.1.RELEASE\\flight_reservation_app\\tickets";
//		String firstName = request.getFirstName();
//		String lastName = request.getLastName();
//		String middleName = request.getMiddleName();
//		String email = request.getEmail();
//		String phone = request.getPhone();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		passengerRepo.save(passenger);
		
		long flightId = request.getFlightId();
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		
		
		String filePath = "C:\\Users\\jsroy\\Documents\\workspace-spring-tool-suite-4-4.8.1.RELEASE\\flight_reservation_app\\tickets\\reservation"+reservation.getId()+".pdf";
		reservationRepo.save(reservation);
		
		//PDFgenerator pdf = new PDFgenerator();
		//pdf.generatePDF(filePath+reservation.getId()+".pdf", request.getFirstName(), request.getEmail(), request.getPhone(), flight.getOperatingAirlines(), flight.getDateOfDeparture(), flight.getDepartureCity(), flight.getArrivalCity());
		
		pdfGenerator.generateItinerary(reservation, filePath);
		//emailUtil.sendItinerary(passenger.getEmail(),filePath);
		emailService.sendEmailWithAttachment(passenger.getEmail(), "Itinerary of Flight", "Please find the attachment", filePath);
		
		return reservation;
	}

}
