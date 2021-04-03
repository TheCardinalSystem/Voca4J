package com.cardinal.voca4j.impl.youtube;

public class WebPlayerActionsPorting {
	private GetSharePanelCommand getSharePanelCommand;
	private SubscribeCommand subscribeCommand;
	private UnsubscribeCommand unsubscribeCommand;
	private AddToWatchLaterCommand addToWatchLaterCommand;
	private RemoveFromWatchLaterCommand removeFromWatchLaterCommand;

	public GetSharePanelCommand getGetSharePanelCommand() {
		return this.getSharePanelCommand;
	}

	public SubscribeCommand getSubscribeCommand() {
		return this.subscribeCommand;
	}

	public UnsubscribeCommand getUnsubscribeCommand() {
		return this.unsubscribeCommand;
	}

	public AddToWatchLaterCommand getAddToWatchLaterCommand() {
		return this.addToWatchLaterCommand;
	}

	public RemoveFromWatchLaterCommand getRemoveFromWatchLaterCommand() {
		return this.removeFromWatchLaterCommand;
	}
}
