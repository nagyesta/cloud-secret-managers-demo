#!/bin/bash

echo "########### Creating profile ###########"

aws configure set aws_access_key_id default_access_key --profile=localstack
aws configure set aws_secret_access_key default_secret_key --profile=localstack
aws configure set region us-west-2 --profile=localstack

echo "########### Listing profile ###########"
aws configure list --profile=localstack

echo "########### Creating secrets ###########"
aws secretsmanager create-secret --endpoint-url=http://localhost:4566 --name database-connection-url --secret-string "jdbc:mysql://localhost:13306/" --profile=localstack || echo "ERROR"
aws secretsmanager create-secret --endpoint-url=http://localhost:4566 --name database-driver --secret-string "com.mysql.cj.jdbc.Driver" --profile=localstack || echo "ERROR"
aws secretsmanager create-secret --endpoint-url=http://localhost:4566 --name database-username --secret-string "root" --profile=localstack || echo "ERROR"
aws secretsmanager create-secret --endpoint-url=http://localhost:4566 --name database-password --secret-string "e8ce8764-dad6-41de-a2fc-ef905bda44fb" --profile=localstack || echo "ERROR"
echo "########### Secrets created ###########"
