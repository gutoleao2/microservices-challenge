package com.williams.crud.exception;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 558018138956763566L;

    private Date timestamp;
    private String messageError;
    private String details;


}
