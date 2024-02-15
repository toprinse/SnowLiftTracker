#ifndef PROXY_H
#define PROXY_H

#include "stdlib.h"
#include "string.h"
#include "stdio.h"
#include "thread"
#include "mutex"
#include "time.h"
#include "czmq.h"
#include "unordered_map"

// const char *PROXY_SUBSCRIBER_ADDRESS = "ipc://frontend";
// const char *PROXY_PUBLISHER_ADDRESS = "ipc://backend";

// const int POLLER_TIMEOUT_MS = -1; // Wait indefinitely
// const int XPUB_VERBOSER = 1; //permit to have multiple sub-unsub notification for the same topic.

using namespace std;

class Proxy{
public:
    Proxy();
    ~Proxy();

    int StartProxy(void); 
    int StopProxy(void);
    
private:
//zmq Attributs
    void *mContext;
    void *mSubscriber;
    void *mPublisher; 
    void *mReader;

    //We prefer to pass by a Map to be independent from ZMQ (zhash)
    //map<topic, payload> payload to reconstruct a ForumObject
    std::unordered_map<std::string, std::string> mCache; 
    std::thread mProxyThread;

    void Initialize(void);

    //void Send(void *socket, const int flagMore );

    //void Receive(void *socket, const int flagWait);

    void Forward();

    void HandleSubscription();
};

#endif