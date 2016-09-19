package beer.brew.vendingmachine.data.model;

import java.io.Serializable;

public class Beer implements Serializable {

    private final Size size;
    private final String description = "Beer";

    public Beer(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public String description() {
        return size.getDescription() + ", " + description;
    }

    public enum Size implements Serializable {
        SMALL("Small Cup"),
        MIDDLE("Middle Cup"),
        BIG("Big Cup");

        private final String description;

        Size(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
