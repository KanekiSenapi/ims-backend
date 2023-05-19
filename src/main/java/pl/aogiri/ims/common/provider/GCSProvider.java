package pl.aogiri.ims.common.provider;

import com.google.cloud.storage.Storage;

public interface GCSProvider {
    Storage get();
}
