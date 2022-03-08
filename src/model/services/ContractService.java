package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public OnlinePaymentService getOnlinePaymentService() {
		return onlinePaymentService;
	}

	public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int month) {
		double basicQuota =  contract.getTotalValue()/month;
		for(int i =1; i<=month; i++) {
			double InterestQuota =  basicQuota + onlinePaymentService.interest(basicQuota, i);
			double fullQuota = InterestQuota + onlinePaymentService.paymentFee(InterestQuota);
			Date dueDate = addMonth(contract.getDate(), i);
			
			contract.addInstallment(new Installment(dueDate, fullQuota));
		}
	}
	
	public Date addMonth(Date date, int  month) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.MONTH, month);
	
	return cal.getTime();
	}
	

}
