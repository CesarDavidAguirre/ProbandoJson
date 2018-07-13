package com.example.cesaraguirre.probandojson.retrofit.io;

public class ResponceError {

    private int error;

    public ResponceError() {
    }

    public ResponceError(int error) {
        this.error = error;
    }

    public int isError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

}
