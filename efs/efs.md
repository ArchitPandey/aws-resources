#### EFS - Elastic File System

- EFS is network file sysstem (NFS). Many EC2 instances can connect to it. 
- Multiple EC2 instances in different availability zones can connect to it.
- After creating EFS, you add a security group to it and that security group is used to manage incoming connections to EFS.
- EFS is highly available, scalable and you only pay for the amount of data you store. It is costlier though.

- Use case of EFS is web serving, data sharing, wordpress
- Compatible only with POSIX systems (so only Linux based file systems, not windows)

##### Scale: 
- can support 1000 of concurrent clients (accessing efs file system) and data transfer upto 10+ GB/s
- grows in size automatically as size of data on EFS increases
- we have an option to say (during EFS creating from aws console) if file is not used in last 'x' days, move it to infrequent access storage class. this storage class has a cost to retrieve file but lower cost to store it.

#### Mount Targets
- when creating EFS we have to specify mount targets. each mount target contains availablity zone, security group. each mount target will provide an endpoint at which we can mount the efs system (which means ec2 instances from three availability zones can contact our file system).

ec2 ---- efs mount target -----|
                               |-------efs
ec2 ---- efs mount target -----| 

#### Connecting EC2-to-EFS
- install amazon-efs-utils package to ec2
- make dir efs in ec2
- allow traffic from ec2 to efs. for this go the security group of efs and edit inbound rule and allow traffic from ec2 security group. inbound rule type will be NFS as EFS uses standart NFS protocol for communication
- run sudo mount -t efs -o tls <efs-instance>/: efs
- now if you go to efs directory and create some file, it will be created in efs file system and other instances can also access that file. so it's a shared file system.
