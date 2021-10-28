#### S3 bucket policies:
S3 bucket polices allow to provide specific permissions to S3 buckets. The examples of S3 bucket policies are provided in this link: 
[S3 bucket policy examples](https://docs.aws.amazon.com/AmazonS3/latest/userguide/example-bucket-policies.html)

#### S3 ACLs:
1. About ACLs
2. ACL structure
3. ACL permissions
4. S3 groups
5. x-amz-* request headers
6. Canned ACLs x-amz-acl
[https://docs.aws.amazon.com/AmazonS3/latest/userguide/acl-overview.html](https://docs.aws.amazon.com/AmazonS3/latest/userguide/acl-overview.html)

7. Creating ACLs using request headers (x-amz-) and request body (using acl xml). Some examples of creating acls with CreateBucket, PutBucketAcl, PutObjectAcl. 
[https://docs.aws.amazon.com/AmazonS3/latest/API/API_CreateBucket.html](https://docs.aws.amazon.com/AmazonS3/latest/API/API_CreateBucket.html)
[https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutBucketAcl.html](https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutBucketAcl.html)
[https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObjectAcl.html](https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObjectAcl.html)

8. Some examples using acls (x-amz-) in condition part of amazon policy. x-amz- acls can be used in Condition part of policy with s3: prefix like this : s3:x-amz-.
[https://docs.aws.amazon.com/AmazonS3/latest/userguide/amazon-s3-policy-keys.html](https://docs.aws.amazon.com/AmazonS3/latest/userguide/amazon-s3-policy-keys.html)


