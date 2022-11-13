package com.ahmetemre90.bookapp.exception;

import com.ahmetemre90.bookapp.dto.ErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericException extends RuntimeException {

    private HttpStatus httpStatus;

    private ErrorCode errorCode;

    private String errorMessage;
}
