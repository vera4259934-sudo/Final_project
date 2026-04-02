/*package p;

public class Recipe_2 {

    public final static int ICE_RECIPE_AMOUNT = 5;
    public final static int TEA_RECIPE_AMOUNT = 1;
    public final static int TAPIOKA_RECIPE_AMOUNT = 110;
    public final static int VANILLA_RECIPE_AMOUNT = 2;
    public final static int SUGAR_RECIPE_AMOUNT = 1;
    public final static int MILK_RECIPE_AMOUNT = 230;
    public final static int TRUB_RECIPE_AMOUNT = 1;

    public static enum TeaKind {
        TEA1,
        TEA2,
    }

    public static enum IceKind {
        ICE1,
        ICE2,
        ICE3,
    }

    public static enum TapiokaKind {
        TAPIOKA1,
        TAPIOKA2,
        TAPIOKA3,
    }

    public static enum TrubKind {
        TRUB1,
        TRUB2,
        TRUB3,
    }
}*/

package p;

public class Recipe_2 {

    public final static int ICE_RECIPE_AMOUNT = 5;
    public final static int TEA_RECIPE_AMOUNT = 1;
    public final static int TAPIOKA_RECIPE_AMOUNT = 110;
    public final static int VANILLA_RECIPE_AMOUNT = 2;
    public final static int SUGAR_RECIPE_AMOUNT = 1;
    public final static int MILK_RECIPE_AMOUNT = 230;
    public final static int TRUB_RECIPE_AMOUNT = 1;

    public static enum TeaKind {
        BLACK_TEA(1),
        GREEN_TEA(2);

        private TeaKind(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static TeaKind findByCode(int code) {
            for(TeaKind kind : values()) {
                if(kind.getCode() == code) {
                    return kind;
                }
            }
            throw new IllegalArgumentException(String.format("Invalid tea-code: %d", code));
        }

        private final int code;
    }

    public static enum IceKind {
        ICE_CUBES(1),
        ICE_HEARTS(2),
        ICE_STARS(3);

        private IceKind(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static IceKind findByCode(int code) {
            for(IceKind kind : values()) {
                if(kind.getCode() == code) {
                    return kind;
                }
            }
            throw new IllegalArgumentException(String.format("Invalid ice-code: %d", code));
        }

        private final int code;
    }

    public static enum TapiokaKind {
        TAPIOKA_BROWN(1),
        TAPIOKA_PINK(2),
        TAPIOKA_YELLOW(3);

        private TapiokaKind(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static TapiokaKind findByCode(int code) {
            for(TapiokaKind kind : values()) {
                if(kind.getCode() == code) {
                    return kind;
                }
            }
            throw new IllegalArgumentException(String.format("Invalid tapioka-code: %d", code));
        }

        private final int code;
    }

    public static enum TrubKind {
        TRUB_BLUE(1),
        TRUB_RED(2),
        TRUB_GREEN(3);

        private TrubKind(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static TrubKind findByCode(int code) {
            for(TrubKind kind : values()) {
                if(kind.getCode() == code) {
                    return kind;
                }
            }
            throw new IllegalArgumentException(String.format("Invalid trub-code: %d", code));
        }

        private final int code;
    }

    public static void main(String...args) {
        System.out.println(TeaKind.findByCode(1));
        System.out.println(TeaKind.findByCode(2));

        System.out.println(IceKind.findByCode(1));
        System.out.println(IceKind.findByCode(2));
        System.out.println(IceKind.findByCode(3));

        System.out.println(TapiokaKind.findByCode(1));
        System.out.println(TapiokaKind.findByCode(2));
        System.out.println(TapiokaKind.findByCode(3));

        System.out.println(TrubKind.findByCode(1));
        System.out.println(TrubKind.findByCode(2));
        System.out.println(TrubKind.findByCode(3));
    }
}