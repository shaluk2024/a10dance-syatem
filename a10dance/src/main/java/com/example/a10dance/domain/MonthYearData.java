package com.example.a10dance.domain;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serial;

@Entity
@Table(name = "month_year_data")
public class MonthYearData {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "month_value", nullable = false, updatable = false)
    @NotNull
    private Integer monthValue;

    @Column(name = "year_value", nullable = false, updatable = false)
    @NotNull
    private Integer yearValue;

    MonthYearData() {
        super();
    }

    public MonthYearData(Integer monthValue, Integer yearValue) {
        this.monthValue = monthValue;
        this.yearValue = yearValue;
    }

    public Integer getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Integer monthValue) {
        this.monthValue = monthValue;
    }

    public Integer getYearValue() {
        return yearValue;
    }

    public void setYearValue(Integer yearValue) {
        this.yearValue = yearValue;
    }
}
