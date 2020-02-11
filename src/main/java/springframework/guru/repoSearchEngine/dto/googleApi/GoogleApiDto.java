package springframework.guru.repoSearchEngine.dto.googleApi;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GoogleApiDto {
    @JsonProperty("items")
    private ArrayList<GoogleLink> items;

    public ArrayList<GoogleLink> getItems() {
        return items;
    }

    public void setItems(ArrayList<GoogleLink> items) {
        this.items = items;
    }
}
