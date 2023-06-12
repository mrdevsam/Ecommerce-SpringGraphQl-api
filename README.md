# Ecommerce-SpringGraphQl-api

Follow the instructions before compiling the project.

In linux:
 * Open terminal in the root directory of this project and execute the commands.
  ----

     $ cd src/main/resources
     $ mkdir certs && cd certs
     $ openssl genrsa -out keypair.pem 2048
     $ openssl rsa -in keypair.pem -pubout -out public.pem
     $ openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
     $ rm keypair.pem

  ----
