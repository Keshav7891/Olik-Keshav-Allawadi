package com.OlikTask.Library.payloads;

import java.util.List;

public class AuthorResponse {
	
	private List<AuthorDto> data;
	
	private int total;

	public List<AuthorDto> getData() {
		return data;
	}

	public void setData(List<AuthorDto> data) {
		this.data = data;
	}
	
	public AuthorResponse(List<AuthorDto> data, int total) {
		super();
		this.data = data;
		this.total = total;
	}

	@Override
	public String toString() {
		return "AuthorResponse [data=" + data + ", total=" + total + "]";
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public AuthorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}

