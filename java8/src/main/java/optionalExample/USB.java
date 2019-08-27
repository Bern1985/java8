package optionalExample;

import java.util.Optional;

public class USB {
    String version;

    String getVersion() {
        return version;
    }

    Optional<String> getVersionOpt() {
        return Optional.ofNullable(version);
    }
}
