package optionalExample;

import java.util.Optional;

public class Computer {
    Soundcard soundcard;

    Soundcard getSoundcard() {
        return soundcard;
    }

    Optional<Soundcard> getSoundcardOpt() {
        return Optional.ofNullable(soundcard);
    }
}
