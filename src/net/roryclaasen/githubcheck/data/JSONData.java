package net.roryclaasen.githubcheck.data;

import net.roryclaasen.util.exception.TagNotFoundException;

import com.google.gson.JsonObject;
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
