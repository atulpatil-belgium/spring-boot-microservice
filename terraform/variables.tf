variable "aws_region" {
  default = "eu-west-3"
}

variable "services" {
  type    = list(string)
  default = ["eureka-server-service", "order-service", "payment-service", "notification-service", "user-service"]
}

variable "account_id" {
  description = "AWS ACCOUNT ID"
  type        = string
}

variable "vpc_id" {
  description = "VPC ID"
  type        = string
}

variable "subnets" {
    type    = list(string)
    default = ["subnet-040c0fcad42b57e3f", "subnet-02b52e4749fc80f46"]
}

