package qq.service.contact

import com.tencent.qqnt.kernel.nativeinterface.Contact
import com.tencent.qqnt.kernel.nativeinterface.MsgConstant
import io.kritor.message.Scene

suspend fun Contact.longPeer(): Long {
    return when(this.chatType) {
        MsgConstant.KCHATTYPEGROUP -> peerUid.toLong()
        MsgConstant.KCHATTYPETEMPC2CFROMGROUP, MsgConstant.KCHATTYPEC2C -> if (peerUid.startsWith("u_")) ContactHelper.getUinByUidAsync(peerUid).toLong() else peerUid.toLong()
        else -> 0L
    }
}

suspend fun io.kritor.message.Contact.longPeer(): Long {
    return when(this.scene) {
        Scene.GROUP -> peer.toLong()
        Scene.FRIEND, Scene.STRANGER, Scene.STRANGER_FROM_GROUP -> if (peer.startsWith("u_")) ContactHelper.getUinByUidAsync(peer).toLong() else peer.toLong()
        else -> 0L
    }
}