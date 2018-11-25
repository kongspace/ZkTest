package zk.api.test;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author KQS
 * @since 2018/11/24.
 */
public class CreateSessionSync implements Watcher {
    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {

        if(watchedEvent.getState().equals(Event.KeeperState.SyncConnected)) {
            doBus();
        }
    }

    private void doBus() {
        try {
            if(null != zooKeeper.exists("/note_scot/note_scot_a",false)) {
                System.out.println("/note_scot/note_scot_a 节点已存在");
                return;
            }
            String path = zooKeeper.create("/note_scot/note_scot_a","aa".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);


           /*权限相关
            try {
                ACL aclIp = new ACL(ZooDefs.Perms.READ,new Id("ip","127.0.0.1"));
                ACL aclDigest = new ACL(ZooDefs.Perms.READ| ZooDefs.Perms.WRITE,
                        new Id("digest", DigestAuthenticationProvider.generateDigest("id:pass")));
                zooKeeper.addAuthInfo("digest", "id:pass".getBytes());　
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }*/

            System.out.println("zookeeper return:" + path);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper("192.168.117.128:2181",5000, new CreateSessionSync());
            System.out.println(zooKeeper.getState());
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
