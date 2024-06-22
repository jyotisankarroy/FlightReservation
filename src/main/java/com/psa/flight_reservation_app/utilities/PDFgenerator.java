package com.psa.flight_reservation_app.utilities;

import java.io.FileOutputStream;
import org.springframework.stereotype.Component;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.psa.flight_reservation_app.entity.Reservation;

@Component
public class PDFgenerator {
	//Note: "@Component" It's a stereotype, I am creating a java class whose bean I need to create using "@Autowired" I am not be able to create the bean if we don't mark the class with "@Component"...
	//private static String FILE = "c:/temp/FirstPdf.pdf";

    	public void generateItinerary(Reservation reservation, String filePath) {
	    	Document document = new Document();
	    	try {
	            PdfWriter.getInstance(document, new FileOutputStream(filePath));
	            
	            document.open();
	            document.add(generateTable(reservation));
	            document.close();
		        } catch (Exception e) {	//(FileNotFoundException | DocumentException e)
		            e.printStackTrace();
		        }
	    }

		private PdfPTable generateTable(Reservation reservation){
    	
	        PdfPTable table = new PdfPTable(2);
	        PdfPCell cell;
	
	        cell = new PdfPCell(new Phrase("Passenger Details"));
	        cell.setColspan(2);
	        table.addCell(cell);
	        
	        table.addCell("Passenger Name");
	        table.addCell(reservation.getPassenger().getFirstName());
	        
	        table.addCell("Email Id");
	        table.addCell(reservation.getPassenger().getEmail());
	        
	        table.addCell("Phone Number");
	        table.addCell(reservation.getPassenger().getPhone());
	        
	        cell = new PdfPCell(new Phrase("Flight Details"));
	        cell.setColspan(2);
	        table.addCell(cell);
	
	        table.addCell("Departure City");
	        table.addCell(reservation.getFlight().getDepartureCity());
	        
	        table.addCell("Arrival City");
	        table.addCell(reservation.getFlight().getArrivalCity());
	        
	        table.addCell("Flight Number");
	        table.addCell(reservation.getFlight().getFlightNumber());
	        
	        table.addCell("Operating Airlines");
	        table.addCell(reservation.getFlight().getOperatingAirlines());
	        
	        table.addCell("Departure Date");
	        table.addCell(reservation.getFlight().getDateOfDeparture().toString());
	        //document.add(table);
	        //document.close();
	
	        return table;
	}
}

