package org.krisbox.json.ipse.mapper;

import java.io.Serializable;
import java.util.HashMap;
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
import org.krisbox.json.ipse.properties.IpseProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ipse"
})
public class JsonIpseMapper implements Serializable, Parcelable
{
    @JsonProperty("ipseProperties")
    private IpseProperties ipseProperties;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<JsonIpseMapper> CREATOR = new Creator<JsonIpseMapper>() {


        @SuppressWarnings({
                "unchecked"
        })
        public JsonIpseMapper createFromParcel(Parcel in) {
            JsonIpseMapper instance = new JsonIpseMapper();
            instance.ipseProperties = ((IpseProperties) in.readValue((IpseProperties.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public JsonIpseMapper[] newArray(int size) {
            return (new JsonIpseMapper[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4874517748542075527L;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonIpseMapper() {
    }

    /**
     *
     * @param ipseProperties
     */
    public JsonIpseMapper(IpseProperties ipseProperties) {
        super();
        this.ipseProperties = ipseProperties;
    }

    @JsonProperty("ipseProperties")
    public IpseProperties getIpseProperties() {
        return ipseProperties;
    }

    @JsonProperty("ipseProperties")
    public void setIpseProperties(IpseProperties ipseProperties) {
        this.ipseProperties = ipseProperties;
    }

    public JsonIpseMapper withIpseProperties(IpseProperties ipseProperties) {
        this.ipseProperties = ipseProperties;
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

    public JsonIpseMapper withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ipseProperties).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof JsonIpseMapper) == false) {
            return false;
        }
        JsonIpseMapper rhs = ((JsonIpseMapper) other);
        return new EqualsBuilder().append(ipseProperties, rhs.ipseProperties).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ipseProperties);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }
}
