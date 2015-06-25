package entities;

public class Service {

	private String name;
	private String status;
	private String description;
	private String url;

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setUrl(String url) {
		this.url = url;
		
	}

	@Override
	public String toString() {
		return "Service [name=" + name + ", status=" + status
				+ ", description=" + description + "]";
	}


	
	
}
