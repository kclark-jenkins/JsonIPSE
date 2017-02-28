package org.krisbox.json.ipse.properties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
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
        "username",
        "password",
        "volume",
        "volumeProfile",
        "extendedCredentials",
        "enterprise",
        "userHomeFolder",
        "sessionLevelParameters"
})
public class IpseProperties implements Serializable, Parcelable
{

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("volume")
    private String volume;
    @JsonProperty("volumeProfile")
    private String volumeProfile;
    @JsonProperty("extendedCredentials")
    private String extendedCredentials;
    @JsonProperty("enterprise")
    private boolean enterprise;
    @JsonProperty("userHomeFolder")
    private String userHomeFolder;
    @JsonProperty("sessionLevelParameters")
    private SessionLevelParameters sessionLevelParameters;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<IpseProperties> CREATOR = new Creator<IpseProperties>() {


        @SuppressWarnings({
                "unchecked"
        })
        public IpseProperties createFromParcel(Parcel in) {
            IpseProperties instance = new IpseProperties();
            instance.username = ((String) in.readValue((String.class.getClassLoader())));
            instance.password = ((String) in.readValue((String.class.getClassLoader())));
            instance.volume = ((String) in.readValue((String.class.getClassLoader())));
            instance.volumeProfile = ((String) in.readValue((String.class.getClassLoader())));
            instance.extendedCredentials = ((String) in.readValue((String.class.getClassLoader())));
            instance.enterprise = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.userHomeFolder = ((String) in.readValue((String.class.getClassLoader())));
            instance.sessionLevelParameters = ((SessionLevelParameters) in.readValue((SessionLevelParameters.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public IpseProperties[] newArray(int size) {
            return (new IpseProperties[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8622533608975231758L;

    /**
     * No args constructor for use in serialization
     *
     */
    public IpseProperties() {
    }

    /**
     *
     * @param volumeProfile
     * @param username
     * @param extendedCredentials
     * @param volume
     * @param sessionLevelParameters
     * @param enterprise
     * @param userHomeFolder
     * @param password
     */
    public IpseProperties(String username, String password, String volume, String volumeProfile, String extendedCredentials, boolean enterprise, String userHomeFolder, SessionLevelParameters sessionLevelParameters) {
        super();
        this.username = username;
        this.password = password;
        this.volume = volume;
        this.volumeProfile = volumeProfile;
        this.extendedCredentials = extendedCredentials;
        this.enterprise = enterprise;
        this.userHomeFolder = userHomeFolder;
        this.sessionLevelParameters = sessionLevelParameters;
    }

    @JsonProperty("username")
    public String getUsername() {
        return isNull(username);
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public IpseProperties withUsername(String username) {
        this.username = username;
        return this;
    }

    @JsonProperty("password")
    public String getPassword() {
        return isNull(password);
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public IpseProperties withPassword(String password) {
        this.password = password;
        return this;
    }

    @JsonProperty("volume")
    public String getVolume() {
        return isNull(volume);
    }

    @JsonProperty("volume")
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public IpseProperties withVolume(String volume) {
        this.volume = volume;
        return this;
    }

    @JsonProperty("volumeProfile")
    public String getVolumeProfile() {
        return isNull(volumeProfile);
    }

    @JsonProperty("volumeProfile")
    public void setVolumeProfile(String volumeProfile) {
        this.volumeProfile = volumeProfile;
    }

    public IpseProperties withVolumeProfile(String volumeProfile) {
        this.volumeProfile = volumeProfile;
        return this;
    }

    @JsonProperty("extendedCredentials")
    public String getExtendedCredentials() {
        return isNull(extendedCredentials);
    }

    @JsonProperty("extendedCredentials")
    public void setExtendedCredentials(String extendedCredentials) {
        this.extendedCredentials = extendedCredentials;
    }

    public IpseProperties withExtendedCredentials(String extendedCredentials) {
        this.extendedCredentials = extendedCredentials;
        return this;
    }

    @JsonProperty("enterprise")
    public boolean isEnterprise() {
        return enterprise;
    }

    @JsonProperty("enterprise")
    public void setEnterprise(boolean enterprise) {
        this.enterprise = enterprise;
    }

    public IpseProperties withEnterprise(boolean enterprise) {
        this.enterprise = enterprise;
        return this;
    }

    @JsonProperty("userHomeFolder")
    public String getUserHomeFolder() {
        return isNull(userHomeFolder);
    }

    @JsonProperty("userHomeFolder")
    public void setUserHomeFolder(String userHomeFolder) {
        this.userHomeFolder = userHomeFolder;
    }

    public IpseProperties withUserHomeFolder(String userHomeFolder) {
        this.userHomeFolder = userHomeFolder;
        return this;
    }

    @JsonProperty("sessionLevelParameters")
    public SessionLevelParameters getSessionLevelParameters() {
        return sessionLevelParameters;
    }

    @JsonProperty("sessionLevelParameters")
    public void setSessionLevelParameters(SessionLevelParameters sessionLevelParameters) {
        this.sessionLevelParameters = sessionLevelParameters;
    }

    public IpseProperties withSessionLevelParameters(SessionLevelParameters sessionLevelParameters) {
        this.sessionLevelParameters = sessionLevelParameters;
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

    public IpseProperties withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(username).append(password).append(volume).append(volumeProfile).append(extendedCredentials).append(enterprise).append(userHomeFolder).append(sessionLevelParameters).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof IpseProperties) == false) {
            return false;
        }
        IpseProperties rhs = ((IpseProperties) other);
        return new EqualsBuilder().append(username, rhs.username).append(password, rhs.password).append(volume, rhs.volume).append(volumeProfile, rhs.volumeProfile).append(extendedCredentials, rhs.extendedCredentials).append(enterprise, rhs.enterprise).append(userHomeFolder, rhs.userHomeFolder).append(sessionLevelParameters, rhs.sessionLevelParameters).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(username);
        dest.writeValue(password);
        dest.writeValue(volume);
        dest.writeValue(volumeProfile);
        dest.writeValue(extendedCredentials);
        dest.writeValue(enterprise);
        dest.writeValue(userHomeFolder);
        dest.writeValue(sessionLevelParameters);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

    private String isNull(String value) {
        if(value == null) {
            return "";
        }

        return value;
    }

}