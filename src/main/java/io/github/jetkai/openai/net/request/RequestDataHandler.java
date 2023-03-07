package io.github.jetkai.openai.net.request;

import java.util.Map;

/**
 * RequestDataHandler
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
interface RequestDataHandler {
    void populateMap(Object data, Map<Object, Object> map);
}
