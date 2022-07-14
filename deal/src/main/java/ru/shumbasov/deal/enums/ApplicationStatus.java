package ru.shumbasov.deal.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ApplicationStatus {
    @JsonProperty("preapproval")
    PREAPPROVAL,
    @JsonProperty("approved")
    APPROVED,
    @JsonProperty("ccdenied")
    CC_DENIED,
    @JsonProperty("ccapproved")
    CC_APPROVED,
    @JsonProperty("preparedocuments")
    PREPARE_DOCUMENTS,
    @JsonProperty("documentcreated")
    DOCUMENT_CREATED,
    @JsonProperty("clientdenied")
    CLIENT_DENIED,
    @JsonProperty("documentsigned")
    DOCUMENT_SIGNED,
    @JsonProperty("creditissued")
    CREDIT_ISSUED;
    }
