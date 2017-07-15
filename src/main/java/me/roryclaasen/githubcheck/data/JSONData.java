package me.roryclaasen.githubcheck.data;

import com.google.gson.JsonObject;

import me.roryclaasen.util.exception.TagNotFoundException;
/**
 * @author Rory Claasen
 */
public abstract class JSONData {
	private JsonObject object;

	public JSONData(JsonObject object) {
		this.object = object;
		try {
			load(object);
		} catch (TagNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected abstract void load(JsonObject object) throws TagNotFoundException;

	public boolean hasKey(String key) {
		if (object.has(key)) return true;
		return false;
	}

	public JsonObject getJSONObject() {
		return object;
	}
}
