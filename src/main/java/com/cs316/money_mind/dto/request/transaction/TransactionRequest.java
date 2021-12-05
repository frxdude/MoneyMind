package com.cs316.money_mind.dto.request.transaction;

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

    @NotNull(message = "{val.not.null}")
    private Double value;

    @NotNull(message = "{val.not.null}")
    private LocalDateTime dateTime;

    @NotBlank(message = "{val.not.null}")
    @Pattern(regexp = "^revenue$|^expense$", message = "{val.type}")
    private String type;

}
