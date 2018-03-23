
package models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "elements"
})
public class Row {

    @JsonProperty("elements")
    private List<Element> elements = null;

    @JsonProperty("elements")
    public List<Element> getElements() {
        return elements;
    }

    @JsonProperty("elements")
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}
