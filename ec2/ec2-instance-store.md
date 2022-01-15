### EC2 Instance store:

- EC2 Instace store is the storage, EC2 instance is physically attached to. EC2 instance store is faster than EBS (as EBS is network attached).
- EC2 Instance storage is lost when EC2 instance is stopped. So EC2 instance storage is an ephemeral storage.
- EC2 Instance storage is good when we need very high IOPS but it's okay to lose the data. For eg. for buffer, cache, scratch data or temporary content.
