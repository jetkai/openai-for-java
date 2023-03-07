package io.github.jetkai.openai.net.request;

import java.util.Map;

interface RequestDataHandler {
    void populateMap(Object data, Map<Object, Object> map);
}
