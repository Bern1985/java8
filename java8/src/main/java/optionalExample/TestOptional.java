package optionalExample;

public class TestOptional {
    public static void main(String[] args) {
        System.out.println(testNullReferenceByCheck());
        System.out.println(testNullReferenceByOptional());
    }

    private static String testNullReferenceByCheck(){
        Computer computer = new Computer();
        // 正确的做法，繁琐
        String version = "UNKNOWN";
        if(computer != null){
            Soundcard soundcard = computer.getSoundcard();
            if(soundcard != null){
                USB usb = soundcard.getUSB();
                if(usb != null){
                    version = usb.getVersion();
                }
            }
        }
        return version;
    }

    private static String testNullReferenceByOptional(){
        Computer computer = new Computer();
        // 使用Optional
        String version = computer
                .getSoundcardOpt()
                .flatMap(Soundcard::getUSBOpt)
                .flatMap(USB::getVersionOpt)
                .orElse("UNKNOWN");
        return version;
    }
}
