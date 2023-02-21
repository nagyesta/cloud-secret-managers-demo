disable_mlock = true
storage "inmem" {}

listener "tcp" {
  address     = "0.0.0.0:18201"
  tls_disable = 1
}

ui                = true
max_lease_ttl     = "7200h"
default_lease_ttl = "7200h"
api_addr          = "http://127.0.0.1:18201"
