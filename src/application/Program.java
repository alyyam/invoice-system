package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List <Installment> installments = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter contract data: ");
		System.out.print("Number:");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		
		Contract c = new Contract(number, date, totalValue);
		
		System.out.print("Enter number of installments: ");
		int month = sc.nextInt();
		
		ContractService cs = new ContractService(new PaypalService());
		cs.processContract(c, month);
		
		
		System.out.println("Installment: ");
		for(Installment inst: c.getInstallments()) {
			System.out.println(inst);
		}
		
		
		
		
		
		
		
		
		sc.close();
	




	}
}
