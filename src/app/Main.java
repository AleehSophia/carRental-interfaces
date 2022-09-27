package app;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("Enter rental data: ");

        System.out.print("Car model: ");
        String car = sc.nextLine();

        System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
        Date start = sdf.parse(sc.nextLine());

        System.out.print("Return (dd/MM/yyyy hh:ss): ");
        Date finish = sdf.parse(sc.nextLine());

        CarRental cr = new CarRental(start, finish, new Vehicle(car));

        System.out.print("Enter price per hour: ");
        double priceHour = sc.nextDouble();

        System.out.print("Enter price per day: ");
        double priceDay = sc.nextDouble();

        RentalService rentalService = new RentalService(priceDay, priceHour, new BrazilTaxService());

        rentalService.processInvoice(cr);

        System.out.println("INVOICE: ");
        System.out.println("Basic Payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Total Payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));

        sc.close();
    }
}