package com.aws.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BucketExistsResponse {

    boolean bucketExists;

}
