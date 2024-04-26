package com.OlikTask.Library.payloads;

import java.util.List;

public class RentalResponse {

	private List<RentalDto> data;
	
	private int total;

	public List<RentalDto> getData() {
		return data;
	}

	public void setData(List<RentalDto> data) {
		this.data = data;
	}

	public RentalResponse(List<RentalDto> data, int total) {
		super();
		this.data = data;
		this.total = total;
	}

	@Override
	public String toString() {
		return "RentalResponse [data=" + data + ", total=" + total + "]";
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public RentalResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
