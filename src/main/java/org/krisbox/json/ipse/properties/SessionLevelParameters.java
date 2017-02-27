package org.krisbox.json.ipse.properties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "parameterNames",
        "parameterValues"
})
public class SessionLevelParameters implements Serializable, Parcelable
{

    @JsonProperty("parameterNames")
    private List<String> parameterNames = null;
    @JsonProperty("parameterValues")
    private List<String> parameterValues = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<SessionLevelParameters> CREATOR = new Creator<SessionLevelParameters>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SessionLevelParameters createFromParcel(Parcel in) {
            SessionLevelParameters instance = new SessionLevelParameters();
            in.readList(instance.parameterNames, (java.lang.String.class.getClassLoader()));
            in.readList(instance.parameterValues, (java.lang.String.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public SessionLevelParameters[] newArray(int size) {
            return (new SessionLevelParameters[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4333592878573324001L;

    /**
     * No args constructor for use in serialization
     *
     */
    public SessionLevelParameters() {
    }

    /**
     *
     * @param parameterNames
     * @param parameterValues
     */
    public SessionLevelParameters(List<String> parameterNames, List<String> parameterValues) {
        super();
        this.parameterNames = parameterNames;
        this.parameterValues = parameterValues;
    }

    @JsonProperty("parameterNames")
    public List<String> getParameterNames() {
        return parameterNames;
    }

    @JsonProperty("parameterNames")
    public void setParameterNames(List<String> parameterNames) {
        this.parameterNames = parameterNames;
    }

    public SessionLevelParameters withParameterNames(List<String> parameterNames) {
        this.parameterNames = parameterNames;
        return this;
    }

    @JsonProperty("parameterValues")
    public List<String> getParameterValues() {
        return parameterValues;
    }

    @JsonProperty("parameterValues")
    public void setParameterValues(List<String> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public SessionLevelParameters withParameterValues(List<String> parameterValues) {
        this.parameterValues = parameterValues;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public SessionLevelParameters withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(parameterNames).append(parameterValues).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SessionLevelParameters) == false) {
            return false;
        }
        SessionLevelParameters rhs = ((SessionLevelParameters) other);
        return new EqualsBuilder().append(parameterNames, rhs.parameterNames).append(parameterValues, rhs.parameterValues).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(parameterNames);
        dest.writeList(parameterValues);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}