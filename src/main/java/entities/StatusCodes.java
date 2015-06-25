package entities;

public enum StatusCodes {
	
	OK("OK"), PLANNED_OUTAGE("PLANNED OUTAGE"), UNPLANNED_OUTAGE("UNPLANNED OUTAGE");

	private String code;
	
	private StatusCodes(String code){
		this.code = code;
	}

	public String toString(){
		return this.code;
	}
}
