package org.visallo.core.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class HtmlSanitizer {
    private static final String SANITIZED_HTML_JSON_KEY = "sanitizedHtml";
    private static PolicyFactory sanitizer;

    public static String sanitizeJSONString(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        if (!jsonString.contains(SANITIZED_HTML_JSON_KEY)) {
            return jsonString;
        }
        JSONObject json = new JSONObject(jsonString);
        json = HtmlSanitizer.sanitizeJSONObject(json);
        return json.toString();
    }

    /**
     * Looks for any string properties named "sanitizedHtml" and sanitizes them
     */
    public static JSONObject sanitizeJSONObject(JSONObject json) {
        for (Object keyObject : json.keySet()) {
            String key = (String) keyObject;
            Object value = json.opt(key);
            if (key.equals(SANITIZED_HTML_JSON_KEY) && value instanceof String) {
                json.put(key, sanitizeString((String) value));
            } else if (value instanceof JSONObject) {
                json.put(key, sanitizeJSONObject((JSONObject) value));
            } else if (value instanceof JSONArray) {
                json.put(key, sanitizeJSONArray((JSONArray) value));
            }
        }
        return json;
    }

    public static JSONArray sanitizeJSONArray(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONObject) {
                array.put(i, sanitizeJSONObject((JSONObject) value));
            } else if (value instanceof JSONArray) {
                array.put(i, sanitizeJSONArray((JSONArray) value));
            }
        }
        return array;
    }

    public static String sanitizeString(String string) {
        PolicyFactory sanitizer = getSanitizer();
        return sanitizer.sanitize(string);
    }

    private static PolicyFactory getSanitizer() {
        if (sanitizer == null) {
            sanitizer = Sanitizers.BLOCKS
                    .and(Sanitizers.FORMATTING)
                    .and(Sanitizers.IMAGES)
                    .and(Sanitizers.LINKS)
                    .and(Sanitizers.STYLES);
        }
        return sanitizer;
    }
}
