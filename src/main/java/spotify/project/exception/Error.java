package spotify.project.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Error {
    private Date timestamp;
    private String message;
    private String method;
    private String path;
}
