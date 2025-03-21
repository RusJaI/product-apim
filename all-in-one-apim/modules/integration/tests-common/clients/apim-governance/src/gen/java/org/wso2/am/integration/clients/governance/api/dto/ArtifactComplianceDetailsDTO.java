/*
 * WSO2 API Manager - Governance
 * This document specifies a **RESTful API** for WSO2 **API Manager** - Governance. 
 *
 * The version of the OpenAPI document: v1.1
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.governance.api.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.wso2.am.integration.clients.governance.api.dto.ArtifactInfoDTO;
import org.wso2.am.integration.clients.governance.api.dto.PolicyAdherenceWithRulesetsDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
/**
* Provides compliance details of an artifact.
*/
    @ApiModel(description = "Provides compliance details of an artifact.")

public class ArtifactComplianceDetailsDTO {
        public static final String SERIALIZED_NAME_ID = "id";
        @SerializedName(SERIALIZED_NAME_ID)
            private String id;

            /**
* Status of the artifact&#39;s governance compliance.
*/
    @JsonAdapter(StatusEnum.Adapter.class)
public enum StatusEnum {
        COMPLIANT("COMPLIANT"),
        
        NON_COMPLIANT("NON-COMPLIANT"),
        
        NOT_APPLICABLE("NOT-APPLICABLE"),
        
        PENDING("PENDING");

private String value;

StatusEnum(String value) {
this.value = value;
}

public String getValue() {
return value;
}

@Override
public String toString() {
return String.valueOf(value);
}

public static StatusEnum fromValue(String value) {
    for (StatusEnum b : StatusEnum.values()) {
    if (b.name().equals(value)) {
        return b;
    }
}
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
}

    public static class Adapter extends TypeAdapter<StatusEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
    jsonWriter.value(enumeration.getValue());
    }

    @Override
    public StatusEnum read(final JsonReader jsonReader) throws IOException {
    String value =  jsonReader.nextString();
    return StatusEnum.fromValue(value);
    }
    }
}

        public static final String SERIALIZED_NAME_STATUS = "status";
        @SerializedName(SERIALIZED_NAME_STATUS)
            private StatusEnum status;

        public static final String SERIALIZED_NAME_INFO = "info";
        @SerializedName(SERIALIZED_NAME_INFO)
            private ArtifactInfoDTO info;

        public static final String SERIALIZED_NAME_GOVERNED_POLICIES = "governedPolicies";
        @SerializedName(SERIALIZED_NAME_GOVERNED_POLICIES)
            private List<PolicyAdherenceWithRulesetsDTO> governedPolicies = null;


        public ArtifactComplianceDetailsDTO id(String id) {
        
        this.id = id;
        return this;
        }

    /**
        * UUID of the artifact.
    * @return id
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "123e4567-e89b-12d3-a456-426614174000", value = "UUID of the artifact.")
    
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


        public ArtifactComplianceDetailsDTO status(StatusEnum status) {
        
        this.status = status;
        return this;
        }

    /**
        * Status of the artifact&#39;s governance compliance.
    * @return status
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "COMPLIANT", value = "Status of the artifact's governance compliance.")
    
    public StatusEnum getStatus() {
        return status;
    }


    public void setStatus(StatusEnum status) {
        this.status = status;
    }


        public ArtifactComplianceDetailsDTO info(ArtifactInfoDTO info) {
        
        this.info = info;
        return this;
        }

    /**
        * Get info
    * @return info
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "")
    
    public ArtifactInfoDTO getInfo() {
        return info;
    }


    public void setInfo(ArtifactInfoDTO info) {
        this.info = info;
    }


        public ArtifactComplianceDetailsDTO governedPolicies(List<PolicyAdherenceWithRulesetsDTO> governedPolicies) {
        
        this.governedPolicies = governedPolicies;
        return this;
        }

    /**
        * List of policies under which the artifact was governed.
    * @return governedPolicies
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(value = "List of policies under which the artifact was governed.")
    
    public List<PolicyAdherenceWithRulesetsDTO> getGovernedPolicies() {
        return governedPolicies;
    }


    public void setGovernedPolicies(List<PolicyAdherenceWithRulesetsDTO> governedPolicies) {
        this.governedPolicies = governedPolicies;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
            ArtifactComplianceDetailsDTO artifactComplianceDetails = (ArtifactComplianceDetailsDTO) o;
            return Objects.equals(this.id, artifactComplianceDetails.id) &&
            Objects.equals(this.status, artifactComplianceDetails.status) &&
            Objects.equals(this.info, artifactComplianceDetails.info) &&
            Objects.equals(this.governedPolicies, artifactComplianceDetails.governedPolicies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, info, governedPolicies);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class ArtifactComplianceDetailsDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    governedPolicies: ").append(toIndentedString(governedPolicies)).append("\n");
sb.append("}");
return sb.toString();
}

/**
* Convert the given object to string with each line indented by 4 spaces
* (except the first line).
*/
private String toIndentedString(Object o) {
if (o == null) {
return "null";
}
return o.toString().replace("\n", "\n    ");
}

}

