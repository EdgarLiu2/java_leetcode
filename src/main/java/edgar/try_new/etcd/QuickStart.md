# Commands

## List members 
1. docker exec -t dev-leetcode_etcd-1_1 etcdctl member list
1. curl -L http://127.0.0.1:2379/v2/members

## Set & Get Value
1. curl -L http://127.0.0.1:2379/v2/keys/foo -XPUT -d value="Hello foo"
1. curl -L http://127.0.0.1:2379/v2/keys/foo

1. curl -L http://127.0.0.1:2379/v2/keys/foo2/foo1 -XPUT -d value="Hello foo1"
1. curl -L http://127.0.0.1:2379/v2/keys/foo2/foo2 -XPUT -d value="Hello foo2"
1. curl -L http://127.0.0.1:2379/v2/keys/foo2/foo21/foo21 -XPUT -d value="Hello foo21"
1. curl -L http://127.0.0.1:2379/v2/keys/foo2?recursive=true

## User Management
1. 加root用户，开启权限必须先加
docker exec -t dev-leetcode_etcd-1_1 etcdctl user add root:123
1. 权限管理开启
docker exec -t dev-leetcode_etcd-1_1 etcdctl auth enable
1. 查看用户
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 user list
1. 删除用户
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 user remove test_user
1. 
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 user add test_user:123
1. 加role
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 user grant --roles test_role test_user
1. 减role
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 user revoke --roles test_role test_user

## Role Management
1. 
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 role add test_role
1. 加权限
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 role grant --path "/*" --rw test_role
1. 减权限
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 role revoke --path "/*" --write test_role
1. 
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 role remove test_role
1.
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 role list
1.
docker exec -t dev-leetcode_etcd-1_1 etcdctl --username root:123 role get test_role


