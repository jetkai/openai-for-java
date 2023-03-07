package io.github.jetkai.openai.net.handlers;

import java.util.Map;

public interface RequestDataHandler {
    void populateMap(Object data, Map<Object, Object> map);
}
