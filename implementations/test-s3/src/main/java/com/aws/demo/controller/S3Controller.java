package com.aws.demo.controller;

import com.aws.demo.model.BucketExistsRequest;
import com.aws.demo.model.BucketExistsResponse;
import com.aws.demo.model.CreateS3BucketRequest;
import com.aws.demo.model.CreateS3BucketResponse;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    private S3Client s3Client;

    S3Controller(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @PostMapping("/bucketExists")
    public BucketExistsResponse bucketExists(@RequestBody BucketExistsRequest bucketExistsRequest) {
        HeadBucketRequest headBucketRequest = HeadBucketRequest
                .builder()
                .bucket(bucketExistsRequest.getBucketName())
                .build();

        try {
            HeadBucketResponse headBucketResponse = this.s3Client.headBucket(headBucketRequest);
            return BucketExistsResponse.builder().bucketExists(true).build();
        } catch (NoSuchBucketException e) {
            return BucketExistsResponse.builder().bucketExists(false).build();
        }
    }

    @PostMapping("/createBucket")
    public CreateS3BucketResponse createBucket(@RequestBody CreateS3BucketRequest createS3BucketRequest) {

        try {
            S3Waiter s3Waiter = s3Client.waiter();

            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket(createS3BucketRequest.getBucketName()).build();
            CreateBucketResponse createBucketResponse = s3Client.createBucket(createBucketRequest);

            HeadBucketRequest headBucketRequest = HeadBucketRequest.builder().bucket(createS3BucketRequest.getBucketName()).build();
            WaiterResponse<HeadBucketResponse> headBucketWaiterResponse = s3Waiter.waitUntilBucketExists(headBucketRequest);

            if(headBucketWaiterResponse.matched().response().isPresent()) {
                return CreateS3BucketResponse.builder().success(true).response(headBucketWaiterResponse.matched().response().get()).build();
            }
            else {
                if(headBucketWaiterResponse.matched().exception().isPresent()) {
                    return CreateS3BucketResponse.builder().success(false).errorMsg(headBucketWaiterResponse.matched().exception().get().getMessage()).build();
                } else {
                    return CreateS3BucketResponse.builder().success(false).errorMsg("Unable to determine status of bucket creation").build();
                }
            }

        } catch(BucketAlreadyOwnedByYouException | BucketAlreadyExistsException e) {
            return CreateS3BucketResponse.builder().success(false).errorMsg(e.getMessage()).build();
        }
    }
}
