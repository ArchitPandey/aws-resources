### EBS Volume

- EBS volumes is network drive that can be attached to an EC2 instance. EBS volumes allows instances (to which they are attached) to persist data after they are terminated. 
- Analogy, think of EBS volumes as a hard drive that connects to machine using a LAN wire. Since EBS volume communicates to EC2 over a network, there is a latency involved in data transfer. Also since it's a volume we need to provision storage and io capacity per second when creating EBS volume.
- Since EBS volumes attach to an EC2 instance, they are bound to a specific availability zone. So an EBS volume create in US-east-1a can only attach to EC2 instances in US-east-1a.
- We can have one/ multiple EBS volumes attach / mount to single EC2 instance. We can also have multiple EC2 instances mount same EBS volume.
- To move data between instances in different AZs, we need to take snapshot of the EBS volume in first AZ and then we can spin up another EBS volume in second AZ and attach it to an EC2 instance in that AZ.
