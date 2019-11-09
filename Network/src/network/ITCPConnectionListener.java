package network;

public interface ITCPConnectionListener {

    void onConnectionReady(TCPConnection tcpConnection);
    void onReceiveString(TCPConnection tcpConnection,String value);
    void onDisconnect(TCPConnection tcpConnection);
    void onException(TCPConnection tcpConnection,Exception e);
}
