package com.cardinal.voca4j.impl.youtube;

import java.util.List;

public class MealbarPromoRenderer {
	private Icon icon;
	private List<MessageText> messageTexts;
	private ActionButton actionButton;
	private DismissButton dismissButton;
	private String triggerCondition;
	private String style;
	private String trackingParams;
	private List<ImpressionEndpoint> impressionEndpoints;
	private boolean isVisible;
	private MessageTitle messageTitle;

	public Icon getIcon() {
		return this.icon;
	}

	public void setMessageTexts(List<MessageText> messageTexts) {
		this.messageTexts = messageTexts;
	}

	public List<MessageText> getMessageTexts() {
		return this.messageTexts;
	}

	public ActionButton getActionButton() {
		return this.actionButton;
	}

	public DismissButton getDismissButton() {
		return this.dismissButton;
	}

	public String getTriggerCondition() {
		return this.triggerCondition;
	}

	public String getStyle() {
		return this.style;
	}

	public String getTrackingParams() {
		return this.trackingParams;
	}

	public void setImpressionEndpoints(List<ImpressionEndpoint> impressionEndpoints) {
		this.impressionEndpoints = impressionEndpoints;
	}

	public List<ImpressionEndpoint> getImpressionEndpoints() {
		return this.impressionEndpoints;
	}

	public boolean getIsVisible() {
		return this.isVisible;
	}

	public MessageTitle getMessageTitle() {
		return this.messageTitle;
	}
}
