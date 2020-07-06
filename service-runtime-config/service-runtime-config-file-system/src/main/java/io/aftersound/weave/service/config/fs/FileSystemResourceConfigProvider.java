package io.aftersound.weave.service.config.fs;

import io.aftersound.weave.fs.FileSystem;
import io.aftersound.weave.fs.Reader;
import io.aftersound.weave.resource.ResourceConfig;
import io.aftersound.weave.service.runtime.ClientAndNamespaceAwareConfigProvider;
import io.aftersound.weave.service.runtime.ConfigFormat;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

final class FileSystemResourceConfigProvider extends ClientAndNamespaceAwareConfigProvider<FileSystem, ResourceConfig> {

    FileSystemResourceConfigProvider(FileSystem client, String namespace, String configIdentifier, ConfigFormat configFormat) {
        super(client, namespace, configIdentifier, configFormat);
    }

    @Override
    protected List<ResourceConfig> getConfigList() {
        String fileName = Util.getFileName(namespace, configIdentifier, configFormat);

        Reader<ResourceConfig[]> reader = new Reader<ResourceConfig[]>() {

            @Override
            protected ResourceConfig[] read(InputStream is) throws Exception {
                return configReader.readValue(is, ResourceConfig[].class);
            }
        };
        try {
            return Arrays.asList(client.read(fileName, reader));
        } catch (Exception e) {
            // ${namespace}.${configIdentifier}.json
            throw new RuntimeException("failed to read config from " + fileName, e);
        }
    }
}
