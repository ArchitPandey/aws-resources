### Interacting with Dynamo DB using APIs

#### Dependency
    `<dependency>
	<groupId>com.amazonaws</groupId>
	<artifactId>aws-java-sdk-dynamodb</artifactId>
	<version>1.12.89</version>
    </dependency>`
    
#### Bean with connection details
[https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GettingStarted.Java.Summary.html](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GettingStarted.Java.Summary.html)

For dynamo db downloaded in local:
`AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
.build();`

For dynamo db AWS service:
`AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
.withRegion(Regions.US_WEST_2)
.build();`

#### Performing Table operations
[https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GettingStarted.Java.01.html](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GettingStarted.Java.01.html)
