package net.roryclaasen.githubcheck.data;

import net.roryclaasen.util.exception.TagNotFoundException;

import org.json.simple.JSONObject;

public abstract class JSONData {
	private JSONObject object;

	public JSONData(JSONObject object) {
		this.object = object;
		try {
			load(object);
		} catch (TagNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected abstract void load(JSONObject object) throws TagNotFoundException;

	public boolean hasKey(String key) {
		if (object.containsKey(key)) return true;
		return false;
	}

	public JSONObject getJSONObject() {
		return object;
	}
}
