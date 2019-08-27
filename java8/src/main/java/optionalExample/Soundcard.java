package optionalExample;

import java.util.Optional;

public class Soundcard {
    USB usb;

    USB getUSB() {
        return usb;
    }

    Optional<USB> getUSBOpt() {
        return Optional.ofNullable(usb);
    }
}
