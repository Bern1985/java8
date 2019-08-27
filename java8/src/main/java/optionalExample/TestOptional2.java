package optionalExample;

import java.util.Optional;


public class TestOptional2 {
    public static void main(String[] args) {
        System.out.println(testGetUSBVersionByOptional());
    }

    private static String testGetUSBVersionByOptional() {
        Optional<Computer> computer = Optional.empty();
        String name = computer.flatMap(Computer::getSoundcard)
                .flatMap(Soundcard::getUSB)
                .map(USB::getVersion)
                .orElse("UNKNOWN");
        return name;
    }

    class Computer {
        public void setSoundcard(Optional<Soundcard> soundcard) {
            this.soundcard = soundcard;
        }

        private Optional<Soundcard> soundcard;

        public Optional<Soundcard> getSoundcard() {
            return soundcard;
        }

    }

    class Soundcard {
        public Soundcard(Optional<USB> usb) {
            super();
            this.usb = usb;
        }

        private Optional<USB> usb;

        public Optional<USB> getUSB() {
            return usb;
        }

    }

    class USB {
        public String getVersion() {
            return "123";
        }
    }
}
