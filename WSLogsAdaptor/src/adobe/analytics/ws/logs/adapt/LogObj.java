package adobe.analytics.ws.logs.adapt;

public class LogObj {

	private String url;
	private String method;
	private String reqBody;
	private String response;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		if (method != null)
			return method;
		else
			return "";
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getReqBody() {
		if (reqBody != null)
			return reqBody;
		else
			return "";
	}

	public void setReqBody(String reqBody) {
		this.reqBody = reqBody;
	}

	public String getResponse() {
		if (response != null)
			return response;
		else
			return "";
	}

	public void setResponse(String response) {
		this.response = response;
	}

}