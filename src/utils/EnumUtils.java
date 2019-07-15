package utils;

public class EnumUtils {

    public enum SearchType {
        URLS("URLS"),
        EMAILS("EMAILS"),
        CRAWL_EMAILS("CRAWL_EMAILS");

        public String type;

        SearchType(String type) {
            this.type = type;
        }

        public String type() {
            return type;
        }
    }

    public enum LinkParts {
        FIRST_PART("https://www."),
        LAST_PART(".com");

        public String part;

        LinkParts(String part) {
            this.part = part;
        }

        public String part() {
            return part;
        }
    }

}
