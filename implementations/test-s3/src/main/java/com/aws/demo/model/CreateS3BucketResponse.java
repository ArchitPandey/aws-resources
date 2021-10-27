package com.aws.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.model.HeadBucketResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateS3BucketResponse {

    private boolean success;

    private String message;
}
