package com.example.a10dance.common;

/**
 * Response for retrieving a page of records.
 */
public abstract class PaginatedResponse extends Response {

    /**
     * The page number associated with the paginated response.
     */
    private int pageNumber;

    /**
     * The size of each page in the paginated response.
     */
    private int pageSize;

    /**
     * The total number of elements in the entire dataset, not just the current page.
     */
    private long total;

    /**
     * Retrieves the page number associated with the paginated response.
     *
     * @return The page number.
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the page number for the paginated response.
     *
     * @param pageNumber The page number to be set.
     */
    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Retrieves the size of each page in the paginated response.
     *
     * @return The page size.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the size of each page for the paginated response.
     *
     * @param pageSize The page size to be set.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Retrieves the total number of elements in the entire dataset.
     *
     * @return The total number of elements.
     */
    public long getTotal() {
        return total;
    }

    /**
     * Sets the total number of elements for the paginated response.
     *
     * @param total The total number of elements to be set.
     */
    public void setTotal(final long total) {
        this.total = total;
    }
}
