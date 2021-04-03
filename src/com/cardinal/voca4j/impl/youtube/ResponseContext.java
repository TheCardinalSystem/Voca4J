package com.cardinal.voca4j.impl.youtube;

import java.util.List;

public class ResponseContext {
	private List<ServiceTrackingParams> serviceTrackingParams;
	private MainAppWebResponseContext mainAppWebResponseContext;
	private WebResponseContextExtensionData webResponseContextExtensionData;

	public void setServiceTrackingParams(List<ServiceTrackingParams> serviceTrackingParams) {
		this.serviceTrackingParams = serviceTrackingParams;
	}

	public List<ServiceTrackingParams> getServiceTrackingParams() {
		return this.serviceTrackingParams;
	}

	public MainAppWebResponseContext getMainAppWebResponseContext() {
		return this.mainAppWebResponseContext;
	}

	public WebResponseContextExtensionData getWebResponseContextExtensionData() {
		return this.webResponseContextExtensionData;
	}
}
