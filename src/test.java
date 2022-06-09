public class test {
    public static void main(String[] args) {
        LgTv lgTv = new LgTv();
        SamsungTv samsungTv = new SamsungTv();
        Tv appleTv = new AppleTv();

        Tv[] tvArr = {samsungTv, lgTv, appleTv};
        movable(tvArr);

    }

    public static void movable(Tv[] arr) {
        for(Tv tv : arr) {
            if(tv.isMovable()) {
                System.out.println(tv.getClass().getName() + "해당 티비는 이동이 가능합니다.");
            }
        }
    }

    public static void volumeDown(Tv[] arr) {
        for(Tv tv : arr) {
            if(tv instanceof SamsungTv) {
                ((SamsungTv) tv).volume = Math.max(0, ((SamsungTv) tv).volume - 1);
            } else if(tv instanceof LgTv) {
                ((LgTv) tv).volume = Math.max(0, ((LgTv) tv).volume - 3);
            } else if(tv instanceof AppleTv) {
                ((AppleTv) tv).volume = Math.max(0, ((AppleTv) tv).volume - 5);
            }
        }
    }

    public static void turnOn(Tv tv) {
        if(tv instanceof SamsungTv) {
            System.out.println("삼성 티비는 켤때마다 500원 추가됩니다.");
            tv.on();
        } else if(tv instanceof LgTv) {
            System.out.println("LgTv는 정전중...");
        } else if(tv instanceof AppleTv) {
            tv.on();
        }
    }
}

abstract class Tv {
    abstract void on();
    public boolean isMovable() {
        return false;
    }

    public static void asdasd() {

    }
}

class LgTv extends Tv {
    public int volume = 10;

    @Override
    public void on() {
        System.out.println("LG TV가 켜졌습니다.");
    }

    @Override
    public boolean isMovable() {
        return true;
    }
}

class SamsungTv extends Tv {
    public int volume = 10;

    @Override
    public void on() {
        System.out.println("삼성 TV가 켜졌습니다.");
    }
}

class AppleTv extends Tv {
    public int volume = 10;

    @Override
    public void on() {
        System.out.println("애플 TV가 켜졌습니다.");
    }
}