package com.OlikTask.Library.payloads;

import java.util.List;

public class BookResponse {
	
	private List<BookDto> data;
	
	private int total;

	public List<BookDto> getData() {
		return data;
	}

	public void setData(List<BookDto> data) {
		this.data = data;
	}


	public BookResponse(List<BookDto> data, int total) {
		super();
		this.data = data;
		this.total = total;
	}

	@Override
	public String toString() {
		return "BookResponse [data=" + data + ", total=" + total + "]";
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public BookResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
