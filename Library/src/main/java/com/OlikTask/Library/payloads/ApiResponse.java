package com.OlikTask.Library.payloads;

public class ApiResponse {
    private String message;
    private Boolean success;
    
    public ApiResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", success=" + success + "]";
	}

	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
