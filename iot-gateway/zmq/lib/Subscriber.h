#ifndef SUBSCRIBER_H
#define SUBSCRIBER_H

#include "string.h"
#include "string"
#include "unistd.h"
#include "czmq.h"

#define POLLER_TIMEOUT 1000

using namespace std;

class Subscriber{
public: 
    Subscriber(void)= delete;
    Subscriber(string identity);
    virtual ~Subscriber();
    Subscriber& operator=(Subscriber&) = delete;
    
    /* ZMQ offer to either 'bind' or 'connect', the default action of a PUB is to bind.
     * But to communicate with our `Proxy` we need to 'connect'. 
     * In this small API we go by ZMQ logic and bind by defauls.
     * @attention ZMQ support various Inter process protocols like tcp, inproc, pgm, epgm and vmci.
     * @param `address` : the ZMQ endpoint (eg : "ipc://myendpoint").
     * @param `bind` : Set to `true` to bind, default `false`.
     */
    int Connect(string address, bool bind=false);

    /* Basic Subscribe method
     * @param `topic` : topic to receive data on.  
     */
    int Subscribe(string topic);

    /* Basic blocking Receive method
     * @param `data` : data.  
     */
    int ReceiveBlock(string &data);

    /* Basic non blocking Receive method
     * @param `data` : data.  
     */
    int ReceiveNoBlock(string &data);

private:
    // ZMQ socket
    zsock_t* mSubscriber;

    string mIdentity;
};

#endif
