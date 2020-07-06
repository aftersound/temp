package io.aftersound.weave.service.config.fs;

import io.aftersound.weave.service.runtime.ConfigFormat;

class Util {

    static String getFileName(String namespace, String configIdentifer, ConfigFormat configFormat) {
        return namespace + "." + configIdentifer + (configFormat == ConfigFormat.Yaml ? ".yaml" : ".json");
    }

}
