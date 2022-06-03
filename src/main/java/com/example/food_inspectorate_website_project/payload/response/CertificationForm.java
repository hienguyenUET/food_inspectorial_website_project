package com.example.food_inspectorate_website_project.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Setter
@Getter
@ToString
public class CertificationForm {
    @JsonProperty("reg_number")
    private String regNumber;
    @JsonProperty("certificate_number")
    private String certNumber;
    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty("expiration_date")
    private Date expirationDate;
}
