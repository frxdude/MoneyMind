package com.cs316.money_mind.dto.request.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * AddRequest
 *
 * @author Sainjargal Ishdorj
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionRequest {

    @ApiModelProperty(notes = "Transaction amount", name = "amount", required = true, value = "33.3")
    @NotNull(message = "{val.not.null}")
    private Double amount;

    @ApiModelProperty(notes = "Transaction category id", name = "categoryId", required = true, value = "3")
    @NotNull(message = "{val.not.null}")
    private Long categoryId;

    @ApiModelProperty(notes = "Transaction date time", name = "dateTime", required = true, value = "2021-03-12T00:09:10")
    @NotNull(message = "{val.not.null}")
    private LocalDateTime dateTime;

    @ApiModelProperty(notes = "Transaction type", name = "type", required = true, value = "revenue")
    @NotBlank(message = "{val.not.null}")
    @Pattern(regexp = "^revenue$|^expense$", message = "{val.type}")
    private String type;

}
