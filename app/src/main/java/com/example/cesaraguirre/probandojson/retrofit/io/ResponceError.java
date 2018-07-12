package com.example.cesaraguirre.probandojson.retrofit.io;

public class ResponceError {

    private boolean error;

    public ResponceError() {
    }

    public ResponceError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
