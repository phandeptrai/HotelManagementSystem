package builder;

import room.RoomInfo;
import java.time.LocalDateTime;

public class Bill {
	private String billId;
	private RoomInfo roomInfo;
	private double totalAmount;
	private String paymentMethod;
	private boolean paid;
	private LocalDateTime paymentTime;

	public Bill(String billId, RoomInfo roomInfo, double totalAmount, String paymentMethod, boolean paid,
			LocalDateTime paymentTime) {
		this.billId = billId;
		this.roomInfo = roomInfo;
		this.totalAmount = totalAmount;
		this.paymentMethod = paymentMethod;
		this.paid = paid;
		this.paymentTime = paymentTime;
	}

	public String getBillId() {
		return billId;
	}

	public RoomInfo getRoomInfo() {
		return roomInfo;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public boolean isPaid() {
		return paid;
	}

	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	@Override
	public String toString() {
		return "Bill{" + "billId='" + billId + '\'' + ", roomInfo=" + roomInfo.getRoom().getDescription()
				+ ", totalAmount=" + totalAmount + ", paymentMethod='" + paymentMethod + '\'' + ", paid=" + paid
				+ ", paymentTime=" + paymentTime + '}';
	}
}