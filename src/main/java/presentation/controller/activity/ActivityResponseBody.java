package presentation.controller.activity;

import webapp.kuga.domain.model.Activity;

public class ActivityResponseBody {
	private Activity activity;

	public ActivityResponseBody(Activity activity) {
		this.activity = activity;
	}

	public String getId() {
		return this.activity.getId();
	}

	public String getTitle() {
		return this.activity.getTitle();
	}

	public String getDate() {
		return this.activity.getDate();
	}

	public int getCount() {
		return this.activity.getScoreList().size();
	}
}