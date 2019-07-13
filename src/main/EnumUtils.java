package main;

public class EnumUtils {

    public enum Type {
        URLS("URLS"),
        EMAILS("EMAILS");

        public String type;

        Type(String type) {
            this.type = type;
        }

        public String type() {
            return type;
        }
    }

}
