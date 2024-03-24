package com.coderhack.coderhack.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdatedScoreDto {

    @Min(0)
    @Max(100)
    Integer score;
}
