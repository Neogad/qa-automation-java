package com.tinkoff.edu.retrofit;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Generated("jsonschema2pojo")
public class Client {


    public Client(@JsonProperty("login") String login, @JsonProperty("salt") String salt, @JsonProperty("secret") String secret) {
        this.login = login;
        this.salt = salt;
        this.secret = secret;
    }

    @JsonProperty("id")
    @JsonPropertyDescription("Client id for auth")
    private int id;

    @JsonProperty("login")
    @JsonPropertyDescription("Client login for auth")
    private String login;

    @JsonProperty("salt")
    @JsonPropertyDescription("Client salt")
    private String salt;

    @JsonProperty("secret")
    @JsonPropertyDescription("Client secret")
    private String secret;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @JsonProperty("login")
    public String getLogin() {
        return login;
    }


    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Client salt
     * (Required)
     *
     */
    @JsonProperty("salt")
    public String getSalt() {
        return salt;
    }

    /**
     * Client salt
     * (Required)
     *
     */
    @JsonProperty("salt")
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Client secret
     * (Required)
     *
     */
    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    /**
     * Client secret
     * (Required)
     *
     */
    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", salt='" + salt + '\'' +
                ", secret='" + secret + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}