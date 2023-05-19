package pl.aogiri.ims.file.domain.value;

import com.google.cloud.storage.Blob;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;


@AllArgsConstructor
@Data
public class File {
    private String extension;
    private Blob file;
    private FileType type;

    public URI getFileUri() {
        return URI.create("gs://" + file.getBucket() + "/" + file.getName());
    }
}
