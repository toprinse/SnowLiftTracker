#ifndef PUBLISHER_H
#define PUBLISHER_H

#include "string.h"
#include "string"
#include "unistd.h"
#include "czmq.h"
#include "assert.h"

using namespace std;

class Publisher{
public: 
    Publisher(void) = delete;
    Publisher(std::string identity);
    virtual ~Publisher();
    Publisher& operator=(Publisher&) = delete;

    /* ZMQ offer to either 'bind' or 'connect', the default action of a PUB is to bind.
     * But to communicate with our `Proxy` we need to 'connect'. 
     * In this small API we go by ZMQ logic and bind by defauls.
     * @attention ZMQ support various Inter process protocols like tcp, inproc, pgm, epgm and vmci.
     * @param `address` : the ZMQ endpoint (eg : "ipc://myendpoint").
     * @param `connect` : Set to `true` to connect, default `false`.
     */
    int Bind(std::string address, bool connect=false);

    /* Basic Publish method
     * @param `topic` : topic to send data to.  
     * @param `data` : data to transmit.
     */
    int Publish(std::string topic, std::string data);

private:
    // ZMQ socket
    zsock_t* mPublisher;

    std::string mIdentity;
    // ZMQ helper function
    inline static int s_send(void *socket, const char *string, int flags = 0) {
        int rc;
        zmq_msg_t message;
        rc = zmq_msg_init_data(&message, (void*)string, strlen(string), NULL, NULL);
        printf("init data rc = %i : %s\n", rc, strerror(rc));
        rc = zmq_msg_send(&message, socket, flags);
        printf("s_send rc = %i : %s\n", rc, strerror(rc));
        assert(-1 != rc);
        zmq_msg_close(&message);
        return (rc);
    }
};

#endif
