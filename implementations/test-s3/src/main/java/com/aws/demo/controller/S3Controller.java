package com.aws.demo.controller;

import com.aws.demo.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
                return CreateS3BucketResponse.builder().success(true).message(headBucketWaiterResponse.matched().response().get().toString()).build();
            }
            else {
                if(headBucketWaiterResponse.matched().exception().isPresent()) {
                    return CreateS3BucketResponse.builder().success(false).message(headBucketWaiterResponse.matched().exception().get().getMessage()).build();
                } else {
                    return CreateS3BucketResponse.builder().success(false).message("Unable to determine status of bucket creation").build();
                }
            }

        } catch(BucketAlreadyOwnedByYouException | BucketAlreadyExistsException e) {
            return CreateS3BucketResponse.builder().success(false).message(e.getMessage()).build();
        }
    }

    @GetMapping("/listBucket")
    public List<String> listBuckets() {
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse response = s3Client.listBuckets(listBucketsRequest);
        return response.buckets().stream().map(e -> e.toString()).collect(Collectors.toList());
    }

    @PostMapping("/putObject")
    public PutObjectS3Response putObject(@RequestParam("file") MultipartFile file, @RequestParam("bucket") String bucket, @RequestParam("key") String key) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucket).key(key).build();
            PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
            return PutObjectS3Response.builder().eTag(putObjectResponse.eTag()).charge(putObjectResponse.requestChargedAsString()).status(true).build();
        } catch(Exception e) {
            return PutObjectS3Response.builder().status(false).message(e.getMessage()).build();
        }
    }

    @GetMapping("/getObject")
    public ResponseEntity<byte[]> getObject(@RequestParam("bucket") String bucket, @RequestParam("key") String key) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucket).key(key).build();
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return new ResponseEntity<byte[]>(responseBytes.asByteArray(), HttpStatus.OK);
    }
}
