package com.devicehive.model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO JavaDoc
 */
@Entity
@Table(name = "device_class")
public class DeviceClass {

    @SerializedName("id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @SerializedName("name")
    @Column
    @NotNull(message = "name field cannot be null.")
    @Size(min = 1, max = 128, message = "Field cannot be empty. The length of name shouldn't be more than 128 symbols.")
    private String name;

    @SerializedName("version")
    @Column
    @NotNull(message = "name field cannot be null.")
    @Size(min = 1, max = 32, message = "Field cannot be empty. The length of version shouldn't be more than 32 " +
            "symbols.")
    private String version;

    @SerializedName("isPermanent")
    @Column(name = "is_permanent")
    private Boolean isPermanent;

    @SerializedName("offlineTimeout")
    @Column(name = "offline_timeout")
    private Integer offlineTimeout;

    @SerializedName("data")
    @Column
    private String data;

    public DeviceClass() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getPermanent() {
        return isPermanent;
    }

    public void setPermanent(Boolean permanent) {
        isPermanent = permanent;
    }

    public Integer getOfflineTimeout() {
        return offlineTimeout;
    }

    public void setOfflineTimeout(Integer offlineTimeout) {
        this.offlineTimeout = offlineTimeout;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * Validates deviceClass representation. Returns set of strings which are represent constraint violations. Set will
     * be empty if no constraint violations found.
     * @param deviceClass
     * DeviceClass that should be validated
     * @param validator
     * Validator
     * @return Set of strings which are represent constraint violations
     */
    public static Set<String> validate(DeviceClass deviceClass, Validator validator) {
        Set<ConstraintViolation<DeviceClass>> constraintViolations = validator.validate(deviceClass);
        Set<String> result = new HashSet<>();
        if (constraintViolations.size()>0){
            for (ConstraintViolation<DeviceClass> cv : constraintViolations)
                result.add(String.format("Error! property: [%s], value: [%s], message: [%s]",
                        cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
        }
        return result;

    }
}
