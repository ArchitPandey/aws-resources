### EBS Volume Types

EBS volumes are segregated or categorized by size and iops (throughput). There are six types of EBS volumes.

- gp2 and gp3 (ssd): General purpose SSD that balances price and performance.
- io1 and io2 (ssd): Highest performance SSD for mission critical low-latency and high-throughput workload.
- st1 (hdd): low cost hdd for frequently accessed low throughput workloads
- sc1 (hdd): lowest cost hdd for less frequently accessed workloads.

only gp2/gp3 or io1/io2 can be used as boot volumes.

#### More on these:
- gp2 and gp3 (ssd): They are cost-effective and low-latency. gp3 is newer generate of volumes, gp2 is older one. gp3 has more iops compare to gp2.
    - gp3 has baseline of 3000 iops and throughput 125Mib/s
    - gp3 can increase iops to 16000 and throughput upto 1000Mib/s. iops are not linked to the amount of storage.
    - gp2 is smaller. can burst upto 3000 iops.
    - gp2 iops is linked to size of volume. max 16000 iops supported. 3 iops per Gb. so at 53334 GB we max out the iops, no iops increase after that. 

- provisioned iops instances (between 4Gib -  are useful for critical business applications that need sustained iops performance. or applications that need more than 16000 iops. like database workloads. we can attach single io1 / io2 to multiple ec2 instances (only io1 / io2 type ebs volumes support this multi-attach functionality). 64000 is max iops we can get from io1/io2 instances (and 256000 iops if using io2 block express drive)

- st1 and sc1 (hdd): can be 125Mib to 16Tib. st1 is throughput optimized and good for big data, data warehousing and log processing. max throughput is 500Mib/s and max iops is 500. sc1 used for cases that need infrequent access and scenarios where lowest cost is important. max iops is 250 and max thoughput is 250Mib/s.

