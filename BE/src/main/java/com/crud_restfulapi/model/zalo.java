package com.crud_restfulapi.model;

public class zalo {
    private String error_name;
    private String       error_reason;
    private String ref_doc;
    private String       error_description;
    private int error;

    public String getError_name() {
        return error_name;
    }

    public void setError_name(String error_name) {
        this.error_name = error_name;
    }

    public String getError_reason() {
        return error_reason;
    }

    public void setError_reason(String error_reason) {
        this.error_reason = error_reason;
    }

    public String getRef_doc() {
        return ref_doc;
    }

    public void setRef_doc(String ref_doc) {
        this.ref_doc = ref_doc;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
