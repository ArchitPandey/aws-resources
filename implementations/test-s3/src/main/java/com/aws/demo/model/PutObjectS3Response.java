package com.aws.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PutObjectS3Response {

    private boolean status;

    private String eTag;

    private String charge;

    private String message;
}
