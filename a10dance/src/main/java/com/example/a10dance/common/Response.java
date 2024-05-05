package com.example.a10dance.common;

public class Response {

    /**
     * A descriptive message providing information about the error.
     */
    public String error;

    /**
     * A descriptive message providing information about the logout operation.
     */
    public String message;

    /**
     * The HTTP status code indicating the success or failure of the logout operation.
     */
    private int status;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * Retrieves the message associated with the logout response.
     *
     * @return The logout message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the logout response.
     *
     * @param message The logout message to be set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the HTTP status code associated with the logout response.
     *
     * @return The HTTP status code.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code associated with the logout response.
     *
     * @param status The HTTP status code to be set.
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
