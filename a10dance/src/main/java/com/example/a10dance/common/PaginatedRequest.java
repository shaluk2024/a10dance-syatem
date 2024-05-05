package com.example.a10dance.common;

/**
 * Request for retrieving a page of records.
 */
public abstract class PaginatedRequest extends Request {

    /**
     * The page number to retrieve.
     */
    private int pageNumber;

    /**
     * The number of records to retrieve.
     */
    private int pageSize;

    /**
     * Gets the page number to retrieve.
     *
     * @return The page number to retrieve.
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the requested page number for the paginated request.
     *
     * @param pageNumber The requested page number to be set.
     */
    public void setPageNumber(final int pageNumber) {
        this.pageNumber = Math.max(0, pageNumber);
    }

    /**
     * Gets the number of records to retrieve.
     *
     * @return The number of records to retrieve.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the requested number of records per page for the paginated request.
     * Ensures that the page size is within a valid range (1 to 20).
     *
     * @param pageSize The requested number of records per page to be set.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = Math.min(20, Math.max(1, pageSize));
    }
}