package pl.aogiri.ims.common.provider;

import com.google.cloud.NoCredentials;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!online")
public class GCSEmulatorProvider implements GCSProvider {

    @Value("${ims.gcp.storage.host}")
    private String host;

    @Value("${ims.gcp.project}")
    private String project;

    @Value("${ims.gcp.storage.bucket}")
    private String bucket;

    public Storage get() {
        Storage service = StorageOptions.newBuilder()
                .setHost(host)
                .setCredentials(NoCredentials.getInstance())
                .setProjectId(project)
                .build()
                .getService();

        if (service.get(bucket) == null) {
            service.create(BucketInfo.of(bucket));
        }

        return service;
    }
}
