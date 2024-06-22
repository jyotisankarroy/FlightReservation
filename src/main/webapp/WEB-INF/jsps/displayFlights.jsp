<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>List of Flights</title>
	</head>
	<body>
		<h2>List of Flights</h2>
		<table border="1">
		<tr>
			<th>Airlines</th>
			<th>Departure City</th>
			<th>Arrival City</th>
			<th>Departure Time</th>
			<th>Select Flight</th>
		</tr>
		<c:forEach var="findFlight" items="${findFlights}">  
		     <tr>
		     	<td>${findFlight.operatingAirlines}</td>
		     	<td>${findFlight.departureCity}</td>
		     	<td>${findFlight.arrivalCity}</td>
		     	<td>${findFlight.estimatedDepartureTime}</td>
		     	<td><a href="showCompleteReservation?flightId=${findFlight.id}">Select</a></td>
		     </tr>
		</c:forEach>  
		</table>
	</body>
</html>